package hr.ante.test.asktable.autocomplete.ccombo;

import hr.ante.test.asktable.autocomplete.AutocompleteContentProposalProvider;
import hr.ante.test.asktable.autocomplete.AutocompleteInputContentProposalProvider;

import org.eclipse.swt.custom.CCombo;

public class AutocompleteCComboInput extends AutocompleteCCombo{

	public AutocompleteCComboInput(CCombo m_Combo) {
		super(m_Combo);
	}

	protected AutocompleteContentProposalProvider getContentProposalProvider(String[] proposals) {
		return new AutocompleteInputContentProposalProvider(proposals);
	}

}
