package controlador;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

import modelo.*;


//Gestiona el carrito de compras, incluyendo la adición de productos, el resumen de la compra y el registro de ventas.
public class GestorCarrito {
    private static List<ItemCarrito> items = new ArrayList<>();
    private static final String ARCHIVO_VENTAS = "ventas.txt";
//Añade un producto al carrito de compras.
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
    //Muestra un resumen de la compra, con posibles descuentos aplicados.
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
    //Registra la compra actual en el archivo de ventas y vacía el carrito.
    public static String realizarCompra() {
        Map<String, Map<String, Integer>> ventasPorCategoria = new HashMap<>();

        // Leer el archivo existente
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
            String linea;
            String categoriaActual = null;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith("    ")) {
                    // Línea de categoría
                    String[] partes = linea.split(": ");
                    categoriaActual = partes[0];
                    int cantidadCategoria = Integer.parseInt(partes[1].trim());
                    ventasPorCategoria.putIfAbsent(categoriaActual, new HashMap<>());
                    ventasPorCategoria.get(categoriaActual).put(categoriaActual, cantidadCategoria);
                } else {
                    // Línea de producto
                    String[] partes = linea.trim().split(": ");
                    String nombreProducto = partes[0];
                    int cantidadProducto = Integer.parseInt(partes[1].trim());
                    ventasPorCategoria.get(categoriaActual).put(nombreProducto, cantidadProducto);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de ventas no encontrado.");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al leer el archivo de ventas.";
        }

        // Actualizar el archivo con los productos del carrito
        for (ItemCarrito item : items) {
            String categoria = item.getProducto().getCategoria();
            String nombreProducto = item.getProducto().getNombre();
            int cantidadComprada = item.getCantidad();

            ventasPorCategoria.putIfAbsent(categoria, new HashMap<>());
            Map<String, Integer> productosEnCategoria = ventasPorCategoria.get(categoria);
            
            // Actualizar cantidad de la categoría
            productosEnCategoria.put(categoria, productosEnCategoria.getOrDefault(categoria, 0) + cantidadComprada);

            // Actualizar cantidad del producto
            productosEnCategoria.put(nombreProducto, productosEnCategoria.getOrDefault(nombreProducto, 0) + cantidadComprada);
        }

        // Guardar cambios
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_VENTAS))) {
            for (String categoria : ventasPorCategoria.keySet()) {
                Map<String, Integer> productosEnCategoria = ventasPorCategoria.get(categoria);

                // Escribir la categoría con su cantidad total
                int totalCategoria = productosEnCategoria.getOrDefault(categoria, 0);
                pw.println(categoria + ": " + totalCategoria);

                // Escribir los productos con sus cantidades
                for (Map.Entry<String, Integer> entry : productosEnCategoria.entrySet()) {
                    String nombreProducto = entry.getKey();
                    if (!nombreProducto.equals(categoria)) {
                        int cantidadProducto = entry.getValue();
                        pw.println("    " + nombreProducto + ": " + cantidadProducto);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al escribir en el archivo de ventas.";
        }

        // Limpiamos el carrito
        items.clear();

        return "Compra realizada.";
    }
}

