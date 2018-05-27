package frsf.isi.died.tp.taller03;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class TestPaso05 {

	//Los atributos y las inicializaciones de los mismos fueron copiados de la clase TestPaso04.
	
	private BibliotecaABB biblioteca;
	private Libro matA;
	private Libro matC;
	private Libro matX;
	private Libro matB;
	private Libro matZ;
	private Libro matK;
	private Video matV;
	private Video matW;
	private Video matN;
	private Video matF;
	private Video matH;
	
	@Before
	public void init() {
		matA= new Libro(1, "A", 10.0, 20.0, 10);
		matC= new Libro(2, "C", 20.0, 10.0, 10);
		matX= new Libro(3, "X", 30.0, 20.0, 10);
		matB= new Libro(4, "B", 40.0, 30.0, 10);
		matZ= new Libro(5, "Z", 50.0, 25.0, 10);
		matK= new Libro(6, "K", 60.0, 20.0, 10);
		matV= new Video(7, "V", 70.0, 10);
		matW= new Video(8, "W", 80.0, 10);
		matN= new Video(9, "N", 30.0, 10);
		matF= new Video(10, "F", 40.0, 10);
		matH= new Video(11, "H", 50.0, 10);
		biblioteca = new BibliotecaABB();
	}
	
	@Test
	public void testRango() {
		
		biblioteca.agregar(matX);
		biblioteca.agregar(matF);
		biblioteca.agregar(matC);
		biblioteca.agregar(matA);
		
		//PRIMER TESTEO
		ArrayList<MaterialCapacitacion> test1 = (ArrayList<MaterialCapacitacion>) biblioteca.getMateriales().rango(0.0, 80.0);
		ArrayList<MaterialCapacitacion> aux = new ArrayList<MaterialCapacitacion>();
		
		aux.add(matX);
		aux.add(matF);
		aux.add(matC);
		aux.add(matA);
		
		assertEquals(aux, test1);
		System.out.println("PRIMER TESTING: ");
		for(MaterialCapacitacion a : aux) {
			System.out.println(a);
		}
		
		//SEGUNDO TESTEO
		ArrayList<MaterialCapacitacion> test2 = (ArrayList<MaterialCapacitacion>) biblioteca.getMateriales().rango(40.0, 50.0);
		aux = new ArrayList<MaterialCapacitacion>();
		
		aux.add(matX);
		aux.add(matF);
		
		assertEquals(aux, test2);
		System.out.println("-------------------------");
		System.out.println("SEGUNDO TESTING: ");
		for(MaterialCapacitacion a : aux) {
			System.out.println(a);
		}
		
		//TERCER TESTEO
		ArrayList<MaterialCapacitacion> test3 = (ArrayList<MaterialCapacitacion>) biblioteca.getMateriales().rango(0.0, 20.0);
		aux = new ArrayList<MaterialCapacitacion>();
		
		assertEquals(aux, test3);
		System.out.println("-------------------------");
		System.out.println("TERCER TESTING: ");
		System.out.println("LISTA VACIA");
	}
	
}
