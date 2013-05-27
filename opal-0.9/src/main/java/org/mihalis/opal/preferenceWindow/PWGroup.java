/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - Initial implementation and API
 *******************************************************************************/
package org.mihalis.opal.preferenceWindow;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.mihalis.opal.preferenceWindow.widgets.PWWidget;

/**
 * Instances of this class are groups
 */
public class PWGroup extends PWRowGroup {

	private final String label;
	private final boolean hasBorder;
	private final List<PWRow> children;

	/**
	 * Constructor
	 * 
	 * @param hasBorder if <code>true</code>, the group has a border
	 */
	public PWGroup(final boolean hasBorder) {
		this(null, hasBorder);
	}

	/**
	 * Constructor
	 * 
	 * @param label label associated to the group
	 */
	public PWGroup(final String label) {
		this(label, true);
	}

	/**
	 * Constructor
	 * 
	 * @param label label associated to the group
	 * @param hasBorder if <code>true</code>, the group has a border
	 */
	public PWGroup(final String label, final boolean hasBorder) {
		this.label = label;
		this.hasBorder = hasBorder;
		this.children = new ArrayList<PWRow>();
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#add(org.mihalis.opal.preferenceWindow.PWContainer)
	 */
	@Override
	public PWContainer add(final PWContainer element) {
		if (!(element instanceof PWRow)) {
			throw new UnsupportedOperationException("Can only add a PWRow.");
		}
		this.children.add((PWRow) element);
		return this;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#add(org.mihalis.opal.preferenceWindow.widgets.PWWidget)
	 */
	@Override
	public PWContainer add(final PWWidget widget) {
		final PWRow row = new PWRow();
		row.add(widget);
		this.children.add(row);
		return this;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void build(final Composite parent) {
		final Composite composite;
		if (this.hasBorder) {
			composite = new Group(parent, SWT.NONE);
			if (this.label != null && !this.label.trim().equals("")) {
				((Group) composite).setText(this.label);
			}
		} else {
			composite = new Composite(parent, SWT.BORDER);
		}

		final int numCol = computeNumberOfColumns();

		composite.setLayout(new GridLayout(numCol, false));
		composite.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, false, this.parentNumberOfColums, 1));

		for (final PWRow row : this.children) {
			row.setParentNumberOfColumns(numCol);
			row.build(composite);
		}

	}

	/**
	 * @return
	 */
	private int computeNumberOfColumns() {
		int numberOfColumns = 1;
		for (final PWRow row : this.children) {
			numberOfColumns = Math.max(numberOfColumns, row.getNumberOfColums());
		}
		return numberOfColumns;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWRowGroup#checkParent(org.mihalis.opal.preferenceWindow.PWContainer)
	 */
	@Override
	protected void checkParent(final PWContainer parent) {
		if (parent instanceof PWTab) {
			return;
		}
		throw new UnsupportedOperationException("Bad parent, should be only PWTab ");
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWRowGroup#enableOrDisable()
	 */
	@Override
	public void enableOrDisable() {
		if (this.enabler == null) {
			return;
		}

		final boolean enabled = this.enabler.isEnabled();
		for (final PWRow row : this.children) {
			enableOrDisable(row, enabled);
		}
	}

	/**
	 * Enable or disable a row
	 * 
	 * @param row row to enable or disable
	 * @param enabled enable flag
	 */
	private void enableOrDisable(final PWRow row, final boolean enabled) {
		for (final PWWidget widget : row.widgets) {
			final boolean widgetEnable = widget.enableOrDisable();

			for (final Control c : widget.getControls()) {
				if (!c.isDisposed()) {
					c.setEnabled(enabled && widgetEnable);
				}
			}
		}

	}

}
