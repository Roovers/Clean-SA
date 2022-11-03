package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleadoDepositoForm extends JDialog{
    private JButton editarUnProductoButton;
    private JButton buscarUnProductoButton;
    private ListaDeProductos productosUtil = new ListaDeProductos();
    private JButton consultarInventarioButton;
    private JButton ingresarUnProductoAlButton;
    private JPanel panelEmplDeposito;
    private JButton cerrarSesionButton;
    public MenuEmpleadoDepositoForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEmplDeposito);
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

        ingresarUnProductoAlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProducto agregarProducto = new AgregarProducto(null, "empleado");
            }
        });
        consultarInventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaProductos tablaProductos = new TablaProductos(null, "NO PAR");
            }
        });
        editarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProductoAEditar = Integer.parseInt( JOptionPane.showInputDialog( "Ingresa el id del producto que deseas editar" ) );
                Producto p = productosUtil.buscarProducto(idProductoAEditar);
                if( p!= null ){
                    if ( !p.getNivelDeToxi().equalsIgnoreCase("alto")) {
                        String id = p.getIdProducto().toString();
                        String nombre = p.getNombreDeProducto();
                        String detalle = p.getDetalle();
                        Integer precio = p.getPrecio();
                        Integer stock = p.getCantidad();
                        String nivelToxico = p.getNivelDeToxi();
                        EditarProducto editarProducto = new EditarProducto(null, "empleado");
                        editarProducto.setValues(new String[]{nombre, detalle, nivelToxico, stock.toString(), precio.toString(), id});
                        editarProducto.llenarCampos();
                        editarProducto.setVisible(true);
                        for (int i = 0; i < editarProducto.getValues().length; i++) {
                            System.out.println(editarProducto.getValues()[i]);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes permisos para editar este producto");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto no existe");
                }
            }
        });

        buscarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del producto que deseas buscar"));
                productosUtil.buscarProductoComun( idProducto );
            }
        });
        setVisible(true);
    }
}
