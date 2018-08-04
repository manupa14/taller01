package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.ActualizarLibroController;
import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ActualizarLibroPanel extends JPanel{
	
	private JLabel lblSeleccionar;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblPrecioCompra;
	private JLabel lblPaginas;
	private JLabel lblCalificacion;
	private JLabel lblFechaPub;
	private JLabel lblRelevancia;
	private JLabel lblTema;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtPrecioCompra;
	private JTextField txtPaginas;
	private JTextField txtCalificacion;
	private JTextField txtFechaPub;
	private JTextField txtRelevancia;
	private JTextField txtTema;
	private JButton btnSeleccionar;
	private JComboBox comboBox;
	
	private ActualizarLibroController controller;
	
	private MaterialCapacitacionDao materialDAO;
	
	public ActualizarLibroPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		this.materialDAO = new MaterialCapacitacionDaoDefault();
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblSeleccionar = new JLabel("Seleccione libro: ");
		gridConst.gridx=11;
		gridConst.gridy=0;
		this.add(lblSeleccionar, gridConst);
		
		comboBox = new JComboBox(materialDAO.listaLibros().toArray());
		gridConst.gridx=12;
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(comboBox, gridConst);
		
		btnSeleccionar = new JButton("Editar");
		btnSeleccionar.addActionListener(e -> {
			try {
			Double costo = Double.valueOf(txtCosto.getText());
			Double precio = Double.valueOf(txtPrecioCompra.getText());
			Integer paginas = Integer.valueOf(txtPaginas.getText());
			Integer calificacion = Integer.valueOf(txtCalificacion.getText());

			Date fechaPub = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
				fechaPub = formato.parse(txtFechaPub.getText());
			
			
			Relevancia relevancia = Relevancia.valueOf(txtRelevancia.getText());
			String tema = String.valueOf(txtTema.getText());
			
			this.materialDAO.borrarLibro((Libro) comboBox.getSelectedItem());
			controller.agregarLibro(txtTitulo.getText(), costo, precio, paginas, calificacion, fechaPub, relevancia, tema);
			txtTitulo.setText("");
			txtCosto.setText("");
			txtPrecioCompra.setText("");
			txtPaginas.setText("");
			txtCalificacion.setText("");
			txtFechaPub.setText("");
			txtRelevancia.setText("");
			txtTema.setText("");
			JOptionPane.showMessageDialog(this, "Se actualizo el libro correctamente, reiniciar para completar los cambios");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		gridConst.gridx=13;
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
		gridConst.gridx=6;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=7;
		this.add(txtCosto, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx=8;
		this.add(lblTema, gridConst);
		
		txtTema = new JTextField();
		txtTema.setColumns(5);
		gridConst.gridx=9;
		this.add(txtTema, gridConst);
		
		lblPrecioCompra = new JLabel("Precio: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(lblPrecioCompra, gridConst);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(5);
		gridConst.gridx=1;
		this.add(txtPrecioCompra, gridConst);
		
		lblPaginas= new JLabel("Paginas: ");		
		gridConst.gridx=2;
		this.add(lblPaginas, gridConst);
		
		txtPaginas = new JTextField();
		txtPaginas.setColumns(5);
		gridConst.gridx=3;
		this.add(txtPaginas, gridConst);
		
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
	
	public void setController(ActualizarLibroController controller) {
		this.controller = controller;
	}
	
	public ActualizarLibroController getController() {
		return this.controller;
	}
	
}
