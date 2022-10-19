package VendingCo.VendingMachine;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fabricante.externo.tarjetas.TarjetaMonedero;

import org.junit.Test;

import VendingCo.Product.Product;

public class VendingMachineTest {

	private LocalDate crearFechaPorDefecto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "16/08/2021";
		LocalDate fecha = LocalDate.parse(date,formatter);
		return fecha;
	}
	@Test(expected=IllegalArgumentException.class)
	public void testIdentificadorNegativo() {
		int identificadorMaquina=-1;
		boolean estado = true;
		int numeroLineas=2;
		int cantidadMax=20;
		
		new VendingMachine(identificadorMaquina,estado,numeroLineas,cantidadMax);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNumeroLineaNegativo() {
		int identificadorMaquina=1;
		boolean estado = true;
		int numeroLineas=0;
		int cantidadMax=20;
		
		new VendingMachine(identificadorMaquina,estado,numeroLineas,cantidadMax);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCantidadMaxMal() {
		int identificadorMaquina=1;
		boolean estado = true;
		int numeroLineas=2;
		int cantidadMax=0;
		
		new VendingMachine(identificadorMaquina,estado,numeroLineas,cantidadMax);
	}
	
	@Test
	public void testVendingMachineCorrecto() {
		
		new VendingMachine(0,true,2,20);
	}
	
	@Test
	public void testAnnadirLineaCorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,3);
		
		m1.nuevaLinea(l1);
	}
	@Test
	public void testGetNumeroLineas() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		assertEquals(m1.getNumeroLineas(),2);
		}
	@Test(expected=IllegalArgumentException.class)
	public void testAnnadirLineaYaExistente() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,3);
		Linea l2 = new Linea(0,p1,7,5);
		
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAnnadirLineasOcupadas() {
		VendingMachine m1 = new VendingMachine(0,true,1,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,3);
		Linea l2 = new Linea(1,p1,7,5);
		
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
	}
	
	@Test
	public void testQuitarLineaCorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,3);
		Linea l2 = new Linea(1,p1,7,5);
		
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.quitarLinea(0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testQuitarLineaIdentificadorIncorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,3);
		Linea l2 = new Linea(1,p1,7,5);
		
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.quitarLinea(2);
	}
	

	@Test
	public void testMeterProductos() {
VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("patatas", crearFechaPorDefecto(), "823880024474", 10);
		Linea l1 = new Linea(0,p1,5,2);
		Linea l2 = new Linea(1,p1,7,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.meterProductos(0, 18);
		assertEquals(m1.getLineasMaquina().get(0).getCantidad(),20);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testMeterProductosIdentificadorIncorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,5,2);
		Linea l2 = new Linea(1,p1,7,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.meterProductos(2, 4);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testMeterProductosCantidadMaximaSuperada() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,5,2);
		Linea l2 = new Linea(1,p1,7,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.meterProductos(0, 19);
	}
	@Test
	public void testActualizarEstadoLleno() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,5,2);
		Linea l2 = new Linea(1,p1,7,5);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		m1.actualizarEstado();
		
		assertEquals(m1.getEstado(),true);
	}
	@Test
	public void testActualizarEstadoVacio() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,5,0);
		Linea l2 = new Linea(1,p1,7,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		m1.actualizarEstado();
		
		assertEquals(m1.getEstado(),false);
	}
	
	@Test
	public void testQuePrecioCorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,3);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		double delta=0.0;
		assertEquals(m1.quePrecio(0),8,delta);
		assertEquals(m1.quePrecio(1),10,delta);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testQuePrecioIdentificadorIncorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,3);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		m1.quePrecio(2);
	}
	
	@Test
	public void testGetLineasVaciasCero() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);

		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,3);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		assertEquals(m1.getLineasVacias(),0);
	}
	@Test
	public void testGetLineasVacias() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		assertEquals(m1.getLineasVacias(),1);
	}
	@Test
	public void testGetIdentificadorMaquina() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		assertEquals(m1.getIdentificadorMaquina(),0);
	}
	@Test
	public void testSacarCantidadCorrecta() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",20.0);
		
		m1.sacarCantidad(0, visa);
		
		double delta=0.0;
		assertEquals(m1.getLineasMaquina().get(0).getCantidad(),5);
		assertEquals(visa.getSaldoActual(),10,delta);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSacarCantidadIdentificadorIncorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",2000.0);
		
		m1.sacarCantidad(2, visa);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSacarCantidadDemasiada() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,0);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",2000.0);
		
		m1.sacarCantidad(0, visa);
	}
	@Test
	public void testSacarCantidadJusta() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,1);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",2000.0);
		
		m1.sacarCantidad(0, visa);   
		double delta=0.0;
		assertEquals(m1.getLineasMaquina().get(0).getCantidad(),0);
		assertEquals(visa.getSaldoActual(),1990,delta);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSacarCantidadDineroInsuficiente() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",9.0);
		
		m1.sacarCantidad(0, visa);
	}
	@Test
	public void testSacarCantidadDineroJusto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",10.0);
		
		m1.sacarCantidad(0, visa);
		double delta=0.0;
		assertEquals(m1.getLineasMaquina().get(0).getCantidad(),5);
		assertEquals(visa.getSaldoActual(),0,delta);
	}
	@Test
	public void testRellenarFilaCorrecta() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.rellenarLinea(0);
		m1.rellenarLinea(1);
		assertEquals(m1.getLineasMaquina().get(0).getCantidad(),20);
		assertEquals(m1.getLineasMaquina().get(1).getCantidad(),20);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testRellenarFilaIdentificadorIncorrecto() {
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		
		Product p1 = new  Product("Patatas", crearFechaPorDefecto(), "823880024474", 5);
		Linea l1 = new Linea(0,p1,5,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		
		m1.rellenarLinea(2);
	}

}
