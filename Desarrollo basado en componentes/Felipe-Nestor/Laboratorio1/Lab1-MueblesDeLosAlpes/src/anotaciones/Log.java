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
 * Anotacion creada para indicar que los metodos deberan ser ingresados
 * al archivo de log
 * 
 * @author NCRUZ
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    
}
