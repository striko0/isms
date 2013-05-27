package hr.ante.isms.parts;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ISMSASKTableModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;



public class Isms {
	@Inject IStylingEngine engine;
    @Inject private MApplication app;

    @Inject
	private	IEclipseContext context;
    static Image imovinaIcon=null;
    static Image identPrijetnjiIcon=null;
    static Image identRanjivostiIcon = null;
    static Image utvrdVjerojatnostiIcon =null;
    static Image analizaUcinkaIcon = null;
    // create images for toolbar buttons


    private Composite mParent;
	 @Inject
	  MDirtyable dirty;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setBounds(0, 0, 490, 537);
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
		mParent.getShell().setText("Upravljanje Rizicima");
		mParent.setLayout(new GridLayout(1, false));

				Composite compositeASKTable = new Composite(mParent, SWT.NONE);
				compositeASKTable.setLayout(new FillLayout());
				GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
				gd_compositeASKTable.minimumHeight = 100;
				gd_compositeASKTable.widthHint = 778;
				gd_compositeASKTable.horizontalIndent = 10;
				compositeASKTable.setLayoutData(gd_compositeASKTable);
				new ASKTable(compositeASKTable,new ISMSASKTableModel(), 717,compositeASKTable.getBounds().height );

				Composite composite = new Composite(mParent, SWT.NONE);
				composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				composite.setLayout(new GridLayout(2, false));

				CLabel label = new CLabel(composite, SWT.NONE);
				label.setText("ANTE STRIKOMAN");
				label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

				CLabel label_1 = new CLabel(composite, SWT.NONE);
				label_1.setText("ponedjeljak, 27. svibanj 2013");
				label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

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
