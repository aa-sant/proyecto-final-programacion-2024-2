package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.*;

public class GestorCarrito {
    private static List<ItemCarrito> items = new ArrayList<>();

    public static String anadirProductoAlCarrito(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())) {
                // Si el producto ya está en el carrito, actualizamos la cantidad
                int nuevaCantidad = item.getCantidad() + cantidad;
                
                items.remove(item);
                items.add(new ItemCarrito(producto, nuevaCantidad));
                
                return "El producto especificado ya estaba dentro del carrito, hemos actualizado la cantidad.";
            }
        }
        
        // Si no está en el carrito, lo añadimos
        items.add(new ItemCarrito(producto, cantidad));
        return "Producto añadido correctamente al carrito.";
    }

    public static List<ItemCarrito> getItems() {
        return items;
    }
}

