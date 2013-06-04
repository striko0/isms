package hr.ante.isms.parts;

import java.sql.SQLException;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ThreatIdentASKTableModel;
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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.kupzog.ktable.KTable;

public class ThreatIdentification {

	 @Inject
	  MDirtyable dirty;

	private Composite mParent;
	private ASKTable table;
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

		final Combo comboVrstaPrijet_ = new Combo(composite, SWT.NONE);

				Label lblPrijetnja_ = new Label(composite, SWT.NONE);
				lblPrijetnja_.setText("Prijetnja:");
		final Combo comboPrijetnja_ = new Combo(composite, SWT.NONE);
		comboPrijetnja_.setEnabled(false);


				comboPrijetnja_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboVrstaPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboVrstaPrijet_.setItems(getComboItemsFromDB("as_threat_type"));
		comboVrstaPrijet_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				int index = comboVrstaPrijet_.getSelectionIndex()+1;
				comboPrijetnja_.setEnabled(true);
				comboPrijetnja_.setItems(getComboItemsFromDB("as_threat", "WHERE threattype_id LIKE '"+index+"%'",true));
			}

		});


		Composite compositeASKTable = new Composite(mParent, SWT.INHERIT_FORCE);
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);
		compositeASKTable.setLayout(new FillLayout());
		table = new ASKTable(compositeASKTable,new ThreatIdentASKTableModel(), 717,compositeASKTable.getBounds().height );

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

	public String[] getComboItemsFromDB(String tableName){
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
		return new String[]{};

	}

	public String[] getComboItemsFromDB(String tableName, String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsWithWhere(tableName, whereStatement);

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
		return new String[]{};

	}

	public String[] getComboItemsFromDB(String tableName, String whereStatement, boolean valid){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {
//			if(type=="threat")
				return con.getComboItemsThreatOrVulnerability(tableName, whereStatement);

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
		return new String[]{};

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
