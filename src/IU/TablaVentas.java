package IU;

import domain.ItemTicket;
import domain.Ticket;
import negocios.ListaDeProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TablaVentas extends JDialog {
    public ListaDeProductos productosUtil = new ListaDeProductos();
    private JTable table1;
    private JPanel panel1;
    private JButton verDetalleButton;

    public TablaVentas(JFrame parent) {
        super(parent);

        setTitle("LISTA DE VENTAS");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        llenarTabla();

        verDetalleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer rowSelect = table1.getSelectedRow();
                if( rowSelect.equals(-1)){
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un ticket");
                }else {
                    Integer idVenta = Integer.parseInt(table1.getValueAt(rowSelect, 0).toString());
                    List<Ticket> listaVentas = productosUtil.verRegistroVentas();
                    for (Ticket t : listaVentas) {
                        if (t.getId().equals(idVenta)) {
                           String lista = t.getListaProductos().toString().replace("[","");
                            lista = lista.replace("]","");
                            lista = lista.replace(",","");
                            JOptionPane.showMessageDialog(null,lista);
                        }
                    }
                }
            }
        });
        setVisible(true);
    }

    public void llenarTabla(){
        String[]headers = {"ID","UNIDADES","FECHA","TOTAL"};
        Object[] registros = new Object[4];
        DefaultTableModel model = new DefaultTableModel(null, headers);
        model.addRow(headers);
        for (Ticket t : productosUtil.verRegistroVentas()){
            registros[0] = t.getId().toString();
            registros[1] = t.getListaProductos().size();
            registros[2] = String.valueOf(t.getFecha());
            registros[3] = String.valueOf(t.getTotal());
            model.addRow(registros);
        }
        table1.setModel(model);
    }
}
