package domain;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

        private static Connection con = null;

        public static Connection getConnection(){
            try{
                if( con == null || con.isClosed() ){      // valida que la conexion no sea nula y que NO este cerrada
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_cleansa","root","");

                }
                return con;
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
}
