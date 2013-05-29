package hr.ante.isms.parts.table;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellSelectionAdapter;
import de.kupzog.ktable.KTableSortComparator;
import de.kupzog.ktable.KTableSortedModel;


/**
 * This class provides the code that makes the table sort when the user
 * clicks on the table header.
 *
 */
public class ASKTableSortOnClick extends KTableCellSelectionAdapter {

	KTable m_Table;
	KTableSortComparator m_SortComparator;

	public ASKTableSortOnClick(KTable table,int col, int row, KTableSortComparator comparator, int direction, int button) {

		if (table.getModel() instanceof KTableSortedModel) {
			KTableSortedModel model = (KTableSortedModel) table.getModel();
			if (row<model.getFixedHeaderRowCount() &&	col>=model.getFixedHeaderColumnCount()) {
					if (button == 1) {
						// implement the sorting when clicking on the header.
						if (model.getSortColumn() == col) {

							if (model.getSortState() == KTableSortComparator.SORT_UP) {
								direction = KTableSortComparator.SORT_DOWN;
							}
							if (model.getSortState() == KTableSortComparator.SORT_DOWN) {
								direction = KTableSortComparator.SORT_NONE;
							}else if (model.getSortState() == KTableSortComparator.SORT_NONE) {
								direction = KTableSortComparator.SORT_UP;
							}
						}
					}


				// update the comparator properly:
				comparator.setColumnToCompare(col);
				comparator.setSortDirection(direction);

				// perform the sorting
				model.sort(comparator);


				// needed to make the resorting visible!
				table.redraw();
			}
			}
		}

}

