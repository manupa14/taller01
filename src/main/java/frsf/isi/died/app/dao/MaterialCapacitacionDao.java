package frsf.isi.died.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public interface MaterialCapacitacionDao {

	public void agregarLibro(Libro mat);
	public void borrarLibro(Libro mat);
	public List<ArrayList<String>> buscarLibroPorTitulo(String dato);
	public List<ArrayList<String>> buscarLibroPorTema(String dato);
	public List<ArrayList<String>> buscarLibroPorCalificacion(String dato);
	public List<ArrayList<String>> buscarLibroPorFecha(String desde, String hasta);
	public void agregarVideo(Video mat);
	public void borrarVideo(Video mat);
	public List<ArrayList<String>> buscarVideoPorTitulo(String dato);
	public List<ArrayList<String>> buscarVideoPorTema(String dato);
	public List<ArrayList<String>> buscarVideoPorCalificacion(String dato);
	public List<ArrayList<String>> buscarVideoPorFecha(String desde, String hasta);
	public void agregarAWishlist(MaterialCapacitacion material);
	public PriorityQueue<MaterialCapacitacion> getWishlist();
	public Double pageRank(MaterialCapacitacion material);
	public void actualizarPageRank(List<MaterialCapacitacion> materiales, List<Double> pageRankPorMaterial);
	public void calcularPageRank(List<MaterialCapacitacion> materiales);
	public List<Libro> listaLibros();
	public List<Video> listaVideos();
	public List<MaterialCapacitacion> listaMateriales();
	public List<MaterialCapacitacion> listaMaterialesPorTema(String tema);
	public MaterialCapacitacion findById(Integer id);
	public void crearCamino(Integer idOrigen, Integer idDestino);
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos);
}
