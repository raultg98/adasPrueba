package com.adas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adas.models.ActividadesAcordeonModel;
import com.adas.models.ActividadesModel;
import com.adas.services.IActividadService;

@Controller
@RequestMapping("/servicios/actividades")
public class ActividadesController {
	
	@Autowired
	private IActividadService actividadService;
	
	private String pathActividades = "src//main//resources//static//img//actividades//";

	
	// REVISAR CODIGO PARA METERLO TODOS EN UN BUCLE.
//	@GetMapping("")
//	public String findAllActividades(Model model) {
//		List<ActividadesModel> allActidades = this.actividadService.findAll();
//		
//		List<ActividadesModel> actividades2014 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2015 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2016 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2017 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2018 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2019 = new ArrayList<ActividadesModel>();
//		List<ActividadesModel> actividades2020 = new ArrayList<ActividadesModel>();
//		
//		for(int i=0; i<allActidades.size(); i++) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(allActidades.get(i).getFecha());
//			int fechaActual = calendar.get(Calendar.YEAR);
//			
//			switch (fechaActual) {
//				case 2014: actividades2014.add(allActidades.get(i)); break;
//				case 2015: actividades2015.add(allActidades.get(i)); break;
//				case 2016: actividades2016.add(allActidades.get(i)); break;
//				case 2017: actividades2017.add(allActidades.get(i)); break;
//				case 2018: actividades2018.add(allActidades.get(i)); break;
//				case 2019: actividades2019.add(allActidades.get(i)); break;
//				case 2020: actividades2020.add(allActidades.get(i)); break;
//			}
//			
//		}
//		
//		model.addAttribute("actividades2014", actividades2014);
//		model.addAttribute("actividades2015", actividades2015);
//		model.addAttribute("actividades2016", actividades2016);
//		model.addAttribute("actividades2017", actividades2017);
//		model.addAttribute("actividades2018", actividades2018);
//		model.addAttribute("actividades2019", actividades2019);
//		model.addAttribute("actividades2020", actividades2020);
//		
//		return "servicios/actividades/actividades.html";
//	}
	
	@GetMapping("")
	public String findAllActividades2(Model model) { 
		List<ActividadesModel> allActividades = this.actividadService.findAll();
		List<ActividadesAcordeonModel> actividadesPorAnio = new ArrayList<ActividadesAcordeonModel>();
	
		ActividadesAcordeonModel actividades2014 = new ActividadesAcordeonModel(2014);
		ActividadesAcordeonModel actividades2015 = new ActividadesAcordeonModel(2015);
		ActividadesAcordeonModel actividades2016 = new ActividadesAcordeonModel(2016);
		ActividadesAcordeonModel actividades2017 = new ActividadesAcordeonModel(2017);
		ActividadesAcordeonModel actividades2018 = new ActividadesAcordeonModel(2018);
		ActividadesAcordeonModel actividades2019 = new ActividadesAcordeonModel(2019);
		ActividadesAcordeonModel actividades2020 = new ActividadesAcordeonModel(2020);
		
		for(ActividadesModel actividad : allActividades) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(actividad.getFecha());
			int fechaActual = calendar.get(Calendar.YEAR);
			
			switch (fechaActual) {
				case 2014: actividades2014.setActividad(actividad); break;
				case 2015: actividades2015.setActividad(actividad); break;
				case 2016: actividades2016.setActividad(actividad); break;
				case 2017: actividades2017.setActividad(actividad); break;
				case 2018: actividades2018.setActividad(actividad); break;
				case 2019: actividades2019.setActividad(actividad); break;
				case 2020: actividades2020.setActividad(actividad); break;
			}
		}
		
		actividadesPorAnio.add(actividades2014);
		actividadesPorAnio.add(actividades2015);
		actividadesPorAnio.add(actividades2016);
		actividadesPorAnio.add(actividades2017);
		actividadesPorAnio.add(actividades2018);
		actividadesPorAnio.add(actividades2019);
		actividadesPorAnio.add(actividades2020);
		
		model.addAttribute("actividadesPorAnio", actividadesPorAnio);
	
		return "servicios/actividades/actividades.html";
	}
	
	
	/****************** MODIFICAR ESTE METODO */
	@GetMapping("/actividad/{id}")
	public String findActividadById(@PathVariable("id") Integer id,
								 	Model model) {
		ActividadesModel actividad = this.actividadService.findById(id);
		int anio = obtenerAnioActividadById(id);
		
		File carpeta = new File(this.pathActividades + anio +"/"+ actividad.getId());
		List<String> archivos = new ArrayList<String>();
		
		if(carpeta.listFiles() != null) {
			for(File archivo : carpeta.listFiles()) {
				archivos.add(archivo.getName());
			}
		}	
		
		model.addAttribute("fotos", archivos);
		model.addAttribute("actividadAnio", anio);
		model.addAttribute("actividad", actividad);
		
		return "servicios/actividades/actividad.html";
	}
	
	@GetMapping("/admin")
	public String findAllActividadesAdmin(Model model) {
		List<ActividadesModel> allActividades = this.actividadService.findAll();
		List<ActividadesAcordeonModel> actividadesPorAnio = new ArrayList<ActividadesAcordeonModel>();
	
		ActividadesAcordeonModel actividades2014 = new ActividadesAcordeonModel(2014);
		ActividadesAcordeonModel actividades2015 = new ActividadesAcordeonModel(2015);
		ActividadesAcordeonModel actividades2016 = new ActividadesAcordeonModel(2016);
		ActividadesAcordeonModel actividades2017 = new ActividadesAcordeonModel(2017);
		ActividadesAcordeonModel actividades2018 = new ActividadesAcordeonModel(2018);
		ActividadesAcordeonModel actividades2019 = new ActividadesAcordeonModel(2019);
		ActividadesAcordeonModel actividades2020 = new ActividadesAcordeonModel(2020);
		
		for(ActividadesModel actividad : allActividades) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(actividad.getFecha());
			int fechaActual = calendar.get(Calendar.YEAR);
			
			switch (fechaActual) {
				case 2014: actividades2014.setActividad(actividad); break;
				case 2015: actividades2015.setActividad(actividad); break;
				case 2016: actividades2016.setActividad(actividad); break;
				case 2017: actividades2017.setActividad(actividad); break;
				case 2018: actividades2018.setActividad(actividad); break;
				case 2019: actividades2019.setActividad(actividad); break;
				case 2020: actividades2020.setActividad(actividad); break;
			}
		}
		
		actividadesPorAnio.add(actividades2014);
		actividadesPorAnio.add(actividades2015);
		actividadesPorAnio.add(actividades2016);
		actividadesPorAnio.add(actividades2017);
		actividadesPorAnio.add(actividades2018);
		actividadesPorAnio.add(actividades2019);
		actividadesPorAnio.add(actividades2020);
		
		model.addAttribute("actividadesPorAnio", actividadesPorAnio);
		
		return "servicios/actividades/actividadesAdmin.html";
	}
	
	/******************** ACTIVIDAD ADMIN ********************/
	@GetMapping("/actividad/admin/{id}")
	public String findActividadByIdAdmin(@PathVariable("id") Integer id,
								 	  	 Model model) {
		ActividadesModel actividad = this.actividadService.findById(id);
		int anio = obtenerAnioActividadById(id);
		
		File carpeta = new File(this.pathActividades + anio +"/"+ actividad.getId());
		List<String> archivos = new ArrayList<String>();
		
		if(carpeta.listFiles() != null) {
			for(File archivo : carpeta.listFiles()) {
				archivos.add(archivo.getName());
			}
		}
		
		model.addAttribute("fotos", archivos);
		model.addAttribute("actividad", actividad);
		model.addAttribute("actividadAnio", anio);
		return "servicios/actividades/actividadAdmin.html";
	}

	@GetMapping("/actividad/admin/borrar/{anio}/{id}/{nombreFoto}")
	public String deletePhotoByActividad(@PathVariable("anio") String anio, 
										 @PathVariable("id") Integer id, 
										 @PathVariable("nombreFoto") String nombre, 
										 RedirectAttributes flash) {
		ActividadesModel actividad = this.actividadService.findById(id);
		
		File carpeta = new File(this.pathActividades+ anio +"/"+ id);
		
		for(File archivo : carpeta.listFiles()) {
			if(archivo.getName().equals(nombre)) {
				archivo.delete();
			}
		}
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha eliminado correctamente la imagen de "+ actividad.getNombre());
		
		return "redirect:/servicios/actividades/actividad/admin/"+ id;
	}

	
	/******************** ADD ********************/
	@GetMapping("/add/{anio}")
	public String createdNewActividad(@PathVariable("anio") String anio,
						  			  Model model) {
		ActividadesModel actividad = new ActividadesModel();
		
		model.addAttribute("actividad", actividad);
		
		return "servicios/actividades/actividad_add.html";
	}
	
	@PostMapping("/add/{anio}")
	public String saveActividad(@PathVariable("anio") String anio, 
								@Validated ActividadesModel actividad, 
								BindingResult result, 
								RedirectAttributes flash, 
								Model model) {
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), 
						"El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			
			model.addAttribute("errores", errores);
			model.addAttribute("actividad", actividad);
		}
		
		this.actividadService.save(actividad);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha registrado correctamente la actividad");
		
		return "redirect:/servicios/actividades/admin";
	}
	
	/******************** ADD ACTIVIDAD AÑO ********************/
	@GetMapping("/actividad/add/{anio}/{id}")
	public String showAddGaleriaActividadByYear(@PathVariable("anio") String anio, 
			   									@PathVariable("id") Integer id,
			   									Model model) {
		ActividadesModel actividad = this.actividadService.findById(id);
		
		model.addAttribute("actividad", actividad);
		model.addAttribute("actividadAnio", anio);
		
		return "servicios/actividades/actividadesGaleria_add.html";
	}
	
	@PostMapping("/actividad/add/{anio}/{id}")
	public String saveActividadGaleria(@PathVariable("anio") String anio, 
									   @PathVariable("id") Integer id, 
									   @RequestParam("addFoto") List<MultipartFile> imagenes, 
									   RedirectAttributes flash,
									   Model model) throws IOException {
		if(imagenes.size() > 0) {
			Path directorioActividades = 
				Paths.get(this.pathActividades+ anio +"/"+ id);
			String rutaAbsoluta = directorioActividades.toFile().getAbsolutePath();
			File carpetaActividades = new File(rutaAbsoluta);
			
			if(!carpetaActividades.exists()) {
				carpetaActividades.mkdirs();
			}
			
			for(int i=0; i<imagenes.size(); i++) {
				MultipartFile imagen = imagenes.get(i);
				
				try {
					byte[] byteImg = imagen.getBytes();
					Calendar calendar = Calendar.getInstance();
					Long nombreArchivo = calendar.getTimeInMillis();
					
					Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ nombreArchivo +".jpeg");
					
					Files.write(rutaCompleta, byteImg);
				}catch (IOException e) {
					throw new IOException("No se pudo guardar la imagen: "+ imagenes.get(i).getOriginalFilename());
				}
			}
			
			flash.addFlashAttribute("clase", "success");
			if(imagenes.size() == 1) {
				flash.addFlashAttribute("message", "La imagen se ha subido correctamente");
			}else {
				flash.addFlashAttribute("message", "Las imagenes se han subido correctamente");
			}
		}
		
		return "redirect:/servicios/actividades/admin";
	}
	
	/******************** EDITAR ********************/
	@GetMapping("/actividad/editar/{id}")
	public String editarActividad(@PathVariable("id") Integer id,
								  Model model){
		ActividadesModel actividad = this.actividadService.findById(id);
		
		model.addAttribute("actividad", actividad);
		
		return "servicios/actividades/actividad_editar.html";
	}
	
	@PostMapping("/actividad/editar/{id}")
	public String editandoActividad(@PathVariable("id") Integer id,
									@Validated ActividadesModel actividad, 
									BindingResult result,
									RedirectAttributes flash, 
									Model model) {
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();	
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), 
						"El campo "+ err.getField() +" "+ err.getDefaultMessage());
			});
			
			model.addAttribute("errores", errores);
			model.addAttribute("actividad", actividad);
		}
		
		int anio = obtenerAnioActividadById(id);
		
		this.actividadService.save(actividad);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha editado correctamente la actividad, "+ actividad.getId() +" - "+ actividad.getNombre());
		
		return "redirect:/servicios/actividades/admin";
	}
	
	/******************** BORRAR ACTIVIDAD ********************/
	@GetMapping("/actividad/borrar/{id}")
	public String deleteActividad(@PathVariable("id") Integer id,
								  RedirectAttributes flash) {
		ActividadesModel actividad = this.actividadService.findById(id);
		
		int anio = obtenerAnioActividadById(id);
		File carpeta = new File(this.pathActividades+ anio +"/"+ id);
		
		// PRIMERO TENGO QUE COMPROBAR QUE EXISTE LA CARPETA
		if(carpeta.exists()) {
			// TENGO QUE COMPROBAR QUE LA CARPETA TIENE ARCHIVOS
			if(carpeta.list().length == 0) {
				carpeta.delete();
			}else  if(carpeta.list().length > 0) {
				for(File archivo : carpeta.listFiles()) {
					archivo.delete();
				}
				
				carpeta.delete();
			}
		
		}
		try {			
			this.actividadService.deleteActividad(id);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("message", "Se elimino correctamente la actividad, "+ id);
		}catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("message", "ERROR: La actividad no se ha podido eliminar, "+ id);
		}
		
		return "redirect:/servicios/actividades/admin";
	}
	
	private int obtenerAnioActividadByNombre(String nombre) {
		ActividadesModel actividad = this.actividadService.findByNombre(nombre);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(actividad.getFecha());
		int anio = calendar.get(Calendar.YEAR);
		
		return anio;
	}
	
	private int obtenerAnioActividadById(Integer id) {
		ActividadesModel actividad = this.actividadService.findById(id);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(actividad.getFecha());
		int anio = calendar.get(Calendar.YEAR);
		
		return anio;
	}
}