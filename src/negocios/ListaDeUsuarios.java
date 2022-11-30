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

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Método que borra usuarios del sistema.
    public void borrarUsuario(Integer id) {
        usuarioDAO.deleteUsuario(id);
    }


    // Método que valida ingreso de datos.
    public boolean addUser(Usuario usuario) {


            char[] arrLetras = usuario.getNombreDeUsuario().toCharArray();
            if (arrLetras.length >= 1 && arrLetras.length < 20) {
                arrLetras = usuario.getPassword().toCharArray();
                if (arrLetras.length > 0 && arrLetras.length <= 20) {
                    if (usuario.getNivelPermisos() >= 0 && usuario.getNivelPermisos() <= 4) {
                        usuarioDAO.darDeAltaUsuario(usuario);
                        return true ;
                    }
                }
            }

        JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
        return false ;
    }

    public void editarUsuario(Usuario u){
        usuarioDAO.updateUsuario(u);
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
    public Usuario buscarUsuarioPorUsername(String username){

        return usuarioDAO.buscarPorUsername( username );
    }
    // Método de logeo y validación de datos.
    public Integer login(String nombreUsuario, String password) {
        Usuario u = usuarioDAO.buscarUsuarioPorUsernameYPassword(nombreUsuario, password);
            if( u != null ){
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
        return 5;
    }
}
