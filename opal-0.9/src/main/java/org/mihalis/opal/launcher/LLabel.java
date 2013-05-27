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
package org.mihalis.opal.launcher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
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
 * Instance of this class are a button with text, image and a nice animation
 * effect
 */
public class LLabel extends Canvas {

	private String text;
	private Image image;
	private Font font;

	private static final int GAP = 12;
	private static int DRAW_FLAGS = SWT.DRAW_MNEMONIC | SWT.DRAW_TAB | SWT.DRAW_TRANSPARENT | SWT.DRAW_DELIMITER;
	private static final int DEFAULT_MARGIN = 5;
	private final int leftMargin = DEFAULT_MARGIN;
	private final int topMargin = DEFAULT_MARGIN;
	private final int rightMargin = DEFAULT_MARGIN;
	private final int bottomMargin = DEFAULT_MARGIN;
	private Point textSize;

	private static final int MAX_NUMBER_OF_STEPS = 10;
	private int animationStep = 0;

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
	 *                </ul>
	 *
	 */
	public LLabel(final Composite parent, final int style) {
		super(parent, style | SWT.BORDER | SWT.DOUBLE_BUFFERED);

		final Font original = super.getFont();

		this.font = new Font(getDisplay(), original.getFontData()[0].getName(), 18, SWT.BOLD);

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(final PaintEvent event) {
				onPaint(event);
			}
		});

		addListener(SWT.Dispose, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				onDispose(event);
			}
		});
	}

	/**
	 * Draw the content of the LLabel
	 *
	 * @param event paintevent
	 */
	private void onPaint(final PaintEvent event) {
		final Rectangle rect = getClientArea();
		if (rect.width == 0 || rect.height == 0) {
			return;
		}

		final Image bufferImage = new Image(getDisplay(), Math.max(1, rect.width), Math.max(1, rect.height));

		final GC gc = new GC(bufferImage);
		gc.setForeground(getForeground());
		gc.setBackground(getBackground());

		gc.fillRectangle(rect);

		final Point extent = getTotalSize(this.image.getBounds().width, this.image.getBounds().height);
		final int xImage = (rect.width - this.image.getBounds().width) / 2;
		final int yImage = (rect.height - extent.y) / 2;
		gc.drawImage(this.image, xImage, yImage);

		gc.setFont(this.font);
		final int xText = (rect.width - this.textSize.x) / 2;
		final int yText = yImage + this.image.getBounds().height + GAP - this.textSize.y / 2;
		gc.drawString(this.text, xText, yText);

		if (this.animationStep != 0) {
			final float zoom = 1f + this.animationStep * (Math.max(extent.x, extent.y) - Math.max(this.image.getBounds().width, this.image.getBounds().height)) / MAX_NUMBER_OF_STEPS / 100f;

			final int newSizeX = (int) (this.image.getBounds().width * zoom);
			final int newSizeY = (int) (this.image.getBounds().height * zoom);

			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);

			gc.setAlpha(255 - 255 / MAX_NUMBER_OF_STEPS * this.animationStep);

			final Point extentZoomedImage = getTotalSize(newSizeX, newSizeY);
			final int xZoomedImage = (rect.width - newSizeX) / 2;
			final int yZoomedImage = (rect.height - extentZoomedImage.y) / 2;
			gc.drawImage(this.image, 0, 0, this.image.getBounds().width, this.image.getBounds().height, xZoomedImage, yZoomedImage, (int) (this.image.getBounds().width * zoom), (int) (this.image.getBounds().height * zoom));

		}

		gc.dispose();

		event.gc.drawImage(bufferImage, 0, 0);

		bufferImage.dispose();

	}

	/**
	 * Dispose elements when the widget is disposed
	 *
	 * @param event dispose event
	 */
	private void onDispose(final Event event) {
		SWTGraphicUtil.dispose(this.image);
		SWTGraphicUtil.dispose(this.font);
		this.text = null;
		this.image = null;
	}

	/**
	 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
	 */
	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
		checkWidget();
		final Point e = getTotalSize(this.image.getBounds().width, this.image.getBounds().height);
		if (wHint == SWT.DEFAULT) {
			e.x += this.leftMargin + this.rightMargin;
		} else {
			e.x = wHint;
		}
		if (hHint == SWT.DEFAULT) {
			e.y += this.topMargin + this.bottomMargin;
		} else {
			e.y = hHint;
		}
		return e;
	}

	/**
	 * Compute the size of the content (image + text + gap)
	 *
	 * @param imgWidth image width
	 * @param imgHeight image height
	 * @return the size of the content
	 */
	private Point getTotalSize(final int imgWidth, final int imgHeight) {
		final Point size = new Point(0, 0);

		int textWidth = 0;
		int textHeight = 0;

		if (this.textSize == null) {
			final GC gc = new GC(this);
			gc.setFont(this.font);

			this.textSize = gc.textExtent(this.text, DRAW_FLAGS);
			gc.dispose();

		}
		textWidth = this.textSize.x;
		textHeight = this.textSize.y;

		size.x = Math.max(imgWidth, textWidth);
		size.y = imgHeight + GAP + textHeight;

		return size;
	}

	/**
	 * @return the text
	 */
	String getText() {
		return this.text;
	}

	/**
	 * @param text the text to set
	 */
	void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return the image
	 */
	Image getImage() {
		return this.image;
	}

	/**
	 * @param image the image to set
	 */
	void setImage(final Image image) {
		this.image = image;
	}

	/**
	 * @return the font
	 */
	@Override
	public Font getFont() {
		return this.font;
	}

	/**
	 * @param font the font to set
	 */
	@Override
	public void setFont(final Font font) {
		SWTGraphicUtil.dispose(font);
		this.font = font;
	}

	/**
	 * Increment the steps of the animation
	 *
	 * @return true if animation keeps running, false otherwise
	 */
	boolean incrementAnimation() {
		this.animationStep++;
		final boolean stopAnimation = this.animationStep > MAX_NUMBER_OF_STEPS;

		if (stopAnimation) {
			this.animationStep = 0;
		}
		if (!isDisposed()) {
			redraw();
		}
		return !stopAnimation;
	}

}
