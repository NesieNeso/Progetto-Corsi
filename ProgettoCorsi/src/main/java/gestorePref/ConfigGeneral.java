package gestorePref;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigGeneral {
	ConfigStyle configStyle;
	ConfigGeographical configGeographical;
	
	@Autowired
	public void setConfigStyle(ConfigStyle configStyle) {
		this.configStyle = configStyle;
	}
	
	@Autowired
	public void setConfigGeographical(ConfigGeographical configGeographical) {
		this.configGeographical = configGeographical;
	}
	
	@Override
	public String toString() {
		return(configStyle.toString() + "\n" + configGeographical.toString() );
	}
	
}
