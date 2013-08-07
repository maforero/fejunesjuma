/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ManejadorVendedores.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import anotaciones.Cargar;
import anotaciones.Driver;
import anotaciones.Log;
import java.util.ArrayList;

/**
 * Clase encargada de de manejar la creación, eliminación y búsqueda de los vendedores de MLA
 * @author Germán Sotelo
 */
public class ManejadorVendedores {

    /**
     * Lista de vendedores de MLA
     */
    @Cargar
    private ArrayList<Vendedor> vendedores;

    /**
     * Id generado para los nuevos muebles
     */
    private int idGenerator;

    public ManejadorVendedores() {
    }

    /**
     * Retorna la lista de vendedores
     * @return
     */
    @Log
    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    /**
     * Crea un nuevo vendedor y lo retorna
     * @return
     */
    @Log
    public Vendedor nuevoVendedor() {
        Vendedor nuevo = (Vendedor)Driver.instanciar(Vendedor.class);;
        nuevo.setId(idGenerator++);
        vendedores.add(nuevo);
        return nuevo;
    }

    /**
     * Busca un vendedor dado su ID
     * @param id
     * @return
     */
    public Vendedor findVendedor(int id) {
        for(int e = 0;e<vendedores.size();e++){
            if(vendedores.get(e).getId()==id){
                return vendedores.get(e);
            }
        }
        return null;
    }

    /**
     * Elimina un vendedor dado su ID
     * @param id
     */
    @Log
    public void eliminarVendedor(int id) {
        for(int e = 0;e<vendedores.size();e++){
            if(vendedores.get(e).getId()==id){
                vendedores.remove(e);
                return;
            }
        }

    }

}
