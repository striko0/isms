package hr.ante.isms.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimBar;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.internal.WorkbenchWindow;
//import org.eclipse.e4.ui.model.[CONTINUE...]
//.application.descriptor.basic.MPartDescriptor;

public class AdminHandler {
	private int m_User;
	private int assetVisible = 0;
	@Inject
	protected EPartService partService;
	@Inject
	private MApplication app;

	@Execute
	public void execute(MWindow window) {
//		m_User = Toolbar.user;
//		final MPart ToolbarUser = partService.findPart("hr.ante.isms.toolcontrol.user");
		
	System.out.println(window.getChildren());
//		IContributionItem item = toolBarManager.find("hr.ante.isms.toolcontrol.user");
		

	}
}