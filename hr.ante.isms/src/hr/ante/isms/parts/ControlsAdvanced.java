package hr.ante.isms.parts;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ImpactAnalysisASKTableModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.DateTime;

public class ControlsAdvanced {

	private Composite mParent;
	 @Inject
	  MDirtyable dirty;
	 private Text textPodsj_;
	 private Text textEvid_;
	 private Text textHard_;
	 private Text textSoft_;
	 private Text textServis_;
	 private Text textLjudi_;
	 private Text textEduk_;


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

		mParent.getShell().setSize(630, 640);
		mParent.getShell().setText("Ažuriranje Kontrole - Nastavak");
		mParent.setLayout(new GridLayout(2, false));

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(2, false));
		composite1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		Group grpProvedba_ = new Group(composite1, SWT.NONE);
		GridData gd_grpProvedba_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 2);
		gd_grpProvedba_.widthHint = 396;
		grpProvedba_.setLayoutData(gd_grpProvedba_);
		grpProvedba_.setText("Provedba");
		grpProvedba_.setLayout(new GridLayout(2, false));

		Label lblRokProv_ = new Label(grpProvedba_, SWT.NONE);
		lblRokProv_.setText("Rok Provedbe:");

		DateTime dateTimeRokProv_ = new DateTime(grpProvedba_, SWT.BORDER
				| SWT.DROP_DOWN);

		Label lblOpisProv_ = new Label(grpProvedba_, SWT.NONE);
		lblOpisProv_.setText("Opis Provedbe:");
		new Label(grpProvedba_, SWT.NONE);

		StyledText styleTxtOpisProv_ = new StyledText(grpProvedba_, SWT.BORDER);
		GridData gd_styleTxtOpisProv_= new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 2);
		gd_styleTxtOpisProv_.widthHint = 355;
		gd_styleTxtOpisProv_.heightHint = 75;
		styleTxtOpisProv_.setLayoutData(gd_styleTxtOpisProv_);

		Label lblDatumProv_ = new Label(grpProvedba_, SWT.NONE);
		lblDatumProv_.setText("Datum Provedbe:");

		DateTime dateTimeDatumProv_ = new DateTime(grpProvedba_, SWT.BORDER
				| SWT.DROP_DOWN);

		Label lblPodsj_= new Label(grpProvedba_, SWT.NONE);
		lblPodsj_.setText("Podsjetnik:");

		textPodsj_ = new Text(grpProvedba_, SWT.BORDER);
		textPodsj_.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Label lblEvid_ = new Label(grpProvedba_, SWT.NONE);
		lblEvid_.setText("Evidencija:");

		textEvid_ = new Text(grpProvedba_, SWT.BORDER);
		textEvid_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Group grpCijenaProvedbe_ = new Group(composite1, SWT.NONE);
		grpCijenaProvedbe_.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));
		grpCijenaProvedbe_.setText("Cijena Provedbe");
		grpCijenaProvedbe_.setLayout(new GridLayout(2, false));

		Label lblHard_ = new Label(grpCijenaProvedbe_, SWT.NONE);
		lblHard_.setText("Hardver:");

		textHard_ = new Text(grpCijenaProvedbe_, SWT.BORDER);
		textHard_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblSoft_ = new Label(grpCijenaProvedbe_, SWT.NONE);
		lblSoft_.setText("Softver:");

		textSoft_ = new Text(grpCijenaProvedbe_, SWT.BORDER);
		textSoft_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblServis_ = new Label(grpCijenaProvedbe_, SWT.NONE);
		lblServis_.setText("Servis:");

		textServis_ = new Text(grpCijenaProvedbe_, SWT.BORDER);
		textServis_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblLjudi_ = new Label(grpCijenaProvedbe_, SWT.NONE);
		lblLjudi_.setText("Ljudi");

		textLjudi_ = new Text(grpCijenaProvedbe_, SWT.BORDER);
		textLjudi_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblEduk_ = new Label(grpCijenaProvedbe_, SWT.NONE);
		lblEduk_.setText("Edukacija:");

		textEduk_ = new Text(grpCijenaProvedbe_, SWT.BORDER);
		textEduk_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(composite1, SWT.NONE);

		Composite composite2 = new Composite(mParent, SWT.NONE);
		composite2.setLayout(new GridLayout(1, false));
		GridData gd_composite2 = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_composite2.heightHint = 114;
		gd_composite2.widthHint = 560;
		composite2.setLayoutData(gd_composite2);

		Label lblTest_ = new Label(composite2, SWT.NONE);
		lblTest_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblTest_.setText("Testiranje:");

		StyledText styleTxtTest_ = new StyledText(composite2, SWT.BORDER);
		GridData gd_styleTxtTest_ = new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1);
		gd_styleTxtTest_.heightHint = 73;
		gd_styleTxtTest_.widthHint = 567;
		styleTxtTest_.setLayoutData(gd_styleTxtTest_);

		Group grpRazinaRiz_ = new Group(mParent, SWT.NONE);
		grpRazinaRiz_.setText("Razina Rizika");
		grpRazinaRiz_.setLayout(new GridLayout(4, false));

		Button btnRadNemRiz_ = new Button(grpRazinaRiz_, SWT.RADIO);
		btnRadNemRiz_.setText("Nema rizika");

		Button btnRadNizak_ = new Button(grpRazinaRiz_, SWT.RADIO);
		btnRadNizak_.setText("Nizak");

		Button btnRadSred_ = new Button(grpRazinaRiz_, SWT.RADIO);
		btnRadSred_.setText("Srednji");

		Button btnRadVisok_ = new Button(grpRazinaRiz_, SWT.RADIO);
		btnRadVisok_.setText("Visok");
		new Label(mParent, SWT.NONE);

		Composite composite3 = new Composite(mParent, SWT.NONE);
		composite3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite3.setLayout(new GridLayout(1, false));

		Label lblRizici = new Label(composite3, SWT.NONE);
		lblRizici.setText("Rizici:");

		StyledText styleTxtRizici_ = new StyledText(composite3, SWT.BORDER);
		GridData gd_styleTxtRizici_ = new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1);
		gd_styleTxtRizici_.widthHint = 567;
		gd_styleTxtRizici_.heightHint = 73;
		styleTxtRizici_.setLayoutData(gd_styleTxtRizici_);
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

		Button btnPrihvati_ = new Button(compositeButtons_, SWT.NONE);
		btnPrihvati_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnPrihvati_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnPrihvati_.widthHint = 100;
		btnPrihvati_.setLayoutData(gd_btnPrihvati_);
		btnPrihvati_.setText("Nastavak");

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
