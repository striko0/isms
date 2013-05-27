
package hr.ante.isms.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.mihalis.opal.titledSeparator.TitledSeparator;

public class buttons {


	@PostConstruct
	public void createComposite(Composite parent) {
		final TitledSeparator sep1 = new TitledSeparator(parent, SWT.NONE);
		sep1.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		sep1.setText("Customer Info");


	}

//	@Focus
//	public void setFocus() {
//
//	}
}
