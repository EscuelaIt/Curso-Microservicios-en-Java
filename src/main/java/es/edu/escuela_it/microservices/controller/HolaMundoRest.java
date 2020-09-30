package es.edu.escuela_it.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.edu.escuela_it.microservices.configuration.ConfigurationApp;

@RestController
public class HolaMundoRest {
	
	@Autowired
	private ConfigurationApp appConf;

	@GetMapping("/holaMundo")
	public String saludo() {
		
		return "Hola Mundo Servicio Rest Java " + appConf;
	}

}
