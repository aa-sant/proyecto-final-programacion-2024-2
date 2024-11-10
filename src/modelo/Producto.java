package modelo;
//Representa un producto con su categoría, nombre y precio.
public class Producto {
	private String categoria;
	private String nombre;
	private float precio;
	//Crea un producto con la categoría, nombre y precio especificados.
	/* 
    * @param categoria Categoría del producto.
    * @param nombre    Nombre del producto.
    * @param precio    Precio del producto.
    */
	public Producto(String categoria, String nombre, float precio) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
	}
//Obtiene la categoría del producto.
	/* 
    * @return Categoría del producto.
    */
	public String getCategoria() {
		return categoria;
	}
//Establece la categoría del producto.
	/* 
    * @param categoria Nueva categoría del producto.
    */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
//Obtiene el nombre del producto.
	/* 
    * @return Nombre del producto.
    */
	public String getNombre() {
		return nombre;
	}
//Establece el nombre del producto.
	/* 
    * @param nombre Nuevo nombre del producto.
    */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//Obtiene el precio del producto.
	/* 
    * @return Precio del producto.
    */
	public float getPrecio() {
		return precio;
	}
//Establece el precio del producto.
	/* 
    * @param precio Nuevo precio del producto.
    */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
//Representa el producto como un string de lista con nombre y precio.
	/* 
    * @return String que representa el producto.
    */
	public String toListItem() {
		return nombre + ". Precio: $" + precio;
	}
}
