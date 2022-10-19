package VendingCo.Product;

import java.time.LocalDate;

/**
 * Clase en la que se almacena toda la información relativa a un producto
 * 
 * @author javabad
 * @author alemina
 *
 */
public class Product {
	
	private String nombre ;
	private LocalDate fechaCaducidad;
	private String upc;
	private double precio;
	
	/**
	 * Inicializa una instancia de la clase Producto con los valores dados como argumento
	 * @param nombre - Es el nombre del producto
	 * @param fecha - Es la fecha de caducidad del producto
	 * @param UPC - Es el codigo identificador del producto
	 * @param precio - Es el precio del producto
	 * @throws IllegalArgumentException si {@code UPC.length() != 12}
	 * @throws IllegalArgumentException si {@code c < '0' || c > '9' siendo c UPC.charAt(i)}
	 * @throws IllegalArgumentException si {@code UPC.charAt(12)!=d siendo d el digito de control}
	 * @throws IllegalArgumentException si {@code precio <=0}}
	 */
	public Product (String nombre, LocalDate fechaCaducidad, String upc, double precio) {
		comprobarCodigo(upc);
		this.nombre = nombre;		
		this.fechaCaducidad = fechaCaducidad;
		this.upc = upc;
		comprobarPrecio(precio);
		this.precio = precio;
	}
	

	/**
	 * Comprueba que el codigo dado es correcto
	 * @param UPC - Es el codigo identificador del producto

	 */
	private static void comprobarCodigo(String upc) {
		if (upc.length() != 12)
			throw new IllegalArgumentException("Codigo erroneo: debe tener 12 numeros");
		for (int i = 0; i < 12; i++) {
			char c = upc.charAt(i);
			if (c < '0' || c > '9')
				throw new IllegalArgumentException("Codigo erroneo: el numero de serie tiene que ser digitos");
		}
		if (!comprobacionDigitoUpc(upc))
			throw new IllegalArgumentException("Codigo erroneo: el ultimo digito no es correcto.");
		
	}

	/**
	 * Comprueba si el ultimo digito de UPC es el correcto
	 * @param UPC - Es el codigo identificador del producto
	 * @return true si el ultimo digito de upc es correcto, false si no lo es
	 */
	private static boolean comprobacionDigitoUpc(String upc) {
		boolean correcto=true;
		int s=0;
		for (int x=0; x<10; x+=2) {
			s+=(upc.charAt(x)-'0')*3;
			s+=(upc.charAt(x+1)-'0')*1;
		}
		s+=(upc.charAt(10)-'0')*3;
		int m = 0;
		m= (int) (10*(Math.round((double)s/10)));
		int d = Math.abs(s-m);
		
		int ultimoDigito = upc.charAt(11)-'0';
		if (ultimoDigito!=d)
			correcto = false;
		
		return correcto;
	}
	/**
	 * Comprueba si el precio es correcto
	 * @param precio - Es el numero que contiene el precio del producto
	 */
	private static void comprobarPrecio(double precio){
		if (precio <=0) {
			throw new IllegalArgumentException("Codigo erroneo: el precio no puede ser 0 o negativo");
		}
	}
	/**
	 * Comprueba si el producto esta caducado
	 * @param fechaActual - Es la fecha que se toma como actual para comparar con la de caducidad
	 * @return true si el producto está caducado, false si no lo está
	 */
	public boolean estaCaducadoRespectoA (LocalDate fechaActual) {
		boolean caducado=false;
		if ((fechaCaducidad.getYear()<fechaActual.getYear())||(fechaCaducidad.getYear()==fechaActual.getYear())&&
				(fechaCaducidad.getMonthValue()<fechaActual.getMonthValue())||
				(fechaCaducidad.getMonthValue()==fechaActual.getMonthValue()) && 
				fechaCaducidad.getDayOfMonth()<fechaActual.getDayOfMonth()){
			caducado= true;
		}
		
		return caducado;
	}
	/**
	 * Devuelve el valor precio
	 * @return precio - El precio del producto
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * Devuelve el valor del nombre del producto
	 * @return nombre - Cadena con el nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Devuelve la fecha de caducidad
	 * @return fecha - Fecha de caducidad
	 */
	public LocalDate getFecha() {
		return fechaCaducidad;
	}
	/**
	 * Devuelve el codigo del producto
	 * @return UPC - Código de identificación del producto
	 */
	public String getUPC() {
		return upc;
	}
	/**
	 * Indica si dos productos son iguales o no
	 * @param p1 - Producto para comparar
	 * @return boolean true si son los dos productos iguales
	 */
	public boolean igual(Product p1){
			 return this.nombre.equals(p1.nombre) && this.precio==p1.precio && this.upc.equals(upc);
	 }	
}
