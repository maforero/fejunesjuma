/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ VendedorBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.beans;

import com.losalpes.entities.ExperienciaVendedor;
import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.OperacionInvalidaException;
import com.losalpes.servicios.IServicioVendedoresMockLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Managed Bean encargado de la administración de vendedores en el sistema
 * @author Juan Sebastián Urrego
 */
public class VendedorBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Relación con la interfaz que provee los servicios necesarios del vendedor
     */
    @EJB
    private IServicioVendedoresMockLocal servicio;

    /**
     * Representa un nuevo vendedor a ingresar
     */
    private Vendedor vendedor;

    /**
     * Representa una nueva experiencia de vendedor
     */
    private ExperienciaVendedor experiencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public VendedorBean()
    {
        vendedor=new Vendedor();
        experiencia=new ExperienciaVendedor();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el objeto de vendedor actual
     * @return vendedor Vendedor actual
     */
    public Vendedor getVendedor()
    {
        return vendedor;
    }

    /**
     * Modifica al vendedor actual
     * @param vendedor Nuevo vendedor
     */
    public void setVendedor(Vendedor vendedor)
    {
        this.vendedor = vendedor;
    }

    /**
     * Devuelve todos los vendedores del sistema
     * @return vendedores Lista con todos los vendedores del sistema
     */
    public List<Vendedor> getVendedores()
    {
        return servicio.getVendedores();
    }

    /**
     * Devuelve el objeto actual de experiencia vendedor
     * @return experiencia Objeto de la experiencia del vendedor actual
     */
    public ExperienciaVendedor getExperiencia()
    {
        return experiencia;
    }

    /**
     * Modifica la experiencia del vendedor actual
     * @param experiencia Nueva experiencia del vendedor
     */
    public void setExperiencia(ExperienciaVendedor experiencia)
    {
        this.experiencia = experiencia;
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un nuevo vendedor al sistema
     */
    public void agregarVendedor() throws OperacionInvalidaException
    {
        try 
        {
            servicio.agregarVendedor(vendedor);
            vendedor=new Vendedor();
            experiencia=new ExperienciaVendedor();
        }
        catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    /**
     * Elimina un vendedor del sistema
     * @param evento Evento que contiene como parámetro el ID del vendedor
     */
    public void eliminarVendedor(ActionEvent evento) throws OperacionInvalidaException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();

        long vendedorId=Long.parseLong((String)map.get("vendedorId"));
        try
        {
            servicio.eliminarVendedor(vendedorId);
        }
        catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    /**
     * Agrega un item de experiencia a lista de experiencia del vendedor
     */
    public void agregarItemExperiencia()
    {
        long id=vendedor.getExperiencia().size()+1;
        experiencia.setId(id);
        vendedor.setItemExperiencia(experiencia);
        experiencia=new ExperienciaVendedor();
    }
}
