package IU;

import domain.Producto;
import domain.Usuario;
import negocios.ListaDeUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUsersForm extends JDialog{
    private JPanel AdminUsersForm;
    private JButton agregarUsuarioButton;
    private JButton eliminarUnUsuarioButton;
    private JButton editarUnUsuarioButton;
    private JButton buscarUnUsuarioButton;
    private JButton volverButton;

    private ListaDeUsuarios usuariosUtil = new ListaDeUsuarios();

    public AdminUsersForm (JFrame parent) {
        super(parent);
        setTitle("ADMINISTRAR USUARIOS");
        setContentPane(AdminUsersForm);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarUsuarioForm formAddUser = new AgregarUsuarioForm(null);
            }
        });
        buscarUnUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Ingresa el nombre del usuario que quieras buscar");
                Usuario u =  usuariosUtil.buscarUsuarioPorUsername( username );

             if( u != null){
                 switch (u.getNivelPermisos()){
                     case 0:
                         JOptionPane.showMessageDialog(null, "------USUARIO------\n" +
                                 "-Nombre de Usuario: " + u.getNombreDeUsuario() +
                                 "\n-Password: " + u.getPassword() +
                                 "\n-Cargo: Administrador");
                         break;
                     case 1:
                         JOptionPane.showMessageDialog(null, "------USUARIO------\n" +
                                 "-Nombre de Usuario: " + u.getNombreDeUsuario() +
                                 "\n-Password: " + u.getPassword() +
                                 "\n-Cargo: Encargado de Ventas");
                         break;
                     case 2:
                         JOptionPane.showMessageDialog(null, "------USUARIO------\n" +
                                 "-Nombre de Usuario: " + u.getNombreDeUsuario() +
                                 "\n-Password: " + u.getPassword() +
                                 "\n-Cargo: Encargado de Deposito");
                         break;
                     case 3:
                         JOptionPane.showMessageDialog(null, "------USUARIO------\n" +
                                 "-Nombre de Usuario: " + u.getNombreDeUsuario() +
                                 "\n-Password: " + u.getPassword() +
                                 "\n-Cargo: Empleado de Ventas");
                         break;
                     case 4:
                         JOptionPane.showMessageDialog(null, "      ------USUARIO------\n" +
                                 "-Nombre de Usuario: " + u.getNombreDeUsuario() +
                                 "\n-Password: " + u.getPassword() +
                                 "\n-Cargo: Empleado de Deposito");
                         break;
                 }

             } else {
                 JOptionPane.showMessageDialog(null, "El usuario con nombre: " + username + " no esta registrado en el sistema.");
             }
            }
        });
        eliminarUnUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del usuario que deseas eliminar"));
                Usuario u = usuariosUtil.buscarUsuario(id);
                if (u != null){
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "¿Estas seguro que deseas borrar este Usuario?",
                            "Confirmacion",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if( confirm == 0) {
                        usuariosUtil.borrarUsuario(id);
                        JOptionPane.showMessageDialog(
                                null,
                                " El Usuario se elimino correctamente ! ",
                                "BORRAR USUARIO", JOptionPane.DEFAULT_OPTION,
                                new ImageIcon(interfaz.class.getResource("/img/delete.png")));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR - El ID ingresado no corresponde a ningun usuario registrado en el sistema.");
                }
            }
        });
        editarUnUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int usuarioAEditar = Integer.parseInt( JOptionPane.showInputDialog( "Ingresa el id del usuario que deseas editar" ) );
                Usuario u = usuariosUtil.buscarUsuario(usuarioAEditar);
                if( u != null ){
                    String id = u.getId().toString();
                    String nombreUsuario = u.getNombreDeUsuario();
                    String password = u.getPassword();
                    String cargo = "";
                    switch (u.getNivelPermisos()){
                        case 0:
                            cargo = "Admin";
                            break;
                        case 1:
                            cargo = "Encargado de Ventas";
                            break;
                        case 2:
                            cargo = "Encargado de Deposito";
                            break;
                        case 3:
                            cargo = "Empleado de Ventas";
                            break;
                        case 4:
                            cargo = "Empleado de Deposito";
                            break;
                    }
                    EditarUsuario editarUsuario = new EditarUsuario(null);
                    editarUsuario.setValues( new String[]{id,nombreUsuario,password,cargo});
                    editarUsuario.llenarCampos();
                    editarUsuario.setVisible( true );
                    for(int i = 0; i <editarUsuario.getValues().length; i++){
                        System.out.println(editarUsuario.getValues()[i]);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no existe");
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
