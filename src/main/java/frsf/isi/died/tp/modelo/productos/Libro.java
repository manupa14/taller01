/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Representa un libro en el sistema de biblioteca digital
 * @author mdominguez
 */
public class Libro extends MaterialCapacitacion {
	/**
	 * Precio al que el libro se compro a la editorial que lo vende
	 */
	private Double precioCompra;
	/**
	 * Cantidad de paginas del libro
	 */
	private Integer paginas;

	/**
	 * Constructor por defecto del Libro
	 */
	public Libro() {
	}

	/**
	 * permite crerar un libro solo con su titulo e identificador
	 * @param id es el identificador del libro
	 * @param titulo es el titulo del libro
	 */
	public Libro(Integer id, String titulo) {
		this(id, titulo,0.0,0.0,0,0,null,null);
	}

	/**
	 * permite crear un libro con todos sus parametros
	 * @param id es el identificador del libro
	 * @param titulo es el titulo del libro
	 * @param costo es el costo de ofrecerlo online
	 * @param precioCompra es el precio al que se adquirió el libro a la editorial
	 * @param paginas cantidad de paginas del libro
	 */
	
	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas) {
		super(id, titulo, costo, 0, null, null);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
	}
	
	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Integer calificacion, Date fechaPub, Relevancia relevancia) {
		super(id, titulo, costo, calificacion, fechaPub, relevancia);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
	}

	/**
	 * retorna el precio al que el libro fue comprado
	 * @return precio al que el libro fue comprado
	 */
	public Double getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * asigna el precio de compra del libro
	 * @param precioCompra
	 */
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	/**
	 * retorna la cantidad de paginas del libro
	 * @return cantidad de paginas
	 */
	public Integer getPaginas() {
		return paginas;
	}

	/**
	 * asigna la cantidad de paginas del libro
	 * @param paginas
	 */
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	@Override
	public Boolean esLibro() {
		//TODO 03: implementar metodo abstracto
		return true;
	}

	@Override
	public Boolean esVideo() {
		//TODO 04: implementar metodo abstracto
		return false;
	}

	/**
	 * El precio de un libro se calcula segun la siguiente formula
	 * PRECIO = costo + (precio de compra * FACTOR_PAGINAS)
	 * FACTOR_PAGINAS es un 3% cada 150 paginas.
	 * 
	 * Por ejemplo, para un libro cuyo costo de publicacion es de $50 y cuyo precio de compra
	 * es de $100 si tiene 460 paginas se calculará su precio final como
	 * 
	 * PRECIO = 50.0 + (100.0 * (1.0+ (0.03 * 460/150))); 
	 * PRECIO = 50.0 + (100.0 * (1.0 + 0.09)) = 50.0 + 109.0 = 159.0
	 */
	@Override
	public Double precio() {
		//TODO 05: implementar metodo abstracto
		
		return super.costo+(this.precioCompra*(1.0+(0.03*(this.paginas/150))));
	}
	
	@Override
	public boolean equals(Object Aux) {
		if((Aux instanceof Libro) && (this.titulo.equalsIgnoreCase(((Libro) Aux).getTitulo()))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.paginas.toString());
		lista.add(this.precioCompra.toString());
		lista.add(this.calificacion.toString());
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		lista.add(formato.format(this.fechaPub));
		
		lista.add(this.relevancia.toString());
		return lista;
	}
	
	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.paginas =Integer.valueOf(datos.get(3));
		this.precioCompra =Double.valueOf(datos.get(4));
		this.calificacion =Integer.valueOf(datos.get(5));

		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.fechaPub = formato.parse(datos.get(6));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.relevancia =Relevancia.valueOf(datos.get(7));
	}
}