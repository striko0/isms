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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * Instances of this class are checkboxes
 */
public class PWCheckbox extends PWWidget {

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWCheckbox(final String label, final String propertyKey) {
		super(label, propertyKey, 1, true);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control build(final Composite parent) {
		if (getLabel() == null) {
			throw new UnsupportedOperationException("Please specify a label for a checkbox");
		}
		final Button button = new Button(parent, SWT.CHECK);
		addControl(button);
		button.setText(getLabel());
		final boolean originalSelection = (Boolean) PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		button.setSelection(originalSelection);

		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(final SelectionEvent e) {
				PreferenceWindow1.getInstance().setValue(getPropertyKey(), button.getSelection());
			}
		});
		return button;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#check()
	 */
	@Override
	public void check() {
		final Object value = PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		if (value == null) {
			PreferenceWindow1.getInstance().setValue(getPropertyKey(), new Boolean(false));
		} else {
			if (!(value instanceof Boolean)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a Boolean because it is associated to a checkbox");
			}
		}
	}
}
