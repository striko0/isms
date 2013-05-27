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

import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.mihalis.opal.propertyTable.PTProperty;
import org.mihalis.opal.utils.ResourceManager;

/**
 * Editor for {@link Dimension} values
 */
public class PTDimensionEditor extends PTWindowEditor {

	private Text width;
	private Text height;

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTWindowEditor#createContent(org.eclipse.swt.widgets.Shell,
	 *      org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	protected void createContent(final Shell shell, final PTProperty property) {
		final Label widthLabel = new Label(shell, SWT.NONE);
		widthLabel.setLayoutData(new GridData(GridData.END, GridData.BEGINNING, false, false));
		widthLabel.setText(ResourceManager.getLabel(ResourceManager.WIDTH));

		this.width = new Text(shell, SWT.BORDER);
		this.width.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
		if (property.getValue() != null) {
			final Dimension d = (Dimension) property.getValue();
			this.width.setText(String.valueOf(d.width));
		}
		addVerifyListeners(this.width);

		final Label heightLabel = new Label(shell, SWT.NONE);
		heightLabel.setLayoutData(new GridData(GridData.END, GridData.BEGINNING, false, false));
		heightLabel.setText(ResourceManager.getLabel(ResourceManager.HEIGHT));

		this.height = new Text(shell, SWT.BORDER);
		this.height.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
		if (property.getValue() != null) {
			final Dimension d = (Dimension) property.getValue();
			this.height.setText(String.valueOf(d.height));
		}
		addVerifyListeners(this.height);

	}

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTWindowEditor#fillProperty(org.eclipse.swt.widgets.Item,
	 *      org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	protected void fillProperty(final Item item, final PTProperty property) {
		final Dimension d = new Dimension();
		d.width = getIntValue(this.width);
		d.height = getIntValue(this.height);
		property.setValue(d);
		if (item instanceof TableItem) {
			((TableItem) item).setText(1, getTextFor(property));
		} else {
			((TreeItem) item).setText(1, getTextFor(property));
		}
	}

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTChooserEditor#getTextFor(org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	protected String getTextFor(final PTProperty property) {
		if (property.getValue() == null) {
			return "(null)";
		}
		final Dimension d = (Dimension) property.getValue();
		return "[" + d.width + "," + d.height + "]";
	}

}
