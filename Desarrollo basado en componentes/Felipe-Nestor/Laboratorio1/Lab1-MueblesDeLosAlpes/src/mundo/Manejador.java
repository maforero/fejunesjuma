/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import anotaciones.ConfirmacionInvoker;
import anotaciones.Invoke;
import anotaciones.Log;

/**
 * Interfaz utilizada por el manejador de muebles
 * @author Felipe
 */
public interface Manejador {
    
    /**
     * Crea un nuevo mueble, lo añade a la lista y lo retorna
     * @return
     */
    @Log
    @Invoke(preInvoke=ConfirmacionInvoker.class)
    public Mueble nuevoMueble();
    
     /**
     * Elimina un mueble dado su identificador
     * @param id Id del mueble
     */
    @Log
    @Invoke(preInvoke=ConfirmacionInvoker.class)
    public void eliminarMueble(int id);
}
