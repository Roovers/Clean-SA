package IU;

import domain.Producto;
import negocios.ListaDeProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class TablaProductos extends  JDialog {
    private JPanel panel1;
    private JTable tabla;
    private ListaDeProductos productosUtil = new ListaDeProductos();
    public TablaProductos (JFrame parent, String categoria) {

        if( categoria.equalsIgnoreCase("todos")){
            llenarTablaTodos();
        } else if( categoria.equalsIgnoreCase("PAR")){
            llenarTablaPar();
        } else {
            llenarTablaNoPar();
        }
        setVisible(true);
    }
    void llenarTablaTodos() {
        String[]headers = {"ID","NOMBRE","DETALLE","PRECIO","STOCK","NIVEL TOXICO"};
        String[] registros = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, headers);
        model.addRow(headers);
        for (Producto p : productosUtil.listarProductos()){
            registros[0] = p.getIdProducto().toString();
            registros[1] = p.getNombreDeProducto();
            registros[2] = p.getDetalle();
            registros[3] =p.getPrecio().toString();
            registros[4] = p.getCantidad().toString();
            registros[5] = p.getNivelDeToxi();
            model.addRow(registros);
        }
        tabla.setModel(model);
    }
    void llenarTablaPar() {
        String[]headers = {"ID","NOMBRE","DETALLE","PRECIO","STOCK","NIVEL TOXICO"};
        String[] registros = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, headers);
        model.addRow(headers);
        for (Producto p : productosUtil.listarProductosPar()){
            registros[0] = p.getIdProducto().toString();
            registros[1] = p.getNombreDeProducto();
            registros[2] = p.getDetalle();
            registros[3] =p.getPrecio().toString();
            registros[4] = p.getCantidad().toString();
            registros[5] = p.getNivelDeToxi();
            model.addRow(registros);
        }
        tabla.setModel(model);
    }
    void llenarTablaNoPar() {
        String[]headers = {"ID","NOMBRE","DETALLE","PRECIO","STOCK","NIVEL TOXICO"};
        String[] registros = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, headers);
        model.addRow(headers);
        for (Producto p : productosUtil.listarProductosNoPar()){
            registros[0] = p.getIdProducto().toString();
            registros[1] = p.getNombreDeProducto();
            registros[2] = p.getDetalle();
            registros[3] =p.getPrecio().toString();
            registros[4] = p.getCantidad().toString();
            registros[5] = p.getNivelDeToxi();
            model.addRow(registros);
        }
        tabla.setModel(model);
    }
}
