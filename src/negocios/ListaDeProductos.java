package negocios;

import IU.interfaz;
import domain.Producto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaDeProductos {

    // Lista que contiene los productos en inventario.
    ArrayList<Producto> inventario = new ArrayList<Producto>();


    // Mertodo que agrega productos al inventario.
    public boolean addProducto(Producto producto) {
        if (producto.getIdProducto() > 0) {

            char[] arrLetras = producto.getNombreDeProducto().toCharArray();
            if (arrLetras.length >= 1 && arrLetras.length < 60) {
                arrLetras = producto.getNivelDeToxi().toCharArray();
                if (arrLetras.length > 0 && arrLetras.length <= 4) {
                    arrLetras = producto.getDetalle().toCharArray();
                    if (arrLetras.length > 5 && arrLetras.length < 200) {
                        if (producto.getPrecio() > 0) {
                            inventario.add(producto);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    // Mertodo que borra productos del inventario.
    public boolean borrarProducto(Integer idProducto) {
        if (idProducto > 0) {
            for (Producto p : this.inventario) {
                if (p.getIdProducto() == idProducto) {
                    inventario.remove(p);
                    return true;
                }
            }

        }
        return false;
    }


    // Mertodo que busca productos en el inventario.
    public Producto buscarProducto(Integer idProducto){
        for (Producto p : this.inventario) {
            if (p.getIdProducto() == idProducto) {
                return p;
            }
        }
        return null;
    }


    // Mertodo que modifica productos del inventario.
    public void editarProducto(Integer idProducto){
        for (Producto p : this.inventario) {
            if (p.getIdProducto() == idProducto) {
                p.setNombreDeProducto(JOptionPane.showInputDialog("Ingresa el nombre", p.getNombreDeProducto()));
                p.setPrecio(Integer.parseInt(JOptionPane.showInputDialog("Ingresa el precio", p.getPrecio())));
                p.setDetalle(JOptionPane.showInputDialog("Ingresa el detalle", p.getDetalle()));
                p.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad en stock", p.getCantidad())));
                p.setNivelDeToxi(JOptionPane.showInputDialog("Ingresa el nivel de toxicidad // ALTO o BAJO", p.getNivelDeToxi()));
                JOptionPane.showMessageDialog(null,"Producto editado correctamente");
                return;
            }

        }
        JOptionPane.showMessageDialog(null,"No se encontro un producto con el serial ingresado");
    }


    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public void listarProductos () {
        if (inventario.size() > 0) {
            for (Producto p : this.inventario) {
                JOptionPane.showMessageDialog(null, "P R O D U C T O " +
                        "\n------------------------" +
                        "\n Serial De Producto= " + p.getIdProducto() +
                        "\n Nombre De Producto= " + p.getNombreDeProducto() +
                        "\n Precio Del Producto= $" + p.getPrecio() +
                        "\n Detalle Del Producto= " + p.getDetalle() +
                        "\n Cantidad en stock= " + p.getCantidad() + " unidades" +
                        "\n Nivel De Toxicidad= " + p.getNivelDeToxi());
            }
        } else {
            JOptionPane.showMessageDialog(null, " No hay productos en el inventario ! ", "I N V E N T A R I O", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/fail.png")));
        }
    }

    public void listarProductosPar () {
        if (inventario.size() > 0) {
            for (Producto p : this.inventario) {
                if (!p.getNivelDeToxi().equals( "bajo")) {
                    JOptionPane.showMessageDialog(null, "P R O D U C T O " +
                            "\n------------------------" +
                            "\n Serial De Producto= " + p.getIdProducto() +
                            "\n Nombre De Producto= " + p.getNombreDeProducto() +
                            "\n Precio Del Producto= $" + p.getPrecio() +
                            "\n Detalle Del Producto= " + p.getDetalle() +
                            "\n Cantidad en stock= " + p.getCantidad() + " unidades" +
                            "\n Nivel De Toxicidad= " + p.getNivelDeToxi());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, " No hay productos en el inventario ! ", "I N V E N T A R I O", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/fail.png")));
        }
    }

    public void venderUnProducto(Integer idProducto){
        Producto p = buscarProducto(idProducto);
        if(p != null) {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad que desea vender del producto " + p.getNombreDeProducto() + "\n" +
                    "Actualmente hay " + p.getCantidad() + " en stock"));
            if(cantidad > 0){

                if(cantidad > p.getCantidad()){
                    JOptionPane.showMessageDialog(null, "Cantidad insuficiente en el inventario!");
                } else {
                    p.setCantidad(p.getCantidad() - cantidad);
                    JOptionPane.showMessageDialog(null, "Producto vendido exitosamente!");

                }
            } else {
                JOptionPane.showMessageDialog(null, "No puedes vender 0 unidades de un producto!");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes vender un producto que no existe en el inventario!");
        }
    }

    public void consultarStock(Integer idProducto){
        Producto p =  buscarProducto(idProducto);
        if(p != null){
            JOptionPane.showMessageDialog(null,"El stock del producto " + p.getNombreDeProducto().toUpperCase() + "  es de " + p.getCantidad() + " Unidades ");
        } else {
            JOptionPane.showMessageDialog(null,"No existe el producto con id " + idProducto + " en el inventario" );

        }
    }
}

