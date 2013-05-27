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

/**
 * Focus/Control listener for a CCombo widget
 */
class CComboFocusControlListener extends BaseFocusControlListener {

	/**
	 * Constructor
	 * 
	 * @param control control on which this listener will be attached
	 */
	public CComboFocusControlListener(final CCombo control) {
		super(control);
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#hidePrompt()
	 */
	@Override
	protected void hidePrompt() {
		((CCombo) this.control).setText("");
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#highLightPrompt()
	 */
	@Override
	protected void highLightPrompt() {
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#fillPromptText()
	 */
	@Override
	protected void fillPromptText() {
		final String promptText = PromptSupport.getPrompt(this.control);
		if (promptText != null) {
			((CCombo) this.control).setText(promptText);
		}
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#isFilled()
	 */
	@Override
	protected boolean isFilled() {
		final String promptText = PromptSupport.getPrompt(this.control);
		if (promptText != null && promptText.equals(((CCombo) this.control).getText().trim())) {
			return false;
		}
		return !"".equals(((CCombo) this.control).getText().trim());
	}

}
