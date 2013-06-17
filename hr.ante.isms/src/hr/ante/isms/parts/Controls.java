package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;

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
import org.eclipse.swt.events.SelectionListener;
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

public class Controls{

	private Composite mParent;

	@Inject
	private MApplication app;

	private int action = 1;
	private DataFromDatabase dB;

	private Text textNaziv_;
	private Text textOznaka_;
	private Text textOpis_;
	private Text textSmjerPrim_;
	private Text textStaPrim_;
	private Text textPrep_;

	private Button btnRadAkt_;
	private Button btnRadPlan_;
	private Button btnRadNeAkt_;
	private Button btnRadUprav_;
	private Button btnRadLog_;
	private Button btnRadFiz_;
	private Button btnRadPrev_;
	private Button btnRadDet_ ;
	private Button btnRadReak_;
	private Button btnSpremiIPripremi_;

	private Combo comboOdgOsoba_;
	private Combo comboOcjDjelot_;
	private Combo comboSukl_;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);

		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(550);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);

		mParent.getShell().setSize(675, 600);
		mParent.getShell().setText("Evidencija Kontrola");
		mParent.setLayout(new GridLayout(2, false));

		dB = new DataFromDatabase();

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(4, false));
		GridData gd_composite1 = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
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
		textOpis_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 3));

		Group group = new Group(composite1, SWT.NONE);
		group.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		group.setText("Status");
		group.setLayout(new GridLayout(3, false));

		btnRadAkt_ = new Button(group, SWT.RADIO);
		btnRadAkt_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadAkt_.setText("Aktivno");

		btnRadPlan_= new Button(group, SWT.RADIO);
		btnRadPlan_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadPlan_.setText("Planirano");

		btnRadNeAkt_ = new Button(group, SWT.RADIO);
		btnRadNeAkt_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadNeAkt_.setText("Neaktivno");

		Group group_1 = new Group(composite1, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		group_1.setText("Tip");
		group_1.setLayout(new GridLayout(3, false));

		btnRadUprav_ = new Button(group_1, SWT.RADIO);
		btnRadUprav_.setText("Upravlja\u010Dka");

		btnRadLog_ = new Button(group_1, SWT.RADIO);
		btnRadLog_.setText("Logi\u010Dka");

		btnRadFiz_ = new Button(group_1, SWT.RADIO);
		btnRadFiz_.setText("Fizi\u010Dka");

		Group group_2 = new Group(composite1, SWT.NONE);
		group_2.setText("Vrsta");
		group_2.setLayout(new GridLayout(3, false));

		btnRadPrev_ = new Button(group_2, SWT.RADIO);
		btnRadPrev_.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		btnRadPrev_.setText("Preventivna");

		btnRadDet_ = new Button(group_2, SWT.RADIO);
		btnRadDet_.setText("Detekcijska");

		btnRadReak_ = new Button(group_2, SWT.RADIO);
		btnRadReak_.setText("Reakcijska");

		Composite composite2 = new Composite(mParent, SWT.NONE);
		composite2.setLayout(new GridLayout(6, false));
		composite2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				2, 1));

		Label lblOcjDjelot_ = new Label(composite2, SWT.NONE);
		lblOcjDjelot_.setText("Ocjena Djelotvornosti:");
		lblOcjDjelot_.setBounds(0, 0, 32, 15);

		comboOcjDjelot_ = new Combo(composite2, SWT.READ_ONLY);
		comboOcjDjelot_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));


		Label lblOdgOsoba_ = new Label(composite2, SWT.NONE);
		lblOdgOsoba_.setText("Odgovorna Osoba:");

		comboOdgOsoba_ = new Combo(composite2, SWT.NONE);
		comboOdgOsoba_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));


		Label lblSukl_ = new Label(composite2, SWT.NONE);
		lblSukl_.setText("Sukladnost:");

		comboSukl_ = new Combo(composite2, SWT.NONE);
		comboSukl_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));


		Composite composite3 = new Composite(mParent, SWT.NONE);
		composite3.setLayout(new GridLayout(1, false));
		composite3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		Label lblSmjerPrim_ = new Label(composite3, SWT.NONE);
		lblSmjerPrim_.setText("Smjernice Primjene:");

		textSmjerPrim_ = new Text(composite3, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_textSmjerPrim_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_textSmjerPrim_.heightHint = 43;
		textSmjerPrim_.setLayoutData(gd_textSmjerPrim_);

		Label lblStaPrim_ = new Label(composite3, SWT.NONE);
		lblStaPrim_.setText("Stanje Primjene:");

		textStaPrim_ = new Text(composite3, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_textStaPrim_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_textStaPrim_.heightHint = 39;
		textStaPrim_.setLayoutData(gd_textStaPrim_);

		Label lblPrep_ = new Label(composite3, SWT.NONE);
		lblPrep_.setText("Preporuka:");

		textPrep_ = new Text(composite3, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_textPrep_ = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textPrep_.heightHint = 52;
		textPrep_.setLayoutData(gd_textPrep_);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				true, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 461;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(4, false));

		btnSpremiIPripremi_ = new Button(compositeButtons_, SWT.NONE);
		btnSpremiIPripremi_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSpremiIPripremi_.setText("Spremi i pripremi novo");

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.setText("Spremi");

		Button btnNast_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNast = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnNast.widthHint = 100;
		btnNast_.setLayoutData(gd_btnNast);
		btnNast_.addSelectionListener(new SelectionListener() {

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

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btnNast_.setText("Nastavak");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setText("Izlaz");
		new Label(mParent, SWT.NONE);
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
		 * Poèetno postavljanje controla
		 *
		 */
		action=1;
		initialSettings();


	}

	private void initialSettings(){
		comboOcjDjelot_.setItems(dB.getComboItemsFromDB("as_control_effectiveness"));
		comboOdgOsoba_.setItems(dB.getComboItemsFromDB("as_owner"));
		comboSukl_.setItems(dB.getComboItemsFromDB("as_control_compliance"));

		textStaPrim_.setText("");
		textSmjerPrim_.setText("");
		textPrep_.setText("");
		textOznaka_.setText("");
		textOpis_.setText("");
		textNaziv_.setText("");

		btnRadAkt_.setSelection(false);
		btnRadPlan_.setSelection(false);
		btnRadNeAkt_.setSelection(false);

		btnRadUprav_.setSelection(false);
		btnRadLog_.setSelection(false);
		btnRadFiz_.setSelection(false);

		btnRadPrev_.setSelection(false);
		btnRadDet_.setSelection(false);
		btnRadReak_.setSelection(false);


	}

	public void saveAction(){
//		if((textNaziv_.getText()!="" && textNaziv_.getText().length()>0 )
//				&& (textOznaka_.getText()!="" && textOznaka_.getText().length()>0)
//
//
//				){
//			Hashtable<String, String> data = new Hashtable<String, String>();
//
//			String temp = comboVrsta_.getText();
//			int t = temp.indexOf("-");
//			data.put("vulnerabilitytype_id",comboVrsta_.getText().substring(0,t));
//			data.put("name",textNaziv_.getText());
//			if(!textOpis_.getText().equals("")){
//				data.put("description",textOpis_.getText());
//			}
//			int radio = getRadioButtonSelection();
//			switch (radio) {
//			case 1:
//				data.put("active", 1+"");
//				break;
//			case 2:
//				data.put("active", 2+"");
//				break;
//			case 3:
//				data.put("active", 3+"");
//				break;
//
//			default:
//				break;
//			}
//			if(!comboPosljedica_.getText().equals("")){
//				temp = comboPosljedica_.getText();
//				t = temp.indexOf("-");
//				data.put("vulnerability_level", comboPosljedica_.getText().substring(0,t));
//			}
//
//			if(textPrimjerPrijetnje_.getText().equals("")){
//				data.put("example",textPrimjerPrijetnje_.getText());
//			}
//			System.out.println("Hashtable" + data);
//			try {
//
//				if (action == 2) {
//					dB.insertDataInDB("as_vulnerability", data, "update","Vulnerability", m_VulnerabilityId);
//
//				} else
//					dB.insertDataInDB("as_vulnerability", data, "insert","Vulnerability", "");
//
//
//			} catch (Exception e1) {
//				e1.printStackTrace();
//
//			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"),"Spremanje uspješno", "Podaci su spremljeni", NotifierTheme.GREEN_THEME);

//			refreshTable();
//			fillForm();
//		}

//		else
//			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
//					"src/icons/error.ico"),"Nemože se spremiti", "Niste unijeli sve potrebno podatke", NotifierTheme.RED_THEME);

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
