package hr.ante.isms.parts;

import com.ibm.icu.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class StartPage {

	private Composite mParent;

	 @Inject
	  MDirtyable dirty;



	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(200);
		scrollBox.setMinWidth(300);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		//parent.setSize(new Point(759, 359));

		GridLayout gl_mParent = new GridLayout(1, false);
		gl_mParent.verticalSpacing = 0;
		gl_mParent.marginWidth = 0;
		gl_mParent.marginHeight = 0;
		mParent.setLayout(gl_mParent);

		Composite composite_1 = new Composite(mParent, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_1.setLayout(new GridLayout(2, false));

		Label lblIsms = new Label(composite_1, SWT.NONE);
		lblIsms.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true,
				1, 1));
		lblIsms.setFont(SWTResourceManager.getFont("Georgia", 18, SWT.BOLD));
		lblIsms.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblIsms.setText("ISMS - Upravljanje Rizicima");
		new Label(composite_1, SWT.NONE);

		Composite composite = new Composite(mParent, SWT.NONE);
		GridData gd_composite = new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 1);
		gd_composite.heightHint = 20;
		composite.setLayoutData(gd_composite);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		CLabel label = new CLabel(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false, 1, 1));
		label.setText("TESTUser");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		Label label_1 = new Label(composite, SWT.SEPARATOR);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		CLabel label_2 = new CLabel(composite, SWT.NONE);
		GridData gd_label_2 = new GridData(SWT.RIGHT, SWT.TOP, true, false, 1, 1);
		gd_label_2.widthHint = 150;
		label_2.setLayoutData(gd_label_2);
//		label_2.setText("Petak, 21. Lipanj 2013");
		label_2.setText(getCurrentDate());
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		// parent.getShell().setMinimumSize(759,390);
		// parent.getShell().setMinimumSize(759, 390);
		// parent.getShell().setSize(780, 410);

		mParent.getShell().setText("Upravljanje Rizicima");

		//**********textObjanjenjeostalo_ layout
		FormData fd_textObjanjenjeostalo = new FormData();
		fd_textObjanjenjeostalo.bottom = new FormAttachment(100,0);
		fd_textObjanjenjeostalo.right = new FormAttachment(100,0);

		scrollBox.setContent(mParent);


	}
	
	private String getCurrentDate()
	{
		Locale currentLocale = new Locale("hr","HR");
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,currentLocale);
		Date today = new Date();
		String dateOut = dateFormatter.format(today);
		return dateOut;
		
	}	

	@PreDestroy
	public void dispose() throws Exception {
	  System.out.println("Closing Administration");
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
