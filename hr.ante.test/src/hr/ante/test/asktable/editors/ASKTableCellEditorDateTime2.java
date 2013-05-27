package hr.ante.test.asktable.editors;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;

public class ASKTableCellEditorDateTime2 extends KTableCellEditor {

	private DateTime m_DateTime;
	protected Text m_TempText;
	private boolean spremi=false;
	private Rectangle cellBounds ;
	Point clickLocation;

	public KeyAdapter keyListener = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			try {
				 spremi = true;
				onKeyPressed(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	};

	private TraverseListener travListener = new TraverseListener() {
		public void keyTraversed(TraverseEvent e) {

			 spremi = true;
			 onTraverse(e);
			 }

	};

	protected String getEditorContent() {
		return m_Model.getContentAt(m_Col, m_Row).toString();
	}


	public void open(KTable table, int col, int row, Rectangle rect) {
		super.open(table, col, row, rect);
		m_TempText.setText(getEditorContent());
		getDayMonthYearFromCell();
		m_DateTime.setVisible(true);
		m_DateTime.setFocus();

//		cellBounds = m_Table.getCellRect(m_Col, m_Row);
//		clickLocation = new Point(get, cellBounds.height);
//


	}

	//Gets contents from cell and parses to date format
	public void getDayMonthYearFromCell(){

		/**U celiji mora biti upisan datum kako bi se ovo uspješno izvelo**/
		if (m_TempText.getCharCount() > 6 && m_Row !=0){
			//Set Day
			m_DateTime.setDay(Integer.parseInt(m_TempText.getText(0, 1)));
			//Set Month
			m_DateTime.setMonth((Integer.parseInt(m_TempText.getText(3, 4)))-1);
			//Set Year
			m_DateTime.setYear(Integer.parseInt(m_TempText.getText(6, 9)));

			}

	}




	public void close(boolean save) {
		if (save) {

			Calendar instance = Calendar.getInstance();
			instance.set(Calendar.DAY_OF_MONTH, m_DateTime.getDay());
			instance.set(Calendar.MONTH, m_DateTime.getMonth());
			instance.set(Calendar.YEAR, m_DateTime.getYear());
			String dateString = new SimpleDateFormat("dd.MM.yyyy").format(instance.getTime());

			m_Model.setContentAt(m_Col, m_Row, dateString);
			m_DateTime.removeKeyListener(keyListener);
			m_DateTime.removeTraverseListener(travListener);

		}
		if (m_DateTime!=null && spremi==true) {

			super.close(save);
			m_DateTime.dispose();
			//m_TempText.dispose();
			m_DateTime = null;
			m_TempText = null;
			spremi=false;
		}
	}

	protected Control createControl() {

		m_TempText = new Text(m_Table, SWT.NONE);
		m_DateTime = new DateTime(m_Table, SWT.DROP_DOWN );
		m_DateTime.addKeyListener(keyListener);
		m_DateTime.addTraverseListener(travListener);


	//	m_DateTime.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));


//        return new KTableCellEditorCheckbox2(sensible, SWTX.ALIGN_HORIZONTAL_CENTER, SWTX.ALIGN_VERTICAL_CENTER)

//		m_DateTime.addListener(SWT.MouseDown, new Listener() {
//
//		@Override
//		public void handleEvent(Event event) {
//				// TODO Auto-generated method stub
//
//				cellBounds = m_Table.getCellRect(m_Col, m_Row);
//				clickLocation = new Point(cellBounds.width, cellBounds.height);
//
//			}
//		});

//		m_DateTime.addListener(SWT.BUTTON1, new Listener() {
//
//			@Override
//			public void handleEvent(Event event) {
//					// TODO Auto-generated method stub
//				if (cellBounds.contains(clickLocation)){
//					spremi=true;
//					close(true);
//
//				}
//
//				}
//			});
//
//		m_DateTime.addListener(SWT.MouseUp, new Listener() {
//
//			@Override
//			public void handleEvent(Event event) {
//					// TODO Auto-generated method stub
//
//
//					if (cellBounds.contains(clickLocation)){
//						spremi=true;
//						close(true);
//
//					}
//				}
//			});

		m_DateTime.addListener(SWT.MouseExit, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println("MOUSE EXIT 1" + m_DateTime.toString());
				spremi=true;
				close(true);
			}
		});

//		m_DateTime.addSelectionListener(new SelectionAdapter() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
////				openCalendarDialog(m_Table);
//				System.out.println("stisnuto 1" + m_DateTime.toString());
//				spremi=true;
//				close(true);
//
//			}
//		});

		m_DateTime.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
			  /* if (!m_DateTime.getEnabled())
			    	calendarFocusLost=true;*/


				System.out.println("Focus lost");
			}

		});


		/*
		 * m_DateTime.addTraverseListener(new TraverseListener() { public void
		 * keyTraversed(TraverseEvent arg0) { onTraverse(arg0); } });
		 */

		return m_DateTime;
	}

	/**
	 * Implement In-Textfield navigation with the keys...
	 * @see de.kupzog.ktable.KTableCellEditor#onTraverse(org.eclipse.swt.events.TraverseEvent)
	 */
	protected void onTraverse(TraverseEvent e) {
	    if (e.keyCode == SWT.ARROW_LEFT) {
	        // handel the event within the text widget!
	    }else if (e.keyCode == SWT.ARROW_RIGHT) {
	        // handle the event within the text widget!

	    }
	    else if (e.keyCode == SWT.ARROW_UP) {
	    	// handel the event within the text widget!
	    }
	    else if (e.keyCode == SWT.ARROW_DOWN) {
	    	// handel the event within the text widget!
	    }
	}

	protected void onKeyPressed(KeyEvent e) {
		if(e.keyCode == SWT.DEL)
		{
			m_Model.setContentAt(m_Col, m_Row, null);

			close(false);
		}
		if ((e.character == '\r')  && ((e.stateMask & SWT.SHIFT) == 0)) {
			close(true);
			// move one row below!
//			if (m_Row<m_Model.getRowCount())
//			    m_Table.setSelection(m_Col, m_Row+1, true);
		} else if (e.character == SWT.ESC) {
			close(false);
		}

	}

	/*
	 * overridden from superclass
	 */
	public void setBounds(Rectangle rect) {
		super.setBounds(new Rectangle(rect.x, rect.y + 1, rect.width,
				rect.height - 2));
	}




	/**Treba testirati ovu metodu:**/
	@Override
	public void setContent(Object content) {
		m_TempText.setText(content.toString());
		getDayMonthYearFromCell();


	}



}