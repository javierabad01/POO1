package VendingCo.VendingMachine;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import VendingCo.Product.Product;

public class LineaTest {
	
	private LocalDate crearFechaPorDefecto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "16/08/2021";
		return LocalDate.parse(date,formatter);
	}
	String upcValido="823880024474";
	String nombre="Patatas";

	@Test(expected=IllegalArgumentException.class)
	public void testIdentificadorLineaNegativo() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=-1;
		double precioExtra=10;
		int cantidad=10;
		new Linea (identificadorLinea,p1,precioExtra,cantidad);
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrecioExtraNegativo() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=-1;
		int cantidad=10;
		new Linea (identificadorLinea,p1,precioExtra,cantidad);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCantidadNegativa() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=-1;
		new Linea (identificadorLinea,p1,precioExtra,cantidad);
		
	}
	@Test
	public void testCantidadIdentificadorPrecioExtraCero() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=0;
		int cantidad=0;
		new Linea (identificadorLinea,p1,precioExtra,cantidad);
		
	}
	@Test
	public void testLineaCorrecta() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=3;
		double precioExtra=10;
		int cantidad=5;
		new Linea (identificadorLinea,p1,precioExtra,cantidad);
		
	}
	
	
	@Test
	public void testGetIdentificadorLinea() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		assertEquals(l1.getIdentificadorLinea(),0);
	}

	@Test
	public void testGetProducto() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		assertEquals(l1.getProducto(),p1);
	}

	@Test
	public void testGetPrecio() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		double delta=0.0;
		assertEquals(l1.getPrecio(),p1.getPrecio()+precioExtra,delta);
	}

	@Test
	public void testLineaEstaVacia() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=0;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		assertEquals(l1.estaVacia(l1),true);
	}
	@Test
	public void testLineaNoEstaVacia() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		assertEquals(l1.estaVacia(l1),false);
	}
	@Test
	public void testGetCantidad() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		assertEquals(l1.getCantidad(),5);
	}
	@Test 
	public void testCantidadExtraCorrecto() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		l1.annadirCantidadExtra(5);
		assertEquals(l1.getCantidad(),10);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testCantidadExtraIncorrecto() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		l1.annadirCantidadExtra(-1);
	}
	@Test 
	public void testCantidadExtraCorrectoCero() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		l1.annadirCantidadExtra(0);
		assertEquals(l1.getCantidad(),5);
	}
	
	@Test 
	public void testSacarUno() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		l1.sacarUno();
		assertEquals(l1.getCantidad(),4);
	}
	@Test 
	public void testVaciarLinea() {
		Product p1 = new  Product(nombre, crearFechaPorDefecto(), upcValido, 10);
		
		int identificadorLinea=0;
		double precioExtra=10;
		int cantidad=5;
		Linea l1 = new Linea (identificadorLinea,p1,precioExtra,cantidad);
		l1.vaciarLinea();
		assertEquals(l1.getCantidad(),0);
	}
}
