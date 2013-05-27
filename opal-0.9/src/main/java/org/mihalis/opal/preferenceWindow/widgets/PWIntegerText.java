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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * Instances of this class are text box to type Integers
 */
public class PWIntegerText extends PWText {

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWIntegerText(final String label, final String propertyKey) {
		super(label, propertyKey);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWText#addVerifyListeners()
	 */
	@Override
	public void addVerifyListeners() {
		this.text.addListener(SWT.Verify, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				final String string = e.text;
				final char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && e.keyCode != SWT.BS && e.keyCode != SWT.DEL) {
						e.doit = false;
						return;
					}
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
			PreferenceWindow1.getInstance().setValue(getPropertyKey(), new Integer(0));
		} else {
			if (!(value instanceof Integer)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be an Integer because it is associated to a integer text widget");
			}
		}
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWText#convertValue()
	 */
	@Override
	public Object convertValue() {
		return Integer.parseInt(this.text.getText());
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWText#getStyle()
	 */
	@Override
	public int getStyle() {
		return SWT.NONE;
	}
}
