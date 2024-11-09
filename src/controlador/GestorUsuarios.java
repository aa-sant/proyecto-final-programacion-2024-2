package controlador;

import java.io.*;
import java.util.*;

import modelo.Usuario;

public class GestorUsuarios {

    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    private static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            writer.write(usuario.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    private static List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                usuarios.add(Usuario.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer los usuarios: " + e.getMessage());
        }
        
        return usuarios;
    }

    public static Usuario iniciarSesion(String id) {
        List<Usuario> usuarios = leerUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        
        return null;
    }
    
    public static Usuario registrarUsuario(String id, String nombre) {
    	List<Usuario> usuarios = leerUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return null;
            }
        }
        
        Usuario nuevoUsuario = new Usuario(id, nombre, false);
        guardarUsuario(nuevoUsuario);
        
        return nuevoUsuario;
    }
}
 