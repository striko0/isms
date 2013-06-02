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

public class MeasureEvaluation {
	private Composite mParent;
	@Inject
	MDirtyable dirty;

	@Inject
	private MApplication app;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		// scrollBox.setBounds(0, 0, 837, 298);
		// scrollBox.setBounds(0, 0, 760, 450);
		// scrollBox.setBounds(0, 0, 448, 375);
		scrollBox.setMinHeight(200);
		scrollBox.setMinWidth(400);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		// mParent.setSize(790, 659);
		// parent.setSize(new Point(759, 359));
		mParent.getShell().setSize(450, 250);
		mParent.getShell().setText("Mjere za smanjenje rizika");
		mParent.setLayout(new GridLayout(1, false));

		Group grpOekivaneVrijednostiNakon = new Group(mParent, SWT.NONE);
		grpOekivaneVrijednostiNakon.setLayout(new GridLayout(4, false));
		grpOekivaneVrijednostiNakon.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpOekivaneVrijednostiNakon.setText("O\u010Dekivane Vrijednosti Nakon Uvo\u0111enja Predlo\u017Eenih Mjera");

		Label lblVjerojatnost = new Label(grpOekivaneVrijednostiNakon, SWT.NONE);
		lblVjerojatnost.setText("Vjerojatnost:");

		Combo combo = new Combo(grpOekivaneVrijednostiNakon, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblUinak = new Label(grpOekivaneVrijednostiNakon, SWT.NONE);
		lblUinak.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUinak.setText("U\u010Dinak: ");

		Combo combo_1 = new Combo(grpOekivaneVrijednostiNakon, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				new Label(mParent, SWT.NONE);

				Group grpNainSmanjivanjaRizika = new Group(mParent, SWT.NONE);
				grpNainSmanjivanjaRizika.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				grpNainSmanjivanjaRizika.setText("Na\u010Din Smanjivanja Rizika");
				grpNainSmanjivanjaRizika.setLayout(new GridLayout(2, false));

						Label lblNainSmanjivanja = new Label(grpNainSmanjivanjaRizika, SWT.NONE);
						lblNainSmanjivanja.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						lblNainSmanjivanja.setText("Na\u010Din Smanjivanja: ");

								Combo combo_2 = new Combo(grpNainSmanjivanjaRizika, SWT.NONE);
								combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
