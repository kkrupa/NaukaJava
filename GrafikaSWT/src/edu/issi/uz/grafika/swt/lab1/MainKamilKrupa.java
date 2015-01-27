package edu.uz.grafika.lab2;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.RGB;


public class MainKamilKrupa {
    Image img = null;
    ImageData imageData = null;
    ImageData imageDataY = null;
    ImageData imageDataCb = null;
    ImageData imageDataCr = null;
    ImageData imageDataNew = null;
    Canvas canvas = null;
    
	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainKamilKrupa window = new MainKamilKrupa();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1024, 768);
		shell.setText("Konwerter RGB do YCbCr (Kamil Krupa)");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmLoad = new MenuItem(menu, SWT.NONE);
		mntmLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Otworz plik graficzny");
				fd.setFilterPath("C:/");
				String[] filterExtensions = {"*.png;*.jpg;*.jpeg;*.gif;*.tif;*.tiff"}; 
				fd.setFilterExtensions( filterExtensions);
				String nazwaPliku = fd.open();
				try {
					// Tworzymy kilka klonow tego samego obrazka, zeby je pozniej nadpisac
					imageData = new ImageData(nazwaPliku);
					imageDataY = new ImageData(nazwaPliku);
					imageDataCb = new ImageData(nazwaPliku);
					imageDataCr = new ImageData(nazwaPliku);
					imageDataNew = new ImageData(nazwaPliku); 
					
					int y, cb, cr;
					RGB color;
					for (int i=1; i<imageData.width; i++){
				        for (int j=1; j<imageData.height; j++){
				            color = imageData.palette.getRGB(imageData.getPixel( i, j));
				            
				            // Ponizsze wzory zwracaja wartosci spoza zakresu 0-255
//				            y = (int) (0 + (0.299*color.red + 0.587*color.green + 0.114*color.blue));
//				            cb = (int) (128 + (-0.168736*color.red - 0.331264*color.green + 0.5*color.blue));
//				            cr = (int) (128 + (0.5*color.red - 0.418688*color.green - 0.081312*color.blue));
				            
				            // kazdy pixel RGB przeliczamy na wartosci YCbCr
				            y = (int) (16 + (65.738*color.red)/256 + (129.057*color.green)/256 + (25.064*color.blue)/256);
				            cb = (int) (128 - (37.945*color.red)/256 - (74.494*color.green)/256 + (112.439*color.blue)/256);
				            cr = (int) (128 + (112.439*color.red)/256 - (94.154*color.green)/256 - (18.285*color.blue)/256);
				            
				            // wypelniamy/rysujemy/tworzymy kopie kazdej obrazka dla kazdej skladowej YCbCr
				            imageDataY.setPixel(i, j, imageDataY.palette.getPixel(new RGB(y,y,y)));
				            imageDataCb.setPixel(i, j, imageDataCb.palette.getPixel(new RGB(y,cb,y)));
				            imageDataCr.setPixel(i, j, imageDataCr.palette.getPixel(new RGB(y,y,cr)));
				        }
				    }
					
					// zmniejszamy i polowe a nastepnie zwiekszamy obrazy skladowych CbCr
					imageDataCb.scaledTo(imageDataCb.width/2, imageDataCb.height/2);
					imageDataCr.scaledTo(imageDataCr.width/2, imageDataCr.height/2);
					imageDataCb.scaledTo(imageDataCb.width*2, imageDataCb.height*2);
					imageDataCr.scaledTo(imageDataCr.width*2, imageDataCr.height*2);
					
					int r, g, b;
					// bedziemy tworzyc nowa wersje obrazka bazujac na klonach obrazka Y, Cb i Cr
					for (int i=1; i<imageDataNew.width; i++){
				        for (int j=1; j<imageDataNew.height; j++){
				          
				        	// tworzymy zmienne pomocnicze dla czytelnosci
				            y = imageDataY.palette.getRGB(imageDataY.getPixel(i, j)).red;
				            cb = imageDataCb.palette.getRGB(imageDataCb.getPixel(i, j)).green;
				            cr = imageDataCr.palette.getRGB(imageDataCr.getPixel(i, j)).blue;
				            
				            // przeliczamy spowrotem z YCbCr na wartosci RGB
				            r = (int) (y + 1.402*(cr-128));
				            g = (int) (y - 0.34414*(cb-128)-0.71414*(cr-128));
				            b = (int) (y + 1.772*(cb-128));

				            // wzory ponizej potrafia zwrocic bledne wartosci (spoza zakresu 0-255)		            
//				            r = (int) ((298.082*y)/256 + (408.583*cr)/256 - 222.921);
//				            g = (int) ((298.082*y)/256 - (100.291*cb)/256 - (208.120*cr)/256 + 135.576);
//				            b = (int) ((298.082*y)/256 + (516.412*cb)/256 - 276.836);
				          	
				            // wpisujemy nowe wartosci RGB do obrazka
				            imageDataNew.setPixel(i, j, imageDataNew.palette.getPixel(new RGB(r,g,b)));
				        }
				    }   
					
					img = new Image(Display.getDefault(), imageDataNew);
					
					shell.setSize(shell.getClientArea().width, shell.getClientArea().height);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mntmLoad.setText("Load");
		
		MenuItem mntmWyswietl = new MenuItem(menu, SWT.CASCADE);
		mntmWyswietl.setText("Wyswietl");
		
		Menu menu_1 = new Menu(mntmWyswietl);
		mntmWyswietl.setMenu(menu_1);
		
		MenuItem mntmOryginal_1 = new MenuItem(menu_1, SWT.NONE);
		mntmOryginal_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				img = new Image(Display.getDefault(), imageData);
				canvas.redraw();
			}
		});
		mntmOryginal_1.setText("Oryginal");
		
		MenuItem mntmYcbcr = new MenuItem(menu_1, SWT.NONE);
		mntmYcbcr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				img = new Image(Display.getDefault(), imageDataNew);
				canvas.redraw();				
			}
		});
		mntmYcbcr.setText("YCbCr");
		
		MenuItem mntmY = new MenuItem(menu_1, SWT.NONE);
		mntmY.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				img = new Image(Display.getDefault(), imageDataY);
				canvas.redraw();
			}
		});
		mntmY.setText("Y");
		
		MenuItem mntmCb = new MenuItem(menu_1, SWT.NONE);
		mntmCb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				img = new Image(Display.getDefault(), imageDataCb);
				canvas.redraw();
			}
		});
		mntmCb.setText("Cb");
		
		MenuItem mntmCr = new MenuItem(menu_1, SWT.NONE);
		mntmCr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				img = new Image(Display.getDefault(), imageDataCr);
				canvas.redraw();
			}
		});
		mntmCr.setText("Cr");
		
		MenuItem mntmKoniec = new MenuItem(menu, SWT.NONE);
		mntmKoniec.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		mntmKoniec.setText("Koniec");
		
		final Point poczatek = new Point(0, 0);
		
		canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE | SWT.H_SCROLL | SWT.V_SCROLL);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {

				if (img != null)
					e.gc.drawImage(img, poczatek.x, poczatek.y);
				
			}
		});
		
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int hSelection = hBar.getSelection();
				int destX = - hSelection - poczatek.x;
				Rectangle obszar = img.getBounds();
				canvas.scroll(destX, 0, 0, 0, obszar.width, obszar.height, false);
				poczatek.x = - hSelection;
			}
		});
		
		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();
				int destY = - vSelection - poczatek.y;
				Rectangle obszar = img.getBounds();
				canvas.scroll(0, destY, 0, 0, obszar.width, obszar.height, false);
				poczatek.y = - vSelection;
			}
		});
		
		canvas.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				if (img != null) {
					Rectangle obszar_img = img.getBounds();
					Rectangle obszar_canvas = canvas.getClientArea();
					hBar.setMaximum(obszar_img.width);
					vBar.setMaximum(obszar_img.height);
					hBar.setThumb(Math.min(obszar_img.width, obszar_canvas.width));
					vBar.setThumb(Math.min(obszar_img.height, obszar_canvas.height));
					
					int hPage = obszar_img.width - obszar_canvas.width;
					int vPage = obszar_img.height - obszar_canvas.height;
					
					int hSelection = hBar.getSelection();
					int vSelection = vBar.getSelection();
					
					if (hSelection >= hPage) {
						if (hPage <= 0)
							hSelection = 0;
						poczatek.x = -hSelection;
					}
					if (vSelection >= vPage) {
						if (vPage <= 0)
							vSelection = 0;
						poczatek.y = -vSelection;
					}
					canvas.redraw();
				}
			}
		});
		
	}
}