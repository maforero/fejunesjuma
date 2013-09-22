/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * Ciudad.java Universidad de los Andes (Bogotá - Colombia) Departamento de
 * Ingeniería de Sistemas y Computación Licenciado bajo el esquema Academic Free
 * License version 3.0
 *
 * Ejercicio: Muebles de los Alpes Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Clase que representa una ciudad en el sistema
 *
 * @author Juan Sebastián Urrego
 */
@Entity
@NamedQueries({
    @NamedQuery(name="buscarCiudadPorNombre", query="SELECT c FROM Ciudad c WHERE c.nombre=?1")
})
public class Ciudad {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Nombre de la ciudad
     */
    private String nombre;

    /**
     * Devuelve el nombre de la ciudad
     *
     * @return nombre Nombre de la ciudad
     */
    public String getNombre() {
        return nombre;
    }

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase (sin argumentos)
     */
    public Ciudad() {
    }

    /**
     * Constructor de la clase (con argumentos)
     *
     * @param nombre
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * Modifica el nombre de la ciudad
     *
     * @param nombre Nuevo nombre de la ciudad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
