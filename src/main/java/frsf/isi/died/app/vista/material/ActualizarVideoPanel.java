package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.ActualizarVideoController;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ActualizarVideoPanel extends JPanel{
	
	private JLabel lblSeleccionar;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblDuracion;
	private JLabel lblCalificacion;
	private JLabel lblFechaPub;
	private JLabel lblRelevancia;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtDuracion;
	private JTextField txtCalificacion;
	private JTextField txtFechaPub;
	private JTextField txtRelevancia;
	private JButton btnSeleccionar;
	private JComboBox comboBox;
	
	private ActualizarVideoController controller;
	
	private MaterialCapacitacionDao materialDAO;
	
	public ActualizarVideoPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		this.materialDAO = new MaterialCapacitacionDaoDefault();
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblSeleccionar = new JLabel("Seleccione video: ");
		gridConst.gridx=10;
		gridConst.gridy=0;
		this.add(lblSeleccionar, gridConst);
		
		comboBox = new JComboBox(materialDAO.listaVideos().toArray());
		gridConst.gridx=11;
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(comboBox, gridConst);
		
		btnSeleccionar = new JButton("Editar");
		btnSeleccionar.addActionListener(e -> {
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Integer duracion = Integer.valueOf(txtDuracion.getText());
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());
				
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaPub = formato.parse(txtFechaPub.getText());
				
				Relevancia relevancia = Relevancia.valueOf(txtRelevancia.getText());
			
				this.materialDAO.borrarVideo((Video) comboBox.getSelectedItem());
				controller.agregarVideo(txtTitulo.getText(), costo, duracion, calificacion, fechaPub, relevancia);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtDuracion.setText("");
				txtCalificacion.setText("");
				txtFechaPub.setText("");
				txtRelevancia.setText("");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		gridConst.gridx=12;
		this.add(btnSeleccionar, gridConst);
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		//gridConst.gridy=1;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(27);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		this.add(txtTitulo, gridConst);
		
		lblCosto = new JLabel("Costo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblDuracion = new JLabel("Duracion: ");
		gridConst.gridx=2;
		this.add(lblDuracion, gridConst);
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(5);
		gridConst.gridx=3;
		this.add(txtDuracion, gridConst);
		
		lblCalificacion= new JLabel("Calificacion: ");
		gridConst.gridx=4;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(5);
		gridConst.gridx=5;
		this.add(txtCalificacion, gridConst);
		
		lblFechaPub= new JLabel("Fecha: ");
		gridConst.gridx=6;
		this.add(lblFechaPub, gridConst);
		
		txtFechaPub= new JTextField();
		txtFechaPub.setColumns(5);
		gridConst.gridx=7;
		this.add(txtFechaPub, gridConst);
		
		lblRelevancia= new JLabel("Relevancia: ");
		gridConst.gridx=8;
		this.add(lblRelevancia, gridConst);
		
		txtRelevancia= new JTextField();
		txtRelevancia.setColumns(5);
		gridConst.gridx=9;
		this.add(txtRelevancia, gridConst);
	}
	
	public void setController(ActualizarVideoController controller) {
		this.controller = controller;
	}
	
	public ActualizarVideoController getController() {
		return this.controller;
	}
	
}