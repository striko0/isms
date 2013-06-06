package hr.ante.isms.parts;

import java.util.concurrent.CopyOnWriteArrayList;

import hr.ante.isms.parts.table.ASKTable;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.NewASKTable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;



public class ListAssets {
	@Inject IStylingEngine engine;
    @Inject private MApplication app;

    @Inject
	private	IEclipseContext context;
    // create images for toolbar buttons


	@Inject
	protected EPartService partService;

    private Composite mParent;
    private NewASKTable table;
	@Inject
	MDirtyable dirty;


	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrollBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
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
		mParent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		//parent.setSize(new Point(759, 359));
		//mParent.getShell().setSize(759, 400);
		mParent.getShell().setText("Upravljanje Rizicima");
		GridLayout gl_mParent = new GridLayout(1, false);
		gl_mParent.verticalSpacing = 0;
		gl_mParent.horizontalSpacing = 0;
		gl_mParent.marginWidth = 0;
		gl_mParent.marginHeight = 0;
		mParent.setLayout(gl_mParent);



		MPart assetPart = partService.findPart("hr.ante.isms.part.asset");
		if(assetPart.isDirty())
			mParent.redraw();

		Label naslov_ = new Label (mParent, SWT.NONE);
		naslov_.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		naslov_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		naslov_.setFont(SWTResourceManager.getFont("Georgia", 18, SWT.BOLD));
		naslov_.setText("Imovina");
		naslov_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.minimumHeight = 100;
		gd_compositeASKTable.widthHint = 778;
		compositeASKTable.setLayoutData(gd_compositeASKTable);


		table = new NewASKTable(compositeASKTable, new ListAssetASKTableModel(),
				717, compositeASKTable.getBounds().height);
//		ContextInjectionFactory.inject(table, context);



		Composite compositeUser_ = new Composite(mParent, SWT.NONE);
		compositeUser_.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridData gd_compositeUser_ = new GridData(SWT.RIGHT, SWT.FILL, true,
				false, 1, 1);
		gd_compositeUser_.heightHint = 20;
		compositeUser_.setLayoutData(gd_compositeUser_);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		gl_composite.marginWidth = 0;
		compositeUser_.setLayout(gl_composite);

		CLabel labelUser_ = new CLabel(compositeUser_, SWT.NONE);
		labelUser_.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false,
				1, 1));
		labelUser_.setText("TESTUser");
		labelUser_.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		Label labelsep_ = new Label(compositeUser_, SWT.SEPARATOR);
		CLabel labelUserDatum_ = new CLabel(compositeUser_, SWT.NONE);
		labelUserDatum_.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true,
				false, 1, 1));
		labelUserDatum_.setText("ponedjeljak, 27. svibanj 2013");
		labelUserDatum_.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		new Label(compositeUser_, SWT.NONE);
		new Label(compositeUser_, SWT.NONE);
		new Label(compositeUser_, SWT.NONE);

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
