package hr.ante.isms.parts;

import hr.ante.isms.parts.table.NewASKTable1;
import hr.ante.isms.parts.table.model.ListRiskASKTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

import de.kupzog.ktable.KTableSortedModel;

public class ToolbarUser {

	private KTableSortedModel m_Model;
	private NewASKTable1 m_Table;
	public ToolItem tltmIdentifikacijaRanjivosti;
	private int m_Row;
	private Composite m_Parent;
	private MPart assetPart ;
	public static int assetIsVisible=0;
	private String userName;

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
		//parent.setSize(new Point(500, 650));
		m_Parent=parent;
				GridLayout gl_parent = new GridLayout(1, false);

		gl_parent.marginWidth = 0;
		gl_parent.marginHeight = 0;
		parent.setLayout(gl_parent);

		expandBar = new ExpandBar(parent, SWT.V_SCROLL);
		//gd_expandBar.heightHint = 490;
		GridData gd_expandBar = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1);
		gd_expandBar.widthHint = 230;
		expandBar.setLayoutData(gd_expandBar);
		expandBar.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		expandBar.setBounds(0, 0, 165, 177);
		expandBar.setSpacing(8);

		m_Table = NewASKTable1.m_Table;
		m_Model = DataFromServer.listRiskASKTableModel;
		
		
		
			

		final ExpandItem xpndtmProcjenaRizika = new ExpandItem(expandBar,
				SWT.NONE);
		xpndtmProcjenaRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/identPrijetnjiIcon.png"));
		xpndtmProcjenaRizika.setText("Procjena Rizika");

		final ToolBar bar2 = new ToolBar(expandBar, SWT.FLAT | SWT.WRAP | SWT.RIGHT | SWT.SHADOW_OUT | SWT.VERTICAL);
		xpndtmProcjenaRizika.setControl(bar2);
		xpndtmProcjenaRizika.setHeight(300);

		ToolItem tltmImovina = new ToolItem(bar2, SWT.NONE);
		tltmImovina.setImage(ResourceManager.getPluginImage("hr.ante.isms",
				"src/icons/application_form.png"));
		tltmImovina.setText("Imovina");
		tltmImovina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Assets3");
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
//						if(m_Row!=0 && !m_Table.m_Selection.isEmpty()){
//
						m_Row = NewASKTable1.clickedAssetRow;
						if(m_Row!=0){
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();

						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ThreatIdentification");
						part.setCloseable(true);

						window.getChildren().add(part);

						app.getChildren().add(window);

						System.out.println("identPrijetnji_");
						}
						else{
							Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
									"src/icons/error.ico"),"Problem", "Morate odabrati imovinu", NotifierTheme.RED_THEME);
						}
					}
				});

		tltmIdentifikacijaRanjivosti = new ToolItem(bar2, SWT.NONE);
		tltmIdentifikacijaRanjivosti.setEnabled(true);
		tltmIdentifikacijaRanjivosti.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmIdentifikacijaRanjivosti.setText("Identifikacija Ranjivosti");
		tltmIdentifikacijaRanjivosti
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						m_Row = NewASKTable1.clickedAssetRow;
						if(m_Row!=0){
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();

						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.VulnerabilityIdentification");
						part.setCloseable(true);
						window.getChildren().add(part);

						app.getChildren().add(window);
//						System.out.println("identRanjivosti");
						}
						else{
							Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
									"src/icons/error.ico"),"Problem", "Morate odabrati imovinu", NotifierTheme.RED_THEME);
						}
					}
				});


//				ToolItem tltmAnalizaKontrola = new ToolItem(bar2, SWT.NONE);
//				tltmAnalizaKontrola.setImage(ResourceManager.getPluginImage(
//						"hr.ante.isms", "src/icons/application_form.png"));
//				tltmAnalizaKontrola.setText("Analiza Kontrola");
//				tltmAnalizaKontrola.addSelectionListener(new SelectionAdapter() {
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						final MWindow window = MBasicFactory.INSTANCE.createWindow();
//						MPart part = MBasicFactory.INSTANCE.createPart();
//						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ControlsAnalysis");
//						part.setCloseable(true);
//						window.getChildren().add(part);
//
//						app.getChildren().add(window);
//						// System.out.println("identRanjivosti");
//					}
//				});


		ToolItem tltmUtvrivanjeVjerojatnosti = new ToolItem(bar2, SWT.NONE);
		tltmUtvrivanjeVjerojatnosti.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmUtvrivanjeVjerojatnosti.setText("Odre\u0111ivanje Vjerojatnosti");
		tltmUtvrivanjeVjerojatnosti
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						// System.out.println("utvrdVjerojatnosti_");
						m_Row = NewASKTable1.clickedAssetRow;
						if(m_Row!=0){
						final MWindow window = MBasicFactory.INSTANCE
								.createWindow();
						MPart part = MBasicFactory.INSTANCE.createPart();
						part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Probability");
						part.setElementId("hr.ante.isms.part.probability");
						part.setCloseable(true);
						window.getChildren().add(part);

						app.getChildren().add(window);
						// System.out.println("identRanjivosti");
						}
						else{
							Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
									"src/icons/error.ico"),"Problem", "Morate odabrati imovinu", NotifierTheme.RED_THEME);
						}

					}
				});


		ToolItem tltmAnalizaUinka = new ToolItem(bar2, SWT.NONE);
		tltmAnalizaUinka.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/application_form.png"));
		tltmAnalizaUinka.setText("Analiza U\u010Dinka");
		tltmAnalizaUinka.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Row = NewASKTable1.clickedAssetRow;
				if (m_Row != 0) {
					final MWindow window = MBasicFactory.INSTANCE
							.createWindow();
					MPart part = MBasicFactory.INSTANCE.createPart();
					part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ImpactAnalysis");
					part.setCloseable(true);
					window.getChildren().add(part);

					app.getChildren().add(window);
					// System.out.println("identRanjivosti");
				} else {
					Notifier.notify(ResourceManager.getPluginImage(
							"hr.ante.isms", "src/icons/error.ico"), "Problem",
							"Morate odabrati imovinu", NotifierTheme.RED_THEME);
				}
			}
		});

		xpndtmProcjenaRizika.setHeight(240);

		final ExpandItem xpndtmSmanjivanjeRizika = new ExpandItem(expandBar,
				SWT.NONE);
		xpndtmSmanjivanjeRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/identRanjivostiIcon.png"));
		xpndtmSmanjivanjeRizika.setText("Smanjivanje Rizika");

		final MPart riskPart = partService.findPart("hr.ante.isms.part.risk");
		assetPart = partService.findPart("hr.ante.isms.part.asset");
		final MPart startPart = partService.findPart("hr.ante.isms.part.start");

		expandBar.addExpandListener(new ExpandListener() {

			@Override
			public void itemCollapsed(ExpandEvent e) {
				// expandBar.setSize(expandBar.getSize().x,
				// xpndtmWarningDetails.getHeaderHeight());
				// parent.layout(true);
				
					if (e.item == xpndtmProcjenaRizika) {
						if (xpndtmSmanjivanjeRizika.getExpanded() != true
								) {
							riskPart.setVisible(false);
							assetPart.setVisible(false);
							startPart.setVisible(true);
							assetIsVisible = 0;

						}
					}

					if (e.item == xpndtmSmanjivanjeRizika) {
						if (xpndtmProcjenaRizika.getExpanded() != true
								) {
							riskPart.setVisible(false);
							assetPart.setVisible(false);
							startPart.setVisible(true);
							assetIsVisible = 0;

						}

						// MPart part =
						// partService.findPart("hr.ante.isms.part.risk");
						// part.setVisible(false);
						// MPart partToShow =
						// partService.findPart("hr.ante.isms.part.asset");
						// partToShow.setVisible(true);
					}

				

				
				
			}

			@Override
			public void itemExpanded(ExpandEvent e) {

				
				if (e.item == xpndtmProcjenaRizika) {
					xpndtmSmanjivanjeRizika.setExpanded(false);
					
					riskPart.setVisible(false);					
					startPart.setVisible(false);
					assetPart.setVisible(true);
					assetIsVisible=1;
				}

				if (e.item == xpndtmSmanjivanjeRizika) {
					((ListRiskASKTableModel)m_Model).readAllFromDB();					
					xpndtmProcjenaRizika.setExpanded(false);
				
					assetPart.setVisible(false);					
					startPart.setVisible(false);
					riskPart.setVisible(true);
					assetIsVisible=0;

//					MPart part = partService.findPart("hr.ante.isms.part.risk");
//					part.setVisible(true);
//					MPart partToShow = partService
//							.findPart("hr.ante.isms.part.asset");
//					partToShow.setVisible(false);
				}


			
			
			
			}

		});


		ToolBar bar3 = new ToolBar(expandBar, SWT.FLAT | SWT.RIGHT
				| SWT.SHADOW_OUT | SWT.VERTICAL);
		xpndtmSmanjivanjeRizika.setControl(bar3);
		xpndtmSmanjivanjeRizika.setHeight(100);

		ToolItem tltmUtvrivanjeRizika = new ToolItem(bar3, SWT.NONE);
		tltmUtvrivanjeRizika.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Row = NewASKTable1.clickedRiskRow;
				if (m_Row != 0) {
					final MWindow window = MBasicFactory.INSTANCE
							.createWindow();
					MPart part = MBasicFactory.INSTANCE.createPart();
					part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.RiskAssessment");
					part.setCloseable(true);
					window.getChildren().add(part);

					app.getChildren().add(window);

				} else {
					Notifier.notify(ResourceManager.getPluginImage(
							"hr.ante.isms", "src/icons/error.ico"), "Problem",
							"Morate odabrati rizik", NotifierTheme.RED_THEME);
				}

			}

		});
		tltmUtvrivanjeRizika.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/risk.png"));
		tltmUtvrivanjeRizika.setText("Odre\u0111ivanje Rizika");

		ToolItem tltmPredlaganjeMjera = new ToolItem(bar3, SWT.NONE);
		tltmPredlaganjeMjera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Row = NewASKTable1.clickedRiskRow;
				if (m_Row != 0) {
					final MWindow window = MBasicFactory.INSTANCE
							.createWindow();
					MPart part = MBasicFactory.INSTANCE.createPart();
					part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.SuggestMeasures");
					part.setCloseable(true);
					window.getChildren().add(part);

					app.getChildren().add(window);
				} else {
					Notifier.notify(ResourceManager.getPluginImage(
							"hr.ante.isms", "src/icons/error.ico"), "Problem",
							"Morate odabrati rizik", NotifierTheme.RED_THEME);
				}
			}
		});
		tltmPredlaganjeMjera.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/form_input_select_multiple.png"));
		tltmPredlaganjeMjera.setText("Predlaganje Mjera");

		ToolItem tltmOcjenaMjera = new ToolItem(bar3, SWT.NONE);
		tltmOcjenaMjera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Row = NewASKTable1.clickedRiskRow;
				if (m_Row != 0) {
					final MWindow window = MBasicFactory.INSTANCE
							.createWindow();
					MPart part = MBasicFactory.INSTANCE.createPart();
					part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.MeasureEvaluation");
					part.setCloseable(true);
					window.getChildren().add(part);

					app.getChildren().add(window);
				} else {
					Notifier.notify(ResourceManager.getPluginImage(
							"hr.ante.isms", "src/icons/error.ico"), "Problem",
							"Morate odabrati rizik", NotifierTheme.RED_THEME);
				}
			}
		});
		tltmOcjenaMjera.setImage(ResourceManager.getPluginImage("hr.ante.isms",
				"src/icons/to_do_list_checked1 (1).png"));
		tltmOcjenaMjera.setText("Procjena Predlo\u017Eenih Mjera");

//		ToolItem tltmPotvrdaProcjene = new ToolItem(bar3, SWT.NONE);
//		tltmPotvrdaProcjene.setImage(ResourceManager.getPluginImage(
//				"hr.ante.isms", "src/icons/form_input_button_ok.png"));
//		tltmPotvrdaProcjene.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				HashMap hm = new HashMap();
//				try {
//
//					Connection connection = null;
//					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//					String url = "jdbc:sqlserver://192.168.0.76"/* 192.168.0.70 */;
//					String username = "sa"; // You should modify this.
//					String password = "sa"; // You should modify this.
//					// Load the JDBC driver
//					try {
//						Class.forName(driverName);
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					// Create a connection to the database
//					try {
//						connection = DriverManager.getConnection(url, username,
//								password);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
//					JasperPrint print = JasperFillManager.fillReport("C:/Documents and Settings/Zrosko/git/isms/hr.ante.isms/bin/reports/registar_imovine.jasper", hm, /*new JREmptyDataSource()*/connection);
//					JasperViewer.viewReport(print,false);
//
//				} catch (JRException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		tltmPotvrdaProcjene.setText("Potvrda Procjene");

	}
	
	public int isAssetPartVisible(){
		
		
		
		if(assetPart.isVisible())
			return 1;
		else
			return 0;
		
	}

	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
		m_Parent.getShell().close();
	}




	@Focus
	public void setFocus() {
		expandBar.setFocus();
	}
}
