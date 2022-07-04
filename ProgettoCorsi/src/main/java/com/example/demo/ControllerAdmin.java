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
	
	@PostMapping("/Admin/HomeAdmin/allUsers")
	public String allUsers(HttpServletRequest req) {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showAll();
		
		HttpSession ses = req.getSession();
		ses.setAttribute("risultato", g.showAll());

		return "/Admin/HomeAdmin";
	}

	@PostMapping("/Admin/HomeAdmin/onlyUsers")
	public String onlyUsers(HttpServletRequest req) {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showUsers();
		
		HttpSession ses = req.getSession();
		ses.setAttribute("risultato", g.showAll());
		
		return "/Admin/HomeAdmin";
	}
	@PostMapping("/Admin/HomeAdmin/onlyAdmin")
	public String onlyAdmins(HttpServletRequest req) {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showAdmin();

		HttpSession ses = req.getSession();
		ses.setAttribute("risultato", g.showAll());
		
		
		return "/Admin/HomeAdmin";
	}
	


}