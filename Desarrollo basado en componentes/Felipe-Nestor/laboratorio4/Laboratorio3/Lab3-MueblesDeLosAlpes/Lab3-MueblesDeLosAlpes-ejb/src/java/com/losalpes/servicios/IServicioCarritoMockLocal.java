/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioCarritoMockLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Usuario;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 * Contrato funcional de los servicios para el carrito de compras
 * @author Juan Sebastián Urrego
 */
@Local
public interface IServicioCarritoMockLocal
{

    /**
     * Devuelve el inventario de muebles que se encuentran en el carrito
     * @return inventario Lista con los muebles que se encuentran en el carrito
     */
    public ArrayList<Mueble> getInventario();

    /**
     * Modifica el inventario del carrito
     * @param inventario Nueva lista de muebles
     */
    public void setInventario(ArrayList<Mueble> inventario);

    /**
     * Devuelve el precio total del inventario
     * @return precioTotalInventario Precio total del inventario
     */
    public double getPrecioTotalInventario();

    /**
     * Devuelve el cantidad total de unidades en el carrito
     * @return totalUnidades Cantidad total de unidades en el carrito
     */
    public int getTotalUnidades();

    /**
     * Realiza la compra de los items que se encuentran en el carrito
     * @param usuario Usuario que realiza la compra
     */
    public void comprar(Usuario usuario);

    /**
     * Agrega un nuevo mueble al carro de compras
     * @param mueble Mueble que se agrega al carrito
     */
    public void agregarItem(Mueble mueble);

    /**
     * Remueve un mueble del carrito de compra
     * @param mueble Mueble a remover
     * @param removerCero Indica si al ser cero se elimina de la lista
     */
    public void removerItem(Mueble mueble, boolean removerCero);

    /**
     * Recalcula el costo y la cantidad de inventario
     */
    public void recalcularInventarioTotal();

    /**
     * Limpia el carrito de compras
     */
    public void limpiarLista();

}
