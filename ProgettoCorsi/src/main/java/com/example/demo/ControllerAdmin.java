package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerAdmin {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping("/HomeAdmin/allUsers")
	public String allUsers() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showAll();
		return "/Admin/HomeAdmin";
	}

	@PostMapping("/Admin/HomeAdmin/onlyUsers")
	public String onlyUsers() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showUsers();
		
		return "/Admin/HomeAdmin";
	}
	@PostMapping("/Admin/HomeAdmin/onlyAdmin")
	public String onlyAdmins() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		g.showAdmin();
		
		return "/Admin/HomeAdmin";
	}
	


}