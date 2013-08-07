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
 * Todo atributo anotado con esta anotación no puede 
 * ser alterado por la ejecución de una anotación Init.
 * Restricciones:Aplica solo para atributos de clase
 * @author Julian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NoInit {
    
}
