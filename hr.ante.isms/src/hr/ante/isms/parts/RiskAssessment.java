package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import de.kupzog.ktable.KTableSortedModel;

public class RiskAssessment {
	private Composite mParent;

	private int action = 1;
	private KTableSortedModel m_Model;
	private KTableSortedModel m_ModelAsset;
	private NewASKTable1 m_Table;
	private String m_RiskId;
	private int m_Row;
	private int m_AssetRow;
	private String assetName;
	private String riskName;
	private DataFromDatabase dB;

	private Combo comboPrijetnja_;
	private Combo comboRanjivost_;
	private Combo comboVjerojatnost_;
	private Combo comboVjerojatnostOtkrivanja_;
	private Combo comboUtjecajNaPovjerljivost_;
	private Combo comboUtjecajNaCjelovitost_;
	private Combo comboUtjecajNaRaspolozivost_ ;
	private Combo comboUcinak_;
	private Text textImovina_;
	private Text textNazivRizika_;
	private Text textOpisVjerojatnosti_;
	private Text textFaktorIzlozenosti_;
	private Text textPotencijalniGubitak_;
	private Text textOpisUcinka_;
	private Text textOpisFaktoraIzlozenosti_;
	private Text textOpisVjerojatnostiOtkrivanja_;

	private String threatId;
	private String vulnerabilityId;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.getShell().setSize(760, 600);

		m_Table = NewASKTable1.m_TableRisk;
		m_Model = DataFromServer.listRiskASKTableModel;
		m_ModelAsset = DataFromServer.listAssetASKTableModel;
		m_AssetRow = NewASKTable1.clickedAssetRow;
		m_Row = NewASKTable1.clickedRiskRow;
		dB = new DataFromDatabase();

		riskName = dB.getDesiredColumnFromDB("view_risk", "name",
				"WHERE risk_id='" + m_Model.getContentAt(1, m_Row) + "'");
		mParent.getShell().setText(
				"Evidencija Rizika: " + riskName.toUpperCase() + "");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblImovina_ = new Label(composite, SWT.NONE);
		lblImovina_.setText("Imovina:");

		textImovina_ = new Text(composite, SWT.BORDER);
		textImovina_.setEnabled(false);
		GridData gd_textImovina_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_textImovina_.widthHint = 200;
		textImovina_.setLayoutData(gd_textImovina_);

		Label lblNazivRizika_ = new Label(composite, SWT.NONE);
		lblNazivRizika_.setText("Naziv rizika: ");

		textNazivRizika_ = new Text(composite, SWT.BORDER);
		GridData gd_textNazivRizika_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_textNazivRizika_.widthHint = 300;
		textNazivRizika_.setLayoutData(gd_textNazivRizika_);

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");

		comboPrijetnja_ = new Combo(composite, SWT.NONE);
		GridData gd_comboPrijetnja_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboPrijetnja_.widthHint = 300;
		comboPrijetnja_.setLayoutData(gd_comboPrijetnja_);

		Label lblRanjivost_ = new Label(composite, SWT.NONE);
		lblRanjivost_.setText("Ranjivost");

		comboRanjivost_ = new Combo(composite, SWT.NONE);
		GridData gd_comboRanjivost_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboRanjivost_.widthHint = 300;
		comboRanjivost_.setLayoutData(gd_comboRanjivost_);

		Group grpVjerojatnostOstvarenjaPrijetnje = new Group(mParent, SWT.NONE);
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL,
				SWT.FILL, true, true, 1, 1);
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 111;
		grpVjerojatnostOstvarenjaPrijetnje
				.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje
				.setText("Vjerojatnost Ostvarenja Prijetnje");
		grpVjerojatnostOstvarenjaPrijetnje.setLayout(new GridLayout(4, false));

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost: ");

		comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 217;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblVjerojatnostOtkrivanja_ = new Label(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblVjerojatnostOtkrivanja_.setText("Vjerojatnost Otkrivanja: ");

		comboVjerojatnostOtkrivanja_ = new Combo(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		comboVjerojatnostOtkrivanja_.setLayoutData(new GridData(SWT.FILL,
				SWT.CENTER, true, false, 1, 1));

		Label lblOpisVjerojatnosti_ = new Label(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojatnosti_.setText("Opis vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		Label lblOpisVjerojatnostiOtkrivanja_ = new Label(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojatnostiOtkrivanja_
				.setText("Opis vjerojatnosti otkrivanja:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		textOpisVjerojatnosti_ = new Text(grpVjerojatnostOstvarenjaPrijetnje,
				SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpisVjerojatnosti_ = new GridData(SWT.FILL, SWT.FILL,
				true, true, 2, 1);
		gd_textOpisVjerojatnosti_.minimumHeight = 50;
		gd_textOpisVjerojatnosti_.heightHint = 42;
		textOpisVjerojatnosti_.setLayoutData(gd_textOpisVjerojatnosti_);

		textOpisVjerojatnostiOtkrivanja_ = new Text(
				grpVjerojatnostOstvarenjaPrijetnje, SWT.BORDER | SWT.WRAP
						| SWT.V_SCROLL);
		GridData gd_textOpisVjerojatnostiOtkrivanja_ = new GridData(SWT.FILL,
				SWT.FILL, true, true, 2, 1);
		gd_textOpisVjerojatnostiOtkrivanja_.minimumHeight = 50;
		textOpisVjerojatnostiOtkrivanja_
				.setLayoutData(gd_textOpisVjerojatnostiOtkrivanja_);
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		Group grpAnalizaUinkaPrijetnje = new Group(mParent, SWT.NONE);
		grpAnalizaUinkaPrijetnje.setLayout(new GridLayout(4, false));
		GridData gd_grpAnalizaUinkaPrijetnje = new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1);
		gd_grpAnalizaUinkaPrijetnje.heightHint = 215;
		grpAnalizaUinkaPrijetnje.setLayoutData(gd_grpAnalizaUinkaPrijetnje);
		grpAnalizaUinkaPrijetnje.setText("Analiza U\u010Dinka Prijetnje");

		Label lblUcinak_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUcinak_.setText("U\u010Dinak:");

		comboUcinak_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUcinak_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboUcinak_.widthHint = 75;
		comboUcinak_.setLayoutData(gd_comboUcinak_);

		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		Label lblUtjecajNaPovjerljivost_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblUtjecajNaPovjerljivost_.setText("Utjecaj na povjerljivost:");

		comboUtjecajNaPovjerljivost_ = new Combo(
				grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUtjecajNaPovjerljivost_ = new GridData(SWT.LEFT,
				SWT.CENTER, true, false, 1, 1);
		gd_comboUtjecajNaPovjerljivost_.widthHint = 120;
		comboUtjecajNaPovjerljivost_
				.setLayoutData(gd_comboUtjecajNaPovjerljivost_);

		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		Label lblUtjecajNaCjelovitost_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblUtjecajNaCjelovitost_.setText("Utjecaj na cjelovitost:");

		comboUtjecajNaCjelovitost_ = new Combo(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		GridData gd_comboUtjecajNaCjelovitost_ = new GridData(SWT.LEFT,
				SWT.CENTER, true, false, 1, 1);
		gd_comboUtjecajNaCjelovitost_.widthHint = 120;
		comboUtjecajNaCjelovitost_.setLayoutData(gd_comboUtjecajNaCjelovitost_);

		Label lblFaktorIzlozenosti_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblFaktorIzlozenosti_.setText("Faktor izlo\u017Eenosti:");

		textFaktorIzlozenosti_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER);
		GridData gd_textFaktorIzlozenosti_ = new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1);
		gd_textFaktorIzlozenosti_.widthHint = 75;
		textFaktorIzlozenosti_.setLayoutData(gd_textFaktorIzlozenosti_);

		Label lblUtjecajNaRaspolozivost_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblUtjecajNaRaspolozivost_.setLayoutData(new GridData(SWT.RIGHT,
				SWT.CENTER, false, false, 1, 1));
		lblUtjecajNaRaspolozivost_.setText("Utjecaj na raspolo\u017Eivost:");

		comboUtjecajNaRaspolozivost_ = new Combo(
				grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUtjecajNaRaspolozivost_ = new GridData(SWT.LEFT,
				SWT.CENTER, true, false, 1, 1);
		gd_comboUtjecajNaRaspolozivost_.widthHint = 120;
		comboUtjecajNaRaspolozivost_
				.setLayoutData(gd_comboUtjecajNaRaspolozivost_);

		Label lblPotencijalniGubitak_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblPotencijalniGubitak_.setText("Potencijalni gubitak:");

		textPotencijalniGubitak_ = new Text(grpAnalizaUinkaPrijetnje,
				SWT.BORDER);
		GridData gd_textPotencijalniGubitak_ = new GridData(SWT.LEFT,
				SWT.CENTER, true, false, 1, 1);
		gd_textPotencijalniGubitak_.widthHint = 75;
		textPotencijalniGubitak_.setLayoutData(gd_textPotencijalniGubitak_);

		Label lblOpisUcinka_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblOpisUcinka_.setText("Opis u\u010Dinka:");
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		Label lblOpisFaktoraIzlozenosti_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblOpisFaktoraIzlozenosti_
				.setText("Opis faktora izlo\u017Eenosti i potenc. gubitka");
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		textOpisUcinka_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpisUcinka_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 2);
		gd_textOpisUcinka_.minimumHeight = 50;
		textOpisUcinka_.setLayoutData(gd_textOpisUcinka_);

		textOpisFaktoraIzlozenosti_ = new Text(grpAnalizaUinkaPrijetnje,
				SWT.BORDER | SWT.V_SCROLL);
		GridData gd_textOpisFaktoraIzlozenosti_ = new GridData(SWT.FILL,
				SWT.FILL, true, true, 2, 2);
		gd_textOpisFaktoraIzlozenosti_.minimumHeight = 50;
		textOpisFaktoraIzlozenosti_
				.setLayoutData(gd_textOpisFaktoraIzlozenosti_);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 230;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(2, false));

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
				action=2;
			}
		});
		btnSpremi_.setText("Spremi");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				mParent.getShell().close();
			}
		});
		btnIzlaz_.setText("Izlaz");


		fillForm();
		scrollBox.setContent(mParent);
	}

	public void refreshTable(){
		((ListRiskASKTableModel)m_Model).readAllFromDB();
		((ListAssetASKTableModel) m_ModelAsset).readAllFromDB();
		m_Table.redraw();

	}

	private void fillForm() {
		// TODO Auto-generated method stub

		/**
		 * Poèetno postavljanje controla
		 *
		 */
		action=1;
		comboVjerojatnost_.setItems(dB.getComboItemsFromDB("as_probability"));
		comboVjerojatnostOtkrivanja_.setItems(dB.getComboItemsFromDB("as_detection_probability"));
		comboUcinak_.setItems(dB.getComboItemsFromDB("as_threat_impact"));
		String[] impactItems = dB.getComboItemsFromDB("as_impact");
		comboUtjecajNaPovjerljivost_.setItems(impactItems);
		comboUtjecajNaCjelovitost_.setItems(impactItems);
		comboUtjecajNaRaspolozivost_.setItems(impactItems);

		comboVjerojatnost_.setText("");
		comboVjerojatnostOtkrivanja_.setText("");
		comboUcinak_.setText("");
		comboUtjecajNaPovjerljivost_.setText("");
		comboUtjecajNaCjelovitost_.setText("");
		comboUtjecajNaRaspolozivost_.setText("");

		if(m_Row!=0/* && !m_Table.m_Selection.isEmpty()*/){
			action=2;
			m_RiskId=m_Model.getContentAt(1, m_Row).toString();

			textImovina_.setText(m_Model.getContentAt(3, m_Row).toString());
			textNazivRizika_.setText(m_Model.getContentAt(2, m_Row).toString());

			String threat = m_Model.getContentAt(5, m_Row).toString();
			threatId = dB.getDesiredColumnFromDB("view_threat",
					"threat_id", "WHERE name='" + threat + "'");

			String vulnerability = m_Model.getContentAt(6, m_Row).toString();
			vulnerabilityId = dB.getDesiredColumnFromDB("view_vulnerability",
					"vulnerability_id", "WHERE name='" + vulnerability + "'");

			String probabilityId = m_Model.getContentAt(8, m_Row).toString();
			String probability = dB.getDesiredColumnFromDB("as_probability",
					"name", "WHERE probability_id='" + probabilityId + "'");

			String detection_probabilityId  = m_Model.getContentAt(9, m_Row).toString();
			if(detection_probabilityId!="")
			{
				String detection_probability = dB.getDesiredColumnFromDB("as_detection_probability",
						"name", "WHERE asdetectionprobability_id='" + detection_probabilityId + "'");
				comboVjerojatnostOtkrivanja_.setText(detection_probabilityId+"-"+detection_probability);

			}

			textOpisVjerojatnosti_.setText(m_Model.getContentAt(10, m_Row).toString());
			textOpisVjerojatnostiOtkrivanja_.setText(m_Model.getContentAt(11, m_Row).toString());

			String impactId = m_Model.getContentAt(12, m_Row).toString();
			String impact = dB.getDesiredColumnFromDB("as_impact",
					"name", "WHERE asimpact_id='" + impactId + "'");

			textOpisUcinka_.setText(m_Model.getContentAt(13, m_Row).toString());

			textFaktorIzlozenosti_.setText(m_Model.getContentAt(14, m_Row).toString());

			textPotencijalniGubitak_.setText(m_Model.getContentAt(15, m_Row).toString());

			textOpisFaktoraIzlozenosti_.setText(m_Model.getContentAt(16, m_Row).toString());

			comboPrijetnja_.setText(threatId+"-"+threat);
			comboRanjivost_.setText(vulnerabilityId+"-"+vulnerability);
			comboVjerojatnost_.setText(probabilityId+"-"+probability);

			comboUcinak_.setText(impactId+"-"+impact);

//			comboPrijetnja_.setText(string)





		}



	}


	public void saveAction(){
		if((comboPrijetnja_.getText()!="" && comboPrijetnja_.getText().length()>0 )
				&& (comboRanjivost_.getText()!="" && comboRanjivost_.getText().length()>0)
				&& (comboVjerojatnost_.getText()!="" && comboVjerojatnost_.getText().length()>0)
				&& (comboUcinak_.getText()!="" && comboUcinak_.getText().length()>0)
				){
			Hashtable<String, String> data = new Hashtable<String, String>();

//			String temp = comboPrijetnja_.getText();
//			int t = temp.indexOf("-");
			assetName = m_Model.getContentAt(3, m_Row).toString();
			data.put("threat_id",threatId);
			data.put("vulnerability_id",vulnerabilityId);
			data.put("asset_id",dB.getDesiredColumnFromDB("view_asset", "asset_id", "WHERE name='"+assetName+"'"));
			data.put("name", textNazivRizika_.getText());
			data.put("assetsubcateg_id", dB.getDesiredColumnFromDB("view_asset", "category", "WHERE name='"+assetName+"'"));
			data.put("owner", m_Model.getContentAt(4, m_Row).toString());
			data.put("asset_value", dB.getDesiredColumnFromDB("view_asset", "asset_value", "WHERE name='"+assetName+"'"));
			data.put("confidentiality_level",dB.getDesiredColumnFromDB("view_asset", "confidentiality_level", "WHERE name='"+assetName+"'"));
			data.put("integrity_level", dB.getDesiredColumnFromDB("view_asset", "integrity_level", "WHERE name='"+assetName+"'"));
			data.put("accessibility_level",	dB.getDesiredColumnFromDB("view_asset", "accessibility_level", "WHERE name='"+assetName+"'"));
			data.put("businessimpact_level",dB.getDesiredColumnFromDB("view_asset", "businessimpact_level", "WHERE name='"+assetName+"'"));
			data.put("risk_probability", comboVjerojatnost_.getText());
			if(textOpisVjerojatnosti_.getText().equals(""))
				data.put("description_risk_probability", " ");
			else{
				data.put("description_risk_probability", textOpisVjerojatnosti_.getText());
			}
			if(comboVjerojatnostOtkrivanja_.getText().equals(""))
				data.put("detection_probability", " ");
			else{
				data.put("detection_probability", comboVjerojatnostOtkrivanja_.getText());
			}
			if( textOpisVjerojatnostiOtkrivanja_.equals(""))
				data.put("description_detection_probability", " ");
			else{
				data.put("description_detection_probability", textOpisVjerojatnostiOtkrivanja_.getText());
			}

			data.put("impact", comboUcinak_.getText());


			if(textOpisUcinka_.getText().equals(""))
				data.put("description_impact", " ");
			else{
				data.put("description_impact", textOpisUcinka_.getText());
			}
			if(textFaktorIzlozenosti_.getText().equals(""))
				data.put("exposure_factor", "0.00");
			else{
				data.put("exposure_factor", textFaktorIzlozenosti_.getText());
			}
			if(textOpisFaktoraIzlozenosti_.getText().equals(""))
				data.put("description_exposure_factor", " ");
			else{
				data.put("description_exposure_factor", textOpisFaktoraIzlozenosti_.getText());
			}
			if(textPotencijalniGubitak_.getText().equals(""))
				data.put("financial_impact", "0.00");
			else{
				data.put("financial_impact", textPotencijalniGubitak_.getText());
			}


			System.out.println("Hashtable" + data);
			try {

				if (action == 2) {
					dB.insertDataInDB("as_risk", data, "update","RiskAssessment", m_RiskId);

				} else
					dB.insertDataInDB("as_risk", data, "insert","RiskAssessment", "");


			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);
			refreshTable();
		}

		else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);

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
