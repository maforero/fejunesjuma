/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Vendedor.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
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
import anotaciones.PostConstructor;

/**
 * Entidad que representa a un vendedor de MLA
 * @author Germán Sotelo
 */
@Init(String="-- Vacio --")
public class Vendedor
{

    /**
     * Id del vendedor. Tiene que ser único.
     */
    private int id;

    /**
     * Nombres del vendedor.
     */
    private String nombres;

    /**
     * Apellidos del vendedor
     */
    private String apellidos;

    /**
     * Sexo del vendedor
     */
    private String sexo;
    
    @PostConstructor
    public void postConstructor() {
        System.out.println("aca se ejecuta este metodo en vendedor");
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

}
