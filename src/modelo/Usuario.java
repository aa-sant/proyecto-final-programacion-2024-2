package modelo;

public class Usuario {
	private String id;
	private String nombre;
	private boolean afiliado;
	
	public Usuario(String id, String nombre, boolean afiliado) {
		this.id = id;
		this.nombre = nombre;
		this.afiliado = afiliado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isAfiliado() {
		return afiliado;
	}

	public void setAfiliado(boolean afiliado) {
		this.afiliado = afiliado;
	}
}
