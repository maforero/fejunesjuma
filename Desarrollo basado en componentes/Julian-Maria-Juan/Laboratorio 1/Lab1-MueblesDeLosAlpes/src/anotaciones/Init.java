/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Init.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package anotaciones;

import java.lang.annotation.*;

/**
 * Cuando un método anotado con esta clase se ejecuta, los atributos de los tipos básicos quedan inicializados con los valores de esta anotación
 * Cuando se carga una clase anotada, los atributos de los tipos básicos quedan inicializados con los valores de esta anotación
 * Restricción: Para que esta anotación funcione se debe instanciar la entidad por medio del Driver
 * @author Germán Sotelo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.METHOD})
public @interface Init {

    String String() default "";
    double Double() default 0.0;
    int Integer() default 200;
    char Char() default '\0';
    boolean Boolean() default false;
    long Long() default -1;
    float Float() default 0;
    
}
