package frsf.isi.died.tp.estructuras.contenido;

import java.util.ArrayList;

public class ArbolContenido {

	private String valor;
	private TipoNodo tipo;
	private ArrayList<ArbolContenido> hijos;
	
	public ArbolContenido(String valor, TipoNodo tipo) {
		this.valor = valor;
		this.tipo = tipo;
		this.hijos = new ArrayList<ArbolContenido>();
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public void setValor(String unValor) {
		this.valor = unValor;
	}
	
	public TipoNodo getTipo() {
		return this.tipo;
	}
	
	public void setTipoNodo(TipoNodo tipo) {
		this.tipo = tipo;
	}
	
	public ArrayList<ArbolContenido> getHijos() {
		return this.hijos;
	}
	
	public void agregarHijo(ArbolContenido hijo) {
		this.hijos.add(hijo);
	}
	
	public ArbolContenido getCapitulo(ArrayList<ArbolContenido> hijos) {
		String pito = "hola";
		
		
		return new ArbolContenido(pito, TipoNodo.CAPITULO);
	}
	
	public void agregarHijo(ArbolContenido hijo, TipoNodo padre) {
		
		switch(padre) {
			case TITULO:
				this.agregarHijo(hijo);
				break;
			case CAPITULO:
				for(ArbolContenido aux : this.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						aux.agregarHijo(hijo);
					}
				}
				break;
			case SECCION:
				for(ArbolContenido aux : this.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.SECCION)) {
								aux2.agregarHijo(hijo);
							}
						}
					}
				}
				break;
			case RESUMEN:
				for(ArbolContenido aux : this.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.RESUMEN)) {
						aux.agregarHijo(hijo);
					}
				}
				break;
			case TITULOMETADATO:
				for(ArbolContenido aux : this.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.METADATO)) {
						aux.agregarHijo(hijo);
					}
				}
				break;
			case CAPITULOMETADATO:
				for(ArbolContenido aux : this.getHijos()) {
					if(aux.getTipo().equals(TipoNodo.CAPITULO)) {
						for(ArbolContenido aux2 : aux.getHijos()) {
							if(aux2.getTipo().equals(TipoNodo.METADATO)) {
								aux2.agregarHijo(hijo);
							}
						}
					}
				}
				break;
			default:
				break;
		}
		
	}
	
}
