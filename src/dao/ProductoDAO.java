package dao;

import IU.interfaz;
import domain.*;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

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
        String sql = "INSERT INTO venta (fk_ticket, fk_producto, unidades) VALUES (?, ?, ?)";

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );

            pstmt.setInt(1, idTicket);
            pstmt.setInt(2, venta.getProducto().getIdProducto());
            pstmt.setInt(3, venta.getCantidad());

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
    public List<Producto> findAllProducts () {
        ArrayList<Producto> inventario = new ArrayList<>();
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_producto, nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock " +
                "FROM producto;" ;

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Producto p = null;
            while ( rs.next() ){                        // Lee los registros
                p = new Producto();
                p.setIdProducto( rs.getInt("id_producto"));
                p.setNombreDeProducto(rs.getString("nombre_producto"));
                p.setPrecio( rs.getInt("precio"));
                p.setDetalle( rs.getString("detalle_producto"));
                p.setNivelDeToxi( rs.getString("nivel_toxicidad"));
                p.setCantidad( rs.getInt("cantidad_en_stock"));
                inventario.add(p);
            }
            con.close();
            return inventario;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public List<Producto> findAllParProducts () {
        ArrayList<Producto> inventario = new ArrayList<>();
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_producto, nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock " +
                "FROM producto " +
                "WHERE nivel_toxicidad = 'alto' "; // query.
        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Producto p = null;
            while ( rs.next() ){                        // Lee los registros
                p = new Producto();
                p.setIdProducto( rs.getInt("id_producto"));
                p.setNombreDeProducto(rs.getString("nombre_producto"));
                p.setPrecio( rs.getInt("precio"));
                p.setDetalle( rs.getString("detalle_producto"));
                p.setNivelDeToxi( rs.getString("nivel_toxicidad"));
                p.setCantidad( rs.getInt("cantidad_en_stock"));
                inventario.add(p);
            }
            con.close();
            return inventario;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Producto> findAllNoParProducts () {
        ArrayList<Producto> inventario = new ArrayList<>();
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_producto, nombre_producto, detalle_producto, precio, cantidad_en_stock, nivel_toxicidad " +
                "FROM producto "+
                "WHERE nivel_toxicidad = 'bajo'"; // query.
        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Producto p = null;
            while ( rs.next() ){                        // Lee los registros
                p = new Producto();
                p.setIdProducto( rs.getInt("id_producto"));
                p.setNombreDeProducto(rs.getString("nombre_producto"));
                p.setPrecio( rs.getInt("precio"));
                p.setDetalle( rs.getString("detalle_producto"));
                p.setNivelDeToxi( rs.getString("nivel_toxicidad"));
                p.setCantidad( rs.getInt("cantidad_en_stock"));
                inventario.add(p);
            }
            con.close();
            return inventario;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Ticket> findAllTickets () {
        List <Ticket> listaDeTickets = new ArrayList<Ticket>();
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT * FROM ticket "; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Ticket t = null;
            while ( rs.next() ){                        // Lee los registros
                t = new Ticket();
                t.setId( rs.getInt("id_ticket"));
                t.setFecha(rs.getDate("fecha"));
                t.setTotal( rs.getInt("total"));
                listaDeTickets.add(t);

            }
            con.close();
            rs.close();
            return listaDeTickets;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<ItemTicket> buscarVentasPorTickets (int idTicket) {
        List <ItemTicket> listaDeVentas = new ArrayList<ItemTicket>();
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT * FROM venta " +
                "INNER JOIN producto " +
                "ON venta.fk_producto = producto.id_producto " +
                "WHERE fk_ticket = ?"; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setInt(1, idTicket);
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            ItemTicket item = null;

            while ( rs.next() ){                        // Lee los registros
                item = new ItemTicket();
                item.setProducto(new Producto(rs.getInt("id_producto"),rs.getString("nombre_producto")));
                item.setCantidad(rs.getInt("unidades"));
                listaDeVentas.add(item);
            }
            con.close();
            rs.close();
            return listaDeVentas;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Producto buscarProductoPorNombre(String nombre){
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_producto, nombre_producto, precio, detalle_producto, nivel_toxicidad, cantidad_en_stock FROM producto WHERE nombre_producto = ?"; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setString(1, nombre);
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

}
