package modules.tableRowConditionTest.InvoiceItem;

import org.skyve.metadata.model.document.Bizlet;
import org.skyve.util.Util;
import org.skyve.web.WebContext;

public class InvoiceItemBizlet extends Bizlet<InvoiceItemExtension> {

	private static final long serialVersionUID = -5961727355587474240L;

	@Override
	public void preRerender(String source, InvoiceItemExtension bean, WebContext webContext) throws Exception {
		Util.LOGGER.info("InvoiceItemBizlet:preRererender(" + source + ")");
		super.preRerender(source, bean, webContext);
	}

}
