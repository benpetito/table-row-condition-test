package modules.tableRowConditionTest.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.tableRowConditionTest.InvoiceItem.InvoiceItemExtension;
import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.domain.ChildBean;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Decimal2;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.Decimal2Mapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Invoice Item
 * 
 * @depend - - - Type
 * @stereotype "persistent child"
 */
@XmlType
@XmlRootElement
public abstract class InvoiceItem extends AbstractPersistentBean implements ChildBean<Invoice> {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "tableRowConditionTest";

	/** @hidden */
	public static final String DOCUMENT_NAME = "InvoiceItem";

	/** @hidden */
	public static final String itemPropertyName = "item";

	/** @hidden */
	public static final String amountPropertyName = "amount";

	/** @hidden */
	public static final String quantityPropertyName = "quantity";

	/** @hidden */
	public static final String typePropertyName = "type";

	/**
	 * Type
	 **/
	@XmlEnum
	public static enum Type implements Enumeration {
		items("Items", "Items"),
		hours("Hours", "Hours");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Type(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Type fromCode(String code) {
			Type result = null;

			for (Type value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Type fromDescription(String description) {
			Type result = null;

			for (Type value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Type[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Type value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Item
	 **/
	private String item;

	/**
	 * Amount
	 **/
	private Decimal2 amount;

	/**
	 * Quantity
	 **/
	private Integer quantity;

	/**
	 * Type
	 **/
	private Type type;

	private Invoice parent;

	private Integer bizOrdinal;

	@Override
	@XmlTransient
	public String getBizModule() {
		return InvoiceItem.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return InvoiceItem.DOCUMENT_NAME;
	}

	public static InvoiceItemExtension newInstance() {
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
			return org.skyve.util.Binder.formatMessage("Invoice Item - {item}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof InvoiceItem) && 
					this.getBizId().equals(((InvoiceItem) o).getBizId()));
	}

	/**
	 * {@link #item} accessor.
	 * @return	The value.
	 **/
	public String getItem() {
		return item;
	}

	/**
	 * {@link #item} mutator.
	 * @param item	The new value.
	 **/
	@XmlElement
	public void setItem(String item) {
		preset(itemPropertyName, item);
		this.item = item;
	}

	/**
	 * {@link #amount} accessor.
	 * @return	The value.
	 **/
	public Decimal2 getAmount() {
		return amount;
	}

	/**
	 * {@link #amount} mutator.
	 * @param amount	The new value.
	 **/
	@XmlElement
	@XmlJavaTypeAdapter(Decimal2Mapper.class)
	public void setAmount(Decimal2 amount) {
		preset(amountPropertyName, amount);
		this.amount = amount;
	}

	/**
	 * {@link #quantity} accessor.
	 * @return	The value.
	 **/
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * {@link #quantity} mutator.
	 * @param quantity	The new value.
	 **/
	@XmlElement
	public void setQuantity(Integer quantity) {
		preset(quantityPropertyName, quantity);
		this.quantity = quantity;
	}

	/**
	 * {@link #type} accessor.
	 * @return	The value.
	 **/
	public Type getType() {
		return type;
	}

	/**
	 * {@link #type} mutator.
	 * @param type	The new value.
	 **/
	@XmlElement
	public void setType(Type type) {
		preset(typePropertyName, type);
		this.type = type;
	}

	/**
	 * Allows InvoiceItem rows within a Data Grid to be modified
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isCanChange() {
		return (((InvoiceItemExtension)this).canChange());
	}

	/**
	 * {@link #isCanChange} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotCanChange() {
		return (! isCanChange());
	}

	@Override
	public Invoice getParent() {
		return parent;
	}

	@Override
	@XmlElement
	public void setParent(Invoice parent) {
		if (this.parent != parent) {
			preset(ChildBean.PARENT_NAME, parent);
			this.parent = parent;
		}
	}

	@Override
	public Integer getBizOrdinal() {
		return bizOrdinal;
	}

	@Override
	@XmlElement
	public void setBizOrdinal(Integer bizOrdinal) {
		preset(Bean.ORDINAL_NAME, bizOrdinal);
		this.bizOrdinal =  bizOrdinal;
	}
}
