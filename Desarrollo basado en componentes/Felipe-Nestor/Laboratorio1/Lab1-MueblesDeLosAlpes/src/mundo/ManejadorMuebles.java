/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ManejadorMuebles.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
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
 * Clase encargada de de manejar la creación, eliminación y búsqueda de los muebles de MLA
 * @author Germán Sotelo
 */
public class ManejadorMuebles {

    /**
     * Lista de muebles
     */
    @Cargar
    private ArrayList<Mueble> muebles;

    /**
     * Id generado para los muebles (id++)
     */
    private int idGenerator;

    public ManejadorMuebles() {
    }

    /**
     * Devuelve la lista de muebles de MLA
     * @return
     */
    @Log
    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    /**
     * Crea un nuevo mueble, lo añade a la lista y lo retorna
     * @return
     */
    @Log
    public Mueble nuevoMueble() {
        Mueble nuevo = (Mueble)Driver.instanciar(Mueble.class);
        nuevo.setId(idGenerator++);
        muebles.add(nuevo);
        return nuevo;
    }

    /**
     * Encuentra un mueble dado su identificador
     * @param id Id del mueble a buscar
     * @return
     */
    @Log
    public Mueble findMueble(int id) {
        for(int e = 0;e<muebles.size();e++){
            if(muebles.get(e).getId()==id){
                return muebles.get(e);
            }
        }
        return null;
    }

    /**
     * Elimina un mueble dado su identificador
     * @param id Id del mueble
     */
    @Log
    public void eliminarMueble(int id) {
        for(int e = 0;e<muebles.size();e++){
            if(muebles.get(e).getId()==id){
                muebles.remove(e);
                return;
            }
        }
        
    }
}
