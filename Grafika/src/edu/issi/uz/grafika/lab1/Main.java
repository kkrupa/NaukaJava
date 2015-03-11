package edu.issi.uz.grafika.lab1;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class Main {
	Image img = null;
	Canvas canvas = null;

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmLoad = new MenuItem(menu, SWT.NONE);
		mntmLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Otw�rz plik graficzny");
				fd.setFilterPath("C:/");
				String[] filterExtensions = { "*.png;*.jpg;*.jpeg;*.gif;*.tif;*.tiff" };
				fd.setFilterExtensions(filterExtensions);
				String nazwaPliku = fd.open();
				try {
					img = new Image(e.display, nazwaPliku);
					shell.setSize(shell.getClientArea().width,
							shell.getClientArea().height);
				} catch (Exception e2) {

				}
			}
		});
		mntmLoad.setText("Load");

		MenuItem mntmKoniec = new MenuItem(menu, SWT.NONE);
		mntmKoniec.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		mntmKoniec.setText("Koniec");

		final Point poczatek = new Point(0, 0);

		canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE | SWT.H_SCROLL
				| SWT.V_SCROLL);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle rect = ((Canvas) e.widget).getBounds();
				if (img != null)
					e.gc.drawImage(img, poczatek.x, poczatek.y);

			}
		});

		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int hSelection = hBar.getSelection();
				int destX = -hSelection - poczatek.x;
				Rectangle obszar = img.getBounds();
				canvas.scroll(destX, 0, 0, 0, obszar.width, obszar.height,
						false);
				poczatek.x = -hSelection;
			}
		});

		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();
				int destY = -vSelection - poczatek.y;
				Rectangle obszar = img.getBounds();
				canvas.scroll(0, destY, 0, 0, obszar.width, obszar.height,
						false);
				poczatek.y = -vSelection;
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
					hBar.setThumb(Math.min(obszar_img.width,
							obszar_canvas.width));
					vBar.setThumb(Math.min(obszar_img.height,
							obszar_canvas.height));

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