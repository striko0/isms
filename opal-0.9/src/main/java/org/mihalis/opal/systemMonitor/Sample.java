/*******************************************************************************
 * Copyright (c) 2011 2Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - initial API and implementation
 *******************************************************************************/
package org.mihalis.opal.systemMonitor;

/**
 * This interface represents a data sample that will be displayed in the
 * SystemMonitor widget
 */
public interface Sample {
	/**
	 * @return the current value of the sample
	 */
	double getValue();

	/**
	 * @return the max value of the sample
	 */
	double getMaxValue();

}
