package modelo;

//Representa un ítem en el carrito de compras, incluyendo el producto y su cantidad.

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    //Crea un nuevo ítem en el carrito con el producto y la cantidad especificados.
    /* 
    * @param producto Producto a añadir.
    * @param cantidad Cantidad del producto.
    */
    
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
//Obtiene el producto del ítem.
    /* 
    * @return Producto del ítem.
    */
	public Producto getProducto() {
		return producto;
	}
//Establece el producto del ítem.
	/* 
    * @param producto Nuevo producto del ítem.
    */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
//Obtiene la cantidad del producto en el ítem.
	/* 
    * @return Cantidad del producto.
    */
	public int getCantidad() {
		return cantidad;
	}
//Establece la cantidad del producto en el ítem.
	/* 
    * @param cantidad Nueva cantidad del producto.
    */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
