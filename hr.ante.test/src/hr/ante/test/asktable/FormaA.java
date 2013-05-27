package hr.ante.test.asktable;

import hr.ante.test.asktable.comparator.ASKTableSortOnClick2;
import hr.ante.test.asktable.comparator.ASSortComparatorExample2;

import java.awt.MouseInfo;
import java.awt.PointerInfo;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableActionHandler;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableClickInterceptionListener;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;
import de.kupzog.ktable.renderers.CheckableCellRenderer;

public class FormaA {
	
	KTable table;
	KTableSortComparator comparator;
	KTableSortedModel model;
	KTableActionHandler handler;
	Menu menu;
	Boolean selectedFixedCell=false;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		
		
		
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite comp1 = new Composite(parent, SWT.NONE);
		comp1.setLayout(new FillLayout());
		
		
		
	
		
//		KTable table = new KTable(comp1, SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL 
//				| SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL | SWTX.EDIT_ON_KEY);
		table = new KTable(comp1,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);
		model = new ASTableModel4();
		
		
//		final ASTableModel3 model = new ASTableModel3();
		
		
		table.setModel(model);
		table.setData("org.eclipse.e4.ui.css.id", "ASKTable");

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
						table.redraw();
						//new ASKTableContextMenu(table,(KTableSortedModel)table.getModel());
						new ASKTableSortOnClick2(table,col, row, new ASSortComparatorExample2(model,-1, ASSortComparatorExample2.SORT_NONE),ASSortComparatorExample2.SORT_NONE, 1);
					}		
				}
				System.out.println("botun " + button);
				
				if (row == 0 && button == 3 && col != 0) {

					System.out.println("cursor " + table.getCursor());
					table.redraw();
					new ASKTableContextMenu2(table,(KTableSortedModel)table.getModel());
					//createContextMenuSort(col, row);
					// return true;
				} 
			
//				} 
				else
					table.setMenu(null);

				// TODO Auto-generated method stub
				return false;
			}
		});
//			}
//		});
		
		
		table.addCellSelectionListener(new KTableCellSelectionListener() {
			
			@Override
			public void fixedCellSelected(int col, int row, int statemask) {
				// TODO Auto-generated method stub
//				if(col!=0 && row == 0){
					
//					new KTableActionHandler(table, "SelectAll",col);
//				}
					
				
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
	
	/** Kreiranje menu **/
	
	public void createContextMenuSort(final int col, final int row) {
		MenuItem item1;
		MenuItem item2;
		Menu subMenu;
		final MenuItem subItem1;
		MenuItem subItem2;
		//final Image bullet = new Image(table.getDisplay(), "c:\\IT\\astrikom\\workspace\\icons\\bullet_black_icon.png");
		final MenuItem subItem3;		
		
		menu = new Menu(table.getShell(), SWT.POP_UP);
		item1 = new MenuItem(menu, SWT.PUSH);
		item1.setText("Item");
		item2 = new MenuItem(menu, SWT.CASCADE);
		item2.setText("Sort");

		subMenu = new Menu(menu);
		item2.setMenu(subMenu);
		subItem1 = new MenuItem(subMenu, SWT.PUSH);
		subItem1.setText("Ascending");
		

		subItem1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
//				subItem1.setImage(ResourceManager.getPluginImage("hr.ante.test", "icons/bullet_black_icon.png"));
				sortOnClick(col, row, new ASSortComparatorExample2(model,col, ASSortComparatorExample2.SORT_DOWN),ASSortComparatorExample2.SORT_DOWN, 3);
				

			}
		});

		subItem2 = new MenuItem(subMenu, SWT.PUSH);
		subItem2.setText("Descending");
		subItem2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// implement the sorting when clicking on the header.
				
				sortOnClick(col, row, new ASSortComparatorExample2(model,col, ASSortComparatorExample2.SORT_UP),ASSortComparatorExample2.SORT_UP, 3);
				
				

			}
		});
		
		subItem3 = new MenuItem(subMenu, SWT.PUSH);
		
		subItem3.setText("Clean Sort");
		subItem3.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// implement the sorting when clicking on the header.
				//subItem3.setImage(bullet);
				sortOnClick(col, row, new ASSortComparatorExample2(model,col, ASSortComparatorExample2.SORT_NONE),ASSortComparatorExample2.SORT_NONE, 3);
				
			}
		});

		table.setMenu(menu);
	}

	
public void sortOnClick(int col, int row, KTableSortComparator comparator, int direction, int button) {

	if (table.getModel() instanceof KTableSortedModel) {
		KTableSortedModel model = (KTableSortedModel) table.getModel();
		if (row<model.getFixedHeaderRowCount() && 
				col>=model.getFixedHeaderColumnCount()) {
				if (button == 1) {
					// implement the sorting when clicking on the header.

//					int type = direction;

//					if (model.getSortColumn() == col) {
						
						if (model.getSortState() == KTableSortComparator.SORT_UP) {
							direction = KTableSortComparator.SORT_DOWN;
						} if (model.getSortState() == KTableSortComparator.SORT_UP) {
							direction = KTableSortComparator.SORT_DOWN;
						} else if (model.getSortState() == KTableSortComparator.SORT_NONE) {
							direction = KTableSortComparator.SORT_UP;
						}
//					}
				}
		
			
		
			// update the comparator properly: 
			comparator.setColumnToCompare(col);
			comparator.setSortDirection(direction);
			
			// perform the sorting
			model.sort(comparator);
			
			
			// needed to make the resorting visible!
			table.redraw(); 
		}
		}
	}
}
	

		
		
	

