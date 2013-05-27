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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * Instances of this class are used to select a color
 * 
 */
public class PWColorChooser extends PWWidget {

	private Color color;

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWColorChooser(final String label, final String propertyKey) {
		super(label, propertyKey, label == null ? 1 : 2, false);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control build(final Composite parent) {
		final RGB rgb = (RGB) PreferenceWindow1.getInstance().getValueFor(getPropertyKey());

		if (rgb == null) {
			this.color = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		} else {
			this.color = new Color(Display.getDefault(), rgb);
		}

		buildLabel(parent, GridData.CENTER);
		final Button button = new Button(parent, SWT.PUSH);
		addControl(button);

		button.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				drawButton(button);
			}
		});

		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				final ColorDialog dialog = new ColorDialog(button.getShell());
				final RGB result = dialog.open();
				if (result != null) {
					SWTGraphicUtil.dispose(PWColorChooser.this.color);
					PWColorChooser.this.color = new Color(button.getDisplay(), result);
					drawButton(button);
					PreferenceWindow1.getInstance().setValue(getPropertyKey(), result);
				}
			}
		});

		button.addListener(SWT.Dispose, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				SWTGraphicUtil.dispose(PWColorChooser.this.color);
			}
		});

		return button;
	}

	/**
	 * @param button button on which we draw the rectangle
	 */
	protected void drawButton(final Button button) {
		final int height = (int) button.getFont().getFontData()[0].height;
		final int width = button.getBounds().width - 16;

		final Image newImage = new Image(button.getDisplay(), Math.max(1, width), Math.max(1, height));

		final GC gc = new GC(newImage);
		gc.setBackground(PWColorChooser.this.color);
		gc.fillRectangle(0, 0, width, height);

		gc.setForeground(button.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.drawRectangle(0, 0, width - 1, height - 1);

		gc.dispose();

		button.setImage(newImage);
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
			if (!(value instanceof RGB)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a RGB because it is associated to a color chooser");
			}
		}
	}

}
