package hr.ante.isms.parts.table.model;


import hr.ante.isms.connection.DataFromDatabase;
import hr.ante.isms.connection.DatabaseConnectionDoma;
import hr.ante.isms.parts.DataFromServer;
import hr.ante.isms.parts.table.ASRiskTextCellRenderer;
import hr.ante.test.renderers.ASCurrencyTextCellRenderer;
import hr.ante.test.renderers.ASFixedCellRenderer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

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

public class ListThreatASKTableModel extends KTableSortedModel {

	private int columnNumber=-1;
	private int rowNumber=0;
	public ListThreatASKTableModel m_TableLA;
	private HashMap content = new HashMap();
	public HashMap meta = new HashMap();
	private int[] colWidths;
	private DataFromDatabase dB;

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

	private KTableCellRenderer m_CheckableRenderer = new CheckableCellRenderer(
			CheckableCellRenderer.INDICATION_CLICKED
					| CheckableCellRenderer.INDICATION_FOCUS);


	public ASRiskTextCellRenderer m_1Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN) );
	public ASRiskTextCellRenderer m_2Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_BLUE) );
	public ASRiskTextCellRenderer m_3Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_GRAY) );
	public ASRiskTextCellRenderer m_4Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_RED) );
	public ASRiskTextCellRenderer m_5Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED) );



	public void readAllFromDB(){
		content = dB.getContentFromDB("view_threat", "ListThreatASKTableModel");
		DataFromServer.listThreatASKTableModel=this;
		columnNumber=4;
		rowNumber=Integer.parseInt(content.get("@@brojac").toString());
		initialize();
	}
	public ListThreatASKTableModel() {

		dB = new DataFromDatabase();

		initialize();


		//meta.put("0", "");
		for (int i=1; i<=columnNumber;i++)
			meta.put(""+i+"", "Text");


		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}


		readAllFromDB();

		setColumnWidth(0, 30);
		setColumnWidth(1,200);
		setColumnWidth(2,100);
		setColumnWidth(3,200);
		setColumnWidth(4,100);

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

//		if(content.get(9+"/"+row)==null)
//			return m_DefaultRenderer;
//
//		if (content.get(9+"/"+row).equals("1"))
//		{
//			return m_1Renderer;
//		}
//
//		if (content.get(9+"/"+row).equals("2"))
//				{
//			return m_2Renderer;
//		}
//
//		if (content.get(9+"/"+row).equals("3")) {
//			return m_3Renderer;
//		}
//
//		if (content.get(9+"/"+row).equals("4")) {
//
//			return m_4Renderer;
//		}
//
//		if (content.get(9+"/"+row).equals("5")) {
//			return m_5Renderer;
//		}


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
		return rowNumber + getFixedRowCount();
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
		return "Èelija: " + col + "/" + row;
	}
}
