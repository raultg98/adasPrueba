package com.adas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicios")
public class Servicios {

//	@GetMapping("/talleres")
//	public String showtalleres() {
//		return "servicios/talleres.html";
//	}
	
	@GetMapping("/programas")
	public String showProgramas() {
		return "servicios/programas.html";
	}
	
	@GetMapping("/manuales")
	public String showManuales() {
		return "servicios/manuales.html";
	}
	
	@GetMapping("/idi")
	public String showIDI() {
		return "servicios/idi.html";
	}
}
