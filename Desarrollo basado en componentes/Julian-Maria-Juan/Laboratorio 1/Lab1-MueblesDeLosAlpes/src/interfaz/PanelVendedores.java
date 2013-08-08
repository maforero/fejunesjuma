/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelVendedores.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mundo.ManejadorVendedores;
import mundo.Vendedor;

/**
 * Panel encargado de mostrar a los vendedores
 * @author Germán Sotelo
 */
public class PanelVendedores extends JPanel implements ActionListener{

    static final String NUEVO = "NUEVO";

    static final String ELIMINAR = "ELIMINAR";

    /**
     * Manejador de los vendedores
     */
    private ManejadorVendedores manager;

    /**
     * Entidad encargada de contener el modelo de la tabla de vendedores
     */
    private DefaultTableModel dtmVendedores = new myTableModel(new String[]{"Id","Nombres","Apellidos","Sexo"});

    /**
     * Entidad encarga de visualizar la tabla de vendedores
     */
    private JTable tablaVendedores;

    /**
     * Método constructor del panel encargado de mostrar los vendedores de MLA
     * @param manager Entidad encargada de manejar la lista de vendedores de MLA
     */
    public PanelVendedores(ManejadorVendedores manager) {
        super(new BorderLayout());
        tablaVendedores = new JTable(dtmVendedores);
        this.manager=manager;
        JScrollPane jspVendedores=new JScrollPane(tablaVendedores);
        add(jspVendedores,BorderLayout.CENTER);

        JPanel opcionesVendedores = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=-1;

        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.NORTH;

        JButton nuevoVendedor = new JButton("Nuevo");
        nuevoVendedor.addActionListener(this);
        nuevoVendedor.setActionCommand(NUEVO);
        JButton eliminarVendedor = new JButton("Eliminar");
        eliminarVendedor.addActionListener(this);
        eliminarVendedor.setActionCommand(ELIMINAR);
        opcionesVendedores.add(nuevoVendedor,gbc);
        opcionesVendedores.add(eliminarVendedor,gbc);

        JPanel temporal = new JPanel(new BorderLayout());
        temporal.add(opcionesVendedores,BorderLayout.NORTH);
        add(temporal,BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(NUEVO)){
            // Se crea un nuevo vendedor a traves del manejador
            Vendedor c = manager.nuevoVendedor();
            Object[] data = {c.getId(),c.getNombres(),c.getApellidos(),c.getSexo()};
            // Se añade el nuevo vendedor al modelo de la tabla de vendedores
            dtmVendedores.addRow(data);
        }
        else if(e.getActionCommand().equals(ELIMINAR)){
            int s = tablaVendedores.getSelectedRow();
            if(s!=-1){
                // Se busca el ID del vendedor seleccionado y se elimina
                manager.eliminarVendedor((Integer)dtmVendedores.getValueAt(s,0));
                dtmVendedores.removeRow(s);
                tablaVendedores.revalidate();
            }
        }
    }

    /**
     * Clase encargada de manejar el modelo de la tabla de vendedores.
     */
    public class myTableModel extends DefaultTableModel{
        public myTableModel(String[] columns) {
            super(columns,0);
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            Vendedor vendedor = manager.findVendedor((Integer)dtmVendedores.getValueAt(row,0));
            if(column==1){
                vendedor.setNombres(""+aValue);
                super.setValueAt(vendedor.getNombres(), row, column);
            }
            else if(column==2){
                vendedor.setApellidos(""+aValue);
                super.setValueAt(vendedor.getApellidos(), row, column);
            }
            else if(column==3){
                vendedor.setSexo(""+aValue);
                super.setValueAt(vendedor.getSexo(), row, column);
            }
        }
    }

}
