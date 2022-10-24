package negocios;

import IU.interfaz;
import dao.ProductoDAO;
import domain.ItemTicket;
import domain.Producto;
import domain.Ticket;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class ListaDeProductos {

    // Lista que contiene los productos en inventario.
    ArrayList<Producto> inventario = new ArrayList<Producto>();

    ArrayList<Ticket> registroVentas = new ArrayList<Ticket>();

    ProductoDAO productoDAO = new ProductoDAO();


    // Método que valida datos para agregar productos al inventario.
    public boolean addProducto(Producto producto) {
        if (producto.getIdProducto() > 0) {
            char[] arrLetras = producto.getNombreDeProducto().toCharArray();
            if (arrLetras.length >= 1 && arrLetras.length < 60) {
                arrLetras = producto.getNivelDeToxi().toCharArray();
                if (arrLetras.length > 0 && arrLetras.length <= 4) {
                    arrLetras = producto.getDetalle().toCharArray();
                    if (arrLetras.length > 5 && arrLetras.length < 200) {
                        if (producto.getPrecio() > 0) {
                            productoDAO.agregarProducto(producto);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    // Metodo que borra productos del inventario.
    public void borrarProducto(Integer idProducto) {
        Producto p = this.buscarProducto(idProducto);
        if (p != null) {

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estas seguro que deseas borrar este Producto?",
                    "Confirmacion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == 0) {
                productoDAO.deleteProducto(idProducto);
                JOptionPane.showMessageDialog(
                        null,
                        " El Producto se elimino correctamente ! ",
                        "BORRAR USUARIO", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/delete.png")));
            }
        }  else {
            JOptionPane.showMessageDialog(
                    null,
                    " ERROR - El código ingresado es erroneo ",
                    "ERROR", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/error.png")));
        }
    }


    // Método que busca productos en el inventario.
    public Producto buscarProducto(Integer idProducto){
        Producto p =   productoDAO.buscarProductoPorId(idProducto);
        if( p != null ){
            return p;
        }
        return null;
    }


    // Método que modifica productos del inventario mediante ingreso de datos.
    public void editarProducto(Integer idProducto){
            Producto producto = buscarProducto(idProducto);
            if (producto != null) {
                producto.setNombreDeProducto(JOptionPane.showInputDialog("Ingresa el nombre", producto.getNombreDeProducto()));
                producto.setPrecio(Integer.parseInt(JOptionPane.showInputDialog("Ingresa el precio", producto.getPrecio())));
                producto.setDetalle(JOptionPane.showInputDialog("Ingresa el detalle", producto.getDetalle()));
                producto.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad en stock", producto.getCantidad())));
                producto.setNivelDeToxi(JOptionPane.showInputDialog("Ingresa el nivel de toxicidad // ALTO o BAJO", producto.getNivelDeToxi()));
                productoDAO.updateProducto(producto);
                JOptionPane.showMessageDialog(null,"Producto editado correctamente");
                return;
            }
        JOptionPane.showMessageDialog(null,"No se encontro un producto con el serial ingresado");
            return;
        }

    // Método que imprime datos de productos ( con validación ).
    public void listarProductos () {
        productoDAO.findAllProducts();
    }


    // Método que imprime datos de productos PAR ( con validación ).
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

    // Método para consultar stock de productos .
    public void consultarStock(Integer idProducto){
        Producto p =  buscarProducto(idProducto);
        if(p != null){
            JOptionPane.showMessageDialog(null,"El stock del producto " + p.getNombreDeProducto().toUpperCase() + "  es de " + p.getCantidad() + " Unidades ");
        } else {
            JOptionPane.showMessageDialog(null,"No existe el producto con id " + idProducto + " en el inventario" );

        }
    }


    // Método para vender productos NO PAR ( con validación ) .
    public void venderUnProductoComun(Integer idProducto){
        Producto p = buscarProducto(idProducto);
        if(p != null) {
            if(!p.getNivelDeToxi().equalsIgnoreCase("alto")) {
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad que desea vender del producto " + p.getNombreDeProducto() + "\n" +
                        "Actualmente hay " + p.getCantidad() + " en stock"));
                if (cantidad > 0) {
                    if (cantidad > p.getCantidad()) {
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
                JOptionPane.showMessageDialog(null, "No tienes los permisos necesarios para vender este producto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No podes vender un producto que no existe en el inventario!");
        }
    }


    // Método que muestra stock de productos NO PAR en el inventario ( con validación ).
    public void consultarStockProductoComun(Integer idProducto){
        Producto p =  buscarProducto(idProducto);
        if(p != null){
            if(!p.getNivelDeToxi().equalsIgnoreCase("alto")) {
                JOptionPane.showMessageDialog(null, "El stock del producto " + p.getNombreDeProducto().toUpperCase() + "  es de " + p.getCantidad() + " Unidades ");
            } else{
                JOptionPane.showMessageDialog(null,"No tienes los permisos necesarios para acceder al stock de este producto" );
            }
        } else {
            JOptionPane.showMessageDialog(null,"No existe el producto con id " + idProducto + " en el inventario" );
        }
    }

    // Método que busca productos NO PAR en el inventario ( con validación ).
    public void buscarProductoComun(Integer idProducto){
        for (Producto p : this.inventario) {
            if (p.getIdProducto() == idProducto && p.getNivelDeToxi().equalsIgnoreCase("bajo")) {
                JOptionPane.showMessageDialog(null, p);
                return;
            }
            if (p.getIdProducto() == idProducto && p.getNivelDeToxi().equalsIgnoreCase("alto")) {
                JOptionPane.showMessageDialog(null, "No tienes los permisos necesarios para acceder a la informacion de este producto");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "El ID ingresado no correponde a ningun producto en el inventario");
        return;
    }

    public  void generarVenta(){
        Ticket t = new Ticket(1, new ArrayList<ItemTicket>(), LocalDate.now())
        ;
        int pregunta = 0;
        ItemTicket i = new ItemTicket();
        do {
            i = null;
            int codigoProducto = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea vender", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            Producto p = buscarProducto(codigoProducto);
            if(p != null) {
                int cantidad = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese la cantidad que desea vender del producto " + p.getNombreDeProducto() + "\n" +
                                "Actualmente hay " + p.getCantidad() + " en stock", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/cant.png")), null, null));
                if (cantidad > 0){
                    if(cantidad <= p.getCantidad()){
                        productoDAO.descontarStockPorVenta(p.getCantidad() - cantidad, p.getIdProducto());
                        i = new ItemTicket(p,cantidad);
                        t.agregarProductoAlTicket(i);
                        JOptionPane.showMessageDialog(null, " Producto Agregado Al ticket Exitosamernte", "PRODUCTO AGREGADO", JOptionPane.PLAIN_MESSAGE,
                                new ImageIcon(interfaz.class.getResource("/img/ticket.png")));
                    } else {
                        JOptionPane.showMessageDialog(null, " Cantidad insuficiente en el inventario!", "ERROR", JOptionPane.PLAIN_MESSAGE,
                                new ImageIcon(interfaz.class.getResource("/img/error.png")));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes vender 0 unidades de un producto!", "ERROR", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon(interfaz.class.getResource("/img/error.png")));
                }
            } else {
                JOptionPane.showMessageDialog(null, "No puedes vender un producto que no existe en el inventario!", "ERROR", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/error.png")));
            }
            pregunta = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea agregar otro Producto al Ticket? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/mas.png")), null, null));

        } while (pregunta == 1 );
        if(pregunta == 2){
            t.calcularTotal();
           int resultado =  productoDAO.generarTicket(t);

            for ( ItemTicket item : t.getListaProductos()){
                productoDAO.hacerVenta(item, resultado);
            }

            JOptionPane.showMessageDialog(null, "Venta finalizada exitosamente!", "VENTA FINALIZADA", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/ok.png")));
        }
    }

    public void editarProductoNoPar(Integer idProducto){

        for (Producto producto : this.inventario) {
            if (producto.getIdProducto() == idProducto) {
                if(!producto.getNivelDeToxi().equals("alto")) {
                    producto.setNombreDeProducto(JOptionPane.showInputDialog("Ingresa el nombre", producto.getNombreDeProducto()));
                    producto.setPrecio(Integer.parseInt(JOptionPane.showInputDialog("Ingresa el precio", producto.getPrecio())));
                    producto.setDetalle(JOptionPane.showInputDialog("Ingresa el detalle", producto.getDetalle()));
                    producto.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad en stock", producto.getCantidad())));
                    JOptionPane.showMessageDialog(null, "Producto editado correctamente");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No tiene permiso para esta acción");
               return;
            }
        }
        JOptionPane.showMessageDialog(null,"No se encontro un producto con el serial ingresado");
    }

    public void verRegistroVentas(){
        List<Ticket> listaVentas = productoDAO.listarVentas();

        for ( Ticket t : listaVentas ){
            JOptionPane.showMessageDialog(null, t.getId());
            JOptionPane.showMessageDialog(null, t.getFecha());
            JOptionPane.showMessageDialog(null, t.getTotal());

        }
    }

    // constructor
    public ArrayList<Producto> getInventario() {
        return inventario;
    }
}

