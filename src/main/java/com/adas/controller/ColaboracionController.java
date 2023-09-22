package com.adas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adas.models.CorreoModel;

@Controller
@RequestMapping("/colaboracion")
public class ColaboracionController {

	@Autowired
	private JavaMailSender mail;
	
	@GetMapping("")
	public String renderColaboracion(Model model) {
		CorreoModel correo = new CorreoModel();
		
		model.addAttribute("correoObj", correo);
		
		return "individualViews/colaboracion.html";
	}
	
	@PostMapping("")
	public String sendEmail(@Validated CorreoModel correo, 
							RedirectAttributes flash, 
							Model model) {
		SimpleMailMessage email =  new SimpleMailMessage();
		
		email.setTo("rteixeira@adas.es");
		email.setFrom("rteixeira@adas.es");
		email.setSubject(correo.getAsunto());
		
		String mensaje = "Este correo ha sido enviado desde la vista de colaboracion por: \n";
		mensaje += "Nombre: "+ correo.getNombre() +" "+ correo.getApellidos() +"\n";
		mensaje += "Correo: "+ correo.getCorreo() +"\n";
		mensaje += "Asunto: "+ correo.getAsunto() +"\n";
		mensaje += "Mensaje: "+ correo.getMensaje();
		
		email.setText(mensaje);
		
		mail.send(email);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha enviado correctamente el correo.");
		
		return "redirect:/colaboracion";
	}
}
