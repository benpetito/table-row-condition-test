package au.com.bizhub;

import org.skyve.impl.web.faces.pipeline.component.ComponentBuilderChain;
import org.skyve.impl.web.faces.pipeline.component.ResponsiveComponentBuilder;

public class CustomComponentBuilderChain extends ComponentBuilderChain {

	public CustomComponentBuilderChain() {
		super(new DataGridComponentBuilder(), new ResponsiveComponentBuilder());
	}
}
