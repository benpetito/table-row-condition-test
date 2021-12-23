package modules.tableRowConditionTest.Invoice;

import org.skyve.metadata.model.document.Bizlet;
import org.skyve.util.Util;
import org.skyve.web.WebContext;

import modules.tableRowConditionTest.domain.Invoice;

public class InvoiceBizlet extends Bizlet<Invoice> {

	private static final long serialVersionUID = -1319186153923191583L;

	@Override
	public void preRerender(String source, Invoice bean, WebContext webContext) throws Exception {
		Util.LOGGER.info("InvoiceBizlet:preRererender(" + source + ")");
		super.preRerender(source, bean, webContext);
	}
}
