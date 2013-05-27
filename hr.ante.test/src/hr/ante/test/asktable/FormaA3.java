package hr.ante.test.asktable;

import hr.ante.test.asktable.comparator.ASKTableSortOnClick2;
import hr.ante.test.asktable.comparator.ASSortComparatorExample2;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableActionHandler;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableClickInterceptionListener;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;

public class FormaA3 {

	KTable table;
	KTableSortComparator comparator;
	KTableSortedModel model;
	KTableActionHandler handler;
	Menu menu;
	int sortingColumn = -1;

	@PostConstruct
	public void createComposite(Composite parent) {



		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		model = new ASTableModel4();
		
		
		
		
		
		//		KTable table = new KTable(comp1, SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL
		//				| SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL | SWTX.EDIT_ON_KEY);
				table = new KTable(parent,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);
				
						table.setModel(model);
						table.setData("org.eclipse.e4.ui.css.id", "ASKTable");
						
								//new HeaderListener2(table);
							//	table.addListener(SWT.Activate, new HeaderListener2(table));
						
						
						//		table.addListener(SWT.MouseDown, new HeaderListener2(table));
						//		table.addListener(SWT.MouseMove, new HeaderListener2(table));
						
						
								table.addClickInterceptionListener(new KTableClickInterceptionListener() {
						
									@Override
									public boolean cellClicked(int col, int row, Rectangle cellRect,
											int x, int y, int button, KTable table) {
						
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
						
												//new ASKTableContextMenu(table,(KTableSortedModel)table.getModel());
												new ASKTableSortOnClick2(table,col, row, new ASSortComparatorExample2(model,-1, ASSortComparatorExample2.SORT_UP),ASSortComparatorExample2.SORT_UP, 1);
						
											}
										}
										// TODO Auto-generated method stub
										return false;
									}
								});
								
								
										new ASKTableContextMenu2(table,(KTableSortedModel)table.getModel());
		table.addCellSelectionListener(new KTableCellSelectionListener() {

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
				System.out.println("ovo " + table.m_Selection);

			}
		});

//		table.addCellSelectionListener(new KTableCellSelectionListener() {
//
//
//			public void cellSelected(int col, int row, int statemask) {
//				    	// the idea is to map the row index back to the model index since the given row index
//				    	// changes when sorting is done.
//				       int modelRow = model.mapRowIndexToModel(row);
//				   		new KTableActionHandler(table);
////				       table.setMenu(null);
//
//						System.out.println("Cell ["+col+";"+row+"] selected. - Model row: "+modelRow);
//						System.out.println("ovo " + table.m_Selection);
//					}
//			public void fixedCellSelected(final int col, final int row, int statemask) {
//						System.out.println("Header ["+col+";"+row+"] selected.");
//
//
//					}
//				});


		table.addCellResizeListener(new KTableCellResizeListener() {

			@Override
			public void rowResized(int row, int newHeight) {
				System.out.println("Row "+row+" resized to "+newHeight);

			}

			@Override
			public void columnResized(int col, int newWidth) {
				System.out.println("Column "+col+" resized to "+newWidth);


			}
		});

		/**
		 *  Set Excel-like table cursors
		 */

        if ( SWT.getPlatform().equals("win32") ) {

			// Cross

			Image crossCursor = SWTX.loadImageResource(table.getDisplay(), "/icons/cross_win32.gif");

			// Row Resize

			Image row_resizeCursor = SWTX.loadImageResource(table.getDisplay(), "/icons/row_resize_win32.gif");

			// Column Resize

			Image column_resizeCursor  = SWTX.loadImageResource(table.getDisplay(), "/icons/column_resize_win32.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);

			table.setDefaultCursor(new Cursor(table.getDisplay(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			table.setDefaultRowResizeCursor(new Cursor(table.getDisplay(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			table.setDefaultColumnResizeCursor(new Cursor(table.getDisplay(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		} else {

			// Cross

			Image crossCursor      = SWTX.loadImageResource(table.getDisplay(), "/icons/cross.gif");
			Image crossCursor_mask = SWTX.loadImageResource(table.getDisplay(), "/icons/cross_mask.gif");

			// Row Resize

			Image row_resizeCursor      = SWTX.loadImageResource(table.getDisplay(), "/icons/row_resize.gif");
			Image row_resizeCursor_mask = SWTX.loadImageResource(table.getDisplay(), "/icons/row_resize_mask.gif");

			// Column Resize

			Image column_resizeCursor      = SWTX.loadImageResource(table.getDisplay(), "/icons/column_resize.gif");
			Image column_resizeCursor_mask = SWTX.loadImageResource(table.getDisplay(), "/icons/column_resize_mask.gif");

			// we set the hotspot to the center, so calculate the number of pixels from hotspot to lower border:

			Rectangle crossBound        = crossCursor.getBounds();
			Rectangle rowresizeBound    = row_resizeCursor.getBounds();
			Rectangle columnresizeBound = column_resizeCursor.getBounds();

			Point crossSize        = new Point(crossBound.width/2, crossBound.height/2);
			Point rowresizeSize    = new Point(rowresizeBound.width/2, rowresizeBound.height/2);
			Point columnresizeSize = new Point(columnresizeBound.width/2, columnresizeBound.height/2);


			table.setDefaultCursor(new Cursor(table.getDisplay(), crossCursor_mask.getImageData(), crossCursor.getImageData(), crossSize.x, crossSize.y), crossSize);
			table.setDefaultRowResizeCursor(new Cursor(table.getDisplay(), row_resizeCursor_mask.getImageData(), row_resizeCursor.getImageData(), rowresizeSize.x, rowresizeSize.y));
			table.setDefaultColumnResizeCursor(new Cursor(table.getDisplay(), column_resizeCursor_mask.getImageData(), column_resizeCursor.getImageData(), columnresizeSize.x, columnresizeSize.y));

		}


		}


}






