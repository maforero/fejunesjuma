/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import anotaciones.Init;
import anotaciones.Log;
import anotaciones.Validator;

/**
 *
 * @author maria
 */
public interface Restart {
    @Validator
    @Init(Double=-2,String="N/A")
    @Log
    public void reiniciar();
}
