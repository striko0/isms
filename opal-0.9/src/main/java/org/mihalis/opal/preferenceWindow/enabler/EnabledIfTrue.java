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
package org.mihalis.opal.preferenceWindow.enabler;

import org.mihalis.opal.preferenceWindow.PreferenceWindow1;

/**
 * This enabler is used to enable a widget if a boolean property is true
 */
public class EnabledIfTrue extends Enabler {

	/**
	 * Constructor
	 * 
	 * @param prop boolean property
	 */
	public EnabledIfTrue(final String prop) {
		super(prop);
	}

	/**
	 * @see org.mihalis.opal.preferenceWindow.enabler.Enabler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		final Object value = PreferenceWindow1.getInstance().getValueFor(this.prop);

		if (value != null && !(value instanceof Boolean)) {
			throw new UnsupportedOperationException("Impossible to evaluate [" + this.prop + "] because it is not a Boolean !");
		}

		if (value == null) {
			return true;
		}

		return ((Boolean) value).booleanValue();
	}
}
