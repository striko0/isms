package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.ListControlRiskASKTableModel;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;

import de.kupzog.ktable.KTableSortedModel;

public class SuggestMeasures implements ViewSelected {
	private Composite mParent;

	private int action = 1;
	private KTableSortedModel m_RiskModel;
	private KTableSortedModel m_Model;
	private String m_ControlId;
	private String m_RiskId;
	private String m_AssetId;
	private String m_VulnerabilityId;
	private String m_ThreatId;
	private String m_ControlRiskId;
	private int m_Row;
	private String assetName;
	private String riskName;
	private NewASKTable1 table;
	private DataFromDatabase dB;

	private Combo comboKontrola_ ;
	private Text textPrimjena_;

	private Button btnNovo_;
	private Button btnBrisi_;

	@Inject
	private MApplication app;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		scrollBox.setMinHeight(300);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.getShell().setSize(760, 360);

		m_RiskModel = DataFromServer.listRiskASKTableModel;
		m_Model = DataFromServer.listControlRiskASKTableModel;
		m_Row = NewASKTable1.clickedRiskRow;
		dB = new DataFromDatabase();

		m_RiskId = m_RiskModel.getContentAt(1, m_Row).toString();

		riskName = dB.getDesiredColumnFromDB("view_risk", "name",
				"WHERE risk_id='" + m_RiskId + "'");

		m_AssetId=dB.getDesiredColumnFromDB("view_risk", "asset_id",
				"WHERE risk_id='" + m_RiskId + "'");
		m_VulnerabilityId=dB.getDesiredColumnFromDB("view_risk", "vulnerability_id",
				"WHERE risk_id='" + m_RiskId + "'");
		m_ThreatId=dB.getDesiredColumnFromDB("view_risk", "threat_id",
				"WHERE risk_id='" + m_RiskId + "'");

		mParent.getShell().setText("Analiza mjera(kontrola) za rizik: "+riskName.toUpperCase()+"");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblKontrola_ = new Label(composite, SWT.NONE);
		lblKontrola_.setText("Kontrola:");

		comboKontrola_ = new Combo(composite, SWT.READ_ONLY);
		comboKontrola_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblPrijedlog_ = new Label(composite, SWT.NONE);
		lblPrijedlog_.setText("Prijedlog:");
		new Label(composite, SWT.NONE);

		textPrimjena_  = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_Primjena_ = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 2);
		gd_Primjena_.heightHint = 50;
		textPrimjena_.setLayoutData(gd_Primjena_);

		Composite compositeASKTtable = new Composite(mParent, SWT.NONE);
		compositeASKTtable.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeASKTtable = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_compositeASKTtable.minimumHeight = 150;
		gd_compositeASKTtable.heightHint = 107;
		compositeASKTtable.setLayoutData(gd_compositeASKTtable);

		table = new NewASKTable1(this, compositeASKTtable, new ListControlRiskASKTableModel(m_RiskId), 717, 200);
//		new ASKTable(compositeASKTtable, new ControlsAnalysisASKTableModel(), 717,compositeASKTtable.getBounds().height );

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 530;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(5, false));

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

		Button btnEvidKontrola_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnEvidKontrola_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnEvidKontrola_.widthHint = 100;
		btnEvidKontrola_.setLayoutData(gd_btnEvidKontrola_);
		btnEvidKontrola_.addSelectionListener(new SelectionAdapter() {
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
		btnEvidKontrola_.setText("Evid. Kontrola");

		btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_ControlRiskId=null;
				fillForm();
			}
		});
		btnNovo_.setText("Novo");

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
						dB.deleteDataFromDB("as_control_risk", "ascontrolrisk_id", m_ControlRiskId);
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

		fillForm();
		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);
	}

	public void refreshTable(){
		((ListRiskASKTableModel)m_RiskModel).readAllFromDB();
		((ListControlRiskASKTableModel)table.getModel()).readAllFromDB();
//		((ListRiskASKTableModel) m_ModelRisk).readAllFromDB();
		table.redraw();
	}

	private void fillForm() {
		// TODO Auto-generated method stub

		/**
		 * Poèetno postavljanje controla
		 *
		 */
		action=1;
		initialSettings();
		table.setSelection(null, false);



	}

	private void initialSettings()
	{

		btnBrisi_.setEnabled(false);
		btnNovo_.setEnabled(false);
		comboKontrola_.setItems(dB.getControlItemsFromDB("as_control", ""));
		textPrimjena_.setText("");
		comboKontrola_.setText("");

	}


	@Override
	public void rowSelected(int row) {
		// TODO Auto-generated method stub
		if (row!=0 && !table.getModel().getContentAt(1, row).toString().equals("")) {
			action=2;
			btnBrisi_.setEnabled(true);
			btnNovo_.setEnabled(true);

			m_ControlId=table.getModel().getContentAt(6, row).toString();

			m_ControlRiskId=table.getModel().getContentAt(4, row).toString();

			String control = table.getModel().getContentAt(2, row).toString();

			String description = table.getModel().getContentAt(5, row).toString();
			
//			if (description == null)
//				textPrimjena_.setText("");
//			else
				textPrimjena_.setText(description);
			comboKontrola_.setText(m_ControlId+"-"+control);

		}
		else
		{
			fillForm();
		}

	}

	public void saveAction(){
		if((comboKontrola_.getText()!="" && comboKontrola_.getText().length()>0 )){
			Hashtable<String, String> data = new Hashtable<String, String>();


			String temp = comboKontrola_.getText();
			int t = temp.indexOf("-");
			data.put("control_id",comboKontrola_.getText().substring(0,t));
			data.put("risk_id",m_RiskId);		
//			if(textPrimjena_.getText().equals("")){
//				data.put("application","");
//			}
//			else{
				data.put("application", textPrimjena_.getText());
//			}
			
			if (action == 2) {
				try {
					System.out.println("Hashtable" + data);
					dB.insertDataInDB("as_control_risk", data, "update","SuggestMeasures", m_ControlRiskId);
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			} else {
				
				data.put("vulnerability_id",m_VulnerabilityId);
				data.put("threat_id",m_ThreatId);

				System.out.println("Hashtable" + data);
				try {
					dB.insertDataInDB("as_control_risk", data, "insert","SuggestMeasures", "");


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
