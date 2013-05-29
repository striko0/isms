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

public class ImpactAnalysis {

	private Composite mParent;
	 @Inject
	  MDirtyable dirty;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		//scrollBox.setBounds(0, 0, 760, 450);
		//scrollBox.setBounds(0, 0, 448, 375);
		scrollBox.setMinHeight(470);
		scrollBox.setMinWidth(500);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		//parent.setSize(new Point(759, 359));
		//mParent.getShell().setSize(759, 400);
		mParent.getShell().setText("Analiza uèinka ostvarenja prijetnja za imovinu");
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
		GridData gd_grpVjerojatnostOstvarenjaPrijetnje = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2);
		gd_grpVjerojatnostOstvarenjaPrijetnje.horizontalIndent = 10;
		gd_grpVjerojatnostOstvarenjaPrijetnje.heightHint = 107;
		gd_grpVjerojatnostOstvarenjaPrijetnje.widthHint = 799;
		grpVjerojatnostOstvarenjaPrijetnje.setLayoutData(gd_grpVjerojatnostOstvarenjaPrijetnje);
		grpVjerojatnostOstvarenjaPrijetnje.setText("Vjerojatnost Ostvarenja Prijetnje");

		Label lblVjerojatnost_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_lblVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblVjerojatnost_.widthHint = 96;
		lblVjerojatnost_.setLayoutData(gd_lblVjerojatnost_);
		lblVjerojatnost_.setText("Vjerojatnost:");

		Combo comboVjerojatnost_ = new Combo(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		GridData gd_comboVjerojatnost_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_comboVjerojatnost_.widthHint = 250;
		comboVjerojatnost_.setLayoutData(gd_comboVjerojatnost_);

		Label lblOpisVjerojat_ = new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);
		lblOpisVjerojat_.setText("Opis Vjerojatnosti:");
		new Label(grpVjerojatnostOstvarenjaPrijetnje, SWT.NONE);

		StyledText styledTextOpisVjerojat_ = new StyledText(grpVjerojatnostOstvarenjaPrijetnje, SWT.BORDER);
		GridData gd_styledTextOpisVjerojat_ = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_styledTextOpisVjerojat_.heightHint = 17;
		styledTextOpisVjerojat_.setLayoutData(gd_styledTextOpisVjerojat_);

		Group grpAnalizaUinkaPrijetnje = new Group(mParent, SWT.NONE);
		grpAnalizaUinkaPrijetnje.setText("Analiza U\u010Dinka Prijetnje");
		GridData gd_grpAnalizaUinkaPrijetnje = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_grpAnalizaUinkaPrijetnje.horizontalIndent = 10;
		gd_grpAnalizaUinkaPrijetnje.heightHint = 86;
		gd_grpAnalizaUinkaPrijetnje.widthHint = 371;
		grpAnalizaUinkaPrijetnje.setLayoutData(gd_grpAnalizaUinkaPrijetnje);
		grpAnalizaUinkaPrijetnje.setLayout(new GridLayout(2, false));

		Label lblUcinak_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		lblUcinak_.setText("U\u010Dinak:");

		Combo comboUcinak_ = new Combo(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_comboUcinak_ = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_comboUcinak_.widthHint = 250;
		comboUcinak_.setLayoutData(gd_comboUcinak_);

		Label lblOpisUcinka_ = new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
		GridData gd_lblOpisUcinka_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblOpisUcinka_.widthHint = 104;
		lblOpisUcinka_.setLayoutData(gd_lblOpisUcinka_);
		lblOpisUcinka_.setText("Opis U\u010Dinka:");
		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);

		StyledText styledTextUcinak_ = new StyledText(grpAnalizaUinkaPrijetnje, SWT.BORDER);
		GridData gd_styledTextUcinak_ = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_styledTextUcinak_.heightHint = 9;
		styledTextUcinak_.setLayoutData(gd_styledTextUcinak_);
//		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
//		new Label(grpAnalizaUinkaPrijetnje, SWT.NONE);
//		new Label(mParent, SWT.NONE);
//		new Label(mParent, SWT.NONE);


		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_compositeASKTable.heightHint = 164;
		gd_compositeASKTable.minimumHeight = 100;
		gd_compositeASKTable.widthHint = 778;
		gd_compositeASKTable.horizontalIndent = 10;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

//		new ASKTable(compositeASKTable, 717,compositeASKTable.getBounds().height );
		new ASKTable(compositeASKTable,new ImpactAnalysisASKTableModel(), 717,compositeASKTable.getBounds().height );
				new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.CENTER,
				true, false, 1, 1);
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

		Button btnBrisi_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnBrisi_ = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.setText("Bri\u0161i");

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
