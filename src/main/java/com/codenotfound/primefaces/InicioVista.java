package com.codenotfound.primefaces;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class InicioVista {
	private String firstName = "John";
	private String lastName = "Doe";

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String showGreeting() {
		if (firstName.equals("FPDual") && lastName.equals("1234")) {
			return "Logado correctamente " + firstName + "!";
		} else {
			return "Credenciales no validas...";
		}
	}
}
