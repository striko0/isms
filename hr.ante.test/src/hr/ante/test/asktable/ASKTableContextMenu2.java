package hr.ante.test.asktable;

import hr.ante.test.asktable.comparator.ASKTableSortOnClick2;
import hr.ante.test.asktable.comparator.ASSortComparatorExample2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.mihalis.opal.opalDialog.Dialog;
import org.mihalis.opal.panels.BlurredPanel;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableModel;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;

public class ASKTableContextMenu2{
	 static final char TAB = '\t';
	 static final String PlatformLineDelimiter = System.getProperty("line.separator");



	 //public KTableCutAction m_CutAction;
	 //public KTablePasteAction m_PasteAction;
	 public KTableSelectAllAction m_SelectAllAction;
	 public ASKTableSelectAllColumnRowsAction m_SelectAllColumnRowsAction;
	 public ASKTableSelectAllRowColumnsAction m_SelectAllRowColumnsAction;
	 public KTableCopyAction m_CopyAction;
	 public KTableCopyAllAction m_CopyAllAction;
	 public ASKTableSortAction m_SortAction;
	 public ASKTableFilterAction m_FilterAction;

	 protected int buttonClicked=1;
	 protected int m_Col;
	 protected int m_Row;
	 protected Vector<Integer> rowsWithText = null;
	 protected KTableSortedModel m_Model;
	 protected Menu m_Menu;

	 protected Menu m_FixedMenu;
	 protected MenuItem fixedItemCopy;
	 protected MenuItem fixedItemSort;
	 protected MenuItem fixedItemSelectAllColRows;
	 protected MenuItem fixedItemFilter;
	 protected MenuItem fixedItemSelectAllRowCols;
	 protected MenuItem fixedItemSelectAll;
	 protected MenuItem fixedItemCopyAll;
	 protected Menu fixedItemSubMenu;
	 protected MenuItem fixedItemSubItem1;
	 protected MenuItem fixedItemSubItem2;
	 protected MenuItem fixedItemSubItem3;
	 protected MenuItem ItemSeparator;

	 protected Menu m_CellMenu;
	 protected MenuItem cellItemCopy;
	 protected MenuItem cellItemSelectAll;


	 protected KTable m_table;
	 protected MenuManager m_fixedContextMenuManager;
	 MenuManager m_SubitemSort;



	public ASKTableContextMenu2(KTable table,KTableSortedModel model/*,boolean isFixedCell, String doSomething, int clickedColumn*/)  {

		m_Model = model;
		m_table = table;
		createActions();
		registerActionUpdater();
		// add actions to context menu:
		//m_fixedContextMenuManager = new MenuManager("#PopupMenu");
		//m_fixedContextMenuManager.setRemoveAllWhenShown(true);
		//m_Menu = new Menu(m_table.getShell(), SWT.POP_UP);
		//m_Menu = m_fixedContextMenuManager.createContextMenu(m_table);
		//m_table.setMenu(m_Menu);

	}

	public void createContextMenu(boolean isFixedCell,boolean isColumnSelected)
    {

		m_FixedMenu = new Menu(m_table.getShell(), SWT.POP_UP);

		if(isFixedCell){
			if(!isColumnSelected)
			{
//				fixedItemSelectAllRowCols = new MenuItem(m_FixedMenu, SWT.PUSH);
//				fixedItemSelectAllRowCols.setText("Select All Row Columns");
//				fixedItemSelectAllRowCols.addSelectionListener(new SelectionAdapter() {
//
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						// TODO Auto-generated method stub
//						m_SelectAllRowColumnsAction.run();
//					}
//				});



				//1. item in RowFixedMenu
				fixedItemCopy = new MenuItem(m_FixedMenu, SWT.CASCADE);
				fixedItemCopy.setText("Copy");
				fixedItemCopy.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						m_CopyAction.run();
					}
				});


//				//2. item in RowFixedMenu - separator
//				ItemSeparator = new MenuItem(m_FixedMenu, SWT.SEPARATOR);



			}
			else{
				Image ascending = null;
				Image descending = null;
				Image sort = null;
				Image filter = null;

				try {
					ascending = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.test\\src\\icons\\sort_up_green.png");
					descending = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.test\\src\\icons\\sort_down_green.png");
					sort = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.test\\src\\icons\\sort_neutral_green.png");
					filter = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.test\\src\\icons\\filter.png");
		        } catch (Exception e) {
		            System.out.println("Cannot load images");
		            System.out.println(e.getMessage());
		            System.exit(1);
		        }


				//1. item in FixedMenu
				fixedItemCopy = new MenuItem(m_FixedMenu, SWT.CASCADE);
				fixedItemCopy.setText("Copy");
				fixedItemCopy.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						m_CopyAction.run();
					}
				});

				//2. item in FixedMenu - separator
				ItemSeparator = new MenuItem(m_FixedMenu, SWT.SEPARATOR);

				//3. item in FixedMenu - separator
				fixedItemSort = new MenuItem(m_FixedMenu, SWT.CASCADE);
				fixedItemSort.setText("Sort");
				fixedItemSort.setImage(sort);

				//Sub menu in 3. item in FixedMenu
				fixedItemSubMenu = new Menu(m_FixedMenu);
				fixedItemSort.setMenu(fixedItemSubMenu);

				//1. sub item in 3. item in FixedMenu
				fixedItemSubItem1 = new MenuItem(fixedItemSubMenu, SWT.PUSH);
				fixedItemSubItem1.setText("Ascending");
				fixedItemSubItem1.setImage(ascending/*ResourceManager.getPluginImage("hr.ante.test.icons", "sort_up.png")*/);
				fixedItemSubItem1.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

						//m_table.setSelection(null, false);
						m_SortAction.run(ASSortComparatorExample2.SORT_DOWN);

					}
				});

				//2. sub item in 3. item in FixedMenu
				fixedItemSubItem2 = new MenuItem(fixedItemSubMenu, SWT.PUSH);
				fixedItemSubItem2.setText("Descending");
				fixedItemSubItem2.setImage(descending);
				fixedItemSubItem2.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						// implement the sorting when clicking on the header.
						//m_table.setSelection(null, false);
						m_SortAction.run(ASSortComparatorExample2.SORT_UP);
						//sortOnClick(col, row, new ASSortComparatorExample2(model,col, ASSortComparatorExample2.SORT_UP),ASSortComparatorExample2.SORT_UP, 3);
					}
				});

				//3. sub item in 3. item in FixedMenu
				fixedItemSubItem3 = new MenuItem(fixedItemSubMenu, SWT.PUSH);
				fixedItemSubItem3.setText("Clean Sort");
				fixedItemSubItem3.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						// implement the sorting when clicking on the header.
						//m_table.setSelection(null, false);
						m_SortAction.run(ASSortComparatorExample2.SORT_NONE);
//						sortOnClick(col, row, new ASSortComparatorExample2(model,col, ASSortComparatorExample2.SORT_NONE),ASSortComparatorExample2.SORT_NONE, 3);
					}
				});

				//4. item in FixedMenu - separator
				fixedItemFilter = new MenuItem(m_FixedMenu, SWT.PUSH);
				fixedItemFilter.setImage(filter);
				fixedItemFilter.setText("Filter");
				fixedItemFilter.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO Auto-generated method stub


						String input = Dialog.ask("Enter Filter", "Enter Filter text", "");


						if(input!="" && input!=null){
							System.out.println("Choice is..." + input);

							m_FilterAction.run(input);

						}
					}
				});


//				//2. item in FixedMenu
//				fixedItemSelectAllColRows = new MenuItem(m_FixedMenu, SWT.PUSH);
//				fixedItemSelectAllColRows.setText("Select All Column Rows");
//				fixedItemSelectAllColRows.addSelectionListener(new SelectionAdapter() {
//
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						// TODO Auto-generated method stub
//						m_SelectAllColumnRowsAction.run();
//					}
//				});
//
//
//
//				//3. item in FixedMenu - separator
//				fixedItemSeparator = new MenuItem(m_FixedMenu, SWT.SEPARATOR);
//
//				//4. item in FixedMenu
//				fixedItemSelectAll = new MenuItem(m_FixedMenu, SWT.PUSH);
//				fixedItemSelectAll.setText("Select All");
//				fixedItemSelectAll.addSelectionListener(new SelectionAdapter() {
//
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						// TODO Auto-generated method stub
//						m_SelectAllAction.run();
//					}
//				});
//
//				fixedItemCopyAll = new MenuItem(m_FixedMenu, SWT.PUSH);
//				fixedItemCopyAll.setEnabled(true);
//				fixedItemCopyAll.setText("Copy All");
//				fixedItemCopyAll.addSelectionListener(new SelectionAdapter() {
//
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						// TODO Auto-generated method stub
//						m_CopyAllAction.run();
//					}
//				});

			}

			m_table.setMenu(m_FixedMenu);

		}

		else{

			m_CellMenu = new Menu(m_table.getShell(), SWT.POP_UP);

			//1. item in cellMenu
			cellItemCopy = new MenuItem(m_CellMenu, SWT.PUSH);
			cellItemCopy.setText("Copy");
			cellItemCopy.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					m_CopyAction.run();
				}
			});

			//2. item in CellMenu - separator
			ItemSeparator = new MenuItem(m_FixedMenu, SWT.SEPARATOR);

			//3. item in cellMenu
			cellItemSelectAll = new MenuItem(m_CellMenu, SWT.PUSH);
			cellItemSelectAll.setText("Select All");
			cellItemSelectAll.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					m_SelectAllAction.run();
				}
			});



			m_table.setMenu(m_CellMenu);


		}


		//m_table.redraw();
//
//    	m_fixedContextMenuManager.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				fillFixedContextMenu(manager);
//			}
//		});


	}



    protected void fillFixedContextMenu(IMenuManager menumanager) {


//		menumanager.add(m_CopyAction);
//		menumanager.add(m_CutAction);
//		menumanager.add(m_PasteAction);
//		menumanager.add(new Separator());
//		menumanager.add(m_CopyAllAction);
//		menumanager.add(m_SortAction);

//		createFixedSortMenu();

//		menumanager.add(m_SubitemSort);
//		menumanager.add(m_SelectAllAction);
//		menumanager.add(new Separator());
//		// Other plug-ins can contribute their actions here
//		menumanager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

    /**
     * Registers the cut, copy, paste and select_all actions for global use at the IActionBar given.<p>
     * Currently does not set up the UNDO and REDO actions because they will be implemented in another way.
     * @param actionBar The IActionBars that allows global action registration. Normally
     * you can get that with getViewerSite().getActionBars() or getEditorSite().getActionBars().
     */
	public void registerGlobalActions(IActionBars actionBar) {
		// actionBar.setGlobalActionHandler(ActionFactory.CUT.getId(),
		// this.m_CutAction);
		actionBar.setGlobalActionHandler(ActionFactory.COPY.getId(),
				this.m_CopyAction);
		// actionBar.setGlobalActionHandler(ActionFactory.PASTE.getId(),this.m_PasteAction);
		actionBar.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
				this.m_SelectAllAction);
		actionBar.updateActionBars();
	}




	public void sortOnClick(int col, int row, KTableSortComparator comparator, int direction, int button) {

		if (m_table.getModel() instanceof KTableSortedModel) {
			//KTableSortedModel model = (KTableSortedModel) m_table.getModel();
			if (row<m_Model.getFixedHeaderRowCount() &&
					col>=m_Model.getFixedHeaderColumnCount()) {
					if (button == 1) {
						// implement the sorting when clicking on the header.

//						int type = direction;

//						if (model.getSortColumn() == col) {

							if (m_Model.getSortState() == KTableSortComparator.SORT_UP) {
								direction = KTableSortComparator.SORT_DOWN;
							} if (m_Model.getSortState() == KTableSortComparator.SORT_UP) {
								direction = KTableSortComparator.SORT_DOWN;
							} else if (m_Model.getSortState() == KTableSortComparator.SORT_NONE) {
								direction = KTableSortComparator.SORT_UP;
							}
//						}
					}



				// update the comparator properly:
				comparator.setColumnToCompare(col);
				comparator.setSortDirection(direction);

				// perform the sorting
				m_Model.sort(comparator);


				// needed to make the resorting visible!
				m_table.redraw();
			}
			}
	}

	protected void createActions() {
		m_CopyAction = new KTableCopyAction();
		m_CopyAllAction = new KTableCopyAllAction();
//		m_PasteAction = new KTablePasteAction();
		m_FilterAction = new ASKTableFilterAction();
		m_SortAction = new ASKTableSortAction();
        m_SelectAllAction = new KTableSelectAllAction();
        m_SelectAllColumnRowsAction = new ASKTableSelectAllColumnRowsAction();
        m_SelectAllRowColumnsAction = new ASKTableSelectAllRowColumnsAction();


//      m_CutAction = new KTableCutAction();

  }

	protected void registerActionUpdater() {
	    m_table.addCellSelectionListener(new KTableCellSelectionListener() {

            public void cellSelected(int col, int row, int statemask) {
            	m_table.setMenu(null);
//            	m_table.redraw();
                m_Col=col;
                m_Row=row;
                createContextMenu(false,false);
                m_table.redraw();
            }

            public void fixedCellSelected(int col, int row, int statemask) {
            	m_table.setMenu(null);

            	//m_table.setSelection(new Point[]{}, false);
            	if(col==0 && row==0){
            		m_table.setMenu(null);
            		m_table.redraw();
            		m_SelectAllAction.run();
            		m_table.redraw();
            	}
            	else if(col==0 && row!=0){
                    m_Col=col;
                    m_Row=row;
                    m_SelectAllRowColumnsAction.run();
                    createContextMenu(true,false);
                    m_table.redraw();

            	}

            	else if(col!=0 && row==0){
                    m_Col=col;
                    m_Row=row;
                    m_SelectAllColumnRowsAction.run();
                    createContextMenu(true,true);
                    m_table.redraw();

            	}


            }
	    });
	}

	/*protected void updateActions() {
      m_CopyAction.updateEnabledState();
      m_CutAction.updateEnabledState();
      m_PasteAction.updateEnabledState();

  }*/

	/*protected void updateFixedActions() {
        m_CopyAction.updateEnabledState();
        m_CopyAllAction.updateEnabledState();
        m_CutAction.updateEnabledState();
        m_PasteAction.updateEnabledState();
		m_SortAction.updateEnabledState();

        m_SelectAllAction.updateEnabledState();
    }*/

	/**
	 * SELECT ALL
	 *
	 */
	protected class KTableSelectAllAction extends Action {
		protected KTableSelectAllAction() {
			setId("KTableSelectAllActionHandler");//$NON-NLS-1$
			//setEnabled(false);
			setText("Select All");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
				if (m_Model != null) {
					selectAll(m_Model);
				}
			}
		}
		// //public void updateEnabledState() {
		// // if (m_table != null && !m_table.isDisposed() &&
		// m_table.isMultiSelectMode()) {
		// // setEnabled(true);
		// // } else setEnabled(false);
		// //}

		protected void selectAll(KTableModel model) {
		    Vector<Point> sel = new Vector<Point>();
		    for (int row=model.getFixedHeaderRowCount(); row<model.getRowCount(); row++)
		        for (int col=model.getFixedHeaderColumnCount(); col<model.getColumnCount(); col++) {
		        	if (model.belongsToCell(col, row)!=null){
			        	Point cell = model.belongsToCell(col, row);
			            if (cell.x==col && cell.y==row)
			                sel.add(cell);
		        	}
		        	else
		    			sel.add(new Point(col, row));
		        }
		    try {
		        m_table.setRedraw(false);
		        m_table.setSelection(new Point[]{}, false);
			    m_table.setSelection((Point[])sel.toArray(new Point[]{}), false);
		    } finally {
		        m_table.setRedraw(true);
		    }
		}
    }


	/**
	 * SELECT ALL COLUMN ROWS
	 *
	 */
	protected class ASKTableSelectAllColumnRowsAction extends Action {
		protected ASKTableSelectAllColumnRowsAction() {
			setId("ASKTableSelectAllColumnRowsActionHandler");//$NON-NLS-1$
			setText("Select Column");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
			    if (m_Model!=null){
			    	if (!m_table.isRowSelectMode() && m_table.isMultiSelectMode()) {
			    		selectAllRows(m_Model);
		    		}
		    	}
			}
		}
		public void updateEnabledState(boolean setTrue) {
			if (m_table != null && !m_table.isDisposed() && m_table.isMultiSelectMode() && setTrue) {
				fixedItemSelectAllColRows.setEnabled(true);
			} else fixedItemSelectAllColRows.setEnabled(false);
		}
		protected void selectAllRows(KTableModel model) {
		    Vector<Point> sel = new Vector<Point>();
		    for (int row=model.getFixedHeaderRowCount(); row<model.getRowCount(); row++)
		         {
		    		if (model.belongsToCell(m_Col, row)!=null){
			    		Point currentCell = model.belongsToCell(m_Col, row);
			            if (currentCell.x==m_Col && currentCell.y==row)
			                sel.add(currentCell);
		    		}
		    		else
		    			sel.add(new Point(m_Col,row));
		        }
		    try {
		        m_table.setRedraw(false);
		        m_table.setSelection(new Point[]{}, false);
			    m_table.setSelection((Point[])sel.toArray(new Point[]{}), false);
		    } finally {
		        m_table.setRedraw(true);
		    }
		}
    }

	/**
	 * SELECT ALL ROW COLUMNS
	 *
	 */
	protected class ASKTableSelectAllRowColumnsAction extends Action {
		protected ASKTableSelectAllRowColumnsAction() {
			setId("ASKTableSelectAllRowColumnsActionHandler");//$NON-NLS-1$
			setText("Select Row");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
			    if (m_Model!=null){
			    	if (!m_table.isRowSelectMode() && m_table.isMultiSelectMode()) {
			    		selectAllColumns(m_Model);
			    	}
			    }
			}
		}
		public void updateEnabledState(boolean setTrue) {
			if (m_table != null && !m_table.isDisposed() && m_table.isMultiSelectMode() && setTrue) {
				fixedItemSelectAllRowCols.setEnabled(true);
			} else fixedItemSelectAllRowCols.setEnabled(false);
		}
		protected void selectAllColumns(KTableModel model) {
		    Vector<Point> sel = new Vector<Point>();
		    for (int col=model.getFixedHeaderColumnCount(); col<model.getColumnCount(); col++)
		         {
		    		if (model.belongsToCell(col, m_Row)!=null){
			    		Point currentCell = model.belongsToCell(col, m_Row);
			            if (currentCell.x==col && currentCell.y==m_Row)
			                sel.add(currentCell);
		    		}
		    		else
		    			sel.add(new Point(col,m_Row));
		        }

		    try {
		        m_table.setRedraw(false);
		        m_table.setSelection(new Point[]{}, false);
			    m_table.setSelection((Point[])sel.toArray(new Point[]{}), false);
		    } finally {
		        m_table.setRedraw(true);
		    }
		}

    }

	/**
	 * COPY
	 *
	 */
	protected class KTableCopyAction extends Action {
		protected KTableCopyAction() {
			setId("KTableCopyActionHandler");//$NON-NLS-1$
			setText("Copy");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
				setClipboardContent(m_table.getCellSelection());
			}
		}
		//public void updateEnabledState() {
		//	if (m_table != null && !m_table.isDisposed()) {
		//		Point[] selection = m_table.getCellSelection();
		//		setEnabled(selection!=null && selection.length>0);
		//	} else setEnabled(false);
		//}
    }


	/**
	 * COPY ALL
	 *
	 */
 protected class KTableCopyAllAction extends Action {
        protected KTableCopyAllAction() {
			setId("KTableCopyAllActionHandler");//$NON-NLS-1$
			setText("Copy All");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
				if(getAllTableCells()!=null)
					setClipboardContent(getAllTableCells());

			}
		}
		//public void updateEnabledState() {
		//	if (m_table != null && !m_table.isDisposed()) {
		//		setEnabled(true);
		//	} else setEnabled(false);
		//}
		private Point[] getAllTableCells() {
		    if (m_Model==null) return new Point[]{};
		    Vector<Point> cells = new Vector<Point>(m_Model.getColumnCount()*m_Model.getRowCount());
		    for (int row=0; row<m_Model.getRowCount(); row++) {
		        for (int col=0; col<m_Model.getColumnCount(); col++) {
		        	if (m_Model.belongsToCell(col, row)!=null){
		        		Point valid = m_Model.belongsToCell(col, row);
			            if (valid.y==row && valid.x==col)
			                cells.add(valid);
		        	}
		        	else
		        		cells.add(new Point(col, row));

		        }
		    }
		    return (Point[])cells.toArray(new Point[]{});
		}
    }

/* protected class KTablePasteAction extends Action {
		protected KTablePasteAction() {
			setId("KTablePasteActionHandler");//$NON-NLS-1$
			setText("Paste");
		}
		public void run() {
			if (m_table != null && !m_table.isDisposed()) {
			    pasteToSelection(getTextFromClipboard(), m_table.getCellSelection());
			}
		}
        protected String getTextFromClipboard() {
            Clipboard clipboard = new Clipboard(m_table.getDisplay());
            try {
                return clipboard.getContents(TextTransfer.getInstance()).toString();
            } catch (Exception ex) {
                return " ";
            } finally {
                clipboard.dispose();
            }
        }
        protected void pasteToSelection(String text, Point[] selection) {
            if (selection==null || selection.length==0) return;
            if (m_Model==null) return;

            try {
                m_table.setRedraw(false);
                m_table.setSelection(new Point[]{}, false);
                Vector<Point> sel = new Vector<Point>();

                String[][] cellTexts = parseCellTexts(text);
                for (int row=0; row<cellTexts.length; row++)
                    for (int col=0; col<cellTexts[row].length; col++) {
                    	m_Model.setContentAt(col+selection[0].x, row+selection[0].y, cellTexts[row][col]);
                        sel.add(new Point(col+selection[0].x, row+selection[0].y));
                    }
                m_table.setSelection((Point[])sel.toArray(new Point[]{}), false);
            } finally {
                m_table.setRedraw(true);
            }
        }
        protected String[][] parseCellTexts(String text) {
            if (!m_table.isMultiSelectMode()) {
                return new String[][]{{text}};
            } else {
                String[] lines = text.split(PlatformLineDelimiter);
                String[][] cellText = new String[lines.length][];
                for (int line=0; line<lines.length; line++)
                    cellText[line] = lines[line].split(TAB+"");
                return cellText;
            }
        }
        //public void updateEnabledState() {
		//	if (m_table != null && !m_table.isDisposed()) {
		//		Point[] selection = m_table.getCellSelection();
		//		if (selection==null)
		//		    setEnabled(false);
		//		else if (selection.length>1) // && !m_table.isMultiSelectMode())
		//		    setEnabled(false);
		//		else setEnabled(true);
		//	} else setEnabled(false);
		}
 }*/

	/**
	 * SORT
	 *
	 */
	protected class ASKTableSortAction extends Action {
		protected ASKTableSortAction() {
			setId("KTableSortActionHandler");//$NON-NLS-1$
			setText("Sort");
		}
		public void run(int direction) {
			if (m_table != null && !m_table.isDisposed()) {
					//sortOnClick(m_Col, m_Row, new ASSortComparatorExample2(m_Model,m_Col, ASSortComparatorExample2.SORT_DOWN),ASSortComparatorExample2.SORT_DOWN, 3);
					//sortOnClick(m_Col, m_Row, new ASSortComparatorExample2(m_Model,m_Col, direction),direction, buttonClicked);
					new ASKTableSortOnClick2(m_table,m_Col, 0, new ASSortComparatorExample2(m_Model,m_Col, direction),direction, 3);

			}
		}
		//public void updateEnabledState() {
		//	if (m_table != null && !m_table.isDisposed()) {
		//		setEnabled(true);
		//	} else setEnabled(false);
		//}
	}

	/**
	 * FILTER
	 *
	 */
	protected class ASKTableFilterAction extends Action {
		protected ASKTableFilterAction() {
			setId("KTableFilterActionHandler");//$NON-NLS-1$
			setText("Filter");
		}
		public void run(String filterText) {
			if (m_table != null && !m_table.isDisposed()) {

				Vector<Point> sel = new Vector<Point>();

				//Object content;
				searchText(filterText);
			    for (int row=m_Model.getFixedHeaderRowCount(); row<m_Model.getRowCount(); row++){
			        for (int col=m_Model.getFixedHeaderColumnCount(); col<m_Model.getColumnCount(); col++) {
			        	if (m_Model.belongsToCell(col, row)!=null){
				        	Point cell = m_Model.belongsToCell(col, row);
				        	if (cell.x==col && cell.y==row)
				        		sel.add(cell);
				        	else
				        		sel.add(new Point(col, row));
//			        	if(col==m_Col){
//			        		content = m_Model.getContentAt(col, row);
//			            	if(content.toString().contains(filterText)){
//								m_Model.setContentAt(m_Col, rowCounter, content);
//								filterSuccesful=true;
//
//								rowCounter++;
//								if(content instanceof Boolean)
//									m_Model.setContentAt(col,row,false);
//								m_Model.setContentAt(col,row,"");
//							}
//
//			        	}






				         //       sel.add(cell);
			        	}
			        	//else
			    		//	sel.add(new Point(col, row));
			       }
			    }

			    try {
			        m_table.setRedraw(false);
			        m_table.setSelection(new Point[]{}, false);
				    m_table.setSelection((Point[])sel.toArray(new Point[]{}), false);
			    } finally {
			        m_table.setRedraw(true);
			    }
			}

		}
		//public void updateEnabledState() {
		//	if (m_table != null && !m_table.isDisposed()) {
		//		setEnabled(true);
		//	} else setEnabled(false);
		//}
		protected void  searchText(String filterText)
		{
			 Object content;
			 rowsWithText = new Vector<Integer>();

			 int ctr=1;
			 boolean filterSuccesful=false;
//				int rowCounter=1;
//				int rowBefore=1;
			for (int row = m_Model.getFixedHeaderRowCount(); row < m_Model
					.getRowCount(); row++) {

				content = m_Model.getContentAt(m_Col, row);
				if (content.toString().contains(filterText)) {
					rowsWithText.add(row);
					filterSuccesful = true;
					// rowCounter++;
					// if(content instanceof Boolean)
					// m_Model.setContentAt(col,row,false);
					// m_Model.setContentAt(col,row,"");

				}

			}
			if (!filterSuccesful) {
				Dialog.inform("Filter failed", ".........");
				return;
			}

			int rowCounter=1;
			int elementCounter=0;


			if(rowsWithText!=null){
				for (int row=m_Model.getFixedHeaderRowCount(); row<rowsWithText.size(); row++){
			        for (int col=m_Model.getFixedHeaderColumnCount(); col<m_Model.getColumnCount(); col++) {
			        	if(!rowsWithText.isEmpty()){
			        		m_Model.setContentAt(col, row, m_Model.getContentAt(col, rowsWithText.get(elementCounter)));
			        		elementCounter++;
			        	}
			        	else
			        		if(m_Model.getContentAt(col, row) instanceof Boolean)
			        			m_Model.setContentAt(col, row,false);
			        		m_Model.setContentAt(col, row,"");


			        }
				}
			}


	}
	}









	/**
	 * Copies the specified text range to the clipboard.  The table will be placed
	 * in the clipboard in plain text format and RTF format.
	 * @param selection The list of cell indices thats content should be set
	 * to the clipboard.
	 *
	 * @exception SWTError, see Clipboard.setContents
	 * @see org.eclipse.swt.dnd.Clipboard.setContents
	 */
	protected void setClipboardContent(Point[] selection) throws SWTError {
		//RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		TextTransfer plainTextTransfer = TextTransfer.getInstance();
		//HTMLTransfer htmlTransfer = HTMLTransfer.getInstance();


		//String rtfText = getRTFForSelection(selection);
		String plainText = getTextForSelection(selection);
		//String htmlText = getHTMLForSelection(selection);

		Clipboard clipboard = new Clipboard(m_table.getDisplay());
		try {
			//clipboard.setContents(new String[] {plainText,rtfText},	new Transfer[] {plainTextTransfer,rtfTransfer});//RTF Transfer
			clipboard.setContents(new String[] {plainText},	new Transfer[] {plainTextTransfer});//Plain Text Transfer
			//clipboard.setContents(new String[] { plainText, htmlText },	new Transfer[] { plainTextTransfer, htmlTransfer }); // HTmlTransfer
		} catch (SWTError error) {
			// Copy to clipboard failed. This happens when another application
			// is accessing the clipboard while we copy. Ignore the error.
			// Rethrow all other errors.
			if (error.code != DND.ERROR_CANNOT_SET_CLIPBOARD) {
				throw error;
			}
		} finally {
			clipboard.dispose();
		}

	}

	private Point[] findTableDimensions(Point[] selection) {
	    Point topLeft = new Point(-1,-1);
	    Point bottomRight = new Point(-1, -1);

	    for (int i=0; i<selection.length; i++) {
	        Point cell = selection[i];
	        if (topLeft.x<0) topLeft.x = cell.x;
	        else if (topLeft.x>cell.x) topLeft.x = cell.x;
	        if (bottomRight.x<0) bottomRight.x = cell.x;
	        else if (bottomRight.x<cell.x) bottomRight.x = cell.x;

	        if (topLeft.y<0) topLeft.y = cell.y;
	        else if (topLeft.y>cell.y) topLeft.y = cell.y;
	        if (bottomRight.y<0) bottomRight.y = cell.y;
	        else if (bottomRight.y<cell.y) bottomRight.y = cell.y;
	    }
	    return new Point[]{topLeft, bottomRight};
	}

	private Point findCellSpanning(int col, int row, KTableModel model) {
	    Point spanning = new Point(1,1);
	    Point cell = new Point(col, row);
	    while (model.belongsToCell(col+spanning.x, row).equals(cell))
	        spanning.x++;

	    while (model.belongsToCell(col, row+spanning.y).equals(cell))
	        spanning.y++;

	    return spanning;
	}


	protected String getHTMLForSelection(Point[] selection) {
	    StringBuffer html = new StringBuffer();
	    sortSelectedCells(selection);

	    Point[] dimensions = findTableDimensions(selection);
	    Point topLeft = dimensions[0];
	    Point bottomRight = dimensions[1];

	    KTableModel model = m_table.getModel();
        if (model==null) return "";

        // add header:
	    html.append("Version:1.0\n");
	    html.append("StartHTML:0000000000\n");
	    html.append("EndHTML:0000000000\n");
	    html.append("StartFragment:0000000000\n");
	    html.append("EndFragment:0000000000\n");
	    html.append("<html><body><table>");


	    Point nextValidCell = selection[0];
	    int selCounter = 1;
	    for (int row = topLeft.y; row<=bottomRight.y; row++) {
	        html.append("<tr>");
	        for (int col = topLeft.x; col<=bottomRight.x; col++) {

	            if (model.belongsToCell(col, row)!=null){
	            	// may skip the cell when it is spanned by another one.
	            	if(model.belongsToCell(col, row).equals(new Point(col, row))) {

		                if (nextValidCell.x == col && nextValidCell.y == row) {
			                html.append("<td");
		                    Point spanning = findCellSpanning(col, row, model);
			                if (spanning.x>1)
			                    html.append(" colspan=\""+spanning.x+"\"");
			                if (spanning.y>1)
			                    html.append(" rowspan=\""+spanning.y+"\"");
		                    html.append(">");

		                    Object content = model.getContentAt(col, row);
		                    html.append(maskHtmlChars(content.toString()));
		                    if (selCounter<selection.length) {
		                        nextValidCell = selection[selCounter];
		                        selCounter++;
		                    }
		                }
		                else
			                html.append("<td>");

		                html.append("</td>");

	            	}
	            }

	        }
	        html.append("</tr>");
	    }
	    html.append("</table></body></html>");

	    return html.toString();
	}

	private String maskHtmlChars(String text) {
	    text = text.replaceAll("&", "&amp;");
	    text = text.replaceAll("ä","&auml;");
	    text = text.replaceAll("Ä", "&Auml;");
	    text = text.replaceAll("ö", "&ouml;");
	    text = text.replaceAll("Ö", "&Ouml;");
	    text = text.replaceAll("ü", "&uuml;");
	    text = text.replaceAll("Ü", "&Uuml;");
	    text = text.replaceAll("ß", "&szlig;");
	    text = text.replaceAll("\"", "&quot;");
	    text = text.replaceAll("<", "&lt");
	    text = text.replaceAll(">", "&gt");
	    text = text.replaceAll("€", "&euro;");
	    return text;
	}

	protected String getTextForSelection(Point[] selection) {
	    StringBuffer text = new StringBuffer();
	    Point topLeft = sortSelectedCells(selection);
	    KTableModel model = m_table.getModel();
        if (model==null) return "";

	    int currentCol = topLeft.x;
	    for (int i=0; i<selection.length; i++) {
	        for (; currentCol<selection[i].x; currentCol++)
	            text.append(TAB);

	        Object content = model.getContentAt(selection[i].x, selection[i].y);
	        text.append(content.toString());

	        if (i+1<selection.length) {
	            for (int row = selection[i].y; row<selection[i+1].y; row++)
	                text.append(PlatformLineDelimiter);
	            if (selection[i].y!=selection[i+1].y)
	                currentCol=topLeft.x;
	        }
	    }
	    if(text.toString().length()==0)
	    	return " ";
	    return text.toString();
	}

	protected String getRTFForSelection(Point[] selection) {
	    return getTextForSelection(selection);
	}

	protected Point sortSelectedCells(Point[] selection) {
	    Arrays.sort(selection, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Point p1 = (Point)o1;
                Point p2 = (Point)o2;
                if (p1.y<p2.y) return -1;
                if (p1.y>p2.y) return +1;
                if (p1.x<p2.x) return -1;
                if (p1.x>p2.x) return +1;
                return 0;
            }

	    });

	    int minCol = selection[0].x;
	    for (int i=1; i<selection.length; i++)
	        if (selection[i].x<minCol) minCol=selection[i].x;
	    return new Point(minCol, selection[0].y);
	}




}














