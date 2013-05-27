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
package org.mihalis.opal.columns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * Instances of this class provide a data browser similar to the ones used in
 * Mac OS X. Look at http://en.wikipedia.org/wiki/Miller_Columns
 * <p>
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>BORDER</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class ColumnBrowserWidget extends ScrolledComposite {

	private final List<Table> columns;
	private final Composite composite;
	private final Image columnArrow;
	private final List<SelectionListener> selectionListeners;

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance.
	 * <p>
	 * The style value is either one of the style constants defined in class
	 * <code>SWT</code> which is applicable to instances of this class, or must
	 * be built by <em>bitwise OR</em>'ing together (that is, using the
	 * <code>int</code> "|" operator) two or more of those <code>SWT</code>
	 * style constants. The class description lists the style constants that are
	 * applicable to the class. Style bits are also inherited from superclasses.
	 * </p>
	 * 
	 * @param parent a widget which will be the parent of the new instance
	 *            (cannot be null)
	 * @param style the style of widget to construct
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the parent</li>
	 *                </ul>
	 * 
	 * @see Composite#Composite(Composite, int)
	 * @see SWT#BORDER
	 * @see Widget#getStyle
	 */
	public ColumnBrowserWidget(final Composite parent, final int style) {
		super(parent, style | SWT.H_SCROLL | SWT.V_SCROLL);

		this.composite = new Composite(this, SWT.NONE);
		final RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.spacing = 1;
		layout.pack = false;
		this.composite.setLayout(layout);

		this.columnArrow = SWTGraphicUtil.createImage("images/columnArrow.png");

		this.columns = new ArrayList<Table>();
		for (int i = 0; i < 3; i++) {
			this.createTable();
		}

		// Store root
		this.columns.get(0).setData(new ColumnItem(this));

		this.setContent(this.composite);
		this.setExpandHorizontal(true);
		this.setExpandVertical(true);
		this.setShowFocusedControl(true);
		this.updateContent();
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		this.selectionListeners = new ArrayList<SelectionListener>();

		this.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(final DisposeEvent arg0) {
				SWTGraphicUtil.dispose(ColumnBrowserWidget.this.columnArrow);
			}
		});

	}

	/**
	 * Create a column that displays data
	 */
	private void createTable() {
		final Table table = new Table(this.composite, SWT.SINGLE | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		new TableColumn(table, SWT.LEFT);

		table.setLayoutData(new RowData(150, 175));
		this.columns.add(table);

		table.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				final int width = table.getSize().x;
				table.getColumn(0).setWidth(width - 5);
			}
		});

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				final Table table = (Table) event.widget;
				if (table.getSelection() == null || table.getSelection().length != 1) {
					return;
				}
				ColumnBrowserWidget.this.selectItem(table.getSelection()[0]);
			}
		});

		final Listener paintListener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				switch (event.type) {
				case SWT.MeasureItem: {
					final Rectangle rect = ColumnBrowserWidget.this.columnArrow.getBounds();
					event.width += rect.width;
					event.height = Math.max(event.height, rect.height + 2);
					break;
				}

				case SWT.PaintItem: {
					if (!(event.item instanceof TableItem)) {
						return;
					}
					final TableItem item = (TableItem) event.item;
					if (item.getData() == null) {
						return;
					}

					if (((ColumnItem) item.getData()).getItemCount() == 0) {
						return;
					}

					final int x = event.x + event.width;
					final Rectangle rect = ColumnBrowserWidget.this.columnArrow.getBounds();
					final int offset = Math.max(0, (event.height - rect.height) / 2);
					event.gc.drawImage(ColumnBrowserWidget.this.columnArrow, x, event.y + offset);
					break;
				}
				}
			}
		};
		table.addListener(SWT.MeasureItem, paintListener);
		table.addListener(SWT.PaintItem, paintListener);

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				ColumnBrowserWidget.this.fireSelectionListeners(e);
			}

			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				ColumnBrowserWidget.this.fireSelectionListeners(e);
			}
		});

		if (super.getBackground() != null && super.getBackground().getRed() != 240 && super.getBackground().getGreen() != 240 && super.getBackground().getBlue() != 240) {
			table.setBackground(super.getBackground());
		}
		table.setBackgroundImage(super.getBackgroundImage());
		table.setBackgroundMode(super.getBackgroundMode());
		table.setCursor(super.getCursor());
		table.setFont(super.getFont());
		table.setForeground(super.getForeground());
		table.setMenu(super.getMenu());
		table.setToolTipText(super.getToolTipText());

	}

	/**
	 * Fire the selection listeners
	 * 
	 * @param selectionEvent mouse event
	 * @return true if the selection could be changed, false otherwise
	 */
	private boolean fireSelectionListeners(final SelectionEvent selectionEvent) {
		for (final SelectionListener listener : this.selectionListeners) {
			final Event event = new Event();

			event.button = 0;
			event.display = this.getDisplay();
			event.item = null;
			event.widget = this;
			event.data = null;
			event.time = selectionEvent.time;
			event.x = selectionEvent.x;
			event.y = selectionEvent.y;

			final SelectionEvent selEvent = new SelectionEvent(event);
			listener.widgetSelected(selEvent);
			if (!selEvent.doit) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Perform actions when an item is selected (ie fill the next column and
	 * force focus on it)
	 * 
	 * @param tableItem selected item
	 */
	private void selectItem(final TableItem tableItem) {
		final ColumnItem c = (ColumnItem) tableItem.getData();

		if (c.getItemCount() == 0) {
			return;
		}

		final int selectedColumn = this.findSelectedColumn(tableItem);
		boolean needPacking = false;
		if (selectedColumn != this.columns.size() - 1) {
			for (int i = selectedColumn + 1; i < this.columns.size(); i++) {
				this.columns.get(i).setData(null);
				this.columns.get(i).deselectAll();
			}

			int i = 0;
			final Iterator<Table> it = this.columns.iterator();
			while (it.hasNext()) {
				final Table t = it.next();
				if (i >= 3) {
					t.dispose();
					it.remove();
					// Don't know why, it's not working if I do not include this
					// :(
					this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
				i++;
			}

			if (selectedColumn != this.columns.size() - 1) {
				this.columns.get(selectedColumn + 1).setData(c);
			} else {
				this.createTable();
				this.columns.get(this.columns.size() - 1).setData(c);
			}
			needPacking = true;

		} else {
			this.createTable();
			needPacking = true;
			this.columns.get(this.columns.size() - 1).setData(c);
		}
		this.updateContent();
		if (needPacking) {
			this.composite.pack();
			this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		this.columns.get(this.columns.size() - 1).forceFocus();
	}

	/**
	 * Find which column has been selected
	 * 
	 * @param tableItem selected table item
	 * @return the index of the selected column
	 */
	private int findSelectedColumn(final TableItem tableItem) {
		for (int i = 0; i < this.columns.size(); i++) {
			if (this.columns.get(i).equals(tableItem.getParent())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Update the content of the widget
	 */
	void updateContent() {
		if (this.columns == null) {
			return;
		}

		for (int i = 0; i < this.columns.size(); i++) {

			final Table table = this.columns.get(i);
			final int index = table.getSelectionIndex();
			table.removeAll();
			if (table.getData() == null) {
				continue;
			}
			for (final ColumnItem c : ((ColumnItem) table.getData()).getItems()) {
				final TableItem item = new TableItem(table, SWT.NONE);
				item.setData(c);
				if (c.getText() != null) {
					item.setText(c.getText());
				}
				if (c.getImage() != null) {
					item.setImage(c.getImage());
				}
			}
			table.setSelection(index);
		}
	}

	/**
	 * Adds the listener to the collection of listeners who will be notified
	 * when the user changes the receiver's selection, by sending it one of the
	 * messages defined in the <code>SelectionListener</code> interface.
	 * <p>
	 * When <code>widgetSelected</code> is called, the item field of the event
	 * object is valid. If the receiver has the <code>SWT.CHECK</code> style and
	 * the check selection changes, the event object detail field contains the
	 * value <code>SWT.CHECK</code>. <code>widgetDefaultSelected</code> is
	 * typically called when an item is double-clicked. The item field of the
	 * event object is valid for default selection, but the detail field is not
	 * used.
	 * </p>
	 * 
	 * @param listener the listener which should be notified when the user
	 *            changes the receiver's selection
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see SelectionListener
	 * @see #removeSelectionListener
	 * @see SelectionEvent
	 */
	public void addSelectionListener(final SelectionListener listener) {
		checkWidget();
		this.selectionListeners.add(listener);
	}

	/**
	 * Clear the selection
	 * 
	 * @param needPacking if <code>true</code>, the widget is packed
	 */
	public void clear(final boolean needPacking) {
		final Iterator<Table> it = this.columns.iterator();
		int i = 0;
		while (it.hasNext()) {
			final Table t = it.next();
			if (i >= 3) {
				t.dispose();
				it.remove();
			} else {
				if (i != 0) {
					t.setData(null);
				}
				t.deselectAll();
			}
			i++;
		}
		this.updateContent();
		if (needPacking) {
			this.composite.pack();
			this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		this.columns.get(0).forceFocus();
	}

	/**
	 * Returns the <code>ColumnItem</code>s that is currently selected in the
	 * receiver.
	 * 
	 * @return the selected item, or <code>null</code> if no one is selected
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public ColumnItem getSelection() {
		for (int i = this.columns.size() - 1; i >= 0; i--) {
			final Table table = this.columns.get(i);
			if (table == null || table.getData() == null || table.getSelection().length == 0) {
				continue;
			}

			return (ColumnItem) table.getItem(table.getSelectionIndex()).getData();

		}
		return null;
	}

	/**
	 * Removes the listener from the collection of listeners who will be
	 * notified when the user changes the receiver's selection.
	 * 
	 * @param listener the listener which should no longer be notified
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see SelectionListener
	 * @see #addSelectionListener
	 */
	public void removeSelectionListener(final SelectionListener listener) {
		checkWidget();
		this.selectionListeners.remove(listener);
	}

	/**
	 * Selects an item in the receiver. If the item was already selected, it
	 * remains selected.
	 * 
	 * @param item the item to be selected
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the item is null</li>
	 *                <li>ERROR_INVALID_ARGUMENT - if the item has been disposed
	 *                </li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void select(final ColumnItem item) {

		final List<ColumnItem> items = new ArrayList<ColumnItem>();
		this.findElement(item, items);
		Collections.reverse(items);
		if (items.isEmpty()) {
			return;
		}

		this.clear(false);
		for (int i = 3; i < items.size(); i++) {
			this.createTable();
		}
		for (int i = 0; i < items.size() - 1; i++) {
			this.columns.get(i + 1).setData(items.get(i));
		}
		this.updateContent();

		for (int i = 0; i < this.columns.size() - 1; i++) {
			final ColumnItem nextItem = (ColumnItem) this.columns.get(i + 1).getData();
			for (final TableItem tableItem : this.columns.get(i).getItems()) {
				if (tableItem.getData() != null && tableItem.getData().equals(nextItem)) {
					tableItem.getParent().setSelection(tableItem);
				}
			}
		}

		this.composite.pack();
		this.setMinSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.columns.get(this.columns.size() - 1).forceFocus();

	}

	/**
	 * Build an array that contains the hierarchy of ColumnItem from the root
	 * node to a given item.
	 * 
	 * @param item item to find
	 * @param items the lists of item that composes the hierarchy
	 */
	private void findElement(final ColumnItem item, final List<ColumnItem> items) {
		if (item == null) {
			return;
		}
		items.add(item);
		this.findElement(item.getParentItem(), items);
	}

	/**
	 * Sets the receiver's background color to the color specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform. For
	 * example, on Windows the background of a Button cannot be changed.
	 * </p>
	 * 
	 * @param color the new color (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setBackground(final Color color) {
		super.setBackground(color);
		for (final Table column : this.columns) {
			column.setBackground(color);
		}
	}

	/**
	 * Sets the background drawing mode to the argument which should be one of
	 * the following constants defined in class <code>SWT</code>:
	 * <code>INHERIT_NONE</code>, <code>INHERIT_DEFAULT</code>,
	 * <code>INHERIT_FORCE</code>.
	 * 
	 * @param mode the new background mode
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see SWT
	 * @see org.eclipse.swt.widgets.Composite#setBackgroundMode(int)
	 */
	@Override
	public void setBackgroundMode(final int mode) {
		super.setBackgroundMode(mode);
		for (final Table column : this.columns) {
			column.setBackgroundMode(mode);
		}
	}

	/**
	 * Sets the receiver's background image to the image specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null. The background image is tiled to fill the available space.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform. For
	 * example, on Windows the background of a Button cannot be changed.
	 * </p>
	 * 
	 * @param image the new image (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument is not a
	 *                bitmap</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see org.eclipse.swt.widgets.Control#setBackgroundImage(org.eclipse.swt.graphics.Image)
	 */
	@Override
	public void setBackgroundImage(final Image image) {
		super.setBackgroundImage(image);
		for (final Table column : this.columns) {
			column.setBackgroundImage(image);
		}
	}

	/**
	 * Sets the receiver's cursor to the cursor specified by the argument, or to
	 * the default cursor for that kind of control if the argument is null.
	 * <p>
	 * When the mouse pointer passes over a control its appearance is changed to
	 * match the control's cursor.
	 * </p>
	 * 
	 * @param cursor the new cursor (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setCursor(org.eclipse.swt.graphics.Cursor)
	 */
	@Override
	public void setCursor(final Cursor cursor) {
		super.setCursor(cursor);
		for (final Table column : this.columns) {
			column.setCursor(cursor);
		}
	}

	/**
	 * Sets the font that the receiver will use to paint textual information to
	 * the font specified by the argument, or to the default font for that kind
	 * of control if the argument is null.
	 * 
	 * @param font the new font (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setFont(org.eclipse.swt.graphics.Font)
	 */
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		for (final Table column : this.columns) {
			column.setFont(font);
		}
	}

	/**
	 * Sets the receiver's foreground color to the color specified by the
	 * argument, or to the default system color for the control if the argument
	 * is null.
	 * <p>
	 * Note: This operation is a hint and may be overridden by the platform.
	 * </p>
	 * 
	 * @param color the new color (or null)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the argument has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setForeground(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setForeground(final Color color) {
		super.setForeground(color);
		for (final Table column : this.columns) {
			column.setForeground(color);
		}
	}

	/**
	 * Sets the receiver's pop up menu to the argument. All controls may
	 * optionally have a pop up menu that is displayed when the user requests
	 * one for the control. The sequence of key strokes, button presses and/or
	 * button releases that are used to request a pop up menu is platform
	 * specific.
	 * <p>
	 * Note: Disposing of a control that has a pop up menu will dispose of the
	 * menu. To avoid this behavior, set the menu to null before the control is
	 * disposed.
	 * </p>
	 * 
	 * @param menu the new pop up menu
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_MENU_NOT_POP_UP - the menu is not a pop up menu</li>
	 *                <li>ERROR_INVALID_PARENT - if the menu is not in the same
	 *                widget tree</li>
	 *                <li>ERROR_INVALID_ARGUMENT - if the menu has been disposed
	 *                </li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setMenu(org.eclipse.swt.widgets.Menu)
	 */
	@Override
	public void setMenu(final Menu menu) {
		super.setMenu(menu);
		for (final Table column : this.columns) {
			column.setMenu(menu);
		}
	}

	/**
	 * Sets the receiver's tool tip text to the argument, which may be null
	 * indicating that the default tool tip for the control will be shown. For a
	 * control that has a default tool tip, such as the Tree control on Windows,
	 * setting the tool tip text to an empty string replaces the default,
	 * causing no tool tip text to be shown.
	 * <p>
	 * The mnemonic indicator (character '&amp;') is not displayed in a tool
	 * tip. To display a single '&amp;' in the tool tip, the character '&amp;'
	 * can be escaped by doubling it in the string.
	 * </p>
	 * 
	 * @param string the new tool tip text (or null)
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * @see org.eclipse.swt.widgets.Control#setToolTipText(java.lang.String)
	 */
	@Override
	public void setToolTipText(final String tooltipText) {
		super.setToolTipText(tooltipText);
		for (final Table column : this.columns) {
			column.setToolTipText(tooltipText);
		}
	}

	/**
	 * @return the root item, or null if there is no data
	 */
	ColumnItem getRootItem() {
		if (this.columns == null || this.columns.isEmpty()) {
			return null;
		}
		return (ColumnItem) this.columns.get(0).getData();
	}

}
