/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ CarritoBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.beans;

import com.icesoft.faces.component.dragdrop.DndEvent;
import com.icesoft.faces.component.dragdrop.DragEvent;
import com.icesoft.faces.component.ext.HtmlPanelGroup;
import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.Highlight;
import com.losalpes.entities.Mueble;
import com.losalpes.entities.Usuario;
import com.losalpes.servicios.IServicioCarritoMockLocal;
import com.losalpes.servicios.IServicioCatalogoMockLocal;
import com.losalpes.servicios.ServicioCarritoMock;
import com.losalpes.servicios.ServicioCatalogoMock;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Managed Bean encargado del carrito de compras del cliente
 * @author Juan Sebastián Urrego
 */
public class CarritoBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Relación con la interfaz que provee los servicios necesarios del carrito de compras
     */
    @EJB
    private IServicioCarritoMockLocal carrito;

    /**
     * Relación con la interfaz que provee los servicios necesarios del catálogo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogo;

    /**
     * Efecto para la tabla del carrito
     */
    private Effect efectoTablaCarrito;

    /**
     * Efecto para el carrito
     */
    private Effect efectoCarrito;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public CarritoBean()
    {
        //Inicializa los efectos del carrito de compras
        efectoTablaCarrito = new Highlight("#fda505");
        efectoTablaCarrito.setFired(true);
        efectoCarrito = new Highlight("#fda505");
        efectoCarrito.setFired(true);
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el efecto de la tabla del carrito
     * @return efectoTablaCarrito Efecto
     */
    public Effect getEfectoTablaCarrito()
    {
        return efectoTablaCarrito;
    }

    /**
     * Devuelve el efecto del carrito
     * @return efectoCarrito Efecto del carrito
     */
    public Effect getEfectoCarrito()
    {
        return efectoCarrito;
    }
    
    /**
     * Devuelve el cambio de efecto
     * @return efectoCarrio Efecto del carrito
     */
    public Effect getCambioEfecto()
    {
        return efectoCarrito;
    }

    /**
     * Devuelve todos los muebles del sistema
     * @return muebles Lista con todos los muebles del sistema
     */
    public List<Mueble> getMuebles()
    {
        return catalogo.darMuebles();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un item al carrito de compras
     * @param evento Evento que contiene el valor del mueble
     */
    public void agregarItemCarrito(DragEvent evento)
    {

        if (evento.getEventType() == DndEvent.DROPPED)
        {
            String targetId = evento.getTargetClientId();
            if ((targetId != null))
            {

                Mueble inventoryItem = (Mueble) ((HtmlPanelGroup) evento.getComponent()).getDragValue();

                if (inventoryItem.getCantidad() > 0)
                {
                    Mueble m=new Mueble(inventoryItem.getReferencia(),inventoryItem.getNombre(),inventoryItem.getDescripcion(),inventoryItem.getTipo(),0,inventoryItem.getImagen(),inventoryItem.getPrecio());
                    // add the inventory item to the shopping chart
                    carrito.agregarItem(m);
                    efectoCarrito.setFired(true);
                }
            }
        }

        //Lanza el efecto
        else if (evento.getEventType() == DndEvent.HOVER_START)
        {
            efectoTablaCarrito.setFired(false);
            efectoCarrito.setFired(false);
        }
    }
    
    /**
     * Remueve un item del carrito
     * @param evento Evento que tiene como parámetro el ID del mueble
     */
    public void removerItemCarrito(ActionEvent evento)
    {
        // Obtiente el ID del mueble
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        int inventoryId = Integer.parseInt((String) map.get("inventoryId"));
       

        Mueble item;
        for (int i = 0, max = carrito.getInventario().size(); i < max; i++)
        {
            item = (Mueble) carrito.getInventario().get(i);
            if (item.getReferencia() == inventoryId)
            {
                carrito.removerItem(item, true);
                break;
            }
        }
    }

    /**
     * Realiza una compra basado en los items del carrito
     */
    public void comprar()
    {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("loginBean"))
        {
            LoginBean sessionSecurity = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
            Usuario user = sessionSecurity.getSesion();
            if (user != null)
            {
                carrito.comprar(user);
            }
            destroyBean();
        }
    }

    /**
     * Devuelve el precio total del inventario
     * @return precioTotal Precio total
     */
    public double getPrecioTotal()
    {
        return carrito.getPrecioTotalInventario();
    }

    /**
     * Devuelve el total de unidades en el carrito
     * @return totalUnidades Total de unidades en el carrito
     */
    public int getTotalUnidades()
    {
        return carrito.getTotalUnidades();
    }

    /**
     * Devuelve la lista con los muebles que se encuentran en el carrito
     * @return inventario Lista con los muebles del carrito
     */
    public List<Mueble> getInventario()
    {
        return carrito.getInventario();
    }

    /**
     * Remueve el presente bean de la sesión en curso
     */
    public void destroyBean()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("carritoBean");
    }

}
