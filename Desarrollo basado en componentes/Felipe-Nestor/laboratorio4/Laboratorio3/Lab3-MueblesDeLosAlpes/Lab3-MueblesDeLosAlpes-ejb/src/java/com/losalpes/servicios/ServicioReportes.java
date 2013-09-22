/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Usuario;
import com.losalpes.servicios.persistencia.IServicioPersistenciaMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author feliperojas
 */
@Stateless
public class ServicioReportes implements IServicioReportesLocal {

    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    @Override
    public List<RegistroVenta> historialComprasCliente(Usuario usuario) {
        return persistencia.executeNamedQuerySingleResult("reporteHistorialComprasCliente", usuario);        
    }
}
