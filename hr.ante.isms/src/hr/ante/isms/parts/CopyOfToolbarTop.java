package hr.ante.isms.parts;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.mihalis.opal.preferenceWindow.PWGroup;
import org.mihalis.opal.preferenceWindow.PWRow;
import org.mihalis.opal.preferenceWindow.PWTab;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;
import org.mihalis.opal.preferenceWindow.enabler.EnabledIfEquals;
import org.mihalis.opal.preferenceWindow.enabler.EnabledIfTrue;
import org.mihalis.opal.preferenceWindow.widgets.PWButton;
import org.mihalis.opal.preferenceWindow.widgets.PWCheckbox;
import org.mihalis.opal.preferenceWindow.widgets.PWColorChooser;
import org.mihalis.opal.preferenceWindow.widgets.PWCombo;
import org.mihalis.opal.preferenceWindow.widgets.PWFontChooser;
import org.mihalis.opal.preferenceWindow.widgets.PWLabel;
import org.mihalis.opal.preferenceWindow.widgets.PWRadio;
import org.mihalis.opal.preferenceWindow.widgets.PWScale;
import org.mihalis.opal.preferenceWindow.widgets.PWSeparator;
import org.mihalis.opal.preferenceWindow.widgets.PWSpinner;
import org.mihalis.opal.preferenceWindow.widgets.PWStringText;
import org.mihalis.opal.preferenceWindow.widgets.PWTextarea;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class CopyOfToolbarTop {

        @Inject IStylingEngine engine;
        @Inject private MApplication app;

        @Inject
    	private	IEclipseContext context;

        static Image imovinaIcon=null;
        static Image identPrijetnjiIcon=null;
        static Image identRanjivostiIcon = null;
        static Image utvrdVjerojatnostiIcon =null;
        static Image analizaUcinkaIcon = null;
        // create images for toolbar buttons


        @PostConstruct
    	public void createComposite(final Composite parent) {

        	 Image imovinaIcon=null;
             Image identPrijetnjiIcon=null;
             Image identRanjivostiIcon = null;
             Image utvrdVjerojatnostiIcon =null;
             Image analizaUcinkaIcon = null;
		parent.setLayout(new GridLayout(1, false));
           

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		composite.setLayout(new GridLayout(1, false));

		// Toolbar( ) {

		final ToolBar bar = new ToolBar(composite, SWT.RIGHT);
		bar.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		bar.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));
		// create and add the button for performing an open operation
		final ToolItem imovina_ = new ToolItem(bar, SWT.PUSH);
		imovina_.setWidth(100);
		try {
			imovina_.setImage(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/imovinaIcon.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		imovina_.setText("Imovina");
		imovina_.setToolTipText("Imovina");
		// table.setData("org.eclipse.e4.ui.css.id", "ASKTable");
		engine.setClassname(imovina_, "mainButtons");
		imovina_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");

		// create and add the button for performing a save operation
		final ToolItem identPrijetnji_ = new ToolItem(bar, SWT.PUSH);
		identPrijetnji_.setWidth(150);
		try {
			identPrijetnji_.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "bin/icons/identPrijetnjiIcon.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		identPrijetnji_.setText("Prijetnje");
		identPrijetnji_.setToolTipText("Prijetnje");
		engine.setClassname(identPrijetnji_, "mainButtons");
		identPrijetnji_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a cut operation
		final ToolItem identRanjivosti_ = new ToolItem(bar, SWT.PUSH);
		identRanjivosti_.setWidth(150);
		try {
			identRanjivosti_.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "src/icons/identRanjivostiIcon.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		identRanjivosti_.setText("Identifikacija Ranjivosti");
		identRanjivosti_.setToolTipText("Identifikacija Ranjivosti");
		engine.setClassname(identRanjivosti_, "mainButtons");
		identRanjivosti_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a copy operation
		final ToolItem utvrdVjerojatnosti_ = new ToolItem(bar, SWT.PUSH);
		try {
			utvrdVjerojatnosti_.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "src/icons/utvrdVjerojatnostiIcon.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		utvrdVjerojatnosti_.setText("Utvrðivanje Vjerojatnosti");
		utvrdVjerojatnosti_.setToolTipText("Utvrðivanje Vrijednosti");
		engine.setClassname(utvrdVjerojatnosti_, "mainButtons");
		utvrdVjerojatnosti_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a paste operation
		final ToolItem analizaUcinka_ = new ToolItem(bar, SWT.PUSH);
		try {
			analizaUcinka_.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "src/icons/analizaUcinkaIcon.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		analizaUcinka_.setText("Analiza Uèinka");
		analizaUcinka_.setToolTipText("Analiza Uèinka");
		engine.setClassname(analizaUcinka_, "mainButtons");
		analizaUcinka_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");
		
		ToolItem controls_ = new ToolItem(bar, SWT.NONE);
		controls_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Controls");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		controls_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "bin/icons/controls.png"));
		controls_.setText("Kontrole");

		imovina_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Assets2");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});

		identPrijetnji_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {

				// Create the window

				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ThreatIdentification");
				part.setCloseable(true);

				window.getChildren().add(part);

				app.getChildren().add(window);

				System.out.println("identPrijetnji_");
			}

			public void widgetDefaultSelected(SelectionEvent event) {

			}
		});
		identRanjivosti_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Vulnerability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				System.out.println("identRanjivosti");
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
		utvrdVjerojatnosti_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// System.out.println("utvrdVjerojatnosti_");
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Probability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
		analizaUcinka_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// System.out.println("analizaUcinka_");

				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ImpactAnalysis");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
        }
}


