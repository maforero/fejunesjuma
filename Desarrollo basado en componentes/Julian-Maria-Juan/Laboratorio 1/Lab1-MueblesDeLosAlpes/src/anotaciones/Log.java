/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cuando un método con esta anotación sea invocado debe imprimir le fecha, 
 * la hora, el nombre de su clase y el nombre del método invocado en un archivo de Log 
 * (El log se imprime al final del archivo)
 * Restricciones:
 * @author Julian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    
}
