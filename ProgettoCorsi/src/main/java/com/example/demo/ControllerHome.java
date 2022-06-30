package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerHome {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@RequestMapping("/")
	public String homepage() {
		
		return "home/Homepage";
	}
			

	//Decide che pagina visualizzare, se admin o user
	@PostMapping("/redirector")
	public String redirector(@RequestParam("email") String email, @RequestParam("Password") String password, ModelMap modelmap) {
		modelmap.put("email", email);
		modelmap.put("password", password);
		
		/*
		 * int utenteID = GestisciUtenti.getIdFromUserPassword(email, password);
		 * 
		 */
		
		if(/*utenteID==0*/true) {
			return "Admin/HomeAdmin";
		}
		else {
			return "Utenti/HomeUtenti";
		}
	}
	
	@RequestMapping("/HomeAdmin")
	public String homeAdmin() {
		return "Admin/HomeAdmin";
	}

	
}
