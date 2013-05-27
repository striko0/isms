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
package hr.ante.isms.parts;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.preferenceWindow.PWRowGroup;
import org.mihalis.opal.preferenceWindow.PWTab;
import org.mihalis.opal.preferenceWindow.PWTabContainer;
import org.mihalis.opal.preferenceWindow.ValueAndAssociatedWidgets;
import org.mihalis.opal.preferenceWindow.widgets.PWWidget;
import org.mihalis.opal.utils.ResourceManager;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * This class is a preference window
 *
 */
public class ASPreferenceWindow {
	private final Map<String, ValueAndAssociatedWidgets> values;
	private final List<PWTab> tabs;
	private final Shell parentShell;
	private boolean returnedValue;
	private Shell shell;
	private static ASPreferenceWindow instance;
	private PWTabContainer container;
	private int selectedTab;

	/**
	 * Constructor
	 *
	 * @param parent parent shell (may be null)
	 * @param values a map that contains all values that will be displayed in
	 *            widgets
	 */
	private ASPreferenceWindow(final Shell parent, final Map<String, Object> values) {
		this.parentShell = parent;
		this.values = new HashMap<String, ValueAndAssociatedWidgets>(values.size());

		for (final String key : values.keySet()) {
			this.values.put(key, new ValueAndAssociatedWidgets(values.get(key)));
		}

		this.tabs = new ArrayList<PWTab>();
	}

	/**
	 * Create a preference window (a singleton)
	 *
	 * @param parent parent shell (may be null)
	 * @param values a map that contains all values that will be displayed in
	 *            widgets
	 * @return
	 */
	public static ASPreferenceWindow create(final Shell parent, final Map<String, Object> values) {
		instance = new ASPreferenceWindow(parent, values);
		return instance;
	}

	/**
	 * Create a preference window (a singleton)
	 *
	 * @param values a map that contains all values that will be displayed in
	 *            widgets
	 * @return
	 */
	public static ASPreferenceWindow create(final Map<String, Object> values) {
		instance = new ASPreferenceWindow(null, values);
		return instance;
	}

	/**
	 * @return an instance of the preference window
	 */
	public static ASPreferenceWindow getInstance() {
		if (instance == null) {
			throw new NullPointerException("The instance of PreferenceWindow has not yet been created or has been destroyed.");
		}
		return instance;
	}

	/**
	 * Add a tab to the preference window
	 *
	 * @param image image associated to the tab
	 * @param text text associated to the image
	 * @return the
	 */
	public PWTab addTab(final Image image, final String text) {
		final PWTab tab = new PWTab(image, text);
		this.tabs.add(tab);
		return tab;
	}

	/**
	 * Add a widget that is linked to a given property
	 *
	 * @param propertyKey the property
	 * @param widget the widget
	 */
	public void addWidgetLinkedTo(final String propertyKey, final PWWidget widget) {
		if (!this.values.containsKey(propertyKey)) {
			this.values.put(propertyKey, new ValueAndAssociatedWidgets(null));
		}
		this.values.get(propertyKey).addWidget(widget);
	}

	/**
	 * Add a row group that is linked to a given property
	 *
	 * @param propertyKey the property
	 * @param rowGroup the widget
	 */
	public void addRowGroupLinkedTo(final String propertyKey, final PWRowGroup rowGroup) {
		if (!this.values.containsKey(propertyKey)) {
			this.values.put(propertyKey, new ValueAndAssociatedWidgets(null));
		}
		this.values.get(propertyKey).addRowGroup(rowGroup);
	}

	/**
	 * Open the preference window
	 *
	 * @return <code>true</code> if the user pressed on the Ok button,
	 *         <code>false</code> if the user pressed on the Cancel button
	 */
	public boolean open() {
		if (this.parentShell == null) {
			this.shell = new Shell(SWT.SHELL_TRIM);
		} else {
			this.shell = new Shell(instance.parentShell, SWT.SHELL_TRIM);
		}

		this.shell.addListener(SWT.Dispose, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				instance = null;
			}
		});

		buildShell();
		openShell();

		return this.returnedValue;
	}

	/**
	 * Builds the shell
	 */
	private void buildShell() {
		this.shell.setText(ResourceManager.getLabel(ResourceManager.PREFERENCES));
		final GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
		this.shell.setLayout(gridLayout);
		container = new PWTabContainer(this.shell, SWT.NONE, this.tabs);
		container.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
		container.build();

		final Label sep = new Label(this.shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		sep.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));

		buildButtons();

	}

	/**
	 * Builds the buttons
	 */
	private void buildButtons() {
//		final Button buttonOK = new Button(this.shell, SWT.PUSH);
//		buttonOK.setText(ResourceManager.getLabel(ResourceManager.OK));
//		final GridData gridDataOk = new GridData(GridData.END, GridData.END, true, false);
//		gridDataOk.widthHint = 100;
//		buttonOK.setLayoutData(gridDataOk);
//		buttonOK.addSelectionListener(new SelectionAdapter() {
//
//			/**
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(final SelectionEvent e) {
//				ASPreferenceWindow.this.returnedValue = true;
//				ASPreferenceWindow.this.shell.dispose();
//			}
//
//		});
//		this.shell.setDefaultButton(buttonOK);
//
//		final Button buttonCancel = new Button(this.shell, SWT.PUSH);
//		buttonCancel.setText(ResourceManager.getLabel(ResourceManager.CANCEL));
//		final GridData gridDataCancel = new GridData(GridData.BEGINNING, GridData.END, false, false);
//		gridDataCancel.widthHint = 100;
//		buttonCancel.setLayoutData(gridDataCancel);
//		buttonCancel.addSelectionListener(new SelectionAdapter() {
//
//			/**
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(final SelectionEvent e) {
//				ASPreferenceWindow.this.returnedValue = false;
//				ASPreferenceWindow.this.shell.dispose();
//			}
//
//		});

	final Button buttonPrevious = new Button(this.shell, SWT.PUSH);
	buttonPrevious.setText("< Previous");
	final GridData gridDataPrevious = new GridData(GridData.END, GridData.END, true, false);
	gridDataPrevious.widthHint = 100;
	buttonPrevious.setLayoutData(gridDataPrevious);
	buttonPrevious.addSelectionListener(new SelectionAdapter() {

		/**
		 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected(final SelectionEvent e) {
			ASPreferenceWindow.this.returnedValue = true;
			ASPreferenceWindow.this.shell.dispose();
		}

	});
//	this.shell.setDefaultButton(buttonOK);


	final Button buttonNext = new Button(this.shell, SWT.PUSH);
	buttonNext.setText("Next >");
	final GridData gridDataNext = new GridData(GridData.END, GridData.END, true, false);
	gridDataNext.widthHint = 100;
	buttonNext.setLayoutData(gridDataNext);
	buttonNext.addSelectionListener(new SelectionAdapter() {

		/**
		 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected(final SelectionEvent e) {
			ASPreferenceWindow.this.returnedValue = true;
			ASPreferenceWindow.this.shell.dispose();
		}

	});




//	final Button buttonCancel = new Button(this.shell, SWT.PUSH);
//	buttonCancel.setText(ResourceManager.getLabel(ResourceManager.CANCEL));
//	final GridData gridDataCancel = new GridData(GridData.BEGINNING, GridData.END, false, false);
//	gridDataCancel.widthHint = 100;
//	buttonCancel.setLayoutData(gridDataCancel);
//	buttonCancel.addSelectionListener(new SelectionAdapter() {
//
//		/**
//		 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//		 */
//		@Override
//		public void widgetSelected(final SelectionEvent e) {
//			ASPreferenceWindow.this.returnedValue = false;
//			ASPreferenceWindow.this.shell.dispose();
//		}
//
//	});
	}

	/**
	 * Open the shell
	 */
	private void openShell() {
		this.shell.pack();
		this.shell.open();
		SWTGraphicUtil.centerShell(this.shell);

		while (!this.shell.isDisposed()) {
			if (!this.shell.getDisplay().readAndDispatch()) {
				this.shell.getDisplay().sleep();
			}
		}

	}

	/**
	 * Fire all enablers
	 */
	public void fireEnablers() {
		for (final String key : this.values.keySet()) {
			this.values.get(key).fireValueChanged();
		}
	}

	/**
	 * @return the selected tab
	 */
	public int getSelectedTab() {
		return selectedTab;
	}

	/**
	 * @param key
	 * @return the value associated to the <i>key</i>
	 */
	public Object getValueFor(final String key) {
		if (this.values.containsKey(key)) {
			return this.values.get(key).getValue();
		}
		return null;
	}

	/**
	 * @return the list of all values
	 */
	public Map<String, Object> getValues() {
		final Map<String, Object> returnedValues = new HashMap<String, Object>();
		for (final String key : this.values.keySet()) {
			returnedValues.put(key, this.values.get(key).getValue());
		}
		return returnedValues;
	}

	/**
	 * Store a value associated to the key
	 *
	 * @param key
	 * @param value
	 */
	public void setValue(final String key, final Object value) {
		if (this.values.containsKey(key)) {
			this.values.get(key).setValue(value);
		} else {
			this.values.put(key, new ValueAndAssociatedWidgets(value));
		}

	}

	/**
	 * Set the selected tab
	 * @param selectedTab
	 */
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
		if (container != null) {
			container.redraw();
			container.update();
		}
	}



}
