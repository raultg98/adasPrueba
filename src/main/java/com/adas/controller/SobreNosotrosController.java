package com.adas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sobreNosotros")
public class SobreNosotrosController {

	@GetMapping("/quienesSomos")
	public String showQuienesSomos() {
		return "sobreNosotros/quienesSomos.html";
	}
	
	@GetMapping("/estatutos")
	public String showEstatutos() {
		return "sobreNosotros/estatutos.html";
	}
	
	
}
