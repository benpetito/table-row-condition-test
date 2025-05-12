package modules.tableRowConditionTest.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;

/**
 * Assessment List
 * 
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class AssessmentList extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "tableRowConditionTest";

	/** @hidden */
	public static final String DOCUMENT_NAME = "AssessmentList";

	@Override
	@XmlTransient
	public String getBizModule() {
		return AssessmentList.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return AssessmentList.DOCUMENT_NAME;
	}

	public static AssessmentList newInstance() {
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
			return org.skyve.util.Binder.formatMessage("AssessmentList", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof AssessmentList) && 
					this.getBizId().equals(((AssessmentList) o).getBizId()));
	}
}
