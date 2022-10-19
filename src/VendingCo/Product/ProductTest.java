package VendingCo.Product;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class ProductTest {
	
	String upcValido="823880024474";
	String nombre="Patatas";
	String date = "16/08/2021";
	String formatoFecha="d/MM/yyyy";
	

	@Test(expected=IllegalArgumentException.class)
	public void testupcLogitudNull() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoFecha);
		LocalDate fecha = LocalDate.parse(date,formatter);
		String upc = "";
		double precio = 7.5;
		new  Product(nombre, fecha, upc, precio);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testupcLogitudErroneaCorta() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		String upc="12345678911";
		double precio = 7.5;
		new Product(nombre, fecha, upc, precio);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testupcLogitudLarga() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		String upc="1234567891234";
		double precio = 7.5;
		
		new  Product(nombre, fecha, upc, precio);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testupcConLetras() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);		
		String upc="SajdieJIoalF";
		double precio = 7.5;
		new  Product(nombre, fecha, upc, precio);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testupcUltimoDigitoIncorrecto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);		
		String upc="823880024473";
		double precio = 7.5;
		new  Product(nombre, fecha, upc, precio);
	}
	
	
	@Test
	public void testupcLongitudCorrecta() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);		
		
		double precio = 7.5;
		new  Product(nombre, fecha, upcValido, precio);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrecioIgualCero() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);		
		
		double precio = 0;
		new  Product(nombre, fecha, upcValido, precio);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrecioIgualNegativo() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);		
		
		double precio = -1;
		new  Product(nombre, fecha, upcValido, precio);
	}
	
	@Test
	public void testNoEstaCaducado() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);

		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date1 = "16/08/2021";
		LocalDate fecha1 = LocalDate.parse(date1,formatter1);
		
		assertEquals(p1.estaCaducadoRespectoA(fecha1),false);
	}
	
	@Test
	public void testEstaCaducadoDias() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date1 = "17/08/2021";
		LocalDate fecha1 = LocalDate.parse(date1,formatter1);
		
		assertEquals(p1.estaCaducadoRespectoA(fecha1),true);
	}
	
	@Test
	public void testEstaCaducadoMeses() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date1 = "16/09/2021";
		LocalDate fecha1 = LocalDate.parse(date1,formatter1);
		
		assertEquals(p1.estaCaducadoRespectoA(fecha1),true);
	}
	@Test
	public void testEstaCaducadoAnnos() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fecha = LocalDate.parse(date,formatter);
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date1 = "16/08/2022";
		LocalDate fecha1 = LocalDate.parse(date1,formatter1);
		
		assertEquals(p1.estaCaducadoRespectoA(fecha1),true);
	}


	@Test
	public void testGetPrecio() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		
		double delta=0.0;
		assertEquals(p1.getPrecio(),precio,delta);
	}

	@Test
	public void testGetNombre() {
		
		double precio = 10;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		assertEquals(p1.getNombre(),nombre);
	}

	@Test
	public void testGetFecha() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		assertEquals(p1.getFecha(),fecha);
	}

	@Test
	public void testGetCodigoProducto() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);

		assertEquals(p1.getUPC(),upcValido);
	}
	
	@Test
	public void testProductoIgualProducto() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(date,formatter);
		
		double precio = 10;
		Product p1 = new  Product(nombre, fecha, upcValido, precio);
		Product p2 = new  Product(nombre, fecha, upcValido, precio);

		assertEquals(p1.igual(p2),true);
	}
	@Test
	public void testProductoNoIgualProducto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		String date1 = "16/09/2021";
		LocalDate fecha = LocalDate.parse(date,formatter);
		LocalDate fecha1 = LocalDate.parse(date1,formatter);

		Product p1 = new  Product("Patatas", fecha,upcValido, 5);
		Product p2 = new  Product("Agua", fecha1,upcValido, 4);

		assertEquals(p1.igual(p2),false);
	}

}
