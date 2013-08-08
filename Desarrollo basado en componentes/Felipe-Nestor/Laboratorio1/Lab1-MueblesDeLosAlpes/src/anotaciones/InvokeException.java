/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

/**
 * Es la excepcion lanzada cuando ocurre un error en la invocacion de los metodos
 * de la anotacion @invoke
 * 
 * @author Felipe
 */
public class InvokeException extends RuntimeException {

    public InvokeException(String mensaje) {
        super(mensaje);
    }
}
