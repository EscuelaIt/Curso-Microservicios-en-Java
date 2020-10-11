package es.edu.escuela_it.microservices.controllers;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.edu.escuela_it.microservices.configurations.ConfigurationApp;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HolaMundoRest {

	// private final Logger log = LoggerFactory.getLogger(HolaMundoRest.class);

	@Autowired
	private ConfigurationApp appConf;

	@GetMapping("/holaMundo/{name}")
	public String saludo(@PathVariable String name) {
		
		MDC.put("userid", name);

		for (int i = 0; i < 100; i++) {

			log.trace("Ejecutando hola mundo trace");
			log.debug("Ejecutando hola mundo debug");
			log.info("Ejecutando hola mundo info");
			log.warn("Ejecutando hola mundo warn");
			log.error("Ejecutando hola mundo error");
		}
		return "Hola Mundo Servicio Rest Java " + appConf;
	}

}
