package hr.ante.isms.parts;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.login.LoginDialog;
import org.mihalis.opal.login.LoginDialogVerifier;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * This snippet demonstrates the Login Dialog widget
 *
 */
public class Login {
    @Inject private static MApplication app;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		//Locale.setDefault(Locale.ENGLISH);

		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Login dialog snippet");
		shell.setLayout(new GridLayout(2, false));

		final LoginDialogVerifier verifier = new LoginDialogVerifier() {

			@Override
			public void authenticate(final String login, final String password)
					throws Exception {
				if ("".equals(login)) {
					throw new Exception("Please enter a login.");
				}

				if ("".equals(password)) {
					throw new Exception("Please enter a password.");
				}

				if (!login.equalsIgnoreCase("strikoman")) {
					throw new Exception("Login unknown.");
				}

				if (!password.equalsIgnoreCase("strikoman")) {
					throw new Exception(
							"Authentication failed, please check your password.");
				}

			}
		};

		final LoginDialog dialog = new LoginDialog();
		dialog.setVerifier(verifier);

		final boolean result = dialog.open();
		if (result) {
			System.out.println("Login confirmed : " + dialog.getLogin());
		} else {
			System.out.println("User canceled !");
		}

		dialog.setAutorizedLogin("Ante");
		dialog.setLogin("Strikoman");

//		final MTrimmedWindow window = MBasicFactory.INSTANCE.createTrimmedWindow();
//
//
//		MPart part = MBasicFactory.INSTANCE.createPart();
//		part.setContributionURI("platform:/plugin/hr.ante.isms/hr.ante.isms.parts.Assets2");
//		part.setCloseable(true);
//		window.getChildren().add(part);
//
//		app.getChildren().add(window);
		SWTGraphicUtil.centerShell(shell);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

}
