package hr.ante.test.asktable.autocomplete.text;

import org.eclipse.swt.widgets.Text;

import hr.ante.test.asktable.autocomplete.AutocompleteContentProposalProvider;
import hr.ante.test.asktable.autocomplete.AutocompleteSelectorContentProposalProvider;

public class AutocompleteTextSelector extends AutocompleteText {

	public AutocompleteTextSelector(Text text, String[] selectionItems) {
		super(text, selectionItems);
	}

	protected AutocompleteContentProposalProvider getContentProposalProvider(String[] proposals) {
		return new AutocompleteSelectorContentProposalProvider(proposals, this.text);
	}

}
