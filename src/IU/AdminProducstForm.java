package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminProducstForm extends JDialog {

    private JButton agregarUnProductoButton;
    private JPanel AdminProducstForm;
    private ListaDeProductos productosUtil = new ListaDeProductos();
    private JButton consultarUnProductoButton;
    private JButton eliminarUnProductoButton;
    private JButton editarUnProductoButton;
    private JButton buscarUnProductoButton;
    private JButton volverButton;
    private JLabel AdminProductsForm;

    public AdminProducstForm (JFrame parent) {
        super(parent);
        setTitle("ADMINISTRAR PRODUCTOS");
        setContentPane(AdminProducstForm);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        agregarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProducto addProductForm = new AgregarProducto(null, "admin");
            }
        });

        consultarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt( JOptionPane.showInputDialog( "Ingresa el ID del producto que deseas saber el stock" ));
                productosUtil.consultarStock( idProducto );
            }
        });
        eliminarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto =Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el id del producto que deseas eliminar"));
                if(productosUtil.borrarProducto( idProducto )){
                    JOptionPane.showMessageDialog(
                            null,
                            " El Producto se elimino correctamente ! ",
                            "BORRAR USUARIO", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/delete.png")));
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            " ERROR - El código ingresado es erroneo ",
                            "ERROR", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon(interfaz.class.getResource("/img/error.png")));
                }
            }
        });

        editarUnProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProductoAEditar = Integer.parseInt( JOptionPane.showInputDialog( "Ingresa el id del producto que deseas editar" ) );
                Producto p = productosUtil.buscarProducto(idProductoAEditar);
                if( p!= null ){
                    String id = p.getIdProducto().toString();
                    String nombre = p.getNombreDeProducto();
                    String detalle = p.getDetalle();
                    Integer precio = p.getPrecio();
                    Integer stock = p.getCantidad();
                    String nivelToxico = p.getNivelDeToxi();
                    EditarProducto editarProducto = new EditarProducto(null, "encargado");
                    editarProducto.setValues( new String[]{nombre,detalle,nivelToxico,stock.toString(),precio.toString(),id});
                    editarProducto.llenarCampos();
                    editarProducto.setVisible( true );
                    for(int i = 0; i < editarProducto.getValues().length; i++){
                        System.out.println(editarProducto.getValues()[i]);
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
               Producto p = productosUtil.buscarProducto( idProducto );
               if( p != null){
                   JOptionPane.showMessageDialog(null, p);
               }  else {
                   JOptionPane.showMessageDialog(null, "El producto no existe en el inventario");
               }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

//                MenuAdminForm menu = new MenuAdminForm(null);
            }
        });

        setVisible(true);
    }

}
