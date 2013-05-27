/*******************************************************************************
 * Copyright (c) 2012 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - initial API and implementation 
 *******************************************************************************/
package org.mihalis.opal.propertyTable.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.mihalis.opal.propertyTable.PTProperty;
import org.mihalis.opal.propertyTable.PTWidget;

/**
 * This editor is a spinner
 * 
 */
public class PTSpinnerEditor extends PTEditor {
	private final int max;
	private final int min;

	/**
	 * Constructor
	 * 
	 * @param min minimum value
	 * @param max maximum value
	 */
	public PTSpinnerEditor(final int min, final int max) {
		this.min = min;
		this.max = max;
	}

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTEditor#render(org.mihalis.opal.propertyTable.PTWidget,
	 *      org.eclipse.swt.widgets.Item,
	 *      org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	public ControlEditor render(final PTWidget widget, final Item item, final PTProperty property) {
		ControlEditor editor;
		if (widget.getWidget() instanceof Table) {
			editor = new TableEditor((Table) widget.getWidget());
		} else {
			editor = new TreeEditor((Tree) widget.getWidget());
		}

		final Spinner spinner = new Spinner(widget.getWidget(), SWT.HORIZONTAL);

		spinner.setMinimum(this.min);
		spinner.setMaximum(this.max);
		final Integer originalValue = (Integer) property.getValue();
		spinner.setSelection(originalValue == null ? this.min : originalValue.intValue());

		spinner.addListener(SWT.FocusIn, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				widget.updateDescriptionPanel(property);
			}
		});

		spinner.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				property.setValue(new Integer(spinner.getSelection()));
			}
		});

		editor.grabHorizontal = true;
		if (widget.getWidget() instanceof Table) {
			((TableEditor) editor).setEditor(spinner, (TableItem) item, 1);
		} else {
			((TreeEditor) editor).setEditor(spinner, (TreeItem) item, 1);
		}

		spinner.setEnabled(property.isEnabled());

		return editor;

	}

}
