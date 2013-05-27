package hr.ante.test.asktable.autocomplete.text;

import hr.ante.test.asktable.autocomplete.AutocompleteContentProposalProvider;
import hr.ante.test.asktable.autocomplete.AutocompleteInputContentProposalProvider;

import org.eclipse.swt.widgets.Text;

public class AutocompleteTextInput extends AutocompleteText {

	public AutocompleteTextInput(Text text, String[] selectionItems) {
		super(text, selectionItems);
	}

	protected AutocompleteContentProposalProvider getContentProposalProvider(String[] proposals) {
		return new AutocompleteInputContentProposalProvider(proposals);
	}

}
