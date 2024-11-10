package modelo;

//Representa un ítem en el carrito de compras, incluyendo el producto y su cantidad.

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    //Crea un nuevo ítem en el carrito con el producto y la cantidad especificados.
    
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
//Obtiene el producto del ítem.
	public Producto getProducto() {
		return producto;
	}
//Establece el producto del ítem.
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
//Obtiene la cantidad del producto en el ítem.
	public int getCantidad() {
		return cantidad;
	}
//Establece la cantidad del producto en el ítem.
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
