package com.daisyit.controller;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "accessService", eager = true)
@ViewScoped
public class AccessService {
	@PostConstruct
	public void init() {
		
	}
	
	public String createUser() {
		String result = null;
		result = "register.xhtml";
		return result;
	}
	

}
