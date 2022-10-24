package dao;

import domain.*;


import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ProductoDAO {


    public List<Ticket> listarVentas( ){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Ticket;";
        List<Ticket> lista = new ArrayList<Ticket>();
        Ticket t = null;
        try {
            Connection con =  Conexion.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while ( rs.next() ){

                t.setId(rs.getInt( "id_ticket"));
                t.setFecha(rs.getDate("fecha"));
                t.setTotal(rs.getInt( "total"));
                lista.add(t);
            }

        return lista;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }




    }


    public Producto buscarProductoPorId(int idProd){
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_producto, nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock FROM producto WHERE id_producto = ?"; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setInt(1, idProd);
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Producto p = null;
            if ( rs.next() ){                        // Lee los registros
                p = new Producto();
                p.setIdProducto( rs.getInt("id_producto"));
                p.setNombreDeProducto(rs.getString("nombre_producto"));
                p.setPrecio( rs.getInt("precio"));
                p.setDetalle( rs.getString("detalle_producto"));
                p.setNivelDeToxi( rs.getString("nivel_toxicidad"));
                p.setCantidad( rs.getInt("cantidad_en_stock"));
            }            con.close();

            return  p;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public int generarTicket( Ticket ticket ){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO bdd_cleansa.ticket ( fecha, total) VALUES ( ? , ? ); ";
        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
            pstmt.setString(1, ticket.getFecha().toString());
            pstmt.setInt(2, ticket.getTotal());
            int id = 0;
            int resultado =  pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if ( rs.next() ){
                 id = rs.getInt(1);
            }
            return id;
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public int consultarStockDeProducto( Integer idProducto ){
        PreparedStatement pstmt = null;
        String sql = "SELECT cantidad_en_stock FROM producto WHERE id_producto = ?";
        ResultSet rs = null;
        Integer stock = 0;
        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );

            pstmt.setInt(1,idProducto);



             rs = pstmt.executeQuery();
             if( rs.next() ){
                 stock = rs.getInt("cantidad_en_stock");
             }
              con.close();

             return stock;


        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void descontarStockPorVenta( Integer cantidad, Integer idProducto ){
        PreparedStatement pstmt = null;
        String sql = "UPDATE producto SET cantidad_en_stock = ? WHERE id_producto = ?";

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );

            pstmt.setInt(1,cantidad);
            pstmt.setInt(2, idProducto);


            int resultado =  pstmt.executeUpdate();

            con.close();
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void hacerVenta(ItemTicket venta, Integer idTicket){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO venta (fk_ticket, fk_producto) VALUES (?, ?)";

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );

//            pstmt.setInt(1,idTicket);
            pstmt.setInt(1, idTicket);
            pstmt.setInt(2, venta.getProducto().getIdProducto());

            int resultado =  pstmt.executeUpdate();

            con.close();
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void agregarProducto( Producto p ){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO bdd_cleansa.producto ( nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock, fk_deposito) VALUES ( ? , ? , ? , ? , ?, 1); ";

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setString(1, p.getNombreDeProducto());
            pstmt.setInt(2, p.getPrecio());
            pstmt.setString(3, p.getDetalle());
            pstmt.setString(4, p.getNivelDeToxi());
            pstmt.setInt(5, p.getCantidad());
            int resultado =  pstmt.executeUpdate();

            con.close();
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public void updateProducto( Producto p){
        PreparedStatement pstmt = null;
        String sql = "UPDATE bdd_cleansa.producto SET nombre_producto=?, precio=?, detalle_producto=?, nivel_toxicidad=?, cantidad_en_stock=? WHERE id_producto=?;";
        try {

            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, p.getNombreDeProducto());
            pstmt.setInt(2, p.getPrecio());
            pstmt.setString( 3, p.getDetalle());
            pstmt.setString(4, p.getNivelDeToxi());
            pstmt.setInt(5, p.getCantidad());
            pstmt.setInt(6, p.getIdProducto());
            int resultado = pstmt.executeUpdate();
            con.close();
        }catch ( Exception e ){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteProducto (int idProducto) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM bdd_cleansa.producto WHERE id_producto=?;" ;

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setInt(1, idProducto);
            int resultado = pstmt.executeUpdate();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void findAllProducts () {
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT (id_producto, nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock) FROM producto "; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Producto p = null;
            while ( rs.next() ){                        // Lee los registros
                p = new Producto();
                p.setIdProducto( rs.getInt("id_producto"));
                p.setNombreDeProducto(rs.getString("nombre"));
                p.setPrecio( rs.getInt("precio"));
                p.setDetalle( rs.getString("detalle"));
                p.setNivelDeToxi( rs.getString("nivel_toxico"));
                p.setCantidad( rs.getInt("cantidad"));
                JOptionPane.showMessageDialog(null, "P R O D U C T O " +
                        "\n------------------------" +
                        "\n Serial De Producto= " + p.getIdProducto() +
                        "\n Nombre De Producto= " + p.getNombreDeProducto() +
                        "\n Precio Del Producto= $" + p.getPrecio() +
                        "\n Detalle Del Producto= " + p.getDetalle() +
                        "\n Cantidad en stock= " + p.getCantidad() + " unidades" +
                        "\n Nivel De Toxicidad= " + p.getNivelDeToxi());
            }
            con.close();


        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
