package hr.ante.isms.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class ToolbarLeft {

	@Inject
	IStylingEngine engine;

	 @Inject private MApplication app;
	

     private ExpandBar expandBar;

	@PostConstruct
	public void createComposite(final Composite parent) {
		parent.setLayout(null);

		expandBar = new ExpandBar(parent, SWT.NONE);
		expandBar.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		expandBar.setBounds(0, 0, 165, 177);
		expandBar.setSpacing(8);

		ExpandItem xpndtmPostavke = new ExpandItem(expandBar, SWT.NONE);
		try {
			xpndtmPostavke.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "src/icons/applications_system.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		xpndtmPostavke.setText("Postavke");
		// Toolbar( ) {

		final ToolBar bar = new ToolBar(expandBar, SWT.RIGHT | SWT.SHADOW_OUT
				| SWT.VERTICAL);
		xpndtmPostavke.setControl(bar);

		ToolItem tltmKontrole_ = new ToolItem(bar, SWT.NONE);
		tltmKontrole_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Controls");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});

		try {
			tltmKontrole_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "bin/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		tltmKontrole_.setText("Dodaj kontrole");
		xpndtmPostavke.setHeight(130);

		ToolItem tltmDodajRanjivosti = new ToolItem(bar, SWT.NONE);
		tltmDodajRanjivosti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.AddVulnerability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		tltmDodajRanjivosti.setText("Dodaj Ranjivosti");
		try {
			tltmDodajRanjivosti.setImage(ResourceManager.getPluginImage("hr.ante.isms", "bin/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}

		ToolItem tltmDodajPrijetnje = new ToolItem(bar, SWT.NONE);
		tltmDodajPrijetnje.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.AddThreats");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		try {
			tltmDodajPrijetnje.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		tltmDodajPrijetnje.setText("Dodaj Prijetnje");


	}
	@PreDestroy
	public void dispose() throws Exception {
	  System.out.println("Closing application");
	}

	 @Persist
	  public void save() {
	    System.out.println("Saving data");
	  }


	@Focus
	public void setFocus() {
		expandBar.setFocus();
	}
}
