package com.adas.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adas.models.AutoresModel;
import com.adas.models.MensajesModel;
import com.adas.models.NoticiasModel;
import com.adas.services.IAutorService;
import com.adas.services.IMensajeService;
import com.adas.services.INoticiaService;

@Controller
@RequestMapping("/mensajes")
public class MensajesController {

	@Autowired
	private IMensajeService mensajeService;
	
	@Autowired
	private INoticiaService noticiaService;
	
	@Autowired
	private IAutorService autorService;
	
	@GetMapping("")
	public String findAllMensajes(Model model) {
		List<MensajesModel> mensajes = this.mensajeService.findAll();
		
		model.addAttribute("mensajes", mensajes);
		
		return "mensajes/mensajes.html";
	}
	
	/******* MENSAJES ADD *******/
	@GetMapping("/add/{id}")
	public String showCreateMensajeForm(@PathVariable("id") Integer id,
										Model model) {
		MensajesModel mensaje = new MensajesModel();
		
		model.addAttribute("mensajeObj", mensaje);
		
		return "mensajes/mensajes_add.html";
	}
	
	@PostMapping("/add/{id}")
	public String saveMensajeAndCreateAuthor(@PathVariable("id") Integer id,
										    @RequestParam("mensaje") String mensaje,
										    @RequestParam("nombre") String nombre, 
										    @RequestParam("correo") String correo,
										    RedirectAttributes flash, 
										    Model model) {
		NoticiasModel noticia = this.noticiaService.findById(id);
		MensajesModel newMensaje = new MensajesModel();
		Date fecha = new Date();
		AutoresModel autor;
		
		if(this.autorService.existsAutor(correo)) {
			autor = this.autorService.findByCorreo(correo);
		}else {
			autor = new AutoresModel(nombre, correo);
		}
		
		this.autorService.save(autor);
		
		
		newMensaje.setFecha(fecha);
		newMensaje.setMensaje(mensaje);
		newMensaje.setNoticiaId(noticia);
		newMensaje.setAutorId(autor);
		
		
		this.mensajeService.save(newMensaje);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se creo correctamente el mensaje");
		
		return "redirect:/noticias/noticia/"+ id;
	}
	
	
	/******* MENSAJES EDITAR *******/
	// UNA VEZ CREADO EL MENSAJE NO SE PODRA EDITAR Y 
	// SOLAMENTE UN USUARIO REGISTRADO PUEDE BORRAR MENSAJES
	
	/******* MENSAJES BORRAR *******/
	@GetMapping("/borrar/{id}")
	public String deleteMensajeById(@PathVariable("id") Integer id, 
									RedirectAttributes flash) {
		try {
			this.mensajeService.deleteMensaje(id);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("message", "Se ha borrado el mensajes correctamente, "+ id);
		}catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("message", "NO SE HA PODIDO ELMINAR EL MENSAJE, "+ id);
		}
		
		return "redirect:/mensajes";
	}
}