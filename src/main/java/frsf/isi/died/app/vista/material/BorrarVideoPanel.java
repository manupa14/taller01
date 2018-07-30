package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.app.controller.BorrarVideoController;
import frsf.isi.died.app.controller.VideoController;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.Video;

public class BorrarVideoPanel extends JPanel{
	
	private JLabel lblTitulo;
	private JButton btnBorrar;
	private JComboBox comboBox;
	
	private BorrarVideoController controller;
	
	private MaterialCapacitacionDao materialDAO;
	
	public BorrarVideoPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		this.materialDAO = new MaterialCapacitacionDaoDefault();
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Seleccione video a borrar: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		comboBox = new JComboBox(materialDAO.listaVideos().toArray());
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(comboBox, gridConst);
		
		btnBorrar = new JButton("Borrar");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(btnBorrar, gridConst);
		
		btnBorrar.addActionListener(e -> {
			this.materialDAO.borrarVideo((Video) comboBox.getSelectedItem());
		});	
		
	}
	
	public void setController(BorrarVideoController controller) {
		this.controller = controller;
	}
	
	public BorrarVideoController getController() {
		return controller;
	}
}