package frsf.isi.died.tp.modelo.productos;

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
		super(id, titulo);
		super.costo= costo;
	}

	public Video(Integer id,String titulo, Double costo, Integer duracion) {
		super(id, titulo);
		super.costo=costo;
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
}
