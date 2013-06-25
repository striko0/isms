package hr.ante.isms.parts.table;

import hr.ante.isms.parts.AssetRowSelected;
import hr.ante.isms.parts.Controls;
import hr.ante.isms.parts.ImpactAnalysis;
import hr.ante.isms.parts.ListRisks;
import hr.ante.isms.parts.Probability;
import hr.ante.isms.parts.SuggestMeasures;
import hr.ante.isms.parts.ThreatIdentification;
import hr.ante.isms.parts.Threats;
import hr.ante.isms.parts.ViewSelected;
import hr.ante.isms.parts.Vulnerability;
import hr.ante.isms.parts.VulnerabilityIdentification;

import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.SWTResourceManager;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableActionHandler;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableClickInterceptionListener;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;

public class NewASKTable1 extends KTable{

	public static int clickedRow=0;
	public static int clickedAssetRow=0;
	public static int clickedRiskRow=0;
	public static int clickedVulnerabilityRow = 0;
	public static int clickedThreatRow = 0;
	public static int clickedControlRow = 0;
	public static int clickedControlRiskRow = 0;
	private boolean selection=true;
	private ViewSelected m_View;
	private ViewSelected m_ViewVulnerability;
	private ViewSelected m_ViewThreat;
	private ViewSelected m_ViewRisk;
	private ViewSelected m_ViewAsset;
	private ViewSelected m_ViewControl;
	private ViewSelected m_ViewControlRisk;
	private AssetRowSelected m_AssetRow;
	private int cRow = -1;
	private int cCol = -1;

	@Inject
	protected EPartService partService;

	public static NewASKTable1 m_Table;
	public static NewASKTable1 m_TableAsset;
	public static NewASKTable1 m_TableRisk;
	Label statusLabel;
	KTableSortComparator comparator;
	public static KTableSortedModel model;
	private KTableSortedModel m_Model;
	KTableActionHandler handler;
	Menu menu;
	int sortingColumn = -1;
	KTableCellSelectionListener cellSelListner;


//	@PostConstruct
//	public void createComposite(Composite parent) {
//
//
//		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
//		Composite comp1 = new Composite(parent, SWT.NONE);
//		comp1.setLayout(new FillLayout());



	public NewASKTable1(ViewSelected view, Composite parent, KTableSortedModel givenModel,int widht, int height) {
//		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);
		super(parent,  SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);



		// TODO Auto-generated constructor stub
		if(view instanceof Vulnerability){
			m_ViewVulnerability=view;
		}

		if(view instanceof Threats){
			m_ViewThreat=view;
		}
		
		if(view instanceof Controls){
			m_ViewControl=view;
		}

		if(view instanceof SuggestMeasures){
			m_ViewControlRisk=view;
		}
		
		if(view instanceof ThreatIdentification 
				|| view instanceof VulnerabilityIdentification 
				|| view instanceof Probability
				|| view instanceof ImpactAnalysis
				|| view instanceof ListRisks)
		{
			m_ViewRisk=view;
		}

//		if(view instanceof ListRisks){
//			m_ViewRisk=view;
//		}
//
//		if(view instanceof ListAssets){
//			m_ViewAsset=view;
//		}
		else
			m_View = view;

		if(givenModel instanceof ListAssetASKTableModel)
			m_TableAsset = this;
		if(givenModel instanceof ListRiskASKTableModel)
			m_TableRisk = this;

		else
			m_Table=this;

		Composite comp1 = new Composite(parent, SWT.NONE);
		//comp1.setLayout(new GridLayout(1,false));

		GridLayout gl_comp1 = new GridLayout(1, false);
		gl_comp1.verticalSpacing = 0;
		gl_comp1.horizontalSpacing = 0;
		gl_comp1.marginHeight = 0;
		gl_comp1.marginWidth = 0;
		comp1.setLayout(gl_comp1);
		//comp1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));


//		KTable table = new KTable(comp1, SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL
//				| SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL | SWTX.EDIT_ON_KEY);

		this.setParent(comp1);
		setToolTipText("Tablica");
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		model = givenModel;
		m_Model=givenModel;
		//table.setSize(parent.getSize());

		setFont(SWTResourceManager.getFont("Times New Roman CYR", 15, SWT.NORMAL));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FillLayout());
		setBounds(0,0, widht, height);
		setModel(givenModel);
//		setData("org.eclipse.e4.ui.css.id", "ASKTable");


//		table.addListener(SWT.MouseDown, new HeaderListener2(table));
//		table.addListener(SWT.MouseMove, new HeaderListener2(table));

		


		new ASKTableContextMenu1(this,(KTableSortedModel)this.getModel());

		addTraverseListener(new TraverseListener() {
//
			@Override
			public void keyTraversed(TraverseEvent e) {
//				// TODO Auto-generated method stub
//				if (e.keyCode == SWT.ARROW_DOWN) {
//					cRow=clickedRow;					
//					cRow++;
//					setClickedRow(cRow);
////					removeCellSelectionListener(cellSelListner);
//					updateStatus(cCol, cRow);
//					
//				}
//
//				if (e.keyCode == SWT.ARROW_UP) {
//					cRow=clickedRow;
//					cRow--;
//					setClickedRow(cRow);
//					updateStatus(cCol, cRow);
//				}
//				
				if(e.keyCode == SWT.CTRL)
					selection=false;
//
			}
		});
		
		addClickInterceptionListener(new KTableClickInterceptionListener() {

			@Override
			public boolean cellClicked(int col, int row, Rectangle cellRect,
					int x, int y, int button, KTable table) {

				updateStatus(col, row);

				if(row == 0 && button == 1 && col != 0)
				{


					/**
					 * If clicked on left or right border of cell, do not sort!
					 */
			        Point sensible = new Point(cellRect.width, cellRect.height);
			        Point clickLocation = new Point (x,y);

					Rectangle active = new Rectangle(cellRect.x+10, cellRect.y, sensible.x-20, sensible.y);
					Rectangle cellBoundary = table.getCellRect(col, row);

					if (active.contains(clickLocation))
					{

//						new ASKTableContextMenu(table,(KTableSortedModel)table.getModel());
//						new ASKTableSortOnClick(table,col, row, new ASSortComparatorISMS(model,-1, ASSortComparatorISMS.SORT_UP),ASSortComparatorISMS.SORT_UP, 1);
						new ASKTableSortOnClick(table,col, row, new ASSortComparatorISMS(m_Model,-1, ASSortComparatorISMS.SORT_UP),ASSortComparatorISMS.SORT_UP, 1);
						//new ASKTableSortOnClick(this,m_Col, 0, new ASSortComparatorISMS(m_Model,m_Col, direction),direction, 3);
					}
				}
				if(row != 0 && col != 0)
				{


					cCol=col;
//					if(!table.m_Selection.isEmpty())
//					
				
					// TODO Auto-generated method stub
					setClickedRow(row);
							
				
//					if (selection==true) {
//						if(table.getModel() instanceof ListAssetASKTableModel){
//
//							clickedAssetRow=row;
//						}
//
//						if(table.getModel() instanceof ListRiskASKTableModel){
//
//							clickedRiskRow=row;
//							m_View.rowSelected(row);
//						}
//						clickedRow = row;
//						selection=false;
//					}



				}
				// TODO Auto-generated method stub
				return false;
			}


		});
//
		addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if ((e.stateMask & SWT.CTRL) == 0) {

					selection = false;

				} else
					selection = true;
				}
			



		});
		
		
		
		addCellSelectionListener(new KTableCellSelectionListener() {

			@Override
			public void fixedCellSelected(int col, int row, int statemask) {
				// TODO Auto-generated method stub


			}

			@Override
			public void cellSelected(int col, int row, int statemask) {
				// TODO Auto-generated method stub
				// the idea is to map the row index back to the model index since the given row index
		    	// changes when sorting is done.
			
				int modelRow = model.mapRowIndexToModel(row);
				setClickedRow(row);		
				updateStatus(col, row);
				System.out.println("Cell [" + col + ";" + row
						+ "] selected. - Model row: " + modelRow);
				System.out.println("ovo " + m_Table.m_Selection);


//				new KTableActionHandler(table);
				// table.setMenu(null);
//				new KTableActionHandler(table);
				//new ASKTableContextMenu(table,(KTableSortedModel)table.getModel());
//				if(m_Selection.isEmpty()){
//					clickedRow=0;
//					m_View.rowSelected(0);
//				}
//
//				else{
//					m_View.rowSelected(row);
//					clickedRow = row;
//				}
				//new ASMenu(table,1);
			

			}
		});
//



		addCellResizeListener(new KTableCellResizeListener() {

			@Override
			public void rowResized(int row, int newHeight) {
				System.out.println("Row "+row+" resized to "+newHeight);

			}

			@Override
			public void columnResized(int col, int newWidth) {
				System.out.println("Column "+col+" resized to "+newWidth);


			}
		});

		Composite statusComposite = new Composite(comp1, SWT.BORDER);
		statusComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		statusComposite.setLayout(new GridLayout(2,false));

		GridData gd_statusComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_statusComposite.widthHint = 500;
		gd_statusComposite.heightHint = 20;
		gd_statusComposite.horizontalIndent = 0;
		gd_statusComposite.verticalIndent = 0;
		statusComposite.setLayoutData(gd_statusComposite);

		Label statusLabelSuma = new Label(statusComposite, SWT.NONE);
		statusLabelSuma.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		statusLabelSuma.setToolTipText("Statusna Linija");
		statusLabelSuma.setText("Zbroj: " + (givenModel.doGetRowCount()-1) + " | ");
		statusLabel = new Label(statusComposite, SWT.NONE);
		statusLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_statusLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_statusLabel.widthHint = 93;
		statusLabel.setLayoutData(gd_statusLabel);
		statusLabel.setText("Odabrano: 0");

//		 Listener armListener = new Listener() {
//		      public void handleEvent(Event event) {
//		      //MenuItem item = (MenuItem) event.widget;
//		        label.setText(item.getText());
//		        label.update();
//		      }
//		    };
//		    Listener showListener = new Listener() {
//		      public void handleEvent(Event event) {
////		        Menu menu = (Menu) event.widget;
////		        MenuItem item = menu.getParentItem();
//		        if (item != null) {
//		          label.setText(item.getText());
//		          label.update();
//		        }
//		      }
//		    };
//		    Listener hideListener = new Listener() {
//		      public void handleEvent(Event event) {
//		        label.setText("");
//		        label.update();
//		      }
//		    };

		/**
		 *  Set Excel-like table cursors
		 */

        if ( SWT.getPlatform().equals("win32") ) {

			// Cross

			Image crossCursor = SWTX.loadImageResource(this.getDisplay(), "/icons/cross_win32.gif");

			// Row Resize

			Image row_resizeCursor = SWTX.loadImageResource(this.getDisplay(), "/icons/row_resize_win32.gif");

			// Column Resize

			Image column_resizeCursor  = SWTX.loadImageResource(this.getDisplay(), "/icons/column_resize_win32.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);

			this.setDefaultCursor(new Cursor(this.getDisplay(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			this.setDefaultRowResizeCursor(new Cursor(this.getDisplay(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			this.setDefaultColumnResizeCursor(new Cursor(this.getDisplay(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		} else {

			// Cross

			Image crossCursor      = SWTX.loadImageResource(this.getDisplay(), "/icons/cross.gif");
			Image crossCursor_mask = SWTX.loadImageResource(this.getDisplay(), "/icons/cross_mask.gif");

			// Row Resize

			Image row_resizeCursor      = SWTX.loadImageResource(this.getDisplay(), "/icons/row_resize.gif");
			Image row_resizeCursor_mask = SWTX.loadImageResource(this.getDisplay(), "/icons/row_resize_mask.gif");

			// Column Resize

			Image column_resizeCursor      = SWTX.loadImageResource(this.getDisplay(), "/icons/column_resize.gif");
			Image column_resizeCursor_mask = SWTX.loadImageResource(this.getDisplay(), "/icons/column_resize_mask.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);


			this.setDefaultCursor(new Cursor(this.getDisplay(), crossCursor_mask.getImageData(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			this.setDefaultRowResizeCursor(new Cursor(this.getDisplay(), row_resizeCursor_mask.getImageData(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			this.setDefaultColumnResizeCursor(new Cursor(this.getDisplay(), column_resizeCursor_mask.getImageData(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		}


		}

	private void setClickedRow(int row){
//		cRow=row;
		
			if (selection == true) {
				System.out.println("CLICKDEEEED ROW " + row);
				if (getModel() instanceof ListAssetASKTableModel) {

					clickedAssetRow = row;

					// m_ViewAsset.rowSelected(row);
				}

				if (getModel() instanceof ListVulnerabilityASKTableModel) {

					clickedVulnerabilityRow = row;
					m_ViewVulnerability.rowSelected(row);
				}

				if (getModel() instanceof ListThreatASKTableModel) {

					clickedThreatRow = row;
					m_ViewThreat.rowSelected(row);
				}

				if (getModel() instanceof ListControlRiskASKTableModel) {

					clickedControlRiskRow = row;
					m_ViewControlRisk.rowSelected(row);
				}
				
				if (getModel() instanceof ListControlASKTableModel) {

					clickedControlRow = row;
					m_ViewControl.rowSelected(row);
				}

				if (getModel() instanceof ListRiskASKTableModel) {

					clickedRiskRow = row;
					m_ViewRisk.rowSelected(row);
				}
				else{
					clickedRow = row;
//					if ((getModel() instanceof ListControlRiskASKTableModel) == false)
//						m_View.rowSelected(row);
					
				}
				
			}

			else {
				clickedRow = 0;
				if ((getModel() instanceof ListControlRiskASKTableModel) == false)
					m_View.rowSelected(0);

				if (getModel() instanceof ListAssetASKTableModel) {

					clickedAssetRow = 0;
					// m_ViewAsset.rowSelected(0);
				}

				if (getModel() instanceof ListVulnerabilityASKTableModel) {

					clickedVulnerabilityRow = 0;
					m_ViewVulnerability.rowSelected(0);
				}

				if (getModel() instanceof ListThreatASKTableModel) {

					clickedThreatRow = 0;
					m_ViewThreat.rowSelected(0);
				}
				
				if (getModel() instanceof ListControlASKTableModel) {

					clickedControlRow = 0;
					m_ViewControl.rowSelected(0);
				}

				if (getModel() instanceof ListControlRiskASKTableModel) {

					clickedControlRiskRow = 0;
					m_ViewControlRisk.rowSelected(0);
				}

				if (getModel() instanceof ListRiskASKTableModel) {

					clickedRiskRow = 0;
					m_View.rowSelected(0);
					// m_ViewRisk.rowSelected(0);
				}
				selection = true;
			}
		
	}
	private void updateStatus(int col, int row) {
		// TODO Auto-generated method stub
			statusLabel.setText("Odabrano: " + col + "/" + row);


	}


}






