package hr.ante.isms.handlers;

import hr.ante.isms.parts.Toolbar;
import hr.ante.isms.parts.table.NewASKTable1;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
//import org.eclipse.e4.ui.model.[CONTINUE...]
//.application.descriptor.basic.MPartDescriptor;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

public class ThreatIdentificationHandler {
	private int m_Row;
	private int assetVisible = 0;
	@Inject
	private MApplication app;

	@Execute
	public void execute(MWindow window) {
		m_Row = NewASKTable1.clickedAssetRow;

		assetVisible = Toolbar.assetIsVisible;

		if (assetVisible == 1) {

			if (m_Row != 0) {
				window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.riskassessment.ThreatIdentification");
				part.setCloseable(true);

				window.getChildren().add(part);

				app.getChildren().add(window);

				System.out.println("identPrijetnji_");
			} else {
				Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
						"src/icons/error.ico"), "Problem",
						"Morate odabrati imovinu", NotifierTheme.RED_THEME);
			}
		}

		else {
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"), "Problem",
					"Morate odabrati perspektivu Procjena Rizika",
					NotifierTheme.RED_THEME);
		}

	}
}