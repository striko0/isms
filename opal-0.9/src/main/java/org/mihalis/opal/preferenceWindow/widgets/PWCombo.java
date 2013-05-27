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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.mihalis.opal.preferenceWindow.MainView;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * Instances of this class are Combo
 *
 */
public class PWCombo extends PWWidget {

	private final List<Object> data;
	private final boolean editable;

	/**
	 * Constructor
	 *
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	/*public PWCombo(final String label, final String propertyKey, final Object... values) {
		this(label, propertyKey, false, values);
	}*/

	/**
	 * Constructor
	 *
	 * @param label associated label
	 * @param propertyKey associated key
	 */
	public PWCombo(MainView mW, final String label, final String propertyKey, final boolean editable, final Object... values) {
		super(mW,label, propertyKey, label == null ? 1 : 2, false);
		this.data = new ArrayList<Object>(Arrays.asList(values));
		this.editable = editable;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control build(final Composite parent) {
		buildLabel(parent, GridData.CENTER);

		final Combo combo = new Combo(parent, SWT.BORDER | (this.editable ? SWT.NONE : SWT.READ_ONLY));
		addControl(combo);

		for (int i = 0; i < this.data.size(); i++) {
			final Object datum = this.data.get(i);
			combo.add(datum.toString());
			if (datum.equals(PreferenceWindow1.getInstance().getValueFor(getPropertyKey()))) {
				combo.select(i);
			}
		}

		combo.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(final Event event) {
//				PreferenceWindow1.getInstance().setValue(getPropertyKey(), PWCombo.this.data.get(combo.getSelectionIndex()));
				mainView.setValue(getPropertyKey(), PWCombo.this.data.get(combo.getSelectionIndex()));
			}
		});

		return combo;
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
			if (this.editable && !(value instanceof String)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a String because it is associated to an editable combo");
			}

			if (!this.data.isEmpty()) {
				if (!value.getClass().equals(this.data.get(0).getClass())) {
					throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be a " + this.data.get(0).getClass() + " because it is associated to a combo");
				}
			}

		}
	}
}
