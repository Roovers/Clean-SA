package IU;

import domain.Producto;
import domain.Ticket;
import negocios.ListaDeProductos;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEncargadoVentasForm extends JDialog {
    private JPanel EncVentas;
    private JButton buscarProductoButton;
    private JButton consultarStockButton;
    private JButton hacerUnaVentaButton;
    private JButton verRegistroDeVentasButton;
    private JButton cerrarSesionButton;

    private ListaDeProductos productosUtil = new ListaDeProductos();

    public MenuEncargadoVentasForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(EncVentas);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm lf = new LoginForm(null);
            }
        });
        hacerUnaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HacerVenta formVentas = new HacerVenta(null,"encargado");
            }
        });
        consultarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto que desea consultar stock"));
                productosUtil.consultarStock(idProducto);
            }
        });
        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto que desea buscar"));
                Producto p = productosUtil.buscarProducto(idProducto);
                if(p != null){
                    JOptionPane.showMessageDialog(null, p);
                } else{
                    JOptionPane.showMessageDialog(null, "El producto no esta en el inventario");
                }
            }
        });
        verRegistroDeVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ticket> listaVentas =  productosUtil.verRegistroVentas();
                for ( Ticket t : listaVentas ){
                    JOptionPane.showMessageDialog(null,
                            "ID de la venta : " + t.getId() +
                                    "\nPRODUCTOS : " + t.getListaProductos()+
                                    "\nFECHA DE LA VENTA : " + t.getFecha() +
                                    "\nMONTO TOTAL : $ " + t.getTotal(),
                            "TICKET", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon(interfaz.class.getResource("/img/ticket.png")));
                }
            }
        });
        setVisible(true);
    }
}
