/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazTiendaLosAlpes.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package interfaz;

import anotaciones.Driver;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import mundo.*;

/**
 * Ventana principal y punto de entrada de la aplicación
 * @author Germán Sotelo
 */
public class InterfazTiendaLosAlpes extends JFrame {

    /**
     * Método constructor del frame principal de la aplicación
     */
    public InterfazTiendaLosAlpes() {
        MueblesLosAlpes mla = (MueblesLosAlpes) (Driver.instanciar(MueblesLosAlpes.class));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel header = new JPanel();
        JLabel head = new JLabel(new ImageIcon("./data/header.png"));
        header.add(head);

        JPanel center = new JPanel();
        JTabbedPane jtp = new JTabbedPane();
        center.add(jtp);

        jtp.addTab("Muebles", new PanelMuebles(mla.getManejadorMuebles()));
        jtp.addTab("Vendedores", new PanelVendedores(mla.getManejadorVendedores()));

        add(jtp, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        pack();
    }

    /**
     * Punto de entrada de la aplicación
     * @param args Parametros de consola para la ejecución de la aplicación
     */
    public static void main(String[] args) {
        new InterfazTiendaLosAlpes().setVisible(true);
    }

}

