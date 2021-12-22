package eredua.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;

public class Authentication {

	BLFacade facadeBL;
	private String izena;
	private String pasahitza;

	public Authentication() {
		facadeBL = FacadeBean.getBusinessLogic();
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String egiaztatu() {
		boolean valid = facadeBL.validateLogin(izena, pasahitza);
		if (valid) {
			return "ok";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Wrong user or password"));
			logout();
			return "error";
		}
		
	}
	
	public String register() {
		if (pasahitza.matches("(\\S){8,}")) {
			boolean valid = facadeBL.validateRegister(izena, pasahitza);
			if (valid) {
				return "ok";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						 new FacesMessage("The user already exists"));
				logout();
				return "error";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Invalid password"));
			logout();
			return "error";
		}
		
		
	}
	public void logout() {
		izena = null;
		pasahitza = null;
	}
}
