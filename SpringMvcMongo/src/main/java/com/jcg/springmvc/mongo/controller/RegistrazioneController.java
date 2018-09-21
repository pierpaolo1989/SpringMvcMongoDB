package com.jcg.springmvc.mongo.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RegistrazioneController {
	
	private static Logger log = Logger.getLogger(RegistrazioneController.class);

	// Displaying the initial books list.
	@RequestMapping(value = "/registrati", method = RequestMethod.GET)
	public String getPersons(Model model) {
		log.debug("Request of rendering registrazione page");
		return "registrazione";
	}

}
