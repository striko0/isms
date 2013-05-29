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

public class ListAssetASKTableModel extends KTableSortedModel {

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

	private KTableCellRenderer m_CheckableRenderer = new CheckableCellRenderer(
			CheckableCellRenderer.INDICATION_CLICKED
					| CheckableCellRenderer.INDICATION_FOCUS);


	public ASRiskTextCellRenderer m_1Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN) );
	public ASRiskTextCellRenderer m_2Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_BLUE) );
	public ASRiskTextCellRenderer m_3Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_GRAY) );
	public ASRiskTextCellRenderer m_4Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_RED) );
	public ASRiskTextCellRenderer m_5Renderer = new ASRiskTextCellRenderer(ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT | SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED) );



	/**
   *
   */
	public ListAssetASKTableModel() {
		// before initializing, you probably have to set some member values
		// to make all model getter methods work properly.

		initialize();


		//statusLine.setMessage("hello");



		/** BROJ STUPACA!
		 *
		 */
		columnNumber=9;
		//meta.put("0", "");
		for (int i=1; i<=columnNumber;i++)
			meta.put(""+i+"", "Text");
//		meta.put("2", "Text");
//		meta.put("3", "Text");
//		meta.put("4", "Text");
//		meta.put("5", "Text");
//		meta.put("6", "Text");
//		meta.put("7", "Text");
//		meta.put("8", "Text");
//		meta.put("9", "Text");
//		meta.put("10", "Text");




		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}

		content = new HashMap();

//		/**OVO TRIBA IZMINITI **/
//		setContentAt(0, 0, "#");
//
//		setContentAt(1, 0, "�ifra");
//		setContentAt(2, 0, "Naziv");
//		setContentAt(3, 0, "Podkategorija");
//		setContentAt(4, 0, "Vlasnik");
//		setContentAt(5, 0, "Vrijednost");
//		setContentAt(6, 0, "Povjerljivost");
//		setContentAt(7, 0, "Cjelovitost");
//		setContentAt(8, 0, "Raspolo�ivost");
//		setContentAt(9, 0, "Poslovni Utjecaj");

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
		setContentAt(0, 0, "#");
		for (int i=getFixedRowCount(); i<getRowCount();i++)
			setContentAt(0, i, ""+i+"");

		//1 Column
		setContentAt(1, 0, "�ifra");
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
		setContentAt(1, 13, "301");
		setContentAt(1, 14, "320");
		setContentAt(1, 15, "1186");
		setContentAt(1, 16, "1161");
		setContentAt(1, 17, "309");

		//2 Column
		setContentAt(2, 0, "Naziv");
		setContentAt(2, 1, "Za�tita od zlo�udnog i prenosivog koda");
		setContentAt(2, 2, "Kreiranje sigurnosnih kopija");
		setContentAt(2, 3, "Upravljanje sigurno��u mre�e");
		setContentAt(2, 4, "Sigurnost opomene");
		setContentAt(2, 5, "Razmjena informacija");
		setContentAt(2, 6, "Kontrola obrade informacija u aplikacijama");
		setContentAt(2, 7, "Internet bankarstvo");
		setContentAt(2, 8, "PORTAL programski kod");
		setContentAt(2, 9, "BSA izvorni kod (source)");
		setContentAt(2, 10, "BSA izvr�ni kod,baza i �urnali");
		setContentAt(2, 11, "Ugovor o isporuci informacijskog sustava ");
		setContentAt(2, 12, "Izjave o zaprimanju korisni�kih naziva i zaporki");
		setContentAt(2, 13, "BSA Jabanet");
		setContentAt(2, 14, "JABAnet certifikati");
		setContentAt(2, 15, "SRVIBA3 server internet bankarstva");
		setContentAt(2, 16, "STARA oprema - neodlo�ena");
		setContentAt(2, 17, "BSA Pla�e komitenata");

		//3 Column
		setContentAt(3, 0, "Podkategorija");
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
		setContentAt(3, 13, "301016 - Aplikativni softver (BSA, Bonitet,�)");
		setContentAt(3, 14, "301016 - Aplikativni softver (BSA, Bonitet,�)");
		setContentAt(3, 15, "Produkcijski server");
		setContentAt(3, 16, "4020 - Oprema i uredaji");
		setContentAt(3, 17, "301016 - Aplikativni softver (BSA, Bonitet,�)");

		//4 Column
		setContentAt(4, 0, "Vlasnik");
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
		setContentAt(4, 13, "Direkcija informatike");
		setContentAt(4, 14, "Direkcija informatike");
		setContentAt(4, 15, "Direkcija informatike");
		setContentAt(4, 16, "Direkcija informatike");
		setContentAt(4, 17, "Direkcija informatike");

		//5 Column
		setContentAt(5, 0, "Vrijednost");
		setContentAt(5, 1, "3");
		setContentAt(5, 2, "3");
		setContentAt(5, 3, "3");
		setContentAt(5, 4, "4");
		setContentAt(5, 5, "3");
		setContentAt(5, 6, "3");
		setContentAt(5, 7, "4");
		setContentAt(5, 8, "3");
		setContentAt(5, 9, "3");
		setContentAt(5, 10, "4");
		setContentAt(5, 11, "4");
		setContentAt(5, 12, "2");
		setContentAt(5, 13, "4");
		setContentAt(5, 14, "5");
		setContentAt(5, 15, "5");
		setContentAt(5, 16, "2");
		setContentAt(5, 17, "1");

		//Sixth Column
		setContentAt(6, 0, "Povjerljivost");
		setContentAt(6, 1, "Srednja (povjerljivo)");
		setContentAt(6, 2, "Visoka (tajno)");
		setContentAt(6, 3, "Srednja (povjerljivo");
		setContentAt(6, 4, "Niska (ograni�eno)");
		setContentAt(6, 5, "Srednja (povjerljivo");
		setContentAt(6, 6, "Srednja (povjerljivo");
		setContentAt(6, 7, "Srednja (povjerljivo");
		setContentAt(6, 8, "Srednja (povjerljivo");
		setContentAt(6, 9, "Srednja (povjerljivo");
		setContentAt(6, 10, "Visoka (tajno)");
		setContentAt(6, 11, "Visoka (tajno)");
		setContentAt(6, 12, "Niska (ograni�eno)");
		setContentAt(6, 13, "Visoka (tajno)");
		setContentAt(6, 14, "Vrlo visoka (vrlo tajno)");
		setContentAt(6, 15, "Visoka (tajno)");
		setContentAt(6, 16, "Srednja (povjerljivo)");
		setContentAt(6, 17, "Vrlo niska (javno)");

		//Seventh Column
		setContentAt(7, 0, "Cjelovitost");
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
		setContentAt(7, 13, "Vrlo visoka");
		setContentAt(7, 14, "Vrlo visoka");
		setContentAt(7, 15, "Vrlo visoka");
		setContentAt(7, 16, "Vrlo niska");
		setContentAt(7, 17, " Vrlo niska");

		//8 Column
		setContentAt(8, 0, "Raspolo�ivost");
		setContentAt(8, 1, "Va�na (do 8 sati)");
		setContentAt(8, 2, "Nije vrlo va�na (do 48 sati)");
		setContentAt(8, 3, "Va�na (do 8 sati)");
		setContentAt(8, 4, "Nije vrlo va�na (do 48 sati)");
		setContentAt(8, 5, "Va�na (do 8 sati)");
		setContentAt(8, 6, "Nije vrlo va�na (do 48 sati)");
		setContentAt(8, 7, "Vrlo va�na (do 1 sat)");
		setContentAt(8, 8, "Nije va�na (do 72 sata)");
		setContentAt(8, 9, "Nije va�na (do 72 sata)");
		setContentAt(8, 10, "Va�na (do 8 sati)");
		setContentAt(8, 11, "Va�na (do 8 sati)");
		setContentAt(8, 12, "Nije va�na (do 72 sata)");
		setContentAt(8, 13, "Vrlo va�na  ( do 1 sat)");
		setContentAt(8, 14, "Ekstremno va�na  (bez odgode)");
		setContentAt(8, 15, "Ekstremno va�na  (bez odgode)");
		setContentAt(8, 16, "Nije va�na (do 72 sata)");
		setContentAt(8, 17, "Nije va�na (do 72 sata)");

		// 9 Column
		setContentAt(9, 0, "Poslovni Utjecaj");
		setContentAt(9, 1, "Nije vrlo va�an");
		setContentAt(9, 2, "Va�an");
		setContentAt(9, 3, "Nije vrlo va�an");
		setContentAt(9, 4, "Nije vrlo va�an");
		setContentAt(9, 5, "Va�an");
		setContentAt(9, 6, "Va�an");
		setContentAt(9, 7, "Va�an");
		setContentAt(9, 8, "Nije vrlo va�an");
		setContentAt(9, 9, "Nije vrlo va�an");
		setContentAt(9, 10, "Va�an");
		setContentAt(9, 11, "Kriti�na");
		setContentAt(9, 12, "Nije va�an");
		setContentAt(9, 13, "Va�an");
		setContentAt(9, 14, "Kriti�na");
		setContentAt(9, 15, "Kriti�na");
		setContentAt(9, 16, "Nije va�an");
		setContentAt(9, 17, "Nije va�an");

//		//10 Column
//		setContentAt(10, 0, "Klasifikacija");
//		setContentAt(10, 1, "Vrlo osjetljivo");
//		setContentAt(10, 2, "Vrlo osjetljivo");
//		setContentAt(10, 3, "Vrlo osjetljivo");
//		setContentAt(10, 4, "Osjetljivo");
//		setContentAt(10, 5, "Vrlo osjetljivo");
//		setContentAt(10, 6, "Vrlo osjetljivo");
//		setContentAt(10, 7, "Vrlo osjetljivo");
//		setContentAt(10, 8, "Vrlo osjetljivo");
//		setContentAt(10, 9, "Vrlo osjetljivo");
//		setContentAt(10, 10, "Vrlo osjetljivo");
//		setContentAt(10, 11, "Vrlo osjetljivo");
//		setContentAt(10, 12, "Osjetljivo");
//		setContentAt(10, 13, "Vrlo osjetljivo");
//		setContentAt(10, 14, "Vrlo osjetljivo");
//		setContentAt(10, 15, "1150");
//		setContentAt(10, 16, "1150");
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

		if (content.get(5+"/"+row)=="1")
		{
			return m_1Renderer;
		}

		if (content.get(5+"/"+row)=="2")
				{
			return m_2Renderer;
		}

		if (content.get(5+"/"+row)=="3") {
			return m_3Renderer;
		}

		if (/*getContentAt(5, row) == "4"*/content.get(5+"/"+row)=="4") {
//			 for (int i=getFixedHeaderRowCount(); i<getRowCount(); i++)
//			        for (int j=getFixedHeaderColumnCount(); j<getColumnCount(); j++)
							return m_4Renderer;
		}

		if (content.get(5+"/"+row)=="5") {
			return m_5Renderer;
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
		return 17 + getFixedRowCount();
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
