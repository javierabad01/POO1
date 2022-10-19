package VendingCo.VendingSystem;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Test;

import VendingCo.Product.Product;
import VendingCo.VendingMachine.Linea;
import VendingCo.VendingMachine.VendingMachine;

public class VendingSystemTest {

	@Test
	public void testVendingSystemCorrecto() {
		new VendingSystem();
	}

	@Test
	public void testAnnadirMaquinaCorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		s1.annadirMaquina(m1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAnnadirMaquinaYaExistente() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(0,true,2,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
	}
	
	@Test
	public void testEliminarMaquinaCorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, true, 3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		s1.eliminarMaquina(0);
		s1.eliminarMaquina(1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testEliminarMaquinaYaEliminada() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1,true,3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		s1.eliminarMaquina(0);
		s1.eliminarMaquina(0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testEliminarMaquinaConSistemaVacio() {
		VendingSystem s1 = new VendingSystem();
		s1.eliminarMaquina(0);
	}
	
	
	@Test
	public void testMaquinasOperativas() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, false, 3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		assertEquals(s1.maquinasOperativas(),1);
	}

	@Test
	public void testGetMaquinas() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, false, 3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		ArrayList<VendingMachine> maquinas= new ArrayList<>();
		maquinas.add(m1);
		maquinas.add(m2);
		
		assertEquals(s1.getMaquinas(),maquinas);	
	}
	
	@Test
	public void testGetMaquinasOperativasCorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, false, 3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		ArrayList<VendingMachine> maquinasOperativas = new ArrayList<>();
		maquinasOperativas.add(m1);
		assertEquals(s1.getMaquinasOperativas(),maquinasOperativas);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetMaquinasOperativasIncorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,false,2,20);
		VendingMachine m2 = new VendingMachine(1, false, 3,20);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		ArrayList<VendingMachine> maquinasOperativas = new ArrayList<>();
		maquinasOperativas.add(m1);
		maquinasOperativas.add(m2);
		s1.getMaquinasOperativas();
	}

	@Test
	public void testGetMaquinasLineaVaciaCorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, true, 1,20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "16/08/2021";
		LocalDate fecha = LocalDate.parse(date,formatter);
		Product p1 = new  Product("Patatas", fecha, "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,0);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		m2.nuevaLinea(l1);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		
		ArrayList<VendingMachine> maquinasLineaVacias = new ArrayList<>();
		maquinasLineaVacias.add(m1);
		assertEquals(s1.getMaquinasLineaVacia(),maquinasLineaVacias);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetMaquinasLineaVaciaIncorrecto() {
		VendingSystem s1 = new VendingSystem();
		VendingMachine m1 = new VendingMachine(0,true,2,20);
		VendingMachine m2 = new VendingMachine(1, true, 1,20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "16/08/2021";
		LocalDate fecha = LocalDate.parse(date,formatter);
		Product p1 = new  Product("Patatas", fecha, "823880024474", 8);
		Linea l1 = new Linea(0,p1,0,6);
		Linea l2 = new Linea(1,p1,2,6);
		m1.nuevaLinea(l1);
		m1.nuevaLinea(l2);
		m2.nuevaLinea(l1);
		s1.annadirMaquina(m1);
		s1.annadirMaquina(m2);
		
		ArrayList<VendingMachine> maquinasLineaVacias = new ArrayList<>();
		maquinasLineaVacias.add(m1);
		maquinasLineaVacias.add(m2);
		s1.getMaquinasLineaVacia();
		}

}
