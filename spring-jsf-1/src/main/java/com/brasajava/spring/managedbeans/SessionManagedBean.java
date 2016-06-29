package com.brasajava.spring.managedbeans;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionManagedBean {
	
	private static final Logger log = LoggerFactory.getLogger(SessionManagedBean.class);
	private Locale locale;
	private String textLocale = "en";
	public static final Locale DEFAULT_LOCALE = new Locale("en");

	@PostConstruct
	public void init() {
		log.info("Method init setting locale = " + FacesContext.getCurrentInstance().getViewRoot().getLocale() + ". Geted from the browser");
		this.setLocale(new Locale("en"));//FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
	public void test(){
		System.out.println(" TEST METHOD and the textLocale is: " + textLocale + " and the locale is: " + locale.getLanguage());
	}

	public String getTextLocale() {
		return textLocale;
	}

	public void setTextLocale(String textLocale) {
		this.textLocale = textLocale;
		try{
			setLocale(new Locale(textLocale));
		}catch( Exception ex){
			setLocale(DEFAULT_LOCALE);
			ex.printStackTrace();
		}
	}

}
