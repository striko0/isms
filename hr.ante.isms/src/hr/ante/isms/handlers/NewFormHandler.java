package hr.ante.isms.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;

public class NewFormHandler {


	  @Execute
	  public void execute(MApplication application) {



		// Create the window
	    final MWindow window = MBasicFactory.INSTANCE.createWindow();
	   // window.getTags().add("temporaryObject");
	    ///window.setHeight(500);
	    //window.setWidth(600);

	    MPart part = MBasicFactory.INSTANCE.createPart();
	    part.setContributionURI( "platform:/plugin/hr.ante.isms/hr.ante.isms.parts.Assets1");
	    window.getChildren().add(part);
	    application.getChildren().add(window);


	    //application.getParent().

	    System.out.println("NEW FORM HANDLER TRIGGERRED");


//	    // Create the toolbar
//	    MTrimBar topTrim = MBasicFactory.INSTANCE.createTrimBar();
//	    topTrim.setSide(SideValue.TOP);
//	    window.getTrimBars().add(topTrim);
//	    MToolBar toolbar = MMenuFactory.INSTANCE.createToolBar();
//	    topTrim.getChildren().add(toolbar);
//	    MHandledToolItem sendItem = MMenuFactory.INSTANCE.createHandledToolItem();
//	    sendItem.setLabel("Send");
//	    sendItem.setIconURI("platform:/plugin/org.eclipse.e4.demo.mailapp.newmail/images/email_go.png")
//	;
//	    for( MCommand cmd : application.getCommands() ) {
//	      if( "org.eclipse.e4.demo.mailapp.command.sendmail".equals(cmd.getElementId()) ) {
//	        sendItem.setCommand(cmd);
//	      }
//	    }
//	    toolbar.getChildren().add(sendItem);
//	    // Create the mail editor


//	    shell.getParent().add(window);
	    //window.getContext().set(IMail.class, mailSession.createMail());
	  }
//	  @CanExecute
//	public boolean canExecute(@Optional IMailSession mailSession) {
//	    return mailSession != null;
//	  }
	}
