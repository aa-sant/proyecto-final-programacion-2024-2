package modelo;
//Representa un producto con su categoría, nombre y precio.
public class Producto {
	private String categoria;
	private String nombre;
	private float precio;
	//Crea un producto con la categoría, nombre y precio especificados.
	public Producto(String categoria, String nombre, float precio) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
	}
//Obtiene la categoría del producto.
	public String getCategoria() {
		return categoria;
	}
//Establece la categoría del producto.
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
//Obtiene el nombre del producto.
	public String getNombre() {
		return nombre;
	}
//Establece el nombre del producto.
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//Obtiene el precio del producto.
	public float getPrecio() {
		return precio;
	}
//Establece el precio del producto.
	public void setPrecio(float precio) {
		this.precio = precio;
	}
//Representa el producto como un string de lista con nombre y precio.
	public String toListItem() {
		return nombre + ". Precio: $" + precio;
	}
}
