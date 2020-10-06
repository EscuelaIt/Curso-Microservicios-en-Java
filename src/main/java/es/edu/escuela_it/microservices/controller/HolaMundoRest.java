package es.edu.escuela_it.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.edu.escuela_it.microservices.configuration.ConfigurationApp;

@RestController
public class HolaMundoRest {
	
	private Logger log = LoggerFactory.getLogger(HolaMundoRest.class);
	
	@Autowired
	private ConfigurationApp appConf;

	@GetMapping("/holaMundo")
	public String saludo() {
		
		log.trace("Ejecutando hola mundo trace");
		log.debug("Ejecutando hola mundo debug");
		log.info("Ejecutando hola mundo info");
		log.warn("Ejecutando hola mundo warn");
		log.error("Ejecutando hola mundo error");
		
		return "Hola Mundo Servicio Rest Java " + appConf;
	}

}
