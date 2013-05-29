package hr.ante.isms.parts;

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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.mihalis.opal.opalDialog.Dialog;

public class Assets2 {

	private Composite mParent;
	private Text textNaziv_;
	private Text textOpis_;
	private Text textObjanjenjeostalo_;

	 @Inject
	  MDirtyable dirty;



	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(349);
		scrollBox.setMinWidth(650);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		// Using 0 here ensures the horizontal scroll bar will never appear. If
		// you want the horizontal bar to appear at some threshold (say 100
		// pixels) then send that value instead.

		mParent = new Composite(scrollBox, SWT.NONE);
		//parent.setSize(new Point(759, 359));
		parent.getShell().setSize(759, 389);

		FormLayout layout = new FormLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		mParent.setLayout(layout);

		// parent.getShell().setMinimumSize(759,390);
		// parent.getShell().setMinimumSize(759, 390);
		// parent.getShell().setSize(780, 410);

		mParent.getShell().setText("Unesi novu imovinu");


//		parent.setLayout(null);
//		parent.getShell().setMinimumSize(759, 384);
//		parent.getShell().setText("Unesi novu imovinu");
//		parent.getShell().setSize(759, 384);

		Composite compositeLeft = new Composite(mParent, SWT.NONE);
		compositeLeft.setLayout(new FormLayout());
		compositeLeft.setBounds(5, 10, 392, 299);


		Label lblNaziv_ = new Label(compositeLeft, SWT.NONE);
		lblNaziv_.setText("Naziv:");

		textNaziv_ = new Text(compositeLeft, SWT.BORDER);

		Label lblKategorija_ = new Label(compositeLeft, SWT.NONE);
		lblKategorija_.setText("Kategorija:");

		Combo comboKateg_ = new Combo(compositeLeft, SWT.NONE);	

		Label lblPodkateg_ = new Label(compositeLeft, SWT.NONE);
		lblPodkateg_.setText("Podkategorija:");

		Combo comboPodkateg_ = new Combo(compositeLeft, SWT.NONE);

		Label lblNoisteljorgjed_ = new Label(compositeLeft, SWT.NONE);
		lblNoisteljorgjed_.setText("Nositelj (org.jed.):");

		Combo comboNoisteljorgjed_ = new Combo(compositeLeft, SWT.NONE);

		Label lblPoslovniProces_ = new Label(compositeLeft, SWT.NONE);
		lblPoslovniProces_.setText("Poslovni proces:");

		Combo comboPoslovniProces_ = new Combo(compositeLeft, SWT.NONE);

		textOpis_ = new Text(compositeLeft, SWT.MULTI | SWT.BORDER);

		Label lblOpis_ = new Label(compositeLeft, SWT.NONE);
		lblOpis_.setText("Opis:");

		Group grpVanostImovine = new Group(mParent, SWT.NONE);
		grpVanostImovine.setText("Va\u017Enost Imovine");
		grpVanostImovine.setLocation(413, 10);
		grpVanostImovine.setSize(328, 253);

		Label lblPovjerljivost_ = new Label(grpVanostImovine, SWT.NONE);
		lblPovjerljivost_.setLocation(15, 43);
		lblPovjerljivost_.setSize(73, 13);
		lblPovjerljivost_.setText("Povjerljivost:");

		Combo comboPovjerljivost_ = new Combo(grpVanostImovine, SWT.NONE);
		comboPovjerljivost_.setLocation(90, 40);
		
		comboPovjerljivost_.setSize(180, 21);
		comboPovjerljivost_.setItems(new String[]{"1-Vrlo niska (javno)","2-Niska (ogranièeno)","3-Srednja (povjerljivo)","4-Visoka (tajno)","5-Vrlo visoka (vrlo tajno)"});

		Label lblCjelovitost = new Label(grpVanostImovine, SWT.NONE);
		lblCjelovitost.setLocation(15, 73);
		lblCjelovitost.setSize(65, 13);
		lblCjelovitost.setText("Cjelovitost:");

		Combo comboCjelovitost_ = new Combo(grpVanostImovine, SWT.NONE);
		comboCjelovitost_.setLocation(90, 70);
		comboCjelovitost_.setSize(180, 21);
		comboCjelovitost_.setItems(new String[]{"1-Vrlo niska","2-Niska","3-Srednja","4-Visoka","5-Vrlo visoka"});

		Label lblRaspoloivost_ = new Label(grpVanostImovine, SWT.NONE);
		lblRaspoloivost_.setLocation(15, 103);
		lblRaspoloivost_.setSize(73, 13);
		lblRaspoloivost_.setText("Raspolo\u017Eivost:");

		Combo comboRaspolozivost_ = new Combo(grpVanostImovine, SWT.NONE);
		comboRaspolozivost_.setLocation(90, 100);
		comboRaspolozivost_.setSize(180, 21);
		comboRaspolozivost_.setItems(new String[]{"1-Nije važna (do 72 sata)","2-Nije vrlo važna (do 48 sati)","3-Važna (do 8 sati)","4-Vrlo važna ( do 1 sat)","5-Ekstremno važna (kritièna bez odgode)"});

		Label lblBi_ = new Label(grpVanostImovine, SWT.NONE);
		lblBi_.setLocation(15, 133);
		lblBi_.setSize(65, 13);
		lblBi_.setText("P. Utjecaj:");

		Combo comboBi_ = new Combo(grpVanostImovine, SWT.NONE);
		comboBi_.setLocation(90, 130);
		comboBi_.setSize(180, 21);
		comboBi_.setItems(new String[]{"1-Nije važna","2-Nije vrlo važna","3-Važna","4-Kritièna","5-Vrlo kritièna"});

		Label lblObjanjenjeostalo_ = new Label(grpVanostImovine, SWT.NONE);
		lblObjanjenjeostalo_.setText("Obja\u0161njenje (ostalo):");
		lblObjanjenjeostalo_.setBounds(15, 163, 127, 13);

		textObjanjenjeostalo_ = new Text(grpVanostImovine, SWT.BORDER);
		textObjanjenjeostalo_.setBounds(10, 180, 308, 92);
		
		

		Button btnDescriptionPov_ = new Button(grpVanostImovine, SWT.NONE);
		btnDescriptionPov_.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
		btnDescriptionPov_.setBounds(276, 40, 42, 23);
		btnDescriptionPov_.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
					new HelpDialog(mParent.getShell(), 1);
				}

			
		});
		
		
		
		

		Button btnDescriptionCje_ = new Button(grpVanostImovine, SWT.NONE);
		btnDescriptionCje_.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
		btnDescriptionCje_.setBounds(276, 70, 42, 23);
		btnDescriptionCje_.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
					new HelpDialog(mParent.getShell(), 2);
				}

			
		});

		Button btnDescriptionRas_ = new Button(grpVanostImovine, SWT.NONE);
		btnDescriptionRas_.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
		btnDescriptionRas_.setBounds(276, 100, 42, 23);
		btnDescriptionRas_.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
					new HelpDialog(mParent.getShell(), 3);
				}

			
		});

		Button btnDescriptionBi_ = new Button(grpVanostImovine, SWT.NONE);
		btnDescriptionBi_.setImage(ResourceManager.getPluginImage(
				"hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
		btnDescriptionBi_.setBounds(276, 130, 42, 23);
		btnDescriptionBi_.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
					new HelpDialog(mParent.getShell(), 4);
				}

			
		});

		Composite compositeButtons_ = new Composite(mParent, SWT.NONE);
		compositeButtons_.setBounds(506, 315, 235, 33);
		compositeButtons_.setLayout(new GridLayout(2, false));

		Button btnSpremi_ = new Button(compositeButtons_, SWT.NONE);
		//btnSpremi_.setBounds(518, 324, 100, 23);
		GridData gd_btnSpremi_ = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setText("Spremi");
		btnSpremi_.setLayoutData(gd_btnSpremi_);


		Button btnIzlaz_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnIzlaz_ = new GridData(SWT.RIGHT, SWT.CENTER, true,
				false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// MWindow window = new Assets1();
				mParent.getShell().close();

			}

		});
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dirty.setDirty(true);

			}
		});


		//**********compositeLeft layout
		FormData co_compositeLeft = new FormData();
		co_compositeLeft.right = new FormAttachment(grpVanostImovine, -15);
		co_compositeLeft.left = new FormAttachment(0);
		co_compositeLeft.bottom = new FormAttachment(90, 0);
		co_compositeLeft.top = new FormAttachment(0);
		compositeLeft.setLayoutData(co_compositeLeft);

		//**********lblNaziv_ layout
		FormData data = new FormData();
		data.right = new FormAttachment(0, 50);
		data.top = new FormAttachment(0, 13);
		data.left = new FormAttachment(0, 15);
		lblNaziv_.setLayoutData(data);

		//************textNaziv_ layout
		FormData data1 = new FormData();
		data1.right = new FormAttachment(100, -5);
		data1.top = new FormAttachment(0, 10);
		data1.left = new FormAttachment(0, 125);
		textNaziv_.setLayoutData(data1);

		//**********lblKategorija_ layout
		FormData data2 = new FormData();
		data2.right = new FormAttachment(0, 73);
		data2.top = new FormAttachment(0, 43);
		data2.left = new FormAttachment(0, 15);
		lblKategorija_.setLayoutData(data2);

		//**********comboKateg_ layout
		FormData fd_comboKateg_ = new FormData();
		fd_comboKateg_.right = new FormAttachment(100, -70);
		fd_comboKateg_.top = new FormAttachment(0, 40);
		fd_comboKateg_.left = new FormAttachment(0, 125);
		comboKateg_.setLayoutData(fd_comboKateg_);

		//**********lblPodkateg_ layout
		FormData fd_lblPodkateg_ = new FormData();
		fd_lblPodkateg_.right = new FormAttachment(0, 88);
		fd_lblPodkateg_.top = new FormAttachment(0, 73);
		fd_lblPodkateg_.left = new FormAttachment(0, 15);
		lblPodkateg_.setLayoutData(fd_lblPodkateg_);

		//**********comboPodkateg_ layout
		FormData fd_comboPodkateg_ = new FormData();
		fd_comboPodkateg_.right = new FormAttachment(100, -50);
		fd_comboPodkateg_.top = new FormAttachment(0, 70);
		fd_comboPodkateg_.left = new FormAttachment(0, 125);
		comboPodkateg_.setLayoutData(fd_comboPodkateg_);

		//**********lblNoisteljorgjed_ layout
		FormData fd_lblNoisteljorgjed_ = new FormData();
		fd_lblNoisteljorgjed_.right = new FormAttachment(0, 110);
		fd_lblNoisteljorgjed_.top = new FormAttachment(0, 103);
		fd_lblNoisteljorgjed_.left = new FormAttachment(0, 15);
		lblNoisteljorgjed_.setLayoutData(fd_lblNoisteljorgjed_);

		//**********comboNoisteljorgjed_ layout
		FormData fd_comboNoisteljorgjed_ = new FormData();
		fd_comboNoisteljorgjed_.right = new FormAttachment(100, -5);
		fd_comboNoisteljorgjed_.top = new FormAttachment(0, 100);
		fd_comboNoisteljorgjed_.left = new FormAttachment(0, 125);
		comboNoisteljorgjed_.setLayoutData(fd_comboNoisteljorgjed_);

		//**********lblPoslovniProces_ layout
		FormData fd_lblPoslovniProces_ = new FormData();
		fd_lblPoslovniProces_.right = new FormAttachment(0, 110);
		fd_lblPoslovniProces_.top = new FormAttachment(0, 133);
		fd_lblPoslovniProces_.left = new FormAttachment(0, 15);
		lblPoslovniProces_.setLayoutData(fd_lblPoslovniProces_);

		//**********comboPoslovniProces_ layout
		FormData fd_comboPoslovniProces_ = new FormData();
		fd_comboPoslovniProces_.right = new FormAttachment(100, -5);
		fd_comboPoslovniProces_.top = new FormAttachment(0, 130);
		fd_comboPoslovniProces_.left = new FormAttachment(0, 125);
		comboPoslovniProces_.setLayoutData(fd_comboPoslovniProces_);

		//**********lblOpis_ layout
		FormData fd_lblOpis_ = new FormData();
		fd_lblOpis_.right = new FormAttachment(0, 50);
		fd_lblOpis_.top = new FormAttachment(0, 163);
		fd_lblOpis_.left = new FormAttachment(0, 15);
		lblOpis_.setLayoutData(fd_lblOpis_);

		//**********textOpis_ layout
		FormData fd_textOpis_ = new FormData();
		fd_textOpis_.bottom = new FormAttachment(100, 0);
		fd_textOpis_.right = new FormAttachment(100, -5);
		fd_textOpis_.top = new FormAttachment(0, 180);
		fd_textOpis_.left = new FormAttachment(0, 15);
		textOpis_.setLayoutData(fd_textOpis_);

		//**********grpVanostImovine layout
		FormData fd_grpVanostImovine = new FormData();
		fd_grpVanostImovine.top = new FormAttachment(0);
		//fd_grpVanostImovine.bottom = new FormAttachment(100, -72);
		//fd_grpVanostImovine.left = new FormAttachment(compositeLeft, 5);
		fd_grpVanostImovine.right = new FormAttachment(100, 0);
		grpVanostImovine.setLayoutData(fd_grpVanostImovine);

		//**********textObjanjenjeostalo_ layout
		FormData fd_textObjanjenjeostalo = new FormData();
		fd_textObjanjenjeostalo.top = new FormAttachment(comboBi_,0);
		fd_textObjanjenjeostalo.bottom = new FormAttachment(100,0);
		fd_textObjanjenjeostalo.right = new FormAttachment(100,0);
		textObjanjenjeostalo_.setLayoutData(fd_textObjanjenjeostalo);


		// **********compositeButtons_ layout
		FormData data6 = new FormData();
		data6.top = new FormAttachment(grpVanostImovine, 34);
		data6.right = new FormAttachment(100,0);
		// data6.bottom = new FormAttachment(100, 0);
		compositeButtons_.setLayoutData(data6);

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
