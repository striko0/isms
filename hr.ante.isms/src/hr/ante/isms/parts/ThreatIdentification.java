package hr.ante.isms.parts;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable;
import hr.ante.test.asktable.ASTableModel4;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableClickInterceptionListener;
import de.kupzog.ktable.KTableSortedModel;

public class ThreatIdentification {

	@Inject
	MDirtyable dirty;

	private KTableSortedModel m_Model;
	private KTableSortedModel m_ModelRisk;
	private NewASKTable m_Table;
	private int vrsta;
	private int m_Row;
	private int innerRow=0;
	private int action=1;
	private Combo comboVrstaPrijet_;
	private Combo comboPrijetnja_;
	private Composite mParent;
	private NewASKTable table;
	private ASTableModel4 model;
	private String assetName;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(359);
		scrollBox.setMinWidth(450);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);

		parent.getShell().setSize(759, 389);

		m_Model = DataFromServer.listAssetASKTableModel;
		m_ModelRisk = DataFromServer.listRiskASKTableModel;
		m_Row = NewASKTable.clickedRow;
		m_Table = NewASKTable.m_Table;

		assetName = getDesiredColumnFromDB("as_asset", "name", "WHERE asset_id='"+m_Model.getContentAt(1, m_Row)+"'");
		mParent.getShell().setText(
				"Identifikacija prijetnji za imovinu: "+assetName.toUpperCase()+"");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.INHERIT_NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_composite.horizontalIndent = 10;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(2, false));

		Label lblVrstaPrijet_ = new Label(composite, SWT.NONE);
		lblVrstaPrijet_.setText("Vrsta prijetnje:");

		comboVrstaPrijet_ = new Combo(composite, SWT.NONE);

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");
		comboPrijetnja_ = new Combo(composite, SWT.NONE);
		comboPrijetnja_.setEnabled(false);

		comboPrijetnja_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		comboVrstaPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));


		Composite compositeASKTable = new Composite(mParent, SWT.INHERIT_FORCE);
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);
		compositeASKTable.setLayout(new FillLayout());
		table = new NewASKTable(compositeASKTable, new ListRiskASKTableModel(2, 2, m_Model.getContentAt(1,m_Row).toString()),
				717, compositeASKTable.getBounds().height);

//		table = new ASKTable(compositeASKTable, new ThreatIdentASKTableModel(),
//				717, compositeASKTable.getBounds().height);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		compositeButtons_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		compositeButtons_.setLayout(new GridLayout(4, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnSpremi_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
//				dirty.setDirty(true);
				Hashtable<String, String> data = new Hashtable<String, String>();

				/**
				 *
				 *
				 * UPDATE
				 */
				data.put("threat_id",getDesiredColumnFromDB("as_threat","threat_id", "WHERE name='"+comboPrijetnja_.getText().substring(3)+"'"));
				data.put("asset_id",m_Model.getContentAt(1, m_Row).toString());
				data.put("name", m_Model.getContentAt(2, m_Row).toString());
				//data.put("threat_name", comboPrijetnja_.getText().substring(3));
				data.put("assetsubcateg_id", m_Model.getContentAt(3, m_Row).toString());
				data.put("owner", m_Model.getContentAt(4, m_Row).toString());
				data.put("asset_value", m_Model.getContentAt(9, m_Row).toString());
				data.put("confidentiality_level", m_Model.getContentAt(5, m_Row).toString());
				data.put("integrity_level",m_Model.getContentAt(6, m_Row).toString());
				data.put("accessibility_level", m_Model.getContentAt(7, m_Row).toString());
				data.put("businessimpact_level",  m_Model.getContentAt(8, m_Row).toString());

				System.out.println("Hashtable" + data);
				try{
//					if(action==2)
//					{
//
//						insertDataInDB("as_threat", data, "update", m_Model.getContentAt(1, m_Row).toString());
////
////						MPart assetPart = partService.findPart("hr.ante.isms.part.asset");
//						//assetPart.setDirty(true);
//					}
//					else
						insertDataInDB("as_risk", data, "insert", "");

					((ListAssetASKTableModel)m_Model).readAllFromDB();
					((ListRiskASKTableModel)m_ModelRisk).readAllFromDB();


				}
				catch(Exception e1){
					e1.printStackTrace();

				}

			}
		});
		btnSpremi_.setText("Spremi");

		Button btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.setText("Novo");

		Button btnBrisi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnBrisi_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.setText("Bri\u0161i");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
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

	}

	private void fillForm() {
		// TODO Auto-generated method stub

		/**
		 * Dohvaæanje iz baze
		 *
		 */
		comboVrstaPrijet_.setItems(getComboItemsFromDB("as_threat_type"));
		comboVrstaPrijet_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = comboVrstaPrijet_.getSelectionIndex() + 1;
				comboPrijetnja_.setEnabled(true);
				comboPrijetnja_.setItems(getComboItemsFromDB("as_threat",
						"WHERE threattype_id LIKE '" + index + "%'", true));
			}

		});

		if (!table.m_Selection.isEmpty()) {
			action = 2;
			table.addCellSelectionListener(new KTableCellSelectionListener() {

				@Override
				public void fixedCellSelected(int col, int row, int statemask) {
					// TODO Auto-generated method stub

				}

				@Override
				public void cellSelected(int col, int row, int statemask) {
					// TODO Auto-generated method stub
					comboPrijetnja_.setText(getDesiredColumnFromDB("as_threat","threat_id","WHERE name='"+m_Model.getContentAt(7, row).toString()+"'"));
					comboVrstaPrijet_.setItems(getComboItemsFromDB("as_threat_type"));
					comboVrstaPrijet_.setText(getDesiredColumnFromDB("as_threat", "threattype_id", "WHERE name="+m_Model.getContentAt(7, row).toString()+"'"));

				}
			});

		}

		/**
		 * KAD UPDATE RADIMO
		 */
		if (m_Row != 0 && !m_Table.m_Selection.isEmpty()) {
			action = 3;
			comboPrijetnja_.setEnabled(true);
//			comboPrijetnja_.setText(getDesiredColumnFromDB("as_threat","threat_id","WHERE name='"+m_Model.getContentAt(7, m_Row).toString()+"'"));
//			comboVrstaPrijet_.setItems(getComboItemsFromDB("as_threat_type"));
//			comboVrstaPrijet_.setText(getDesiredColumnFromDB("as_threat", "threattype_id", "WHERE threat_id="+m_ModelRisk.getContentAt(1, m_Row).toString()+""));
//
//			table.addClickInterceptionListener(new KTableClickInterceptionListener() {
//
//				@Override
//				public boolean cellClicked(int col, int row, Rectangle cellRect, int x,
//						int y, int button, KTable table) {
//					// TODO Auto-generated method stub
//					comboPrijetnja_.setEnabled(true);
//					comboPrijetnja_.setText(m_ModelRisk.getContentAt(2, m_Row).toString());
//					comboVrstaPrijet_.setItems(getComboItemsFromDB("as_threat_type"));
//					comboVrstaPrijet_.setText(getDesiredColumnFromDB("as_threat", "threattype_id", "WHERE threat_id="+m_ModelRisk.getContentAt(1, m_Row).toString()+""));
//					return false;
//				}
//			});


//			textNaziv_.setText(m_Model.getContentAt(2, m_Row).toString());
//			comboPodkateg_.setText(m_Model.getContentAt(3, m_Row).toString());
//			comboKateg_.setText(getTextFromDB(
//					"as_asset_type",
//					"WHERE assettype_id="
//							+ comboPodkateg_.getText().substring(0, 2) + ""));
			// comboPodkateg_.select(2);

//			 comboPodkateg_.setItems(getComboItemsFromDB("as_subcateg",
//			 "WHERE assubcateg_id LIKE '"+comboKateg_.getText().substring(0,1)+""));

//			comboNoisteljorgjed_.setText(m_Model.getContentAt(4, m_Row)
//					.toString());
//			comboPovjerljivost_.setText(m_Model.getContentAt(5, m_Row)
//					.toString());
//			comboCjelovitost_
//					.setText(m_Model.getContentAt(6, m_Row).toString());
//			comboRaspolozivost_.setText(m_Model.getContentAt(7, m_Row)
//					.toString());
//			comboBi_.setText(m_Model.getContentAt(8, m_Row).toString());
//
//			String Opis_ = getDescriptionFromDB("as_asset", "WHERE name ='"
//					+ textNaziv_.getText() + "'");
//			if (Opis_ == null)
//				Opis_ = " ";
//			textOpis_.setText(Opis_);
//
//			String Objanjenjeostalo_ = getDescriptionFromDB(
//					"as_business_impact", "WHERE businessimpact_level ='"
//							+ m_Model.getContentAt(8, m_Row).toString() + "'");
//			if (Objanjenjeostalo_ == null)
//				Objanjenjeostalo_ = " ";
//			textObjanjenjeostalo_.setText(Objanjenjeostalo_);

		}

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

	public String[] getComboItemsFromDB(String tableName, String whereStatement) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsWithWhere(tableName, whereStatement);

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

	public String[] getComboItemsFromDB(String tableName,
			String whereStatement, boolean valid) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {
			// if(type=="threat")
			return con.getComboItemsThreatOrVulnerability(tableName,
					whereStatement);

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

	public void insertDataInDB(String tableName, Hashtable data, String updateOrInsert, String id) throws Exception{
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			if(updateOrInsert=="insert")
				con.insertThretIdentData(tableName, data);
			if(updateOrInsert=="update")
				con.updateAssetData(tableName, data, id);

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


	public String getTextFromDB(String tableName, String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getTextWithWhere(tableName, whereStatement);

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

	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
	}

	@Persist
	public void save() {
		System.out.println("Saving data");
		// Save the data
		// ...
		// Now set the dirty flag to false
		dirty.setDirty(false);
	}

	@Focus
	public void setFocus() {

		mParent.setFocus();
	}
}
