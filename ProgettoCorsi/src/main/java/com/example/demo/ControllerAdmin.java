package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	
}