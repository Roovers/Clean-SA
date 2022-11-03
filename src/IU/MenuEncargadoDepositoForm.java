package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEncargadoDepositoForm extends JDialog{
    private JButton editarUnProductoButton;
    private JButton listarProductosPARButton;
    private JButton borrarUnProductoButton;
    private JButton consultarInventarioButton;
    private JButton ingresarUnProductoAlButton;
    private JPanel panelEncDeposito;
    private JButton cerrarSesionButton;

    ListaDeProductos productosUtil = new ListaDeProductos();

    public MenuEncargadoDepositoForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEncDeposito);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        ingresarUnProductoAlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProducto formAgregar = new AgregarProducto(null,"encargado");
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm lf = new LoginForm(null);
            }
        });
        borrarUnProductoButton.addActionListener(new ActionListener() {
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
                };
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


        consultarInventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaProductos tabla = new TablaProductos(null,"todos");
            }
        });

        listarProductosPARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaProductos tabla = new TablaProductos(null,"PAR");
            }
        });
        setVisible(true);
    }
}
