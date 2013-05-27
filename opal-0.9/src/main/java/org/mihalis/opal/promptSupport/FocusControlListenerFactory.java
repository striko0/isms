/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Peter Weishapl - Inspiration
 *     Laurent CARON (laurent.caron@gmail.com) - initial API and implementation
 *******************************************************************************/
package org.mihalis.opal.promptSupport;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * This is a factory of focus/control listeners
 * 
 */
class FocusControlListenerFactory {

	/**
	 * @param control control on which the listener will be added
	 * @return a BaseControlFocus Listener that can be attached to the events
	 *         focusLost, focusGained and controlResized
	 */
	static BaseFocusControlListener getFocusControlListenerFor(final Control control) {
		if (control instanceof Combo) {
			return new ComboFocusControlListener((Combo) control);
		}
		if (control instanceof CCombo) {
			return new CComboFocusControlListener((CCombo) control);
		}

		if (control instanceof Text) {
			return new TextFocusControlListener((Text) control);
		}

		if (control instanceof StyledText) {
			return new StyledTextFocusControlListener((StyledText) control);
		}
		throw new IllegalArgumentException("Control should be a Text, a Combo, a CCombo or a StyledText widget");
	}

}
