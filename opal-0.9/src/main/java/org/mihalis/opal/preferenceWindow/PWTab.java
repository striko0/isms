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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.mihalis.opal.preferenceWindow.widgets.PWWidget;

/**
 * Instance of this class are tabs
 *
 */
public class PWTab extends PWContainer {
	private final Image image;
	private final String text;
	private final List<PWRowGroup> children;

	/**
	 * Constructor
	 *
	 * @param image image associated to the tab
	 * @param text text associated to the tab
	 */
	public PWTab(final Image image, final String text) {
		this.image = image;
		this.text = text;
		this.children = new ArrayList<PWRowGroup>();
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#add(org.mihalis.opal.preferenceWindow.PWContainer)
	 */
	@Override
	public PWContainer add(final PWContainer element) {
		if (!(element instanceof PWGroup) && !(element instanceof PWRow)) {
			throw new UnsupportedOperationException("Can only add a PWGroup or a PWRow.");
		}
		((PWRowGroup) element).setParent(this);
		this.children.add((PWRowGroup) element);
		return this;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#add(org.mihalis.opal.preferenceWindow.widgets.PWWidget)
	 */
	@Override
	public PWContainer add(final PWWidget widget) {
		final PWRow row = new PWRow();
		row.setParent(this);
		row.add(widget);
		this.children.add(row);
		return this;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.PWContainer#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void build(final Composite parent) {
		final int numberOfColumns = computeNumberOfColums();
		parent.setLayout(new GridLayout(numberOfColumns, false));

		for (final PWRowGroup rowGroup : this.children) {
			rowGroup.setParentNumberOfColumns(numberOfColumns);
			rowGroup.build(parent);
		}

		PreferenceWindow1.getInstance().fireEnablers();

	}

	/**
	 * @return the total number of columns in this tab
	 */
	private int computeNumberOfColums() {
		int numberOfColumns = 1;
		for (final PWRowGroup rowGroup : this.children) {
			if (rowGroup instanceof PWRow) {
				numberOfColumns = Math.max(numberOfColumns, rowGroup.getNumberOfColums());
			}
		}
		return numberOfColumns;
	}

	/**
	 * @return the image associate to this tab
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * @return the text associated to this tab
	 */
	public String getText() {
		return this.text;
	}

}
