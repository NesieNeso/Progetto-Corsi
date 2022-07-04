package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerIscritto {
	

	@PostMapping("/iscrizioneNuovoCorso") 
	public String inserisciRegistrazione(@RequestParam("idutente") String idUtente,@RequestParam("idcorso") String idCorso, ModelMap modelmap) { 
		modelmap.put("idutente", idUtente); 
		modelmap.put("idcorso", idCorso);

		String sql = "INSERT INTO iscritto (id_corso, id_utente, pagina_attuale) VALUES (?, ?, ?)";
		//int result = jdbcTemplate.update(sql,idCorso, idUtente, 0);
		
		//if (result > 0){ System.out.println("Riga inserita."); }
		return "Utenti/HomeUtenti"; 
	} 
}
