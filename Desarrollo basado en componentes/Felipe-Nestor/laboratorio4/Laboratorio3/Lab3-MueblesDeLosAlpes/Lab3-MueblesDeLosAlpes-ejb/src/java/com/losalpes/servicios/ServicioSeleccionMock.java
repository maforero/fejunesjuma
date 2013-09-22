/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Pais;
import com.losalpes.servicios.persistencia.IServicioPersistenciaMockLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author feliperojas
 */
@Singleton
public class ServicioSeleccionMock implements IServicioSeleccionMockLocal {

    private List<Pais> paises;
    
    @EJB
    private IServicioPersistenciaMockLocal servicioPersistencia;
    
    @PostConstruct
    public void iniciarSelecciones() {
        paises = servicioPersistencia.findAll(Pais.class);
    }
    
    @Override
    public List<Pais> obtenerPaises() {
        return paises;
    }
}
