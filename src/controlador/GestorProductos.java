package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class GestorProductos {
    private static final String FILE_PATH = "productos.txt";

    public static List<Producto> obtenerProductosPorCategoria(String nombreCategoria) {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length == 3) {
                    String categoria = partes[0].trim();
                    String nombre = partes[1].trim();
                    float precio = Float.parseFloat(partes[2].trim());

                    if (categoria.equalsIgnoreCase(nombreCategoria)) {
                        productos.add(new Producto(categoria, nombre, precio));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los productos: " + e.getMessage());
        }

        return productos;
    }
}
