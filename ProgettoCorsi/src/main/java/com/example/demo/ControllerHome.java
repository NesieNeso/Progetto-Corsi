package com.example.demo;

import javax.servlet.http.HttpServletRequest;

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
	private boolean flagError = false;
	
	@RequestMapping("/")
	public String homepage(HttpServletRequest request) {
		String reg = request.getParameter("registrazione");
		if(flagError) {
			System.out.println("errore");
			return "home/Homepage";
		}else {
			System.out.println("ok: " + reg);
			if(reg == null) {
				/*SIAMO IN LOGIN*/

				System.out.println("Login");
				return "home/Homepage";
			}else {
				/*SIAMO IN Registrazione*/
				try {
					
					/**
					 *  -> 
					 * 
					 */
					
					System.out.println("registrazione");
					return "redirect:/HomeAdmin";
				}catch (Exception e) {
					flagError = true;
					return "redirect: home/Homepage";
				}
			}
		
		}	
		
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
