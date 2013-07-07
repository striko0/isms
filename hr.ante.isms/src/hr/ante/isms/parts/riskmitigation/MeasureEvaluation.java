package hr.ante.isms.parts.riskmitigation;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.DataFromServer;
import hr.ante.isms.parts.table.NewASKTable1;
import hr.ante.isms.parts.table.model.ListAssetASKTableModel;
import hr.ante.isms.parts.table.model.ListRiskASKTableModel;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
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
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

import de.kupzog.ktable.KTableSortedModel;

public class MeasureEvaluation {

//	private int action = 1;
	private KTableSortedModel m_Model;
	private String m_RiskId;
	private int m_Row;
	private String assetName;
	private String riskName;
	private NewASKTable1 table;
	private DataFromDatabase dB;

	private Composite mParent;
	private Combo comboVjerojatnost_;
	private Combo comboUcinak_;
	private Combo comboNacinSmanjivanja_;



	@Inject
	private MApplication app;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		scrollBox.setMinHeight(170);
		scrollBox.setMinWidth(400);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.getShell().setSize(450, 220);

		m_Model = DataFromServer.listRiskASKTableModel;
		m_Row = NewASKTable1.clickedRiskRow;
		dB = new DataFromDatabase();

		riskName = dB.getDesiredColumnFromDB("view_risk", "name",
				"WHERE risk_id='" + m_Model.getContentAt(1, m_Row) + "'");

		mParent.getShell().setText("Mjere za smanjenje rizika: "+riskName.toUpperCase()+"");
		mParent.setLayout(new GridLayout(1, false));

		Group grpOcekivaneVrijednostiNakon_ = new Group(mParent, SWT.NONE);
		grpOcekivaneVrijednostiNakon_.setLayout(new GridLayout(4, false));
		grpOcekivaneVrijednostiNakon_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpOcekivaneVrijednostiNakon_.setText("O\u010Dekivane Vrijednosti Nakon Uvo\u0111enja Predlo\u017Eenih Mjera");

		Label lblVjerojatnost_ = new Label(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost:");

		comboVjerojatnost_ = new Combo(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		comboVjerojatnost_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


		Label lblUcinak_ = new Label(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		lblUcinak_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUcinak_.setText("U\u010Dinak: ");

		comboUcinak_ = new Combo(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		comboUcinak_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(mParent, SWT.NONE);

		Group grpNainSmanjivanjaRizika = new Group(mParent, SWT.NONE);
		grpNainSmanjivanjaRizika.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, false, 1, 1));
		grpNainSmanjivanjaRizika.setText("Na\u010Din Smanjivanja Rizika");
		grpNainSmanjivanjaRizika.setLayout(new GridLayout(2, false));

		Label lblNacinSmanjivanja_ = new Label(grpNainSmanjivanjaRizika,
				SWT.NONE);
		lblNacinSmanjivanja_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblNacinSmanjivanja_.setText("Na\u010Din Smanjivanja: ");

		comboNacinSmanjivanja_ = new Combo(grpNainSmanjivanjaRizika,
				SWT.NONE);
		comboNacinSmanjivanja_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));


		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 210;
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
//				action=2;
			}
		});
		btnSpremi_.setText("Spremi");

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

	private void fillForm() {
	/**
	 * Poèetno postavljanje controla
	 *
	 */
//	action=1;
	comboVjerojatnost_.setItems(dB.getComboItemsFromDB("as_probability"));
	comboUcinak_.setItems(dB.getComboItemsFromDB("as_impact"));
	comboNacinSmanjivanja_.setItems(dB.getComboItemsFromDB("as_risk_mitigation"));


	comboVjerojatnost_.setText("");
	comboUcinak_.setText("");
	comboNacinSmanjivanja_.setText("");

	if(m_Row!=0){
//		action=2;
		m_RiskId=m_Model.getContentAt(1, m_Row).toString();

		String probabilityId = m_Model.getContentAt(8, m_Row).toString();
		String probability = dB.getDesiredColumnFromDB("as_probability",
				"name", "WHERE probability_id='" + probabilityId + "'");


		String impactId = m_Model.getContentAt(12, m_Row).toString();
		String impact = dB.getDesiredColumnFromDB("as_impact",
				"name", "WHERE asimpact_id='" + impactId + "'");

		String mitigationId = m_Model.getContentAt(17, m_Row).toString();

		if(!mitigationId.equals("")){
			String mitigation = dB.getDesiredColumnFromDB("as_risk_mitigation",
					"name", "WHERE asriskmitigation_id='" + mitigationId + "'");
			comboNacinSmanjivanja_.setText(mitigationId+"-"+mitigation);
		}
		else
			comboNacinSmanjivanja_.setText("");
		
		comboVjerojatnost_.setText(probabilityId+"-"+probability);
		comboUcinak_.setText(impactId+"-"+impact);
	}
	}

	public void refreshTable(){
		((ListRiskASKTableModel)m_Model).readAllFromDB();

	}

	public void saveAction(){
		if((comboVjerojatnost_.getText()!="" && comboVjerojatnost_.getText().length()>0 )
				&& (comboUcinak_.getText()!="" && comboUcinak_.getText().length()>0)
				&& (comboNacinSmanjivanja_.getText()!="" && comboNacinSmanjivanja_.getText().length()>0)
				){
			Hashtable<String, String> data = new Hashtable<String, String>();

			data.put("risk_probability", comboVjerojatnost_.getText());
			data.put("impact", comboUcinak_.getText());
			data.put("mitigation_id", comboNacinSmanjivanja_.getText());

			System.out.println("Hashtable" + data);
			try {
					dB.insertDataInDB("as_risk", data, "update","MeasureEvaluation", m_RiskId);


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
