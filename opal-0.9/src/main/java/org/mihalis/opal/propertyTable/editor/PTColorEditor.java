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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.mihalis.opal.propertyTable.PTProperty;
import org.mihalis.opal.propertyTable.PTWidget;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * A color editor
 */
public class PTColorEditor extends PTChooserEditor {

	@Override
	protected void openWindow(final PTWidget widget, final Item item, final PTProperty property) {
		final ColorDialog dialog = new ColorDialog(widget.getWidget().getShell());
		final RGB result = dialog.open();
		if (result != null) {
			property.setValue(result);

			final Color bgColor = getBackgroundColor(property);
			if (bgColor != null) {
				if (item instanceof TableItem) {
					((TableItem) item).setBackground(1, bgColor);
				}
				if (item instanceof TreeItem) {
					((TreeItem) item).setBackground(1, bgColor);
				}
				SWTGraphicUtil.dispose(item, bgColor);
			}

			if (item instanceof TableItem) {
				((TableItem) item).setText(1, getTextFor(property));

			} else {
				((TreeItem) item).setText(1, getTextFor(property));
			}
		}
	}

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTChooserEditor#getTextFor(org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	protected String getTextFor(final PTProperty property) {

		if (property.getValue() == null) {
			return "";
		}

		final RGB rgb = (RGB) property.getValue();

		final StringBuilder sb = new StringBuilder();
		sb.append("R:").append(rgb.red).append(" G:").append(rgb.green).append(" B:").append(rgb.blue);
		sb.append(" - #");
		sb.append(("0" + Integer.toHexString(rgb.red)).substring(0, 2));
		sb.append(("0" + Integer.toHexString(rgb.green)).substring(0, 2));
		sb.append(("0" + Integer.toHexString(rgb.blue)).substring(0, 2));
		return sb.toString();
	}

	/**
	 * @see org.mihalis.opal.propertyTable.editor.PTChooserEditor#getBackgroundColor(org.mihalis.opal.propertyTable.PTProperty)
	 */
	@Override
	protected Color getBackgroundColor(final PTProperty property) {
		if (property.getValue() == null) {
			return null;
		}
		final RGB rgb = (RGB) property.getValue();
		return new Color(Display.getDefault(), rgb);
	}

}
