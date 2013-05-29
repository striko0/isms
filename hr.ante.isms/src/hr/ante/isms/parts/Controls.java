package hr.ante.isms.parts;

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
import org.eclipse.swt.custom.StyledText;
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

public class Controls {

	private Composite mParent;
	 @Inject
	  MDirtyable dirty;

     @Inject private MApplication app;
	 private Text textNaziv_;
	 private Text textOznaka_;
	 private Text textOpis_;
	 private Text textSmjerPrim_;
	 private Text textStaPrim_;
	 private Text textPrep_;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);

		scrollBox.setMinHeight(550);
		scrollBox.setMinWidth(550);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);

		// parent.setSize(new Point(759, 359));
		mParent.getShell().setSize(675, 600);
		mParent.getShell().setText("Evidencija Kontrole");
		mParent.setLayout(new GridLayout(2, false));

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

		Button button = new Button(group, SWT.RADIO);
		button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		button.setText("Aktivno");

		Button button_1 = new Button(group, SWT.RADIO);
		button_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		button_1.setText("Planirano");

		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		button_2.setText("Neaktivno");

		Group group_1 = new Group(composite1, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		group_1.setText("Tip");
		group_1.setLayout(new GridLayout(3, false));

		Button button_3 = new Button(group_1, SWT.RADIO);
		button_3.setText("Upravlja\u010Dka");

		Button button_4 = new Button(group_1, SWT.RADIO);
		button_4.setText("Logi\u010Dka");

		Button button_5 = new Button(group_1, SWT.RADIO);
		button_5.setText("Fizi\u010Dka");

		Group group_2 = new Group(composite1, SWT.NONE);
		group_2.setText("Vrsta");
		group_2.setLayout(new GridLayout(3, false));

		Button button_6 = new Button(group_2, SWT.RADIO);
		button_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		button_6.setText("Preventivna");

		Button button_7 = new Button(group_2, SWT.RADIO);
		button_7.setText("Detekcijska");

		Button button_8 = new Button(group_2, SWT.RADIO);
		button_8.setText("Reakcijska");

		Composite composite2 = new Composite(mParent, SWT.NONE);
		composite2.setLayout(new GridLayout(6, false));
		composite2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				2, 1));

		Label lblOcjDjelot_ = new Label(composite2, SWT.NONE);
		lblOcjDjelot_.setText("Ocjena Djelotvornosti:");
		lblOcjDjelot_.setBounds(0, 0, 32, 15);

		Combo comboOcjDjelot_ = new Combo(composite2, SWT.NONE);
		comboOcjDjelot_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblOdgOsoba_ = new Label(composite2, SWT.NONE);
		lblOdgOsoba_.setText("Odgovorna Osoba:");

		Combo comboOdgOsoba_ = new Combo(composite2, SWT.NONE);
		comboOdgOsoba_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblSukl_ = new Label(composite2, SWT.NONE);
		lblSukl_.setText("Sukladnost:");

		Combo comboSukl_ = new Combo(composite2, SWT.NONE);
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

		Button btnSpremiIPripremi_ = new Button(compositeButtons_, SWT.NONE);
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
