package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListThreatASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;

import de.kupzog.ktable.KTableSortedModel;

public class Threats implements ViewSelected {

	private int action = 1;
	private KTableSortedModel m_Model;
	private String m_ThreatId;
	private NewASKTable1 table;
	private int m_Row;
	private DataFromDatabase dB;

	private Composite mParent;
	private Combo comboVrsta_;
	private Combo comboVjerojatnost_;
	private Combo comboPorijeklo_;
	private Combo comboUcestalost_;
	private Combo comboRazorMoc_;
	private Combo comboIzvor_;
	private Combo comboNamjera_;
	private Text textNaziv_;
	private Text textOpis_;
	private Text textMotivation_;
	private Button btnNovo_;
	private Button btnDupliciraj_;
	private Button btnBrisi_;

	private String threatTypeId;
	private String probabilityId;
	private String frequencyId;
	private String impactId;
	private String sourceId;
	private String intentionId;
	private String originId;

	@Inject
	private MApplication app;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(-195, 85);
		scrollBox.setMinHeight(450);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);

		m_Model = DataFromServer.listThreatASKTableModel;
		m_Row = NewASKTable1.clickedThreatRow;
		dB = new DataFromDatabase();

		mParent.getShell().setSize(760, 500);
		mParent.getShell().setText("Evidencija Prijetnji");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(4, false));
		GridData gd_composite1 = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_composite1.heightHint = 214;
		composite1.setLayoutData(gd_composite1);

		Label lblNaziv_ = new Label(composite1, SWT.NONE);
		lblNaziv_.setText("Naziv:");

		textNaziv_ = new Text(composite1, SWT.BORDER);
		textNaziv_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblVrsta_ = new Label(composite1, SWT.NONE);
		lblVrsta_.setText("Vrsta:");

		comboVrsta_ = new Combo(composite1, SWT.READ_ONLY);
		GridData gd_comboVrsta_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 3, 1);
		gd_comboVrsta_.widthHint = 190;
		comboVrsta_.setLayoutData(gd_comboVrsta_);

		Label lblOpis_ = new Label(composite1, SWT.NONE);
		lblOpis_.setText("Opis:");
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);

		textOpis_ = new Text(composite1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpis_ = new GridData(SWT.FILL, SWT.FILL, true, true, 4,
				2);
		gd_textOpis_.heightHint = 44;
		textOpis_.setLayoutData(gd_textOpis_);

		Label lblMotivacija = new Label(composite1, SWT.NONE);
		lblMotivacija.setText("Motivacija:");
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);

		textMotivation_ = new Text(composite1, SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL);
		GridData gd_textMotivation_ = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 2);
		gd_textMotivation_.heightHint = 44;
		textMotivation_.setLayoutData(gd_textMotivation_);

		Label lblVjerojatnost_ = new Label(composite1, SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost:");
		lblVjerojatnost_.setBounds(0, 0, 32, 15);

		comboVjerojatnost_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 200;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblPorijeklo_ = new Label(composite1, SWT.NONE);
		lblPorijeklo_.setText("Porijeklo:");

		comboPorijeklo_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboPorijeklo_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboPorijeklo_.widthHint = 130;
		comboPorijeklo_.setLayoutData(gd_comboPorijeklo_);

		Label lblUcestalost_ = new Label(composite1, SWT.NONE);
		lblUcestalost_.setText("U\u010Destalost:");

		comboUcestalost_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboUcestalost_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboUcestalost_.widthHint = 130;
		comboUcestalost_.setLayoutData(gd_comboUcestalost_);

		Label lblRazorMoc_ = new Label(composite1, SWT.NONE);
		lblRazorMoc_.setText("Razorna Mo\u010D:");

		comboRazorMoc_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboRazorMoc_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboRazorMoc_.widthHint = 130;
		comboRazorMoc_.setLayoutData(gd_comboRazorMoc_);

		Label lblIzvor_ = new Label(composite1, SWT.NONE);
		lblIzvor_.setText("Izvor:");

		comboIzvor_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboIzvor_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboIzvor_.widthHint = 130;
		comboIzvor_.setLayoutData(gd_comboIzvor_);

		Label lblNamjera_ = new Label(composite1, SWT.NONE);
		lblNamjera_.setText("Namjera:");

		comboNamjera_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboNamjera_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboNamjera_.widthHint = 130;
		comboNamjera_.setLayoutData(gd_comboNamjera_);

		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_compositeASKTable.heightHint = 72;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		table = new NewASKTable1(this,compositeASKTable, new ListThreatASKTableModel(),
				717, compositeASKTable.getBounds().height);

//		new ASKTable(compositeASKTtable, new ThreatsASKTableModel(), 717,
//				compositeASKTtable.getBounds().height);
		// new Label(mParent, SWT.NONE);

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
				m_ThreatId=null;
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
						dB.deleteDataFromDB("as_threat", "threat_id", m_ThreatId);
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
				HashMap hm = new HashMap();
				try {

					Connection connection = null;
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//					String url = "jdbc:sqlserver://192.168.0.76"/* 192.168.0.70 */;
					String url = "jdbc:sqlserver://127.0.0.1:1433;integratedSecurity=true"; 
//					String username = "sa"; // You should modify this.
//					String password = "sa"; // You should modify this.
					// Load the JDBC driver
					try {
						Class.forName(driverName);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Create a connection to the database
					try {
//						connection = DriverManager.getConnection(url, username,
//								password);
						connection = DriverManager.getConnection(url);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

//					JasperPrint print = JasperFillManager.fillReport("C:/Documents and Settings/Zrosko/git/isms/hr.ante.isms/src/reports/prijetnje.jasper", hm, /*new JREmptyDataSource()*/connection);
					JasperPrint print = JasperFillManager.fillReport("C:/Reports/prijetnje.jasper", hm, /*new JREmptyDataSource()*/connection);

					JasperViewer.viewReport(print,false);

				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIspis_.setLayoutData(gd_btnIspis_);

		fillForm();
		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);

	}

	public void refreshTable(){
		((ListThreatASKTableModel)table.getModel()).readAllFromDB();
		table.redraw();
	}

	private void fillForm() {
		// TODO Auto-generated method stub
		action=1;
		initialSettings();
		table.setSelection(null, false);

	}

	private void initialSettings(){


		comboVrsta_.setItems(dB.getComboItemsFromDB("as_threat_type"));
		comboVjerojatnost_.setItems(dB.getComboItemsFromDB("as_probability"));
		comboPorijeklo_.setItems(dB.getComboItemsFromDB("as_threat_origin"));
		comboUcestalost_.setItems(dB.getComboItemsFromDB("as_threat_frequency"));
		comboRazorMoc_.setItems(dB.getComboItemsFromDB("as_threat_impact"));
		comboIzvor_.setItems(dB.getComboItemsFromDB("as_threat_source"));
		comboNamjera_.setItems(dB.getComboItemsFromDB("as_threat_intention"));

		btnBrisi_.setEnabled(false);
		btnDupliciraj_.setEnabled(false);
		btnNovo_.setEnabled(false);

		textNaziv_.setText("");
		textMotivation_.setText("");
		comboVrsta_.setText("");
		textOpis_.setText("");
		comboVjerojatnost_.setText("");
		comboPorijeklo_.setText("");
		comboUcestalost_.setText("");
		comboRazorMoc_.setText("");
		comboIzvor_.setText("");
		comboNamjera_.setText("");
	}

	@Override
	public void rowSelected(int row) {
		// TODO Auto-generated method stub
		if (row!=0 && !table.getModel().getContentAt(1, row).toString().equals("")) {
			if(action==2){
				initialSettings();
			}
			action=2;
			btnBrisi_.setEnabled(true);
			btnDupliciraj_.setEnabled(true);
			btnNovo_.setEnabled(true);
			m_ThreatId=table.getModel().getContentAt(5, row).toString();

			textNaziv_.setText(table.getModel().getContentAt(1, row).toString());

			String threatType =  table.getModel().getContentAt(3, row).toString();
			threatTypeId =  dB.getDesiredColumnFromDB("view_threat",
					"threattype_id", "WHERE threat_id='" + m_ThreatId + "'");

			textOpis_.setText(table.getModel().getContentAt(7, row).toString());

			textMotivation_.setText(table.getModel().getContentAt(8, row).toString());

			String probability =  table.getModel().getContentAt(4, row).toString();
			if(probability.equals(""))
			{
				comboVjerojatnost_.setText("");
			}
			else{
				probabilityId =  dB.getDesiredColumnFromDB("view_threat",
						"probability", "WHERE threat_id='" + m_ThreatId + "'");
				comboVjerojatnost_.setText(probabilityId + "-" + probability);
				
			}			

			String origin =  table.getModel().getContentAt(9, row).toString();			
			if(origin.equals(""))
			{
				comboPorijeklo_.setText("");
			}
			else{
				originId =  dB.getDesiredColumnFromDB("view_threat",
						"origin", "WHERE threat_id='" + m_ThreatId + "'");
				comboPorijeklo_.setText(originId + "-" + origin);
				
			}
			

			String frequency =  table.getModel().getContentAt(10, row).toString();			
			if(frequency.equals(""))
			{
				comboUcestalost_.setText("");
			}
			else{
				frequencyId =  dB.getDesiredColumnFromDB("view_threat",
						"frequency", "WHERE threat_id='" + m_ThreatId + "'");
				comboUcestalost_.setText(frequencyId + "-" + frequency);
				
			}

			String impact =  table.getModel().getContentAt(11, row).toString();			
			if(impact.equals(""))
			{
				comboRazorMoc_.setText("");
			}
			else{
				impactId =  dB.getDesiredColumnFromDB("view_threat",
						"impact_level", "WHERE threat_id='" + m_ThreatId + "'");
				comboRazorMoc_.setText(impactId+ "-" + impact);
				
			}

			String source =  table.getModel().getContentAt(12, row).toString();			
			if(source.equals(""))
			{
				comboIzvor_.setText("");
			}
			else{
				sourceId =  dB.getDesiredColumnFromDB("view_threat",
						"source", "WHERE threat_id='" + m_ThreatId + "'");
				comboIzvor_.setText(sourceId+ "-" + source);
				
			}

			String intention =  table.getModel().getContentAt(6, row).toString();
			if(intention.equals(""))
			{
				comboNamjera_.setText("");
			}
			else{
				intentionId =  dB.getDesiredColumnFromDB("view_threat",
						"intention", "WHERE threat_id='" + m_ThreatId + "'");
				comboNamjera_.setText(intentionId+"-" + intention);
				
			}

			comboVrsta_.setText(threatTypeId + "-" + threatType);

		}
		else
		{
			fillForm();
		}
	}

	public void saveAction(){
		if((textNaziv_.getText()!="" && textNaziv_.getText().length()>0 )
				&& (comboVrsta_.getText()!="" && comboVrsta_.getText().length()>0)

				){
			Hashtable<String, String> data = new Hashtable<String, String>();


			String temp = comboVrsta_.getText();
			int t = temp.indexOf("-");
			data.put("threattype_id",comboVrsta_.getText().substring(0,t));
			data.put("name",textNaziv_.getText());

			if(!comboIzvor_.getText().equals("")){
				temp = comboIzvor_.getText();
				t = temp.indexOf("-");
				data.put("source",comboIzvor_.getText().substring(0,t));
			}
			else
				data.put("source","");

			if(!comboPorijeklo_.getText().equals("")){
				temp = comboPorijeklo_.getText();
				t = temp.indexOf("-");
				data.put("origin",comboPorijeklo_.getText().substring(0,t));
			}
			else
				data.put("origin","");

			if(!comboNamjera_.getText().equals("")){
				temp = comboNamjera_.getText();
				t = temp.indexOf("-");
				data.put("intention",comboNamjera_.getText().substring(0,t));
			}
			else
				data.put("intention","");

//			if(!textOpis_.getText().equals("")){
				data.put("description",textOpis_.getText());
//			}
//			else
//				data.put("description","");

//			if(!textMotivation_.getText().equals("")){
				data.put("motivation",textMotivation_.getText());
//			}
//			else
//				data.put("motivation","");

			if(!comboRazorMoc_.getText().equals("")){
				temp = comboRazorMoc_.getText();
				t = temp.indexOf("-");
				data.put("impact_level",comboRazorMoc_.getText().substring(0,t));
			}
			else
				data.put("impact_level","");

			if(!comboUcestalost_.getText().equals("")){
				temp = comboUcestalost_.getText();
				t = temp.indexOf("-");
				data.put("frequency",comboUcestalost_.getText().substring(0,t));
			}
			else
				data.put("frequency","");


			if(!comboVjerojatnost_.getText().equals("")){
				temp = comboVjerojatnost_.getText();
				t = temp.indexOf("-");
				data.put("probability",comboVjerojatnost_.getText().substring(0,t));
			}
			else
				data.put("probability","");

			System.out.println("Hashtable" + data);
			try {

				if (action == 2) {
					dB.insertDataInDB("as_threat", data, "update","Threat", m_ThreatId);

				} else
					dB.insertDataInDB("as_threat", data, "insert","Threat", "");


			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);

			refreshTable();
			fillForm();

		}
		else{
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);
			
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
