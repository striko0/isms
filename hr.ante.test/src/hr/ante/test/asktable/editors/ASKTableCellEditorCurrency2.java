package hr.ante.test.asktable.editors;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.SWTX;

public class ASKTableCellEditorCurrency2 extends KTableCellEditor {
	protected Text m_Text;
	protected double m_TextToInt;
	private static final String REGEX = "[-]?[0-9]*[.]?[0-9]*";/*"[(]\\d{3}[)]\\d{3}[-]\\d{4}"*/;  //$NON-NLS-1$
	private static final String template = "#,##0.00"; //$NON-NLS-1$
	private static final String defaultText = "0.00"; //$NON-NLS-1$

	protected KeyAdapter keyListener = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			try {
				onKeyPressed(e);
			} catch (Exception ex) {
				ex.printStackTrace();
				// Do nothing
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
		return m_Model.getContentAt(m_Col, m_Row).toString();
	}

	public void close(boolean save) {

		NumberFormat numberFormat = null;
		if (save) {

			numberFormat = new DecimalFormat("#,##0.00");
			//numberFormat.getCurrencyInstance(Locale.getDefault());
			
			if (m_Text.getText() != null  && m_Text.getText().length()>0)
				m_TextToInt = Double.parseDouble(m_Text.getText());
			else{
			m_TextToInt=0;
			}
			
			/**Za ispis simbola*/
			
//			String currencySymbol = Currency.getInstance(Locale.getDefault())
//					.getSymbol();
			System.out.println("Ovo je integer" + m_TextToInt
					+ " a ovo je m_text " + m_Text.getText());
			// m_Model.setContentAt(m_Col, m_Row,
			// numberFormat.format(m_TextToInt));
			
//			m_Model.setContentAt(m_Col, m_Row, numberFormat.getCurrencyInstance().format(m_TextToInt));
			m_Model.setContentAt(m_Col, m_Row, numberFormat.format(m_TextToInt));

			m_Text.removeKeyListener(keyListener);
			m_Text.removeTraverseListener(travListener);
			
			
			
		}
		super.close(save);
		m_Text = null;
	}

	protected Control createControl() {
		m_Text = new Text(m_Table, SWT.NONE | SWTX.ALIGN_HORIZONTAL_RIGHT);
		m_Text.addListener(SWT.Verify, new Listener() {
							
				//create the pattern for verification
				Pattern pattern = Pattern.compile(REGEX);	
				//ignore event when caused by inserting text inside event handler
				boolean ignore;
				public void handleEvent(Event e) {
					if (ignore) return;
					e.doit = false;
					if (e.start > 13 || e.end > 14) return;
					StringBuffer buffer = new StringBuffer(e.text);
					
					//handle backspace
					if (e.character == '\b') {
						for (int i = e.start; i < e.end; i++) {
							// skip over separators
							switch (i) {
								case 0: 
									if (e.start + 1 == e.end) {
										return;
									} else {
										buffer.append('(');
									}
									break;
								case 4:
									if (e.start + 1 == e.end) {
										buffer.append(new char [] {'#',')'});
										e.start--;
									} else {
										buffer.append(')');
									}
									break;
								case 8:
									if (e.start + 1 == e.end) {
										buffer.append(new char [] {'#','-'});
										e.start--;
									} else {
										buffer.append('-');
									}
									break;
								default: buffer.append('#');
							}
						}
						m_Text.setSelection(e.start, e.start + buffer.length());
						ignore = true;
						m_Text.insert(buffer.toString());
						ignore = false;
						// move cursor backwards over separators
						if (e.start == 5 || e.start == 9) e.start--;
						m_Text.setSelection(e.start, e.start);
						return;
					}
					
					StringBuffer newText = new StringBuffer(defaultText);
					char[] chars = e.text.toCharArray();
					int index = e.start - 1;
					for (int i = 0; i < e.text.length(); i++) {
						index++;
						switch (index) {
							case 0:
								if (chars[i] == '(') continue;
								index++;
								break;
							case 4:
								if (chars[i] == ')') continue;
								index++;
								break;
							case 8:
								if (chars[i] == '-') continue;
								index++;
								break;
						}
						if (index >= newText.length()) return;
						newText.setCharAt(index, chars[i]);
					}
					
					// if text is selected, do not paste beyond range of selection
					if (e.start < e.end && index + 1 != e.end) return;
					Matcher matcher = pattern.matcher(e.text);
					if (matcher.lookingAt()) {
						m_Text.setSelection(e.start, index + 1);
						ignore = true;
						m_Text.insert(newText.substring(e.start, index + 1));
						ignore = false;
					}		
				}
				
			});
		m_Text.addKeyListener(keyListener);
		m_Text.addTraverseListener(travListener);
		return m_Text;
	}
						
					
				
				
				
				
				
				
				
				
//				
//				String string = e.text;
//
//				char[] chars = new char[string.length()];
//
//				string.getChars(0, chars.length, chars, 0);
//
//				for (int i = 0; i < chars.length; i++) {
//					if (!('0' <= chars[i] && chars[i] <= '9' || chars[i] == '.')) {
//						e.doit = false;
//						return;
//					}
//				}
//			}
		

		
				
		
		

	/**
	 * Implement In-Textfield navigation with the keys...
	 * 
	 * @see de.kupzog.ktable.KTableCellEditor#onTraverse(org.eclipse.swt.events.TraverseEvent)
	 */
	protected void onTraverse(TraverseEvent e) {
		if (e.keyCode == SWT.ARROW_LEFT) {
			if (m_Text.getCaretPosition() == 0
					&& m_Text.getSelectionCount() == 0)
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
		if ((e.character == '\r') && ((e.stateMask & SWT.SHIFT) == 0)) {
			close(true);
			// move one row below!
			// if (m_Row<m_Model.getRowCount())
			// m_Table.setSelection(m_Col, m_Row+1, true);
		} else
			super.onKeyPressed(e);
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
