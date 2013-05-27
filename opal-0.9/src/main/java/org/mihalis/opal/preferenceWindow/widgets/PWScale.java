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
import org.eclipse.swt.widgets.Scale;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * Instances of this class are scales
 */
public class PWScale extends PWWidget {

	private final int max;
	private final int min;
	private final int increment;

	/**
	 * Constructor
	 * 
	 * @param label associated label
	 * @param propertyKey associated key
	 * @param min minimum value
	 * @param max maximum value
	 * @param increment increment value
	 */
	public PWScale(final String label, final String propertyKey, final int min, final int max, final int increment) {
		super(label, propertyKey, label == null ? 1 : 2, false);
		this.min = min;
		this.max = max;
		this.increment = increment;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#build(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Control build(final Composite parent) {
		buildLabel(parent, GridData.CENTER);
		final Scale scale = new Scale(parent, SWT.HORIZONTAL);
		addControl(scale);
		scale.setIncrement(this.increment);
		scale.setMinimum(this.min);
		scale.setMaximum(this.max);
		final Integer originalValue = (Integer) PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		scale.setSelection(originalValue.intValue());

		scale.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				PreferenceWindow1.getInstance().setValue(getPropertyKey(), new Integer(scale.getSelection()));
			}
		});

		return scale;
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.widgets.PWWidget#check()
	 */
	@Override
	public void check() {
		final Object value = PreferenceWindow1.getInstance().getValueFor(getPropertyKey());
		if (value == null) {
			PreferenceWindow1.getInstance().setValue(getPropertyKey(), new Integer(this.min));
		} else {
			if (!(value instanceof Integer)) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' has to be an Integer because it is associated to a iscale");
			}

			final int valueAsInt = ((Integer) value).intValue();
			if (valueAsInt < this.min || valueAsInt > this.max) {
				throw new UnsupportedOperationException("The property '" + getPropertyKey() + "' is out of range (value is " + valueAsInt + ", range is " + this.min + "-" + this.max + ")");
			}
		}
	}

}
