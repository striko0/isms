package hr.ante.isms.parts.table;

/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


import hr.ante.test.asktable.ASTableModel4;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;


public class ASSortComparatorISMS extends KTableSortComparator {
	KTableSortedModel m_Model;

	public ASSortComparatorISMS(KTableSortedModel model, int columnIndex,
			int direction) {
		super(model, columnIndex, direction);
		m_Model = model;
	}

	public static final int STRING_COMPARATOR = 1;//-
	public static final int DATE_COMPARATOR = 2;//-
	public static final int INT_COMPARATOR = 4;//-
	public static final int HOUR_COMPARATOR = 5;//-
	public static final int DOUBLE_COMPARATOR = 8;//-

	private int direction;


	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableSortComparator#doCompare(java.lang.Object,
	 * java.lang.Object, int, int)
	 */
	public int doCompare(Object o1, Object o2, int row1, int row2) {


		/** NULL POINTER GREŠKA ako nije ispunjena meta HashMapa **/
//		String metaData = "";
//		try {
//			metaData = ((ListAssetASKTableModel) getModel()).getMeta()
//					.get(super.getColumnToSortOn() + "").toString();
//		} catch (Exception ex) {
//			System.out.println("ERROR: NO COLUMNS " + metaData);
//		}
//
//		metaData = ((ListAssetASKTableModel) getModel()).getMeta()
//				.get(super.getColumnToSortOn() + "").toString();
//		if(metaData==null)
//			metaData="";
//
//		System.out.println(metaData);
//
//		if (metaData == "DateTime") {
//			return DateTimeComparator(o1, o2);
//		}
//
//		else if (metaData == "Time") {
//			return TimeComparator(o1, o2);
//		}
//
//		else if (metaData == "Currency") {
//			return CurrencyComparator(o1, o2);
//		}
//
//		else if (metaData == "Checkbox") {
//			return CheckboxComparator(o1, o2);
//
//		} else {

			return StringComparator(o1, o2);

		}
//		}
//		 else if (metaData == "Combo")
//		 {
//		 return StringComparator(o1,o2);
//		 }


//
//		switch (metaData) {
//		case INT_COMPARATOR:
//			currentComparator = intComparator;
//			break;
//
//		case STRING_COMPARATOR:
//			currentComparator = strComparator;
//			break;
//
//		case "DateTime":
//			result=DateTimeComparator(o1,o2);
//
//			break;
//
//		case DOUBLE_COMPARATOR:
//			currentComparator = doubleComparator;
//			break;
//
//		case HOUR_COMPARATOR:
//			currentComparator = hourComparator;
//			break;
//
//		default:
//			result=StringComparator(o1,o2);
//
//
//		}
//	}


public int DateTimeComparator(Object arg0, Object arg1)
{
	Date subdate1 = null;
	Date subdate2 = null;

	DateFormat df_europe = new SimpleDateFormat("dd.MM.yyyy");
	DateFormat df_usa = new SimpleDateFormat("yyyy/MM/dd");

	DateFormat df = df_europe;

	String target1 = arg0.toString();
	String target2 = arg1.toString();
	if (target1 != null && target1.length()>0){

		try {
			subdate1 = df.parse(target1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return +1;

		}
	}
	else {

		try {
			subdate1 = df.parse(target1);
			//t1 = Integer.parseInt(target1.substring(0, target1.length()));
		} catch (ParseException e) {
			return +1;
		}
	}


	if (target2 != null  && target2.length()>0){

	try {
		subdate2 = df.parse(target2);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
//		return 0;
		return -1;
	}
	}

	else {

		try {
			subdate2 = df.parse(target2);
			//			t2 = Integer.parseInt(target2.substring(0, target2.length()));
		} catch (ParseException ex) {
			return -1;
		}
	}

	if (subdate1.before(subdate2))
		return +1;
	if (subdate2.before(subdate1))
		return -1;
	return 0;

	}
//	if (t2 > t1)
//		return 1;
//	else if (t1 > t2)
//		return -1;
//	else
//		return 0;
//}

public int TimeComparator(Object arg0, Object arg1)
{
	Date subTime1 = null;
	Date subTime2 = null;

	DateFormat df = new SimpleDateFormat("HH:mm:ss");

	String target1 = arg0.toString();
	String target2 = arg1.toString();

//	long t1;
//	long t2;


	if (target1 != null && target1.length()>0){

		try {
			subTime1 = df.parse(target1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return +1;
		}

//		t1 = subTime1.getTime();
	}
	else {

		try {
			subTime1 = df.parse(target1);
//			t1 = Integer.parseInt(target1.substring(0, target1.length()));
		} catch (ParseException e) {
			return +1;
		}
	}


	if (target2 != null  && target2.length()>0){

	try {
		subTime2 = df.parse(target2);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		return -1;
	}
//		t2 = subTime2.getTime();
	}

	else {

		try {
			subTime2 = df.parse(target2);
//			t2 = Integer.parseInt(target2.substring(0, target2.length()));
		} catch (ParseException e) {
			return -1;
		}
	}

	if (subTime1.before(subTime2))
		return +1;
	if (subTime2.before(subTime1))
		return -1;
	return 0;

	}

public int IntComparator(Object arg0, Object arg1)
{


	String s1 = arg0.toString();
	int v1;
	try {

		v1 = Integer.parseInt(s1.substring(0, s1.length()));
	} catch (NumberFormatException ex) {
		return +1;
	}

	String s2 = arg1.toString();
	int v2;
	try {
		// v2 = Integer.parseInt(s2.substring(0, s2.indexOf(' ')));
		v2 = Integer.parseInt(s2.substring(0, s2.length()));
	} catch (NumberFormatException ex) {

		return -1;
	}

	if (v1 < v2)
		return +1;
	if (v1 > v2)
		return -1;
	return 0;

	}


public int CurrencyComparator(Object arg0, Object arg1)
{


	NumberFormat nf = new DecimalFormat("#,##0.00");
	String s1 = arg0.toString();
	String s2 = arg1.toString();

	Number subString1;
	Number subString2;

	double t1=0;
	double t2=0;

	if (s1 != null && s1.length()>0){

		try {
			subString1 = nf.parse(s1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return +1;
		}

		t1 = subString1.doubleValue();

	}
	else {
		try {
			subString1 = nf.parse(s1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return +1;
		}

	}


	if (s2 != null  && s2.length()>0){

	try {
		subString2 = nf.parse(s2);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		return -1;
	}
		t2=subString2.doubleValue();
	}

	else {
		try {
		subString2 = nf.parse(s2);
		}catch (ParseException e) {
		// TODO Auto-generated catch block
		return -1;
		}

	}

	if (t1 < t2)
		return +1;
	if (t1 > t2)
		return -1;
	return 0;


	}

	public int StringComparator(Object arg0, Object arg1) {

		String s1 = arg0.toString();
		String s2 = arg1.toString();

		int t1=0;
		int t2=0;

		if(s1.length()==0)
			return +1;

		if(s2.length()==0)
			return -1;

		return s1.compareTo(s2);


	}

	public int CheckboxComparator(Object arg0, Object arg1) {

//		boolean s1 = Boolean.getBoolean(arg0.toString());
//		boolean s2 = Boolean.getBoolean(arg1.toString());
		String s1 = arg0.toString();
		String s2 = arg1.toString();

		int t1=0;
		int t2=0;
		if(s1=="true")
			t1=1;

		if(s2=="true")
			t2=1;

		if (t1 < t2)
			return +1;
		if (t1 > t2)
			return -1;
		return 0;


	}


}


//		 if (t1 < t2)
//		 return -1;
//		 else if (t1 > t2)
//		 return +1;
//		 else
//		 return 0;
//		 }







