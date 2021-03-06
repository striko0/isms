package hr.ante.isms.parts.table;

import hr.ante.test.asktable.comparator.ASKTableSortOnClick2;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectToolItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
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

public class ASKTable {

	public static int clickedRow=0;
	private int cRow = -1;
	private int cCol = -1;
	@Inject
	protected EPartService partService;

	KTable m_Table;
	Label statusLabel;
	KTableSortComparator comparator;
	public static KTableSortedModel model;
	private KTableSortedModel m_Model;
	KTableActionHandler handler;
	Menu menu;
	int sortingColumn = -1;



//	@PostConstruct
//	public void createComposite(Composite parent) {
//
//
//		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
//		Composite comp1 = new Composite(parent, SWT.NONE);
//		comp1.setLayout(new FillLayout());



	public ASKTable(Composite parent, KTableSortedModel givenModel,int widht, int height) {
//		super(parent,style);
		// TODO Auto-generated constructor stub

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

		m_Table = new KTable(comp1,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);
		m_Table.setToolTipText("Tablica");
		m_Table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		model = givenModel;
		m_Model=givenModel;
		//table.setSize(parent.getSize());

		m_Table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		m_Table.setLayout(new FillLayout());
		m_Table.setBounds(0,0, widht, height);
		m_Table.setModel(givenModel);
		m_Table.setData("org.eclipse.e4.ui.css.id", "ASKTable");


//		table.addListener(SWT.MouseDown, new HeaderListener2(table));
//		table.addListener(SWT.MouseMove, new HeaderListener2(table));

		m_Table.addClickInterceptionListener(new KTableClickInterceptionListener() {

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
						//new ASKTableSortOnClick(m_table,m_Col, 0, new ASSortComparatorISMS(m_Model,m_Col, direction),direction, 3);
					}
				}
				if(row != 0 && col != 0)
				{
//					table.setSelection(null, false);
//					if(cRow == row && cCol == col ){
//						m_Table.m_Selection.clear();
//						clickedRow=0;
//						cRow = 0;
//						cCol = 0;
//					}
//					cRow = row;
//					cCol = col;
					/**
					 *
					 * POGLEDAJ RENDERERE
					 */
					if(!m_Table.m_Selection.isEmpty())
						clickedRow=row;
					else
						clickedRow=0;

				}
				// TODO Auto-generated method stub
				return false;
			}


		});


		new ASKTableContextMenu1(m_Table,(KTableSortedModel)m_Table.getModel());
		m_Table.addCellSelectionListener(new KTableCellSelectionListener() {

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
//				new KTableActionHandler(table);
				// table.setMenu(null);
//				new KTableActionHandler(table);
				//new ASKTableContextMenu(table,(KTableSortedModel)table.getModel());

				//new ASMenu(table,1);
				System.out.println("Cell [" + col + ";" + row
						+ "] selected. - Model row: " + modelRow);
				System.out.println("ovo " + m_Table.m_Selection);

			}
		});

		m_Table.addCellSelectionListener(new KTableCellSelectionListener() {
//
//
			public void cellSelected(int col, int row, int statemask) {
//				    	// the idea is to map the row index back to the model index since the given row index
//				    	// changes when sorting is done.
				       int modelRow = model.mapRowIndexToModel(row);
//				   		new KTableActionHandler(table);
////				       table.setMenu(null);
//
//						System.out.println("Cell ["+col+";"+row+"] selected. - Model row: "+modelRow);
//						System.out.println("ovo " + table.m_Selection);
					}
			public void fixedCellSelected(final int col, final int row, int statemask) {
//						System.out.println("Header ["+col+";"+row+"] selected.");
//
//
					}
				});


		m_Table.addCellResizeListener(new KTableCellResizeListener() {

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

			Image crossCursor = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/cross_win32.gif");

			// Row Resize

			Image row_resizeCursor = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/row_resize_win32.gif");

			// Column Resize

			Image column_resizeCursor  = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/column_resize_win32.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);

			m_Table.setDefaultCursor(new Cursor(m_Table.getDisplay(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			m_Table.setDefaultRowResizeCursor(new Cursor(m_Table.getDisplay(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			m_Table.setDefaultColumnResizeCursor(new Cursor(m_Table.getDisplay(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		} else {

			// Cross

			Image crossCursor      = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/cross.gif");
			Image crossCursor_mask = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/cross_mask.gif");

			// Row Resize

			Image row_resizeCursor      = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/row_resize.gif");
			Image row_resizeCursor_mask = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/row_resize_mask.gif");

			// Column Resize

			Image column_resizeCursor      = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/column_resize.gif");
			Image column_resizeCursor_mask = SWTX.loadImageResource(m_Table.getDisplay(), "/icons/column_resize_mask.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);


			m_Table.setDefaultCursor(new Cursor(m_Table.getDisplay(), crossCursor_mask.getImageData(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			m_Table.setDefaultRowResizeCursor(new Cursor(m_Table.getDisplay(), row_resizeCursor_mask.getImageData(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			m_Table.setDefaultColumnResizeCursor(new Cursor(m_Table.getDisplay(), column_resizeCursor_mask.getImageData(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		}






		}

	private boolean toggleSelection(int col, int row) {

        if (m_Table.isMultiSelectMode()) {
            Object o;
            if (m_Table.isRowSelectMode()) {
                o = new Integer(row);
            } else {
                o = new Point(col, row);
            }
            if (m_Table.m_Selection.get(o) != null) {
            	m_Table.m_Selection.remove(o);
                return false;
            } else {
            	m_Table.m_Selection.put(o, o);
                return true;
            }
        }
        return false;
    }
	private void updateStatus(int col, int row) {
		// TODO Auto-generated method stub
			statusLabel.setText("Odabrano: " + col + "/" + row);



	}


}






