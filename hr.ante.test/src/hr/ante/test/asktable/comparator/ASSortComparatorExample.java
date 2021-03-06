package hr.ante.test.asktable.comparator;

/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


import hr.ante.test.asktable.ASTableModel4;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;

/**
 * 
 * @author Lorenz Maierhofer <lorenz.maierhofer@logicmindguide.com>
 */
public class ASSortComparatorExample extends KTableSortComparator {

    public ASSortComparatorExample(KTableSortedModel model, int columnIndex, int direction) {
        super(model, columnIndex, direction);
    }

    /* (non-Javadoc)
     * @see de.kupzog.ktable.KTableSortComparator#doCompare(java.lang.Object, java.lang.Object, int, int)
     */
    public int doCompare(Object o1, Object o2, int row1, int row2) {
    	
    	
        if (((ASTableModel4)getModel()).getMeta().get(super.getColumnToSortOn()+"").toString() != null){
//    	System.out.println("column index je: " + super.getColumnToSortOn());
        String metaData = ((ASTableModel4)getModel()).getMeta().get(super.getColumnToSortOn()+"").toString();
    	//System.out.println(metaData);
        }
        String s1 = o1.toString();
        int v1;
        try {
        	
             v1 = Integer.parseInt(s1.substring(0, s1.length()));
        } catch (NumberFormatException ex) {
            return 1;
        }
        
        String s2 = o2.toString();
        int v2;
        try {
//            v2 = Integer.parseInt(s2.substring(0, s2.indexOf(' ')));
        	v2 = Integer.parseInt(s2.substring(0, s2.length()));
        } catch (NumberFormatException ex) {
        	
            return -1;
        }
        
        if (v1<v2) return -1;
        if (v1>v2) return +1;
        return 0;
    }

}

