package hr.ante.isms.parts;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IStylingEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.mihalis.opal.preferenceWindow.PWGroup;
import org.mihalis.opal.preferenceWindow.PWRow;
import org.mihalis.opal.preferenceWindow.PWTab;
import org.mihalis.opal.preferenceWindow.PreferenceWindow1;
import org.mihalis.opal.preferenceWindow.enabler.EnabledIfEquals;
import org.mihalis.opal.preferenceWindow.enabler.EnabledIfTrue;
import org.mihalis.opal.preferenceWindow.widgets.PWButton;
import org.mihalis.opal.preferenceWindow.widgets.PWCheckbox;
import org.mihalis.opal.preferenceWindow.widgets.PWColorChooser;
import org.mihalis.opal.preferenceWindow.widgets.PWCombo;
import org.mihalis.opal.preferenceWindow.widgets.PWFontChooser;
import org.mihalis.opal.preferenceWindow.widgets.PWLabel;
import org.mihalis.opal.preferenceWindow.widgets.PWRadio;
import org.mihalis.opal.preferenceWindow.widgets.PWScale;
import org.mihalis.opal.preferenceWindow.widgets.PWSeparator;
import org.mihalis.opal.preferenceWindow.widgets.PWSpinner;
import org.mihalis.opal.preferenceWindow.widgets.PWStringText;
import org.mihalis.opal.preferenceWindow.widgets.PWTextarea;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

public class ToolbarDown {

        @Inject IStylingEngine engine;
        @Inject private MApplication app;

        @Inject
    	private	IEclipseContext context;
        final Map<String, Object> data = fillData();

        static Image imovinaIcon=null;
        static Image identPrijetnjiIcon=null;
        static Image identRanjivostiIcon = null;
        static Image utvrdVjerojatnostiIcon =null;
        static Image analizaUcinkaIcon = null;
        // create images for toolbar buttons


        @PostConstruct
    	public void createComposite(final Composite parent) {
        	parent.setLayout(new GridLayout(1, false));

        	 Image imovinaIcon=null;
             Image identPrijetnjiIcon=null;
             Image identRanjivostiIcon = null;
             Image utvrdVjerojatnostiIcon =null;
             Image analizaUcinkaIcon = null;
             // create images for toolbar buttons
 			try {

 	            imovinaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\imovinaIcon.png");
// 	            identPrijetnjiIcon = new Image(d, "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
 	            identPrijetnjiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
 	            identRanjivostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identRanjivostiIcon.png");
 	            utvrdVjerojatnostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\utvrdVjerojatnostiIcon.png");
 	            analizaUcinkaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\analizaUcinkaIcon.png");

 	        } catch (Exception e) {
 	            System.out.println("Cannot load images");
 	            System.out.println(e.getMessage());
 	            System.exit(1);
 	        }

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		// Toolbar( ) {

		final ToolBar bar = new ToolBar(composite, SWT.RIGHT | SWT.SHADOW_OUT);
		bar.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));
		// create and add the button for performing an open operation
		final ToolItem imovina_ = new ToolItem(bar, SWT.PUSH);
		imovina_.setImage(imovinaIcon);
		imovina_.setText("Imovina");
		imovina_.setToolTipText("Imovina");
		// table.setData("org.eclipse.e4.ui.css.id", "ASKTable");
		engine.setClassname(imovina_, "mainButtons");
		imovina_.setData(" org.eclipse.e4.ui.css.CssClassName", "mainButtons");

		// create and add the button for performing a save operation
		final ToolItem identPrijetnji_ = new ToolItem(bar, SWT.PUSH);
		identPrijetnji_.setImage(identPrijetnjiIcon);
		identPrijetnji_.setText("Identifikacija prijetnji");
		identPrijetnji_.setToolTipText("Identifikacija prijetnji");
		engine.setClassname(identPrijetnji_, "mainButtons");
		identPrijetnji_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a cut operation
		final ToolItem identRanjivosti_ = new ToolItem(bar, SWT.PUSH);
		identRanjivosti_.setImage(identRanjivostiIcon);
		identRanjivosti_.setText("Identifikacija Ranjivosti");
		identRanjivosti_.setToolTipText("Identifikacija Ranjivosti");
		engine.setClassname(identRanjivosti_, "mainButtons");
		identRanjivosti_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a copy operation
		final ToolItem utvrdVjerojatnosti_ = new ToolItem(bar, SWT.PUSH);
		utvrdVjerojatnosti_.setImage(utvrdVjerojatnostiIcon);
		utvrdVjerojatnosti_.setText("Utvrðivanje Vjerojatnosti");
		utvrdVjerojatnosti_.setToolTipText("Utvrðivanje Vrijednosti");
		engine.setClassname(utvrdVjerojatnosti_, "mainButtons");
		utvrdVjerojatnosti_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		// create and add the button for performing a paste operation
		final ToolItem analizaUcinka_ = new ToolItem(bar, SWT.PUSH);
		analizaUcinka_.setImage(analizaUcinkaIcon);
		analizaUcinka_.setText("Analiza Uèinka");
		analizaUcinka_.setToolTipText("Analiza Uèinka");
		engine.setClassname(analizaUcinka_, "mainButtons");
		analizaUcinka_.setData(" org.eclipse.e4.ui.css.CssClassName",
				"mainButtons");

		imovina_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// Create the window
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("platform:/plugin/hr.ante.isms/hr.ante.isms.parts.Assets2");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});

		identPrijetnji_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {

				// Create the window

				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Threats3");
				part.setCloseable(true);

				window.getChildren().add(part);

				app.getChildren().add(window);

				System.out.println("identPrijetnji_");
			}

			public void widgetDefaultSelected(SelectionEvent event) {

			}
		});
		identRanjivosti_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				final MWindow window = MBasicFactory.INSTANCE.createWindow();

				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Vulnerability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				System.out.println("identRanjivosti");
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
		utvrdVjerojatnosti_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// System.out.println("utvrdVjerojatnosti_");
				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.Probability");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
		analizaUcinka_.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				// System.out.println("analizaUcinka_");

				final MWindow window = MBasicFactory.INSTANCE.createWindow();
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setContributionURI("bundleclass://hr.ante.isms/hr.ante.isms.parts.ImpactAnalysis");
				part.setCloseable(true);
				window.getChildren().add(part);

				app.getChildren().add(window);
				// System.out.println("identRanjivosti");
			}

			public void widgetDefaultSelected(SelectionEvent event) {
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

        	final PreferenceWindow1 window = PreferenceWindow1.create(shell, data);



        	try {

                imovinaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\imovinaIcon.png");
//                identPrijetnjiIcon = new Image(d, "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
                identPrijetnjiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identPrijetnjiIcon.png");
                identRanjivostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\identRanjivostiIcon.png");
                utvrdVjerojatnostiIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\utvrdVjerojatnostiIcon.png");
                analizaUcinkaIcon = new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\analizaUcinkaIcon.png");

            } catch (Exception e) {
                System.out.println("Cannot load images");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        	createImovinaTab(window);
			createIdentPrijetnjiTab(window);
			createIdentRanjivostiTab(window);
			createUtvrdVjerojatnostiTab(window);
			createAnalizaUcinka(window);

			window.setSelectedTab(0);

			window.open();
//        	Display d = new Display();
//
//
//        	Shell s = new Shell(d);
//           s.setSize(300,300);
////          // s.setImage(new Image(d, "c:\\icons\\JavaCup.ico"));
////           s.setText("A Shell Toolbar Example");
		}

        private static Map<String, Object> fillData() {
    		final Map<String, Object> data = new HashMap<String, Object>();
    		data.put("text", "A string");
    		data.put("int", new Integer(42));
    		data.put("float", new Float(3.14));
    		data.put("url", "http://www.google.fr/");
    		data.put("password", "password");
    		data.put("directory", "");
    		data.put("file", "");
    		data.put("textarea", "long long\nlong long\nlong long\ntext...");
    		data.put("comboReadOnly", "Value 1");
    		data.put("combo", "Other Value");

    		data.put("cb1", new Boolean(true));
    		// cb2 is not initialized
    		data.put("slider", new Integer(40));
    		data.put("spinner", new Integer(30));
    		data.put("color", new RGB(120, 15, 30));
    		// font is not initialized

    		data.put("radio", "Radio button 3");
    		data.put("cb3", new Boolean(true));

    		// cb4 to cb14 are not initialised

    		data.put("cacheSizeUnit", "Megabytes");
    		data.put("openMode", "Double click");

    		return data;
    	}

        protected static void createImovinaTab(final PreferenceWindow1 window) {
    		final PWTab imovinaTab = window.addTab(/*new Image(Display.getCurrent(), "C:\\IT\\astrikom\\workspace4\\hr.ante.isms\\src\\icons\\imovinaIcon.png")*/imovinaIcon, "Imovina");



    		imovinaTab.add(new PWLabel("<b>Unos nove imovine</b>"));




//    		final PWGroup groupImovinaOsnovno = new PWGroup("");
//    		groupImovinaOsnovno.add(new PWStringText("Naziv:", "text").setAlignment(GridData.FILL));
//    		groupImovinaOsnovno.add(new PWCombo("Kategorija:", "kategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3").setWidth(100).setAlignment(GridData.BEGINNING));
//			groupImovinaOsnovno.add(new PWCombo("Podkategorija imovine:", "podkategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3").setWidth(150));
//			groupImovinaOsnovno.add(new PWCombo("Nositelj (org.jed):", "podkategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3"));
//			groupImovinaOsnovno.add(new PWCombo("Poslovni Proces:", "poslovniProcesCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3"));
//    		groupImovinaOsnovno.add(new PWTextarea("Objašnjenje (ostalo):", "textarea"));


    		imovinaTab.add(new PWStringText("Naziv:", "text").setAlignment(GridData.BEGINNING).setWidth(200));
    		imovinaTab.add(new PWCombo(window, "Kategorija:", "kategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3").setWidth(100).setAlignment(GridData.BEGINNING));
			imovinaTab.add(new PWCombo(window,"Podkategorija imovine:", "podkategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3").setWidth(150));
			imovinaTab.add(new PWCombo(window,"Nositelj (org.jed):", "podkategorijaCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3"));
			imovinaTab.add(new PWCombo(window,"Poslovni Proces:", "poslovniProcesCombo",false, "Kategorija 1", "Kategorija 2", "Kategorija 3"));
//			imovinaTab.add(new PWSeparator());

    		final PWGroup groupVaznostImovine = new PWGroup("Važnost Imovine");
    		groupVaznostImovine.add(new PWRow().
    				add(new PWCombo(window,"Povjerljivost:", "comboPovjerljivost", false, "Value 1", "Value 2", "Value 3").setAlignment(GridData.FILL)).
    				add(new PWButton("po", new SelectionAdapter() {
    				}).setWidth(20)));

    		groupVaznostImovine.add(new PWRow().
    				add(new PWCombo(window,"Cjelovitost:", "comboCjelovitost", false, "Value 1", "Value 2", "Value 3")).
    				add(new PWButton("cj", new SelectionAdapter() {
    				}).setWidth(20)));


    		groupVaznostImovine.add(new PWRow().
    				add(new PWCombo(window,"Raspoloživost", "comboRaspolozivost", false, "Value 1", "Value 2", "Value 3")).
    				add(new PWButton("ra", new SelectionAdapter() {
    				}).setWidth(20)));

    		groupVaznostImovine.add(new PWRow().
    				add(new PWCombo(window,"Ostalo:", "comboOstalo", false, "Value 1", "Value 2", "Value 3")).
    				add(new PWButton("os", new SelectionAdapter() {
    				}).setWidth(20)));
    		groupVaznostImovine.add(new PWTextarea("Objašnjenje (ostalo):", "textarea"));

    		imovinaTab.add(groupVaznostImovine);



//    		imovinaTab.add(new PWRow().//
//    				add(new PWCombo("Cache size", "cacheSize", true, "128", "256", "512", "1024")).//
//    	    				add(new PWCombo(null, "cacheSizeUnit",false, "Bytes", "Kilobytes", "Megabytes")));
//    		imovinaTab.add(new PWFloatText("Float :", "float"));
//    		imovinaTab.add(new PWURLText("URL :", "url"));
//    		imovinaTab.add(new PWPasswordText("Password :", "password"));
//    		imovinaTab.add(new PWDirectoryChooser("Directory :", "directory"));
//    		imovinaTab.add(new PWFileChooser("File :", "file"));
//    		imovinaTab.add(new PWSeparator());
    		imovinaTab.add(new PWTextarea("Opis :", "textarea").setGrabExcessSpace(true).setAlignment(GridData.BEGINNING));
//

//    		final GridData buttonPrevious = new GridData(GridData.END, GridData.END, true, false);
////
//    		imovinaTab.add(new PWRow().//
//
//
//    				add(new PWButton("< Povratak", new SelectionAdapter() {
//
//    			/**
//    			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//    			 */
//    			@Override
//    			public void widgetSelected(final SelectionEvent e) {
//    				Dialog.inform("Hi", "You pressed the first button");
//    			}
//
//    		}).setAlignment(GridData.END)).//
//
//    		add(new PWButton("Nastavak >", new SelectionAdapter() {
//
//    			/**
//    			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//    			 */
//    			@Override
//    			public void widgetSelected(final SelectionEvent e) {
//    				window.setSelectedTab(1);
//    				Dialog.inform("Hi", window.getValueFor("text").toString());
//
//    			}
//
//    		}).setAlignment(GridData.END)).//
//
//
//    			add(new PWButton("Gotovo", new SelectionAdapter() {
//
//    			/**
//    			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//    			 */
//    			@Override
//    			public void widgetSelected(final SelectionEvent e) {
//    				Dialog.inform("Hi", "You pressed the third button");
//    			}
//
//    		}).setAlignment(GridData.END)).//
//
//    		add(new PWButton("Izlaz", new SelectionAdapter() {
//
//    			/**
//    			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//    			 */
//    			@Override
//    			public void widgetSelected(final SelectionEvent e) {
//    				Dialog.inform("Hi", "You pressed the fourth button");
//    			}
//
//    		}).setAlignment(GridData.END)));
    	}

        protected static void createIdentPrijetnjiTab(final PreferenceWindow1 window) {
    		final PWTab identPrijetnjiTab = window.addTab(/*new Image(Display.getCurrent(), PreferenceWindow1Snippet.class.getClassLoader().getResourceAsStream("org/mihalis/opal/PreferenceWindow1/images/info.png"))*/identPrijetnjiIcon, "Identifikacija Prijetnji");

    		identPrijetnjiTab.add(new PWLabel("Checkboxes, Slider,Spinner, Color chooser, Font chooser"));
    		identPrijetnjiTab.add(new PWCheckbox("Checkbox 1", "cb1"));
    		identPrijetnjiTab.add(new PWCheckbox("Checkbox 2", "cb2"));

    		identPrijetnjiTab.add(new PWSeparator());

    		identPrijetnjiTab.add(new PWScale("Slider : ", "slider", 0, 100, 10));
    		identPrijetnjiTab.add(new PWSpinner("Spinner :", "spinner", 0, 100));

    		identPrijetnjiTab.add(new PWSeparator());

    		identPrijetnjiTab.add(new PWColorChooser("Color :", "color"));
    		identPrijetnjiTab.add(new PWFontChooser("Font :", "font"));

    	}

    	protected static void createIdentRanjivostiTab(final PreferenceWindow1 window) {
    		final PWTab identRanjivostiTab = window.addTab(/*new Image(Display.getCurrent(), PreferenceWindow1Snippet.class.getClassLoader().getResourceAsStream("org/mihalis/opal/PreferenceWindow1/images/openterm.png"))*/identRanjivostiIcon, "Identifikacija Ranjivosti");

    		identRanjivostiTab.add(new PWLabel("Group, radio, indentation and group of buttons in a row"));

    		final PWGroup group = new PWGroup("Group of buttons");
    		group.add(new PWRadio("Radio buttons:", "radio", "Radio button 1", "Radio button 2", "Radio button 3"));
    		identRanjivostiTab.add(group);

    		identRanjivostiTab.add(new PWCheckbox("Checkbox 3 (indented)", "cb3").setIndent(30).setWidth(200));

    		identRanjivostiTab.add(new PWRow().//
    				add(new PWButton("First button", new SelectionAdapter() {
    				})).//
    				add(new PWButton("Second button", new SelectionAdapter() {
    				})).//
    				add(new PWButton("Third button", new SelectionAdapter() {
    				})));

    	}

    	protected static void createUtvrdVjerojatnostiTab(final PreferenceWindow1 window) {
    		final PWTab utvrdVjerojatnostiTab = window.addTab(/*new Image(Display.getCurrent(), PreferenceWindow1Snippet.class.getClassLoader().getResourceAsStream("org/mihalis/opal/PreferenceWindow1/images/printer.png"))*/utvrdVjerojatnostiIcon, "Utvrðivanje Vjerojatnosti");

    		utvrdVjerojatnostiTab.add(new PWLabel("Play <i>with</i> <b>checkboxes</b>"));

    		final PWGroup group = new PWGroup(false);
    		group.add(new PWRow().add(new PWCheckbox("First choice", "cb4")).add(new PWCheckbox("Second choice", "cb5")));
    		group.add(new PWRow().add(new PWCheckbox("Third choice", "cb6")).add(new PWCheckbox("Fourth choice", "cb7")));
    		group.add(new PWRow().add(new PWCheckbox("Fifth choice", "cb8")).add(new PWCheckbox("Sixth choice", "cb9")));
    		group.add(new PWRow().add(new PWCheckbox("Seventh choice", "cb10")).add(new PWCheckbox("Eighth choice", "cb11")));
    		utvrdVjerojatnostiTab.add(group);

    		utvrdVjerojatnostiTab.add(new PWRow().//
    				add(new PWCheckbox("Automatically check for new versions", "cb12").setWidth(300)).//
    				add(new PWButton("Check for updates...", new SelectionAdapter() {
    				}).setWidth(250).setAlignment(GridData.END)));

    		utvrdVjerojatnostiTab.add(new PWSeparator());

    		final PWGroup group2 = new PWGroup(false);
    		group2.add(new PWRow().add(new PWLabel("Aligned checkbox")).add(new PWCheckbox("Bla bla bla 1", "cb13")));
    		group2.add(new PWRow().add(new PWLabel("")).add(new PWCheckbox("Bla bla bla 2", "cb14")));
    		utvrdVjerojatnostiTab.add(group2);
    	}

    	protected static void createAnalizaUcinka(final PreferenceWindow1 window) {
    		final PWTab analizaUcinka = window.addTab(/*new Image(Display.getCurrent(), PreferenceWindow1Snippet.class.getClassLoader().getResourceAsStream("org/mihalis/opal/PreferenceWindow1/images/system.png"))*/analizaUcinkaIcon, "Analiza Uèinka");

    		analizaUcinka.add(new PWLabel("Rows..."));

    				analizaUcinka.add(new PWRow().add(new PWCombo(window,"Cache size", "cacheSize", true, "128", "256", "512", "1024")).//
    				add(new PWCombo(window,null, "cacheSizeUnit",false, "Bytes", "Kilobytes", "Megabytes")));

    		analizaUcinka.add(new PWRow().//
    				add(new PWCombo(window,"Display:", "display",false, "10", "20", "30", "40", "50")).//
    				add(new PWLabel("per page")));

    		analizaUcinka.add(new PWSeparator());

    		analizaUcinka.add(new PWLabel("Enabled/disabled..."));

    		analizaUcinka.add(new PWCheckbox("Show information", "show").setWidth(150));
    		analizaUcinka.add(new PWGroup("Open Mode").setEnabler(new EnabledIfTrue("show")).//
    				add(new PWRadio(null, "openMode", "Double click", "Single click")).//
    				add(new PWCheckbox("Select on hover", "selectonhover").setIndent(10).setWidth(200).setEnabler(new EnabledIfEquals("openMode", "Single click"))).//
    				add(new PWCheckbox("Open when using arrow keys", "openarrow").setIndent(10).setWidth(200).setEnabler(new EnabledIfEquals("openMode", "Single click"))));
    	}


}


