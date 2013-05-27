package hr.ante.test.asktable;

import java.util.HashMap;

import org.eclipse.swt.graphics.Point;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableModel;
import de.kupzog.ktable.editors.KTableCellEditorCombo;
import de.kupzog.ktable.editors.KTableCellEditorText;

class ASTableModel implements KTableModel {

	private int[] colWidths;

	private int rowHeight;

	private HashMap content;

	/**
   * 
   */
	public ASTableModel() {
		colWidths = new int[getColumnCount()];
		for (int i = 0; i < colWidths.length; i++) {
			colWidths[i] = 100;
		}

		rowHeight = 18;
		content = new HashMap();
	}

	// Inhalte

	public Object getContentAt(int col, int row) {
		// System.out.println("col "+col+" row "+row);
		String erg = (String) content.get(col + "/" + row);
		if (erg != null)
			return erg;
		return "";
	}
	
	public Object getDateContentAt(int col, int row) {
		// System.out.println("col "+col+" row "+row);
		String erg = (String) content.get(col + row);
		if (erg != null)
			
			return erg;
		return "";
	}

	/*
	 * overridden from superclass
	 */
	public KTableCellEditor doGetCellEditor(int col, int row) {

//		if (col == 1){
////			KTableCellEditorDateTime dateTime = new KTableCellEditorDateTime();
//			
//			
//			return new KTableCellEditorDateTime();
//		}
//		if (col == 3)
//			return new KTableCellEditorTime();
		if (col % 2 == 0) {

			KTableCellEditorCombo e = new KTableCellEditorCombo();
			
			e.setItems(new String[] { "First text", "Second text", "third text" });
			return e;
		} else
			return new KTableCellEditorText();
	}

	/*
	 * overridden from superclass
	 */
	public void setContentAt(int col, int row, Object value) {
		content.put(col + "/" + row, value);
		//
	}

	// Umfang

	public int getRowCount() {
		return 5;
	}

	public int getFixedRowCount() {
		return 1;
	}

	public int getColumnCount() {
		return 7;
	}

	public int getFixedColumnCount() {
		return 0;
	}

	// GroBen

	public int getColumnWidth(int col) {
		return colWidths[col];
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public boolean isColumnResizable(int col) {
		return true;
	}

	public int getFirstRowHeight() {
		return 22;
	}

	public boolean isRowResizable() {
		return true;
	}

	public int getRowHeightMinimum() {
		return 18;
	}

	public void setColumnWidth(int col, int value) {
		colWidths[col] = value;
	}

	public void setRowHeight(int value) {
		if (value < 2)
			value = 2;
		rowHeight = value;
	}

	// Rendering

	public KTableCellRenderer getCellRenderer(int col, int row) {
		return KTableCellRenderer.defaultRenderer;
	}

	@Override
	public String getTooltipAt(int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KTableCellEditor getCellEditor(int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point belongsToCell(int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFixedHeaderRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFixedSelectableRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFixedHeaderColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFixedSelectableColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowHeight(int row) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRowResizable(int row) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRowHeight(int row, int value) {
		// TODO Auto-generated method stub
		
	}

}
