/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Representa la llave primaria compuesta de RegistroVenta
 * @author Felipe
 */
@Embeddable
public class RegistroVentaPK implements Serializable {
    
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    
    private String comprador;
    
    private long producto;

    public RegistroVentaPK() {
    }

    /**
     * Constructor con parametros de la clase
     * @param fechaVenta Fecha de venta
     * @param comprador Login del usuario
     * @param producto Identificador del mueble
     */
    public RegistroVentaPK(Date fechaVenta, String comprador, long producto) {
        this.fechaVenta = fechaVenta;
        this.comprador = comprador;
        this.producto = producto;
    }

    /**
     * Permite obtener el valor de la fecha de venta
     * @return Fecha de venta
     */
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Modificar el valor de la fecha de venta
     * @param fechaVenta 
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * Obtiene el identificador del comprador que realiza la compra
     * @return Identificador del comprador
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * Modificar el valor del identificador del comprador
     * @param comprador Login del usuario
     */
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    /**
     * Permite obtener la referencia del mueble comprado
     * @return Retorna el identificador del producto
     */
    public long getProducto() {
        return producto;
    }

    /**
     * Modifica el valor del identificador del producto
     * @param producto Identificador del mueble
     */
    public void setProducto(long producto) {
        this.producto = producto;
    }   

    

    
    
}
