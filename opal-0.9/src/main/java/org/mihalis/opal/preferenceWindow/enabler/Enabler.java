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

import org.mihalis.opal.preferenceWindow.PWRowGroup;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;
import org.mihalis.opal.preferenceWindow.widgets.PWWidget;

/**
 * This is the abstract class of all Enablers. An enabler is an object used to
 * enable or disable a widget depending on a value of a stored property.
 */
public abstract class Enabler {

	protected String prop;

	/**
	 * Constructor
	 * 
	 * @param prop property linked to the enabler.
	 */
	public Enabler(final String prop) {
		this.prop = prop;
	}

	/**
	 * @return the evaluation condition
	 */
	public abstract boolean isEnabled();

	/**
	 * Link a widget to the enabler
	 * 
	 * @param widget widget to link
	 */
	public void injectWidget(final PWWidget widget) {
		PreferenceWindow1.getInstance().addWidgetLinkedTo(this.prop, widget);
	}

	/**
	 * Link a row or a group to the enabler
	 * 
	 * @param rowGroup RowGroup to link
	 */
	public void injectRowGroup(final PWRowGroup rowGroup) {
		PreferenceWindow1.getInstance().addRowGroupLinkedTo(this.prop, rowGroup);
	}

}
