package modules.tableRowConditionTest.InvoiceItem;

import org.skyve.util.Util;

import modules.tableRowConditionTest.domain.InvoiceItem;

public class InvoiceItemExtension extends InvoiceItem {

	private static final long serialVersionUID = 5593709534467479675L;

	/**
	 * Logic for the canChange condition in InvoiceItem. Does not need to be
	 * implemented in an Extension class, just here so we can do some logging.
	 * 
	 * @return true if there is at least 1 quantity for this InvoiceItem, false otherwise
	 */
	public boolean canChange() {
		Util.LOGGER.info("Can Change condition");
		return getQuantity() != null && getQuantity().intValue() > 0;
	}

}
