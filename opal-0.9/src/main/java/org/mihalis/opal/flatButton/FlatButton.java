/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - initial API and implementation 
 *******************************************************************************/
package org.mihalis.opal.flatButton;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * Instances of this class represent a flat button.
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>UP, DOWN, LEFT, RIGHT, CENTER</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 * <p>
 * Note: Only one of the styles LEFT, RIGHT, and CENTER may be specified.
 * </p>
 * 
 */
public class FlatButton extends Canvas {
	private Image image;
	private String text;
	private boolean selection;
	private int alignment;
	private final List<SelectionListener> listeners;
	private boolean mouseIn;
	private Color backgroundColor;
	private Color selectedColor;
	private Color selectedTextColor;
	private Color mouseOverColor;

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
	 * @param parent a composite control which will be the parent of the new
	 *            instance (cannot be null)
	 * @param style the style of control to construct
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the parent</li>
	 *                <li>ERROR_INVALID_SUBCLASS - if this class is not an
	 *                allowed subclass</li>
	 *                </ul>
	 * 
	 * @see SWT#DOWN
	 * @see SWT#LEFT
	 * @see SWT#RIGHT
	 * @see SWT#CENTER
	 */
	public FlatButton(final Composite parent, final int style) {
		super(parent, style);
		this.listeners = new ArrayList<SelectionListener>();
		buildAlignmentFromStyle(style);
		addListeners();
		initializeDefaultColors();
	}

	private void buildAlignmentFromStyle(final int style) {
		if ((style & SWT.LEFT) == SWT.LEFT) {
			this.alignment = SWT.LEFT;
		} else if ((style & SWT.RIGHT) == SWT.RIGHT) {
			this.alignment = SWT.RIGHT;
		} else {
			this.alignment = SWT.CENTER;

		}
	}

	private void addListeners() {
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(final PaintEvent e) {
				FlatButton.this.paintControl(e);
			}
		});

		addListener(SWT.MouseEnter, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				FlatButton.this.mouseIn = true;
				redraw();
			}
		});

		addListener(SWT.MouseExit, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				FlatButton.this.mouseIn = false;
				redraw();
			}
		});

		addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				boolean doIt = true;
				FlatButton.this.selection = !FlatButton.this.selection;
				for (final SelectionListener listener : FlatButton.this.listeners) {
					final SelectionEvent sEvent = new SelectionEvent(event);
					listener.widgetSelected(sEvent);
					doIt = doIt && sEvent.doit;
				}
				if (!doIt) {
					FlatButton.this.selection = !FlatButton.this.selection;
				}
			}

		});
	}

	private void initializeDefaultColors() {
		this.backgroundColor = getDisplay().getSystemColor(SWT.COLOR_WHITE);
		this.selectedColor = new Color(getDisplay(), 0, 112, 192);
		this.selectedTextColor = getDisplay().getSystemColor(SWT.COLOR_WHITE);
		this.mouseOverColor = new Color(getDisplay(), 235, 234, 226);

		SWTGraphicUtil.dispose(this, this.selectedColor);
		SWTGraphicUtil.dispose(this, this.mouseOverColor);
		SWTGraphicUtil.dispose(this, this.image);
	}

	private void paintControl(final PaintEvent e) {
		final GC gc = e.gc;
		drawBackground(gc);
		if (this.image != null) {
			drawImage(gc);
		}
		if (this.text != null) {
			drawText(gc);
		}
	}

	private void drawBackground(final GC gc) {
		Color color;
		if (this.selection) {
			color = this.selectedColor;
		} else if (this.mouseIn) {
			color = this.mouseOverColor;
		} else {
			color = this.backgroundColor;
		}
		gc.setBackground(color);
		gc.fillRectangle(getClientArea());

	}

	private void drawImage(final GC gc) {
		final Rectangle rect = getClientArea();
		final Point imageSize = new Point(this.image.getBounds().width, this.image.getBounds().height);

		int x;
		if (this.alignment == SWT.LEFT) {
			x = 5;
		} else if (this.alignment == SWT.RIGHT) {
			x = rect.width - imageSize.x - 5;
		} else {
			x = (rect.width - imageSize.x) / 2;
		}
		gc.drawImage(this.image, x, 5);
	}

	private void drawText(final GC gc) {
		final Rectangle rect = getClientArea();

		if (this.selection) {
			gc.setForeground(this.selectedTextColor);
		} else {
			gc.setForeground(getForeground());
		}

		gc.setFont(getFont());
		final Point textSize = gc.stringExtent(this.text);
		int x, y;

		if (this.alignment == SWT.LEFT) {
			x = 5;
		} else if (this.alignment == SWT.RIGHT) {
			x = rect.width - textSize.x - 5;
		} else {
			x = (rect.width - textSize.x) / 2;
		}
		if (this.image == null) {
			y = 5;
		} else {
			y = 10 + this.image.getBounds().height;
		}
		gc.drawString(this.text, x, y, true);
	}

	/**
	 * Adds the listener to the collection of listeners who will be notified
	 * when the control is selected by the user, by sending it one of the
	 * messages defined in the <code>SelectionListener</code> interface.
	 * <p>
	 * <code>widgetSelected</code> is called when the control is selected by the
	 * user. <code>widgetDefaultSelected</code> is not called.
	 * </p>
	 * <p>
	 * When the <code>SWT.RADIO</code> style bit is set, the
	 * <code>widgetSelected</code> method is also called when the receiver loses
	 * selection because another item in the same radio group was selected by
	 * the user. During <code>widgetSelected</code> the application can use
	 * <code>getSelection()</code> to determine the current selected state of
	 * the receiver.
	 * </p>
	 * 
	 * @param listener the listener which should be notified
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
		this.listeners.add(listener);
	}

	/**
	 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
	 */
	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
		int width = 10, height = 15;
		if (this.image != null) {
			final Rectangle bounds = this.image.getBounds();
			width += bounds.width;
			height += bounds.height;
		}

		if (this.text != null) {
			final GC gc = new GC(this);
			final Point extent = gc.stringExtent(this.text);
			gc.dispose();
			width = Math.max(width, extent.x + 10);
			height = height + extent.y;
		}

		return new Point(Math.max(width, wHint), Math.max(height, hHint));
	}

	/**
	 * Returns a value which describes the position of the text in the receiver.
	 * The value will be one of <code>LEFT</code>, <code>RIGHT</code> or
	 * <code>CENTER</code>.
	 * 
	 * @return the alignment
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public int getAlignment() {
		checkWidget();
		return this.alignment;
	}

	/**
	 * Returns a value which describes the default background color
	 * 
	 * @return the default background color
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public Color getBackgroundColor() {
		checkWidget();
		return this.backgroundColor;
	}

	/**
	 * Returns the receiver's image if it has one, or null if it does not.
	 * 
	 * @return the receiver's image
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public Image getImage() {
		checkWidget();
		return this.image;
	}

	/**
	 * Returns a value which describes the color when the mouse is over the
	 * button
	 * 
	 * @return the color when the mouse is over the button
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public Color getMouseOverColor() {
		checkWidget();
		return this.mouseOverColor;
	}

	/**
	 * Returns a value which describes the color when the button is selected
	 * 
	 * @return the color when the button is selected
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public Color getSelectedColor() {
		checkWidget();
		return this.selectedColor;
	}

	/**
	 * Returns a value which describes the color of the text when the button is
	 * selected
	 * 
	 * @return the color of the text when the button is selected
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */

	public Color getSelectedTextColor() {
		return this.selectedTextColor;
	}

	/**
	 * Returns <code>true</code> if the receiver is selected, and false
	 * otherwise.
	 * <p>
	 * 
	 * @return the selection state
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li> <li>ERROR_THREAD_INVALID_ACCESS - if not
	 *                called from the thread that created the receiver</li>
	 *                </ul>
	 */
	public boolean getSelection() {
		checkWidget();
		return this.selection;
	}

	/**
	 * Returns the receiver's text.
	 * 
	 * @return the receiver's text
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public String getText() {
		checkWidget();
		return this.text;
	}

	/**
	 * Removes the listener from the collection of listeners who will be
	 * notified when the control is selected by the user.
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
		this.listeners.remove(listener);
	}

	/**
	 * Controls how text, images and arrows will be displayed in the receiver.
	 * The argument should be one of <code>LEFT</code>, <code>RIGHT</code> or
	 * <code>CENTER</code>.
	 * 
	 * @param alignment the new alignment
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void setAlignment(final int alignment) {
		checkWidget();
		if ((alignment & (SWT.LEFT | SWT.RIGHT | SWT.CENTER)) == 0) {
			return;
		}
		this.alignment = alignment;
		redraw();
	}

	/**
	 * Sets the receiver's background color to the color specified by the
	 * argument.
	 * 
	 * @param color the new color
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
	 */
	public void setBackgroundColor(final Color backgroundColor) {
		checkWidget();
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Sets the receiver's image to the argument, which may be <code>null</code>
	 * indicating that no image should be displayed.
	 * <p>
	 * Note that a Button can display an image and text simultaneously on
	 * Windows (starting with XP), GTK+ and OSX. On other platforms, a Button
	 * that has an image and text set into it will display the image or text
	 * that was set most recently.
	 * </p>
	 * 
	 * @param image the image to display on the receiver (may be
	 *            <code>null</code>)
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_INVALID_ARGUMENT - if the image has been
	 *                disposed</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void setImage(final Image image) {
		checkWidget();
		this.image = image;
		redraw();
	}

	/**
	 * Sets the receiver's color when the mouse if over the button.
	 * 
	 * @param color the new color
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
	 */
	public void setMouseOverColor(final Color mouseOverColor) {
		checkWidget();
		this.mouseOverColor = mouseOverColor;
	}

	/**
	 * Sets the receiver's color when the button is selected.
	 * 
	 * @param color the new color
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
	 */
	public void setSelectedColor(final Color selectedColor) {
		checkWidget();
		this.selectedColor = selectedColor;
	}

	/**
	 * Sets the receiver's text color when the button is selected.
	 * 
	 * @param color the new color
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
	 */
	public void setSelectedTextColor(final Color selectedTextColor) {
		this.selectedTextColor = selectedTextColor;
	}

	/**
	 * Sets the selection state of the receiver.
	 * 
	 * @param selected the new selection state
	 * 
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void setSelection(final boolean selected) {
		checkWidget();
		this.selection = selected;
		redraw();
	}

	/**
	 * Sets the receiver's text.
	 * 
	 * 
	 * @param string the new text
	 * 
	 * @exception IllegalArgumentException <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the text is null</li>
	 *                </ul>
	 * @exception SWTException <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 */
	public void setText(final String text) {
		checkWidget();
		this.text = text;
		redraw();
	}

}
