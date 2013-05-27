package hr.ante.test.asktable.autocomplete.combo;

import hr.ante.test.asktable.autocomplete.AutocompleteContentProposalProvider;
import hr.ante.test.asktable.autocomplete.AutocompleteInputContentProposalProvider;

import org.eclipse.swt.widgets.Combo;

public class AutocompleteComboInput extends AutocompleteCombo {

	public AutocompleteComboInput(Combo m_Combo) {
		super(m_Combo);
	}

	protected AutocompleteContentProposalProvider getContentProposalProvider(String[] proposals) {
		return new AutocompleteInputContentProposalProvider(proposals);
	}

}
