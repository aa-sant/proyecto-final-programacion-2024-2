package modelo;

public class Usuario {
	private String id;
	private String nombre;
	private boolean afiliado;
	
	//* Crea un usuario con la identificación, nombre y estado de afiliación especificados.
    /* 
    * @param id       Identificación del usuario.
    * @param nombre   Nombre del usuario.
    * @param afiliado Estado de afiliación del usuario.
    */
	
	public Usuario(String id, String nombre, boolean afiliado) {
		this.id = id;
		this.nombre = nombre;
		this.afiliado = afiliado;
	}

	/**
     * Obtiene la identificación del usuario.
     * 
     * @return Identificación del usuario.
     */
	public String getId() {
		return id;
	}
	/**
     * Establece la identificación del usuario.
     * 
     * @param id Nueva identificación del usuario.
     */
	public void setId(String id) {
		this.id = id;
	}
	/**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario.
     */
	public String getNombre() {
		return nombre;
	}
	/**
     * Establece el nombre del usuario.
     * 
     * @param nombre Nuevo nombre del usuario.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
     * Obtiene el estado de afiliación del usuario.
     * 
     * @return Verdadero si el usuario está afiliado, falso en caso contrario.
     */
	public boolean isAfiliado() {
		return afiliado;
	}
	/**
     * Establece el estado de afiliación del usuario.
     * 
     * @param afiliado Nuevo estado de afiliación del usuario.
     */
	public void setAfiliado(boolean afiliado) {
		this.afiliado = afiliado;
	}
	/**
     * Representa al usuario como un string con su ID, nombre y estado de afiliación.
     * 
     * @return String representando al usuario.
     */
	@Override
    public String toString() {
        return id + "," + nombre + "," + afiliado;
    }
	/**
     * Crea un usuario a partir de una cadena de texto con formato adecuado.
     * 
     * @param line Cadena de texto que representa un usuario.
     * @return Usuario creado a partir de la cadena.
     */
    public static Usuario fromString(String line) {
        String[] infoUsuario = line.split(",");
        
        String id = infoUsuario[0];
        String nombre = infoUsuario[1];
        boolean afiliado = Boolean.parseBoolean(infoUsuario[2]);
        
        return new Usuario(id, nombre, afiliado);
    }
}
