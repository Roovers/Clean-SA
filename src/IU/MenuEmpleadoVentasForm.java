package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleadoVentasForm extends JDialog {
    private JButton buscarUnProductoButton;
    private JButton hacerUnaVentaButton;
    private JButton consultarStockButton;
    private JPanel panelEmplVentas;

    private ListaDeProductos productosUtil = new ListaDeProductos();

    private JButton cerrarSesionButton;

    public MenuEmpleadoVentasForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEmplVentas);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        consultarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id =  Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del producto que deseas saber el stock"));
                Producto p = productosUtil.buscarProducto(id);
                if(p != null){
                    if ( !p.getNivelDeToxi().equalsIgnoreCase("alto")){
                        JOptionPane.showMessageDialog(null, "El stock del producto: " + p.getNombreDeProducto() +" es de "+ p.getCantidad() + " unidades.");
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "No tienes permisos para acceder al stock de este producto");
                } else {
                    JOptionPane.showMessageDialog(null, "El producto no esta en el inventario.");
                }
            }
        });
        buscarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto que deseas buscar");
                Producto p = productosUtil.buscarPorNombre(nombre);
                if(p != null){
                     if ( !p.getNivelDeToxi().equalsIgnoreCase("alto")){
                         JOptionPane.showMessageDialog(null, p);
                         return;
                     }
                    JOptionPane.showMessageDialog(null, "No tienes permisos para acceder a los detalles de este producto");
                } else {
                    JOptionPane.showMessageDialog(null, "El producto con nombre: " + nombre + " no esta en el inventario.");
                }
            }
        });
        hacerUnaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HacerVenta formVentas = new HacerVenta(null, "empleado");
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm lf = new LoginForm(null);
            }
        });
        setVisible(true);
    }
}
