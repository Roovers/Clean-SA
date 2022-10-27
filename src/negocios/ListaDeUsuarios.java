package negocios;

import IU.interfaz;
import dao.UsuarioDAO;
import domain.Producto;
import domain.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeUsuarios {

    // Lista de usuarios.
    List<Usuario> usuarios = new ArrayList<Usuario>( );

    UsuarioDAO usuarioDAO = new UsuarioDAO();
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

    // Método que borra usuarios del sistema.
    public void borrarUsuario(Integer id) {
        Usuario u = this.buscarUsuario(id);
        if (u != null){
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estas seguro que deseas borrar este Usuario?",
                    "Confirmacion",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if( confirm == 0) {
                usuarioDAO.deleteUsuario(id);
                JOptionPane.showMessageDialog(
                        null,
                        " El Usuario se elimino correctamente ! ",
                        "BORRAR USUARIO", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/delete.png")));
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR - El Usuario ingresado es erroneo");
        }
    }


    // Método que valida ingreso de datos.
    public boolean addUser(Usuario usuario) {

        if (usuario.getId() > 0) {
            char[] arrLetras = usuario.getNombreDeUsuario().toCharArray();
            if (arrLetras.length >= 1 && arrLetras.length < 20) {
                arrLetras = usuario.getPassword().toCharArray();
                if (arrLetras.length > 0 && arrLetras.length <= 20) {
                    if (usuario.getNivelPermisos() >= 0 && usuario.getNivelPermisos() <= 4) {
                        usuarioDAO.darDeAltaUsuario(usuario);
                        JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
                        return true ;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
        return false ;
    }

    public void editarUsuario(Integer idUsuario){
        Usuario usuario = buscarUsuario(idUsuario);
        if( usuario != null){
            usuario.setNombreDeUsuario(JOptionPane.showInputDialog("Ingresa el nombre", usuario.getNombreDeUsuario()));
            usuario.setPassword(JOptionPane.showInputDialog("Ingresa la Clave", usuario.getPassword()));
            usuario.setNivelPermisos(Integer.parseInt(JOptionPane.showInputDialog("Ingresa el nivel de Permisos", usuario.getNivelPermisos())));
            usuarioDAO.updateUsuario(usuario);
            JOptionPane.showMessageDialog(null,"Se actualizo correctamente");
            return;
        }
        JOptionPane.showMessageDialog(null,"No se encontro un usuario con el ID Ingresado");
        return;
    }


    public Usuario buscarUsuario(Integer idUsuario){
//        for (Usuario usuario : this.usuarios) {
//            if (usuario.getId() == idUsuario) {
//                return usuario;
//            }
//        }
//        return null;
        return  usuarioDAO.buscarUsuarioPorId(idUsuario);
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

         String nombreUsuario = (String) JOptionPane.showInputDialog(null, "Ingrese su Nombre de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                 new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);
        int intentosPass = 0;
        int intentosUser = 0;

        Usuario u = usuarioDAO.buscarUsuarioPorUsername(nombreUsuario);
        while (u == null && intentosUser <= 2) {
            JOptionPane.showMessageDialog(null, " El nombre de usuario es incorrecto ", "LOGIN", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/error.png")));
            nombreUsuario = (String) JOptionPane.showInputDialog(null, "Ingrese su Nombre de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);
            u = usuarioDAO.buscarUsuarioPorUsername(nombreUsuario);
            intentosUser++;
        }
        if (u != null) {

            String password = (String) JOptionPane.showInputDialog(null, "Ingrese su Clave de Usuario :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/login.png")), null, null);

            while (!u.getPassword().equals(password) && intentosPass < 2) {
                password = (String) JOptionPane.showInputDialog(null, "Clave de Usuario incorrecta , vuelva a intentarlo :", "LOGIN", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/error.png")), null, null);
                intentosPass++;
            }
            if (intentosPass == 2) {
                JOptionPane.showMessageDialog(null, " Contacte con un Representante ", "LOGIN", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/login.png")));
                return 5;
            }
            JOptionPane.showMessageDialog(null,
                    "BIENVENIDO :" + " " + u.getNombreDeUsuario(),
                    "SESION INICIADA", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/tick.gif")));

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
