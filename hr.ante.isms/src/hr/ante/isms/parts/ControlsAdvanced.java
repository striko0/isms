package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListControlASKTableModel;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

import de.kupzog.ktable.KTableSortedModel;

public class ControlsAdvanced {

	private Composite mParent;
	
	private int action = 1;
	private KTableSortedModel m_Model;
	private int m_Row;
	private Text textCijenaProvedbe_;
	private Text textOpisProvedbe_;
	private Text textSmjernicePrimjene_;
	private Text textStanjePrimjene_;
	private Text textPreporuka_;
	private int m_controlId;
	private String controlName;
	private DataFromDatabase dB;
	private DateTime dateTimeRokProv_;
	private Group grpProvedba_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		//scrollBox.setBounds(0, 0, 837, 298);
		//scrollBox.setBounds(0, 0, 760, 450);
		//scrollBox.setBounds(0, 0, 448, 375);
		scrollBox.setMinHeight(600);
		scrollBox.setMinWidth(600);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

														// Using 0 here ensures the horizontal scroll bar will never appear. If
														// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);


		m_Model = DataFromServer.listControlASKTableModel;
		m_Row = NewASKTable1.clickedControlRow;
		mParent.getShell().setSize(630, 640);
		m_controlId = Controls.controlId;	
		dB = new DataFromDatabase();
		
		controlName = dB.getDesiredColumnFromDB("as_control", "name", "WHERE control_id='"+m_controlId+"'");
		mParent.getShell().setText("Ažuriranje Kontrole: "+controlName.toUpperCase()+"");
		mParent.setLayout(new GridLayout(2, false));

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(1, false));
		composite1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		grpProvedba_ = new Group(composite1, SWT.NONE);
		GridData gd_grpProvedba_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 2);
		gd_grpProvedba_.widthHint = 396;
		grpProvedba_.setLayoutData(gd_grpProvedba_);
		grpProvedba_.setText("Provedba");
		grpProvedba_.setLayout(new GridLayout(4, false));

				Label label = new Label(grpProvedba_, SWT.NONE);
				label.setText("Datum Provedbe:");

				dateTimeRokProv_ = new DateTime(grpProvedba_, SWT.BORDER
						| SWT.DROP_DOWN);
				new Label(grpProvedba_, SWT.NONE);
						new Label(grpProvedba_, SWT.NONE);

						Label lblHard_ = new Label(grpProvedba_, SWT.NONE);
						lblHard_.setText("Cijena provedbe:");

						textCijenaProvedbe_ = new Text(grpProvedba_, SWT.BORDER);
						GridData gd_textCijenaProvedbe_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
						gd_textCijenaProvedbe_.widthHint = 111;
						textCijenaProvedbe_.setLayoutData(gd_textCijenaProvedbe_);
				new Label(grpProvedba_, SWT.NONE);
				new Label(grpProvedba_, SWT.NONE);

				Label lblOpisProv_ = new Label(grpProvedba_, SWT.NONE);
				lblOpisProv_.setText("Opis Provedbe:");
		new Label(grpProvedba_, SWT.NONE);
		new Label(grpProvedba_, SWT.NONE);
		new Label(grpProvedba_, SWT.NONE);

				textOpisProvedbe_ = new Text(grpProvedba_, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				GridData gd_text_3 = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
				gd_text_3.heightHint = 37;
				gd_text_3.minimumHeight = 50;
				textOpisProvedbe_.setLayoutData(gd_text_3);
		
		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Label labelSmjernicePrimjene_ = new Label(composite, SWT.NONE);
		labelSmjernicePrimjene_.setText("Smjernice Primjene:");
		
		textSmjernicePrimjene_ = new Text(composite, SWT.BORDER | SWT.V_SCROLL);
		textSmjernicePrimjene_.setText("");
		GridData gd_textSmjernicePrimjene_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textSmjernicePrimjene_.heightHint = 43;
		textSmjernicePrimjene_.setLayoutData(gd_textSmjernicePrimjene_);
		
		Label labelStanjePrimjene_ = new Label(composite, SWT.NONE);
		labelStanjePrimjene_.setText("Stanje Primjene:");
		
		textStanjePrimjene_ = new Text(composite, SWT.BORDER | SWT.V_SCROLL);
		textStanjePrimjene_.setText("");
		GridData gd_textStanjePrimjene_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textStanjePrimjene_.heightHint = 39;
		textStanjePrimjene_.setLayoutData(gd_textStanjePrimjene_);
		
		Label labelPreporuka_ = new Label(composite, SWT.NONE);
		labelPreporuka_.setText("Preporuka:");
		
		textPreporuka_ = new Text(composite, SWT.BORDER | SWT.V_SCROLL);
		textPreporuka_.setText("");
		GridData gd_textPreporuka_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textPreporuka_.heightHint = 52;
		textPreporuka_.setLayoutData(gd_textPreporuka_);
		new Label(mParent, SWT.NONE);
		new Label(mParent, SWT.NONE);
		new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 220;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(2, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAction();
				mParent.getShell().close();
			}
		});
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
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
		new Label(mParent, SWT.NONE);
		
		fillForm();
		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);
	
	}
//	
	public void refreshTable(){
		((ListControlASKTableModel)m_Model).readAllFromDB();
	}

	private void fillForm() {
		// TODO Auto-generated method stub

		action=1;
		initialSettings();
		
		if(m_controlId!=0)
		{
			action=2;
					
			String cijena = dB.getDesiredColumnFromDB("view_control", "implementation_cost", "WHERE control_id='"+m_controlId+"'");
			if(cijena==null)
			{
				textCijenaProvedbe_.setText("0.0");
			}
			else{
				textCijenaProvedbe_.setText(cijena);
			}
			
			String opisProvedbe = dB.getDesiredColumnFromDB("view_control", "implementation_description", "WHERE control_id='"+m_controlId+"'");
			if(opisProvedbe==null)
			{
				textOpisProvedbe_.setText("");
			}
			else{
				textOpisProvedbe_.setText(opisProvedbe);
			}
			
			String smjernicePrimjene = dB.getDesiredColumnFromDB("view_control", "guidelines", "WHERE control_id='"+m_controlId+"'");
			if(smjernicePrimjene==null)
			{
				textSmjernicePrimjene_.setText("");
			}
			else{
				textSmjernicePrimjene_.setText(smjernicePrimjene);
			}
			
			String stanjePrimjene = dB.getDesiredColumnFromDB("view_control", "stateofcontrol", "WHERE control_id='"+m_controlId+"'");
			if(stanjePrimjene==null)
			{
				textStanjePrimjene_.setText("");
			}
			else{
				textStanjePrimjene_.setText(stanjePrimjene);
			}
			
			String preporuka = dB.getDesiredColumnFromDB("view_control", "recommendation", "WHERE control_id='"+m_controlId+"'");
			if(stanjePrimjene==null)
			{
				textPreporuka_.setText("");
			}
			else{
				textPreporuka_.setText(stanjePrimjene);
			}
			
			
			
			
		}
		
		else
		{
			action=1;
		}
		
		
		
		


	}

	private void initialSettings(){
		
		action=1;
		
		//dateTimeRokProv_.setDate(year, month, day)
		textCijenaProvedbe_.setText("");
		textOpisProvedbe_.setText("");
		textPreporuka_.setText("");
		textSmjernicePrimjene_.setText("");
		textStanjePrimjene_.setText("");


	}	
	
	public void saveAction(){
//		if((textNaziv_.getText()!="" && textNaziv_.getText().length()>0 )
//				&& (textOznaka_.getText()!="" && textOznaka_.getText().length()>0)
//				){
			Hashtable<String, String> data = new Hashtable<String, String>();
//
//			String temp = comboVrsta_.getText();
//			int t = temp.indexOf("-");
			
			data.put("implementation_date", "");
			data.put("implementation_cost", textCijenaProvedbe_.getText());
			data.put("implementation_description", textOpisProvedbe_.getText());			
			data.put("guidelines", textSmjernicePrimjene_.getText());			
			data.put("stateofcontrol", textStanjePrimjene_.getText());			
			data.put("recommendation", textPreporuka_.getText());			
		
			System.out.println("Hashtable" + data);
			try {

				if (action == 2) {
					dB.insertDataInDB("as_control", data, "update","ControlAdvanced", m_controlId+"");

				} else
					dB.insertDataInDB("as_control", data, "insert","ControlAdvanced", "");


			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);

		
			fillForm();
//			}
//
//		else
//			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
//					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);
		
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
