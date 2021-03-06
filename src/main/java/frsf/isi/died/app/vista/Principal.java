package frsf.isi.died.app.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frsf.isi.died.app.controller.TiposAcciones;
import frsf.isi.died.app.controller.MenuController;

public class Principal {
	public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          createAndShowGUI();
	        }
	    });
	}

	 private static void createAndShowGUI() {
		 JFrame f = new JFrame();
		 MenuController controller = new MenuController(f);
				    
	        JMenuBar menuBar;
	        JMenu menu;
	        JMenuItem menuItem;

	        menuBar = new JMenuBar();

	        menu = new JMenu("Inicio");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Nuevo Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_LIBROS));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Actualizar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ACTUALIZAR_LIBRO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Buscar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSCAR_LIBRO));
	        menu.add(menuItem);

	        menuItem = new JMenuItem("Borrar Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BORRAR_LIBRO));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Nuevo Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_VIDEOS));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Actualizar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ACTUALIZAR_VIDEO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Buscar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BUSCAR_VIDEO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Borrar Video");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BORRAR_VIDEO));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Ver Wishlist");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.WISHLIST));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Ver Contenido");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.CONTENIDO));
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Salir");
	        menuItem.addActionListener(e->System.exit(99));
	        menu.add(menuItem);

	        menuBar.add(menu);
	        
	        menu = new JMenu("Opciones");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Asignar Relaciones/Buscar Caminos");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_GRAFO));
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Consultar Page Ranks");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.PAGE_RANK));
	        menu.add(menuItem);
	        menuBar.add(menu);
	        
	        f.setJMenuBar(menuBar);
	        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	        f.pack();
	        f.setVisible(true);
	    }

}
