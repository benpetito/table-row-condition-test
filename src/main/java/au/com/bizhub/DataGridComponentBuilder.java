package au.com.bizhub;

import java.util.Collections;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;

import org.apache.commons.lang3.StringUtils;
import org.skyve.domain.types.converters.Converter;
import org.skyve.domain.types.converters.Format;
import org.skyve.impl.metadata.view.HorizontalAlignment;
import org.skyve.impl.metadata.view.event.RerenderEventAction;
import org.skyve.impl.metadata.view.widget.bound.input.CheckBox;
import org.skyve.impl.metadata.view.widget.bound.input.Combo;
import org.skyve.impl.metadata.view.widget.bound.input.TextField;
import org.skyve.impl.metadata.view.widget.bound.tabular.AbstractDataWidget;
import org.skyve.impl.metadata.view.widget.bound.tabular.DataGridBoundColumn;
import org.skyve.impl.web.faces.pipeline.component.ResponsiveComponentBuilder;
import org.skyve.util.Util;

/**
 * Custom component builder to allow data grid to specify individual rows to be enabled
 * or visible based on a condition.
 * 
 * @author benpetito
 */
public class DataGridComponentBuilder extends ResponsiveComponentBuilder {
	// names of the properties to refer to within the data grid
	public static final String COLLECTION_BINDING_KEY = COLLECTION_BINDING_ATTRIBUTE_KEY;
	public static final String EDITABLE_CONDITION_KEY = "editableCondition";
	public static final String VISIBLE_CONDITION_KEY = "visibleCondition";
	public static final String RERENDER_KEY = "rerender";

	private String boundColumnEditableCondition,
			boundColumnVisibleCondition,
			boundColumnProcess,
			boundColumnUpdate,
			collectionBinding,
			rerenderEventName;
	
	@Override
	public UIComponent addDataGridBoundColumn(UIComponent component, UIComponent current, AbstractDataWidget widget,
			DataGridBoundColumn column, String dataWidgetVar, String columnTitle, String columnBinding,
			StringBuilder gridColumnExpression, HorizontalAlignment alignment) {
		final UIComponent col = super.addDataGridBoundColumn(component, current, widget, column, dataWidgetVar, columnTitle,
				columnBinding,
				gridColumnExpression,
				alignment);

		final String collectionBindingName = column.getProperties().get(COLLECTION_BINDING_KEY);
		if (StringUtils.isNotBlank(collectionBindingName)) {
			collectionBinding = collectionBindingName;
		}

		final String editableCondition = column.getProperties().get(EDITABLE_CONDITION_KEY);
		if (StringUtils.isNotBlank(editableCondition)) {
			boundColumnEditableCondition = editableCondition;
		}

		final String visibleCondition = column.getProperties().get(VISIBLE_CONDITION_KEY);
		if (StringUtils.isNotBlank(visibleCondition)) {
			boundColumnVisibleCondition = visibleCondition;
		}

		if (column.getProperties().containsKey(RERENDER_KEY)) {
			rerenderEventName = column.getProperties().get(RERENDER_KEY);
			boundColumnProcess = column.getProperties().get(PROCESS_KEY);
			boundColumnUpdate = column.getProperties().get(UPDATE_KEY);
		}

		return col;
	}

	@Override
	public EventSourceComponent checkBox(EventSourceComponent component, String dataWidgetVar, CheckBox checkBox,
			String formDisabledConditionName, String title, boolean required) {
		final EventSourceComponent eventSource = super.checkBox(component, dataWidgetVar, checkBox, formDisabledConditionName,
				title, required);
		final UIComponent checkBoxField = eventSource.getComponent();

		if (StringUtils.isNotBlank(rerenderEventName)) {
			addBoundColumnRerenderEvent((UIComponentBase) checkBoxField, collectionBinding, dataWidgetVar, checkBox.getBinding());
			rerenderEventName = null;
		}

		return eventSource;
	}

	@Override
	public EventSourceComponent combo(EventSourceComponent component, String dataWidgetVar, Combo combo,
			String formDisabledConditionName, String title, boolean required) {
		final EventSourceComponent eventSource = super.combo(component, dataWidgetVar, combo, formDisabledConditionName, title,
				required);
		final UIComponent comboField = eventSource.getComponent();

		if (StringUtils.isNotBlank(boundColumnEditableCondition)) {
			final ValueExpression expression = ef.createValueExpression(elc,
					String.format("#{%s['%s'] eq false}", dataWidgetVar, boundColumnEditableCondition), Boolean.class);
			comboField.setValueExpression("disabled", expression);
			boundColumnEditableCondition = null;
		}

		if (StringUtils.isNotBlank(boundColumnVisibleCondition)) {
			final ValueExpression expression = ef.createValueExpression(elc,
					String.format("#{%s['%s']}", dataWidgetVar, boundColumnVisibleCondition), Boolean.class);
			comboField.setValueExpression("rendered", expression);
			boundColumnVisibleCondition = null;
		}

		if (StringUtils.isNotBlank(rerenderEventName)) {
			addBoundColumnRerenderEvent((UIComponentBase) comboField, null, dataWidgetVar, combo.getBinding());
			rerenderEventName = null;
		}

		return eventSource;
	}
	
	@Override
	public EventSourceComponent text(EventSourceComponent component, String dataWidgetVar, TextField text,
			String formDisabledConditionName, String title, boolean required, Integer length, Converter<?> converter,
			Format<?> format, javax.faces.convert.Converter facesConverter) {
		final EventSourceComponent eventSource = super.text(component, dataWidgetVar, text, formDisabledConditionName, title,
				required, length, converter, format,
				facesConverter);
		final UIComponent textField = eventSource.getComponent();

		if (StringUtils.isNotBlank(rerenderEventName)) {
			addBoundColumnRerenderEvent((UIComponentBase) textField, null, dataWidgetVar, text.getBinding());
			rerenderEventName = null;
		}

		return eventSource;
	}

	private void addBoundColumnRerenderEvent(UIComponentBase componentBase, String collectionBinding, String listVar, String binding) {
        final RerenderEventAction rerenderEventAction = new RerenderEventAction();
		rerenderEventAction.setClientValidation(Boolean.TRUE);

        if (StringUtils.isNotBlank(boundColumnProcess)) {
            rerenderEventAction.getProperties().put(PROCESS_KEY, boundColumnProcess);
            boundColumnProcess = null;
        }
        if (StringUtils.isNotBlank(boundColumnUpdate)) {
            rerenderEventAction.getProperties().put(UPDATE_KEY, boundColumnUpdate);
            boundColumnUpdate = null;
        }
		Util.LOGGER.info("addAjaxBehaviour collectionBinding:" + collectionBinding);
        addAjaxBehavior(componentBase, rerenderEventName, collectionBinding, listVar, binding,
                Collections.singletonList(rerenderEventAction));
    }
}
