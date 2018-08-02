package frsf.isi.died.tp.modelo;

import java.util.Comparator;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ComparadorMateriales implements Comparator<MaterialCapacitacion>{
	
	public int compare(MaterialCapacitacion mat1, MaterialCapacitacion mat2) {
		int resultado = mat1.getRelevancia().compareTo(mat2.getRelevancia());
		
		if(resultado == 0) {
			
			resultado = mat1.getCalificacion().compareTo(mat2.getCalificacion());
			
			if(resultado == 0) {
				
				resultado = mat1.precio().compareTo(mat2.precio());
			}
		}
		
		return resultado;
	}
}
