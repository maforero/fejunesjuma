/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios.persistencia;

import com.losalpes.entities.Ciudad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author feliperojas
 */
@Stateless
public class ServicioPersistenciaCiudad implements IServicioPersistenciaCiudadLocal {

    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    @Override
    public List<Ciudad> obtenerCiudades() {
        return persistencia.findAll(Ciudad.class);
    }

    @Override
    public Ciudad obtenerCiudadPorNombre(String nombre) {
        return persistencia.executeNamedQuerySingleResult("buscarCiudadPorNombre", nombre);
    }

}
