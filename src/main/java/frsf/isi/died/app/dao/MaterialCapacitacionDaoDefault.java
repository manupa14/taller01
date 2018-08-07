package frsf.isi.died.app.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import frsf.isi.died.app.dao.util.CsvDatasource;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.modelo.Biblioteca;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.ComparadorMateriales;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao{

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL  = new Grafo<MaterialCapacitacion>();
	private static Integer SECUENCIA_ID=0;
	private static Biblioteca biblioteca = new BibliotecaABB();
	
	private CsvDatasource dataSource;
	
	public MaterialCapacitacionDaoDefault() {
		dataSource = new CsvDatasource();
		if(GRAFO_MATERIAL.esVacio()) {
			cargarGrafo();
		}
	}

	private void cargarGrafo() {
		List<List<String>> libros = dataSource.readFile("libros.csv");
		for(List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.setPageRank(1.0);
			aux.loadFromStringRow(filaLibro);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> videos= dataSource.readFile("videos.csv");
		for(List<String> filaVideo: videos) {
			Video aux = new Video();
			aux.setPageRank(1.0);
			aux.loadFromStringRow(filaVideo);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> aristas= dataSource.readFile("aristas.csv");
		for(List<String> filaArista: aristas) {
			MaterialCapacitacion n1 = this.findById(Integer.valueOf(filaArista.get(0)));
			MaterialCapacitacion n2 = this.findById(Integer.valueOf(filaArista.get(2)));
			GRAFO_MATERIAL.conectar(n1, n2);
		}
 	}
	
	@Override
	public void agregarAWishlist(MaterialCapacitacion material) {
		try {
			dataSource.agregarFilaAlFinal("wishlist.csv", material);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public PriorityQueue<MaterialCapacitacion> getWishlist() {
		PriorityQueue<MaterialCapacitacion> wishlist = new PriorityQueue<MaterialCapacitacion>(10, new ComparadorMateriales());
		List<List<String>> wishlistAux = dataSource.readFile("wishlist.csv");
		
		for(List<String> aux : wishlistAux) {
			if(aux.size() == 8) {
				Video videoAux = new Video();
				videoAux.loadFromStringRow(aux);
				wishlist.add(videoAux);
			} else {
				Libro libroAux = new Libro();
				libroAux.loadFromStringRow(aux);
				wishlist.add(libroAux);
			}
		}
		
		return wishlist;
	}
	
	@Override
	public void calcularPageRank(List<MaterialCapacitacion> materiales) {
		List<Double> pageRankPorMaterial = new ArrayList<Double>();
		
		for(int i=0; i<10; i++) {
			for(MaterialCapacitacion auxMaterial : materiales) {
				pageRankPorMaterial.add(pageRank(auxMaterial));
			}

			actualizarPageRank(materiales, pageRankPorMaterial);
			
			pageRankPorMaterial.clear();
		}
	}
	
	@Override
	public Double pageRank(MaterialCapacitacion material) {
		Double resultado = 1.0;
		Double sumatoria = 0.0;
		
		for(MaterialCapacitacion aux : GRAFO_MATERIAL.getRelacionesPageRank(material)) {
			sumatoria += ((Double) (aux.getPageRank()/GRAFO_MATERIAL.gradoSalida(aux)));
		}
		resultado = 0.5 + (0.5 * sumatoria);
		
		return resultado;
	}
	
	@Override
	public void actualizarPageRank(List<MaterialCapacitacion> materiales, List<Double> pageRankPorMaterial) {
		
		for(int i=0; i<materiales.size(); i++) {
			materiales.get(i).setPageRank(pageRankPorMaterial.get(i));
		}
		
	}
	
	@Override
	public void agregarLibro(Libro mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);	
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("libros.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void borrarLibro(Libro mat) {
		List<List<String>> libros = dataSource.readFile("libros.csv");
		boolean encontrado=false;
		int contador=0;
		
		for(List<String> aux : libros) {
			
			Libro libroAux = new Libro();
			libroAux.loadFromStringRow(aux);
			
			if(libroAux.getId() == mat.getId()) {
				encontrado=true;
				break;
			} 
			contador++;
		}
		
		if(encontrado) {
			libros.remove(contador);
		}
		
		dataSource.borrarArchivo("libros.csv");
		
		for(List<String> aux2 : libros) {
			Libro libroAux = new Libro();
			libroAux.loadFromStringRow(aux2);
			try {
				dataSource.agregarFilaAlFinal("libros.csv", libroAux);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<ArrayList<String>> buscarLibroPorTitulo(String dato){
		List<List<String>> librosAux = dataSource.readFile("libros.csv");
		List<ArrayList<String>> libros = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : librosAux) {
			if(aux.get(1).equals(dato)) {
				libros.add((ArrayList<String>) aux);
			}
		}
		
		return libros;
	}
	
	@Override
	public List<ArrayList<String>> buscarLibroPorTema(String dato){
		List<List<String>> librosAux = dataSource.readFile("libros.csv");
		List<ArrayList<String>> libros = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : librosAux) {
			if(aux.get(8).equals(dato)) {
				libros.add((ArrayList<String>) aux);
			}
		}
		
		return libros;
	}
	
	@Override
	public List<ArrayList<String>> buscarLibroPorCalificacion(String dato) {
		List<List<String>> librosAux = dataSource.readFile("libros.csv");
		List<ArrayList<String>> libros = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : librosAux) {
			if(aux.get(5).equals(dato)) {
				libros.add((ArrayList<String>) aux);
			}
		}
		
		return libros;
	}
	
	@Override
	public List<ArrayList<String>> buscarLibroPorFecha(String desde, String hasta){
		List<List<String>> librosAux = dataSource.readFile("libros.csv");
		List<ArrayList<String>> libros = new ArrayList<ArrayList<String>>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date fechaDesde = formato.parse(desde);
			Date fechaHasta = formato.parse(hasta);
		
			for(List<String> aux : librosAux) {
				Date fechaAux = formato.parse(aux.get(6));
					
				if(fechaAux.equals(fechaDesde) || fechaAux.equals(fechaHasta)) {
					libros.add((ArrayList<String>) aux);
				} else {
					if(fechaAux.after(fechaDesde) && fechaAux.before(fechaHasta)) {
						libros.add((ArrayList<String>) aux);
					}
				}
			}
		} catch (ParseException e) {
				e.printStackTrace();
		}
		
		return libros;
	}
	
	@Override
	public void agregarVideo(Video mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);				
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("videos.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void borrarVideo(Video mat) {
		List<List<String>> videos = dataSource.readFile("videos.csv");
		boolean encontrado=false;
		int contador=0;
		
		for(List<String> aux : videos) {
			
			Video videoAux = new Video();
			videoAux.loadFromStringRow(aux);
			
			if(videoAux.getId() == mat.getId()) {
				encontrado=true;
				break;
			} 
			contador++;
		}
		
		if(encontrado) {
			videos.remove(contador);
		}
		
		dataSource.borrarArchivo("videos.csv");
		
		for(List<String> aux2 : videos) {
			Video videoAux = new Video();
			videoAux.loadFromStringRow(aux2);
			try {
				dataSource.agregarFilaAlFinal("videos.csv", videoAux);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<ArrayList<String>> buscarVideoPorTitulo(String dato){
		List<List<String>> videosAux = dataSource.readFile("videos.csv");
		List<ArrayList<String>> videos = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : videosAux) {
			if(aux.get(1).equals(dato)) {
				videos.add((ArrayList<String>) aux);
			}
		}
		
		return videos;
	}
	
	@Override
	public List<ArrayList<String>> buscarVideoPorTema(String dato){
		List<List<String>> videosAux = dataSource.readFile("videos.csv");
		List<ArrayList<String>> videos = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : videosAux) {
			if(aux.get(7).equals(dato)) {
				videos.add((ArrayList<String>) aux);
			}
		}
		
		return videos;
	}

	@Override
	public List<ArrayList<String>> buscarVideoPorCalificacion(String dato) {
		List<List<String>> videosAux = dataSource.readFile("videos.csv");
		List<ArrayList<String>> videos = new ArrayList<ArrayList<String>>();
		
		for(List<String> aux : videosAux) {
			if(aux.get(4).equals(dato)) {
				videos.add((ArrayList<String>) aux);
			}
		}
		
		return videos;
	}
	
	@Override
	public List<ArrayList<String>> buscarVideoPorFecha(String desde, String hasta){
		List<List<String>> videosAux = dataSource.readFile("videos.csv");
		List<ArrayList<String>> videos = new ArrayList<ArrayList<String>>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date fechaDesde = formato.parse(desde);
			Date fechaHasta = formato.parse(hasta);
		
			for(List<String> aux : videosAux) {
				Date fechaAux = formato.parse(aux.get(5));
					
				if(fechaAux.equals(fechaDesde) || fechaAux.equals(fechaHasta)) {
					videos.add((ArrayList<String>) aux);
				} else {
					if(fechaAux.after(fechaDesde) && fechaAux.before(fechaHasta)) {
						videos.add((ArrayList<String>) aux);
					}
				}
			}
		} catch (ParseException e) {
				e.printStackTrace();
		}
		
		return videos;
	}
	
	@Override
	public List<Libro> listaLibros() {
		List<Libro> libros = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esLibro()) libros.add((Libro)mat); 
		}
		return libros;
	}

	@Override
	public List<Video> listaVideos() {
		List<Video> vids = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esVideo()) vids.add((Video)mat); 
		}
		return vids;
	}

	@Override
	public List<MaterialCapacitacion> listaMateriales() {
		// TODO Auto-generated method stub
		return GRAFO_MATERIAL.listaVertices();
	}
	
	@Override
	public List<MaterialCapacitacion> listaMaterialesPorTema(String tema) {
		ArrayList<MaterialCapacitacion> listaAux = (ArrayList<MaterialCapacitacion>) listaMateriales();
		
		Iterator<MaterialCapacitacion> iter = listaAux.iterator();
		
		while(iter.hasNext()) {
			MaterialCapacitacion aux = iter.next();
			
			if(!aux.getTema().equals(tema)) {
				iter.remove();
			}
		}
		
		return listaAux;
	}

	@Override
	public MaterialCapacitacion findById(Integer id) {
		// TODO Auto-generated method stub
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getId().equals(id)) return mat;
		}
		return null;
	}

	@Override
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		return GRAFO_MATERIAL.buscarCaminoNSaltos(n1, n2, saltos);
	}

	@Override
	public void crearCamino(Integer idOrigen, Integer idDestino) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		GRAFO_MATERIAL.conectar(n1, n2);
		List<String> fila = new ArrayList<>();
		fila.add(n1.getId()+"");
		fila.add(n1.getTitulo());
		fila.add(n2.getId()+"");
		fila.add(n2.getTitulo());
		try {
			dataSource.agregarFilaAlFinal("aristas.csv", fila);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
