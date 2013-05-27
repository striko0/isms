/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron@gmail.com)
 *******************************************************************************/
package org.mihalis.opal.multiChoice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.utils.SimpleSelectionAdapter;

/**
 * A simple snippet for the MultiChoice Widget
 */
public class MultiChoiceSnippet {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(4, false));
		shell.setText("MultiChoice Example");

		// Data
		final String[] euroZone = new String[] { "Austria", "Belgium", "Cyprus", "Estonia", "Finland", "France", "Germany", "Greece", "Ireland", "Italy", "Luxembourg", "Malta", "Netherlands", "Portugal", "Slovakia", "Slovenia", "Spain" };

		final List<Country> membersOfEuropeanUnion = new ArrayList<Country>();
		membersOfEuropeanUnion.add(new Country("Austria", 8372930));
		membersOfEuropeanUnion.add(new Country("Belgium", 10827519));
		membersOfEuropeanUnion.add(new Country("Bulgaria", 7576751));
		membersOfEuropeanUnion.add(new Country("Cyprus", 801851));
		membersOfEuropeanUnion.add(new Country("Czech Republic", 10512397));
		membersOfEuropeanUnion.add(new Country("Denmark", 5547088));
		membersOfEuropeanUnion.add(new Country("Estonia", 1340274));
		membersOfEuropeanUnion.add(new Country("Finland", 5530575));
		membersOfEuropeanUnion.add(new Country("France", 64709480));
		membersOfEuropeanUnion.add(new Country("Germany", 81757595));
		membersOfEuropeanUnion.add(new Country("Greece", 11125179));
		membersOfEuropeanUnion.add(new Country("Hungary", 10013628));
		membersOfEuropeanUnion.add(new Country("Ireland", 4450878));
		membersOfEuropeanUnion.add(new Country("Italy", 60397353));
		membersOfEuropeanUnion.add(new Country("Latvia", 2248961));
		membersOfEuropeanUnion.add(new Country("Lithuania", 3329227));
		membersOfEuropeanUnion.add(new Country("Luxembourg", 502207));
		membersOfEuropeanUnion.add(new Country("Malta", 416333));
		membersOfEuropeanUnion.add(new Country("Netherlands", 16576800));
		membersOfEuropeanUnion.add(new Country("Poland", 38163895));
		membersOfEuropeanUnion.add(new Country("Portugal", 11317192));
		membersOfEuropeanUnion.add(new Country("Romania", 21466174));
		membersOfEuropeanUnion.add(new Country("Slovakia", 5424057));
		membersOfEuropeanUnion.add(new Country("Slovenia", 2054119));
		membersOfEuropeanUnion.add(new Country("Spain", 46087170));
		membersOfEuropeanUnion.add(new Country("Sweden", 9347899));
		membersOfEuropeanUnion.add(new Country("United Kingdom", 62041708));

		final List<Country> membersOfEUSelectAll = new ArrayList<Country>();
		membersOfEUSelectAll.addAll(membersOfEuropeanUnion);
		membersOfEUSelectAll.add(new Country("Select All", -1));

		// Draw the window
		drawLabel(shell, "Simple Multichoice :");
		final MultiChoice<String> mcSimple = new MultiChoice<String>(shell, SWT.None);
		final GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, true);
		gridData.widthHint = 200;
		mcSimple.setLayoutData(gridData);
		mcSimple.addAll(euroZone);
		addButons(mcSimple);

		drawLabel(shell, "Multichoice with beans :");
		final MultiChoice<Country> mcBeans = new MultiChoice<Country>(shell, SWT.None);
		mcBeans.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
		mcBeans.addAll(membersOfEuropeanUnion);
		addButons(mcBeans);

		drawLabel(shell, "Selection listener :");
		final MultiChoice<Country> mcSL = new MultiChoice<Country>(shell, SWT.None);
		mcSL.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
		mcSL.addAll(membersOfEUSelectAll);
		mcSL.setSelectionListener(new MultiChoiceSelectionListener<Country>(mcSL) {

			@Override
			public void handle(final MultiChoice<Country> parent, final Country receiver, final boolean selection, final Shell popup) {
				if ("Select All".equals(receiver.toString())) {
					if (selection) {
						parent.deselectAll();
						parent.selectAll();
					} else {
						parent.deselectAll();
					}
					popup.setVisible(false);
				}

			}
		});
		addButons(mcSL);

		drawLabel(shell, "3 columns :");
		final MultiChoice<String> mc3Columns = new MultiChoice<String>(shell, SWT.None);
		mc3Columns.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
		mc3Columns.addAll(euroZone);
		mc3Columns.setNumberOfColumns(3);
		addButons(mc3Columns);

		drawLabel(shell, "Other separator :");
		final MultiChoice<String> mcOtherSeparator = new MultiChoice<String>(shell, SWT.None);
		mcOtherSeparator.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
		mcOtherSeparator.addAll(euroZone);
		mcOtherSeparator.setSeparator(" - ");
		addButons(mcOtherSeparator);

		// display the shell...
		shell.open();
		shell.pack();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private static void drawLabel(final Shell shell, final String text) {
		final Label label = new Label(shell, SWT.NONE);
		label.setText(text);
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
	}

	private static void addButons(final MultiChoice<?> mc) {
		final Button buttonShowSelection = new Button(mc.getParent(), SWT.PUSH);
		buttonShowSelection.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		buttonShowSelection.setText("Show selection");
		buttonShowSelection.addSelectionListener(new SimpleSelectionAdapter() {

			@Override
			public void handle(final SelectionEvent e) {
				final Iterator<?> it = mc.getSelection().iterator();
				final StringBuilder sb = new StringBuilder();
				while (it.hasNext()) {
					sb.append(it.next().toString());
					if (it.hasNext()) {
						sb.append(", ");
					}
				}
				final MessageBox mb = new MessageBox(mc.getShell(), SWT.OK);
				mb.setMessage(sb.toString());
				mb.open();
			}
		});

		final Button buttonShowSelectedIndex = new Button(mc.getParent(), SWT.PUSH);
		buttonShowSelectedIndex.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		buttonShowSelectedIndex.setText("Show selected index");
		buttonShowSelectedIndex.addSelectionListener(new SimpleSelectionAdapter() {

			@Override
			public void handle(final SelectionEvent e) {
				final StringBuilder sb = new StringBuilder();
				final int[] selectedIndex = mc.getSelectedIndex();
				if (selectedIndex.length > 0) {
					sb.append(selectedIndex[0]);
					for (int i = 1; i < selectedIndex.length; i++) {
						sb.append(",");
						sb.append(selectedIndex[i]);
					}
				} else {
					sb.append("Empty");
				}
				final MessageBox mb = new MessageBox(mc.getShell(), SWT.OK);
				mb.setMessage(sb.toString());
				mb.open();
			}
		});
	}
}
