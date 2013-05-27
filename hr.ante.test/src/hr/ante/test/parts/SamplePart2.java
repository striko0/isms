/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package hr.ante.test.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;
import hr.ante.test.asktable.FormaA4;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class SamplePart2{
	private Table table;
	private FormaA4 formaA4;


	@PostConstruct
	public void createComposite(Composite parent, int style) {
		parent.setLayout(new GridLayout(1, false));

//		formaA4 = new FormaA4(parent, SWT.NONE);
//		formaA4.setLayout(new FillLayout(SWT.HORIZONTAL));
//		GridData gd_formaA4 = new GridData(SWT.LEFT, SWT.FILL, true, true, 1, 1);
//		gd_formaA4.heightHint = 111;
//		formaA4.setLayoutData(gd_formaA4);





	}
}
