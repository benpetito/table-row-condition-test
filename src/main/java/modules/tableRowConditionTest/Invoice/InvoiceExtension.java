package modules.tableRowConditionTest.Invoice;

import org.skyve.util.Util;

import modules.tableRowConditionTest.domain.Invoice;

public class InvoiceExtension extends Invoice {

	private static final long serialVersionUID = 1681594989718538304L;

	/**
	 * Implements the business logic for the {@link Invoice#isShowDetails()} condition.
	 * 
	 * @return true if any of the invoice item quantities are > 0
	 */
	public boolean showDetails() {
		Util.LOGGER.info("InvoiceExtension#showDetails() condition");
		return getItems().stream()
				.anyMatch(ii -> ii.getQuantity() != null && ii.getQuantity().intValue() > 0);
	}
}
