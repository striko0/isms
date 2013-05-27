package hr.ante.test.asktable.editors;


import hr.ante.test.asktable.listeners.AmountVerifyKeyListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;

public class ASKTableCellEditorCurrency3 extends KTableCellEditor {
	protected Text m_Text;
	protected double m_TextToDouble;
	//private Cursor m_TextCursor = new Cursor(Display.getDefault(), SWT.Cursor);


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
		m_Text.setText(getEditorContent());
		m_Text.addVerifyListener(new AmountVerifyKeyListener());
		m_Text.selectAll();
		m_Text.setVisible(true);
		m_Text.setFocus();
		}

	/**
	 * Overwrite this method if you do not want to use the content provided by
	 * the model.
	 *
	 * @return The content for the editor.
	 */
	protected String getEditorContent() {
		String str = m_Model.getContentAt(m_Col, m_Row).toString().trim();
		if (str.length() < 1)
			str = "0,00";
		String editString = formatString(str, true);
		return editString;
	}

	public void close(boolean save) {

		NumberFormat numberFormat = null;
		if (save) {
			numberFormat = new DecimalFormat("#,##0.00");
			// numberFormat.getCurrencyInstance(Locale.getDefault());
			if(m_Text.getText().equals("-")){
				m_Text.setText("0,00");

			}
			if (m_Text.getText() != null && m_Text.getText().length() > 0) {


				String formatedString = formatString(m_Text.getText(), false);
				m_TextToDouble = Double.parseDouble(formatedString);

				/** Za ispis simbola */
				// String currencySymbol =
				// Currency.getInstance(Locale.getDefault()).getSymbol();
				System.out.println("Ovo je integer" + m_TextToDouble
						+ " a ovo je m_text " + m_Text.getText());
				// numberFormat.format(m_TextToInt));
				// m_Model.setContentAt(m_Col, m_Row,
				// numberFormat.getCurrencyInstance().format(m_TextToInt));
				m_Model.setContentAt(m_Col, m_Row,
						numberFormat.format(m_TextToDouble));
			}
			m_Text.setText("0,00");


			// }
			// close(false);
		}
		m_Text.removeKeyListener(keyListener);
		m_Text.removeTraverseListener(travListener);
		super.close(save);
		m_Text = null;

//		if (m_Text != null) {
//			m_Text = null;
//			m_TextToDouble = (Double) null;
//		}

	}

	protected Control createControl() {
		m_Text = new Text(m_Table, SWT.NONE | SWT.RIGHT);;
		m_Text.addKeyListener(keyListener);
		m_Text.addTraverseListener(travListener);
		//m_Text.setCursor(m_ArrowCursor);
		return m_Text;
	}


	/**
	 * Implement In-Textfield navigation with the keys...
	 *
	 * @see de.kupzog.ktable.KTableCellEditor#onTraverse(org.eclipse.swt.events.TraverseEvent)
	 */
	protected void onTraverse(TraverseEvent e) {
		if (e.keyCode == SWT.ARROW_LEFT) {
			if (m_Text.getCaretPosition() == 0 && m_Text.getSelectionCount() == 0)
				super.onTraverse(e);
			// handel the event within the text widget!
		} else if (e.keyCode == SWT.ARROW_RIGHT) {
			if (m_Text.getCaretPosition() == m_Text.getText().length()
					&& m_Text.getSelectionCount() == 0)
				super.onTraverse(e);
			// handle the event within the text widget!
		} else
			super.onTraverse(e);
	}

	protected void onKeyPressed(KeyEvent e) {
		if(e.keyCode == SWT.DEL)
		{
				m_Model.setContentAt(m_Col, m_Row, null);

		}
		// If whole text selected, delete it with backspace
		if (e.character == '\b')
				if(m_Text.getSelectionCount()==m_Text.getText().length()) {
					m_Model.setContentAt(m_Col, m_Row, null);

		}
		if ((e.character == '\r') && ((e.stateMask & SWT.SHIFT) == 0)) {
			close(true);
			// move one row below!
			// if (m_Row<m_Model.getRowCount())
			// m_Table.setSelection(m_Col, m_Row+1, true);
		}
		else if (e.character == SWT.ESC) {
			close(false);
		}

		else
			super.onKeyPressed(e);
	}

	/*Format number*/

	protected String formatString(String text, Boolean editing) {
		StringBuffer sb = new StringBuffer();
		char[] chars = text.toCharArray();
		for (int i = 0; i < text.length(); i++) {
			char chr = text.charAt(i);
			if (editing) {
				if (!Character.isDigit(chr)) {
					if (chr == ',')
						sb.append(',');
					if (chr == '-') {
						sb.append('-');
					}
					if (chr == '.')
						continue;
				} else {
					sb.append(chr);
				}
			} else {
				if (!Character.isDigit(chr)) {
					if (chr == ',')
						sb.append('.');
					if (chr == '-')
						sb.append('-');
					if (chr == '.')
						continue;
				} else {
					sb.append(chr);
				}
			}
		}
		return sb.toString();
	}


	/*
	 * overridden from superclass
	 */
	public void setBounds(Rectangle rect) {
		super.setBounds(new Rectangle(rect.x, rect.y + (rect.height - 15) / 2
				+ 1, rect.width, 15));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableCellEditor#setContent(java.lang.Object)
	 */

	/** NIJE TESTIRANO **/
	public void setContent(Object content) {
		m_Text.setText(content.toString());
		m_Text.setSelection(content.toString().length());
	}

}
