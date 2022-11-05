package IU;

import domain.ItemTicket;
import domain.Producto;
import domain.Ticket;
import negocios.ListaDeProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class HacerVenta extends  JDialog{
    private JComboBox comboBox1;
    private JButton confirmarVentaButton;
    private JButton cancelarButton;
    private JTable table1;
    private JTextField textField1;
    private JButton agregarAlTicketButton;
    private JPanel panel;

    private List<ItemTicket> carrito = new ArrayList<ItemTicket>();

    private final ListaDeProductos productosUtil = new ListaDeProductos();

    public HacerVenta (JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panel);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        String [] nombres = productosUtil.listarNombres();
        for(int i = 0; i< nombres.length;i++){
            comboBox1.addItem(nombres[i]);
        }
        agregarAlTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto p = productosUtil.buscarPorNombre(comboBox1.getSelectedItem().toString());
                if (p.getCantidad() >= Integer.parseInt(textField1.getText())){
                    ItemTicket itemToAdd = new ItemTicket(p, Integer.parseInt(textField1.getText()));
                    carrito.add( itemToAdd );
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null,"No hay suficiente stock del producto seleccionado.\n Intenta con menos cantidad.");
                }
            }
        });
        confirmarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Ticket t = new Ticket(carrito, LocalDate.now());
                t.calcularTotal();
                productosUtil.generarVenta(t);
                limpiarTabla();
            }
        });
        setVisible(true);
    }
    public void llenarTabla(){
        String[]headers = {"NOMBRE","CANTIDAD"};
        DefaultTableModel model = new DefaultTableModel(null, headers);
        model.addRow(headers);
        String[] registros = new String[2];
        for (ItemTicket i : carrito){
           registros[0] = i.getProducto().getNombreDeProducto();
           registros[1] = String.valueOf(i.getCantidad());
            model.addRow(registros);
        }
        table1.setModel(model);
    }
    public void limpiarCampos(){
        textField1.setText("");
    }
    public void limpiarTabla(){
        carrito = new ArrayList<ItemTicket>();
        table1.setModel(new DefaultTableModel());
    }


}
