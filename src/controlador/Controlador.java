package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.*;
import vista.*;

public class Controlador {
	GUI gui;
	Usuario usuarioLogueado;
	
	public Controlador() {
		gui = new GUI(this);
	}
	
	// Gestión Usuarios
	public String iniciarSesion(String identificacion) {
		Usuario usuario = GestorUsuarios.iniciarSesion(identificacion);
		
		if (usuario == null) {
			return "Usuario desconocido, regístrese.";
		} else {
			usuarioLogueado = usuario;
			return "Sesión iniciada correctamente.";
		}
	}
	
	public String registrarUsuario(String identificacion, String nombre) {
		Usuario usuario = GestorUsuarios.registrarUsuario(identificacion, nombre);
		
		if (usuario == null) {
			return "Identificación inválida.";
		} else {
			usuarioLogueado = usuario;
			return "Usuario registrado correctamente.";
		}
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
	
	public String mostrarResumenCompra(){
		return GestorCarrito.mostrarResumenCompra(usuarioLogueado.isAfiliado());
	}
}
