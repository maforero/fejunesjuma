/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de soliciar una confirmacion al usuario para realizar 
 * acciones como crear o eliminar muebles, implementa la interfaz Invokable, debido
 * a que es el tipo requerido por la anotacion Invoke
 * @author Felipe
 */
public class ConfirmacionInvoker implements Invokable {

    /**
     * Metodo invocado definido por la anotacion @invoke en donde se solicita
     * al usuario que confirme la accion
     * @throws InvokeException en caso de que la accion elegida sea no, de forma
     * tal que se cancele la ejecución de la accion
     */
    public void invoke() {
        int value = JOptionPane.showConfirmDialog(null, "Esta seguro que desea realizar la accion", "", JOptionPane.YES_NO_OPTION);
        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            throw new InvokeException("Accion no realizada");
        }
    }
    
    
}
