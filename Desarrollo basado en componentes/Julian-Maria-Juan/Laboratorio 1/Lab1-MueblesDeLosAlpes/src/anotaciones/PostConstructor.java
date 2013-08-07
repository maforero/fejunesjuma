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
 * Cuando el driver encuentra esta anotación en un método
 * lo debe invocar despues de la instanciación (Constructor)
 * Restricciones: El método anotado no debe tener parametros de entrada 
 * @author Julian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PostConstructor {
    
}
