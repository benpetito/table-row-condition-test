package modules.tableRowConditionTest.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;

/**
 * Invoice
 * 
 * @navcomposed 1 items 0..n InvoiceItem
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Invoice extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "tableRowConditionTest";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Invoice";

	/** @hidden */
	public static final String invoiceNoPropertyName = "invoiceNo";

	/** @hidden */
	public static final String descriptionPropertyName = "description";

	/** @hidden */
	public static final String itemsPropertyName = "items";

	/**
	 * Invoice No.
	 **/
	private Integer invoiceNo;

	/**
	 * Description
	 **/
	private String description;

	/**
	 * Items
	 **/
	private List<InvoiceItem> items = new ChangeTrackingArrayList<>("items", this);

	@Override
	@XmlTransient
	public String getBizModule() {
		return Invoice.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Invoice.DOCUMENT_NAME;
	}

	public static Invoice newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage("Invoice - {invoiceNo}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Invoice) && 
					this.getBizId().equals(((Invoice) o).getBizId()));
	}

	/**
	 * {@link #invoiceNo} accessor.
	 * @return	The value.
	 **/
	public Integer getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * {@link #invoiceNo} mutator.
	 * @param invoiceNo	The new value.
	 **/
	@XmlElement
	public void setInvoiceNo(Integer invoiceNo) {
		preset(invoiceNoPropertyName, invoiceNo);
		this.invoiceNo = invoiceNo;
	}

	/**
	 * {@link #description} accessor.
	 * @return	The value.
	 **/
	public String getDescription() {
		return description;
	}

	/**
	 * {@link #description} mutator.
	 * @param description	The new value.
	 **/
	@XmlElement
	public void setDescription(String description) {
		preset(descriptionPropertyName, description);
		this.description = description;
	}

	/**
	 * {@link #items} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<InvoiceItem> getItems() {
		return items;
	}

	/**
	 * {@link #items} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public InvoiceItem getItemsElementById(String bizId) {
		return getElementById(items, bizId);
	}

	/**
	 * {@link #items} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setItemsElementById(String bizId, InvoiceItem element) {
		setElementById(items, element);
	}

	/**
	 * {@link #items} add.
	 * @param element	The element to add.
	 **/
	public boolean addItemsElement(InvoiceItem element) {
		boolean result = items.add(element);
		element.setParent(this);
		return result;
	}

	/**
	 * {@link #items} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addItemsElement(int index, InvoiceItem element) {
		items.add(index, element);
		element.setParent(this);
	}

	/**
	 * {@link #items} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeItemsElement(InvoiceItem element) {
		boolean result = items.remove(element);
		element.setParent(null);
		return result;
	}

	/**
	 * {@link #items} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public InvoiceItem removeItemsElement(int index) {
		InvoiceItem result = items.remove(index);
		result.setParent(null);
		return result;
	}
}
