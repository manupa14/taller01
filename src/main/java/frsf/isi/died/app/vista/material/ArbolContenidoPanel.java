package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.ArbolContenidoController;
import frsf.isi.died.tp.estructuras.contenido.ArbolContenido;
import frsf.isi.died.tp.estructuras.contenido.TipoNodo;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class ArbolContenidoPanel extends JPanel{
	
	private JButton btnSeleccionar;
	private JButton btnBuscar;
	private JLabel lblSeleccionar;
	private JTextField txtSeleccionar;
	private JComboBox comboBox;
	
	private ArbolContenidoController controller;
	
	public ArbolContenidoPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		lblSeleccionar = new JLabel("Seleccione Material de Capacitacion: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblSeleccionar, gridConst);
		
		comboBox = new JComboBox(controller.listaMateriales().toArray());
		gridConst.gridx=1;
		this.add(comboBox, gridConst);
		
		btnSeleccionar = new JButton("Agregar Contenido");
		btnSeleccionar.addActionListener(e -> {
			TipoNodo[] opc = {TipoNodo.METADATO,TipoNodo.AUTOR,TipoNodo.SECCION,
					TipoNodo.PARRAFO,TipoNodo.CAPITULO,TipoNodo.EDITORIAL,
					TipoNodo.RESUMEN,TipoNodo.PALABRA_CLAVE,TipoNodo.SITIO,
					TipoNodo.EJERCICIO,TipoNodo.FECHA_PUBLICACION};
			
			TipoNodo opcionSeleccionada = (TipoNodo) JOptionPane.showInputDialog(this, "Seleccione tipo", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE, null, opc, opc[0]);
			
			agregarContenido(opcionSeleccionada,((MaterialCapacitacion) comboBox.getSelectedItem()).getContenido());
		});
		gridConst.gridx=2;
		this.add(btnSeleccionar, gridConst);
		
		btnBuscar = new JButton("Buscar Contenido");
		btnBuscar.addActionListener(e -> {
			
		});
		gridConst.gridx=3;
		this.add(btnBuscar, gridConst);
		
	}
	
	public void agregarContenido(TipoNodo tema, ArbolContenido arbolContenido) {
		String contenido;
		ArbolContenido hijo;
		Boolean existe; 
		Boolean existeIntermedio; 
		
		TipoNodo nodoPadre;
		//TipoNodo[] opc2 = {TipoNodo.AUTOR, TipoNodo.EDITORIAL, TipoNodo.PALABRA_CLAVE, TipoNodo.FECHA_PUBLICACION};
		//TipoNodo[] opc3 = {TipoNodo.PARRAFO};
		TipoNodo[] opc4 = {TipoNodo.SECCION, TipoNodo.RESUMEN};
		//TipoNodo[] opc5 = {TipoNodo.PALABRA_CLAVE, TipoNodo.SITIO, TipoNodo.EJERCICIO};
		TipoNodo[] opc6 = {TipoNodo.TITULO, TipoNodo.CAPITULO};
		
		switch(tema) {
			case METADATO:
				tema = (TipoNodo) JOptionPane.showInputDialog(this, "Seleccione a donde corresponde el METADATO", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE, null, opc6, opc6[0]);
				switch(tema) {
				case TITULO:
					existe = false;
					for(ArbolContenido aux : arbolContenido.getHijos()) {
						if(aux.getTipo().equals(TipoNodo.METADATO)) {
							JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo METADATO");
							existe = true;
						}
					}
					if(existe == false) {
						contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
						tema = TipoNodo.METADATO;
						hijo = new ArbolContenido(contenido, tema);
						nodoPadre = TipoNodo.TITULO;
						arbolContenido.agregarHijo(hijo, nodoPadre);
					}
					break;
				case CAPITULO:
					existe = false;
					existeIntermedio = false;
					for(ArbolContenido aux : arbolContenido.getHijos()) {
						if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
							existeIntermedio = true;
							for(ArbolContenido aux2 : aux.getHijos()) {
								if(aux2.getTipo().equals(TipoNodo.METADATO)) {
									JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo METADATO");
									existe = true;
								}
							}
							if(existe == false) {
								contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
								tema = TipoNodo.METADATO;
								hijo = new ArbolContenido(contenido, tema);
								nodoPadre = TipoNodo.CAPITULO;
								arbolContenido.agregarHijo(hijo, nodoPadre);
							}
						}else {
							if(existeIntermedio) JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
						}
					}
					break;
				default:
					break;
				}
				break;
			case RESUMEN:
				existe = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.RESUMEN)) {
						JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo RESUMEN");
						existe = true;
					}
				}
				if (existe == false) {
					contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
					tema = TipoNodo.RESUMEN;
					hijo = new ArbolContenido(contenido, tema);
					nodoPadre = TipoNodo.TITULO;
					arbolContenido.agregarHijo(hijo, nodoPadre);
				}
				break;
			case CAPITULO:
				existe = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo CAPITULO");
						existe = true;
					}
				}
				if (existe == false) {
					contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
					tema = TipoNodo.CAPITULO;
					hijo = new ArbolContenido(contenido, tema);
					nodoPadre = TipoNodo.TITULO;
					arbolContenido.agregarHijo(hijo, nodoPadre);
				}
				break;
			case PARRAFO:
				tema = (TipoNodo) JOptionPane.showInputDialog(this, "Seleccione a donde corresponde el PARRAFO", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE, null, opc4, opc4[0]);
				switch(tema) {
				case SECCION:
					existe = false;
					existeIntermedio = false;
					for(ArbolContenido aux : arbolContenido.getHijos()) {
						if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
							existeIntermedio = true;
							for(ArbolContenido aux2 : aux.getHijos()) {
								if(aux2.getTipo().equals(TipoNodo.SECCION)) {
									existe = true;
									contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
									tema = TipoNodo.PARRAFO;
									hijo = new ArbolContenido(contenido, tema);
									nodoPadre = TipoNodo.SECCION;
									arbolContenido.agregarHijo(hijo, nodoPadre);
								}
							}
						} 
					}
					if(existe == false) {
						JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo SECCION");
					}
					if(existeIntermedio == false) {
						JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
					}
					break;
				case RESUMEN:
					existe = false;
					for(ArbolContenido aux : arbolContenido.getHijos()) {
						if(aux.getTipo().equals(TipoNodo.RESUMEN)) {
							existe = true;
							contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
							tema = TipoNodo.PARRAFO;
							hijo = new ArbolContenido(contenido, tema);
							nodoPadre = TipoNodo.RESUMEN;
							arbolContenido.agregarHijo(hijo, nodoPadre);
						}
					}
					if(existe == false) {
						JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo RESUMEN");
					}
					break;
				default:
					break;
				}
				break;
			case SECCION:
				existe = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						existe = true;
					} 
				}
				if(existe == true) {
					contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
					tema = TipoNodo.SECCION;
					hijo = new ArbolContenido(contenido, tema);
					nodoPadre = TipoNodo.CAPITULO;
					arbolContenido.agregarHijo(hijo, nodoPadre);
				} else {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
				}
				break;
			case AUTOR:
				existe = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.METADATO)) {
						existe = true;
					}
				}
				if(existe == true) {
					contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
					tema = TipoNodo.AUTOR;
					hijo = new ArbolContenido(contenido, tema);
					nodoPadre = TipoNodo.TITULOMETADATO;
					arbolContenido.agregarHijo(hijo, nodoPadre);
				} else {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
				}
				break;
			case FECHA_PUBLICACION:
				existe = false;
				existeIntermedio = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.METADATO)) {
						existeIntermedio = true;
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.FECHA_PUBLICACION)) {
								JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo FECHA_PUBLICACION");
								existe = true;
							}
						}
					}
				}
				if(existeIntermedio == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
				} else {
					if(existe == false) {
						contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
						tema = TipoNodo.FECHA_PUBLICACION;
						hijo = new ArbolContenido(contenido, tema);
						nodoPadre = TipoNodo.TITULOMETADATO;
						arbolContenido.agregarHijo(hijo, nodoPadre);
					}
				}
				break;
			case EDITORIAL:
				existe = false;
				existeIntermedio = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.METADATO)) {
						existeIntermedio = true;
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.EDITORIAL)) {
								JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo EDITORIAL");
								existe = true;
							}
						}
						if(existe == false) {
							contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
							tema = TipoNodo.EDITORIAL;
							hijo = new ArbolContenido(contenido, tema);
							nodoPadre = TipoNodo.TITULOMETADATO;
							arbolContenido.agregarHijo(hijo, nodoPadre);
						}
					}
				}
				if(existeIntermedio == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
				}
				break;
			case SITIO:
				existe = false;
				existeIntermedio = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						existe = true;
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.METADATO)) {
								existeIntermedio = true;
								contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
								tema = TipoNodo.SITIO;
								hijo = new ArbolContenido(contenido, tema);
								nodoPadre = TipoNodo.CAPITULOMETADATO;
								arbolContenido.agregarHijo(hijo, nodoPadre);
							}
						}
					}
				}
				if(existeIntermedio == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
				}
				if(existe == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
				}
				break;
			case EJERCICIO:
				existe = false;
				existeIntermedio = false;
				for(ArbolContenido aux : arbolContenido.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						existe = true;
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.METADATO)) {
								existeIntermedio = true;
								contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
								tema = TipoNodo.EJERCICIO;
								hijo = new ArbolContenido(contenido, tema);
								nodoPadre = TipoNodo.CAPITULOMETADATO;
								arbolContenido.agregarHijo(hijo, nodoPadre);
							}
						}
					}
				}
				if(existeIntermedio == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
				}
				if(existe == false) {
					JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
				}
				break;
			case PALABRA_CLAVE:
				tema = (TipoNodo) JOptionPane.showInputDialog(this, "Seleccione a que tipo de METADATO corresponde", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE, null, opc6, opc6[0]);
				switch(tema) {
					case TITULO:
						existe = false;
						existeIntermedio = false;
						for(ArbolContenido aux : arbolContenido.getHijos()) {
							if(aux.getTipo().equals(TipoNodo.METADATO)) {
								existeIntermedio = true;
								for(ArbolContenido aux2 : aux.getHijos()) {
									if(aux2.getTipo().equals(TipoNodo.PALABRA_CLAVE)) {
										JOptionPane.showMessageDialog(this, "ERROR. Ya existe un nodo de tipo PALABRA_CLAVE");
										existe = true;
									}
								}
							}
						}
						if(existeIntermedio == false) {
							JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
						} else {
							if(existe == false) {
								contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
								tema = TipoNodo.PALABRA_CLAVE;
								hijo = new ArbolContenido(contenido, tema);
								nodoPadre = TipoNodo.TITULOMETADATO;
								arbolContenido.agregarHijo(hijo, nodoPadre);
							}
						}
						break;
					case CAPITULO:
						existe = false;
						existeIntermedio = false;
						for(ArbolContenido aux : arbolContenido.getHijos()) {
							if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
								existeIntermedio = true;
								for(ArbolContenido aux2 : aux.getHijos()) {
									if(aux2.getTipo().equals(TipoNodo.METADATO)) {
										existe = true;
										contenido = JOptionPane.showInputDialog(this, "Ingrese contenido", "AGREGAR CONTENIDO", JOptionPane.QUESTION_MESSAGE);
										tema = TipoNodo.PALABRA_CLAVE;
										hijo = new ArbolContenido(contenido, tema);
										nodoPadre = TipoNodo.CAPITULOMETADATO;
										arbolContenido.agregarHijo(hijo, nodoPadre);
									}
								}
							}
						}
						if(existe == false) {
							JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo METADATO");
						}
						if(existeIntermedio == false) {
							JOptionPane.showMessageDialog(this, "ERROR. No existe un nodo de tipo CAPITULO");
						}
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}
	
	public void setController(ArbolContenidoController controllerContenido) {
		this.controller = controllerContenido;
	}
	
	public ArbolContenidoController getController() {
		return this.controller;
	}
	
}
