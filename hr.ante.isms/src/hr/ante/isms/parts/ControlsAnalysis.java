package hr.ante.isms.parts;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ControlsAnalysisASKTableModel;

import java.sql.SQLException;

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

public class ControlsAnalysis {
	private Composite mParent;
	@Inject
	MDirtyable dirty;

	@Inject
	private MApplication app;
	private Text textPrimjena_;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setLocation(0, 0);
		// scrollBox.setBounds(0, 0, 837, 298);
		// scrollBox.setBounds(0, 0, 760, 450);
		// scrollBox.setBounds(0, 0, 448, 375);
		scrollBox.setMinHeight(300);
		scrollBox.setMinWidth(700);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		// mParent.setSize(790, 659);
		// parent.setSize(new Point(759, 359));
		mParent.getShell().setSize(760, 360);
		mParent.getShell().setText("Analiza kontrole za imovinu");
		mParent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblKontrola_ = new Label(composite, SWT.NONE);
		lblKontrola_.setText("Kontrola:");

		Combo comboKontrola_ = new Combo(composite, SWT.NONE);
		comboKontrola_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboKontrola_.setItems(getComboItemsFromDB("as_control"));

		Label lblPrimjena_ = new Label(composite, SWT.NONE);
		lblPrimjena_.setText("Primjena:");
		new Label(composite, SWT.NONE);

		textPrimjena_  = new Text(composite, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_Primjena_ = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 2);
		gd_Primjena_.heightHint = 50;
		textPrimjena_.setLayoutData(gd_Primjena_);

		Composite compositeASKTtable = new Composite(mParent, SWT.NONE);
		compositeASKTtable.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compositeASKTtable = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_compositeASKTtable.heightHint = 107;
		compositeASKTtable.setLayoutData(gd_compositeASKTtable);
		new ASKTable(compositeASKTtable, new ControlsAnalysisASKTableModel(), 717,compositeASKTtable.getBounds().height );

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 2, 1);
		gd_compositeButtons_.heightHint = 42;
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 530;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(5, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.setText("Spremi");

		Button btnEvidKontrola_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnEvidKontrola_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnEvidKontrola_.widthHint = 100;
		btnEvidKontrola_.setLayoutData(gd_btnEvidKontrola_);
		btnEvidKontrola_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Controls");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
			}
		});
		btnEvidKontrola_.setText("Evid. Kontrola");

		Button btnNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNovo_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_btnNovo_.widthHint = 100;
		btnNovo_.setLayoutData(gd_btnNovo_);
		btnNovo_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNovo_.setText("Novo");

		Button btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnBrisi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
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
