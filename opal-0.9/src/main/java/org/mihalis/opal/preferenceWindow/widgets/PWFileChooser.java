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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * Instances of this class are used to select a file
 */
public class PWFileChooser extends PWChooser {

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWFileChooser(final String label, final String propertyKey) {
		super(label, propertyKey);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWChooser#setButtonAction(org.eclipse.swt.widgets.Text,
	 *      org.eclipse.swt.widgets.Button)
	 */
	@Override
	protected void setButtonAction(final Text text, final Button button) {
		final String originalFile = (String) PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		text.setText(originalFile);

		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				final FileDialog dialog = new FileDialog(text.getShell(), SWT.OPEN);
				final String result = dialog.open();
				if (result != null) {
					text.setText(result);
					PreferenceWindow1.getInstance().setValue(getPropertyKey(), result);
				}

			}
		});

	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#check()
	 */
	@Override
	public void check() {
		final Object value = PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		if (value == null) {
			PreferenceWindow1.getInstance().setValue(getPropertyKey(), "");
		} else {
			if (!(value instanceof String)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a String because it is associated to a file chooser");
			}
		}
	}

}
