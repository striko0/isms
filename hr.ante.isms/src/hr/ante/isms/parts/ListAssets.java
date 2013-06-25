package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.parts.table.ListAssetASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;
import org.mihalis.opal.utils.SWTGraphicUtil;

import com.ibm.icu.text.DateFormat;

import de.kupzog.ktable.KTableSortedModel;



public class ListAssets implements ViewSelected{


	private int m_Row;
	private KTableSortedModel m_Model;
	private DataFromDatabase dB;

	@Inject IStylingEngine engine;
    @Inject private MApplication app;

    @Inject
	private	IEclipseContext context;
    // create images for toolbar buttons


	@Inject
	protected EPartService partService;

    private Composite mParent;
    private NewASKTable1 table;

	@PostConstruct
	public void createComposite(final Composite parent) {

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.H_SCROLL | SWT.V_SCROLL);
		scrollBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrollBox.setBounds(0, 0, 490, 537);
		scrollBox.setMinHeight(470);
		scrollBox.setMinWidth(500);


		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);

		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));

		mParent.getShell().setText("Upravljanje Rizicima");
		GridLayout gl_mParent = new GridLayout(1, false);
		gl_mParent.verticalSpacing = 0;
		gl_mParent.horizontalSpacing = 0;
		gl_mParent.marginWidth = 0;
		gl_mParent.marginHeight = 0;
		mParent.setLayout(gl_mParent);

		m_Row = NewASKTable1.clickedAssetRow;
		dB = new DataFromDatabase();

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite.heightHint = 50;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(4, false));

				Label naslov_ = new Label (composite, SWT.NONE);
				GridData gd_naslov_ = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				gd_naslov_.widthHint = 178;
				naslov_.setLayoutData(gd_naslov_);
				naslov_.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
				naslov_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
				naslov_.setFont(SWTResourceManager.getFont("Georgia", 18, SWT.BOLD));
				naslov_.setText("Imovina");

								Button btnIspis_ = new Button(composite, SWT.NONE);
								btnIspis_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								GridData gd_btnIspis_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
								gd_btnIspis_.heightHint = 40;
								gd_btnIspis_.widthHint = 40;
								btnIspis_.setLayoutData(gd_btnIspis_);
								btnIspis_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/filetype_pdf.png"));

								Button btnDelete_ = new Button(composite, SWT.NONE);
								btnDelete_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								btnDelete_.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {

										if (m_Row == 0) {
											Notifier.notify(ResourceManager.getPluginImage(
													"hr.ante.isms", "src/icons/error.ico"), "Problem",
													"Morate odabrati imovinu", NotifierTheme.RED_THEME);
										}
										else{
											boolean confirm = Dialog.isConfirmed("Je ste li sigurni da želite obrisati imovinu?", "Imovina æe biti obrisana");
										if (confirm) {
											try {
												dB.deleteDataFromDB("as_asset", "asset_id", table.getModel().getContentAt(1, m_Row).toString());
												((ListAssetASKTableModel)table.getModel()).readAllFromDB();
												table.redraw();

											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
										}
									}
								});
								GridData gd_btnDelete_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
								gd_btnDelete_.widthHint = 40;
								gd_btnDelete_.heightHint = 40;
								btnDelete_.setLayoutData(gd_btnDelete_);
								btnDelete_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/deletered.png"));
								
									
								Button btnRefresh_ = new Button(composite, SWT.NONE);
								btnRefresh_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
								btnRefresh_.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										table.redraw();
									}
								});
								GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
								gd_btnNewButton.widthHint = 40;
								gd_btnNewButton.heightHint = 40;
								btnRefresh_.setLayoutData(gd_btnNewButton);
								btnRefresh_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/table_refresh.png"));


								btnIspis_.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										HashMap hm = new HashMap();
										try {

											Connection connection = null;
											String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//											String url = "jdbc:sqlserver://192.168.0.76"/* 192.168.0.70 */;
											String url = "jdbc:sqlserver://127.0.0.1:1433;integratedSecurity=true"; 
//											String username = "sa"; // You should modify this.
//											String password = "sa"; // You should modify this.
											// Load the JDBC driver
											try {
												Class.forName(driverName);
											} catch (ClassNotFoundException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											// Create a connection to the database
											try {
//												connection = DriverManager.getConnection(url, username,
//														password);
												connection = DriverManager.getConnection(url);
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}

											JasperPrint print = JasperFillManager.fillReport("C:/Reports/registar_imovine.jasper", hm, /*new JREmptyDataSource()*/connection);
											JasperViewer.viewReport(print,false);

										} catch (JRException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									}
								});

		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.minimumHeight = 100;
		gd_compositeASKTable.widthHint = 778;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		table = new NewASKTable1(this,compositeASKTable, new ListAssetASKTableModel(),
				717, compositeASKTable.getBounds().height);

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
		GridData gd_labelUserDatum_ = new GridData(SWT.RIGHT, SWT.TOP, true,
				false, 1, 1);
		gd_labelUserDatum_.widthHint = 150;
		labelUserDatum_.setLayoutData(gd_labelUserDatum_);
//		labelUserDatum_.setText("Petak, 21. Lipanj 2013");
		labelUserDatum_.setText(getCurrentDate());
		labelUserDatum_.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		new Label(compositeUser_, SWT.NONE);
		new Label(compositeUser_, SWT.NONE);
		new Label(compositeUser_, SWT.NONE);

		scrollBox.setContent(mParent);


	}
	
	public String getCurrentDate()
	{
		Locale currentLocale = new Locale("hr","HR");
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL,currentLocale);
		Date today = new Date();
		String dateOut = dateFormatter.format(today);
		return dateOut;
		
	}

	@PreDestroy
	public void dispose() throws Exception {
	  System.out.println("Closing ListAssets");
	}


	@Override
	public void rowSelected(int row) {

		m_Row = row;
		// TODO Auto-generated method stub
//		if (row!=0)
//				if(getDesiredColumnFromDB("view_risk","threat_id","WHERE asset_id="+table.getModel().getContentAt(1, m_Row)+"").equals(null))
//		{
//
//		}
	}
}
