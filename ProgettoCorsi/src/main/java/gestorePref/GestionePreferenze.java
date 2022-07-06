package gestorePref;

import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Element;

public class GestionePreferenze {
	
	private JdbcTemplate connect;

	
	public GestionePreferenze() {

	}
	

	public String getLingua() {
		// TODO Auto-generated method stub
		return query("select l.locale"
				+ "from localizzazione l"
				+ "join utenti u on u.id_locale = l.id_locale"
				+ "where u.idUtente = 1");
	}


	public String getValuta() {
		// TODO Auto-generated method stub
		return query("");
	}


	public String getBackground() {
		// TODO Auto-generated method stub
		return "base"  /*query("")*/;
	}


	public String getFontFamily() {
		// TODO Auto-generated method stub
		return "base" /*query("")*/;
	}


	public String getColor() {
		return "blue"/*query("")*/;
	}
	

	public String getSizeFamily() {
		return "blue"/*query("")*/;
	}

	
	public String query(String sql){
		 return connect.queryForObject(sql, String.class);
		
	}


}
