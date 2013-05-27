package hr.ante.isms.parts;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ThreatASKTableModel;
import hr.ante.test.asktable.ASTableModel4;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.kupzog.ktable.KTable;

public class Threats3 {

	 @Inject
	  MDirtyable dirty;

	private Composite mParent;
	private KTable table;
	private ASTableModel4 model;
//
//	public Threats1() {
//	}

	@PostConstruct
	public void createComposite(final Composite parent) {




		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(359);
		scrollBox.setMinWidth(450);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);

		parent.getShell().setSize(759, 389);

		mParent.getShell().setText(
				"Identifikacija prijetnji za imovinu: --***--");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.INHERIT_NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite.horizontalIndent = 10;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(2, false));

		Label lblVrstaPrijet_ = new Label(composite, SWT.NONE);
		lblVrstaPrijet_.setText("Vrsta prijetnje:");

		Combo comboVrstaPrijet_ = new Combo(composite, SWT.NONE);
		comboVrstaPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");

		Combo comboPrijetnja_ = new Combo(composite, SWT.NONE);
		comboPrijetnja_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


		Composite compositeASKTable = new Composite(mParent, SWT.INHERIT_FORCE);
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);
		compositeASKTable.setLayout(new FillLayout());
		new ASKTable(compositeASKTable,new ThreatASKTableModel(), 717,compositeASKTable.getBounds().height );

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		compositeButtons_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		compositeButtons_.setLayout(new GridLayout(4, false));


		Button btnSpremi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnSpremi_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSpremi_.setText("Spremi");

		Button btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.setText("Novo");

		Button btnBrisi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnBrisi_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.setText("Bri\u0161i");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
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
