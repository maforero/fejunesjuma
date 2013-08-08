/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

/**
 * Esta intefaz es utilizada para poder pasar diferentes implementaciones de metodos
 * a ser invocados por la anotacion @Invoke
 * 
 * @author Felipe
 */
public interface Invokable {

    /**
     * Metodo que sera invoca por los metodoa anotados con @Invoke
     */
    public void invoke();
}
