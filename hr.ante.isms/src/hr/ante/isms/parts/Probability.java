package hr.ante.isms.parts;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ProbabilityASKTableModel;

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

public class Probability {

	private Composite mParent;
	 @Inject
	  MDirtyable dirty;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		//scrollBox.setBounds(0, 0, 448, 375);
		scrollBox.setMinHeight(380);
		scrollBox.setMinWidth(600);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		//parent.setSize(new Point(759, 359));
		//mParent.getShell().setSize(759, 400);
		mParent.setLayout(new GridLayout(2, false));

		Label labelPrijet_ = new Label(mParent, SWT.NONE);
		GridData gd_labelPrijet_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_labelPrijet_.horizontalIndent = 10;
		gd_labelPrijet_.widthHint = 60;
		labelPrijet_.setLayoutData(gd_labelPrijet_);
		labelPrijet_.setText("Prijetnja:");

		Combo comboPrijet_ = new Combo(mParent, SWT.NONE);
		//gd_comboPrijet_.widthHint = 730;
		comboPrijet_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblRanjivost_ = new Label(mParent, SWT.NONE);
		GridData gd_lblRanjivost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRanjivost_.horizontalIndent = 10;
		lblRanjivost_.setLayoutData(gd_lblRanjivost_);
		lblRanjivost_.setText("Ranjivost:");

		Combo comboRanjivost_ = new Combo(mParent, SWT.NONE);
		//gd_comboRanjivost_.widthHint = 715;
		comboRanjivost_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpVjerojatnostOstvarenjaPrijetnje = new Group(mParent, SWT.NONE);
		grpVjerojatnostOstvarenjaPrijetnje.setLayout(new GridLayout(2, false));
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 2);
		gd_grpVjerojatnostOstvarenjaPrijetnje.horizontalIndent = 10;
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 122;
		gd_grpVjerojatnostOstvarenjaPrijetnje.widthHint = 799;
		grpVjerojatnostOstvarenjaPrijetnje.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje.setText("Vjerojatnost Ostvarenja Prijetnje");

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_lblVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblVjerojatnost_.widthHint = 96;
		lblVjerojatnost_.setLayoutData(gd_lblVjerojatnost_);
		lblVjerojatnost_.setText("Vjerojatnost:");

		Combo comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 217;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblOpisVjerojat_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojat_.setText("Opis Vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		StyledText styledTextOpisVjerojat_ = new StyledText(grpVjerojatnostOstvarenjaPrijetnje, SWT.BORDER);
		styledTextOpisVjerojat_.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));


		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_compositeASKTable.widthHint = 778;
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		new ASKTable(compositeASKTable, new ProbabilityASKTableModel(), 717,compositeASKTable.getBounds().height );
				new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.CENTER,
				true, false, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 320;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(3, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnNewButton);
		btnSpremi_.setText("Spremi");

		Button btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_1.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnNewButton_1);
		btnBrisi_.setText("Bri\u0161i");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_2.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnNewButton_2);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mParent.getShell().close();
			}
		});

		// parent.getShell().setMinimumSize(759,390);
		// parent.getShell().setMinimumSize(759, 390);
		// parent.getShell().setSize(780, 410);

		mParent.getShell().setText("Vjerojatnost ostvarenja prijetnja za odabranu imovinu");

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
