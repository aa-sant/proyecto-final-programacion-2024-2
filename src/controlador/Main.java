package controlador;

import javax.swing.SwingUtilities;

import vista.GUI;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI());
		
	}
}
