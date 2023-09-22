package com.adas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adas.models.MensajesModel;
import com.adas.services.IMensajeService;
import com.adas.services.INoticiaService;
import com.adas.services.IUsuarioService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private INoticiaService noticiaService;

	@Autowired
	private IMensajeService mensajeService;
	
	@GetMapping("")
	public String findAllNoticias(Model model) {
		model.addAttribute("noticias", this.noticiaService.findAll());
		
		return "noticias/noticias.html";
	}
	
	/******* NOTICIAS NOTICIA *******/
	@GetMapping("/noticia/{id}")
	public String findNoticiaById(@PathVariable("id") Integer id, 
								  Model model) {
		MensajesModel mensaje = new MensajesModel();
		
		model.addAttribute("noticia", this.noticiaService.findById(id));
		model.addAttribute("mensajeObj", this.mensajeService.findAllMensajesByIdNoticia(id));
		model.addAttribute("mensajeModel", mensaje);
		
		return "noticias/noticia.html";
	}
}