package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class LibroPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
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
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JButton btnBorrar;
	private LibroTableModel tableModel;

	private LibroController controller;
	
	public LibroPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		this.add(txtTitulo, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx=4;
		this.add(lblTema, gridConst);
		
		txtTema = new JTextField();
		txtTema.setColumns(20);
		gridConst.gridx=6;
		this.add(txtTema, gridConst);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Double precio = Double.valueOf(txtPrecioCompra.getText());
				Integer paginas = Integer.valueOf(txtPaginas.getText());
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaPub = formato.parse(txtFechaPub.getText());
				
				Relevancia relevancia = Relevancia.valueOf(txtRelevancia.getText());
				String tema = String.valueOf(txtTema.getText());
				
				controller.agregarLibro(txtTitulo.getText(), costo, precio, paginas, calificacion, fechaPub, relevancia, tema);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtPrecioCompra.setText("");
				txtPaginas.setText("");
				txtCalificacion.setText("");
				txtFechaPub.setText("");
				txtRelevancia.setText("");
				txtTema.setText("");
				JOptionPane.showMessageDialog(this, "Se agrego el libro correctamente");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=12;
		this.add(btnAgregar, gridConst);
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(11);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblPrecioCompra= new JLabel("Precio Compra: ");
		gridConst.gridx=2;
		this.add(lblPrecioCompra, gridConst);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(11);
		gridConst.gridx=3;
		this.add(txtPrecioCompra, gridConst);
		
		lblPaginas= new JLabel("Paginas: ");		
		gridConst.gridx=4;
		this.add(lblPaginas, gridConst);
		
		txtPaginas = new JTextField();
		txtPaginas.setColumns(11);
		gridConst.gridx=5;
		this.add(txtPaginas, gridConst);
		
		lblCalificacion= new JLabel("Calificacion: ");
		gridConst.gridx=6;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(11);
		gridConst.gridx=7;
		this.add(txtCalificacion, gridConst);
		
		lblFechaPub= new JLabel("Fecha Publicacion (dd/mm/yyyy): ");
		gridConst.gridx=8;
		this.add(lblFechaPub, gridConst);
		
		txtFechaPub= new JTextField();
		txtFechaPub.setColumns(11);
		gridConst.gridx=9;
		this.add(txtFechaPub, gridConst);
		
		lblRelevancia= new JLabel("Relevancia: ");
		gridConst.gridx=10;
		this.add(lblRelevancia, gridConst);
		
		txtRelevancia= new JTextField();
		txtRelevancia.setColumns(11);
		gridConst.gridx=11;
		this.add(txtRelevancia, gridConst);
		
		btnCancelar= new JButton("Cancelar");
		gridConst.gridx=12;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);		
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=13;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public LibroController getController() {
		return controller;
	}

	public void setController(LibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.tableModel.setLibros(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}