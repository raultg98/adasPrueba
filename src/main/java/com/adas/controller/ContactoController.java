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
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	private JavaMailSender mail;

	@GetMapping("")
	public String renderContacto(Model model) {
		CorreoModel correo = new CorreoModel();
		
		model.addAttribute("correoObj", correo);
		
		return "individualViews/contacto.html";
	}
	
	@PostMapping("")
	public String sendEmail(@Validated CorreoModel correo,
							RedirectAttributes flash, 
							Model model) {
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo("rteixeira@adas.es");
		email.setFrom("rteixeira@adas.es");
		email.setSubject(correo.getAsunto());
//		email.setText(correo.getMensaje());
		
		String mensaje = "Nombre: "+ correo.getNombre() +" "+ correo.getApellidos() +"\n";
		mensaje += "Correo del usuario: "+ correo.getCorreo() +"\n";
		mensaje += "Mensaje: "+ correo.getMensaje();
		
		email.setText(mensaje);
		
		mail.send(email);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("message", "Se ha enviado el correo correctamente");
		
		return "redirect:/contacto";
	}
}
