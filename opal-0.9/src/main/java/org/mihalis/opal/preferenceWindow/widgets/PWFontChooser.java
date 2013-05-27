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
package org.mihalis.opal.preferenceWindow.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;
import org.mihalis.opal.utils.ResourceManager;

/**
 * Instances of this class are used to select a font
 */
public class PWFontChooser extends PWChooser {
	private FontData fontData;

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWFontChooser(final String label, final String propertyKey) {
		super(label, propertyKey);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#check()
	 */
	@Override
	public void check() {
		final Object value = PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		if (value == null) {
			PreferenceWindow1.getInstance().setValue(getPropertyKey(), null);
		} else {
			if (!(value instanceof FontData)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a FontData because it is associated to a font chooser");
			}
		}
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWChooser#setButtonAction(org.eclipse.swt.widgets.Text,
	 *      org.eclipse.swt.widgets.Button)
	 */
	@Override
	protected void setButtonAction(final Text text, final Button button) {
		this.fontData = (FontData) PreferenceWindow1.getInstance().getValueFor(getPropertyKey());

		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				final FontDialog dialog = new FontDialog(text.getShell());
				final FontData result = dialog.open();
				if (result != null && result.getName() != null && !"".equals(result.getName().trim())) {
					PWFontChooser.this.fontData = result;
					PreferenceWindow1.getInstance().setValue(getPropertyKey(), result);
					text.setText(buildFontInformation());
				}

			}
		});

	}

	/**
	 * @return a string that contains data about the choosen font
	 */
	protected String buildFontInformation() {
		final StringBuilder sb = new StringBuilder();
		if (this.fontData != null) {
			sb.append(this.fontData.getName()).append(",").append(this.fontData.getHeight()).append(" pt");
			if ((this.fontData.getStyle() & SWT.BOLD) == SWT.BOLD) {
				sb.append(", ").append(ResourceManager.getLabel(ResourceManager.BOLD));
			}
			if ((this.fontData.getStyle() & SWT.ITALIC) == SWT.ITALIC) {
				sb.append(", ").append(ResourceManager.getLabel(ResourceManager.ITALIC));
			}
		}
		return sb.toString();
	}
}
