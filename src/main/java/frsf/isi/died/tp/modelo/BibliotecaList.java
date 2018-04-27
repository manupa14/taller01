package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class BibliotecaList implements Biblioteca {
	
	private ArrayList<MaterialCapacitacion> materiales;
	
	public BibliotecaList() {
		this.materiales = new ArrayList<>();
	}
	
	public void agregar(MaterialCapacitacion mat) {
		this.materiales.add(mat);
	}
	
	public Integer cantidadMateriales() {
		return this.materiales.size();
	}
	
	public Integer cantidadLibros() {
		int libros = 0;
		for(MaterialCapacitacion Aux : materiales) {
			if(Aux instanceof Libro) {
				libros++;
			}
		}
		return libros;
	}
	
	public Integer cantidadVideos() {
		int videos = 0;
		for(MaterialCapacitacion Aux : materiales) {
			if(Aux instanceof Video) {
				videos++;
			}
		}
		return videos;
	}
	
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales();
	}
	
	public void imprimir() {
		for(MaterialCapacitacion Aux : materiales) {
			System.out.println(Aux);
		}
	}
	
	public void ordenarPorPrecio(Boolean b) {
		if(b == true) {
			Collections.sort(this.materiales, (m1, m2) -> m1.precio().intValue() - m2.precio().intValue());
		} else {
			Collections.sort(this.materiales);
		}
	}
}
