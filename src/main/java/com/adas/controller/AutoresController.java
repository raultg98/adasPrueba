package com.adas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adas.models.AutorMensajesModel;
import com.adas.models.AutoresModel;
import com.adas.models.MensajesModel;
import com.adas.services.IAutorService;
import com.adas.services.IMensajeService;

@Controller
@RequestMapping("/autores")
public class AutoresController {
	
	@Autowired
	private IMensajeService mensajeService;

	@Autowired
	private IAutorService autorService;

	@GetMapping("")
	public String findAllAutores(Model model) {
		List<AutoresModel> allAutores =  this.autorService.findAll();
		List<AutorMensajesModel> allAutoresMensajes = new ArrayList<AutorMensajesModel>();
		
		for(AutoresModel autor: allAutores) {
			int numeroMensajes;
			if(mensajeService.findAllMensajesByAutor(autor.getId()) == null) {
				numeroMensajes = 0;
			}else {
				numeroMensajes = mensajeService.findAllMensajesByAutor(autor.getId()).size();
			}
			
			allAutoresMensajes.add(new AutorMensajesModel(autor, numeroMensajes));
		}
		
		model.addAttribute("autoresMensajes", allAutoresMensajes);
		
		return "autores/autores.html";
	}
	
	
	/******* AUTOR BORRAR *******/
	@GetMapping("/autor/borrar/{id}")
	public String borrarAutor(@PathVariable("id") Integer id, 
							  RedirectAttributes flash) {
		try {
			this.autorService.deleteAutor(id);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("message", "Se elimino correctamente al autor, "+ id);
		}catch(Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("message", "NO SE HA PODIDO ELMINAR EL AUTOR, "+ id);
		}
		
		return "redirect:/autores";
	}
	
	
	/******* AUTOR MENSAJES *******/
	@GetMapping("/autor/mensajes/{id}")
	public String home(@PathVariable("id") Integer id, 
			           Model model) {
		AutoresModel autor = this.autorService.findById(id);
		List<MensajesModel> mensajes = this.mensajeService.findAllMensajesByAutor(id);
		int numeroMensajes;
		
		if(this.mensajeService.findAllMensajesByAutor(id) == null) {
			numeroMensajes = 0;
		}else {
			numeroMensajes = mensajes.size();
		}
		
		model.addAttribute("autor", autor);
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("numeroMensajes", numeroMensajes);
		
		return "autores/autor.html";
	}
}