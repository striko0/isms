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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * This is the abstract class for all text widgets (except textarea)
 */
public abstract class PWText extends PWWidget {

	protected Text text;

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated property key
	 */
	public PWText(final String label, final String propertyKey) {
		super(label, propertyKey, label == null ? 1 : 2, false);
		setGrabExcessSpace(true);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control build(final Composite parent) {
		buildLabel(parent, GridData.CENTER);
		this.text = new Text(parent, SWT.BORDER | getStyle());
		addControl(this.text);
		addVerifyListeners();
		this.text.setText(PreferenceWindow1.getInstance().getValueFor(getPropertyKey()).toString());
		this.text.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				PreferenceWindow1.getInstance().setValue(getPropertyKey(), convertValue());
			}
		});
		return this.text;
	}

	/**
	 * Add the verify listeners
	 */
	public abstract void addVerifyListeners();

	/**
	 * @return the value of the data typed by the user in the correct format
	 */
	public abstract Object convertValue();

	/**
	 * @return the style (SWT.NONE or SWT.PASSWORD)
	 */
	public abstract int getStyle();

}
