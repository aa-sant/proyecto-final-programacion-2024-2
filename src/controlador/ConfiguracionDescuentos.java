package controlador;

import java.util.Arrays;
import java.util.List;

//Configura los descuentos aplicables para clientes afiliados y no afiliados.
//Define categorías de descuento, porcentajes y compra mínima.

public class ConfiguracionDescuentos {
	//Categorías de productos con descuento para no afiliados.
	
	public static final List<String> CATEGORIAS_DESCUENTO_NO_AFILIADO = Arrays.asList("BEBIDAS", "ASEO");
	//Porcentaje de descuento para afiliados.
	public static final int PORCENTAJE_DESCUENTO_AFILIADOS = 5;
	//Porcentaje de descuento para no afiliados.
	public static final int PORCENTAJE_DESCUENTO_NO_AFILIADOS = 5;
	//Valor mínimo de compra para descuento de los no afiliados.
	public static final float VALOR_COMPRA_MINIMA_DESCUENTO_NO_AFILIADOS = 80000;
}
