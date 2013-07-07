package hr.ante.isms.handlers;

import hr.ante.isms.parts.Toolbar;
import hr.ante.isms.parts.riskassessment.Assets;

import javax.inject.Inject;
import javax.tools.Tool;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
//import org.eclipse.e4.ui.model.[CONTINUE...]
//.application.descriptor.basic.MPartDescriptor;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

public class AssetsHandler {
	
	private int assetVisible = 0;
	@Inject
	private MApplication app;
	@Inject
	protected EPartService partService;

	public int assetvisibile = 0;

	@Execute
	public void execute(MWindow window) {

		// final MPart assetPart =
		// partService.findPart("hr.ante.isms.part.asset");

		assetVisible = Toolbar.assetIsVisible;

		if (assetVisible == 1) {

			window = MBasicFactory.INSTANCE.createWindow();
			MPart part = MBasicFactory.INSTANCE.createPart();
			part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.riskassessment.Assets");
			part.setCloseable(true);
			window.getChildren().add(part);
			app.getChildren().add(window);
		}

		else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"), "Problem",
					"Morate odabrati perspektivu Procjena Rizika",
					NotifierTheme.RED_THEME);
	}
}