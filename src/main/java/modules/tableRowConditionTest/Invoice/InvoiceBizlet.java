package modules.tableRowConditionTest.Invoice;

import org.skyve.domain.messages.MessageSeverity;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.util.Util;
import org.skyve.web.WebContext;

import modules.tableRowConditionTest.domain.Invoice;
import modules.tableRowConditionTest.domain.InvoiceItem;

public class InvoiceBizlet extends Bizlet<Invoice> {

	@Override
	public void preRerender(String source, Invoice bean, WebContext webContext) throws Exception {
		Util.LOGGER.info("InvoiceBizlet:preRererender(" + source + ")");

		if (InvoiceItem.deliveryPropertyName.equals(source)) {
			webContext.growl(MessageSeverity.info, "Delivery was changed");
		}

		super.preRerender(source, bean, webContext);
	}
}
