/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.util.Ordenable;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * Integrantes:
 * Roces, Mariano
 * Pagliaruzza, Manuel
 * Galv�n, Juan Pablo
 * 
 * URL: https://github.com/manupa14/taller01.git
 * 
 * @author mdominguez
 */


public abstract class MaterialCapacitacion implements Ordenable, Comparable<MaterialCapacitacion>, CsvRecord{
	
	protected Integer id;
	/**
	 * Titulo del material
	 */
	protected String titulo;

	/**
	 * Costo básico que debe sumarse al precio por el mero hecho de publicarlo en el
	 * portal
	 */
	protected Double costo;
	
	protected Integer calificacion;
	protected Date fechaPub;
	protected Relevancia relevancia;
	

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public Relevancia getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(Relevancia relevancia) {
		this.relevancia = relevancia;
	}

	/**
	 * Constructor por defecto
	 */
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0,0,null,null);
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo) {
		this(id,titulo,0.0,0,null,null);
	}
	
	public MaterialCapacitacion(Integer id, String titulo, Double costo) {
		this(id,titulo,costo,0,null,null);
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo, Integer calificacion, Date fechaPub, Relevancia relevancia) {
		this.calificacion = calificacion;
		this.fechaPub = fechaPub;
		this.relevancia = relevancia;
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
	}


	//TODO 01 implementar los metodos getters y setters y escribir el javadoc
	// AYUDA: para implementar estos metodos usar un atajo del IDE 
	// elegir el menu "Source" --> "Generate getters y setters" y elegir alli que metodos generar.
	

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	
	//TODO 02: sobrescribir el metodo toString de la clase "Object"
	//	el método toString retorna un string que representa el material actual
	//  retornando el titulo, y el precio 	 * usando el formato : 
	// [Titulo: <titulo> ; Precio: <precio> ]
	
	@Override
	public String toString() {
		return "[Titulo: "+this.getTitulo()+"; Precio: "+this.precio()+"]";
	}
	
	// TODO 10: implementar Ordenable
	@Override
	public final int valor() {
		return (int)this.precio().intValue();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCapacitacion other = (MaterialCapacitacion) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	@Override 
	public int compareTo(MaterialCapacitacion Aux) {
		if(this.equals(Aux) == true) {
			int precioAux = this.precio().intValue() - Aux.precio().intValue();
			return precioAux;
		} else {
			return this.titulo.compareToIgnoreCase(Aux.getTitulo());
		}
	}

	
}
