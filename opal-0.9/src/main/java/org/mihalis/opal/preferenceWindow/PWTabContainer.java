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
package org.mihalis.opal.preferenceWindow;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.mihalis.opal.flatButton.FlatButton;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * Instances of this class are a container that allows the user to select a tab
 */
public class PWTabContainer extends Composite {

	private final List<PWTab> tabs;
	private Composite container;
	private Image oldButtonContainerImage;
	private final List<FlatButton> buttons;
	private Composite buttonContainer;

	/**
	 * Constructor
	 *
	 * @param parent parent composite
	 * @param style style (not used)
	 * @param tabs list of tabs
	 */
	public PWTabContainer(final Composite parent, final int style, final List<PWTab> tabs) {
		super(parent, style);
		this.tabs = new ArrayList<PWTab>();
		this.tabs.addAll(tabs);

		this.buttons = new ArrayList<FlatButton>();

		final GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
		setLayout(gridLayout);

	}

	/**
	 * Build the container
	 */
	public void build() {
		createButtonsContainer();
		createButtons();
		createContentContainer();

		select(PreferenceWindow1.getInstance().getSelectedTab());
	}

	/**
	 * Create the buttons container
	 */
	private void createButtonsContainer() {
		createContainer();
		createButtonsContainerBackground();

	}

	/**
	 * Create the container
	 */
	private void createContainer() {
		this.buttonContainer = new Composite(this, SWT.NONE);
		final GridData buttonContainerGridData = new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1);
		buttonContainerGridData.heightHint = 63;
		this.buttonContainer.setLayoutData(buttonContainerGridData);

		this.buttonContainer.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));

		final GridLayout gridLayout = new GridLayout(this.tabs.size(), false);
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
		gridLayout.marginBottom = 2;
		this.buttonContainer.setLayout(gridLayout);
	}

	/**
	 * Create the background of the container
	 */
	private void createButtonsContainerBackground() {
		this.buttonContainer.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				final Rectangle rect = PWTabContainer.this.buttonContainer.getClientArea();
				final Image image = new Image(getDisplay(), Math.max(1, rect.width), Math.max(1, rect.height));
				final GC gc = new GC(image);
				final Color grey = new Color(getDisplay(), 204, 204, 204);
				gc.setForeground(grey);
				gc.drawLine(0, rect.height - 1, rect.width, rect.height - 1);
				grey.dispose();
				gc.dispose();
				PWTabContainer.this.buttonContainer.setBackgroundImage(image);
				if (PWTabContainer.this.oldButtonContainerImage != null) {
					PWTabContainer.this.oldButtonContainerImage.dispose();
				}
				PWTabContainer.this.oldButtonContainerImage = image;

			}
		});
		SWTGraphicUtil.dispose(this.buttonContainer, this.oldButtonContainerImage);
	}

	/**
	 * Create the buttons
	 */
	private void createButtons() {
		for (int i = 0; i < this.tabs.size(); i++) {
			final PWTab tab = this.tabs.get(i);
			final FlatButton button = new FlatButton(this.buttonContainer, SWT.NONE);
			button.setText(tab.getText());
			button.setImage(tab.getImage());
			SWTGraphicUtil.dispose(button, tab.getImage());

			final GridData gd;
			if (i == this.tabs.size() - 1) {
				gd = new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false);
			} else {
				gd = new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
			}
			if (i == 0) {
				gd.horizontalIndent = 5;
			}
			gd.widthHint = 75;
			button.setLayoutData(gd);

			final int index = i;
			button.addSelectionListener(new SelectionAdapter() {

				/**
				 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
				 */
				@Override
				public void widgetSelected(final SelectionEvent e) {
					select(index);
				}

			});

			this.buttons.add(button);

		}
	}

	/**
	 * Select a given button
	 *
	 * @param index index of the selected button
	 */
	void select(final int index) {
		for (final Control c : this.container.getChildren()) {
			c.dispose();
		}

		this.tabs.get(index).build(this.container);
		this.container.layout();

		for (int i = 0; i < this.buttons.size(); i++) {
			this.buttons.get(i).setSelection(i == index);
		}
	}

	/**
	 * Create the content container, ie the composite that will contain all
	 * widgets
	 */
	private void createContentContainer() {
		this.container = new Composite(this, SWT.NONE);
		final GridData tempContainer = new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1);
		tempContainer.widthHint = 700;
		tempContainer.heightHint = 550;
		this.container.setLayoutData(tempContainer);
	}

}
