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

import org.eclipse.swt.widgets.Text;

/**
 * Focus/Control listener for a Text widget
 */
class TextFocusControlListener extends BaseFocusControlListener {

	/**
	 * Constructor
	 * 
	 * @param control control on which this listener will be attached
	 */
	public TextFocusControlListener(final Text control) {
		super(control);
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#hidePrompt()
	 */
	@Override
	protected void hidePrompt() {
		((Text) this.control).setText("");
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#highLightPrompt()
	 */
	@Override
	protected void highLightPrompt() {
		// If we do a select all directly, it's not working !
		this.control.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				((Text) TextFocusControlListener.this.control).selectAll();
			}
		});
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#fillPromptText()
	 */
	@Override
	protected void fillPromptText() {
		final String promptText = PromptSupport.getPrompt(this.control);
		if (promptText != null) {
			((Text) this.control).setText(promptText);
		}
	}

	/**
	 * @see org.mihalis.opal.promptSupport.BaseFocusControlListener#isFilled()
	 */
	@Override
	protected boolean isFilled() {
		final String promptText = PromptSupport.getPrompt(this.control);
		if (promptText != null && promptText.equals(((Text) this.control).getText().trim())) {
			return false;
		}
		return !"".equals(((Text) this.control).getText().trim());
	}

}
