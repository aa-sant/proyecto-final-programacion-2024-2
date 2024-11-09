package controlador;

import java.util.*;
import java.text.DecimalFormat;

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
    
    public static String mostrarResumenCompra(boolean esAfiliado) {
        Map<String, List<ItemCarrito>> productosPorCategoria = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#,###");

        // Agrupamos los productos por categoría
        for (ItemCarrito item : items) {
            productosPorCategoria
                .computeIfAbsent(item.getProducto().getCategoria(), k -> new ArrayList<>())
                .add(item);
        }
        
        float valorTotalCompra = 0;
        for (String categoria : productosPorCategoria.keySet()) {
        	List<ItemCarrito> productos = productosPorCategoria.get(categoria);
        	float valorTotalCategoria = 0;

        	for (ItemCarrito item : productos) {
        		valorTotalCategoria += item.getProducto().getPrecio() * item.getCantidad();
        	}

        	valorTotalCompra += valorTotalCategoria;
        }
                
        float valorFinalCompra = 0;
        StringBuilder resumen = new StringBuilder();
        for (String categoria : productosPorCategoria.keySet()) {
            List<ItemCarrito> productos = productosPorCategoria.get(categoria);
            float valorTotalCategoria = 0;

            resumen.append(categoria).append(":\n");

            for (ItemCarrito item : productos) {
                float valorTotalProducto = item.getProducto().getPrecio() * item.getCantidad();
                valorTotalCategoria += valorTotalProducto;
                resumen.append(item.getProducto().getNombre()).append(" x").append(item.getCantidad()).append("\n")
                       .append("Valor Unitario: $").append(df.format(item.getProducto().getPrecio())).append("\n")
                       .append("Valor Total: $").append(df.format(valorTotalProducto)).append("\n\n");
            }

            if (!esAfiliado && ConfiguracionDescuentos.CATEGORIAS_DESCUENTO_NO_AFILIADO.contains(categoria)
                    && valorTotalCompra > ConfiguracionDescuentos.VALOR_COMPRA_MINIMA_DESCUENTO_NO_AFILIADOS) {
                float descuentoCategoria = valorTotalCategoria * ConfiguracionDescuentos.PORCENTAJE_DESCUENTO_NO_AFILIADOS / 100;
                valorTotalCategoria -= descuentoCategoria;
                resumen.append("Descuento del ").append(ConfiguracionDescuentos.PORCENTAJE_DESCUENTO_NO_AFILIADOS)
                       .append("% Aplicado: -$").append(df.format(descuentoCategoria)).append("\n\n");
            }

            valorFinalCompra += valorTotalCategoria;
        }

        if (esAfiliado) {
            float descuentoAfiliado = valorTotalCompra * ConfiguracionDescuentos.PORCENTAJE_DESCUENTO_AFILIADOS / 100;
            valorTotalCompra -= descuentoAfiliado;
            resumen.append("Descuento del ").append(ConfiguracionDescuentos.PORCENTAJE_DESCUENTO_AFILIADOS)
                   .append("% Aplicado: -$").append(df.format(descuentoAfiliado)).append("\n");
        }

        resumen.append("Valor Total de Compra: $").append(df.format(valorFinalCompra)).append("\n");

        return resumen.toString();
    }

    public static List<ItemCarrito> getItems() {
        return items;
    }
}

