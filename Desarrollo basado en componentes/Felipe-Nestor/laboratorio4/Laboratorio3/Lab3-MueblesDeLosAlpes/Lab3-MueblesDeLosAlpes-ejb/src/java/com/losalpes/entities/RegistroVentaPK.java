/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author feliperojas
 */
@Embeddable
public class RegistroVentaPK {

    private long comprador;
    private long producto;
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    public long getComprador() {
        return comprador;
    }

    public void setComprador(long comprador) {
        this.comprador = comprador;
    }

    public long getProducto() {
        return producto;
    }

    public void setProducto(long producto) {
        this.producto = producto;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
}
