package hr.ante.test.asktable.autocomplete.ccombo;

import hr.ante.test.asktable.autocomplete.AutocompleteWidget;

import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;

public abstract class AutocompleteCCombo extends AutocompleteWidget {

	private final class ProposalUpdateFocusListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			provider.setProposals(combo.getItems());
		}

		public void focusLost(FocusEvent e) {
			// do nothing
		}
	}

	protected CCombo combo = null;

	public AutocompleteCCombo(CCombo aCombo) {
		this.combo = aCombo;

		if (combo != null) {
			this.combo.addFocusListener(new ProposalUpdateFocusListener());

			provider = getContentProposalProvider(combo.getItems());
			adapter = new ContentProposalAdapter(combo, new ComboContentAdapter(), provider, getActivationKeystroke(), getAutoactivationChars());
			adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		}
	}

}
