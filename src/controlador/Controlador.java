package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.*;
import vista.*;

public class Controlador {
	GUI gui;
	
	public Controlador() {
		gui = new GUI(this);
	}
	
	// Gestión Usuarios
	public String iniciarSesion(String identificacion) {
		return GestorUsuarios.iniciarSesion(identificacion);
	}
	
	public String registrarUsuario(String identificacion, String nombre) {
		return GestorUsuarios.registrarUsuario(identificacion, nombre);
	}
	
	// Gestión Productos
	public List<String> obtenerProductosPorCategoria(String nombreCategoria){
		List<Producto> productos = GestorProductos.obtenerProductosPorCategoria(nombreCategoria);
		
        List<String> stringProductos = new ArrayList<>();
        for (Producto producto : productos) {
        	stringProductos.add(producto.toListItem());
        }
        
        return stringProductos;
	}
	
	public String anadirProductoAlCarrito(String itemProducto, int cantidad) {
		Producto producto = GestorProductos.obtenerProductoPorItem(itemProducto);
		
		if (producto == null) return "Producto no encontrado.";
		
		return GestorCarrito.anadirProductoAlCarrito(producto, cantidad);
	}
}
