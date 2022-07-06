package gestorePref;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class configuraBean {

	private ConfigGeographical geo;
	private ConfigStyle style;
	

	@Bean(name = "Scuro")
	public ConfigStyle getStyleOne() {
		style = new ConfigStyle();
		style.setBackground("blue");
		style.setColor("Green");

		return style;
	}
	
	@Bean(name = "Chiaro")
	public ConfigStyle getStyleTwo() {
		style = new ConfigStyle();
		style.setBackground("red");
		style.setColor("black");
		
		return style;
	}
	

	@Bean(name = "Francese")
	public ConfigGeographical getFrancia() {
		geo = new ConfigGeographical("", "");
		geo.setLingua("Francese");
		geo.setValuta("Euro");
		geo.setSimbolo("€");
		
		return geo;
	}
	
	@Bean(name = "Italiano")
	public ConfigGeographical getItalia() {
		geo = new ConfigGeographical("", "");
		geo.setLingua("Italia");
		geo.setValuta("Euro");
		geo.setSimbolo("€");
		
		return geo;
	}
	

	@Bean(name = "StatiUniti")
	public ConfigGeographical getStatiUniti() {
		geo = new ConfigGeographical("", "");
		geo.setLingua("Americano");
		geo.setValuta("Dollaro");
		geo.setSimbolo("$");
		
		return geo;
	}
	
	
	
}
