package VendingCo.VendingSystem;
import java.util.ArrayList;
import VendingCo.VendingMachine.VendingMachine;


/**
 * Clase relativa al sistema de gestion de Vending Machine
 * 
 * @author javabad
 * @author alemina
 *
 */
public class VendingSystem {
    private ArrayList<VendingMachine> maquinas;
    
    public VendingSystem() {
        maquinas = new ArrayList<>();
    }
    /**
     * Añadir una nueva maquina vending al sistema
     * @param m1 maquina que se añadirá
     * @throws IllegalArgumentException si {@code maquinas.get(i).getIdentificadorMaquina()==m1.getIdentificadorMaquina()}

     */
    public void annadirMaquina(VendingMachine m1) {
        for (int i=0;i<maquinas.size();i++) {
            if (maquinas.get(i).getIdentificadorMaquina()==m1.getIdentificadorMaquina()) {
                throw new IllegalArgumentException("Error: Ya existe esa maquina.");
            }
        }
        maquinas.add(m1);
    }
    /**
     * Eliminar una maquina del sistema mediante su identificador
     * @param identificador - Entero que identifica la maquina que se eliminará
     * @throws IllegalArgumentException si {@code maquinas.size()==0}
     * @throws IllegalArgumentException si {@code !existe}
     */
    public void eliminarMaquina(int identificador) {
        boolean existe=false;
        if (maquinas.isEmpty()) {
            throw new IllegalArgumentException("Errror: No existen maquinas para eliminar");
        }
        for (int i=0;i<maquinas.size();i++) {
            if(maquinas.get(i).getIdentificadorMaquina()==(identificador)) {
                maquinas.remove(i);
                existe=true;
            }           
        }
        if (!existe) {
            throw new IllegalArgumentException("Error: No hay ninguna maquina con ese identificador");
        } 
    }
    
    /**
     * Obtener una lista con todas las maquinas del sistema existentes
     * independientemente de su estado.
     * @return maquinas - Lista con todas las maquinas.
     */
    public ArrayList<VendingMachine> getMaquinas() {
        return maquinas;
    }
    
    
    /**
     * Obtener el numero de maquinas operativas del sistema.
     * @return entero con el numero de listas operativas.
     */
    public int maquinasOperativas() {
        int contador=0;
        for (int i=0;i<maquinas.size();i++) {
            if (maquinas.get(i).getEstado())
                contador++;
        }
        return contador;
    }
    
    /**
     * Obtener una lista todas las maquinas operativas.
     * @throws IllegalArgumentException si {@code maquinasOperativas()==0}
     * @return lista con las maquinas operativas.
     */
    public ArrayList<VendingMachine> getMaquinasOperativas() {
        ArrayList<VendingMachine> maquinasOperativas = new ArrayList<>();
        if (maquinasOperativas()==0) {
            throw new IllegalArgumentException("Error: No hay ninguna maquina operativa");
        }
        for (int i=0;i<maquinas.size();i++) {
            if (maquinas.get(i).getEstado()) {
                maquinasOperativas.add(maquinas.get(i));
            }
            	
        }
        return maquinasOperativas;
    }

    
    /**
     * Obtener una lista que contenga todas las maquinas que tienen alguna linea vacia.
	 * @throws IllegalArgumentException si {@code lineaVacia==true}
     * @return lista con las maquinas con lineas vacias.
     */
    public ArrayList<VendingMachine> getMaquinasLineaVacia() {
        boolean lineaVacia=true;
        ArrayList<VendingMachine> maquinasLineaVacias = new ArrayList<>();
        for (int i=0;i<getMaquinasOperativas().size();i++) {
            if (getMaquinasOperativas().get(i).getLineasVacias()>0) {
                maquinasLineaVacias.add(getMaquinasOperativas().get(i));
                lineaVacia=false;
            }
        }
        if (lineaVacia)
            throw new IllegalArgumentException("Error: No hay ninguna maquina con lineas vacias");

        return maquinasLineaVacias;
    }
}