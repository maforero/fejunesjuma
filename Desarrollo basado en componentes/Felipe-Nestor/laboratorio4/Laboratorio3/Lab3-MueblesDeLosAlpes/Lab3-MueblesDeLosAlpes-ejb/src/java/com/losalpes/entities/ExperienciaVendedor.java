/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ExperienciaVendedor.java
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenieria de Sistemas y Computacion
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.entities;

/**
 * Clase que modela un ìtem de experiencia de vendedor.
 * @author Juan Sebastián Urrego
 */
public class ExperienciaVendedor
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Identificador del item de experiencia.
     */
    private long id;

    /**
     * Nombre de la empresa que ceritifica la experiencia laboral.
     */
    private String nombreEmpesa;

    /**
     * Cargo que el empleado ocupó en la empresa.
     */
    private String cargo;

    /**
     * Descripción de las funciones del cargo.
     */
    private String descripcion;

    /**
     * Año de terminación del vínculo laboral.
     */
    private int ano;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor de la clase sin argumentos
     */
    public ExperienciaVendedor()
    {
        
    }

    /**
     * Constructor de la clase con argumentos
     * @param id Identificador único de la experiencia
     * @param nombreEmpesa Nombre de la empresa
     * @param cargo Cargo ocupado
     * @param descripcion Descripción de las funcionaes del cargo
     * @param ano Año de terminación del vínculo laboral
     */
    public ExperienciaVendedor(long id, String nombreEmpesa, String cargo, String descripcion, int ano)
    {
        this.id = id;
        this.nombreEmpesa = nombreEmpesa;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.ano = ano;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el identificador único del vendedor
     * @return id Identificador del vendedor
     */
    public long getId()
    {
        return id;
    }

    /**
     * Modifica el identificador único del jugador
     * @param id Nuevo identificador del vendedor
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Devuelve el año de terminación del vínculo laboral
     * @return ano Año de terminación del vínculo laboral
     */
    public int getAno()
    {
        return ano;
    }

    /**
     * Modifica el año de terminación del vínculo laboral
     * @param ano Nuevo año de terminación de vínculo
     */
    public void setAno(int ano)
    {
        this.ano = ano;
    }

    /**
     * Devuelve el cargo del vendedor
     * @return cargo Cargo del vendedor
     */
    public String getCargo()
    {
        return cargo;
    }

    /**
     * Modifica el cargo de un vendedor
     * @param cargo Nuevo cargo
     */
    public void setCargo(String cargo)
    {
        this.cargo = cargo;
    }

    /**
     * Devuelve la descripción del vendedor
     * @return descripcion Descripción del vendedor
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Modifica la descripción del vendedor
     * @param descripcion Nueva descripción del vendedor
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el nombre de la empresa que certifica la experiencia laboral
     * del vendedor
     * @return nombreEmpresa Nombre de la empresa certificadora
     */
    public String getNombreEmpesa()
    {
        return nombreEmpesa;
    }

    /**
     * Modifica el nombre de la empresa que certifica la experiencia laboral
     * del vendedor
     * @param nombreEmpesa Nuevo nombre de la empresa certificadora
     */
    public void setNombreEmpesa(String nombreEmpesa)
    {
        this.nombreEmpesa = nombreEmpesa;
    }

}
