/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion utilizada para indicar que el metodo anotado se debe ejecutar despues
 * de crear la instancia del metodo. El metodo anotado no debe tener parametros y 
 * debe ser publico
 * 
 * @author Felipe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface PostConstructor {
    
}
