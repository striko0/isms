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

public class ISMSASKTableModel extends KTableSortedModel {

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

	private KTableCellRenderer m_TextRenderer = new TextCellRenderer(TextCellRenderer.INDICATION_FOCUS);

	private KTableCellRenderer m_FixedCheckableRenderer = new FixedCheckableCellRenderer(
			FixedCheckableCellRenderer.STYLE_PUSH | FixedCheckableCellRenderer.INDICATION_FOCUS | FixedCheckableCellRenderer.INDICATION_SORT);

	private KTableCellRenderer m_CheckableRenderer = new CheckableCellRenderer(
			CheckableCellRenderer.INDICATION_CLICKED
					| CheckableCellRenderer.INDICATION_FOCUS);


	public ASRiskTextCellRenderer m_3Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_GRAY) );
	public ASRiskTextCellRenderer m_4Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_RED) );
	public ASRiskTextCellRenderer m_2Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_BLUE) );




	/**
   *
   */
	public ISMSASKTableModel() {
		// before initializing, you probably have to set some member values
		// to make all model getter methods work properly.

		initialize();


		//statusLine.setMessage("hello");





		//meta.put("0", "");
		meta.put("1", "Text");
		meta.put("2", "Text");
		meta.put("3", "Text");
		meta.put("4", "Text");
		meta.put("5", "Text");
		meta.put("6", "Text");
		meta.put("7", "Text");
		meta.put("8", "Text");
		meta.put("9", "Text");




		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}

		content = new HashMap();

		/**OVO TRIBA IZMINITI **/
		setContentAt(0, 0, "#");

		setContentAt(1, 0, "Šifra");
		setContentAt(2, 0, "Naziv");
		setContentAt(3, 0, "Podkategorija");
		setContentAt(4, 0, "Vlasnik");
		setContentAt(5, 0, "Vrijednost");
		setContentAt(6, 0, "Povjerljivost");
		setContentAt(7, 0, "Cjelovitost");
		setContentAt(8, 0, "Raspoloživost");
		setContentAt(9, 0, "Poslovni Utjecaj");

		putContents();

		setColumnWidth(0, 30);
		setColumnWidth(1,50);
		setColumnWidth(2,200);
		setColumnWidth(5,50);
		setColumnWidth(6,100);
		setColumnWidth(7,100);
		setColumnWidth(8,100);

//		setColumnWidth(2, 75);

	}


	private void putContents() {

		//Zero Column
		//setContentAt(0, 0, "#");
		setContentAt(0, 1, "1");
		setContentAt(0, 2, "2");
		setContentAt(0, 3, "3");
		setContentAt(0, 4, "4");
		setContentAt(0,5, "5");
		setContentAt(0, 6, "6");
		setContentAt(0, 7, "7");
		setContentAt(0, 8, "8");
		setContentAt(0, 9, "9");
		setContentAt(0, 10, "10");
		setContentAt(0, 11, "11");
		setContentAt(0, 12, "12");

		//1 Column
		//setContentAt(1, 0, "Šifra");
		setContentAt(1, 1, "147");
		setContentAt(1, 2, "148");
		setContentAt(1, 3, "149");
		setContentAt(1, 4, "143");
		setContentAt(1,5, "151");
		setContentAt(1, 6, "157");
		setContentAt(1, 7, "152");
		setContentAt(1, 8, "1151");
		setContentAt(1, 9, "1189");
		setContentAt(1, 10, "1190");
		setContentAt(1, 11, "272");
		setContentAt(1, 12, "1150");

		//2 Column
		//setContentAt(2, 0, "Naziv");
		setContentAt(2, 1, "Zaštita od zloæudnog i prenosivog koda");
		setContentAt(2, 2, "Kreiranje sigurnosnih kopija");
		setContentAt(2, 3, "Upravljanje sigurnošèu mreže");
		setContentAt(2, 4, "Sigurnost opomene");
		setContentAt(2, 5, "Razmjena informacija");
		setContentAt(2, 6, "Kontrola obrade informacija u aplikacijama");
		setContentAt(2, 7, "Internet bankarstvo");
		setContentAt(2, 8, "PORTAL programski kod");
		setContentAt(2, 9, "BSA izvorni kod (source)");
		setContentAt(2, 10, "BSA izvršni kod,baza i žurnali");
		setContentAt(2, 11, "Ugovor o isporuci informacijskog sustava ");
		setContentAt(2, 12, "Izjave o zaprimanju korisnièkih naziva i zaporki");

		//3 Column
		//setContentAt(3, 0, "Podkategorija");
		setContentAt(3, 1, "201011 - Proces druga razina");
		setContentAt(3, 2, "201011 - Proces druga razina");
		setContentAt(3, 3, "201011 - Proces druga razina");
		setContentAt(3, 4, "201011 - Proces druga razina");
		setContentAt(3, 5, "201011 - Proces druga razina");
		setContentAt(3, 6, "201011 - Proces druga razina");
		setContentAt(3, 7, "301016 - Aplikativni softver");
		setContentAt(3, 8, "101010 - Programski kod ");
		setContentAt(3, 9, "101010 - Programski kod ");
		setContentAt(3, 10, "101010 - Programski kod ");
		setContentAt(3, 11, "1020 - Papirnati Dokument");
		setContentAt(3, 12, "1020 - Papirnati Dokument");

		//4 Column
		// setContentAt(4, 0, "Vlasnik");
		setContentAt(4, 1, "Direkcija informatike");
		setContentAt(4, 2, "Direkcija informatike");
		setContentAt(4, 3, "Direkcija informatike");
		setContentAt(4, 4, "Direkcija informatike");
		setContentAt(4, 5, "Direkcija informatike");
		setContentAt(4, 6, "Direkcija informatike");
		setContentAt(4, 7, "Direkcija informatike");
		setContentAt(4, 8, "Direkcija informatike");
		setContentAt(4, 9, "Direkcija informatike");
		setContentAt(4, 10, "Direkcija informatike");
		setContentAt(4, 11, "Direkcija informatike");
		setContentAt(4, 12, "Direkcija informatike");

		//5 Column
		//setContentAt(5, 0, "Vrijednost");
		setContentAt(5, 1, "3");
		setContentAt(5, 2, "3");
		setContentAt(5, 3, "3");
		setContentAt(5, 4, "4");
		setContentAt(5,5, "3");
		setContentAt(5, 6, "3");
		setContentAt(5, 7, "4");
		setContentAt(5, 8, "3");
		setContentAt(5, 9, "3");
		setContentAt(5, 10, "4");
		setContentAt(5, 11, "4");
		setContentAt(5, 12, "2");

		//Sixth Column
		// setContentAt(6, 0, "Povjerljivost");
		setContentAt(6, 1, "Srednja (povjerljivo)");
		setContentAt(6, 2, "Visoka (tajno)");
		setContentAt(6, 3, "Srednja (povjerljivo");
		setContentAt(6, 4, "Niska (ogranièeno)");
		setContentAt(6, 5, "Srednja (povjerljivo");
		setContentAt(6, 6, "Srednja (povjerljivo");
		setContentAt(6, 7, "Srednja (povjerljivo");
		setContentAt(6, 8, "Srednja (povjerljivo");
		setContentAt(6, 9, "Srednja (povjerljivo");
		setContentAt(6, 10, "Visoka (tajno)");
		setContentAt(6, 11, "Visoka (tajno)");
		setContentAt(6, 12, "Niska (ogranièeno)");

		//Seventh Column
		// setContentAt(7, 0, "Cjelovitost");
		setContentAt(7, 1, "Visoka");
		setContentAt(7, 2, "Srednja");
		setContentAt(7, 3, "Srednja");
		setContentAt(7, 4, "Srednja");
		setContentAt(7, 5, "Srednja");
		setContentAt(7, 6, "Visoka");
		setContentAt(7, 7, "Visoka");
		setContentAt(7, 8, "Visoka");
		setContentAt(7, 9, "Vrlo visoka");
		setContentAt(7, 10, "Vrlo visoka");
		setContentAt(7, 11, "Vrlo visoka");
		setContentAt(7, 12, "Niska");

		//8 Column
		// setContentAt(8, 0, "Raspoloživost");
		setContentAt(8, 1, "Važna (do 8 sati)");
		setContentAt(8, 2, "Nije vrlo važna (do 48 sati)");
		setContentAt(8, 3, "Važna (do 8 sati)");
		setContentAt(8, 4, "Nije vrlo važna (do 48 sati)");
		setContentAt(8, 5, "Važna (do 8 sati)");
		setContentAt(8, 6, "Nije vrlo važna (do 48 sati)");
		setContentAt(8, 7, "Vrlo važna (do 1 sat)");
		setContentAt(8, 8, "Nije važna (do 72 sata)");
		setContentAt(8, 9, "Nije važna (do 72 sata)");
		setContentAt(8, 10, "Važna (do 8 sati)");
		setContentAt(8, 11, "Važna (do 8 sati)");
		setContentAt(8, 12, "Nije važna (do 72 sata)");

		// 9 Column
		// setContentAt(9, 0, "Klasifikacija");??
		setContentAt(9, 1, "Nije vrlo važna");
		setContentAt(9, 2, "Važna");
		setContentAt(9, 3, "Nije vrlo važna");
		setContentAt(9, 4, "Nije vrlo važna");
		setContentAt(9, 5, "Važna");
		setContentAt(9, 6, "Važna");
		setContentAt(9, 7, "Važna");
		setContentAt(9, 8, "Nije vrlo važna");
		setContentAt(9, 9, "Nije vrlo važna");
		setContentAt(9, 10, "Važna");
		setContentAt(9, 11, "Kritièna");
		setContentAt(9, 12, "Nije važna");

		//10 Column
		// setContentAt(10, 0, "Klasifikacija");??
		setContentAt(10, 1, "Vrlo osjetljivo");
		setContentAt(10, 2, "Vrlo osjetljivo");
		setContentAt(10, 3, "Vrlo osjetljivo");
		setContentAt(10, 4, "Osjetljivo");
		setContentAt(10, 5, "Vrlo osjetljivo");
		setContentAt(10, 6, "Vrlo osjetljivo");
		setContentAt(10, 7, "Vrlo osjetljivo");
		setContentAt(10, 8, "Vrlo osjetljivo");
		setContentAt(10, 9, "Vrlo osjetljivo");
		setContentAt(10, 10, "Vrlo osjetljivo");
		setContentAt(10, 11, "Vrlo osjetljivo");
		setContentAt(10, 12, "Osjetljivo");
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

		if (getContentAt(5, row) == "2") {
			return m_2Renderer;
		}

		if (getContentAt(5, row) == "3") {
			return m_3Renderer;
		}

		if (getContentAt(5, row) == "4") {
//			 for (int i=getFixedHeaderRowCount(); i<getRowCount(); i++)
//			        for (int j=getFixedHeaderColumnCount(); j<getColumnCount(); j++)
							return m_4Renderer;
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
		return 12 + getFixedRowCount();
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
