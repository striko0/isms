package hr.ante.test.asktable.editors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;

public class ASKTableCellEditorTime extends KTableCellEditor {

	private DateTime m_DateTime;
	protected Text m_TempText;

	 protected KeyAdapter keyListener = new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            try {

	                onKeyPressed(e);
	            } catch (Exception ex) {
	                ex.printStackTrace();

	            }
	        }
	    };

	    protected TraverseListener travListener = new TraverseListener() {
	        public void keyTraversed(TraverseEvent e) {
	            onTraverse(e);
	        }
	    };


	public void open(KTable table, int col, int row, Rectangle rect) {
		 super.open(table, col, row, rect);
		m_TempText.setText(getEditorContent());
		getCellContents();
		m_DateTime.setFocus();
		m_DateTime.setVisible(true);


	}

	// Gets contents from cell and parses to date format
	public void getCellContents() {

		// U celiji mora biti upisan datum kako bi se ovo uspješno izvelo
		if (m_TempText.getCharCount() > 6) {
			// Set Day
			m_DateTime.setHours(Integer.parseInt(m_TempText.getText(0, 1)));
			// Set Month
			m_DateTime.setMinutes(Integer.parseInt(m_TempText.getText(3, 4)));
			// Set Year
			m_DateTime.setSeconds(Integer.parseInt(m_TempText.getText(6, 7)));
		}

	}

	protected String getEditorContent() {
		return m_Model.getContentAt(m_Col, m_Row).toString();
	}

	public void close(boolean save) {
		if (save) {

			Calendar instance = Calendar.getInstance();

//			Formatter fmt = new Formatter();

		    /**PROBLEM SA SATIMA */
			instance.set(Calendar.HOUR_OF_DAY, m_DateTime.getHours());
			instance.set(Calendar.MINUTE, m_DateTime.getMinutes());
			instance.set(Calendar.SECOND, m_DateTime.getSeconds());

			String dateString = new SimpleDateFormat("HH:mm:ss", Locale.UK).format(instance.getTime());
//			fmt.format("%tT", instance);
			m_Model.setContentAt(m_Col, m_Row, dateString);


//			String dateString = new SimpleDateFormat("kk:mm:ss").format(instance.getTime());
//			m_Model.setContentAt(m_Col, m_Row, dateString);
			//System.out.println(m_DateTime.toString());
			//m_Model.setContentAt(m_Col, m_Row, fmt.toString());


		}

		m_DateTime.removeKeyListener(keyListener);
		m_DateTime.removeTraverseListener(travListener);
		super.close(save);
		m_DateTime = null;
		m_TempText = null;
		//if (m_DateTime!=null && m_TempText!=null) {
		//}


	}

	protected Control createControl() {
		m_TempText = new Text(m_Table, SWT.NONE);
		m_DateTime = new DateTime(m_Table, SWT.TIME | SWT.LONG);
		m_DateTime.addKeyListener(keyListener);
		m_DateTime.addTraverseListener(travListener);


//		m_DateTime.addFocusListener(new FocusAdapter() {
//	        public void focusLost(FocusEvent arg0) {
//	           // close(true);
//	            System.out.println("Focus lost");
//	          }
//	        });


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
	    } else if (e.keyCode == SWT.ARROW_RIGHT) {
	        // handle the event within the text widget!

	    }
	    else if (e.keyCode == SWT.ARROW_UP) {
	    	// handel the event within the text widget!
	    }
	    else if (e.keyCode == SWT.ARROW_DOWN) {
	    	// handel the event within the text widget!
	    }else
	        super.onTraverse(e);
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
		} else
		    super.onKeyPressed(e);
	}

	/*
	 * overridden from superclass
	 */

	public void setBounds(Rectangle rect) {
		super.setBounds(new Rectangle(rect.x, rect.y + 1, rect.width,
				rect.height - 2));
	}

	@Override
	public void setContent(Object content) {
		// TODO Auto-generated method stub


	}

}