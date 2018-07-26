package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import frsf.isi.died.app.controller.VideoController;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class VideoPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
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
	private JButton btnAgregar;
	private JButton btnCancelar;

	private VideoTableModel tableModel;

	private VideoController controller;
	
	public VideoPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new VideoTableModel();
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
		

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Integer duracion = Integer.valueOf(txtDuracion.getText());
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());
				
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaPub = formato.parse(txtFechaPub.getText());
				
				Relevancia relevancia = Relevancia.valueOf(txtRelevancia.getText());
				
				controller.agregarVideo(txtTitulo.getText(), costo, duracion, calificacion, fechaPub, relevancia);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtDuracion.setText("");
				txtCalificacion.setText("");
				txtFechaPub.setText("");
				txtRelevancia.setText("");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=10;
		this.add(btnAgregar, gridConst);
		
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(9);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblDuracion= new JLabel("Duracion: ");		
		gridConst.gridx=2;
		this.add(lblDuracion, gridConst);
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(9);
		gridConst.gridx=3;
		this.add(txtDuracion, gridConst);

		lblCalificacion= new JLabel("Calificacion: ");
		gridConst.gridx=4;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(9);
		gridConst.gridx=5;
		this.add(txtCalificacion, gridConst);
		
		lblFechaPub= new JLabel("Fecha Publicacion: ");
		gridConst.gridx=6;
		this.add(lblFechaPub, gridConst);
		
		txtFechaPub= new JTextField();
		txtFechaPub.setColumns(9);
		gridConst.gridx=7;
		this.add(txtFechaPub, gridConst);
		
		lblRelevancia= new JLabel("Relevancia: ");
		gridConst.gridx=8;
		this.add(lblRelevancia, gridConst);
		
		txtRelevancia= new JTextField();
		txtRelevancia.setColumns(9);
		gridConst.gridx=9;
		this.add(txtRelevancia, gridConst);

		btnCancelar= new JButton("Cancelar");
		gridConst.gridx=10;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=11;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public VideoController getController() {
		return controller;
	}

	public void setController(VideoController controller) {
		this.controller = controller;
	}
	
	public void setListaVideos(List<Video> videosLista,boolean actualizar) {
		this.tableModel.setVideos(videosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}