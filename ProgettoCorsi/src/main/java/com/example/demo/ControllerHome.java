package com.example.demo;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gestorePref.ConfigGeneral;
import gestorePref.ConfigGeographical;
import gestorePref.ConfigStyle;
import gestorePref.configuraBean;

@Controller
@RequestMapping("/")
public class ControllerHome {

		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private GestisciUtenti gu;
	
	private boolean flagReg;
	
	private String bnt;

	private String lingua;
	private String stile;
	
	private ConfigStyle geo;
	private AnnotationConfigApplicationContext aCAC ;
	
	private ArrayList<String> errorString;
	private int indexLingua;
	private HttpSession ses;
	

	/*Pagina iniziale*/
	@RequestMapping(value = {"", " "})
	public String getHomepage(HttpServletRequest request) {
		System.out.println("Pagina iniziale");
		
		
		errorString =  new ArrayList<String>();
		aCAC = new AnnotationConfigApplicationContext();
		aCAC.register(configuraBean.class);
		aCAC.refresh();
		
		ses = request.getSession();
		
		flagReg = false;
		
		ses.setAttribute("visible", "block");

		ses.setAttribute("invisible", "none");
		ses.setAttribute("class", "bntstrReg");

		
		if(lingua == null) {
			lingua = "Italiano";
			ses.setAttribute("lingua", lingua);
		}
		if(stile == null) {
			stile = "Scuro";
			ses.setAttribute("stile", stile);
		}
		
		geo = (ConfigStyle) aCAC.getBean(stile);
		
		strRegLog(request);
		setLingua();
		setPagina();
		clearError();
		
		
		return "home/LoginPage";

	}
	@RequestMapping(value = {"base"})
	public String getHomepageBase(HttpServletRequest request) {
		return "home/LoginPage";
	}
	
	/*GESTIONE ERRORI */
	@RequestMapping(value = {"ErrLog"})
	public String getErrorLogin(HttpServletRequest request) {
		System.out.println("Pagina errore Login");

		ses = request.getSession();
		error(errorString.get(3));
		return "home/LoginPage";

	}
	
	@RequestMapping(value = {"ErrReg"})
	public String getError(HttpServletRequest request) {
		System.out.println("Pagina errore Registrazione");

		ses = request.getSession();
		error(errorString.get(2));
		return "home/LoginPage";
	}
	
	@RequestMapping(value = {"ErrBan"})
	public String getBan(HttpServletRequest request) {
		System.out.println("Pagina errore Ban");
		
		ses = request.getSession();
		error(errorString.get(4));
		return "home/LoginPage";
	}
	
	@RequestMapping(value = {"ErrLen"})
	public String getErrLen(HttpServletRequest request) {
		System.out.println("Pagina errore Lunghezza");
		//error(errorString.get(5));
		return "home/LoginPage";
	}
	
	
	private void error(String text) {
		clearError();
		System.out.println("[system] set error: " + text + ", ses: " +(ses!=null));
		ses.setAttribute("errorLogin", text);
	}
	
	private void clearError() {
		System.out.println("[system] Clear error");
		
		ses.setAttribute("errorLogin", "");
		ses.setAttribute("errorPass", "");
		ses.setAttribute("errorEmail", "");
	}

	//Gestisce registrazione e login
	/*
	@PostMapping("/redirector")
	public String redirector() {
		
		
		ses = request.getSession();
		bnt = request.getParameter("bnt");	

		if(email.length()==0 || password.length() == 0) 
			return erroreLunghezza(email, password);

		

		if(bnt.equals("Login")) {
			 return login(email, password, modelmap);
			

		}else if(bnt.equals("StartReg")){		
			return visReg(request);
		}else {
			return registrazione(email, password);
			
		}
	
	}
*/
	

	
	
	
	@PostMapping("registrazione")
	private String registrazione(HttpServletRequest request, @RequestParam("email") String email,  
									@RequestParam("password") String password,  @RequestParam("cognome") String cognome, 
									@RequestParam("nome") String nome, ModelMap modelmap) {
		try {
			gu = new GestisciUtenti(jdbcTemplate);
			if(email.length() == 0 || password.length() ==0 || cognome.length() == 0 || nome.length() ==0)
				return erroreLunghezza(email, password, cognome, nome);
			if(gu.insert(email, password) > 0) 
				return "home/LoginPage";
		}catch (Exception e) {e.printStackTrace();}
		
		return "redirect:/ErrReg";
	}

	@PostMapping("/login")
	private String login(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
		gu = new GestisciUtenti(jdbcTemplate);
		ses = request.getSession();
		List<String> tmp = gu.getIdFromUserPassword(email, password);
		
		System.out.println("["+email + "], [" + password + "] -> " + (email.length() ==0 || password.length() == 0));
		if(email.length() ==0 || password.length() == 0) 
			return erroreLunghezza(email, password);

		if(tmp.size() == 2) {
			/*SONO UN UTENTE*/
			if(tmp.get(1).equals("user")) { 
				ses.setAttribute("id", tmp.get(0));
			
				setModelMap(modelmap, email, password);
				
				return "Utenti/HomeUtenti";
			/*Sono bannato*/
			}else if(tmp.get(1).equals("ban")) {
				
				return "redirect:/ErrBan";
				
			/* Sono un Admin*/
			}else 
				return "Admin/HomeAdmin";
			/*C'è stato un errore*/
		}else{
			return "redirect:/ErrLog";
		}
	}

	
	private String erroreLunghezza(String password, String email) {
		clearError();

		if(email.length() == 0 )

			ses.setAttribute("errorEmail", errorString.get(0));

		if(password.length() == 0)
			ses.setAttribute("errorPass", errorString.get(1));


		return "redirect:/ErrLen";
	}

	private String erroreLunghezza(String password, String email, String nome, String cognome) {
		clearError();

		if(email.length() == 0 )

			ses.setAttribute("errorEmail", errorString.get(0));

		if(password.length() == 0)
			ses.setAttribute("errorPass", errorString.get(1));

		if(nome.length() == 0)
			ses.setAttribute("errorNome", errorString.get(6));

		if(cognome.length() == 0)
			ses.setAttribute("errorCogn", errorString.get(7));

		return "redirect:/ErrLen";
	}

	private void setModelMap(ModelMap modelmap, String email, String password) {
		modelmap.put("email", email);
		modelmap.put("password", password);
		modelmap.put("username", gu.getUsername(email, password));
		modelmap.put("corsi", gu.getLinkIscrizioneCorso(email, password));
		modelmap.put("corsiNuovi", gu.getLinkNuoviCorsi(email, password));
		
	}



	

	/**/
	@PostMapping("/changeLing")
	public String changeLing(HttpServletRequest request) {
			
		ses = request.getSession();
		lingua =(lingua.equals("Italiano"))? "Francese": "Italiano"; 
		

		ses.setAttribute("lingua", lingua);
		
		setLingua();
		
		AnnotationConfigApplicationContext aCAC = new AnnotationConfigApplicationContext();
		aCAC.register(configuraBean.class);
		aCAC.refresh();
		
		ConfigGeographical geof = (ConfigGeographical) aCAC.getBean(lingua);
		
		return "redirect:/";
		
	}
	
	@PostMapping("/changeColor")
	public String changeColor(HttpServletRequest request) {
			
		ses = request.getSession();
		stile =(stile.equals("Scuro"))? "Chiaro": "Scuro"; 
		

		ses.setAttribute("stile", stile);
		
		geo = (ConfigStyle) aCAC.getBean(stile);
		
		return "redirect:/";
		
	}
	
	@PostMapping("/strReg")
	public String strRegLog(HttpServletRequest request) {
		
		ses = request.getSession();
		ses.setAttribute("stile", stile);


		
		if(flagReg) {		
			System.out.println("log -> reg : "+flagReg);
			ses.setAttribute("visible", "none");
			ses.setAttribute("invisible", "block");

			ses.setAttribute("bntshadow", "box-shadow: inset 5px 3px 16px 0px grey");
			 
		}else {
			System.out.println("reg -> log: "+flagReg);
			ses.setAttribute("visible", "block");
			ses.setAttribute("invisible", "none");
			ses.setAttribute("bntshadow", "box-shadow: 3px 3px 3px 3px grey;");

		}

		flagReg = !flagReg; 
		return "redirect:/base";
	}
	

	
	private void setPagina() {
		ses.setAttribute("color", geo.getBackground());
	}

	private void setLingua() {
		indexLingua= 0;
		if(lingua.equals("Italiano"))
			indexLingua = 0;
		else
			indexLingua = 1;
		
		errorString.removeAll(errorString);
		
		String[] testi = {"Corsi  Assolutamente Ottimi", "Des cours absolument géniaux"};
		String[][] label = {{"e-mail","e-mail"},
							{"password","le mot de passe"},
							{"nome","Prénom"},
							{"Cognome","nom"}};
		
		 String[][] errors = {{"non hai inserito un e-mail","vous n'avez pas saisi d'e-mail"},
							{"non hai inserito una password","vous n'avez pas saisi de mot de passe"},
							{"utente già registrato","Utilisateur déjà enregistré"},
							{"password o e-mail sbagliati","mauvais mot de passe ou e-mail"},
							{"sei stato bannato","Vous avez été banni"},
							{"non hai inserito almeno un campo","vous n'avez pas renseigné au moins un champ"}};

		
		for(int i = 0; i != errors.length; i++) 
			errorString.add(errors[i][indexLingua]);
		
		
		String[][] bnt = 	{{"cambia lingua","changer de langue"},
							{"cambia colore","changer de couleur"},
							{"registrati ora","S'inscrire maintenant"},
							{"registrati","s'identifier"},
							{"login","connexion"}};
		
		/* PlaceHolder */
		ses.setAttribute("inputEmail", label[0][indexLingua]);
		ses.setAttribute("inputPassword", label[1][indexLingua]);
		ses.setAttribute("inputNome", label[2][indexLingua]);
		ses.setAttribute("inputCognome", label[3][indexLingua]);
		
		/* Bnt  */
		ses.setAttribute("bntLingua", bnt[0][indexLingua]);
		ses.setAttribute("bntColore", bnt[1][indexLingua]);
		ses.setAttribute("bntRegiNo", bnt[2][indexLingua]);
		ses.setAttribute("bntRegist", bnt[3][indexLingua]);
		ses.setAttribute("bntLogin", bnt[4][indexLingua]);
		
		ses.setAttribute("testoTitolo", testi[indexLingua]);
		
		
		
	
	}
	
	
	
	
}
