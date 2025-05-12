package modules.tableRowConditionTest.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class AssessmentTest extends AbstractDomainTest<Assessment> {

	@Override
	protected Assessment getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Assessment.MODULE_NAME, Assessment.DOCUMENT_NAME);
	}
}