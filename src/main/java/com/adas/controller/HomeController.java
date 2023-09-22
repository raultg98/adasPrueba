package com.adas.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adas.models.ActividadesImagenModel;
import com.adas.models.ActividadesModel;
import com.adas.models.NoticiasModel;
import com.adas.services.IActividadService;
import com.adas.services.INoticiaService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private INoticiaService noticiaService;
	
	@Autowired
	private IActividadService actividadService;
	
	private String directorioGaleria = "src//main//resources//static//img/galeria";
	private String pathActividades = "src//main//resources//static//img//actividades//";

	@GetMapping("")
	public String showIndex(Model model) {
		List<NoticiasModel> allNoticias = this.noticiaService.findAll();
		List<NoticiasModel> noticias = new ArrayList<NoticiasModel>();
		
		// TENGO QUE COMPROBAR QUE AL MENOS TENGO NOTICIAS
		if(allNoticias.size() > 0) {
			int comprobacion;
			
			if(allNoticias.size() <= 3) {
				comprobacion = allNoticias.size();
			}else {
				comprobacion = 3;
			}
			
			int contador = 0;
			int numeroNoticias = allNoticias.size();
			
			while(allNoticias.size() >= contador && contador != comprobacion) {
				contador++;
				
				noticias.add(allNoticias.get(numeroNoticias - contador));
			}
			
			model.addAttribute("noticias", noticias);
		}
		
		// MANDO UN LISTADO DE FOTOS PARA MOSTRAR EN LA GALERIA
		File carpeta = new File(this.directorioGaleria);
		List<String> imagenes = new ArrayList<String>();
		
		if(carpeta.listFiles().length > 0) {
			
			int contador = 0;
			for(File archivo : carpeta.listFiles()) {
				contador++;
				
				if(contador <= 5) {
					imagenes.add("/img/galeria/"+ archivo.getName());
				}
				
			}
			
			model.addAttribute("galeria", imagenes);
		}
		
		// ACTIVIDADES
		List<ActividadesImagenModel> actividadPerYear = findOneActividadPerYear();
		model.addAttribute("actividades", actividadPerYear);

		
		return "index.html";
	}
	
	/**
	 * Método con el cual devuelvo una lista de un nuevo modelo que he creado para pasar solamente una
	 * actividad, en este modelo también paso una imagen de la galeria de dicha actividad
	 * @return List<ActividadesImagenModel>, lista del nuevo modelo
	 */
	public List<ActividadesImagenModel> findOneActividadPerYear() {
		List<ActividadesModel> allActividades = (List<ActividadesModel>) this.actividadService.findAll();
		List<ActividadesImagenModel> actividadPorAnio = new ArrayList<ActividadesImagenModel>();
		
		int contador = 0;
		
		for(ActividadesModel actividad : allActividades) {
			String foto;
			
			// OBTENGO LA FECHA ACTUAL
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(actividad.getFecha());
			int fechaActual = calendar.get(Calendar.YEAR);
			
			// OBTENGO EL NOMBRE DE LA FOTO DE UN AÑO
			File carpeta = new File(this.pathActividades + fechaActual +"/"+ actividad.getId());
			List<String> archivos = new ArrayList<String>();
			
			if(carpeta.listFiles() != null) {
				for(File archivo : carpeta.listFiles()) {
					archivos.add(archivo.getName());
				}
			}
			
			if(fechaActual == 2014) {
				if(contador == 0) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}else if(fechaActual == 2015) {
				if(contador == 0) {
					contador++;
				}
				
				if(contador == 1) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}
			else if(fechaActual == 2016) {
				if(contador == 0 || contador == 1) {
					contador = 2;
				}
				
				if(contador == 2) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}
			else if(fechaActual == 2017) {
				if(contador == 0 || contador == 1 || contador == 2) {
					contador = 3;
				}
				
				if(contador == 3) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}
			else if(fechaActual == 2018) {
				if(contador == 0 || contador == 1 || contador == 2 || contador == 3) {
					contador = 4;
				}
				
				if(contador == 4) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}
			else if(fechaActual == 2019) {
				if(contador == 0 || contador == 1 || contador == 2 || contador == 3 || contador == 4) {
					contador = 5;
				}
				
				if(contador == 5) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}
			else if(fechaActual == 2020) {
				if(contador == 0 || contador == 1 || contador == 2 || contador == 3 || contador == 4 || contador == 5) {
					contador = 6;
				}
				
				if(contador == 6) {
					if(!archivos.isEmpty()) {
						foto = archivos.get(0);
					}else {
						foto = "";
					}
					
					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
					contador++;
				}
			}	
		}
		
		return actividadPorAnio;
	}
}