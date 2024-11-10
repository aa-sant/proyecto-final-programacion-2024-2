package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.*;
import vista.*;

//Controlador principal que maneja la lógica de la aplicación.
//Gestiona usuarios, productos y el carrito de compras.

public class Controlador {
	GUI gui;
	Usuario usuarioLogueado;
	//Inicializa el controlador y la interfaz gráfica.
	public Controlador() {
		gui = new GUI(this);
	}
	
	//Inicia sesión para el usuario dado su identificación.
    
    // @param identificacion Identificación del usuario.
    // @return Mensaje de éxito o error.
	public String iniciarSesion(String identificacion) {
		Usuario usuario = GestorUsuarios.iniciarSesion(identificacion);
		
		if (usuario == null) {
			return "Usuario desconocido, regístrese.";
		} else {
			usuarioLogueado = usuario;
			return "Sesión iniciada correctamente.";
		}
	}
	
	//Registra un nuevo usuario en el sistema.
	
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
	
	//Añade un producto al carrito de compras.
	public String anadirProductoAlCarrito(String itemProducto, int cantidad) {
		Producto producto = GestorProductos.obtenerProductoPorItem(itemProducto);
		
		if (producto == null) return "Producto no encontrado.";
		
		return GestorCarrito.anadirProductoAlCarrito(producto, cantidad);
	}
	//Muestra el resumen de la compra actual.
	public String mostrarResumenCompra(){
		return GestorCarrito.mostrarResumenCompra(usuarioLogueado.isAfiliado());
	}
	//Realiza la compra de los productos en el carrito.
	public String realizarCompra() {
		return GestorCarrito.realizarCompra();
	}
}
