package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import gestioneConnessione.Connessione;

@SpringBootApplication
public class ProgettoCorsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCorsiApplication.class, args);
		
		/*
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		Connessione cn = (Connessione)context.getBean("dataSource");
		
		System.out.println(cn.getPassword());
		

		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		DataSource dataSource = (DataSource) context.getBean("configBean");
		

		JdbcTemplate cb = cn.getTemp();
		*/
	}

}
