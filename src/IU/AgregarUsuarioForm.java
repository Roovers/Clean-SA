package IU;

import domain.Usuario;
import negocios.ListaDeUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarUsuarioForm extends JDialog {
    private JPanel AgregarUsuarioForm;
    private JTextField textField1;
    private JPasswordField passwordField1;
    ListaDeUsuarios usuariosUtil = new ListaDeUsuarios();
    private JButton volverButton;
    private JButton agregarUsuarioButton;
    private JComboBox comboBox1;

    public AgregarUsuarioForm (JFrame parent) {
        super(parent);
        setTitle("AGREGAR USUARIOS");
        setContentPane(AgregarUsuarioForm);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        String [] opciones = {"Admin", "Encargado de Ventas","Encargado de Deposito","Empleado de Ventas","Empleado de Deposito"};
        comboBox1.addItem(opciones[0]);
        comboBox1.addItem(opciones[1]);
        comboBox1.addItem(opciones[2]);
        comboBox1.addItem(opciones[3]);
        comboBox1.addItem(opciones[4]);

        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = passwordField1.getText();
                String nivelPermisos = comboBox1.getSelectedItem().toString();
                Usuario toAdd = null;
                switch (nivelPermisos.toLowerCase()){
                    case "admin":
                       toAdd = new Usuario(username, password, 0);
                        System.out.println(toAdd);
                        break;
                    case "encargado de ventas":
                        toAdd = new Usuario(username,password, 1);
                        break;
                    case "encargado de deposito":
                        toAdd = new Usuario(username, password, 2);
                        break;
                    case "empleado de ventas":
                        toAdd = new Usuario(username,password,3);
                        break;
                    case "empleado de deposito":
                       toAdd = new Usuario(username,password,4);
                        break;
                }
                if (usuariosUtil.addUser(toAdd)){
                    JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "ALGUNO DE LOS CAMPOS COMPLETADOS ES INVALIDO");
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
   void limpiarCampos(){
        textField1.setText("");
        passwordField1.setText("");
   }
}
