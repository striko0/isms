package hr.ante.isms.parts.table;


import hr.ante.test.renderers.ASCurrencyTextCellRenderer;
import hr.ante.test.renderers.ASFixedCellRenderer;

import java.util.HashMap;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;
import de.kupzog.ktable.editors.KTableCellEditorText2;
import de.kupzog.ktable.renderers.CheckableCellRenderer;
import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCheckableCellRenderer;
import de.kupzog.ktable.renderers.TextCellRenderer;

public class ControlsAnalysisASKTableModel extends KTableSortedModel {

	private Random rand = new Random();
	private int columnNumber=-1;
	private HashMap content = new HashMap();
	public HashMap meta = new HashMap();
	private int[] colWidths;

	private int rowHeight;

	private KTableCellRenderer m_FixedFirstColumnRenderer = new ASFixedCellRenderer(
			ASFixedCellRenderer.STYLE_FLAT	| ASFixedCellRenderer.INDICATION_FOCUS
			| ASFixedCellRenderer.INDICATION_CLICKED |  ASFixedCellRenderer.INDICATION_FOCUS_ROW);

	private KTableCellRenderer m_FixedRenderer = new ASFixedCellRenderer(
			ASFixedCellRenderer.STYLE_PUSH | ASFixedCellRenderer.INDICATION_SORT
					| ASFixedCellRenderer.INDICATION_FOCUS
					| ASFixedCellRenderer.INDICATION_CLICKED |  ASFixedCellRenderer.INDICATION_FOCUS_ROW);

	private KTableCellRenderer m_DefaultRenderer = new DefaultCellRenderer(0);

	private KTableCellRenderer m_FixedCheckableRenderer = new FixedCheckableCellRenderer(
			FixedCheckableCellRenderer.STYLE_PUSH | FixedCheckableCellRenderer.INDICATION_FOCUS | FixedCheckableCellRenderer.INDICATION_SORT);


	/**
   *
   */
	public ControlsAnalysisASKTableModel() {
		// before initializing, you probably have to set some member values
		// to make all model getter methods work properly.

		initialize();

		/** BROJ STUPACA!
		 *
		 */
		columnNumber=3;
		//meta.put("0", "");
		for (int i=1; i<=columnNumber;i++)
			meta.put(""+i+"", "Text");

		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}

		content = new HashMap();

		putContents();




//		setColumnWidth(2, 75);

	}


	private void putContents() {

		//Zero Column
		setContentAt(0, 0, "#");
		for (int i=getFixedRowCount(); i<getRowCount();i++)
			setContentAt(0, i, ""+i+"");

		setColumnWidth(0, 30);
		//1 Column
		setContentAt(1, 0, "Oznaka Kontrole");
//		setContentAt(1, 1, "147");
//		setContentAt(1, 2, "148");
//		setContentAt(1, 3, "149");
//		setContentAt(1, 4, "143");
//		setContentAt(1,5, "151");
//		setContentAt(1, 6, "157");
//		setContentAt(1, 7, "152");
//		setContentAt(1, 8, "1151");
//		setContentAt(1, 9, "1189");
//		setContentAt(1, 10, "1190");
//		setContentAt(1, 11, "272");
//		setContentAt(1, 12, "1150");
		setColumnWidth(1,200);


		//2 Column
		setContentAt(2, 0, "Naziv Kontrole");
//		setContentAt(2, 1, "Zaštita od zloæudnog i prenosivog koda");
//		setContentAt(2, 2, "Kreiranje sigurnosnih kopija");
//		setContentAt(2, 3, "Upravljanje sigurnošèu mreže");
//		setContentAt(2, 4, "Sigurnost opomene");
//		setContentAt(2, 5, "Razmjena informacija");
//		setContentAt(2, 6, "Kontrola obrade informacija u aplikacijama");
//		setContentAt(2, 7, "Internet bankarstvo");
//		setContentAt(2, 8, "PORTAL programski kod");
//		setContentAt(2, 9, "BSA izvorni kod (source)");
//		setContentAt(2, 10, "BSA izvršni kod,baza i žurnali");
//		setContentAt(2, 11, "Ugovor o isporuci informacijskog sustava ");
//		setContentAt(2, 12, "Izjave o zaprimanju korisnièkih naziva i zaporki");
		setColumnWidth(2,200);

		//3 Column
		setContentAt(3, 0, "Status Kontrole");
//		setContentAt(3, 1, "201011 - Proces druga razina");
//		setContentAt(3, 2, "201011 - Proces druga razina");
//		setContentAt(3, 3, "201011 - Proces druga razina");
//		setContentAt(3, 4, "201011 - Proces druga razina");
//		setContentAt(3, 5, "201011 - Proces druga razina");
//		setContentAt(3, 6, "201011 - Proces druga razina");
//		setContentAt(3, 7, "301016 - Aplikativni softver");
//		setContentAt(3, 8, "101010 - Programski kod ");
//		setContentAt(3, 9, "101010 - Programski kod ");
//		setContentAt(3, 10, "101010 - Programski kod ");
//		setContentAt(3, 11, "1020 - Papirnati Dokument");
//		setContentAt(3, 12, "1020 - Papirnati Dokument");

		setColumnWidth(3,200);
	}


	public HashMap getMeta() {
		return meta;
	}

	public void switchColumn(int destinationColumn, int initColumn) {
		// TODO Auto-generated method stub
		//content.put(col+"/"+row, val);
		HashMap<Integer,Object> contentFromModel = new HashMap<Integer,Object>();
		for (int row=0; row<getRowCount(); row++){
			if(content.get(initColumn + "/" + row) instanceof Boolean)
				contentFromModel.put(row, (Boolean)content.get(initColumn + "/" + row));
			contentFromModel.put(row, content.get(initColumn + "/" + row));
			if( content.get(destinationColumn + "/" + row) instanceof Boolean)
				content.put(initColumn + "/" + row, (Boolean)content.get(destinationColumn + "/" + row));
			content.put(initColumn + "/" + row, content.get(destinationColumn + "/" + row));
			if(contentFromModel.get(row) instanceof Boolean)
				content.put(destinationColumn + "/" + row, contentFromModel.get(row));
			content.put(destinationColumn + "/" + row, contentFromModel.get(row));
		}


//    //	for (int col=table_.getModel().getFixedHeaderColumnCount(); col<table_.getModel().getColumnCount(); col++)
//    		for (int row=table_.getModel().getFixedHeaderRowCount(); row<table_.getModel().getRowCount(); row++){
//    			rowsX.put(x + "/" + row+"",table_.getModel().getContentAt(x, row));
//    			rowsX2.put(x + "/" + row+"",table_.getModel().getContentAt(x2, row));

    		}

	public Object doGetContentAt(int col, int row) {
		// System.out.println("col "+col+" row "+row);
		String erg = (String) content.get(col + "/" + row);
		if (erg != null)
			return erg;

		return "";

	}

	// Rendering
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		if (isFixedCell(col, row))
		{
					if(col==0)
						return m_FixedFirstColumnRenderer;
			return m_FixedRenderer;
		}



		return m_DefaultRenderer;
	}

	/*
	 * overridden from superclass
	 */
	public KTableCellEditor doGetCellEditor(int col, int row) {
		if (col < getFixedColumnCount() || row < getFixedRowCount())
			return null;
		else
			return new KTableCellEditorText2();


	}

	public void doSetContentAt(int col, int row, Object value) {
		content.put(col + "/" + row, value);
	}

	/**
	 * Implement also cell spans so that it can be demonstrated how the sorting
	 * algorithm works in this case:
	 *
	 * @see de.kupzog.ktable.KTableDefaultModel#doBelongsToCell1(int, int)
	 */
	public Point doBelongsToCell1(int col, int row) {
		if ((col == 2 || col == 3) && !isFixedCell(col, row)) {
			int newRow = row;
			if ((row - getFixedRowCount()) % 2 == 1)
				newRow--;
			return new Point(2, newRow);
		}
		return new Point(col, row);
	}

	public int getInitialColumnWidth(int column) {
		return 90;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableDefaultModel#getInitialRowHeight(int)
	 */
	public int getInitialRowHeight(int row) {
		if (row == 0)
			return 22;
		return 18;
	}

	// Table size:
	public int doGetRowCount() {

		return 0 + getFixedRowCount();
	}

	public int doGetColumnCount() {
		return columnNumber + getFixedColumnCount();
	}

	public int getFixedHeaderRowCount() {
		return 1;
	}

	public int getFixedHeaderColumnCount() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableModel#getFixedSelectableRowCount()
	 */
	public int getFixedSelectableRowCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableModel#getFixedSelectableColumnCount()
	 */
	public int getFixedSelectableColumnCount() {
		return 0;
	}

	public int getInitialFirstRowHeight() {
		return 22;
	}

	public boolean isColumnResizable(int col) {
		return true;
	}

	public boolean isRowResizable(int row) {
		return true;
	}

	public int getRowHeightMinimum() {
		return 18;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableModel#belongsToCell(int, int)
	 */
	public Point doBelongsToCell(int col, int row) {
		// no cell spanning:
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.kupzog.ktable.KTableDefaultModel#getInitialColumnWidth(int)
	 */

	public String doGetTooltipAt(int col, int row) {
		return "Tooltip for cell: " + col + "/" + row;
	}
}
