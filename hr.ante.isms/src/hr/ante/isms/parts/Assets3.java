package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.connection.DatabaseConnectionDoma;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.wb.swt.ResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

import com.ibm.icu.lang.UCharacter.WordBreak;

import de.kupzog.ktable.KTableSortedModel;

public class Assets3 {

	private DataFromDatabase dB;
	private KTableSortedModel m_Model;
	private NewASKTable1 m_Table;
	private int m_Row;
	private String m_AssetId;
	private int action=1;
	private Composite mParent;
	private Text textNaziv_;
	private Text textOpis_;
	private Text textObjanjenjeostalo_;
	private Combo comboKateg_;
	private Combo comboPodkateg_ ;
	private Combo comboNoisteljorgjed_;
	private Combo comboPovjerljivost_;
	private Combo comboCjelovitost_;
	private Combo comboRaspolozivost_ ;
	private Combo comboBi_;
	private String Objanjenjeostalo_;
	public static int assetPartVisible = 0;


	@Inject
	protected EPartService partService;

	@PostConstruct
	public void createComposite(final Composite parent, IEclipseContext context) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.V_SCROLL | SWT.H_SCROLL);
		scrollBox.setMinHeight(349);
		scrollBox.setMinWidth(650);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		parent.getShell().setSize(759, 389);

		FormLayout layout = new FormLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		mParent.setLayout(layout);

		parent.getShell().setText("Unesi novu imovinu");

		m_Table = NewASKTable1.m_TableAsset;
		m_Model = DataFromServer.listAssetASKTableModel;
		m_Row = NewASKTable1.clickedAssetRow;
		dB = new DataFromDatabase();

		Composite compositeLeft = new Composite(mParent, SWT.NONE);
		compositeLeft.setLayout(new FormLayout());
		compositeLeft.setBounds(5, 10, 392, 299);

		Label lblNaziv_ = new Label(compositeLeft, SWT.NONE);
		lblNaziv_.setText("Naziv:");

		textNaziv_ = new Text(compositeLeft, SWT.BORDER);

		Label lblKategorija_ = new Label(compositeLeft, SWT.NONE);
		lblKategorija_.setText("Kategorija:");

		comboKateg_ = new Combo(compositeLeft, SWT.READ_ONLY);
		comboPodkateg_ = new Combo(compositeLeft, SWT.READ_ONLY);
		comboPodkateg_.setEnabled(false);

		Label lblPodkateg_ = new Label(compositeLeft, SWT.NONE);
		lblPodkateg_.setText("Podkategorija:");


		Label lblNoisteljorgjed_ = new Label(compositeLeft, SWT.NONE);
		lblNoisteljorgjed_.setText("Nositelj (org.jed.):");

		comboNoisteljorgjed_ = new Combo(compositeLeft, SWT.NONE);

		textOpis_ = new Text(compositeLeft, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);

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

		comboPovjerljivost_ = new Combo(grpVanostImovine, SWT.READ_ONLY);
		comboPovjerljivost_.setLocation(90, 40);
		comboPovjerljivost_.setSize(180, 21);

		Label lblCjelovitost = new Label(grpVanostImovine, SWT.NONE);
		lblCjelovitost.setLocation(15, 73);
		lblCjelovitost.setSize(65, 13);
		lblCjelovitost.setText("Cjelovitost:");

		comboCjelovitost_ = new Combo(grpVanostImovine, SWT.READ_ONLY);
		comboCjelovitost_.setLocation(90, 70);
		comboCjelovitost_.setSize(180, 21);


		Label lblRaspoloivost_ = new Label(grpVanostImovine, SWT.NONE);
		lblRaspoloivost_.setLocation(15, 103);
		lblRaspoloivost_.setSize(73, 13);
		lblRaspoloivost_.setText("Raspolo\u017Eivost:");

		comboRaspolozivost_ = new Combo(grpVanostImovine, SWT.READ_ONLY);
		comboRaspolozivost_.setLocation(90, 100);
		comboRaspolozivost_.setSize(180, 21);


		Label lblBi_ = new Label(grpVanostImovine, SWT.NONE);
		lblBi_.setLocation(15, 133);
		lblBi_.setSize(65, 13);
		lblBi_.setText("P. Utjecaj:");

		comboBi_ = new Combo(grpVanostImovine, SWT.READ_ONLY);
		comboBi_.setLocation(90, 130);
		comboBi_.setSize(180, 21);


		Label lblObjanjenjeostalo_ = new Label(grpVanostImovine, SWT.NONE);
		lblObjanjenjeostalo_.setText("Obja\u0161njenje (Poslovni utjecaj):");
		lblObjanjenjeostalo_.setBounds(15, 163, 165, 13);

		textObjanjenjeostalo_ = new Text(grpVanostImovine, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
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
		btnSpremi_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
//				dirty.setDirty(true);
				saveAction();
				action=2;


			}
		});
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
				mParent.getShell().close();
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

		//**********lblOpis_ layout
		FormData fd_lblOpis_ = new FormData();
		fd_lblOpis_.bottom = new FormAttachment(textOpis_, -6);
		fd_lblOpis_.left = new FormAttachment(lblNaziv_, 0, SWT.LEFT);
		fd_lblOpis_.right = new FormAttachment(0, 50);
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

		fillForm();

		scrollBox.setContent(mParent);
		mParent.getShell().setDefaultButton(btnSpremi_);


	}

	private void fillForm() {
		// TODO Auto-generated method stub

		

		initialSettings();
		

		if(m_Row!=0/* && !m_Table.m_Selection.isEmpty()*/ ){
			
					
			action=2;
			comboPodkateg_.setEnabled(true);
			m_AssetId=m_Model.getContentAt(10, m_Row).toString();

			textNaziv_.setText(m_Model.getContentAt(2, m_Row).toString());

			String subcategoryId = m_Model.getContentAt(3, m_Row).toString();
			String ownerId= m_Model.getContentAt(4, m_Row).toString();
			String confId= m_Model.getContentAt(5, m_Row).toString();
			String integId= m_Model.getContentAt(6, m_Row).toString();
			String accessId= m_Model.getContentAt(7, m_Row).toString();
			String bimpactId= m_Model.getContentAt(8, m_Row).toString();

			String subcategory = dB.getDesiredColumnFromDB("view_asset", "category_name","WHERE asset_id=" + m_AssetId + "");
			String owner = dB.getDesiredColumnFromDB("view_asset", "owner_name","WHERE asset_id=" + m_AssetId + "");
			
			String confidentiality = dB.getDesiredColumnFromDB("view_asset", "confidentiality","WHERE asset_id=" + m_AssetId + "");
			String integrity = dB.getDesiredColumnFromDB("view_asset", "integrity","WHERE asset_id=" + m_AssetId + "");
			String accessibility = dB.getDesiredColumnFromDB("view_asset", "accessibility","WHERE asset_id=" + m_AssetId + "");
			String businessImpact = dB.getDesiredColumnFromDB("view_asset", "businessimpact","WHERE asset_id=" + m_AssetId + "");

			comboPodkateg_.setItems(dB.getComboItemsFromDB("as_subcateg", "WHERE assubcateg_id LIKE '"+subcategoryId.substring(0,2)+"%'"));

			comboPodkateg_.setText(subcategoryId+"-"+subcategory);

			String categoryId = subcategoryId.substring(0,2);
			String category = dB.getDesiredColumnFromDB("as_asset_type","name", "WHERE assettype_id="+categoryId+"");
			
			comboKateg_.setText(categoryId+"-"+category);
			if(owner==null)
				comboNoisteljorgjed_.setText("");
			else
				comboNoisteljorgjed_.setText(ownerId+"-"+owner);
			comboPovjerljivost_.setText(confId+"-"+confidentiality);
			comboCjelovitost_.setText(integId+"-"+integrity);
			comboRaspolozivost_.setText(accessId+"-"+accessibility);
			comboBi_.setText(bimpactId+"-"+businessImpact);

			String Opis_ = dB.getDesiredColumnFromDB("view_asset", "description", "WHERE asset_id=" + m_AssetId + "");
			if(Opis_==null)
				Opis_ = "";
			else
				textOpis_.setText(Opis_);

			Objanjenjeostalo_= dB.getDesiredColumnFromDB("view_asset", "bi_description", "WHERE asset_id=" + m_AssetId + "");
			if(Objanjenjeostalo_==null)
				Objanjenjeostalo_ = "";
			else
				textObjanjenjeostalo_.setText(Objanjenjeostalo_);

		}
		else
		{
			action=1;
			assetPartVisible=0;
		}





	}

	private void initialSettings(){
		action=1;
		comboKateg_.setItems(dB.getComboItemsFromDB("as_asset_type"));
		comboKateg_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(comboKateg_.getSelectionIndex());
				int index = comboKateg_.getSelectionIndex()+1;
				comboPodkateg_.setEnabled(true);
//				if(comboKateg_.getText()!="")
//					comboPodkateg_.setText(getTextFromDB("as_subcateg", "WHERE assubcateg_id ="+comboKateg_.getText().substring(0,2)+10+""));
				comboPodkateg_.setItems(dB.getComboItemsFromDB("as_subcateg", "WHERE assubcateg_id LIKE '"+index+"%'"));
			}
		});
		
		comboNoisteljorgjed_.setItems(dB.getComboItemsFromDB("as_owner"));
		comboPovjerljivost_.setItems(dB.getComboItemsFromDB("as_confidentiality"));
		comboCjelovitost_.setItems(dB.getComboItemsFromDB("as_integrity"));
		comboRaspolozivost_.setItems(dB.getComboItemsFromDB("as_accessibility", "Combo"));
		comboBi_.setItems(dB.getComboItemsFromDB("as_business_impact"));
	}

	private void saveAction(){

		if (textNaziv_.getText() != "" && textNaziv_.getText().length() > 0
				&& comboPodkateg_.getText() != ""
				&& comboPodkateg_.getText().length() > 0
				&& comboPovjerljivost_.getText() != ""
				&& comboPovjerljivost_.getText().length() > 0
				&& comboCjelovitost_.getText() != ""
				&& comboCjelovitost_.getText().length() > 0
				&& comboRaspolozivost_.getText() != ""
				&& comboRaspolozivost_.getText().length() > 0
				&& comboBi_.getText() != "" && comboBi_.getText().length() > 0) {

			Hashtable<String, String> data = new Hashtable<String, String>();
			data.put("assettype_id", comboKateg_.getText().substring(0,2));
			data.put("name", textNaziv_.getText());
			data.put("category", comboPodkateg_.getText().substring(0,4));			

			if(comboNoisteljorgjed_.getText().equals(""))
				data.put("owner", "");
			else{
				data.put("owner", comboNoisteljorgjed_.getText().substring(0,5));
			}


//			if(textOpis_.getText().equals(""))
//				data.put("description", "");
//			else{
				data.put("description", textOpis_.getText());
//			}
			
			data.put(
					"confidentiality_level",
					comboPovjerljivost_.getText() != null ? comboPovjerljivost_
							.getText().substring(0,1) : "");
			data.put(
					"integrity_level",
					comboCjelovitost_.getText() != null ? comboCjelovitost_
							.getText().substring(0,1) : "");
			data.put(
					"accessibility_level",
					comboRaspolozivost_.getText() != null ? comboRaspolozivost_
							.getText().substring(0,1) : "");
			data.put("businessimpact_level",
					comboBi_.getText() != null ? comboBi_.getText().substring(0,1) : "");

//			if(textObjanjenjeostalo_.getText().equals(""))
//				data.put("bi_description", "");
//			else{
				data.put("bi_description", textObjanjenjeostalo_.getText());
//			}




			System.out.println("Hashtable" + data);
			try {
				if (action == 2) {
					dB.insertDataInDB("as_asset", data, "update","Assets", m_AssetId);

				} else
					dB.insertDataInDB("as_asset", data, "insert","Assets", "");

			} catch (Exception e1) {
				e1.printStackTrace();

			}
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/tick.png"), "Spremanje uspješno",
					"Podaci su spremljeni", NotifierTheme.GREEN_THEME);

		} else
			Notifier.notify(ResourceManager.getPluginImage("hr.ante.isms",
					"src/icons/error.ico"), "Nemože se spremiti",
					"Niste unijeli sve potrebno podatke",
					NotifierTheme.RED_THEME);

		refreshTable();
	}

	private void refreshTable(){
		((ListAssetASKTableModel)m_Model).readAllFromDB();
		m_Table.redraw();
	}


//	public String[] getComboItemsFromDB(String tableName){
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//				return con.getComboItems(tableName);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String[]{};
//
//	}
//
//	public String[] getComboItemsFromDB(String tableName, String whereStatement){
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getComboItemsWithWhere(tableName, whereStatement);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String[]{};
//
//	}

//	public String getTextFromDB(String tableName, String whereStatement){
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getTextWithWhere(tableName, whereStatement);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String();
//
//	}

//	public String getDesiredColumnFromDB(String tableName, String columnName, String whereStatement){
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			return con.getContentFromDesiredColumn(tableName, columnName, whereStatement);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return new String();
//
//	}

	public String getDescriptionFromDB(String tableName, String where){
		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
		con.doConnection();

		try {

			if(tableName=="as_asset")
				return con.getAssetDescription(tableName, where);
			if(tableName=="as_business_impact")
				return con.getCiaDescription(tableName, where);


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
		return new String();

	}

//	public void insertDataInDB(String tableName, Hashtable data, String updateOrInsert, String id) throws Exception{
//		DatabaseConnectionDoma con = new DatabaseConnectionDoma();
//		con.doConnection();
//
//		try {
//
//			if(updateOrInsert=="insert")
//				con.insertAssetData(tableName, data);
//			if(updateOrInsert=="update")
//				con.updateAssetData(tableName, data, id);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//
//	}

//	public void updateDataInDB(String tableName, Hashtable data, String id) throws Exception{
//		DatabaseConnection con = new DatabaseConnection();
//		con.doConnection();
//
//		try {
//
//			con.updateAssetData(tableName, data, id);
//
//		} catch (SQLException ex) {
//			System.out.println(ex.getMessage());
//			try {
//				con.connection.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//		System.out.println("Connection : " + con.doConnection());
//		try {
//			con.connection.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//
//	}




	@PreDestroy
	public void dispose() throws Exception {
	  System.out.println("Closing application");
	}


	@Focus
	public void setFocus() {
		mParent.setFocus();
	}

}
