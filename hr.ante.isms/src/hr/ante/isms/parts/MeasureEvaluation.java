package hr.ante.isms.parts;

import hr.ante.isms.connection.DatabaseConnection;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
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

		Group grpOcekivaneVrijednostiNakon_ = new Group(mParent, SWT.NONE);
		grpOcekivaneVrijednostiNakon_.setLayout(new GridLayout(4, false));
		grpOcekivaneVrijednostiNakon_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpOcekivaneVrijednostiNakon_.setText("O\u010Dekivane Vrijednosti Nakon Uvo\u0111enja Predlo\u017Eenih Mjera");

		Label lblVjerojatnost_ = new Label(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost:");

		Combo comboVjerojatnost_ = new Combo(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		comboVjerojatnost_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboVjerojatnost_.setItems(getComboItemsFromDB("as_probability"));

		Label lblUcinak_ = new Label(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		lblUcinak_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUcinak_.setText("U\u010Dinak: ");

		Combo comboUcinak_ = new Combo(grpOcekivaneVrijednostiNakon_, SWT.NONE);
		comboUcinak_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				new Label(mParent, SWT.NONE);

				Group grpNainSmanjivanjaRizika = new Group(mParent, SWT.NONE);
				grpNainSmanjivanjaRizika.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				grpNainSmanjivanjaRizika.setText("Na\u010Din Smanjivanja Rizika");
				grpNainSmanjivanjaRizika.setLayout(new GridLayout(2, false));

						Label lblNacinSmanjivanja_ = new Label(grpNainSmanjivanjaRizika, SWT.NONE);
						lblNacinSmanjivanja_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						lblNacinSmanjivanja_.setText("Na\u010Din Smanjivanja: ");

								Combo comboNacinSmanjivanja_ = new Combo(grpNainSmanjivanjaRizika, SWT.NONE);
								comboNacinSmanjivanja_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
								comboNacinSmanjivanja_.setItems(getComboItemsFromDB("as_risk_mitigation"));

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

	public String[] getComboItemsFromDB(String tableName) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItems(tableName);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {};

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
