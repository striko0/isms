package hr.ante.test.asktable;


import hr.ante.test.asktable.editors.ASKTableCellEditorComboText;
import hr.ante.test.asktable.editors.ASKTableCellEditorCurrency3;
import hr.ante.test.asktable.editors.ASKTableCellEditorDateTime2;
import hr.ante.test.asktable.editors.ASKTableCellEditorTime;
import hr.ante.test.renderers.ASCurrencyTextCellRenderer;
import hr.ante.test.renderers.ASFixedCellRenderer;

import java.util.HashMap;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableSortedModel;
import de.kupzog.ktable.SWTX;
import de.kupzog.ktable.editors.KTableCellEditorCheckbox2;
import de.kupzog.ktable.editors.KTableCellEditorText2;
import de.kupzog.ktable.renderers.CheckableCellRenderer;
import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCheckableCellRenderer;
import de.kupzog.ktable.renderers.TextCellRenderer;

public class ASTableModel4 extends KTableSortedModel {

	private Random rand = new Random();
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

	private KTableCellRenderer m_TextRenderer = new TextCellRenderer(0);
	private KTableCellRenderer m_FixedCheckableRenderer = new FixedCheckableCellRenderer(
			FixedCheckableCellRenderer.STYLE_PUSH | FixedCheckableCellRenderer.INDICATION_FOCUS | FixedCheckableCellRenderer.INDICATION_SORT);

	private KTableCellRenderer m_CheckableRenderer = new CheckableCellRenderer(
			CheckableCellRenderer.INDICATION_CLICKED
					| CheckableCellRenderer.INDICATION_FOCUS);


	public ASCurrencyTextCellRenderer m_CurrencyRenderer = new ASCurrencyTextCellRenderer(ASCurrencyTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_RIGHT | SWTX.ALIGN_VERTICAL_BOTTOM); /* = new TextCellRenderer(TextCellRenderer.INDICATION_CLICKED
			| TextCellRenderer.INDICATION_FOCUS);*/





	/**
   *
   */
	public ASTableModel4() {
		// before initializing, you probably have to set some member values
		// to make all model getter methods work properly.

		initialize();


		//statusLine.setMessage("hello");



		// we don't want the default foreground color on text cells,
		// so we change it:
		//meta.put("0", "");
		meta.put("1", "DateTime");
		meta.put("2", "Time");
		meta.put("3", "Currency");
		meta.put("4", "Checkbox");
		meta.put("5", "Combo");

		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}

		// rowHeight = 18;
		content = new HashMap();

		/**OVO TRIBA IZMINITI **/
		setContentAt(0, 0, "");
		setContentAt(0, 1, "1");
		setContentAt(0, 2, "2");
		setContentAt(0, 3, "3");
		setContentAt(0, 4, "4");

		setContentAt(1, 0, "DateTime");
		setContentAt(2, 0, "Time");
		setContentAt(3, 0, "Currency");
		setContentAt(4, 0, "Checkbox");
		setContentAt(5, 0, "Combo");


		setColumnWidth(0, 30);
		setColumnWidth(1, 75);
		setColumnWidth(2, 75);

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
//		String erg = (String) content.get(col + "/" + row);
//		if (erg != null)
//			return erg;
//
//		return "";


		Boolean val = null;

		if(col!=4 | (col==4 && row==0) )
		{

			String erg = (String) content.get(col + "/" + row);
			if (erg != null)
				return erg;
			return "";
		}
		else
		{

			val = (Boolean) content.get(col + "/" + row);
			if (val != null)
				return val;
			else
		            val = new Boolean(false);

			/**OVO TRIBA PROVJERITI I IZMINITI **/
			if (row==0){
				return "Checkbox";
			}
			else{
				content.put(col+"/"+row, val);
				 return val;
			}


		}


	}

	// Rendering
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		if (isFixedCell(col, row))
		{
					if(col==0)
						return m_FixedFirstColumnRenderer;
			return m_FixedRenderer;
		}
		if(col==3 && !isFixedCell(3, row))
		{

			String tempString = (String) content.get(3 + "/" + row);
			if (tempString!=null && tempString.length()>0 && tempString.contains("-")) {

				m_CurrencyRenderer.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			}
			else
				m_CurrencyRenderer.setForeground(null);
			return m_CurrencyRenderer;
		}


		if (col == 4)
		{

			if(row==0){
				return m_FixedCheckableRenderer;
			}
			return m_CheckableRenderer;
		}

//		return m_DefaultRenderer;r
		return m_DefaultRenderer;
	}

	/*
	 * overridden from superclass
	 */
	public KTableCellEditor doGetCellEditor(int col, int row) {
		if (col < getFixedColumnCount() || row < getFixedRowCount())
			return null;

		if (col == 1) {

			ASKTableCellEditorDateTime2 dateTime = new ASKTableCellEditorDateTime2();


			return dateTime;
		}
		if (col == 2) {

			return new ASKTableCellEditorTime();
		}
		if (col == 3) {


			return new ASKTableCellEditorCurrency3();

		}


		if (col == 4) {

			Rectangle imgBounds = CheckableCellRenderer.IMAGE_CHECKED.getBounds();
			Point sensible = new Point(imgBounds.width, imgBounds.height);
			return new KTableCellEditorCheckbox2(sensible,SWTX.ALIGN_HORIZONTAL_CENTER, SWTX.ALIGN_VERTICAL_CENTER);

		}
		if(col == 5)
		{

			ASKTableCellEditorComboText e = new ASKTableCellEditorComboText();
            e.setItems(new String[] { "First text", "Second text","first ala", "second bala", "third text" });
            return e;

		}

		else {
			return new KTableCellEditorText2();
		}

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
		return 4 + getFixedRowCount();
	}

	public int doGetColumnCount() {
		return meta.size() + getFixedColumnCount();
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
