package hr.ante.isms.parts;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ThreatsASKTableModel;

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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class Threats {

	private Composite mParent;
	 @Inject
	  MDirtyable dirty;

     @Inject private MApplication app;
	 private Text textNaziv_;
	 private Text textOpis_;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(-195, 85);
		scrollBox.setMinHeight(450);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
//		mParent.setSize(790, 659);
		// parent.setSize(new Point(759, 359));
		mParent.getShell().setSize(760, 500);
		mParent.getShell().setText("Evidencija Prijetnji");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite1 = new Composite(mParent, SWT.NONE);
		composite1.setLayout(new GridLayout(4, false));
		GridData gd_composite1 = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_composite1.heightHint = 214;
		composite1.setLayoutData(gd_composite1);

		Label lblNaziv_ = new Label(composite1, SWT.NONE);
		lblNaziv_.setText("Naziv:");

		textNaziv_ = new Text(composite1, SWT.BORDER);
		textNaziv_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 3, 1));

		Label lblVrsta_ = new Label(composite1, SWT.NONE);
		lblVrsta_.setText("Vrsta:");

		Combo comboVrsta_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboVrsta_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 3, 1);
		gd_comboVrsta_.widthHint = 190;
		comboVrsta_.setLayoutData(gd_comboVrsta_);
		comboVrsta_.setItems(getComboItemsFromDB("as_threat_type"));

		Label lblOpis_ = new Label(composite1, SWT.NONE);
		lblOpis_.setText("Opis:");
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);

		textOpis_ = new Text(composite1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_textOpis_ = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 2);
		gd_textOpis_.heightHint = 44;
		textOpis_.setLayoutData(gd_textOpis_);

		Label lblVjerojatnost_ = new Label(composite1, SWT.NONE);
		lblVjerojatnost_.setText("Vjerojatnost:");
		lblVjerojatnost_.setBounds(0, 0, 32, 15);

		Combo comboVjerojatnost_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 200;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);
		comboVjerojatnost_.setItems(getComboItemsFromDB("as_probability"));

		Label lblPorijeklo_ = new Label(composite1, SWT.NONE);
		lblPorijeklo_.setText("Porijeklo:");

		Combo comboPorijeklo_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboPorijeklo_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
		gd_comboPorijeklo_.widthHint = 130;
		comboPorijeklo_.setLayoutData(gd_comboPorijeklo_);
		comboPorijeklo_.setItems(getComboItemsFromDB("as_threat_origin"));

		Label lblUcestalost_ = new Label(composite1, SWT.NONE);
		lblUcestalost_.setText("U\u010Destalost:");

		Combo comboUcestalost_ = new Combo(composite1, SWT.NONE);
		GridData gd_comboUcestalost_ = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_comboUcestalost_.widthHint = 130;
		comboUcestalost_.setLayoutData(gd_comboUcestalost_);
		comboUcestalost_.setItems(getComboItemsFromDB("as_threat_frequency"));

				Label lblRazorMoc_ = new Label(composite1, SWT.NONE);
				lblRazorMoc_.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblRazorMoc_.setText("Razorna Mo\u010D:");

				Combo comboRazorMoc_ = new Combo(composite1, SWT.NONE);
				GridData gd_comboRazorMoc_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
				gd_comboRazorMoc_.widthHint = 130;
				comboRazorMoc_.setLayoutData(gd_comboRazorMoc_);
				comboRazorMoc_.setItems(getComboItemsFromDB("as_threat_impact"));

				Label lblIzvor_ = new Label(composite1, SWT.NONE);
				lblIzvor_.setText("Izvor:");

				Combo comboIzvor_ = new Combo(composite1, SWT.NONE);
				GridData gd_comboIzvor_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
				gd_comboIzvor_.widthHint = 130;
				comboIzvor_.setLayoutData(gd_comboIzvor_);
				comboIzvor_.setItems(getComboItemsFromDB("as_threat_source"));

				Label lblNamjera_ = new Label(composite1, SWT.NONE);
				lblNamjera_.setText("Namjera:");

				Combo comboNamjera_ = new Combo(composite1, SWT.NONE);
				GridData gd_comboNamjera_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
				gd_comboNamjera_.widthHint = 130;
				comboNamjera_.setLayoutData(gd_comboNamjera_);
				comboNamjera_.setItems(getComboItemsFromDB("as_threat_intention"));
				new Label(composite1, SWT.NONE);
				new Label(composite1, SWT.NONE);
				new Label(composite1, SWT.NONE);
				new Label(composite1, SWT.NONE);


		Composite compositeASKTtable = new Composite(mParent, SWT.NONE);
		compositeASKTtable.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeASKTtable = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_compositeASKTtable.heightHint = 72;
		compositeASKTtable.setLayoutData(gd_compositeASKTtable);
		new ASKTable(compositeASKTtable, new ThreatsASKTableModel(), 717,compositeASKTtable.getBounds().height );
		//new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 641;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(6, false));

				Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
				GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
						false, 1, 1);
				gd_btnSpremi_.widthHint = 100;
				btnSpremi_.setLayoutData(gd_btnSpremi_);
				btnSpremi_.setText("Spremi");

		Button btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNovo_.setText("Novo");

		Button btnDupliciraj_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnDupliciraj_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDupliciraj_.widthHint = 100;
		btnDupliciraj_.setLayoutData(gd_btnDupliciraj_);
		btnDupliciraj_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDupliciraj_.setText("Dupliciraj");

				Button btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
				GridData gd_btnBrisi_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
						1, 1);
				gd_btnBrisi_.widthHint = 100;
				btnBrisi_.setLayoutData(gd_btnBrisi_);
				btnBrisi_.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});
				btnBrisi_.setText("Briši");

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

				Button btnIspis_ = new Button(compositeButtons_, SWT.NONE);
				btnIspis_.setText("Ispis");
				GridData gd_btnIspis_ = new GridData(SWT.LEFT, SWT.CENTER, false,
						false, 1, 1);
				gd_btnIspis_.widthHint = 100;
				btnIspis_.setLayoutData(gd_btnIspis_);



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
