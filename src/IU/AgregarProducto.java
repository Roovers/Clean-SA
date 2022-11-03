package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarProducto extends JDialog {
    private JButton btnAgregarProd;
    private JPanel panel1;
    private JTextField txtNombreProducto;
    private JTextField txtDetalleProducto;
    private JTextField txtPrecio;
    private JTextField txtCantidadEnStock;

    private JButton btnCancel;
    private JComboBox opcionesNivelToxico;

    public  AgregarProducto(JFrame parent, String cargo){
        super(parent);
        setTitle("Agregar un producto");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String [] opciones = {"alto", "bajo"};
        if ( cargo.equalsIgnoreCase("encargado") || cargo.equalsIgnoreCase("admin")){
            opcionesNivelToxico.addItem(opciones[0]);
            opcionesNivelToxico.addItem(opciones[1]);
        } else {
            opcionesNivelToxico.addItem(opciones[1]);
        }
        btnAgregarProd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
                limpiarCampos();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public void agregarProducto(){
        ListaDeProductos l =  new ListaDeProductos();
        String nombre = txtNombreProducto.getText();
        int precio = Integer.parseInt(txtPrecio.getText());
        String detalle = txtDetalleProducto.getText();
        int stock =Integer.parseInt(txtCantidadEnStock.getText());
        String niveltoxi = opcionesNivelToxico.getSelectedItem().toString();
        Producto p = new Producto(nombre,precio,detalle,stock,niveltoxi);
        if( l.addProducto(p) ){
            JOptionPane.showMessageDialog(null,"Agregado correctamente");
            dispose();
        }else {
            JOptionPane.showMessageDialog(null, "Alguno de los campos es erroneo");
        }

    }
    public void limpiarCampos(){
        txtDetalleProducto.setText("");
        txtCantidadEnStock.setText("");
        txtPrecio.setText("");
        txtNombreProducto.setText("");
    }
}
