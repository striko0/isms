package hr.ante.test.asktable.autocomplete.ccombo;

import java.util.Arrays;
import java.util.List;

import hr.ante.test.asktable.autocomplete.AutocompleteContentProposalProvider;
import hr.ante.test.asktable.autocomplete.AutocompleteSelectorContentProposalProvider;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.custom.CCombo;

public class AutocompleteCComboSelector extends AutocompleteCCombo {

	private final class UpdateProposalListFocusListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			// do nothing
		}

		public void focusLost(FocusEvent e) {
			CCombo theCombo = (CCombo) e.getSource();
			List items = Arrays.asList(theCombo.getItems());
			if (! items.contains(theCombo.getText())) {
				theCombo.select(0);
			}

		}
	}

	public AutocompleteCComboSelector(CCombo aCombo) {
		super(aCombo);
		aCombo.addFocusListener(new UpdateProposalListFocusListener());
	}

	protected AutocompleteContentProposalProvider getContentProposalProvider(String[] proposals) {
		return new AutocompleteSelectorContentProposalProvider(proposals, this.combo);
	}

}
