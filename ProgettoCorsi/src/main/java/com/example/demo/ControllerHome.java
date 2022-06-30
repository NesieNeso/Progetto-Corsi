package com.example.demo;

import java.util.List;

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
		
		String email, password;
		
		
		String reg = request.getParameter("registrazione");
		if(flagError) {
			System.out.println("errore");
			return "home/Homepage";
		}else {
			System.out.println("ok: " + reg);
			if(reg == null) {

				
				System.out.println("Login");
				return "home/Homepage";
			}else {
				/*SIAMO IN Registrazione*/
				try {
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
	public String redirector(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
		GestisciUtenti gu = new GestisciUtenti(jdbcTemplate);
		modelmap.put("email", email);
		modelmap.put("password", password);
		List<String> tmp = gu.getIdFromUserPassword(email, password);
		if(tmp.size() == 2) {
			if(tmp.get(1).equals("user")) {
				return "Utenti/HomeUtenti";
			}
			else {
				return "Admin/HomeAdmin";
			}
		}
		return "/";
	
	}
	
	@RequestMapping("/HomeAdmin")
	public String homeAdmin() {
		return "Admin/HomeAdmin";
	}
	
	
	
}
