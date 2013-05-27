package hr.ante.test.asktable;

import hr.ante.test.asktable.editors.ASKTableCellEditorCurrency3;
import hr.ante.test.asktable.editors.ASKTableCellEditorDateTime2;
import hr.ante.test.asktable.editors.ASKTableCellEditorTime;
import hr.ante.test.renderers.ASFixedCellRenderer;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableDefaultModel;
import de.kupzog.ktable.editors.KTableCellEditorCombo;
import de.kupzog.ktable.editors.KTableCellEditorComboText;
import de.kupzog.ktable.editors.KTableCellEditorText;
import de.kupzog.ktable.renderers.TextCellRenderer;

class ASTableModel2 extends KTableDefaultModel{

	private int[] colWidths;

	private int rowHeight;
	
	   private KTableCellRenderer m_FixedRenderer = 
		        new ASFixedCellRenderer(ASFixedCellRenderer.STYLE_PUSH | 
		            ASFixedCellRenderer.INDICATION_SORT | 
		            ASFixedCellRenderer.INDICATION_FOCUS |
		            ASFixedCellRenderer.INDICATION_CLICKED |  TextCellRenderer.INDICATION_FOCUS_ROW);
	   
	    
	    private final TextCellRenderer m_textRenderer = 
	        new TextCellRenderer(TextCellRenderer.INDICATION_FOCUS_ROW);

	private HashMap content = new HashMap();

	/**
   * 
   */
	public ASTableModel2() {
		 // before initializing, you probably have to set some member values
        // to make all model getter methods work properly.
        initialize();
        
        // we don't want the default foreground color on text cells,
        // so we change it:
        
        m_textRenderer.setForeground(
                Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
		
		colWidths = new int[getColumnCount()];
		for (int i = 0; i < colWidths.length; i++) {
			colWidths[i] = 100;
		}

		rowHeight = 18;
		content = new HashMap();
	}

	// Inhalte

	public Object doGetContentAt(int col, int row) {
		// System.out.println("col "+col+" row "+row);
		String erg = (String) content.get(col + "/" + row);
		if (erg != null)
			return erg;
		return col + row;
	}

	
	/*
	 * overridden from superclass
	 */
		public KTableCellEditor doGetCellEditor(int col, int row) {
	    	if (col<getFixedColumnCount() || row<getFixedRowCount())
	    		return null;
	    	if (col == 1){
	    		ASKTableCellEditorDateTime2 dateTime = new ASKTableCellEditorDateTime2();
	    		
	    		return dateTime;
	    	}
	        if (col % 3 == 1){
//	            KTableCellEditorCombo e = new KTableCellEditorCombo();
//	            e.setItems(new String[] { "First text", "Second text",
//	                            "third text" });
	            return new ASKTableCellEditorCurrency3();
	            
	        }
	        else if (col % 3 == 2){
	               
	                return new ASKTableCellEditorTime();
	        }
	        else{
	            return new KTableCellEditorText();
	        }
	    
		}

	public void doSetContentAt(int col, int row, Object value) {
		content.put(col + "/" + row, value);
	}
	
	/** 
     * Implement also cell spans so that it can be demonstrated how
     * the sorting algorithm works in this case: 
     * @see de.kupzog.ktable.KTableDefaultModel#doBelongsToCell1(int, int)
     */
    public Point doBelongsToCell1(int col, int row) {
        if ((col==2 || col==3)&& !isFixedCell(col, row)) {
            int newRow = row;
            if ((row-getFixedRowCount())%2==1)
                newRow--;
            return new Point(2, newRow);
        }
        return new Point(col,row);
    }

	// Table size:
	public int doGetRowCount() {
		return 100 + getFixedRowCount();
	}

	public int getFixedHeaderRowCount() {
		return 1;
	}

	public int doGetColumnCount() {
		return 100 + getFixedColumnCount();
	}

	public int getFixedHeaderColumnCount() {
		return 0;
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

	public boolean isColumnResizable(int col) {
		return true;
	}

	public boolean isRowResizable(int row) {
		return true;
	}

	public int getRowHeightMinimum() {
		return 18;
	}

	// Rendering
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		if (isFixedCell(col, row))
            return m_FixedRenderer;
        
        return m_textRenderer;
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
	
	 public String doGetTooltipAt(int col, int row) {
	        return "Tooltip for cell: "+col+"/"+row;
	    }
}
