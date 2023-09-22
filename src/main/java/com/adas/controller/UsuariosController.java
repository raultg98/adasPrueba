package com.adas.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.adas.models.NoticiasModel;
import com.adas.models.UsuariosModel;
import com.adas.services.INoticiaService;
import com.adas.services.INoticiaService;
import com.adas.services.IUsuarioService;

import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuariosController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private INoticiaService noticiaService;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;

	@GetMapping("")
	public String findAllUsuarios(Model model) {
		model.addAttribute("usuarios", this.usuarioService.findAll());
		
		return "usuarios/usuarios.html";
	}
	
	/******* USUARIOS ADD *******/
	@GetMapping("/add")
	public String showUsuarioCreateForm(Model model) {
		UsuariosModel usuario = new UsuariosModel();
		
		model.addAttribute("usuario", usuario);
		
		return "usuarios/usuarios_add.html";
	}
	
	@PostMapping("/add")
	public String saveNewUsuario(@Validated UsuariosModel usuario,
								 BindingResult result,
								 RedirectAttributes flash, 
								 Model model) {
		
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			
			return "redirect:/admin/usuarios/add";
		}
		
//		usuario.setContrasenia(encoder.encode(usuario.getContrasenia()));
		
		this.usuarioService.save(usuario);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha registrado correctamente al usuario");
		
		return "redirect:/admin/usuarios";
	}
	
	/******* USUARIO ADD NOTICIA *******/
	@GetMapping("usuario/add/{id}")
	public String showUsuarioCreateFormNoticia(@PathVariable("id") Integer id, 
											   Model model) {
		NoticiasModel noticia = new NoticiasModel();
		UsuariosModel usuario = this.usuarioService.findById(id);
		
		model.addAttribute("noticia", noticia);
		model.addAttribute("usuario", usuario);
		
		return "noticias/noticiasUsuario_add.html";
	}
	
	@PostMapping("usuario/add/{id}")
	public String saveNoticiaByUsuario(@Validated NoticiasModel noticia, 
									   @RequestParam("imagen") MultipartFile imagen, 
									   @PathVariable("id") Integer id,
									   RedirectAttributes flash, 
									   Model model) throws IOException{
		UsuariosModel usuario = this.usuarioService.findById(id);
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//img/noticias");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				String nombreUsuario = usuario.getNombre().toLowerCase();
				Integer idNoticia = this.noticiaService.noticiasActuales()+1;
				
				Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ nombreUsuario +"_"+ idNoticia +".jpeg");
				
				Files.write(rutaCompleta, bytesImg);
				
				noticia.setFoto(nombreUsuario +"_"+ idNoticia +".jpeg");
			} catch(IOException e) {
				throw new IOException("No se pudo guardar la imagen: "+ imagen.getOriginalFilename(), e);
			}
		}
		
		Date fecha =  new Date();
		noticia.setFecha(fecha);
		noticia.setUsuarioId(usuario);
		
		this.noticiaService.save(noticia);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se creo correctamente la noticia");
		
		return "redirect:/admin/usuarios/usuario/"+ id;
	}
	
	/******* USUARIOS EDITAR *******/
	@GetMapping("/editar/{id}")
	public String showUsuarioEditForm(@PathVariable("id") Integer id, 
									  Model model) {
		UsuariosModel usuario = this.usuarioService.findById(id);
		model.addAttribute("usuario", usuario);
		
		return "usuarios/usuarios_editar.html";
	}
	
	@PostMapping("/editar/{id}")
	public String saveUsuarioEdited(@PathVariable("id") Integer id,
									@Validated UsuariosModel usuario,
									BindingResult result, 
									RedirectAttributes flash,
									Model model) {
		
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			
			return "redirect:/admin/usuarios/editar";
		}
		
		if(usuario.getContrasenia().length() == 0) {
			String contrasenia = this.usuarioService.findPasswordById(id);
			usuario.setContrasenia(contrasenia);
		}else {
//			usuario.setContrasenia(encoder.encode(usuario.getContrasenia()));
		}
		
		this.usuarioService.save(usuario);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha editado correctamente el usuario, "+ id);
		
		return "redirect:/admin/usuarios";
	}
	
	/******* USUARIOS BORRAR *******/
	@GetMapping("/borrar/{id}")
	public String deleteUsuarioById(@PathVariable("id") Integer id, 
								 	RedirectAttributes flash) {
		try {
			this.usuarioService.deleteUsuario(id);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("message", "Se elimino correctamente el usuario, "+ id);
		}catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("message", "ERROR: El usuario no se ha podido eliminar, "+ id);
		}
		
		return "redirect:/admin/usuarios";
	}
	
	/******* USUARIOS NOTICIAS *******/
	@GetMapping("/usuario/{id}")
	public String findUsuarioAllNoticias(@PathVariable("id") Integer id,
								   		 Model model) {
		model.addAttribute("noticias", this.noticiaService.findByUsuarioId(id));
		model.addAttribute("usuario", this.usuarioService.findById(id));
		
		return "usuarios/usuario.html";
	}
}