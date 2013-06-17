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
	 private Text textCijenaProvedbe_;
	 private Text textOpisProvedbe_;


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
		composite1.setLayout(new GridLayout(1, false));
		composite1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		Group grpProvedba_ = new Group(composite1, SWT.NONE);
		GridData gd_grpProvedba_ = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 2);
		gd_grpProvedba_.widthHint = 396;
		grpProvedba_.setLayoutData(gd_grpProvedba_);
		grpProvedba_.setText("Provedba");
		grpProvedba_.setLayout(new GridLayout(4, false));

				Label label = new Label(grpProvedba_, SWT.NONE);
				label.setText("Datum Provedbe:");

				DateTime dateTimeRokProv_ = new DateTime(grpProvedba_, SWT.BORDER
						| SWT.DROP_DOWN);
				new Label(grpProvedba_, SWT.NONE);
						new Label(grpProvedba_, SWT.NONE);

						Label lblHard_ = new Label(grpProvedba_, SWT.NONE);
						lblHard_.setText("Cijena provedbe:");

						textCijenaProvedbe_ = new Text(grpProvedba_, SWT.BORDER);
						GridData gd_textCijenaProvedbe_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
						gd_textCijenaProvedbe_.widthHint = 111;
						textCijenaProvedbe_.setLayoutData(gd_textCijenaProvedbe_);
				new Label(grpProvedba_, SWT.NONE);
				new Label(grpProvedba_, SWT.NONE);

				Label lblOpisProv_ = new Label(grpProvedba_, SWT.NONE);
				lblOpisProv_.setText("Opis Provedbe:");
		new Label(grpProvedba_, SWT.NONE);
		new Label(grpProvedba_, SWT.NONE);
		new Label(grpProvedba_, SWT.NONE);

				textOpisProvedbe_ = new Text(grpProvedba_, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				GridData gd_text = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
				gd_text.heightHint = 37;
				gd_text.minimumHeight = 50;
				textOpisProvedbe_.setLayoutData(gd_text);
		new Label(mParent, SWT.NONE);
		new Label(mParent, SWT.NONE);

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		GridData gd_compositeButtons_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 1, 1);
		gd_compositeButtons_.horizontalIndent = 10;
		gd_compositeButtons_.widthHint = 220;
		compositeButtons_.setLayoutData(gd_compositeButtons_);
		compositeButtons_.setLayout(new GridLayout(2, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnSpremi_ = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
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
