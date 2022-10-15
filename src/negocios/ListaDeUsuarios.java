package negocios;

import IU.interfaz;
import domain.Producto;
import domain.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeUsuarios {

    // Lista de usuarios.
    List<Usuario> usuarios = new ArrayList<>();

    public void listarUsuarios () {
        if (usuarios.size() > 0) {
            for (Usuario usuario : this.usuarios) {
                JOptionPane.showMessageDialog(null, "U S U A R I O  " +
                        "\n------------------------" +
                        "\n ID De Usuario = " + usuario.getId() +
                        "\n Nombre De Usuario = " + usuario.getNombreDeUsuario() +
                        "\n Password Del Usuario = " + usuario.getPassword() +
                        "\n Nivel de Permisos = " + usuario.getNivelPermisos());
            }
        } else {
            JOptionPane.showMessageDialog(null, " No hay productos en el inventario ! ", "I N V E N T A R I O", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/fail.png")));
        }
    }


    public boolean borrarUsuario(Integer id) {
        if (id > 0) {
            for (Usuario usuario : this.usuarios) {
                if (usuario.getId() == id) {
                    usuarios.remove(usuario);
                    return true;
                }
            }
        }
        return false;
    }


    // Método que valida ingreso de datos.
    public boolean addUser(Usuario usuario) {
        if (usuario.getId() > 0) {
            char[] arrLetras = usuario.getNombreDeUsuario().toCharArray();
            if (arrLetras.length >= 1 && arrLetras.length < 20) {
                arrLetras = usuario.getPassword().toCharArray();
                if (arrLetras.length > 0 && arrLetras.length <= 20) {
                    if (usuario.getNivelPermisos() >= 0 && usuario.getNivelPermisos() <= 4) {
                        usuarios.add(usuario);
                        return true ;
                    }
                }
            }

        }
        JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
        return false ;
    }

    public void editarUsuario(Integer idUsuario){

        for (Usuario usuario : this.usuarios) {
            if (usuario.getId() == idUsuario) {
                usuario.setNombreDeUsuario(JOptionPane.showInputDialog("Ingresa el nombre", usuario.getNombreDeUsuario()));
                usuario.setPassword(JOptionPane.showInputDialog("Ingresa la Clave", usuario.getPassword()));
                usuario.setNivelPermisos(Integer.parseInt(JOptionPane.showInputDialog("Ingresa el nivel de Permisos", usuario.getNivelPermisos())));
                JOptionPane.showMessageDialog(null,"Usuario editado correctamente");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"No se encontro un usuario con el ID Ingresado");
    }


    public Usuario buscarUsuario(Integer idUsuario){
        for (Usuario usuario : this.usuarios) {
            if (usuario.getId() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }


    // Método que recorre lista y busca usuarios.
    public Usuario findByUserName (String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreDeUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }


    // Método de logeo y validación de datos.
    public Integer login() {

        //String nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario ");

         String nombreUsuario = (String) JOptionPane.showInputDialog(null, "Ingrese su Nombre de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                 new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);

        int intentosPass = 0;
        int intentosUser = 0;

        Usuario u = findByUserName(nombreUsuario);
        while (u == null && intentosUser < 3) {
            JOptionPane.showMessageDialog(null, " El nombre de usuario es incorrecto ", "LOGIN", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/error.png")));
            nombreUsuario = (String) JOptionPane.showInputDialog(null, "Ingrese su Nombre de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);
            u = findByUserName(nombreUsuario);
            intentosUser++;
        }
        if (u != null) {

            String password = (String) JOptionPane.showInputDialog(null, "Ingrese su Clave de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);

            while (!u.getPassword().equals(password) && intentosPass < 3) {
                password = (String) JOptionPane.showInputDialog(null, "Clave de Usuario incorrecta , vuelva a intentarlo :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/error.png")), null, null);
                intentosPass++;
            }
            if (intentosPass == 3) {
                JOptionPane.showMessageDialog(null, " Contacte con un Representante ", "LOGIN", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/login.png")));
                return 5;
            }
            switch (u.getNivelPermisos()) {
                case 0 : return 0;
                case 1 : return 1;
                case 2 : return 2;
                case 3 : return 3;
                case 4 : return 4;
                /** return 0 = adminstrador
                 return 1 = encargado ventas
                 return 2 = encargado deposito
                 return 3 = empleado ventas
                 return 4 = empleado deposito **/
            }
        }
        JOptionPane.showMessageDialog(null, "contacte a un administrador");
        return 5;
    }


    public ListaDeUsuarios() {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
