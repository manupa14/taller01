package frsf.isi.died.tp.modelo.productos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Video extends MaterialCapacitacion {
	
	private static final Double costoSegundo=0.15;
	private Integer duracion;
	
	public Video() {
		
	}
	
	public Video(Integer id, String titulo) {
		super(id, titulo);
	}
	


	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Video(Integer id,String titulo, Double costo) {
		super(id, titulo, costo, 0, null, null, null);
	}
	
	public Video(Integer id,String titulo, Double costo, Integer duracion) {
		super(id, titulo, costo, 0, null, null, null);
		this.setDuracion(duracion);
	}
	
	public Video(Integer id,String titulo, Double costo, Integer calificacion, Date fechaPub, Relevancia relevancia, String tema) {
		super(id, titulo, costo, calificacion, fechaPub, relevancia, tema);
	}

	public Video(Integer id,String titulo, Double costo, Integer duracion, Integer calificacion, Date fechaPub, Relevancia relevancia, String tema) {
		super(id, titulo, costo, calificacion, fechaPub, relevancia, tema);
		this.setDuracion(duracion);
	}
	
	@Override
	public Double precio() {
		// TODO Auto-generated method stub
		return (super.costo+(this.duracion*this.costoSegundo));
	}

	@Override
	public Boolean esLibro() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean esVideo() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object Aux) {
		if((Aux instanceof Video) && (this.titulo.equalsIgnoreCase(((Video) Aux).getTitulo()))) {
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
		lista.add(this.duracion.toString());
		lista.add(this.calificacion.toString());
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		lista.add(formato.format(this.fechaPub));
		
		lista.add(this.relevancia.toString());
		lista.add(this.tema.toString());
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.duracion =Integer.valueOf(datos.get(3));
		this.calificacion = Integer.valueOf(datos.get(4));
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.fechaPub = formato.parse(datos.get(5));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.relevancia = Relevancia.valueOf(datos.get(6));
		this.tema = String.valueOf(datos.get(7));
	}
}
