/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import anotaciones.Invoke;
import anotaciones.Log;
import anotaciones.ConfirmacionInvoker;

/**
 *
 * @author Felipe
 */
public interface Manejador {
    
    @Log
    @Invoke(preInvoke=ConfirmacionInvoker.class)
    public Mueble nuevoMueble();
    
    @Log
    @Invoke(preInvoke=ConfirmacionInvoker.class)
    public void eliminarMueble(int id);
}
