/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Mueble.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;
import anotaciones.Init;
import anotaciones.Log;
import anotaciones.NoInit;
import anotaciones.PostConstructor;

/**
 * Entidad que representa un mueble de MLA
 * @author German Sotelo
 */
@Init(String="",Double=0)
public class Mueble {

    /**
     * Nombre del mueble
     */
    private String nombre;

    /**
     * Identificador del mueble, tiene que ser único
     */
    @NoInit
    private int id;

    /**
     * Precio del mueble
     */
    private double precio;

    @PostConstructor
    public void postConstructor() {
        System.out.println("aca se ejecuta este metodo en mueble");
    }
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método que reinica los valores del mueble. No modifica el ID.
     */
    @Init(Double=-2,String="N/A")
    @Log
    public void reiniciar(){

    }
    
}
