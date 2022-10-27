package dao;

import domain.Conexion;
import domain.Usuario;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario buscarUsuarioPorId(int idUser){
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_usuario, nombre_usuario, password, nivel_permisos FROM Usuario WHERE id_usuario = ?"; // query.

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            System.out.println(sql);
            pstmt.setInt(1, idUser);
            rs = pstmt.executeQuery();                // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Usuario u = null;
            if ( rs.next() ){                        // Lee los registros
                u = new Usuario();
                u.setId( rs.getInt("id_usuario"));
                u.setNombreDeUsuario(rs.getString("nombre_usuario"));
                u.setPassword( rs.getString("password"));
                u.setNivelPermisos( rs.getInt("nivel_permisos"));
            }            con.close();

            return  u;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    public Usuario buscarUsuarioPorUsername(String  userName){
        PreparedStatement pstmt = null;                     // prepara la consulta antes de ejecutarla.
        ResultSet rs = null;                                // variable que guarda el resultadod e la query.
        String sql = "SELECT id_usuario, nombre_usuario, password, nivel_permisos FROM Usuario WHERE nombre_usuario = ?"; // query.
        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setString(1, userName );
            System.out.println(sql);
            rs = pstmt.executeQuery();                  // executeUpdate()  se usa solo para INSERT DELETE UPDATE ETC; // executeQuery() se usa para los SELECT;
            Usuario u = null;
            if ( rs.next() ){                        // Lee los registros
                u = new Usuario();
                u.setId( rs.getInt("id_usuario"));
                u.setNombreDeUsuario(rs.getString("nombre_usuario"));
                u.setPassword( rs.getString("password"));
                u.setNivelPermisos( rs.getInt("nivel_permisos"));
            }            con.close();

            return  u;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    public void darDeAltaUsuario( Usuario u ){
        PreparedStatement pstmt = null;
       String sql = "INSERT INTO bdd_cleansa.Usuario (nombre_usuario, password, nivel_permisos, fk_sector) VALUES (? , ?, ?, ?); ";

       try {
           Connection con = Conexion.getConnection();
           pstmt = con.prepareStatement( sql );
           pstmt.setString( 1, u.getNombreDeUsuario());
           pstmt.setString(2, u.getPassword());
           pstmt.setInt(3, u.getNivelPermisos());
           switch( u.getNivelPermisos() ){
               case 0:
                   pstmt.setInt(4, 2);
                   break;
               case 1:
                   pstmt.setInt(4, 1);
                   break;
               case 2:
                   pstmt.setInt(4, 3);
                   break;
               case 3:
                   pstmt.setInt(4, 1);
                   break;
               case 4:
                   pstmt.setInt(4, 3);
                   break;
               }
           System.out.println(sql);
          int resultado =  pstmt.executeUpdate();

              con.close();
       }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
       }
    }
    public void updateUsuario( Usuario u){
        PreparedStatement pstmt = null;
        String sql = "UPDATE bdd_cleansa.Usuario SET nombre_usuario=?, password=?, nivel_permisos=?,  fk_sector=? WHERE id_usuario=?;";
        try {

            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, u.getNombreDeUsuario());
            pstmt.setString( 2, u.getPassword());
            pstmt.setInt(3, u.getNivelPermisos());
            pstmt.setInt(5, u.getId());
            switch( u.getNivelPermisos() ){
                case 0:
                    pstmt.setInt(4, 2);
                    break;
                case 1:
                    pstmt.setInt(4, 1);
                case 2:
                    pstmt.setInt(4, 3);
                    break;
                case 3:
                    pstmt.setInt(4, 1);
                    break;
                case 4:
                    pstmt.setInt(4, 3);
                    break;
            }
            int resultado = pstmt.executeUpdate();
            con.close();
        }catch ( Exception e ){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteUsuario (int idUsuario) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM bdd_cleansa.Usuario WHERE id_usuario=?;" ;

        try {
            Connection con = Conexion.getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setInt(1, idUsuario);
            int resultado = pstmt.executeUpdate();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
