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
		for(int i=0; i < this.cantidadMateriales(); i++) {
			if(this.materiales.get(i) instanceof Libro) {
				libros++;
			}
		}
		return libros;
	}
	
	public Integer cantidadVideos() {
		int videos = 0;
		for(int i=0; i < this.cantidadMateriales(); i++) {
			if(this.materiales.get(i) instanceof Video) {
				videos++;
			}
		}
		return videos;
	}
	
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales();
	}
	
	public void imprimir() {
		for(int i=0; i < this.cantidadMateriales(); i++) {
			System.out.println(this.materiales.get(i).toString());
		}
	}
	
	public void ordenarPorPrecio(Boolean b) {
		if(b == true) {
			Collections.sort(this.materiales, (m1, m2) -> m1.precio().intValue() - m2.precio().intValue());
		} else {
			Collections.sort(this.materiales);
		}
	}
	
	public MaterialCapacitacion buscar(Integer precio) {
		Collections.sort(this.materiales, (m1, m2) ->  m1.getCosto().intValue() - m2.getCosto().intValue());
		return buscadorBinario(0, this.materiales.size(), precio);
	}
	
	private MaterialCapacitacion buscadorBinario(Integer inicio,Integer fin, Integer costo){
		
		if((fin == 1) && (this.materiales.get(0).getCosto().intValue() == costo)) {
			return this.materiales.get(0);
		} else {
			int centro = ((inicio+fin)/2);
			int Aux = this.materiales.get(centro).getCosto().intValue();
			
			if(Aux == costo) {
				return this.materiales.get(centro);
			} else {
				if(Aux > costo) {
					return buscadorBinario(inicio, centro-1, costo);
				} else {
					if(Aux < costo) {
						return buscadorBinario(centro+1, fin, costo);
					} 
				}
			}
			throw new RuntimeException ("Material de precio " + costo + " no encontrado");
		}
	}
}
