package hr.ante.isms.parts;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
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

public class Probability implements ViewSelected {


	private int action=1;
	private KTableSortedModel m_Model;
	private KTableSortedModel m_ModelRisk;
	private NewASKTable m_Table;
	private NewASKTable table;
	private String m_Riskid;
	private int m_Row;
	private String threatId;
	private String assetName;
	private Composite mParent;
	private Combo comboPrijet_;
	private Combo comboRanjivost_;
	private Combo comboVjerojatnost_;
	private Combo comboVjerojatnostOtkrivanja_;
	private Text textVjerojatnostOtkrivanja_;
	private Text textOpisVjerojat_;
	private Button btnBrisi_;
	private Button btnDupl_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(370);
		scrollBox.setMinWidth(600);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.getShell().setSize(759, 400);

		m_ModelRisk = DataFromServer.listRiskASKTableModel;
		m_Model = DataFromServer.listAssetASKTableModel;
		m_Row = NewASKTable.clickedAssetRow;


		assetName = getDesiredColumnFromDB("as_asset", "name", "WHERE asset_id='"+m_Model.getContentAt(1, m_Row)+"'");
		mParent.getShell().setText(
				"Vjerojatnost ostvarenja prijetnje za imovinu: "+assetName.toUpperCase()+"");
		mParent.setLayout(new GridLayout(2, false));

		Label labelPrijet_ = new Label(mParent, SWT.NONE);
		GridData gd_labelPrijet_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_labelPrijet_.horizontalIndent = 10;
		gd_labelPrijet_.widthHint = 60;
		labelPrijet_.setLayoutData(gd_labelPrijet_);
		labelPrijet_.setText("Prijetnja:");

		comboPrijet_ = new Combo(mParent, SWT.NONE);
		comboPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblRanjivost_ = new Label(mParent, SWT.NONE);
		GridData gd_lblRanjivost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRanjivost_.horizontalIndent = 10;
		lblRanjivost_.setLayoutData(gd_lblRanjivost_);
		lblRanjivost_.setText("Ranjivost:");

		comboRanjivost_ = new Combo(mParent, SWT.NONE);
		comboRanjivost_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpVjerojatnostOstvarenjaPrijetnje = new Group(mParent, SWT.NONE);
		grpVjerojatnostOstvarenjaPrijetnje.setLayout(new GridLayout(4, false));
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 2);
		gd_grpVjerojatnostOstvarenjaPrijetnje.horizontalIndent = 10;
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 122;
		gd_grpVjerojatnostOstvarenjaPrijetnje.widthHint = 799;
		grpVjerojatnostOstvarenjaPrijetnje.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje.setText("Vjerojatnost Ostvarenja Prijetnje");

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_lblVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblVjerojatnost_.widthHint = 96;
		lblVjerojatnost_.setLayoutData(gd_lblVjerojatnost_);
		lblVjerojatnost_.setText("Vjerojatnost:");

		comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 150;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);


		Label lblVjerojatnostOtkrivanja_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblVjerojatnostOtkrivanja_.setText("Vjerojatnost otkrivanja:");

		comboVjerojatnostOtkrivanja_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_comboVjerojatnostOtkrivanja_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboVjerojatnostOtkrivanja_.widthHint = 150;
		comboVjerojatnostOtkrivanja_.setLayoutData(gd_comboVjerojatnostOtkrivanja_);

		Label lblOpisVjerojat_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojat_.setText("Opis Vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		Label lblOpisVjerojatnostiOtkrivnja = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojatnostiOtkrivnja.setText("Opis vjerojatnosti otkrivnja:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		textOpisVjerojat_ = new Text(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textOpisVjerojat_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));

		textVjerojatnostOtkrivanja_ = new Text(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.BORDER | SWT.WRAP
						| SWT.V_SCROLL);
		textVjerojatnostOtkrivanja_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));



		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_compositeASKTable.widthHint = 778;
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		table = new NewASKTable(this, compositeASKTable, new ListRiskASKTableModel(3, 4, m_Model.getContentAt(1,m_Row).toString()), 717, 200);
//		new ASKTable(compositeASKTable, new ProbabilityASKTableModel(), 717,compositeASKTable.getBounds().height );
				new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.CENTER,
				true, false, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 320;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(3, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnNewButton);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				saveAction();
				action=2;
			}
		});
		btnSpremi_.setText("Spremi");

		btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		btnBrisi_.setEnabled(false);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_1.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnNewButton_1);
		btnBrisi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				boolean confirm = Dialog.isConfirmed("Je ste li sigurni da želite obrisati podatak?", "Podatak æe biti obrisan");

				if (confirm) {
					try {
						deleteDataFromDB("as_risk", "risk_id", m_Riskid);
						refreshTable();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBrisi_.setText("Bri\u0161i");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_2.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnNewButton_2);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mParent.getShell().close();
			}
		});

		fillForm();
		scrollBox.setContent(mParent);
	}



	public void refreshTable(){
		((ListAssetASKTableModel)m_Model).readAllFromDB();
		((ListRiskASKTableModel)table.getModel()).readAllFromDB();
		((ListRiskASKTableModel) m_ModelRisk).readAllFromDB();
		table.redraw();
	}

	private void fillForm() {
		// TODO Auto-generated method stub

		/**
		 * Dohvaæanje iz baze
		 *
		 */
		action=1;
		comboPrijet_.setItems(getThreatVulnerabilityItemsFromDB("as_threat",""));
		comboRanjivost_.setItems(getThreatVulnerabilityItemsFromDB("as_vulnerability",""));

		comboVjerojatnost_.setItems(getComboItemsFromDB("as_probability"));
		comboVjerojatnostOtkrivanja_.setItems(getComboItemsFromDB("as_detection_probability"));

		textOpisVjerojat_.setText("");
		textVjerojatnostOtkrivanja_.setText("");
		comboPrijet_.setText("");
		comboRanjivost_.setText("");
		comboVjerojatnost_.setText("");
		comboVjerojatnostOtkrivanja_.setText("");

		table.m_Selection.clear();

	}

	@Override
	public void rowSelected(int row) {
		// TODO Auto-generated method stub
		fillForm();
		if (row!=0 && table.getModel().getContentAt(1, row).toString()!=""/*!table.getModel().getContentAt(1, row).toString().equals("")*/) {
			action=2;
			btnBrisi_.setEnabled(true);

			m_Riskid=table.getModel().getContentAt(7, row).toString();

			String vulnerabilityId = table.getModel().getContentAt(5, row).toString();
			threatId= table.getModel().getContentAt(6, row).toString();
			String probability_id = table.getModel().getContentAt(3, row).toString();
			String det_probability_id = table.getModel().getContentAt(4, row).toString();

			String threat = table.getModel().getContentAt(1, row)
						.toString();
			String vulnerability = table.getModel().getContentAt(2, row)
							.toString();

			comboRanjivost_.setText(vulnerabilityId + "-" + vulnerability);
			comboPrijet_.setText(threatId + "-" + threat);



			if ((!probability_id.equals("0") && probability_id.length() > 0)){
					String probability = getDesiredColumnFromDB("as_probability",
							"name", "WHERE probability_id=" + probability_id + "");
					comboVjerojatnost_.setText(probability_id + "-"
							+ probability);

					String opisVjerojatnosti = getDesiredColumnFromDB("view_risk", "description_risk_probability","WHERE risk_id=" + m_Riskid + "");
					if(opisVjerojatnosti!=null)
						textOpisVjerojat_.setText(opisVjerojatnosti);
					else
						textOpisVjerojat_.setText("");
			}
			else{
					comboVjerojatnost_.setText("");
			}
			if(!det_probability_id.equals("0") && det_probability_id.length() > 0) {

					String det_probability = getDesiredColumnFromDB(
							"as_detection_probability", "name",
							"WHERE asdetectionprobability_id=" + det_probability_id
									+ "");
					comboVjerojatnostOtkrivanja_.setText(det_probability_id
							+ "-" + det_probability);

					String opisVjerojatnostOtkr = getDesiredColumnFromDB(
							"view_risk", "description_detection_probability",
							"WHERE risk_id=" + m_Riskid + "");
					if(opisVjerojatnostOtkr!=null)
						textVjerojatnostOtkrivanja_.setText(opisVjerojatnostOtkr);
					else
						textVjerojatnostOtkrivanja_.setText("");

			}
			else{
					comboVjerojatnostOtkrivanja_.setText("");

			}

		}
		else
		{
			fillForm();

		}
	}




	public void saveAction(){
		if((comboPrijet_.getText()!="" && comboPrijet_.getText().length()>0 )
				&& (comboRanjivost_.getText()!="" && comboRanjivost_.getText().length()>0)
				&& (comboVjerojatnost_.getText()!="" && comboVjerojatnost_.getText().length()>0)){
			Hashtable<String, String> data = new Hashtable<String, String>();

			String temp = comboPrijet_.getText();
			int t = temp.indexOf("-");
			data.put("threat_id",getDesiredColumnFromDB("as_threat", "threat_id","WHERE name='"+ comboPrijet_.getText().substring(t+1) + "'"));
			temp = comboRanjivost_.getText();
			t= temp.indexOf("-");
			data.put("vulnerability_id",getDesiredColumnFromDB("as_vulnerability", "vulnerability_id","WHERE name='"+ comboRanjivost_.getText().substring(t+1) + "'"));
			data.put("asset_id", m_Model.getContentAt(1, m_Row).toString());
			data.put("name", m_Model.getContentAt(2, m_Row).toString());
			data.put("assetsubcateg_id", m_Model.getContentAt(3, m_Row).toString());
			data.put("owner", m_Model.getContentAt(4, m_Row).toString());
			data.put("asset_value", m_Model.getContentAt(9, m_Row).toString());
			data.put("confidentiality_level",m_Model.getContentAt(5, m_Row).toString());
			data.put("integrity_level", m_Model.getContentAt(6, m_Row).toString());
			data.put("accessibility_level",	m_Model.getContentAt(7, m_Row).toString());
			data.put("businessimpact_level",m_Model.getContentAt(8, m_Row).toString());

			if(comboVjerojatnost_.getText().equals(""))
				data.put("risk_probability", " ");
			else{
				data.put("risk_probability", comboVjerojatnost_.getText());
			}


			if(comboVjerojatnostOtkrivanja_.getText().equals(""))
				data.put("detection_probability", " ");
			else{
				data.put("detection_probability", comboVjerojatnostOtkrivanja_.getText());
			}

			if(textOpisVjerojat_.getText().equals(""))
				data.put("description_risk_probability", " ");
			else{
				data.put("description_risk_probability", textOpisVjerojat_.getText());
			}

			if( textVjerojatnostOtkrivanja_.equals(""))
				data.put("description_detection_probability", " ");
			else{
				data.put("description_detection_probability", textVjerojatnostOtkrivanja_.getText());
			}



			System.out.println("Hashtable" + data);
			try {

				if (action == 2) {
					insertDataInDB("as_risk", data, "update", m_Riskid);

				} else
					insertDataInDB("as_risk", data, "insert", "");


			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);

		}

		else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);

		refreshTable();
		fillForm();
	}

	public String[] getComboItemsFromDB(String tableName) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItems(tableName);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {};

	}


	public String[] getThreatVulnerabilityItemsFromDB(String tableName,String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsThreatOrVulnerability(tableName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[]{};

	}

	public String getDesiredColumnFromDB(String tableName, String columnName, String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getContentFromDesiredColumn(tableName, columnName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String();

	}

	public void insertDataInDB(String tableName, Hashtable data, String updateOrInsert, String id) throws Exception{
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			if(updateOrInsert=="insert")
				con.insertProbabilityData(tableName, data);
			if(updateOrInsert=="update")
				con.updateProbabilityData(tableName, data, id);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}
	public void deleteDataFromDB(String tableName, String idField, String id) throws Exception{
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			con.deleteData(tableName,idField, id);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
