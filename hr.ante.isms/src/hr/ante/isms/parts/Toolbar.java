package hr.ante.isms.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class Toolbar {

	@Inject
	IStylingEngine engine;
	@Inject
	protected EPartService partService;
	@Inject
	private MApplication app;
	@Inject
	private IEclipseContext context;
	private ExpandBar expandBar;

	// @Inject
	// MDirtyable dirty;

	@PostConstruct
	public void createComposite(final Composite parent) {
		try {
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		try {
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		try {
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		try {
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		try {
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.marginWidth = 0;
		gl_parent.marginHeight = 0;
		parent.setLayout(gl_parent);

		expandBar = new ExpandBar(parent, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_expandBar = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1);
		gd_expandBar.heightHint = 490;
		expandBar.setLayoutData(gd_expandBar);
		expandBar.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
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
		xpndtmPostavke.setText("Administracija");
		// Toolbar( ) {

		final ToolBar bar1 = new ToolBar(expandBar, SWT.FLAT | SWT.RIGHT
				| SWT.SHADOW_OUT | SWT.VERTICAL);
		xpndtmPostavke.setControl(bar1);

		ToolItem tltmKontrole_ = new ToolItem(bar1, SWT.NONE);
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
			tltmKontrole_.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "bin/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		tltmKontrole_.setText("Dodaj Kontrole");
		xpndtmPostavke.setHeight(130);

		ToolItem tltmDodajRanjivosti = new ToolItem(bar1, SWT.NONE);
		tltmDodajRanjivosti.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Vulnerability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		tltmDodajRanjivosti.setText("Dodaj Ranjivosti");
		try {
			tltmDodajRanjivosti.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "bin/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}

		ToolItem tltmDodajPrijetnje = new ToolItem(bar1, SWT.NONE);
		tltmDodajPrijetnje.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Threats");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		try {
			tltmDodajPrijetnje.setImage(ResourceManager.getPluginImage(
					"hr.ante.isms", "src/icons/document2_add.png"));
		} catch (Exception e) {
			System.out.println("Cannot load images");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		tltmDodajPrijetnje.setText("Dodaj Prijetnje");

		final ExpandItem xpndtmProcjenaRizika = new ExpandItem(expandBar,
				SWT.NONE);
		xpndtmProcjenaRizika.setExpanded(true);
		xpndtmProcjenaRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/identPrijetnjiIcon.png"));
		xpndtmProcjenaRizika.setText("Procjena Rizika");

		ToolBar bar2 = new ToolBar(expandBar, SWT.FLAT | SWT.RIGHT
				| SWT.SHADOW_OUT | SWT.VERTICAL);
		xpndtmProcjenaRizika.setControl(bar2);
		xpndtmProcjenaRizika.setControl(bar2);

		ToolItem tltmImovina = new ToolItem(bar2, SWT.NONE);
		tltmImovina.setImage(ResourceManager.getPluginImage("hr.ante.isms",
				"src/icons/application_form.png"));
		tltmImovina.setText("Imovina");
		tltmImovina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Assets2");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});

		ToolItem tltmIdentifikacijaPrijetnji = new ToolItem(bar2, SWT.NONE);
		tltmIdentifikacijaPrijetnji.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmIdentifikacijaPrijetnji.setText("Identifikacija Prijetnji");
		tltmIdentifikacijaPrijetnji
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();

						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ThreatIdentification");
						part.setCloseable(true);

						window.getChildren().add(part);

						app.getChildren().add(window);

						System.out.println("identPrijetnji_");
					}
				});
		ToolItem tltmIdentifikacijaRanjivosti = new ToolItem(bar2, SWT.NONE);
		tltmIdentifikacijaRanjivosti.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmIdentifikacijaRanjivosti.setText("Identifikacija Ranjivosti");
		tltmIdentifikacijaRanjivosti
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();

						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Vulnerability");
						part.setCloseable(true);
						window.getChildren().add(part);

						app.getChildren().add(window);
						System.out.println("identRanjivosti");
					}
				});

		ToolItem tltmUtvrivanjeVjerojatnosti = new ToolItem(bar2, SWT.NONE);
		tltmUtvrivanjeVjerojatnosti.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmUtvrivanjeVjerojatnosti.setText("Utvr\u0111ivanje Vjerojatnosti");
		tltmUtvrivanjeVjerojatnosti
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						// System.out.println("utvrdVjerojatnosti_");
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();
						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Probability");
						part.setCloseable(true);
						window.getChildren().add(part);

						app.getChildren().add(window);
						// System.out.println("identRanjivosti");
					}
				});

		ToolItem tltmAnalizaKontrola = new ToolItem(bar2, SWT.NONE);
		tltmAnalizaKontrola.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmAnalizaKontrola.setText("Analiza Kontrola");
		tltmAnalizaKontrola.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ControlsAnalysis");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");
			}
		});

		System.out.println("identPrijetnji_");

		ToolItem tltmAnalizaUinka = new ToolItem(bar2, SWT.NONE);
		tltmAnalizaUinka.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmAnalizaUinka.setText("Analiza U\u010Dinka");
		tltmAnalizaUinka.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ImpactAnalysis");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");
			}
		});

		xpndtmProcjenaRizika.setHeight(240);

		final ExpandItem xpndtmSmanjivanjeRizika = new ExpandItem(expandBar,
				SWT.NONE);
		xpndtmSmanjivanjeRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/identRanjivostiIcon.png"));
		xpndtmSmanjivanjeRizika.setText("Smanjivanje Rizika");

		expandBar.addExpandListener(new ExpandListener() {

			@Override
			public void itemCollapsed(ExpandEvent e) {
				// expandBar.setSize(expandBar.getSize().x,
				// xpndtmWarningDetails.getHeaderHeight());
				// parent.layout(true);

				if (e.item == xpndtmSmanjivanjeRizika) {

					MPart part = partService.findPart("hr.ante.isms.part.risk");
					part.setVisible(false);
					MPart partToShow = partService
							.findPart("hr.ante.isms.part.asset");
					partToShow.setVisible(true);
				}

			}

			@Override
			public void itemExpanded(ExpandEvent e) {

				if (e.item == xpndtmProcjenaRizika) {
					System.out.println("e.item");
					MPart part = partService.findPart("hr.ante.isms.part.risk");
					part.setVisible(false);
					MPart partToShow = partService
							.findPart("hr.ante.isms.part.asset");
					partToShow.setVisible(true);
				}

				if (e.item == xpndtmSmanjivanjeRizika) {

					MPart part = partService.findPart("hr.ante.isms.part.risk");
					part.setVisible(true);
					MPart partToShow = partService
							.findPart("hr.ante.isms.part.asset");
					partToShow.setVisible(false);
				}

				//
				// expandBar.setSize(expandBar.getSize().x, 300);
				// expandBar.layout(true);
				// parent.layout(true);

			}

		});

		ToolBar bar3 = new ToolBar(expandBar, SWT.FLAT | SWT.RIGHT
				| SWT.VERTICAL);
		xpndtmSmanjivanjeRizika.setControl(bar3);
		xpndtmSmanjivanjeRizika.setHeight(150);

		ToolItem tltmUtvrivanjeRizika = new ToolItem(bar3, SWT.NONE);
		tltmUtvrivanjeRizika.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.RiskAssessment");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);

			}

		});
		tltmUtvrivanjeRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/risk.png"));
		tltmUtvrivanjeRizika.setText("Utvr\u0111ivanje Rizika");

		ToolItem tltmPredlaganjeMjera = new ToolItem(bar3, SWT.NONE);
		tltmPredlaganjeMjera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.SuggestMeasures");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		tltmPredlaganjeMjera.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/form_input_select_multiple.png"));
		tltmPredlaganjeMjera.setText("Predlaganje Mjera");

		ToolItem tltmOcjenaMjera = new ToolItem(bar3, SWT.NONE);
		tltmOcjenaMjera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.MeasureEvaluation");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		tltmOcjenaMjera.setImage(ResourceManager.getPluginImage("hr.ante.isms",
				"src/icons/to_do_list_checked1 (1).png"));
		tltmOcjenaMjera.setText("Ocjena Mjera");

		ToolItem tltmPotvrdaProcjene = new ToolItem(bar3, SWT.NONE);
		tltmPotvrdaProcjene.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/form_input_button_ok.png"));
		tltmPotvrdaProcjene.setText("Potvrda Procjene");

	}

	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
	}

	@Persist
	public void save() {
		// System.out.println("Saving data");
		// // Save the data
		// // ...
		// // Now set the dirty flag to false
		// dirty.setDirty(false);
	}

	@Focus
	public void setFocus() {
		expandBar.setFocus();
	}
}
