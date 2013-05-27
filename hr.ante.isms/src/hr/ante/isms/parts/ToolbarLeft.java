package hr.ante.isms.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;

public class ToolbarLeft {

        @Inject IStylingEngine engine;

        @PostConstruct
    	public void createComposite(final Composite parent) {
        	parent.setLayout(new FillLayout(SWT.HORIZONTAL));
        //Toolbar( )    {


        	final ToolBar bar = new ToolBar(parent,SWT.RIGHT | SWT.SHADOW_OUT | SWT.VERTICAL);
            //bar.setLocation(0,0);

            Image imovinaIcon=null;
            Image identPrijetnjiIcon=null;
            Image identRanjivostiIcon = null;
            Image utvrdVjerojatnostiIcon =null;
            Image analizaUcinkaIcon = null;
            // create images for toolbar buttons
			try {

	            imovinaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\imovinaIcon.png");
//	            identPrijetnjiIcon = new Image(d, "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
	            identPrijetnjiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
	            identRanjivostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identRanjivostiIcon.png");
	            utvrdVjerojatnostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\utvrdVjerojatnostiIcon.png");
	            analizaUcinkaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\analizaUcinkaIcon.png");

	        } catch (Exception e) {
	            System.out.println("Cannot load images");
	            System.out.println(e.getMessage());
	            System.exit(1);
	        }



            final ToolItem sep2 = new ToolItem(bar, SWT.SEPARATOR);


		// create and add the button for performing an open operation
		final ToolItem imovina_ = new ToolItem(bar, SWT.PUSH);
		imovina_.setImage(imovinaIcon);
		imovina_.setText("Imovina");
		imovina_.setToolTipText("Imovina");
		// table.setData("org.eclipse.e4.ui.css.id", "ASKTable");
		engine.setClassname(imovina_, "mainButtons");
		imovina_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");


            //create and add the button for performing a save operation
            final ToolItem identPrijetnji_ = new ToolItem(bar, SWT.PUSH);
            identPrijetnji_.setImage(identPrijetnjiIcon);
            identPrijetnji_.setText("Identifikacija prijetnji");
            identPrijetnji_.setToolTipText("Identifikacija prijetnji");
            engine.setClassname(identPrijetnji_, "mainButtons");
            identPrijetnji_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");

           //create and add the button for performing a cut operation
            final ToolItem identRanjivosti_ = new ToolItem(bar, SWT.PUSH);
            identRanjivosti_.setImage(identRanjivostiIcon);
            identRanjivosti_.setText("Identifikacija Ranjivosti");
            identRanjivosti_.setToolTipText("Identifikacija Ranjivosti");
            engine.setClassname(identRanjivosti_, "mainButtons");
            identRanjivosti_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");

            // create and add the button for performing a copy operation
            final ToolItem utvrdVjerojatnosti_ = new ToolItem(bar, SWT.PUSH);
            utvrdVjerojatnosti_.setImage(utvrdVjerojatnostiIcon);
            utvrdVjerojatnosti_.setText("Utvrðivanje Vrijednosti");
            utvrdVjerojatnosti_.setToolTipText("Utvrðivanje Vrijednosti");
            engine.setClassname(utvrdVjerojatnosti_, "mainButtons");
            utvrdVjerojatnosti_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");

            // create and add the button for performing a paste operation
            final ToolItem analizaUcinka_ = new ToolItem(bar, SWT.PUSH);
            analizaUcinka_.setImage(analizaUcinkaIcon);
            analizaUcinka_.setText("Analiza Uèinka");
            analizaUcinka_.setToolTipText("Analiza Uèinka");
            engine.setClassname(analizaUcinka_, "mainButtons");
            analizaUcinka_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");


    		imovina_.addSelectionListener(new SelectionListener() {
    			public void widgetSelected(SelectionEvent event) {
    				createNewShell(parent.getShell());
    			}

    			public void widgetDefaultSelected(SelectionEvent event) {
    			}
    		});

            identPrijetnji_.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent event) {
                    System.out.println("identPrijetnji_");
                }
                public void widgetDefaultSelected(SelectionEvent event)
                {
                }
          });
            identRanjivosti_.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent event) {
                    System.out.println("identRanjivosti");
                }
                public void widgetDefaultSelected(SelectionEvent event)
                {
                }
          });
            utvrdVjerojatnosti_.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent event) {
                    System.out.println("utvrdVjerojatnosti_");
                }
                public void widgetDefaultSelected(SelectionEvent event)
                {
                }
          });
            analizaUcinka_.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent event) {
                    System.out.println("analizaUcinka_");
                }
                public void widgetDefaultSelected(SelectionEvent event)
                {
                }
            });








            // create the menu
            //Menu m = new Menu(s,SWT.BAR);

            /*// create a File menu and add an Exit item
            final MenuItem file = new MenuItem(m, SWT.CASCADE);
            file.setText("&File");
            final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
            file.setMenu(filemenu);
            final MenuItem openMenuItem = new MenuItem(filemenu, SWT.PUSH);
            openMenuItem.setText("&Open\tCTRL+O");
            openMenuItem.setAccelerator(SWT.CTRL+'O');
            final MenuItem saveMenuItem = new MenuItem(filemenu, SWT.PUSH);
            saveMenuItem.setText("&Save\tCTRL+S");
            saveMenuItem.setAccelerator(SWT.CTRL+'S');
            final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
            final MenuItem exitMenuItem = new MenuItem(filemenu, SWT.PUSH);
            exitMenuItem.setText("&Exit");

            // create an Edit menu and add Cut, Copy, and Paste items
            final MenuItem edit = new MenuItem(m, SWT.CASCADE);
            edit.setText("&Edit");
            final Menu editmenu = new Menu(s, SWT.DROP_DOWN);
            edit.setMenu(editmenu);
            final MenuItem cutMenuItem = new MenuItem(editmenu, SWT.PUSH);
            cutMenuItem.setText("&Cut");
            final MenuItem copyMenuItem = new MenuItem(editmenu, SWT.PUSH);
            copyMenuItem.setText("Co&py");
            final MenuItem pasteMenuItem = new MenuItem(editmenu, SWT.PUSH);
            pasteMenuItem.setText("&Paste");

            //create a Window menu and add Child items
            final MenuItem window = new MenuItem(m, SWT.CASCADE);
            window.setText("&Window");
            final Menu windowmenu = new Menu(s, SWT.DROP_DOWN);
            window.setMenu(windowmenu);
            final MenuItem maxMenuItem = new MenuItem(windowmenu, SWT.PUSH);
            maxMenuItem.setText("Ma&ximize");
            final MenuItem minMenuItem = new MenuItem(windowmenu, SWT.PUSH);
            minMenuItem.setText("Mi&nimize");
            // create a Help menu and add an About item
            final MenuItem help = new MenuItem(m, SWT.CASCADE);
            help.setText("&Help");
            final Menu helpmenu = new Menu(s, SWT.DROP_DOWN);

            help.setMenu(helpmenu);
            final MenuItem abouMenutItem = new MenuItem(helpmenu, SWT.PUSH);
            aboutMenuItem.setText("&About");*/




           /* // add action listeners for the menu items
            openMenuItem.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println("Open");
                }
public void widgetDefaultSelected(SelectionEvent e) {
               }
               });
           saveMenuItem.addSelectionListener(new SelectionListener( ) {
                 public void widgetSelected(SelectionEvent e) {
                     System.out.println("Save");
                }
public void widgetDefaultSelected(SelectionEvent e) {
                }
                });
           exitMenuItem.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent e) {
                    System.exit(0);
                }
public void widgetDefaultSelected(SelectionEvent e) {
                }
                });
           cutMenuItem.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println("Cut");
                }
public void widgetDefaultSelected(SelectionEvent e) {
                }
                });
           copyMenuItem.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println("Copy");
                }
public void widgetDefaultSelected(SelectionEvent e) {
                }
                });
           pasteMenuItem.addSelectionListener(new SelectionListener( ) {
                public void widgetSelected(SelectionEvent e) {
                    System.out.println("Paste");
                }
public void widgetDefaultSelected(SelectionEvent e) {

}
           });
      maxMenuItem.addSelectionListener(new SelectionListener( ) {
           public void widgetSelected(SelectionEvent e) {
               Shell parent = (Shell)maxItem.getParent().getParent( );
               parent.setMaximized(true);
           }
public void widgetDefaultSelected(SelectionEvent e) {
           }
           });
      minMenuItem.addSelectionListener(new SelectionListener( ) {
           public void widgetSelected(SelectionEvent e) {
               Shell parent = (Shell)minItem.getParent().getParent( );
               parent.setMaximized(false);
           }
public void widgetDefaultSelected(SelectionEvent e) {
           }
           });
      aboutMenuItem.addSelectionListener(new SelectionListener( ) {
           public void widgetSelected(SelectionEvent e) {
               System.out.println("Help Invoked");
         }
public void widgetDefaultSelected(SelectionEvent e) {
           }
           });*/



//       s.setMenuBar(m);
//       s.open( );
//       while(!s.isDisposed( )){
//           if(!d.readAndDispatch( ))
//               d.sleep( );
//       }
//       d.dispose( );
   }
        private void createNewShell(Shell shell) {


//        	new SamplePart();
           Shell s = new Shell(shell);
           s.setSize(300,300);
//          // s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
//           s.setText("A Shell Toolbar Example");
		}
}


