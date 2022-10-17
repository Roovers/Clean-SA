package domain;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
        Connection con ;
        public void conectar() {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_cleansa","root","");
                JOptionPane.showMessageDialog(null, "se conecto");
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "error al conectarse");
            }

        }

}
