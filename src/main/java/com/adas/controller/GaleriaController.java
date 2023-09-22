 package com.adas.controller;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/galeria")
public class GaleriaController {
	
	private String directorioImagenes = "src//main//resources//static//img/galeria";
	
	@GetMapping("")
	public String getAllGaleria(Model model) throws IOException {
		File carpeta = new File(this.directorioImagenes);
		List<String> archivos = new ArrayList<String>();
		
		for(File archivo : carpeta.listFiles()) {
			archivos.add("/img/galeria/"+ archivo.getName());
		}
		
		model.addAttribute("fotos", archivos);
		
		return "galeria/galeria.html";
	}

	@GetMapping("/add")
	public String addImagenesForm() {
		
		return "galeria/galeria_add.html";
	}
	
	@PostMapping("/add")
	public String saveImagenes(@RequestParam("addFoto") List<MultipartFile> imagenes,
							   RedirectAttributes flash) throws IOException {
		
		if(imagenes.size() > 0) {
			Path carpetaImagenes = Paths.get(this.directorioImagenes);
			String rutaAbsoluta = carpetaImagenes.toFile().getAbsolutePath();
			
			for(int i=0; i<imagenes.size(); i++) {
				MultipartFile imagen = imagenes.get(i);
				
				try {
					byte[] byteImg = imagen.getBytes();
					Date fecha = new Date();
					Long nombreArchivo = fecha.getTime();
					
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ nombreArchivo +".jpeg");
					
					Files.write(rutaCompleta, byteImg);
					
				}catch(IOException io) {
					throw new IOException("No se pudo guardar la imagen: "+ imagenes.get(i).getOriginalFilename());
				}
			}
			
			flash.addFlashAttribute("clase", "success");
			if(imagenes.size() == 1) {
				flash.addFlashAttribute("message", "La imagen se subio correctamente");
			}else {
				flash.addFlashAttribute("message", "Las imagenes se han subido correctamente");
			}
		}
		
		return "redirect:/galeria/add";
	}
	
	@GetMapping("admin")
	public String getGaleriaAdmin(Model model) {
		File carpeta = new File(this.directorioImagenes);
		List<String> archivos = new ArrayList<String>();
		
		for(File archivo : carpeta.listFiles()) {
			archivos.add(archivo.getName());
		}
		
		model.addAttribute("fotos", archivos);
		
		return "galeria/galeria_admin.html";
	}
	
	@GetMapping("/admin/borrar/{nombre}")
	public String deleteImagen(@PathVariable("nombre") String nombre,
							   RedirectAttributes flash) {
		File carpeta = new File(this.directorioImagenes);
		
		for(File archivo : carpeta.listFiles()) {
			// COMPRUEBO QUE TENGAN EL MISMO NOMBRE (NOMBRE+EXT)
			if(archivo.getName().equals(nombre)) {
				archivo.delete();
			}
		}
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha eliminado correctamente la imagen");
		
		return "redirect:/galeria/admin";
	}
}