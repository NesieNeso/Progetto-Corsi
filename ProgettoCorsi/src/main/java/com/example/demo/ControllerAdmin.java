package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerAdmin {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/HomeAdmin")
	public String homeAdmin() {
		return "Admin/HomeAdmin";
	}
	
	@PostMapping("/Admin/HomeAdmin/allUsers")
	public String allUsers(HttpServletRequest req) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		g.showAll();
		
		HttpSession sesAll = req.getSession();
		sesAll.setAttribute("risultato", g.showAll());

		return "/Admin/HomeAdmin";
	}

	@PostMapping("/Admin/HomeAdmin/onlyUsers")
	public String onlyUsers(HttpServletRequest req) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		g.showUsers();
		
		HttpSession sesUser = req.getSession();
		sesUser.setAttribute("risultato", g.showUsers());
		
		return "/Admin/HomeAdmin";
	}
	@PostMapping("/Admin/HomeAdmin/onlyAdmin")
	public String onlyAdmins(HttpServletRequest req) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		g.showAdmin();

		HttpSession sesAdmin = req.getSession();
		sesAdmin.setAttribute("risultato", g.showAdmin());
		
		
		return "/Admin/HomeAdmin";
	}
	
	@PostMapping("/Admin/HomeAdmin/clearResults")
	public String clearResults(HttpServletRequest req) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		g.clear();
		
		HttpSession sesClear = req.getSession();
		sesClear.setAttribute("risultato", g.clear());
		
		return "/Admin/HomeAdmin";
	}
	
	@PostMapping("/Admin/HomeAdmin/onlyBanned")
	public String onlyBanned(HttpServletRequest req) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		g.showBanned();
		
		HttpSession sesClear = req.getSession();
		sesClear.setAttribute("risultato", g.showBanned());
		
		return "/Admin/HomeAdmin";
	}

	@PostMapping("/Admin/HomeAdmin/banner")
	public String banning(HttpServletRequest req, @RequestParam("mailDaBannare") String email, ModelMap modelmap) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		
		HttpSession sesBan = req.getSession();
		
		System.out.println("email field: " + email);
		
		int check = g.check(email);
		
		switch(check) {
		case -1:
			sesBan.setAttribute("risultato", "Non esiste l'utente che vuoi bannare");
			break;
		case 1:
			sesBan.setAttribute("risultato", "L'utente esiste, ma non ha un ruolo");
			break;
		case 2:
			sesBan.setAttribute("risultato", "L'utente esiste, è un utente");
			int flag = g.ban(email);
			if(flag>0)
				sesBan.setAttribute("risultato", "L'utente esiste, è un utente ed è stato bannato");
			break;
		case 3:
			sesBan.setAttribute("risultato", "L'utente esiste, è un admin");
			break;
		case 4:
			sesBan.setAttribute("risultato", "L'utente esiste, è già bannato");
			break;
		default:
			sesBan.setAttribute("risultato", "ERRORE");
			break;
		}
		return "/Admin/HomeAdmin";
	}
	
	@PostMapping("/Admin/HomeAdmin/sbanner")
	public String sbanning(HttpServletRequest req, @RequestParam("mailDaSbannare") String email, ModelMap modelmap) {
		GestisciAdmin g = new GestisciAdmin(jdbcTemplate);
		
		HttpSession sesBan = req.getSession();
		
		System.out.println("email field: " + email);
		
		int check = g.check(email);
		
		switch(check) {
		case -1:
			sesBan.setAttribute("risultato", "Non esiste l'utente che vuoi bannare");
			break;
		case 1:
			sesBan.setAttribute("risultato", "L'utente esiste, ma non ha un ruolo");
			break;
		case 2:
			sesBan.setAttribute("risultato", "L'utente esiste, è un utente");
			break;
		case 3:
			sesBan.setAttribute("risultato", "L'utente esiste, è un admin");
			break;
		case 4:
			sesBan.setAttribute("risultato", "L'utente esiste, è già bannato");
			int flag = g.sban(email);
			if(flag>0)
				sesBan.setAttribute("risultato", "L'utente esiste, era bannato ed è stato sbannato");
			break;
		default:
			sesBan.setAttribute("risultato", "ERRORE");
			break;
		}
		return "/Admin/HomeAdmin";
	}
}