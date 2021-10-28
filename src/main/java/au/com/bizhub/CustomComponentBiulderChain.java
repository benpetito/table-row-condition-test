package au.com.bizhub;

import org.skyve.impl.web.faces.pipeline.component.ComponentBuilderChain;
import org.skyve.impl.web.faces.pipeline.component.PaginatedListGridBuilder;

public class CustomComponentBiulderChain extends ComponentBuilderChain {

	public CustomComponentBiulderChain() {
		super(new DataGridComponentBuilder(), new PaginatedListGridBuilder());
	}
}
