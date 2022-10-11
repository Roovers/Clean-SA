package negocios;

import domain.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeUsuarios {

    List<Usuario> usuarios = new ArrayList<>();

    public void addUser(Usuario usuario) {
        usuarios.add(usuario);
    }



    public Usuario findByUserName (String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreDeUsuario().equals(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }
    /** return 0 = adminstrador
        return 1 = encargado ventas
        return 2 = encargado deposito
        return 3 = empleado ventas
        return 4 = empleado deposito **/

    public Integer login() {

        String nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario ");

        int intentosPass = 0;
        int intentosUser = 0;

        Usuario u = findByUserName(nombreUsuario);
        while (u == null && intentosUser < 3) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto");
            nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario ");
            u = findByUserName(nombreUsuario);
            intentosUser++;
        }
        if (u != null) {

            String password = JOptionPane.showInputDialog("Ingrese su password ");

            while (!u.getPassword().equals(password) && intentosPass < 3) {
                password = JOptionPane.showInputDialog("password incorrecta , vuelva a ingresarla ");
                intentosPass++;
            }
            if (intentosPass == 3) {
                JOptionPane.showMessageDialog(null, "contacte a un administrador");
                return 5;
            }
            switch (u.getNivelPermisos()) {
                case 0 : return 0;
                case 1 : return 1;
                case 2 : return 2;
                case 3 : return 3;
                case 4 : return 4;
            }
        }
        JOptionPane.showMessageDialog(null, "contacte a un administrador");
        return 5;
    }
}
