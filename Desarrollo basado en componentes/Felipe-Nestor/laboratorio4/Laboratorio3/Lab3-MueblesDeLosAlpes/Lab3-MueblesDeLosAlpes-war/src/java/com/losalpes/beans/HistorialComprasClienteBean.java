/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Usuario;
import com.losalpes.servicios.IServicioReportesLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author feliperojas
 */
@Named
@ManagedBean
@ConversationScoped
public class HistorialComprasClienteBean implements Serializable {

    @EJB
    private IServicioReportesLocal reportes;
    
    private Usuario usuario;
    List<RegistroVenta> historialCliente;

    @Inject
    private Conversation conversation;
    
    public void iniciarHistorialComprasCliente() {
        conversation.begin();
    }
    
    /**
     * Creates a new instance of HistorialComprasClienteBean
     */
    public HistorialComprasClienteBean() {
    }
    
    public void consultarHistorialCliente(ActionEvent event) {
        historialCliente = reportes.historialComprasCliente(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<RegistroVenta> getHistorialCliente() {
        return historialCliente;
    }

    public void setHistorialCliente(List<RegistroVenta> historialCliente) {
        this.historialCliente = historialCliente;
    }
}
