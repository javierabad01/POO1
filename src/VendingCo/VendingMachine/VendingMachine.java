package VendingCo.VendingMachine;

import java.util.ArrayList;
import fabricante.externo.tarjetas.TarjetaMonedero;


/**
 * Clase que almacena todos los datos y operaciones relativas a las maquinas vending
 * 
 * @author javabad
 * @author alemina
 *
 */
public class VendingMachine {
	private int identificadorMaquina;
	private boolean estado;
	private ArrayList<Linea> lineasMaquina;
	private int numeroLineas;
	private int cantidadMax;
	
	/**
	 * Constructor de una maquina expendedora nueva, 
	 * @param identificadorMaquina - Entero con el identificador de la maquina.
	 * @param estado - Booleano con el estado de la maquina (true operativo, false no operativo)
	 * @param numeroLineas - Es el numero de lineas maximo que puede haber en la maquina
	 * @param cantidadMax - Es la cantidad maxima que puede haber de un producto en una linea
	 * @throws IllegalArgumentException {@code identificadorMaquina<0} 
	 * @throws IllegalArgumentException {@code numeroLineas<1} 
	 * @throws IllegalArgumentException si {@code cantidadMax<1} 
	 */
	public VendingMachine (int identificadorMaquina, boolean estado,int numeroLineas, int cantidadMax) {
		comprobarIdentificador(identificadorMaquina);
		comprobarNumeroLineas(numeroLineas);
		comprobarCantidadMaxima(cantidadMax);
		this.identificadorMaquina=identificadorMaquina;
		this.estado=estado;
		this.numeroLineas=numeroLineas;
		this.lineasMaquina = new ArrayList<> (numeroLineas);
		this.cantidadMax=cantidadMax;
	}
	/**
	 * Comprueba que el identificador no sea negativo
	 * @param identificadorMaquina - Entero con el identificador de la maquina.
	 */
	private static void comprobarIdentificador(int identificadorMaquina) {
		if (identificadorMaquina<0) {
			throw new IllegalArgumentException("Codigo erroneo: El identificador no puede ser negativo");
		}
		
	}
	/**
	 * Comprueba que el numero de lineas sea mayor que cero
	 * @param numeroLineas - Entero con el numero de lineas
	 */
	private static void comprobarNumeroLineas(int numeroLineas) {
		if (numeroLineas<1) {
			throw new IllegalArgumentException("Codigo erroneo: Tiene que existir al menos una linea");
		}
		
	}
	/**
	 * Comprueba que cantidad maxima sea mayor que cero
	 * @param cantidadMax - Entero con la cantidad maxima
	 */
	private static void comprobarCantidadMaxima(int cantidadMax) {
		if (cantidadMax<1) {
			throw new IllegalArgumentException("Codigo erroneo: La cantidad maxima tiene que ser mayor que 0");
		}
		
	}
	/**
	 * Permite añadir una nueva linea si no existe ya.
	 * @param l1 - Linea que se quiere añadir
	 * @throws IllegalArgumentException {@code lineasMaquina.get(i).getIdentificadorLinea()==l1.getIdentificadorLinea()} 
	 * @throws IllegalArgumentException {@code lineasMaquina.size()== getNumeroLineas() -1}
	 */
	public void nuevaLinea(Linea l1) {
		for (int i=0;i<lineasMaquina.size();i++) {
			if (lineasMaquina.get(i).getIdentificadorLinea()==l1.getIdentificadorLinea()) {
				throw new IllegalArgumentException("Codigo erroneo: Ya existe una linea con ese identificador");
			}
		}
		if (lineasMaquina.size()== getNumeroLineas()) {
			throw new IllegalArgumentException("Codigo erroneo: Ya no quedan lineas disponibles");
		}
		
		lineasMaquina.add(l1);
	}
	/**
	 * Permite eliminar una linea a traves de un identificador
	 * @param identificador - Entero con el identificador de la linea
	 * @throws IllegalArgumentException si {@code quito==false}
	 */
	public void quitarLinea(int identificador) {
		boolean quito=false;
		for(int i=0;i<lineasMaquina.size();i++)  {
			if (lineasMaquina.get(i).getIdentificadorLinea()==identificador) {
				lineasMaquina.remove(i);
				quito=true;
			}
		}
		if (!quito) {
			throw new IllegalArgumentException("Codigo erroneo: El identificador no existe");
		}
	}
	/**
	 * Introducir productos a la linea correspondiente
	 * @param identificador - Identificador de la linea en la que se introducirán los productos
	 * @param cantidadProducto - Entero con la cantidad de productos a introducir
	 * @throws IllegalArgumentException si {@code j==i} 
	 * @throws IllegalArgumentException si {@code (lineasMaquina.get(i).getCantidad()+cantidadProducto)>cantidadMax}
	 */
	public void meterProductos(int identificador,int cantidadProducto) {
		boolean lleno=false;
		for (int i=0;i<lineasMaquina.size();i++)  {
			if (lineasMaquina.get(i).getIdentificadorLinea()==identificador) {
				if ((lineasMaquina.get(i).getCantidad()+cantidadProducto)>getCantidadMax()) {
					throw new IllegalArgumentException("Codigo erroneo: No caben tantos productos");
				}
				lineasMaquina.get(i).setCantidad(lineasMaquina.get(i).getCantidad()+cantidadProducto);
				lleno=true;
			}
		}	
		if (!lleno) {
			throw new IllegalArgumentException("Codigo erroneo: no se encontro la linea con ese identificador");
		}
	}
	/**
	 * Devuelve el estado de la maquina
	 * @return estado - true si esta operativa, false si no lo está
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * Actualiza el estado de la maquina, el estado sera false (no disponible) si todas las lineas estan vacias
	 * y true si hay alguna linea que no este vacia.
	 */
	public void actualizarEstado() {
		int i=0;
		boolean vacia=true;
		while(i<lineasMaquina.size() && vacia) {
			setEstado(false);
			if(lineasMaquina.get(i).getCantidad()!=0) {
				vacia=false;
				setEstado(true);
			}
			i++;
		}
	}
	/**
	 * Permite cambiar el estado de la maquina
	 * @param estado - Booleano con el estado de la maquina (true operativo, false no operativo)
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * Devuelve cual es el precio del producto de la linea con identificador dado
	 * @param identificador - Entero con el identicador de la linea
	 * @return precio - Es el precio del producto asociado a la linea
	 * @throws IllegalArgumentException si {@code precio=0}
	 */
	public double quePrecio(int identificador) {
		double precio=0;
		for(int i=0;i<lineasMaquina.size();i++) {
			if(lineasMaquina.get(i).getIdentificadorLinea()==identificador) {
				precio=lineasMaquina.get(i).getPrecio();
			}
		}
		if (precio==0) {
			throw new IllegalArgumentException("Codigo erroneo: no se ha encontrado una linea con ese identificador");
		}
		return precio;
	}
	
	
	/**
	 * Sacar de la maquina la cantidad del producto que se diga
	 * @param identificador - Entero con el identificador de una linea
	 * @param tarjeta - Es una tarjeta de tipo TarjetaMonedero con saldo
	 * @throws IllegalArgumentException si {@code noEncontrado} 
	 * @throws IllegalArgumentException si {@code lineasMaquina.get(i).getCantidad()-cantidad<0} 
	 * @throws IllegalArgumentException si {@code tarjeta.getSaldoActual()-lineasMaquina.get(i).getPrecio()*cantidad<0}
	 */
	public void sacarCantidad(int identificador, TarjetaMonedero tarjeta) {
		int i=0;
		boolean noEncontrado=true;
		while (i<lineasMaquina.size()&& noEncontrado) {
			if(lineasMaquina.get(i).getIdentificadorLinea()==identificador) {
				noEncontrado=false;
			}
			i++;
		}
		if (noEncontrado) {
			throw new IllegalArgumentException("Codigo erroneo: no se ha encontrado una linea con ese identificador");
		}else i--;
		if ((lineasMaquina.get(i).getCantidad())==0) {
			throw new IllegalArgumentException("Codigo erroneo: No queda ese producto");
		}
		if ((tarjeta.getSaldoActual()-lineasMaquina.get(i).getPrecio())<0) {
			throw new IllegalArgumentException("Codigo erroneo: No tienes suficiente dinero");
		}
		
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571",lineasMaquina.get(i).getPrecio());
		
		lineasMaquina.get(i).sacarUno();
		   
		
	}
	/**
	 * Permite rellenar una linea al maximo gracias a su identificador
	 * @param identificador - Entero con el identificador de la linea
	 * @throws IllegalArgumentException si {@code existe==false}
	 */
	public void rellenarLinea(int identificador) {
		boolean existe=false;
		for(int i=0;i<lineasMaquina.size();i++) {
			if(lineasMaquina.get(i).getIdentificadorLinea()==identificador) {
				lineasMaquina.get(i).setCantidad(cantidadMax);
				existe=true;
			}
		}
		if (!existe) {
			throw new IllegalArgumentException("Codigo erroneo: no se ha encontrado una linea con ese identificador");
		}
	}
	
	/**
	 * Devuelve la cantidad de lineas vacias que hay en la maquina
	 * @return Entero con el numero de lineas vacias
	 */
	public int getLineasVacias() {
		int cantidadVacias=0;
		for (int i = 0;i<lineasMaquina.size();i++) {
			if(lineasMaquina.get(i).getCantidad()==0) {
				cantidadVacias++;
			}
		}
		return cantidadVacias;
	}
	
	/**
	 * Delvuelve el numero de lineas que hay en la maquina expendedora
	 * @return numeroLineas - Entero con el numero de lineas
	 */
	public int getNumeroLineas() {
		return numeroLineas;
	}
	/**
	 * Devuelve el identificador de la maquina
	 * @return identificadorMaquina - Entero con el identificador
	 */
	public int getIdentificadorMaquina() {
		return identificadorMaquina;
	}
	/**
	 * Devuelve la lista con todas las lineas pertenecientes a la maquina expendedora
	 * @return lineasMaqina - ArrayList con todas las lineas
	 */
	public ArrayList<Linea> getLineasMaquina() {
		return new ArrayList<> (lineasMaquina);
	}
	/**
	 * Devuelve la cantidad maxima que cabe en una linea
	 * @return cantidadMax - Entero con la cantidad maxima
	 */
	public int getCantidadMax() {
		return cantidadMax;
	}

}
