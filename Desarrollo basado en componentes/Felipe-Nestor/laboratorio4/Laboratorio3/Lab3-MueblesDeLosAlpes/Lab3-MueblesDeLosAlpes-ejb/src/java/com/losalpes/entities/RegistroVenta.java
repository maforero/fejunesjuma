/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ RegistroVenta.java
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenieria de Sistemas y Computacion
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.entities;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que modela un registro de venta realizado por un cliente
 * @author Juan Sebastián Urrego
 */
@Entity
public class RegistroVenta
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    @EmbeddedId
    private RegistroVentaPK registroVentaPK;

    /**
     * Producto vendido
     */
    @ManyToOne
    @JoinColumn(name="producto", updatable=false,insertable=false)
    private Mueble producto;

    /**
     * Cantidad vendida del producto
     */
    private int cantidad;

    /**
     * Ciudad en la que se vendió el producto
     */
    @ManyToOne
    private Ciudad ciudad;

    /**
     * Usuario que compró el producto
     */
    @ManyToOne
    @JoinColumn(name="comprador", updatable=false,insertable=false)
    private Usuario comprador;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor sin argumentos
     */
    public RegistroVenta()
    {
        
    }

    /**
     * Constructor de la clase con argumentos
     * @param fechaVenta Fecha en que se realizó la venta
     * @param producto Mueble adquirido
     * @param cantidad Cantidad adquirida
     * @param ciudad Ciudad en la que se vendió el producto
     * @param comprador Usuario que compro el mueble
     */
    public RegistroVenta(Date fechaVenta, Mueble producto, int cantidad,
            Ciudad ciudad, Usuario comprador)
    {
        this.registroVentaPK = new RegistroVentaPK(fechaVenta, comprador.getLogin(), producto.getReferencia());
        this.producto = producto;
        this.cantidad = cantidad;
        this.ciudad = ciudad;
        this.comprador = comprador;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve la cantidad de producto vendido
     * @return cantidad Cantidad de producto vendido
     */
    public int getCantidad()
    {
        return cantidad;
    }

    /**
     * Modifica la cantidad de muebles adquiridos
     * @param cantidad Nueva cantidad de muebles
     */
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }    

    /**
     * Devuelve el mueble adquirido
     * @return producto Mueble adquirido
     */
    public Mueble getProducto()
    {
        return producto;
    }

    /**
     * Modifica el mueble adquirido
     * @param producto Nuevo mueble
     */
    public void setProducto(Mueble producto)
    {
        this.producto = producto;
    }

    /**
     * Devuelve la ciudad en dónde se realizó la venta
     * @return ciudad Ciudad
     */
    public Ciudad getCiudad()
    {
        return ciudad;
    }

    /**
     * Modifica la ciudad dónde se realizó la venta
     * @param ciudad Nueva ciudad
     */
    public void setCiudad(Ciudad ciudad)
    {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve el usuario que realizó la compra
     * @return comprador Usuario que realizó la compra
     */
    public Usuario getComprador()
    {
        return comprador;
    }

    /**
     * Modifica el usuario que realizó la compra
     * @param comprador Nuevo usuario
     */
    public void setComprador(Usuario comprador)
    {
        this.comprador = comprador;
    }

    public RegistroVentaPK getRegistroVentaPK() {
        return registroVentaPK;
    }

    public void setRegistroVentaPK(RegistroVentaPK registroVentaPK) {
        this.registroVentaPK = registroVentaPK;
    }

}
