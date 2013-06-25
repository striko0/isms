package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListControlASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;

import de.kupzog.ktable.KTableSortedModel;

public class Controls implements ViewSelected{

	private Composite mParent;

	@Inject
	private MApplication app;

	private int action = 1;
	private KTableSortedModel m_Model;
	private String m_ControlId;
	private NewASKTable1 table;
	private int m_Row;
	private DataFromDatabase dB;
	public static int controlId;

	private Text textNaziv_;
	private Text textOznaka_;
	private Text textOpis_;

	private Button btnRadAkt_;
	private Button btnRadPlan_;
	private Button btnRadNeAkt_;
	private Button btnRadUprav_;
	private Button btnRadLog_;
	private Button btnRadFiz_;
	private Button btnRadPrev_;
	private Button btnRadDet_ ;
	private Button btnRadReak_;
	private Button btnNovo_;
	private Button btnDupliciraj_;
	private Button btnBrisi_;

	private Combo comboOdgOsoba_;
	private Combo comboOcjDjelot_;
	private Combo comboSukl_;
	private Button btnViseDetalja_;
	private int radioButtonStatus;
	private int radioButtonTip;
	private int radioButtonVrsta;
	private String controlName;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setBounds(0, 0, 591, 423);

		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(550);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);

		mParent.getShell().setSize(750, 700);
	
		mParent.getShell().setText("Evidencija Kontrola");
		mParent.setLayout(new GridLayout(1, false));

		m_Model = DataFromServer.listControlASKTableModel;
		m_Row = NewASKTable1.clickedControlRow;
		dB = new DataFromDatabase();

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(4, false));
		GridData gd_composite1 = new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1);
		gd_composite1.heightHint = 220;
		composite1.setLayoutData(gd_composite1);

		Label lblPrijet_ = new Label(composite1, SWT.NONE);
		lblPrijet_.setText("Naziv:");

		textNaziv_ = new Text(composite1, SWT.BORDER);
		textNaziv_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblOznaka_ = new Label(composite1, SWT.NONE);
		lblOznaka_.setText("Oznaka:");

		textOznaka_ = new Text(composite1, SWT.BORDER);
		GridData gd_textOznaka_ = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_textOznaka_.widthHint = 157;
		textOznaka_.setLayoutData(gd_textOznaka_);
		new Label(composite1, SWT.NONE);
				new Label(composite1, SWT.NONE);

				Label lblOpis_ = new Label(composite1, SWT.NONE);
				lblOpis_.setText("Opis:");
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);

		textOpis_ = new Text(composite1, SWT.BORDER | SWT.V_SCROLL);
		textOpis_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 4));

		Group group = new Group(composite1, SWT.NONE);
		GridData gd_group = new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1);
		gd_group.heightHint = 30;
		group.setLayoutData(gd_group);
		group.setText("Status");
		group.setLayout(new GridLayout(3, false));

		btnRadAkt_ = new Button(group, SWT.RADIO);
		GridData gd_btnRadAkt_ = new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1);
		gd_btnRadAkt_.widthHint = 100;
		btnRadAkt_.setLayoutData(gd_btnRadAkt_);
		btnRadAkt_.setText("Aktivno");

		btnRadPlan_= new Button(group, SWT.RADIO);
		GridData gd_btnRadPlan_ = new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1);
		gd_btnRadPlan_.widthHint = 100;
		btnRadPlan_.setLayoutData(gd_btnRadPlan_);
		btnRadPlan_.setText("Planirano");

		btnRadNeAkt_ = new Button(group, SWT.RADIO);
		GridData gd_btnRadNeAkt_ = new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1);
		gd_btnRadNeAkt_.widthHint = 100;
		btnRadNeAkt_.setLayoutData(gd_btnRadNeAkt_);
		btnRadNeAkt_.setText("Neaktivno");

		Group group_1 = new Group(composite1, SWT.NONE);
		GridData gd_group_1 = new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1);
		gd_group_1.heightHint = 30;
		group_1.setLayoutData(gd_group_1);
		group_1.setText("Tip");
		group_1.setLayout(new GridLayout(3, false));

		btnRadUprav_ = new Button(group_1, SWT.RADIO);
		GridData gd_btnRadUprav_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadUprav_.widthHint = 100;
		btnRadUprav_.setLayoutData(gd_btnRadUprav_);
		btnRadUprav_.setText("Upravlja\u010Dka");

		btnRadLog_ = new Button(group_1, SWT.RADIO);
		GridData gd_btnRadLog_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadLog_.widthHint = 100;
		btnRadLog_.setLayoutData(gd_btnRadLog_);
		btnRadLog_.setText("Logi\u010Dka");

		btnRadFiz_ = new Button(group_1, SWT.RADIO);
		GridData gd_btnRadFiz_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadFiz_.widthHint = 100;
		btnRadFiz_.setLayoutData(gd_btnRadFiz_);
		btnRadFiz_.setText("Fizi\u010Dka");

		Group group_2 = new Group(composite1, SWT.NONE);
		GridData gd_group_2 = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_group_2.heightHint = 30;
		group_2.setLayoutData(gd_group_2);
		group_2.setText("Vrsta");
		group_2.setLayout(new GridLayout(3, false));

		btnRadPrev_ = new Button(group_2, SWT.RADIO);
		GridData gd_btnRadPrev_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadPrev_.widthHint = 100;
		btnRadPrev_.setLayoutData(gd_btnRadPrev_);
		btnRadPrev_.setText("Preventivna");

		btnRadDet_ = new Button(group_2, SWT.RADIO);
		GridData gd_btnRadDet_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadDet_.widthHint = 100;
		btnRadDet_.setLayoutData(gd_btnRadDet_);
		btnRadDet_.setText("Detekcijska");

		btnRadReak_ = new Button(group_2, SWT.RADIO);
		GridData gd_btnRadReak_ = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnRadReak_.widthHint = 100;
		btnRadReak_.setLayoutData(gd_btnRadReak_);
		btnRadReak_.setText("Reakcijska");
		
		btnViseDetalja_ = new Button(composite1, SWT.NONE);
		btnViseDetalja_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ControlsAdvanced");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);

			}

		});
		btnViseDetalja_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/format_indent_more.png"));
		btnViseDetalja_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnViseDetalja_.setText("Vi\u0161e detalja");
		
		
				Composite composite2 = new Composite(mParent, SWT.NONE);
				composite2.setLayout(new GridLayout(6, false));
				composite2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
						1, 1));
				
						Label lblOcjDjelot_ = new Label(composite2, SWT.NONE);
						lblOcjDjelot_.setText("Ocjena Djelotvornosti:");
						lblOcjDjelot_.setBounds(0, 0, 32, 15);
						
								comboOcjDjelot_ = new Combo(composite2, SWT.READ_ONLY);
								GridData gd_comboOcjDjelot_ = new GridData(SWT.FILL, SWT.CENTER, true,
										false, 1, 1);
								gd_comboOcjDjelot_.widthHint = 122;
								comboOcjDjelot_.setLayoutData(gd_comboOcjDjelot_);
								
								
										Label lblOdgOsoba_ = new Label(composite2, SWT.NONE);
										lblOdgOsoba_.setText("Odgovorna Osoba:");
										
												comboOdgOsoba_ = new Combo(composite2, SWT.NONE);
												GridData gd_comboOdgOsoba_ = new GridData(SWT.FILL, SWT.CENTER, true,
														false, 1, 1);
												gd_comboOdgOsoba_.widthHint = 110;
												comboOdgOsoba_.setLayoutData(gd_comboOdgOsoba_);
												
												
														Label lblSukl_ = new Label(composite2, SWT.NONE);
														lblSukl_.setText("Sukladnost:");
														
																comboSukl_ = new Combo(composite2, SWT.NONE);
																GridData gd_comboSukl_ = new GridData(SWT.FILL, SWT.CENTER, true,
																		false, 1, 1);
																gd_comboSukl_.widthHint = 121;
																comboSukl_.setLayoutData(gd_comboSukl_);
		
		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.heightHint = 150;
		gd_compositeASKTable.minimumHeight = 75;
		compositeASKTable.setLayoutData(gd_compositeASKTable);		

		table = new NewASKTable1(this,compositeASKTable, new ListControlASKTableModel(),
				717, compositeASKTable.getBounds().height);
//		
//				Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
//				GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
//						false, 1, 1);
//				gd_compositeButtons_.heightHint = 35;
//				gd_compositeButtons_.horizontalIndent = 10;
//				gd_compositeButtons_.widthHint = 461;
//				compositeButtons_.setLayoutData(gd_compositeButtons_);
//				compositeButtons_.setLayout(new GridLayout(4, false));
//				
//						btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
//						btnBrisi_.addSelectionListener(new SelectionAdapter() {
//							@Override
//							public void widgetSelected(SelectionEvent e) {
//							}
//						});
//						btnBrisi_.setText("Briši");
//						
//								Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
//								GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
//										false, 1, 1);
//								gd_btnSpremi_.widthHint = 100;
//								btnSpremi_.setLayoutData(gd_btnSpremi_);
//								btnSpremi_.setText("Spremi");
//								
//										Button btnNast_ = new Button(compositeButtons_, SWT.NONE);
//										GridData gd_btnNast = new GridData(SWT.LEFT, SWT.CENTER, false, false,
//												1, 1);
//										gd_btnNast.widthHint = 100;
//										btnNast_.setLayoutData(gd_btnNast);
//										btnNast_.addSelectionListener(new SelectionListener() {
//
//											@Override
//											public void widgetSelected(SelectionEvent e) {
//												// TODO Auto-generated method stub
//												final MWindow window = MBasicFactory.INSTANCE.createWindow();
//
//												MPart part = MBasicFactory.INSTANCE.createPart();
//												part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ControlsAdvanced");
//												part.setCloseable(true);
//												window.getChildren().add(part);
//
//												app.getChildren().add(window);
//
//
//											}
//
//											@Override
//											public void widgetDefaultSelected(SelectionEvent e) {
//												// TODO Auto-generated method stub
//
//											}
//										});
//										btnNast_.setText("Nastavak");
//										
//												Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
//												GridData gd_btnIzlaz_ = new GridData(SWT.LEFT, SWT.CENTER, false,
//														false, 1, 1);
//												gd_btnIzlaz_.widthHint = 100;
//												btnIzlaz_.setLayoutData(gd_btnIzlaz_);
//												btnIzlaz_.setText("Izlaz");
//												btnIzlaz_.addSelectionListener(new SelectionAdapter() {
//
//													@Override
//													public void widgetSelected(SelectionEvent e) {
//														// TODO Auto-generated method stub
//														mParent.getShell().close();
//													}
//												});
//
//		fillForm();
//		scrollBox.setContent(mParent);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 641;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(6, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				saveAction();
				//action=1;
			}
		});
		btnSpremi_.setText("Spremi");

		btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		btnNovo_.setEnabled(false);
		GridData gd_btnNovo_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_ControlId=null;
				fillForm();
			}
		});
		btnNovo_.setText("Novo");

		btnDupliciraj_ = new Button(compositeButtons_, SWT.NONE);
		btnDupliciraj_.setEnabled(false);
		GridData gd_btnDupliciraj_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnDupliciraj_.widthHint = 100;
		btnDupliciraj_.setLayoutData(gd_btnDupliciraj_);
		btnDupliciraj_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(action==2){
					action=1;
					saveAction();

				}
			}
		});
		btnDupliciraj_.setText("Dupliciraj");

		btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnBrisi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				boolean confirm = Dialog.isConfirmed("Je ste li sigurni da želite obrisati podatak?", "Podatak æe biti obrisan");

				if (confirm) {
					try {
						dB.deleteDataFromDB("as_control", "control_id", m_ControlId);
						fillForm();
						refreshTable();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnBrisi_.setText("Briši");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				mParent.getShell().close();
			}
		});

		Button btnIspis_ = new Button(compositeButtons_, SWT.NONE);
		btnIspis_.setText("Ispis");
		GridData gd_btnIspis_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnIspis_.widthHint = 100;
		btnIspis_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				HashMap hm = new HashMap();
//				try {
//
//					Connection connection = null;
//					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
////					String url = "jdbc:sqlserver://192.168.0.76"/* 192.168.0.70 */;
//					String url = "jdbc:sqlserver://127.0.0.1:1433;integratedSecurity=true"; 
////					String username = "sa"; // You should modify this.
////					String password = "sa"; // You should modify this.
//					// Load the JDBC driver
//					try {
//						Class.forName(driverName);
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					// Create a connection to the database
//					try {
////						connection = DriverManager.getConnection(url, username,
////								password);
//						connection = DriverManager.getConnection(url);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
////					JasperPrint print = JasperFillManager.fillReport("C:/Documents and Settings/Zrosko/git/isms/hr.ante.isms/src/reports/prijetnje.jasper", hm, /*new JREmptyDataSource()*/connection);
//					JasperPrint print = JasperFillManager.fillReport("C:/Reports/prijetnje.jasper", hm, /*new JREmptyDataSource()*/connection);
//
//					JasperViewer.viewReport(print,false);
//
//				} catch (JRException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		btnIspis_.setLayoutData(gd_btnIspis_);

		fillForm();
		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);
	
	}
	
	public void refreshTable(){
		((ListControlASKTableModel)table.getModel()).readAllFromDB();
		table.redraw();
	}

	private void fillForm() {
		// TODO Auto-generated method stub

		action=1;
		controlId=0;
		setWindowsTitle(false);
		initialSettings();
		table.setSelection(null, false);


	}

	private void initialSettings(){
		comboOcjDjelot_.setItems(dB.getComboItemsFromDB("as_control_effectiveness"));
		comboOdgOsoba_.setItems(dB.getComboItemsFromDB("as_owner"));
		comboSukl_.setItems(dB.getComboItemsFromDB("as_control_compliance"));
		

		btnRadAkt_.setSelection(false);
		btnRadPlan_.setSelection(false);
		btnRadNeAkt_.setSelection(false);

		btnRadUprav_.setSelection(false);
		btnRadLog_.setSelection(false);
		btnRadFiz_.setSelection(false);

		btnRadPrev_.setSelection(false);
		btnRadDet_.setSelection(false);
		btnRadReak_.setSelection(false);
		
		textOznaka_.setText("");
		textOpis_.setText("");
		textNaziv_.setText("");
		comboOcjDjelot_.setText("");
		comboOdgOsoba_.setText("");
		comboSukl_.setText("");


	}	
	
	@Override
	public void rowSelected(int row) {
//		// TODO Auto-generated method stub
		if (row!=0 /*&& !table.getModel().getContentAt(1, row).toString().equals("")*/) {
			if(action==2){
				initialSettings();
			}
			action=2;
			setWindowsTitle(true);
			btnBrisi_.setEnabled(true);
			btnDupliciraj_.setEnabled(true);
			btnNovo_.setEnabled(true);
			m_ControlId=table.getModel().getContentAt(8, row).toString();
			controlId=Integer.parseInt(m_ControlId);

			textNaziv_.setText(table.getModel().getContentAt(3, row).toString());			
			textOznaka_.setText(table.getModel().getContentAt(4, row).toString());
			textOpis_.setText(table.getModel().getContentAt(9, row).toString());
			
			
			String ocjeDjelotId=table.getModel().getContentAt(10, row).toString();
			if(ocjeDjelotId.equals(""))
			{
				comboOcjDjelot_.setText("");
			}
			else{
				String ocjeDjelot =  dB.getDesiredColumnFromDB("view_control",
						"effectiveness_name", "WHERE control_id='" + m_ControlId + "'");				
				comboOcjDjelot_.setText(ocjeDjelotId+"-"+ocjeDjelot);
				
			}
			
			String owner=table.getModel().getContentAt(6, row).toString();
			if(owner.equals(""))
			{
				comboOdgOsoba_.setText("");
			}
			else{
				String ownerId =  dB.getDesiredColumnFromDB("view_control",
						"owner_id", "WHERE control_id='" + m_ControlId + "'");				
				comboOdgOsoba_.setText(ownerId+"-"+owner);
				
			}
			
			String sukladId=table.getModel().getContentAt(11, row).toString();
			if(sukladId.equals(""))
			{
				comboSukl_.setText("");
			}
			else{
				String suklad =  dB.getDesiredColumnFromDB("view_control",
						"compliance_name", "WHERE control_id='" + m_ControlId + "'");				
				comboSukl_.setText(sukladId+"-"+suklad);
				
			}
			
			String status = table.getModel().getContentAt(13, row).toString();
			if (status.equals(""))
				radioButtonStatus = 0;
			else {
				radioButtonStatus = Integer.parseInt(status);
				switch (radioButtonStatus) {
				case 1:
					btnRadAkt_.setSelection(true);
					btnRadPlan_.setSelection(false);
					btnRadNeAkt_.setSelection(false);
					break;
				case 2:
					btnRadAkt_.setSelection(false);
					btnRadPlan_.setSelection(true);
					btnRadNeAkt_.setSelection(false);
					break;
				case 3:
					btnRadAkt_.setSelection(false);
					btnRadPlan_.setSelection(false);
					btnRadNeAkt_.setSelection(true);
					break;

				default:
					btnRadAkt_.setSelection(false);
					btnRadPlan_.setSelection(false);
					btnRadNeAkt_.setSelection(false);
					break;
				}
			}

			String tip = table.getModel().getContentAt(12, row).toString();
			if (tip.equals(""))
				radioButtonTip = 0;
			else {
				radioButtonTip = Integer.parseInt(tip);
				switch (radioButtonTip) {
				case 1:
					btnRadUprav_.setSelection(true);
					btnRadLog_.setSelection(false);
					btnRadFiz_.setSelection(false);
					break;
				case 2:
					btnRadUprav_.setSelection(false);
					btnRadLog_.setSelection(true);
					btnRadFiz_.setSelection(false);
					break;
				case 3:
					btnRadUprav_.setSelection(false);
					btnRadLog_.setSelection(false);
					btnRadFiz_.setSelection(true);
					break;

				default:
					btnRadUprav_.setSelection(false);
					btnRadLog_.setSelection(false);
					btnRadFiz_.setSelection(false);
					break;
				}
			}

			String vrsta = table.getModel().getContentAt(14, row).toString();
			if (vrsta.equals(""))
				radioButtonVrsta = 0;
			else {
				radioButtonVrsta = Integer.parseInt(vrsta);
				switch (radioButtonVrsta) {
				case 1:
					btnRadPrev_.setSelection(true);
					btnRadDet_.setSelection(false);
					btnRadReak_.setSelection(false);
					break;
				case 2:
					btnRadPrev_.setSelection(false);
					btnRadDet_.setSelection(true);
					btnRadReak_.setSelection(false);
					break;
				case 3:
					btnRadPrev_.setSelection(false);
					btnRadDet_.setSelection(false);
					btnRadReak_.setSelection(true);
					break;

				default:
					btnRadPrev_.setSelection(false);
					btnRadDet_.setSelection(false);
					btnRadReak_.setSelection(false);
					break;
				}
			}
		}
		else
		{
			fillForm();
		}
	}
	
	public void saveAction(){
		if((textNaziv_.getText()!="" && textNaziv_.getText().length()>0 )
				&& (textOznaka_.getText()!="" && textOznaka_.getText().length()>0)
				){
			Hashtable<String, String> data = new Hashtable<String, String>();
//
//			String temp = comboVrsta_.getText();
//			int t = temp.indexOf("-");
			
			data.put("name", textNaziv_.getText());
			if(getRadioButtonSelection("Tip")!=0){
				data.put("type_id", getRadioButtonSelection("Tip")+"");
			}
			else
				data.put("type_id", "");	
			if(getRadioButtonSelection("Vrsta")!=0){
				data.put("class_id", getRadioButtonSelection("Vrsta")+"");
			}
			else
				data.put("class_id", "");				
			if(!comboOcjDjelot_.getText().equals("")){
				data.put("effectiveness_id", comboOcjDjelot_.getText().substring(0,1));
			}
			else
				data.put("effectiveness_id", "");
			data.put("description", textOpis_.getText());
			if(!comboSukl_.getText().equals("")){
				data.put("compliance_id", comboSukl_.getText().substring(0,1));
			}
			else
				data.put("compliance_id", "");
			if(getRadioButtonSelection("Status")!=0){
				data.put("active", getRadioButtonSelection("Status")+"");	
			}
			else
				data.put("active", "");							
			data.put("designation", textOznaka_.getText());		
			if(!comboOdgOsoba_.getText().equals("")){
				data.put("owner_id", comboOdgOsoba_.getText().substring(0,5));
			}
			else
				data.put("owner_id", "");
			System.out.println("Hashtable" + data);
			try {

				if (action == 2) {
					dB.insertDataInDB("as_control", data, "update","Control", m_ControlId);

				} else
					dB.insertDataInDB("as_control", data, "insert","Control", "");


			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);

		
			fillForm();
			}

		else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);
		
		refreshTable();

	}

	private int getRadioButtonSelection(String type) {
		// TODO Auto-generated method stub
		if(type=="Status"){
			if(btnRadAkt_.getSelection()==true)
				return 1;
			if(btnRadPlan_.getSelection()==true)
				return 2;
			if(btnRadNeAkt_.getSelection()==true)
				return 3;
			else
				return 0;
			
		}
		
		if(type=="Tip"){
			if(btnRadUprav_.getSelection()==true)
				return 1;
			if(btnRadLog_.getSelection()==true)
				return 2;
			if(btnRadFiz_.getSelection()==true)
				return 3;
			else
				return 0;
			
		}
		
		if(type=="Vrsta"){
			if(btnRadPrev_.getSelection()==true)
				return 1;
			if(btnRadDet_.getSelection()==true)
				return 2;
			if(btnRadReak_.getSelection()==true)
				return 3;
			else
				return 0;
			
		}
		else
			return 0;
		
	}

	private void setWindowsTitle(boolean set){
		
		if(set){
			controlName = dB.getDesiredColumnFromDB("as_control", "name", "WHERE control_id='"+m_ControlId+"'");
			mParent.getShell().setText("Evidencija Kontrole: "+controlName.toUpperCase()+"");
			
		}
		else
		{
			controlName = "";
			mParent.getShell().setText("Evidencija Kontrola");
			
		}
		
		
		
		
	}
	
	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
	}

	@Focus
	public void setFocus() {
		mParent.setFocus();
	}
}
