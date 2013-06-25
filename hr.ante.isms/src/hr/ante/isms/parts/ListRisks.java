package hr.ante.isms.parts;

import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.handlers.QuitHandler;
import hr.ante.isms.parts.table.ListRiskASKTableModel;
import hr.ante.isms.parts.table.NewASKTable1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.ibm.icu.text.DateFormat;
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
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IStylingEngine;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;
import org.mihalis.opal.opalDialog.Dialog;



public class ListRisks implements ViewSelected {

	private NewASKTable1 table;
	private DataFromDatabase dB;
	private int m_Row;

	@Inject IStylingEngine engine;
    @Inject private MApplication app;

    @Inject
	private	IEclipseContext context;
    // create images for toolbar buttons


    private Composite mParent;



	@PostConstruct
	public void createComposite(final Composite parent) {
	

		final ScrolledComposite scrollBox = new ScrolledComposite(parent,
				SWT.H_SCROLL | SWT.V_SCROLL);
		
		
//		Rectangle bounds = parent.getShell().getDisplay().getBounds();
//		Point point = parent.getShell().getSize();
//		
//		int nLeft = (bounds.width - point.x) /2;
//		int nTop = (bounds.height - point.y) /2;
//		
//		parent.getShell().setBounds(nLeft,nTop,point.x,point.y);
		
		
		scrollBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrollBox.setBounds(0, 0, 490, 537);
		scrollBox.setMinHeight(470);
		scrollBox.setMinWidth(500);

		scrollBox.setExpandHorizontal(true);
		scrollBox.setExpandVertical(true);
		
		mParent = new Composite(scrollBox, SWT.NONE);
		mParent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		mParent.getShell().setText("Upravljanje Rizicima");
		
		
		mParent.getShell().addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				
				boolean end = Dialog.isConfirmed(
						"Želite li završiti rad sa programom?",
						"Odaberite Opciju");
				if (end)
					mParent.getShell().close();
//				 MessageBox messageBox = new MessageBox(mParent.getShell(), SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
//				 messageBox.setText("Odaberite Opciju");
//				 messageBox.setMessage("Želite li završiti rad sa programom?")

			}
		});
		
//		Monitor primary = mParent.getShell().getDisplay().getPrimaryMonitor();
//		Rectangle bounds = primary.getBounds();
//		Rectangle rect = mParent.getShell().getBounds();
//		
//		int x = bounds.x + (bounds.width - rect.width)/2;
//		int y = bounds.y + (bounds.height - rect.height)/2;
//		
//		 mParent.getShell().setLocation(x, y);

		
		m_Row = NewASKTable1.clickedRiskRow;
		dB = new DataFromDatabase();

		GridLayout gl_mParent = new GridLayout(1, false);
		gl_mParent.verticalSpacing = 0;
		gl_mParent.horizontalSpacing = 0;
		gl_mParent.marginWidth = 0;
		gl_mParent.marginHeight = 0;
		mParent.setLayout(gl_mParent);

		Composite composite = new Composite(mParent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite.heightHint = 50;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(4, false));

				Label naslov_ = new Label (composite, SWT.NONE);
				naslov_.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				naslov_.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
				naslov_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
				naslov_.setFont(SWTResourceManager.getFont("Georgia", 18, SWT.BOLD));
				naslov_.setText("Rizici");

				Button btnIspis_ = new Button(composite, SWT.NONE);
				btnIspis_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
				GridData gd_btnIspis_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_btnIspis_.widthHint = 40;
				gd_btnIspis_.heightHint = 40;
				btnIspis_.setLayoutData(gd_btnIspis_);
				btnIspis_.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						HashMap hm = new HashMap();
						try {

							Connection connection = null;
							String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//							String url = "jdbc:sqlserver://192.168.0.76"/* 192.168.0.70 */;
							String url = "jdbc:sqlserver://127.0.0.1:1433;integratedSecurity=true";
////							String username = "sa"; // You should modify this.
//							String password = "sa"; // You should modify this.
							// Load the JDBC driver
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							// Create a connection to the database
							try {
//								connection = DriverManager.getConnection(url, username,
//										password);
								connection = DriverManager.getConnection(url);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

//							JasperPrint print = JasperFillManager.fillReport("C:/Documents and Settings/Zrosko/git/isms/hr.ante.isms/src/reports/lista_rizika.jasper", hm, /*new JREmptyDataSource()*/connection);
							JasperPrint print = JasperFillManager.fillReport("C:/Reports/lista_rizika.jasper", hm, /*new JREmptyDataSource()*/connection);
							
							JasperViewer.viewReport(print,false);

						} catch (JRException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnIspis_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/filetype_pdf.png"));

				Button btnDelete_= new Button(composite, SWT.NONE);
				btnDelete_.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		btnDelete_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (m_Row == 0) {
					Notifier.notify(ResourceManager.getPluginImage(
							"hr.ante.isms", "src/icons/error.ico"), "Problem",
							"Morate odabrati rizik", NotifierTheme.RED_THEME);
				} else {
					boolean confirm = Dialog.isConfirmed(
							"Je ste li sigurni da želite obrisati rizik?",
							"Rizik æe biti obrisan");

					if (confirm) {
						try {
							dB.deleteDataFromDB("as_risk", "risk_id", table
									.getModel().getContentAt(1, m_Row)
									.toString());
							((ListRiskASKTableModel) table.getModel())
									.readAllFromDB();
							table.redraw();

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
				GridData gd_btnDelete_ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_btnDelete_.widthHint = 40;
				gd_btnDelete_.heightHint = 40;
				btnDelete_.setLayoutData(gd_btnDelete_);
				btnDelete_.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/deletered.png"));
				
				Button btnRefresh = new Button(composite, SWT.NONE);
				btnRefresh.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
				GridData gd_btnRefresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_btnRefresh.widthHint = 40;
				gd_btnRefresh.heightHint = 40;
				btnRefresh.setLayoutData(gd_btnRefresh);
				btnRefresh.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						table.redraw();
					}
				});
				btnRefresh.setImage(ResourceManager.getPluginImage("hr.ante.isms", "src/icons/table_refresh.png"));

		Composite compositeASKTable = new Composite(mParent, SWT.NONE);
		compositeASKTable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		compositeASKTable.setLayout(new FillLayout());
		GridData gd_compositeASKTable = new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1);
		gd_compositeASKTable.minimumHeight = 100;
		gd_compositeASKTable.widthHint = 778;
		compositeASKTable.setLayoutData(gd_compositeASKTable);

		table = new NewASKTable1(this,compositeASKTable, new ListRiskASKTableModel(7, 1,""),
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
	  System.out.println("Closing ListRisks");
	  System.exit(0);
	}

	@Focus
	public void setFocus() {
		mParent.setFocus();
	}
	@Override
	public void rowSelected(int row) {
		// TODO Auto-generated method stub
		m_Row = row;

	}
}
