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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.mihalis.opal.propertyTable.PTProperty;
import org.mihalis.opal.propertyTable.PTWidget;

/**
 * This editor is for combo values. By default, the combo is editable
 */
public class PTComboEditor extends PTEditor {

	private final boolean readOnly;
	private final List<Object> data;

	/**
	 * Constructor
	 * 
	 * @param readOnly if true, the combo is read-only
	 * @param data data displayed in the combo
	 */
	public PTComboEditor(final boolean readOnly, final Object data) {
		this.readOnly = readOnly;
		this.data = new ArrayList<Object>(Arrays.asList(data));
	}

	/**
	 * Constructor
	 * 
	 * @param data data displayed in the combo.
	 */
	public PTComboEditor(final Object... data) {
		this.readOnly = false;
		this.data = new ArrayList<Object>(Arrays.asList(data));
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

		final CCombo combo = new CCombo(widget.getWidget(), SWT.BORDER | (this.readOnly ? SWT.READ_ONLY : SWT.NONE));

		for (int i = 0; i < this.data.size(); i++) {
			final Object datum = this.data.get(i);
			combo.add(datum.toString());
			if (datum.equals(property)) {
				combo.select(i);
			}
		}

		combo.addSelectionListener(new SelectionAdapter() {
			/**
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(final SelectionEvent e) {
				property.setValue(PTComboEditor.this.data.get(combo.getSelectionIndex()));
			}
		});

		combo.addListener(SWT.FocusIn, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				widget.updateDescriptionPanel(property);
			}
		});

		editor.grabHorizontal = false;
		editor.horizontalAlignment = SWT.LEFT;
		editor.minimumWidth = 200;
		if (widget.getWidget() instanceof Table) {
			((TableEditor) editor).setEditor(combo, (TableItem) item, 1);
		} else {
			((TreeEditor) editor).setEditor(combo, (TreeItem) item, 1);
		}
		return editor;

	}

}
