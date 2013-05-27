package hr.ante.isms.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

public class CopyOfAssets1 {

	private Text textNaziv_;
	private Text textOpis_;
	private Text textObjanjenjeostalo_;
	private Text textRokuvanjaBanka_;
	private Text textZakonskiRok_;

	 @Inject
	  MDirtyable dirty;



	@PostConstruct
	public void createComposite(final Composite parent) {
//		final Shell s = new Shell();
//		s.setSize(759, 384);
//		s.setText("Unos Nove Imovine");
//		s.setLayout(null);


		parent.setLayout(null);
		parent.getShell().setMinimumSize(759, 384);
		parent.getShell().setText("Unesi novu imovinu");
		parent.getShell().setSize(759, 384);

		Composite compositeLeft = new Composite(parent, SWT.NONE);
		compositeLeft.setBounds(5, 10, 392, 290);
		compositeLeft.setLayout(null);

		textNaziv_ = new Text(compositeLeft, SWT.BORDER);
		textNaziv_.setBounds(125, 10, 257, 19);

		Label lblNaziv_ = new Label(compositeLeft, SWT.NONE);
		lblNaziv_.setBounds(15, 13, 35, 13);
		lblNaziv_.setText("Naziv:");

		Label lblKategorija_ = new Label(compositeLeft, SWT.NONE);
		lblKategorija_.setText("Kategorija:");
		lblKategorija_.setBounds(15, 43, 58, 13);

		Combo comboKateg_ = new Combo(compositeLeft, SWT.NONE);
		comboKateg_.setBounds(125, 40, 134, 21);

		Label lblPodkateg_ = new Label(compositeLeft, SWT.NONE);
		lblPodkateg_.setText("Podkategorija:");
		lblPodkateg_.setBounds(15, 73, 73, 13);

		Combo comboPodkateg_ = new Combo(compositeLeft, SWT.NONE);
		comboPodkateg_.setBounds(125, 70, 195, 21);

		Label lblNoisteljorgjed_ = new Label(compositeLeft, SWT.NONE);
		lblNoisteljorgjed_.setText("Nositelj (org.jed.):");
		lblNoisteljorgjed_.setBounds(15, 103, 95, 13);

		Combo comboNoisteljorgjed_ = new Combo(compositeLeft, SWT.NONE);
		comboNoisteljorgjed_.setBounds(125, 100, 257, 21);

		Label lblPoslovniProces_ = new Label(compositeLeft, SWT.NONE);
		lblPoslovniProces_.setText("Poslovni proces:");
		lblPoslovniProces_.setBounds(15, 133, 95, 13);

		Combo comboPoslovniProces_ = new Combo(compositeLeft, SWT.NONE);
		comboPoslovniProces_.setBounds(125, 130, 257, 21);

		textOpis_ = new Text(compositeLeft, SWT.BORDER);
		textOpis_.setBounds(15, 180, 358, 109);

		Label lblOpis_ = new Label(compositeLeft, SWT.NONE);
		lblOpis_.setText("Opis:");
		lblOpis_.setBounds(15, 163, 35, 13);

		Group grpVanostImovine = new Group(parent, SWT.NONE);
		grpVanostImovine.setText("Va\u017Enost Imovine");
		grpVanostImovine.setLocation(413, 10);
		grpVanostImovine.setSize(328, 253);

				Label lblRaspoloivost_ = new Label(grpVanostImovine, SWT.NONE);
				lblRaspoloivost_.setLocation(15, 103);
				lblRaspoloivost_.setSize(73, 13);
				lblRaspoloivost_.setText("Raspolo\u017Eivost:");

						Combo comboRaspolozivost_ = new Combo(grpVanostImovine, SWT.NONE);
						comboRaspolozivost_.setLocation(90, 40);
						comboRaspolozivost_.setSize(180, 21);

								Label lblCjelovitost = new Label(grpVanostImovine, SWT.NONE);
								lblCjelovitost.setLocation(15, 73);
								lblCjelovitost.setSize(65, 13);
								lblCjelovitost.setText("Cjelovitost:");

										Combo comboCjelovitost_ = new Combo(grpVanostImovine, SWT.NONE);
										comboCjelovitost_.setLocation(90, 70);
										comboCjelovitost_.setSize(180, 21);

												Label lblOstalo_ = new Label(grpVanostImovine, SWT.NONE);
												lblOstalo_.setLocation(15, 133);
												lblOstalo_.setSize(65, 13);
												lblOstalo_.setText("Ostalo:");

														Combo comboOstalo_ = new Combo(grpVanostImovine, SWT.NONE);
														comboOstalo_.setLocation(90, 100);
														comboOstalo_.setSize(180, 21);

																Label lblPovjerljivost_ = new Label(grpVanostImovine, SWT.NONE);
																lblPovjerljivost_.setLocation(15, 43);
																lblPovjerljivost_.setSize(73, 13);
																lblPovjerljivost_.setText("Povjerljivost:");

																		Combo comboPovjerljivost_ = new Combo(grpVanostImovine, SWT.NONE);
																		comboPovjerljivost_.setLocation(90, 130);
																		comboPovjerljivost_.setSize(180, 21);

																		Label lblObjanjenjeostalo_ = new Label(grpVanostImovine, SWT.NONE);
																		lblObjanjenjeostalo_.setText("Obja\u0161njenje (ostalo):");
																		lblObjanjenjeostalo_.setBounds(15, 163, 127, 13);

																		textObjanjenjeostalo_ = new Text(grpVanostImovine, SWT.BORDER);
																		textObjanjenjeostalo_.setBounds(10, 180, 308, 65);

																		Button btnDescriptionPov_ = new Button(grpVanostImovine, SWT.NONE);
																		btnDescriptionPov_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
																		btnDescriptionPov_.setBounds(276, 40, 42, 23);

																		Button btnDescriptionCje_ = new Button(grpVanostImovine, SWT.NONE);
																		btnDescriptionCje_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
																		btnDescriptionCje_.setBounds(276, 70, 42, 23);

																		Button btnDescriptionRas_ = new Button(grpVanostImovine, SWT.NONE);
																		btnDescriptionRas_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
																		btnDescriptionRas_.setBounds(276, 100, 42, 23);

																		Button btnDescriptionOst_ = new Button(grpVanostImovine, SWT.NONE);
																		btnDescriptionOst_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/gnome_dialog_question (1).png"));
																		btnDescriptionOst_.setBounds(276, 130, 42, 23);

		Button btnIzlaz_ = new Button(parent, SWT.NONE);
		btnIzlaz_.setBounds(625, 324, 116, 23);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				//MWindow window = new Assets1();
				parent.getShell().close();

			}

		});

		Button btnSpremi_ = new Button(parent, SWT.NONE);
		btnSpremi_.setBounds(518, 324, 100, 23);
		btnSpremi_.setText("Spremi");
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				 dirty.setDirty(true);


			}
		});

		Composite compositeDown_ = new Composite(parent, SWT.NONE);
		compositeDown_.setBounds(423, 269, 318, 40);

		Label lblRokuvanjaBanka_ = new Label(compositeDown_, SWT.NONE);
		lblRokuvanjaBanka_.setText("Rok \u010Duvanja Banka (godina):");
		lblRokuvanjaBanka_.setBounds(10, 0, 144, 13);

		textRokuvanjaBanka_ = new Text(compositeDown_, SWT.BORDER);
		textRokuvanjaBanka_.setBounds(10, 20, 83, 19);

		Label lblZakonskiRok_ = new Label(compositeDown_, SWT.NONE);
		lblZakonskiRok_.setText("Zakonski Rok:");
		lblZakonskiRok_.setBounds(203, 0, 144, 13);

		textZakonskiRok_ = new Text(compositeDown_, SWT.BORDER);
		textZakonskiRok_.setBounds(202, 20, 83, 19);



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
		textNaziv_.setFocus();
	}

}
