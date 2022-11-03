package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarProducto extends  JDialog {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidadEnStock;
    private JTextField txtDetalle;
    private JComboBox txtNivelToxico;
    private JButton editarButton;
    private JButton cancelarButton;
    private JPanel panelEditProducto;
    private ListaDeProductos productosUtil = new ListaDeProductos();
    private String [] values = new String[6];

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public EditarProducto(JFrame parent, String cargo ) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEditProducto);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        String [] opciones = {"alto", "bajo"};
        if( cargo.equalsIgnoreCase("encargado")) {
            txtNivelToxico.addItem(opciones[0]);
            txtNivelToxico.addItem(opciones[1]);
        } else {
            txtNivelToxico.addItem(opciones[1]);
        }
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nombre = txtNombre.getText();
               String detalle =  txtDetalle.getText();
               String nivelToxico = txtNivelToxico.getSelectedItem().toString();
               Integer cantEnStock = Integer.parseInt(txtCantidadEnStock.getText());
               Integer precio = Integer.parseInt(txtPrecio.getText());
               Integer id = Integer.parseInt( values[5]);
               Producto p = new Producto(id,nombre,precio,detalle,cantEnStock,nivelToxico);
               productosUtil.editarProducto(p);
               dispose();
            }
        });
    }
    void llenarCampos(){
        txtNombre.setText(values[0]);
        txtDetalle.setText(values[1]);
        txtNivelToxico.setSelectedItem(values[2]);
        txtCantidadEnStock.setText(values[3]);
        txtPrecio.setText(values[4]);
    }
}
