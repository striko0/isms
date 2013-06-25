package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.connection.DatabaseConnectionDoma;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;
import hr.ante.test.asktable.ASTableModel4;

import java.awt.Point;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;

import de.kupzog.ktable.KTableSortedModel;

public class ThreatIdentification implements ViewSelected {



	private DataFromDatabase dB;
	private KTableSortedModel m_Model;
	private KTableSortedModel m_ModelRisk;
	private NewASKTable1 m_Table;
	private String m_Riskid;
	private int m_Row;
	private String m_AssetId;
	private int m_AssetRow;
	private int m_RiskRow;
	private int innerRow=0;
	private int action=1;
	private Combo comboVrstaPrijet_;
	private Combo comboPrijetnja_;
	private Composite mParent;
	private NewASKTable1 table;
	private String assetName;
	private Button btnBrisi_;
	private Button btnNovo_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(280);
		scrollBox.setMinWidth(450);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);

		mParent.getShell().setSize(759, 400);

		m_ModelRisk = DataFromServer.listRiskASKTableModel;
		m_Model = DataFromServer.listAssetASKTableModel;
		m_Row = NewASKTable1.clickedAssetRow;
		dB = new DataFromDatabase();

		assetName = dB.getDesiredColumnFromDB("as_asset", "name", "WHERE asset_id='"+m_Model.getContentAt(1, m_Row)+"'");
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

		comboVrstaPrijet_ = new Combo(composite, SWT.READ_ONLY);

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");
		comboPrijetnja_ = new Combo(composite, SWT.READ_ONLY);
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

		table = new NewASKTable1(this,compositeASKTable, new ListRiskASKTableModel(2, 2,m_Model.getContentAt(1,m_Row).toString()),
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
					saveAction();
					}
		});
		btnSpremi_.setText("Spremi");

		btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
//				table.m_Selection.clear();
				m_Riskid=null;
				fillForm();
			}
		});
		btnNovo_.setText("Novo");

		btnBrisi_ = new Button(compositeButtons_, SWT.CENTER);
		btnBrisi_.setEnabled(false);
		btnNovo_.setEnabled(false);
		GridData gd_btnBrisi_ = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
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
		mParent.getShell().setDefaultButton(btnSpremi_);

	}

	private void refreshTable(){

		((ListAssetASKTableModel)m_Model).readAllFromDB();
		((ListRiskASKTableModel)table.getModel()).readAllFromDB();
		((ListRiskASKTableModel) m_ModelRisk).readAllFromDB();
		table.redraw();

	}

	private void fillForm() {
		// TODO Auto-generated method stub
		action=1;
		initialSettings();
		table.setSelection(null, false);


	}

	private void initialSettings(){
		
		btnBrisi_.setEnabled(false);
		btnNovo_.setEnabled(false);
		comboPrijetnja_.setEnabled(false);
		comboVrstaPrijet_.setText("");
		comboPrijetnja_.setText("");
		comboVrstaPrijet_.setItems(new String [0]);
		comboPrijetnja_.setItems(new String [0]);
		comboVrstaPrijet_.setItems(dB.getComboItemsFromDB("as_threat_type"));
		comboVrstaPrijet_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = comboVrstaPrijet_.getSelectionIndex() + 1;
				comboPrijetnja_.setEnabled(true);
				comboPrijetnja_.setItems(dB.getComboItemsFromDB("as_threat",
						"WHERE threattype_id LIKE '" + index + "%'", true));
			}

		});

		
	}

	@Override
	public void rowSelected(int row) {

		if (row!=0 && table.getModel().getContentAt(1, row).toString()!="") {
			action=2;
			btnBrisi_.setEnabled(true);
			btnNovo_.setEnabled(true);
			m_Riskid=table.getModel().getContentAt(3, row).toString();
			comboPrijetnja_.setEnabled(true);

			String threatId = table.getModel().getContentAt(1, row).toString();
			if (threatId != "" && threatId.length() > 0) {
				String threat = dB.getDesiredColumnFromDB("view_threat",
						"name", "WHERE threat_id=" + threatId + "");
				String threattypeId = dB.getDesiredColumnFromDB("view_threat",
						"threattype_id", "WHERE threat_id=" + threatId + "");

				comboPrijetnja_.setItems(dB.getComboItemsFromDB("as_threat",
						"WHERE threattype_id=" + threattypeId + "", true));

				comboPrijetnja_.setText(threatId + "-" + threat);
				comboVrstaPrijet_.setText(threattypeId
						+ "-"
						+ dB.getDesiredColumnFromDB("view_threat",
								"threat_type", "WHERE threat_id=" + threatId
										+ ""));

			}
		}
		else
		{
			fillForm();
		}
	}

	private void saveAction(){
		if(comboPrijetnja_.getText()!="" && comboPrijetnja_.getText().length()>0 
				&& comboVrstaPrijet_.getText()!="" && comboVrstaPrijet_.getText().length()>0){
			Hashtable<String, String> data = new Hashtable<String, String>();
			
			String temp = comboPrijetnja_.getText();
			int t = temp.indexOf("-");
			data.put("threat_id",dB.getDesiredColumnFromDB("as_threat", "threat_id","WHERE name='"+ comboPrijetnja_.getText().substring(t+1) + "'"));
			data.put("asset_id", m_Model.getContentAt(1, m_Row)
					.toString());
			
			if(action==2){
				
				
				try {
					System.out.println("Hashtable" + data);
					dB.insertDataInDB("as_risk", data, "update","ThreatIdentification", m_Riskid);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {

				data.put("name", m_Model.getContentAt(2, m_Row).toString());
				// data.put("threat_name",
				// comboPrijetnja_.getText().substring(3));
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
					dB.insertDataInDB("as_risk", data, "insert",
								"ThreatIdentification", "");
					
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			
			
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

	@PreDestroy
	public void dispose() throws Exception {
		System.out.println("Closing application");
	}


	@Focus
	public void setFocus() {

		mParent.setFocus();
	}








}
