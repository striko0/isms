/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
    
    Author: Friederich Kupzog  
    fkmk@kupzog.de
    www.kupzog.de/fkmk
*/
package de.kupzog.examples;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.SWTX;

/**
 * Wrapper for Image with text.
 * @author Friederich Kupzog
 */
public class TownExampleContent {
	public String name;
	public Image image;
	public String country;
	public String notes;
	
	public TownExampleContent(String name, String country)
	{
		this.name = name;
		this.country = country;
		image = SWTX.loadImageResource(Display.getCurrent(), 
			"/icons/examples/"+name+".gif");
		System.out.println(image);
		notes = "Double click to edit and use \n" +			"Shift+Enter to start a new line...";
	}
	
	/* 
	 * overridden from superclass
	 */
	public String toString() {
		return notes;
	}

}
