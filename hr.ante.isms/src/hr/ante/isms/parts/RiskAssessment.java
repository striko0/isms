package hr.ante.isms.parts;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ControlsAnalysisASKTableModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;

public class RiskAssessment {
	private Composite mParent;
	@Inject
	MDirtyable dirty;

	@Inject
	private MApplication app;
	private Text textImovina_;
	private Text textNazivRizika_;
	private Text textOpisVjerojatnosti_;
	private Text textFaktorIzlozenosti_;
	private Text textPotencijalniGubitak_;
	private Text textOpisUcinka_;
	private Text textOpisFaktoraIzlozenosti_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		// mParent.setSize(790, 659);
		// parent.setSize(new Point(759, 359));
		mParent.getShell().setSize(760, 600);
		mParent.getShell().setText("Evidencija Rizika:");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblImovina_ = new Label(composite, SWT.NONE);
		lblImovina_.setText("Imovina:");

		textImovina_ = new Text(composite, SWT.BORDER);
		textImovina_.setEnabled(false);
		GridData gd_textImovina_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_textImovina_.widthHint = 200;
		textImovina_.setLayoutData(gd_textImovina_);

		Label lblNazivRizika_ = new Label(composite, SWT.NONE);
		lblNazivRizika_.setText("Naziv rizika: ");

		textNazivRizika_ = new Text(composite, SWT.BORDER);
		GridData gd_textNazivRizika_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_textNazivRizika_.widthHint = 300;
		textNazivRizika_.setLayoutData(gd_textNazivRizika_);

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");

		Combo comboPrijetnja_ = new Combo(composite, SWT.NONE);
		GridData gd_comboPrijetnja_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboPrijetnja_.widthHint = 300;
		comboPrijetnja_.setLayoutData(gd_comboPrijetnja_);

		Label lblRanjivost_ = new Label(composite, SWT.NONE);
		lblRanjivost_.setText("Ranjivost");

		Combo comboRanjivost_ = new Combo(composite, SWT.NONE);
		GridData gd_comboRanjivost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboRanjivost_.widthHint = 300;
		comboRanjivost_.setLayoutData(gd_comboRanjivost_);

		Group grpVjerojatnostOstvarenjaPrijetnje = new Group(mParent, SWT.NONE);
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 154;
		grpVjerojatnostOstvarenjaPrijetnje.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje.setText("Vjerojatnost Ostvarenja Prijetnje");
		grpVjerojatnostOstvarenjaPrijetnje.setLayout(new GridLayout(2, false));

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost: ");

		Combo comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 217;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblOpisVjerojatnosti_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojatnosti_.setText("Opis vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		textOpisVjerojatnosti_ = new Text(grpVjerojatnostOstvarenjaPrijetnje, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_textOpisVjerojatnosti_ = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		gd_textOpisVjerojatnosti_.heightHint = 40;
		textOpisVjerojatnosti_.setLayoutData(gd_textOpisVjerojatnosti_);


		Group grpAnalizaUinkaPrijetnje = new Group(mParent, SWT.NONE);
		grpAnalizaUinkaPrijetnje.setLayout(new GridLayout(4, false));
		grpAnalizaUinkaPrijetnje.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpAnalizaUinkaPrijetnje.setText("Analiza U\u010Dinka Prijetnje");

		Label lblUcinak_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUcinak_.setText("U\u010Dinak:");

		Combo comboUcinak_  = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUcinak_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboUcinak_.widthHint = 75;
		comboUcinak_.setLayoutData(gd_comboUcinak_);
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
				new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

				Label lblUtjecajNaPovjerljivost_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
				lblUtjecajNaPovjerljivost_.setText("Utjecaj na povjerljivost:");

				Combo comboUtjecajNaPovjerljivost_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
				GridData gd_comboUtjecajNaPovjerljivost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
				gd_comboUtjecajNaPovjerljivost_.widthHint = 120;
				comboUtjecajNaPovjerljivost_.setLayoutData(gd_comboUtjecajNaPovjerljivost_);
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		Label lblUtjecajNaCjelovitost_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUtjecajNaCjelovitost_.setText("Utjecaj na cjelovitost:");

		Combo comboUtjecajNaCjelovitost_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUtjecajNaCjelovitost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboUtjecajNaCjelovitost_.widthHint = 120;
		comboUtjecajNaCjelovitost_.setLayoutData(gd_comboUtjecajNaCjelovitost_);

		Label lblFaktorIzlozenosti_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblFaktorIzlozenosti_.setText("Faktor izlo\u017Eenosti:");

		textFaktorIzlozenosti_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER);
		GridData gd_textFaktorIzlozenosti_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
		gd_textFaktorIzlozenosti_.widthHint = 75;
		textFaktorIzlozenosti_.setLayoutData(gd_textFaktorIzlozenosti_);

		Label lblUtjecajNaRaspolozivost_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUtjecajNaRaspolozivost_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUtjecajNaRaspolozivost_.setText("Utjecaj na raspolo\u017Eivost:");

		Combo comboUtjecajNaRaspolozivost_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUtjecajNaRaspolozivost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboUtjecajNaRaspolozivost_.widthHint = 120;
		comboUtjecajNaRaspolozivost_.setLayoutData(gd_comboUtjecajNaRaspolozivost_);

		Label lblPotencijalniGubitak_ = new Label(grpAnalizaUinkaPrijetnje,
				SWT.NONE);
		lblPotencijalniGubitak_.setText("Potencijalni gubitak:");

		textPotencijalniGubitak_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER);
		GridData gd_textPotencijalniGubitak_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
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

		textOpisUcinka_ = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER | SWT.V_SCROLL);
		textOpisUcinka_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));

		textOpisFaktoraIzlozenosti_  = new Text(grpAnalizaUinkaPrijetnje, SWT.BORDER | SWT.V_SCROLL);
		textOpisFaktoraIzlozenosti_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));


		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 320;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(3, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.setText("Spremi");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setText("Izlaz");

		Button btnIspis_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIspis_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnIspis_.widthHint = 100;
		btnIspis_.setLayoutData(gd_btnIspis_);
		btnIspis_.setText("Ispis");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				mParent.getShell().close();
			}
		});




		scrollBox.setContent(mParent);
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
