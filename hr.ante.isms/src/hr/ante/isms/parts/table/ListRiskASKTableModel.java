package hr.ante.isms.parts.table;

import hr.ante.isms.connection.DatabaseConnection;
import hr.ante.isms.parts.DataFromServer;
import hr.ante.test.renderers.ASCurrencyTextCellRenderer;
import hr.ante.test.renderers.ASFixedCellRenderer;

import java.sql.SQLException;
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

public class ListRiskASKTableModel extends KTableSortedModel {

	private Random rand = new Random();
	private int columnNumber = -1;
	private int rowNumber = 0;
	private String m_assetid = "";
	public int m_Vrsta = 1;
	private HashMap content = new HashMap();
	public HashMap meta = new HashMap();
	private int[] colWidths;

	private int rowHeight;

	private KTableCellRenderer m_FixedFirstColumnRenderer = new ASFixedCellRenderer(
			ASFixedCellRenderer.STYLE_FLAT
					| ASFixedCellRenderer.INDICATION_FOCUS
					| ASFixedCellRenderer.INDICATION_CLICKED
					| ASFixedCellRenderer.INDICATION_FOCUS_ROW);

	private KTableCellRenderer m_FixedRenderer = new ASFixedCellRenderer(
			ASFixedCellRenderer.STYLE_PUSH
					| ASFixedCellRenderer.INDICATION_SORT
					| ASFixedCellRenderer.INDICATION_FOCUS
					| ASFixedCellRenderer.INDICATION_CLICKED
					| ASFixedCellRenderer.INDICATION_FOCUS_ROW);

	private KTableCellRenderer m_DefaultRenderer = new DefaultCellRenderer(0);

	private KTableCellRenderer m_FixedCheckableRenderer = new FixedCheckableCellRenderer(
			FixedCheckableCellRenderer.STYLE_PUSH
					| FixedCheckableCellRenderer.INDICATION_FOCUS
					| FixedCheckableCellRenderer.INDICATION_SORT);

	private KTableCellRenderer m_CheckableRenderer = new CheckableCellRenderer(
			CheckableCellRenderer.INDICATION_CLICKED
					| CheckableCellRenderer.INDICATION_FOCUS);

	public ASRiskTextCellRenderer m_NizakRenderer = new ASRiskTextCellRenderer(
			ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT
					| SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault()
					.getSystemColor(SWT.COLOR_DARK_GREEN));
	public ASRiskTextCellRenderer m_SrednjiRenderer = new ASRiskTextCellRenderer(
			ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT
					| SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault()
					.getSystemColor(SWT.COLOR_DARK_YELLOW));
	public ASRiskTextCellRenderer m_VisokRenderer = new ASRiskTextCellRenderer(
			ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT
					| SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault()
					.getSystemColor(SWT.COLOR_RED));
	public ASRiskTextCellRenderer m_VrloVisokRenderer = new ASRiskTextCellRenderer(
			ASRiskTextCellRenderer.INDICATION_FOCUS, SWTX.ALIGN_HORIZONTAL_LEFT
					| SWTX.ALIGN_VERTICAL_BOTTOM, Display.getDefault()
					.getSystemColor(SWT.COLOR_DARK_RED));

	/**
   *
   */
	public void readAllFromDB() {

		// identity = getContentFromDB("view_risk");

		if (m_Vrsta == 1)
			content = getContentFromDB("view_risk", "ListRiskASKTableModel", "");

		if (m_Vrsta == 2) {
			content = getContentFromDB("view_risk", "ThreatIdentification",
					m_assetid);
		}
		DataFromServer.listRiskASKTableModel = this;
		// columnNumber=2;

		if (content.get("@@brojac").toString() == null )
			rowNumber = 1;
		rowNumber = Integer.parseInt(content.get("@@brojac").toString());
		initialize();
	}

	public ListRiskASKTableModel(int columnNumbers, int vrsta, String assetid) {
		// before initializing, you probably have to set some member values
		// to make all model getter methods work properly.

		m_assetid = assetid;
		columnNumber = columnNumbers;
		m_Vrsta = vrsta;

		initialize();

		// statusLine.setMessage("hello");

		/**
		 * BROJ STUPACA!
		 *
		 */
		// columnNumber=7;
		// meta.put("0", "");
		for (int i = 1; i <= columnNumber; i++)
			meta.put("" + i + "", "Text");
		// meta.put("2", "Text");
		// meta.put("3", "Text");
		// meta.put("4", "Text");
		// meta.put("5", "Text");
		// meta.put("6", "Text");
		// meta.put("7", "Text");
		// meta.put("8", "Text");
		// meta.put("9", "Text");
		// meta.put("10", "Text");

		colWidths = new int[getColumnCount()];

		for (int i = 0; i < colWidths.length; i++) {

			colWidths[i] = 50;
		}

		readAllFromDB();

		// putContents();

		setColumnWidth(0, 30);

		if (m_Vrsta == 1) {
			setColumnWidth(1, 50);
			setColumnWidth(2, 200);
			setColumnWidth(3, 50);
			setColumnWidth(4, 100);
			setColumnWidth(5, 100);
			// setColumnWidth(2, 75);
		}

	}

	private void putContents() {

		// Zero Column
		setContentAt(0, 0, "#");
		for (int i = getFixedRowCount(); i < getRowCount(); i++)
			setContentAt(0, i, "" + i + "");
		// setContentAt(0, 2, "2");
		// setContentAt(0, 3, "3");
		// setContentAt(0, 4, "4");
		// setContentAt(0,5, "5");
		// setContentAt(0, 6, "6");
		// setContentAt(0, 7, "7");
		// setContentAt(0, 8, "8");
		// setContentAt(0, 9, "9");
		// setContentAt(0, 10, "10");
		// setContentAt(0, 11, "11");
		// setContentAt(0, 12, "12");

		// 1 Column
		setContentAt(1, 0, "Broj");
		setContentAt(1, 1, "44");
		setContentAt(1, 2, "125");
		setContentAt(1, 3, "126");
		setContentAt(1, 4, "127");
		setContentAt(1, 5, "131");
		setContentAt(1, 6, "51");
		setContentAt(1, 7, "136");
		setContentAt(1, 8, "152");
		setContentAt(1, 9, "4");
		setContentAt(1, 10, "20");
		// setContentAt(1, 11, "272");
		// setContentAt(1, 12, "1150");

		// 2 Column
		setContentAt(2, 0, "Naziv Rizika");
		setContentAt(2, 1, "Neadekvatan interni razvoj");
		setContentAt(2, 2, "Nepostojanje adekvatnog ugovora");
		setContentAt(2, 3,
				"Zastoj poslovnih procesa zbog loše dokumentiran og softvera");
		setContentAt(2, 4,
				"Nedokumentiranost Rijeènika podataka (Data Dictionary)");
		setContentAt(2, 5,
				"Nepostojanje mehanizama ispravnog adresiranja i prijenosa poruka");
		setContentAt(2, 6,
				"Neuèinkovita kontrola pristupa za nove aplikacije u Banci");
		setContentAt(2, 7, "Lokacija u podrucju podložnom poplavi");
		setContentAt(2, 8,
				"Nekorištenje alata za nadzor integriteta podataka i ispitivanje ranjivosti.");
		setContentAt(2, 9,
				"Curenje informacija sa stare raèunalne opreme Banke");
		setContentAt(2, 10, "Nepostojanje prièuvnog raè.centra");
		// setContentAt(2, 11, "Ugovor o isporuci informacijskog sustava ");
		// setContentAt(2, 12,
		// "Izjave o zaprimanju korisnièkih naziva i zaporki");

		// 3 Column
		setContentAt(3, 0, "Naziv Imovine");
		setContentAt(3, 1, "PORTAL programski kod");
		setContentAt(3, 2, "Ugovor o informatièkim uslugama - BANKSOFT");
		setContentAt(3, 3, "Ugovor o informatièkim uslugama - BANKSOFT");
		setContentAt(3, 4, "Banksoft rijeènik podataka (Data Dictionary)");
		setContentAt(3, 5, "Elektronièka pošta djelatnika");
		setContentAt(3, 6, "Kontrola pristupa");
		setContentAt(3, 7, "Prièuvne kopije");
		setContentAt(3, 8, "MREŽA (network)");
		setContentAt(3, 9, "STARA oprema - neodložena");
		setContentAt(3, 10, "SISTEM SALA - Data Centar");
		// setContentAt(3, 11, "1020 - Papirnati Dokument");
		// setContentAt(3, 12, "1020 - Papirnati Dokument");

		// 4 Column
		setContentAt(4, 0, "Vlasnik Imovine");
		setContentAt(4, 1, "12000");
		setContentAt(4, 2, "12000");
		setContentAt(4, 3, "12000");
		setContentAt(4, 4, "12000");
		setContentAt(4, 5, "12000");
		setContentAt(4, 6, "12000");
		setContentAt(4, 7, "12000");
		setContentAt(4, 8, "12000");
		setContentAt(4, 9, "12000");
		setContentAt(4, 10, "12000");
		// setContentAt(4, 11, "Direkcija informatike");
		// setContentAt(4, 12, "Direkcija informatike");

		// 5 Column
		setContentAt(5, 0, "Prijetnja");
		setContentAt(5, 1, "Potpuni zastoj poslovnih procesa");
		setContentAt(5, 2, "Kršenje regulatornih obveza");
		setContentAt(5, 3, "Potpuni zastoj poslovnih procesa");
		setContentAt(5, 4, "Gubitak integriteta nad podacima");
		setContentAt(5, 5, "Neadekvatna zaštita informacija");
		setContentAt(5, 6, "Ugrožena sukladnost sa normom, standardima itd.");
		setContentAt(5, 7, "Pad kvalitete medija");
		setContentAt(5, 8, "Ugrožavanje sigurnosti");
		setContentAt(5, 9, "Curenje informacija");
		setContentAt(5, 10, "Katastrofe (prirodne, ljudske)");
		// setContentAt(5, 11, "4");
		// setContentAt(5, 12, "2");

		// Sixth Column
		setContentAt(6, 0, "Ranjivost");
		setContentAt(6, 1, "Loše dokumentirani softver");
		setContentAt(6, 2, "Nepostojanje adekvatnog ugovora");
		setContentAt(6, 3, "Loše dokumentiran softver");
		setContentAt(6, 4, "Loše dokumentiran softver");
		setContentAt(6, 5,
				"Nepostojanje mehanizama ispravnog adresiranja i prijenosa poruka");
		setContentAt(6, 6,
				"Akti (procedure, pravilnici) neprikladni, zastarjeli, teško razumljivi");
		setContentAt(6, 7, "Lokacija u podrucju podložnom poplavi");
		setContentAt(6, 8, "Nedostatak mehanizama nadzora");
		setContentAt(6, 9, "Nepostojanje pravila/procedura");
		setContentAt(6, 10, "Nepostojanje off-site resursa");
		// setContentAt(6, 11, "Visoka (tajno)");
		// setContentAt(6, 12, "Niska (ogranièeno)");

		// Seventh Column
		setContentAt(7, 0, "RIZIK");
		setContentAt(7, 1, "Visoki");
		setContentAt(7, 2, "Srednji");
		setContentAt(7, 3, "Visoki");
		setContentAt(7, 4, "Srednji");
		setContentAt(7, 5, "Nizak");
		setContentAt(7, 6, "Nizak");
		setContentAt(7, 7, "Srednji");
		setContentAt(7, 8, "Srednji");
		setContentAt(7, 9, "Nizak");
		setContentAt(7, 10, "Nizak");
		// setContentAt(7, 11, "Vrlo visoka");
		// setContentAt(7, 12, "Niska");

	}

	public HashMap<String, String> getContentFromDB(String tableName,
			String vrsta, String assetId) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getContentForTable(tableName, vrsta, assetId);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new HashMap<String, String>();

	}

	public HashMap getMeta() {
		return meta;
	}

	public void switchColumn(int destinationColumn, int initColumn) {
		// TODO Auto-generated method stub
		// content.put(col+"/"+row, val);
		HashMap<Integer, Object> contentFromModel = new HashMap<Integer, Object>();
		for (int row = 0; row < getRowCount(); row++) {
			if (content.get(initColumn + "/" + row) instanceof Boolean)
				contentFromModel.put(row,
						(Boolean) content.get(initColumn + "/" + row));
			contentFromModel.put(row, content.get(initColumn + "/" + row));
			if (content.get(destinationColumn + "/" + row) instanceof Boolean)
				content.put(initColumn + "/" + row,
						(Boolean) content.get(destinationColumn + "/" + row));
			content.put(initColumn + "/" + row,
					content.get(destinationColumn + "/" + row));
			if (contentFromModel.get(row) instanceof Boolean)
				content.put(destinationColumn + "/" + row,
						contentFromModel.get(row));
			content.put(destinationColumn + "/" + row,
					contentFromModel.get(row));
		}

		// // for (int col=table_.getModel().getFixedHeaderColumnCount();
		// col<table_.getModel().getColumnCount(); col++)
		// for (int row=table_.getModel().getFixedHeaderRowCount();
		// row<table_.getModel().getRowCount(); row++){
		// rowsX.put(x + "/" + row+"",table_.getModel().getContentAt(x, row));
		// rowsX2.put(x + "/" + row+"",table_.getModel().getContentAt(x2, row));

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
		if (isFixedCell(col, row)) {
			if (col == 0)
				return m_FixedFirstColumnRenderer;
			return m_FixedRenderer;
		}
		if (content.get(7 + "/" + row) == null)
			return m_DefaultRenderer;

		if (content.get(7 + "/" + row).equals("Nizak")) {
			return m_NizakRenderer;
		}

		if (content.get(7 + "/" + row).equals("Srednji")) {
			return m_SrednjiRenderer;
		}

		if (content.get(7 + "/" + row).equals("Visok")) {
			return m_VisokRenderer;
		}

		if (content.get(7 + "/" + row).equals("Vrlo visok")) {
			return m_VrloVisokRenderer;
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
		return "Tooltip for cell: " + col + "/" + row;
	}
}
