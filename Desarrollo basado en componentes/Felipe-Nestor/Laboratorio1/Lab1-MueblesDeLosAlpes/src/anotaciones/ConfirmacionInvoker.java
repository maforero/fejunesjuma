/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */
public class ConfirmacionInvoker implements Invokable {

    public void invoke() {
        int value = JOptionPane.showConfirmDialog(null, "Esta seguro que desea realizar la accion");
        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            throw  new RuntimeException("Accion no realizada");
        }
    }
    
}
