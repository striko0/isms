/*******************************************************************************
 * Copyright (c) 2012 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - initial API and implementation 
 *******************************************************************************/
package org.mihalis.opal.propertyTable.editor;

import org.eclipse.swt.SWT;

/**
 * This editor is used to edit string values
 */
public class PTStringEditor extends PTBaseTextEditor {

	@Override
	public void addVerifyListeners() {
	}

	@Override
	public Object convertValue() {
		return this.text.getText();
	}

	@Override
	public int getStyle() {
		return SWT.NONE;
	}

}
