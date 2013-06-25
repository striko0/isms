/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package hr.ante.test.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.obutton.OButton;
import org.mihalis.opal.propertyTable.PTProperty;
import org.mihalis.opal.propertyTable.PropertyTable;
import org.mihalis.opal.propertyTable.editor.PTDateEditor;
import org.mihalis.opal.textAssist.TextAssistContentProvider;
import org.mihalis.opal.tipOfTheDay.TipOfTheDay;
import org.eclipse.swt.widgets.Button;

public class SamplePart {

	private Label label;
	private PropertyTable table;
	private TableColumn tableColumn;
	private DateTime dateTime_1;
	private OButton button;
	private PropertyTable propertyTable;
	private DateTime dateTime_2;
	private Button btnNewButton;


	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout());

		//label.setData("org.eclipse.e4.ui.css.id", "SampleLabel");
		label = new Label(parent, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setText("Sample table");
		new Label(parent, SWT.NONE);
		
		final DateTime dateTime = new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);
		dateTime.setDate(2013, 2, 1);
		  dateTime.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println(dateTime.toString());
				}
			});
		  
		  final TextAssistContentProvider contentProvider = new TextAssistContentProvider() {

				private final String[] EUROZONE = new String[] { "ante", "Austria", "Belgium", "Cyprus", "Estonia", "Finland", "France", "Germany", "Greece", "Ireland", "Italy", "Luxembourg", "Malta", "Netherlands", "Portugal", "Slovakia", "Slovenia", "Spain" };

				@Override
				public List<String> getContent(final String entry) {
					final List<String> returnedList = new ArrayList<String>();

					for (final String country : this.EUROZONE) {
						if (country.toLowerCase().startsWith(entry.toLowerCase())) {
							returnedList.add(country);
						}
					}

					return returnedList;
				}
			};
		
		dateTime_2 = new DateTime(parent, SWT.BORDER | SWT.TIME | SWT.LONG);
		GridData gd_dateTime_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateTime_2.widthHint = 195;
		dateTime_2.setLayoutData(gd_dateTime_2);
		
		dateTime_1 = new DateTime(parent, SWT.BORDER | SWT.DROP_DOWN);
		
		btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setText("New Button");
		
		button = new OButton(parent, SWT.ARROW);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		button.setWidth(100);
//		button.setButtonRenderer(buttonRenderer)
		button.setText("Obuton");
		
		propertyTable = new PropertyTable(parent, SWT.NONE);
		propertyTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		propertyTable.setFont(SWTResourceManager.getFont("Times New Roman", 8, SWT.NORMAL));
		propertyTable.hideButtons();
		propertyTable.hideDescription();
		propertyTable.addProperty(new PTProperty("ime", "Display Name", "Description", "Vrijednost 1")).setCategory("KAtegorija");
		propertyTable.addProperty(new PTProperty("Datum",  "Displaynmae", "OPIS")).setCategory("KAtegorija").setEditor(new PTDateEditor());
		
		
//		table = new PropertyTable(parent, SWT.BORDER | SWT.FULL_SELECTION);
//		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		
//		
//		tableColumn = new TableColumn(table, SWT.NONE);
//		tableColumn.setWidth(100);
//		tableColumn.setText("New Column");
		
		final Shell tipoftheday = new Shell();
		tipoftheday.setText("Tip of the Days snippet");
		tipoftheday.setLayout(new FillLayout(SWT.VERTICAL));
		
		final TipOfTheDay tip = new TipOfTheDay();
		tip.addTip("This is the first tip<br/> " + "<b>This is the first tip</b> " + "<u>This is the first tip</u> " + "<i>This is the first tip</i> " + "This is the first tip " + "This is the first tip<br/>" + "This is the first tip "
				+ "This is the first tip");
		tip.addTip("This is the second tip<br/> " + "<b>This is the second tip</b> " + "<u>This is the second tip</u> <br/>" + "<i>This is the second tip</i> " + "This is the second tip " + "This is the second tip <br/>" + "This is the second tip "
				+ "This is the second tip");

		tip.addTip("This is the third tip<br/> " + "<b>This is the third tip</b> " + "<u>This is the third tip</u> <br/>" + "<i>This is the third tip</i> ");
		
		
	}

	
}
