package hr.ante.isms.parts;

import hr.ante.test.asktable.ASKTableContextMenu2;
import hr.ante.test.asktable.ASTableModel4;
import hr.ante.test.asktable.comparator.ASKTableSortOnClick2;
import hr.ante.test.asktable.comparator.ASSortComparatorExample2;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.KTableClickInterceptionListener;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;
import swing2swt.layout.FlowLayout;

public class CopyOfThreats2 {
	private KTable table;
	private ASTableModel4 model;
//
//	public Threats1() {
//	}

	@PostConstruct
	public void createComposite(final Composite parent) {
		parent.setSize(new Point(759, 390));




		//parent.getShell().setMinimumSize(759,390);
		//parent.getShell().setMinimumSize(759, 390);
		//parent.getShell().setSize(780, 410);
		parent.getShell().setText("Identifikacija prijetnji za imovinu: --***--");
		FormLayout layout = new FormLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		parent.setLayout(layout);

//		FormData data = new FormData();
//		data.top = new FormAttachment(dogNameText, 0, SWT.CENTER);
//		dogName.setLayoutData(data);
//		data = new FormData();
//		data.left = new FormAttachment(dogName, 5);
//		data.right = new FormAttachment(100, 0);
//		dogNameText.setLayoutData(data);

		Composite composite = new Composite(parent, SWT.INHERIT_NONE);
		composite.setLayout(new FormLayout());

		Label lblVrstaPrijet_ = new Label(composite, SWT.NONE);
		lblVrstaPrijet_.setText("Vrsta prijetnje:");

		Combo comboVrstaPrijet_ = new Combo(composite, SWT.NONE);

		Label lblPrijetnja_ = new Label(composite, SWT.NONE);
		lblPrijetnja_.setText("Prijetnja:");

		Combo comboPrijetnja_ = new Combo(composite, SWT.NONE);


		Composite compositeASKTable = new Composite(composite, SWT.INHERIT_FORCE);
		compositeASKTable.setLayout(new FillLayout());
		new TableThreats(compositeASKTable, 717,compositeASKTable.getBounds().height );

		Composite compositeButtons_ = new Composite(parent, SWT.NONE);
		compositeButtons_.setLayout(new GridLayout(4, false));


		Button btnSpremi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnSpremi_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnSpremi_.widthHint = 100;
		btnSpremi_.setLayoutData(gd_btnSpremi_);
		btnSpremi_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSpremi_.setText("Spremi");

		Button buttonNovo_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_buttonNovo_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_buttonNovo_.widthHint = 100;
		buttonNovo_.setLayoutData(gd_buttonNovo_);
		buttonNovo_.setText("Novo");

		Button btnBrisi_ = new Button(compositeButtons_, SWT.CENTER);
		GridData gd_btnBrisi_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBrisi_.widthHint = 100;
		btnBrisi_.setLayoutData(gd_btnBrisi_);
		btnBrisi_.setText("Bri\u0161i");

		Button btnIzlaz_ = new Button(compositeButtons_, SWT.NONE);
		GridData gd_btnIzlaz_ = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnIzlaz_.widthHint = 100;
		btnIzlaz_.setLayoutData(gd_btnIzlaz_);
		btnIzlaz_.setText("Izlaz");
		btnIzlaz_.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				parent.getShell().close();
			}

		});


		//************composite layout
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(80, 0);
		data.right = new FormAttachment(100, 0);
//		fd_composite.right = new FormAttachment(parent);
		composite.setLayoutData(data);

		//**********lblVrstaPrijet_ layout
		FormData data1 = new FormData();
		data1.right = new FormAttachment(0, 88);
		data1.top = new FormAttachment(0, 13);
		data1.left = new FormAttachment(0, 15);
		lblVrstaPrijet_.setLayoutData(data1);

		//**********comboVrstaPrijet_ layout
		FormData data2 = new FormData();
		data2.left = new FormAttachment(lblVrstaPrijet_, 6);
		data2.top = new FormAttachment(0, 10);
		data2.right = new FormAttachment(100, 0);
		comboVrstaPrijet_.setLayoutData(data2);

		//**********lblPrijetnja_ layout
		FormData data3 = new FormData();
		data3.right = new FormAttachment(0, 73);
		data3.top = new FormAttachment(0, 43);
		data3.left = new FormAttachment(0, 15);
		lblPrijetnja_.setLayoutData(data3);

		//**********comboVrstaPrijet_ layout
		//fd_comboVrstaPrijet_.right = new FormAttachment(comboVrstaPrijet_, 0, SWT.RIGHT);
		FormData data4 = new FormData();
		data4.left = new FormAttachment(lblPrijetnja_, 21);
		data4.top = new FormAttachment(0, 40);
		data4.right = new FormAttachment(100, 0);
		comboPrijetnja_.setLayoutData(data4);

		//**********compositeASKTable layout
		//fd_comboPrijetnja_.right = new FormAttachment(compositeASKTable, 0, SWT.RIGHT);
		FormData data5 = new FormData();
		// fd_compositeASKTable.bottom = new FormAttachment(0, 309);
		// fd_compositeASKTable.right = new FormAttachment(0, 747);
		data5.bottom = new FormAttachment(100, 0);
		data5.right = new FormAttachment(100, 0);
		data5.top = new FormAttachment(0, 75);
		data5.left = new FormAttachment(0, 10);
		compositeASKTable.setLayoutData(data5);

		//**********compositeButtons_ layout
		FormData data6 = new FormData();
		data6.top = new FormAttachment(composite,6);
		data6.right = new FormAttachment(100, 0);
	//	data6.bottom = new FormAttachment(100, 0);
		compositeButtons_.setLayoutData(data6);












		// createASKtable(compositeASKTable);


//
//
//		FormData data = new FormData();
//		data.top = new FormAttachment(comboVrstaPrijet_, 0, SWT.CENTER);
//		lblVrstaPrijet_.setLayoutData(data);
//		data = new FormData();
//		data.left = new FormAttachment(lblVrstaPrijet_, 5);
//		data.right = new FormAttachment(100, 0);
//		comboVrstaPrijet_.setLayoutData(data);
//
//		data = new FormData();
//		data.top = new FormAttachment(comboPrijetnja_, 0, SWT.CENTER);
//		lblPrijetnja_.setLayoutData(data);
//
//		data = new FormData();
//		data.top = new FormAttachment(comboPrijetnja_, 5);
//		data.left = new FormAttachment(comboVrstaPrijet_, 0, SWT.LEFT);
//		data.right = new FormAttachment(100, 0);
//		comboPrijetnja_.setLayoutData(data);
//
//		data = new FormData(700, 80);
//		data.top = new FormAttachment(comboPrijetnja_, 5);
//		data.left = new FormAttachment(comboVrstaPrijet_, 0, SWT.LEFT);
//		data.right = new FormAttachment(100, 0);
//		data.bottom = new FormAttachment(compositeButtons_, -5);
//		compositeASKTable.setLayoutData(data);
//
//		data = new FormData();
//		data.right = new FormAttachment(100, 0);
//		data.bottom = new FormAttachment(100, 0);
//		compositeButtons_.setLayoutData(data);
//
//		parent.pack();






	}

	private void createASKtable(Composite composite) {
		// TODO Auto-generated method stub
		table = new KTable(composite,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWTX.FILL_WITH_LASTCOL);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		model = new ASTableModel4();
		//table.setSize(parent.getSize());

		table.setLayout(new FillLayout());
		table.setBounds(0,0, 717, 234);
		table.setModel(model);
		table.setData("org.eclipse.e4.ui.css.id", "ASKTable");



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
