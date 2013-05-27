package hr.ante.test.asktable.listeners;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tracker;

import de.kupzog.ktable.KTable;

public class HeaderListener implements Listener, ControlListener {
    KTable table_ = null;

    Point initPoint = null;
    Tracker tracker = null;

    public HeaderListener(KTable table) {
        table_ = table;
    }
    public void mouseDoubleClick(MouseEvent e) {
    }
    public void mouseUp(int x , int y ) {
        Point p = table_.getCellForCoordinates(x, y);


        System.out.println("Up " + p);

        if (
                (p.y < table_.getModel().getFixedHeaderRowCount()) &&
                (p.x >= table_.getModel().getFixedHeaderColumnCount()) &&
                (p.x != initPoint.x) &&
                (p.y == initPoint.y)
        ){

        //if (p.y == initPoint.y) {
            System.out.println("Move  " + initPoint + " to " + p);
            //((SpanModel)table_.getModel())switchColumn(p.x, initPoint.x);
//            /*(KTableSortedModel)*/table_.getModel()switchColumn(p.x, initPoint.x);
        }
        initPoint = null;
        //tracker.close();
    }


//

    	//String temp = null;

    	//Point tempPoint = table_.getCellForCoordinates(x, y);

    	//temp = table_.getModel().getContentAt(x, row

//    	meta.put("0", "");
//		meta.put("1", "DateTime");
//		meta.put("2", "Time");
////		meta.put("3", "Currency"
//		for (int row=table_.getModel().getFixedHeaderRowCount(); row<table_.getModel().getRowCount(); row++)
//	        for (int col=table_.getModel().getFixedHeaderColumnCount(); col<table_.getModel().getColumnCount(); col++) {
//	        	if(col==x){
//	        		rowsX.put(row,table_.getModel().getContentAt(x, row));
//	        	}
//	        	if(col==x2){
//	        		rowsX2.put(row,table_.getModel().getContentAt(x2, row));
//	        	}
//	        }
//		for (int row=table_.getModel().getFixedHeaderRowCount(); row<table_.getModel().getRowCount(); row++)
//			for (int col=table_.getModel().getFixedHeaderColumnCount(); col<table_.getModel().getColumnCount(); col++) {
//				if(col==x){
//					table_.getModel().setContentAt(col, row,rowsX2.get(row));
//
//
//				}
//				if(col==x2){
//					table_.getModel().setContentAt(col, row,rowsX.get(row));
//
//
//				}
//			}






	public void mouseDown(Event e) {

        Point p = table_.getCellForCoordinates(e.x, e.y);
        if ((p.y < table_.getModel().getFixedHeaderRowCount()) &&  (p.x >= table_.getModel().getFixedHeaderColumnCount()))
        	initPoint = p;

        System.out.println("Down " + p);

    }
    public void mouseMove(Event event) {
        int JITTER = 8;
        Display display = event.display;
        Shell shell = display.getActiveShell();

        Rectangle tableLocation = display.map(table_, null, shell.getClientArea ());
        System.out.println("table location " + tableLocation);

        Point p = table_.getCellForCoordinates(event.x, event.y);
        Rectangle rect  = table_.getCellRect(p.x, p.y);


        int deltaX = initPoint.x - event.x, deltaY = initPoint.y - event.y;
        if (Math.abs (deltaX) < JITTER && Math.abs (deltaY) < JITTER) {
            return;
        }

        tracker = new Tracker (table_, SWT.LEFT | SWT.RIGHT);

        tracker.setRectangles (new Rectangle [] {rect});
        tracker.open ();
        //FALL THROUGH

        Rectangle finalPosition = tracker.getRectangles()[0];
        System.out.println(finalPosition);

        int posX = finalPosition.x +  finalPosition.width / 2  ;//- tableLocation.x;
        int posY = finalPosition.y +  finalPosition.height / 2 ;//- tableLocation.y;
        mouseUp (posX,posY);

    }
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.MouseDown:
            mouseDown((event));
            break;
        case SWT.MouseMove:
            if (initPoint == null) return;
            mouseMove(event);

        case SWT.MouseUp:
            break;
    }
    }
    public void controlMoved(ControlEvent e) {
    }
    public void controlResized(ControlEvent e) {
        // TODO Auto-generated method stub
    }
}