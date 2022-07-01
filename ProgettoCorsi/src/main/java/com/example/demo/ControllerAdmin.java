package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerAdmin {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("Admin/HomeAdmin/allUsers")
	public String allUsers() {
		GestisciUtenti g = new GestisciUtenti(jdbcTemplate);
		//g.showAll();
		System.out.println("ciao");
		return "redirect:Admin/HomeAdmin";
	}
	
	

}
