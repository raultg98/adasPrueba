package com.adas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

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

import com.adas.models.MensajesModel;
import com.adas.models.NoticiasModel;
import com.adas.models.UsuariosModel;
import com.adas.services.IMensajeService;
import com.adas.services.INoticiaService;
import com.adas.services.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminNoticiasController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private INoticiaService noticiaService;

	@Autowired
	private IMensajeService mensajeService;
	
	private String pathNoticias = "src//main//resources//static//img/noticias";
	
	@GetMapping("/noticias")
	public String findAllNoticias(Model model) {
		model.addAttribute("noticias", this.noticiaService.findAll());
		
		return "noticias/noticiasAdmin.html";
	}
	
	/******* NOTICIA (usuario, noticia, mensajes) *******/
	@GetMapping("/noticias/noticia/{id}")
	public String findNoticiaById(@PathVariable("id") Integer id, 
							  	  Model model) {
		NoticiasModel noticia = this.noticiaService.findById(id);
		List<MensajesModel> allMensajes = this.mensajeService.findAllMensajesByIdNoticia(id);
		
		if(allMensajes != null) {

			model.addAttribute("numeroMensajes", allMensajes.size());
		}
		
		model.addAttribute("noticia", noticia);
		model.addAttribute("mensajes", allMensajes);
		
		return "noticias/noticiaAdmin.html";
	}
	
	/******* NOTICIAS ADD *******/
	@GetMapping("/noticias/add")
	public String findNoticiasAndUsuarios(Model model) {
		NoticiasModel noticia = new NoticiasModel();
		List<UsuariosModel> usuarios = this.usuarioService.findAll();
		
		model.addAttribute("noticia", noticia);
		model.addAttribute("usuarios", usuarios);
		
		return "noticias/noticias_add.html";
	}
	
	@PostMapping("/noticias/add")
	public String saveNoticia(@Validated NoticiasModel noticia, 
							  @RequestParam("imagen") MultipartFile imagen,
							  BindingResult result, 
							  RedirectAttributes flash, 
							  Model model) throws IOException{
		Date fecha = new Date();	
		noticia.setFecha(fecha);
		
		/*
		 * Guardo aqui la noticia, para poder tener acceso a su id, como un unico campo que no
		 * tengo hasta que se no se crea la noticia en la BBDD, pues la creo para tener acceso 
		 * a el. El método save() me devuelve un NoticiasModel con los datos de la noticia que 
		 * acabo de guardar en la BBDD. Despues lo unico que tengo que hacer es añadir el campo
		 * del nombre de la foto y hacer un save, para que se actualice la noticia. Puedo hacer 
		 * esto ya que el campo del nombre de la foto no tiene la condicion de not null. 
		 */
		NoticiasModel noticiaCreada = this.noticiaService.save(noticia);
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get(this.pathNoticias);
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				String nombreUsuario = noticia.getUsuarioId().getNombre().toLowerCase();
				Integer idNoticia = noticiaCreada.getId();
				
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ nombreUsuario +"_"+ idNoticia +".jpeg");
				
				Files.write(rutaCompleta, bytesImg);
				
				noticiaCreada.setFoto(nombreUsuario +"_"+ idNoticia +".jpeg");
			} catch (IOException e) {
				throw new IOException("No se pudo guardar la imagen: "+ imagen.getOriginalFilename(), e);
			}
		}

		this.noticiaService.save(noticiaCreada);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se creo correctamente la noticia");
		
		return "redirect:/admin/noticias";
	}
	/******* NOTICIAS EDITAR *******/
	@GetMapping("/noticias/editar/{id}")
	public String findNoticiaByIdToEdit(@PathVariable("id") Integer id, 
									 	Model model) {
		NoticiasModel noticia = this.noticiaService.findById(id);
		List<UsuariosModel> usuarios = this.usuarioService.findAll();
		
		model.addAttribute("noticia", noticia);
		model.addAttribute("usuarios", usuarios);
		
		return "noticias/noticias_editar.html";
	}
	
	@PostMapping("/noticias/editar/{id}")
	public String saveNoticiaByIdEdited(@PathVariable("id") Integer id,
								  		@RequestParam("imagen") MultipartFile imagen,
								  		@Validated NoticiasModel noticia,
								  		RedirectAttributes flash, 
								  		Model model) throws IOException {
		if(noticia.getFoto() == null && imagen.getOriginalFilename().length() == 0) {
			noticia.setFoto(this.noticiaService.findById(id).getFoto());
		}else {
			if(!imagen.isEmpty()) {
				Path directorioImagenes = Paths.get(this.pathNoticias);
				String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
				
				try {
					byte[] bytesImg = imagen.getBytes();
					String nombreUsuario = noticia.getUsuarioId().getNombre().toLowerCase();
					Integer idNoticia = noticia.getId();
					
					System.out.println("================================");
					System.out.println("Nombre Usuario: "+ nombreUsuario);

					Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ nombreUsuario +"_"+ idNoticia +".jpeg");
					
					Files.write(rutaCompleta, bytesImg);
					
					noticia.setFoto(nombreUsuario +"_"+ idNoticia +".jpeg");
				} catch (IOException e) {
					throw new IOException("No se pudo guardar la imagen: "+ imagen.getOriginalFilename(), e);
				}
			}
		}
	
		noticia.setFecha(this.noticiaService.findById(id).getFecha());
		
		this.noticiaService.save(noticia);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha editado correctamente, "+ id);
		
		return "redirect:/admin/noticias";
	}
	
	/******* NOTICIAS BORRAR *******/
	@GetMapping("/noticias/borrar/{id}")
	public String deleteNoticiaById(@PathVariable("id") Integer id, 
							         RedirectAttributes flash) {
		NoticiasModel noticia = this.noticiaService.findById(id);
		String nombreFoto = noticia.getUsuarioId().getNombre().toLowerCase() +"_"+ id +".jpeg";
		File carpeta = new File(this.pathNoticias);
		
		System.out.println("======== usuarioID.getNombre() _ idNoticia .jpeg =========");
		System.out.println(nombreFoto);
		
		try {
			this.noticiaService.delete(id);
			
			if(carpeta.list().length > 0) {
				for(File archivo : carpeta.listFiles()) {
					System.out.println("====== archivo.getName() ======");
					System.out.println(archivo.getName());
					
					if(archivo.getName().equals(nombreFoto)) {
						System.out.println("==============");
						System.out.println("LOS NOMBRE SON IGUALES");
						archivo.delete();
					}
				}
			}
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("message", "Se elimino correctamente la noticia, "+ id);
			
			return "redirect:/admin/noticias";
		}catch(Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("message", "NO SE PUDO ELIMINAR LA NOTICIA, "+ id);
		}
		
		return "redirect:/admin/noticias";
	}
	
	/******* NOTICIAS MENSAJES *******/
	/******* MENSAJES MOSTRAR *******/
	@GetMapping("/noticias/mensajes/{id}")
	public String findNoticiaAllMensajes(@PathVariable("id") Integer id, 
									     Model model) {
		model.addAttribute("noticia", this.noticiaService.findById(id));
		model.addAttribute("mensajes", this.mensajeService.findAllMensajesByIdNoticia(id));
		
		return "noticias/noticias_mensajes.html";
	}
}