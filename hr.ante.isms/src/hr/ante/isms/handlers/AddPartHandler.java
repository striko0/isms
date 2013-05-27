package hr.ante.isms.handlers;


import org.eclipse.e4.core.di.annotations.Execute;
//import org.eclipse.e4.ui.model.[CONTINUE...]
    //.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;

public class AddPartHandler {
  @Execute
  public void execute(MWindow window) {
    MPart part = MBasicFactory.INSTANCE.createPart();
    part.setElementId("mynewid");
    part.setLabel("A new Part");
    part.setContributionURI("bundleclass://com.example." +
        "e4.rcp.todo/com.example.e4.rcp.todo.parts.TodoOverviewPart");
    System.out.println("NEW FORM HANDLER TRIGGERRED");

    window.getChildren().add(part);
  }
}