package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.connection.DatabaseConnectionDoma;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

public class ImpactAnalysis implements ViewSelected {

	private DataFromDatabase dB;
	private int action = 1;
	private KTableSortedModel m_Model;
	private KTableSortedModel m_ModelRisk;
	private NewASKTable1 m_Table;
	private NewASKTable1 table;
	private String m_Riskid;
	private int m_Row;

	private String assetName;
	private Composite mParent;

	private Combo comboPrijet_;
	private Combo comboRanjivost_;
	private Combo comboVjerojatnost_;
	private Combo comboUcinak_;
	private Text textOpisVjerojat_;
	private Text textOpisUcinka_;
	private Button btnBrisi_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);

		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(550);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.getShell().setSize(700, 600);

		m_ModelRisk = DataFromServer.listRiskASKTableModel;
		m_Model = DataFromServer.listAssetASKTableModel;
		m_Row = NewASKTable1.clickedAssetRow;
		dB = new DataFromDatabase();

		assetName = dB.getDesiredColumnFromDB("as_asset", "name",
				"WHERE asset_id='" + m_Model.getContentAt(1, m_Row) + "'");
		mParent.getShell().setText(
				"Analiza uèinka ostvarenja prijetnja za imovinu: "
						+ assetName.toUpperCase() + "");

		mParent.setLayout(new GridLayout(2, false));

		Label labelPrijet_ = new Label(mParent, SWT.NONE);
		GridData gd_labelPrijet_ = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_labelPrijet_.horizontalIndent = 10;
		gd_labelPrijet_.widthHint = 60;
		labelPrijet_.setLayoutData(gd_labelPrijet_);
		labelPrijet_.setText("Prijetnja:");

		comboPrijet_ = new Combo(mParent, SWT.READ_ONLY);
		comboPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblRanjivost_ = new Label(mParent, SWT.NONE);
		GridData gd_lblRanjivost_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblRanjivost_.horizontalIndent = 10;
		lblRanjivost_.setLayoutData(gd_lblRanjivost_);
		lblRanjivost_.setText("Ranjivost:");

		comboRanjivost_ = new Combo(mParent, SWT.READ_ONLY);
		comboRanjivost_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Group grpVjerojatnostOstvarenjaPrijetnje = new Group(mParent, SWT.NONE);
		grpVjerojatnostOstvarenjaPrijetnje.setLayout(new GridLayout(2, false));
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL,
				SWT.FILL, true, true, 2, 2);
		gd_grpVjerojatnostOstvarenjaPrijetnje.horizontalIndent = 10;
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 107;
		gd_grpVjerojatnostOstvarenjaPrijetnje.widthHint = 799;
		grpVjerojatnostOstvarenjaPrijetnje
				.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje
				.setText("Vjerojatnost Ostvarenja Prijetnje");

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.NONE);
		GridData gd_lblVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_lblVjerojatnost_.widthHint = 96;
		lblVjerojatnost_.setLayoutData(gd_lblVjerojatnost_);
		lblVjerojatnost_.setText("Vjerojatnost:");

		comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.READ_ONLY);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 250;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblOpisVjerojat_ = new Label(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.NONE);
		lblOpisVjerojat_.setText("Opis Vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		textOpisVjerojat_ = new Text(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpisVjerojat_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_textOpisVjerojat_.minimumHeight = 65;
		textOpisVjerojat_.setLayoutData(gd_textOpisVjerojat_);

		Group grpAnalizaUinkaPrijetnje = new Group(mParent, SWT.NONE);
		grpAnalizaUinkaPrijetnje.setText("Analiza U\u010Dinka Prijetnje");
		GridData gd_grpAnalizaUinkaPrijetnje = new GridData(SWT.FILL, SWT.FILL,
				true, true, 2, 1);
		gd_grpAnalizaUinkaPrijetnje.horizontalIndent = 10;
		gd_grpAnalizaUinkaPrijetnje.heightHint = 125;
		gd_grpAnalizaUinkaPrijetnje.widthHint = 371;
		grpAnalizaUinkaPrijetnje.setLayoutData(gd_grpAnalizaUinkaPrijetnje);
		grpAnalizaUinkaPrijetnje.setLayout(new GridLayout(2, false));

		Label lblUcinak_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUcinak_.setText("U\u010Dinak:");

		comboUcinak_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.READ_ONLY);
		GridData gd_comboUcinak_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboUcinak_.widthHint = 250;
		comboUcinak_.setLayoutData(gd_comboUcinak_);

		Label lblOpisUcinka_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_lblOpisUcinka_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblOpisUcinka_.widthHint = 104;
		lblOpisUcinka_.setLayoutData(gd_lblOpisUcinka_);
		lblOpisUcinka_.setText("Opis U\u010Dinka:");
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		textOpisUcinka_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpisUcinka_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_textOpisUcinka_.minimumHeight = 65;
		gd_textOpisUcinka_.heightHint = 46;
		textOpisUcinka_.setLayoutData(gd_textOpisUcinka_);
		textOpisUcinka_.setEnabled(false);

		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_compositeASKTable.heightHint = 95;
		gd_compositeASKTable.minimumHeight = 150;
		gd_compositeASKTable.widthHint = 778;
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		table = new NewASKTable1(this, compositeASKTable,
				new ListRiskASKTableModel(4, 5, m_Model.getContentAt(1, m_Row)
						.toString()), 717, 200);
		// new ASKTable(compositeASKTable,new ImpactAnalysisASKTableModel(),
		// 717,compositeASKTable.getBounds().height );
		new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.CENTER,
				true, false, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 320;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(3, false));

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
				//action = 1;
			}
		});
		btnSpremi_.setText("Spremi");

		btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnBrisi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.setEnabled(false);
		btnBrisi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				boolean confirm = Dialog.isConfirmed(
						"Je ste li sigurni da želite obrisati podatak?",
						"Podatak æe biti obrisan");

				if (confirm) {
					try {
						dB.deleteDataFromDB("as_risk", "risk_id", m_Riskid);
						fillForm();
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

		fillForm();
		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);
	}

	public void refreshTable() {
		((ListAssetASKTableModel) m_Model).readAllFromDB();
		((ListRiskASKTableModel) table.getModel()).readAllFromDB();
		((ListRiskASKTableModel) m_ModelRisk).readAllFromDB();
		table.redraw();
	}

	private void fillForm() {
		// TODO Auto-generated method stub
		action = 1;
		initialSettings();
		table.setSelection(null, false);


	}

	private void initialSettings() {

		comboPrijet_
				.setItems(dB.getThreatVulnerabilityItemsFromDB("as_threat", ""));
		comboRanjivost_.setItems(dB.getThreatVulnerabilityItemsFromDB(
				"as_vulnerability", ""));
		comboVjerojatnost_.setItems(dB.getComboItemsFromDB("as_probability"));
		comboUcinak_.setItems(dB.getComboItemsFromDB("as_threat_impact"));
		comboUcinak_.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				textOpisUcinka_.setEnabled(true);
				
			}
			
		});
		comboPrijet_.setText("");
		comboRanjivost_.setText("");
		comboVjerojatnost_.setText("");
		comboUcinak_.setText("");
		textOpisUcinka_.setText("");
		textOpisVjerojat_.setText("");

	}

	@Override
	public void rowSelected(int row) {
		// TODO Auto-generated method stub
		if (row != 0
				&& !table.getModel().getContentAt(1, row).toString().equals("")) {
			action = 2;
			initialSettings();

			btnBrisi_.setEnabled(true);
			m_Riskid = table.getModel().getContentAt(7, row).toString();

			String vulnerabilityId = table.getModel().getContentAt(5, row)
					.toString();
			String threatId = table.getModel().getContentAt(6, row).toString();
			String probability_id = table.getModel().getContentAt(3, row)
					.toString();
			String impact_id = table.getModel().getContentAt(4, row).toString();

			String threat = table.getModel().getContentAt(1, row).toString();
			String vulnerability = table.getModel().getContentAt(2, row)
					.toString();

			String probability = dB.getDesiredColumnFromDB("as_probability",
					"name", "WHERE probability_id=" + probability_id + "");

			comboRanjivost_.setText(vulnerabilityId + "-" + vulnerability);
			comboPrijet_.setText(threatId + "-" + threat);
			comboVjerojatnost_.setText(probability_id + "-" + probability);

			String opisVjerojatnosti = dB.getDesiredColumnFromDB("view_risk",
					"description_risk_probability", "WHERE risk_id=" + m_Riskid
							+ "");
			if (opisVjerojatnosti == null)
				textOpisVjerojat_.setText("");
			else
				textOpisVjerojat_.setText(opisVjerojatnosti);
			if (!impact_id.equals("") && impact_id.length() > 0) {

				String impact = dB.getDesiredColumnFromDB("as_impact", "name",
						"WHERE asimpact_id=" + impact_id + "");
				comboUcinak_.setText(impact_id + "-" + impact);
				textOpisUcinka_.setEnabled(true);
				
					

			} else {
				comboUcinak_.setText("");
				textOpisUcinka_.setEnabled(false);
			}
			
			String opisUcinka = dB.getDesiredColumnFromDB("view_risk",
					"description_impact", "WHERE risk_id=" + m_Riskid + "");
			if (opisUcinka == null)
				textOpisUcinka_.setText("");					
			else
				textOpisUcinka_.setText(opisUcinka);

		} else {
			fillForm();
		}
	}

	public void saveAction() {
		if ((comboPrijet_.getText() != "" && comboPrijet_.getText().length() > 0)
				&& (comboRanjivost_.getText() != "" && comboRanjivost_
						.getText().length() > 0)
				&& (comboVjerojatnost_.getText() != "" && comboVjerojatnost_
						.getText().length() > 0)
				&& (comboUcinak_.getText() != "" && comboUcinak_.getText()
						.length() > 0)) {
			Hashtable<String, String> data = new Hashtable<String, String>();

			String temp = comboPrijet_.getText();
			int t = temp.indexOf("-");
			data.put(
					"threat_id",
					dB.getDesiredColumnFromDB(
							"as_threat",
							"threat_id",
							"WHERE name='"
									+ comboPrijet_.getText().substring(t + 1)
									+ "'"));
			temp = comboRanjivost_.getText();
			t = temp.indexOf("-");
			data.put(
					"vulnerability_id",
					dB.getDesiredColumnFromDB("as_vulnerability",
							"vulnerability_id", "WHERE name='"
									+ comboRanjivost_.getText()
											.substring(t + 1) + "'"));
			data.put("asset_id", m_Model.getContentAt(1, m_Row).toString());
			
			data.put("risk_probability", comboVjerojatnost_.getText());
//			if (textOpisVjerojat_.getText().equals(""))
				data.put("description_risk_probability", "");
//			else {
//				data.put("description_risk_probability",
//						textOpisVjerojat_.getText());
//			}			

//			if (comboUcinak_.getText().equals(""))
//				data.put("impact", "");
//			else {
				data.put("impact", comboUcinak_.getText());
//			}

//			if (textOpisUcinka_.getText().equals(""))
//				data.put("description_impact", "");
//			else {
				data.put("description_impact", textOpisUcinka_.getText());
//			}
			
			
			if (action == 2) {
				try {
					System.out.println("Hashtable" + data);
					dB.insertDataInDB("as_risk", data, "update","ImpactAnalysis", m_Riskid);
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			} else {
				data.put("name", m_Model.getContentAt(2, m_Row).toString());
				data.put("assetsubcateg_id", m_Model.getContentAt(3, m_Row)
						.toString());
				data.put("owner", m_Model.getContentAt(4, m_Row).toString());
				data.put("asset_value", m_Model.getContentAt(9, m_Row)
						.toString());
				data.put("confidentiality_level", m_Model
						.getContentAt(5, m_Row).toString());
				data.put("integrity_level", m_Model.getContentAt(6, m_Row)
						.toString());
				data.put("accessibility_level", m_Model.getContentAt(7, m_Row)
						.toString());
				data.put("businessimpact_level", m_Model.getContentAt(8, m_Row)
						.toString());
				

				
				try {
					System.out.println("Hashtable" + data);
					dB.insertDataInDB("as_risk", data, "insert","ImpactAnalysis", "");

				} catch (Exception e1) {
					e1.printStackTrace();

				}
				
			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"), "Spremanje uspješno",
					"Podaci su spremljeni", NotifierTheme.GREEN_THEME);

			fillForm();
			refreshTable();
		}

		else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"), "Nemože se spremiti",
					"Niste unijeli sve potrebno podatke",
					NotifierTheme.RED_THEME);

	}

//	public String[] getComboItemsFromDB(String tableName) {
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getComboItems(tableName);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String[] {};
//
//	}

//	public String[] getThreatVulnerabilityItemsFromDB(String tableName,
//			String whereStatement) {
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getComboItemsThreatOrVulnerability(tableName,
//					whereStatement);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String[] {};
//
//	}

//	public String getDesiredColumnFromDB(String tableName, String columnName,
//			String whereStatement) {
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getContentFromDesiredColumn(tableName, columnName,
//					whereStatement);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String();
//
//	}

//	public void insertDataInDB(String tableName, Hashtable data,
//			String updateOrInsert, String id) throws Exception {
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			if (updateOrInsert == "insert")
//				con.insertImpactAnalysisData(tableName, data);
//			if (updateOrInsert == "update")
//				con.updateImpactAnalysisData(tableName, data, id);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}
//
//	public void deleteDataFromDB(String tableName, String idField, String id)
//			throws Exception {
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			con.deleteData(tableName, idField, id);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}

	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
	}

	@Focus
	public void setFocus() {
		mParent.setFocus();
	}
}
