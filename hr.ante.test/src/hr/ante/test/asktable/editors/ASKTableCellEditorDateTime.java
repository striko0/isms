package hr.ante.test.asktable.editors;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;

/*******************************************************************************
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software All rights
 * reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Friederich Kupzog - initial API and implementation
 * fkmk@kupzog.de www.kupzog.de/fkmk
 ******************************************************************************/
class ASKTableCellEditorDateTime extends KTableCellEditor {

	private DateTime m_DateTime;
	private DateTime m_OnOpenDateTime; 
	protected Text m_TempText;
	private boolean spremi=false;
	private Display display;
	private Shell dialog ;
	
	public KeyAdapter keyListener = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			try {

                onKeyPressed(e);
			} catch (Exception ex) {
				// Do nothing
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
		
		
	

	}
	
	
	public void openCalendarDialog(Composite composite){
				
		dialog = new Shell (SWT.DIALOG_TRIM);
		dialog.setLayout (new GridLayout (3, false));

		final DateTime calendar = new DateTime (dialog, SWT.CALENDAR);
//		final DateTime date = new DateTime (dialog, SWT.DATE | SWT.SHORT);
//		final DateTime time = new DateTime (dialog, SWT.TIME | SWT.SHORT);

//		new Label (dialog, SWT.NONE);
//		new Label (dialog, SWT.NONE);
		Button ok = new Button (dialog, SWT.PUSH);
		ok.setText ("OK");
		ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
		calendar.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
//			
//				System.out.println ("Calendar date selected (MM/DD/YYYY) = " + (calendar.getMonth () + 1) + "/" + calendar.getDay () + "/" + calendar.getYear ());
//				System.out.println ("Date selected (MM/YYYY) = " + (date.getMonth () + 1) + "/" + date.getYear ());
//				System.out.println ("Time selected (HH:MM) = " + time.getHours () + ":" + (time.getMinutes () < 10 ? "0" : "") + time.getMinutes ());
				m_DateTime.setDay(calendar.getDay ());
				m_DateTime.setMonth(calendar.getMonth ());
				m_DateTime.setYear(calendar.getYear ());
				dialog.close ();
			}
		});
		dialog.setDefaultButton (ok);
		dialog.pack ();
		dialog.open ();
		
	}
	
	
	
	
	
	
	
	//Gets contents from cell and parses to date format 
	public void getDayMonthYearFromCell(){
				
		//U celiji mora biti upisan datum kako bi se ovo uspješno izvelo
		if (m_TempText.getCharCount() > 6){
			//Set Day
			m_DateTime.setDay(Integer.parseInt(m_TempText.getText(0, 1)));
			//Set Month
			m_DateTime.setMonth((Integer.parseInt(m_TempText.getText(3, 4)))-1);
			//Set Year
			m_DateTime.setYear(Integer.parseInt(m_TempText.getText(6, 9)));
			
			m_OnOpenDateTime = m_DateTime;
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
		
		else
			super.close(save);
		

		if (m_DateTime!=null && spremi==true) {
			super.close(save);
			//m_DateTime.dispose();
			//m_TempText.dispose();
			m_DateTime = null;
			m_TempText = null;
			spremi =false;	
		}
		
		
		
		
		
	}

	protected Control createControl() {
		
		m_TempText = new Text(m_Table, SWT.NONE);
		m_DateTime = new DateTime(m_Table, SWT.PUSH);

		m_DateTime.addKeyListener(keyListener);
		m_DateTime.addTraverseListener(travListener);		
		
	//	m_DateTime.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));			
		
		m_DateTime.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		           // close(true);
		        	
		        	System.out.println("Focus lost");
		          }
			
		});

		
		m_DateTime.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				openCalendarDialog(m_Table);
				//System.out.println("stisnuto 1" + m_DateTime.toString());
				spremi=true;
				close(true);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		

		/*
		 * m_DateTime.addTraverseListener(new TraverseListener() { public void
		 * keyTraversed(TraverseEvent arg0) { onTraverse(arg0); } });
		 */
		
		return m_DateTime;
	}
//	public void setDate(DateTime items[]) {
//	    m_Items = items;
//	  }

	/*
	 * overridden from superclass
	 */
	public void setBounds(Rectangle rect) {
		super.setBounds(new Rectangle(rect.x, rect.y + 1, rect.width,
				rect.height - 2));
	}

	
	//Treba testirati ovu metodu:
	@Override
	public void setContent(Object content) {
		m_TempText.setText(content.toString());
		getDayMonthYearFromCell();
		
		
	}

}