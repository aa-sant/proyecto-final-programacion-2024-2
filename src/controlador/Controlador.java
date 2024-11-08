package controlador;

import vista.*;

public class Controlador {
	GUI gui;
	
	public Controlador() {
		gui = new GUI(this);
	}
	
	public String iniciarSesion(String identificacion) {
		return GestorUsuarios.iniciarSesion(identificacion);
	}
	
	public String registrarUsuario(String identificacion, String nombre) {
		return GestorUsuarios.registrarUsuario(identificacion, nombre);
	}
}
