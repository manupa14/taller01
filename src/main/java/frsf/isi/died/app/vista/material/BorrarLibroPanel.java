package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.app.controller.BorrarLibroController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.Libro;

public class BorrarLibroPanel extends JPanel{
	
	private JLabel lblTitulo;
	private JButton btnBorrar;
	private JComboBox comboBox;
	
	private BorrarLibroController controller;
	
	private MaterialCapacitacionDao materialDAO;
	
	public BorrarLibroPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		this.materialDAO = new MaterialCapacitacionDaoDefault();
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Seleccione libro a borrar: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		comboBox = new JComboBox(materialDAO.listaLibros().toArray());
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(comboBox, gridConst);
		
		btnBorrar = new JButton("Borrar");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(btnBorrar, gridConst);
		
		btnBorrar.addActionListener(e -> {
			this.materialDAO.borrarLibro((Libro) comboBox.getSelectedItem());
		});	
		
	}
	
	public void setController(BorrarLibroController controller) {
		this.controller = controller;
	}
	
	public BorrarLibroController getController() {
		return controller;
	}
}
