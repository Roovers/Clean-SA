package IU;

import domain.Usuario;
import negocios.ListaDeUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarUsuario extends JDialog {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtConfirmPassword;
    private JComboBox cargos;
    ListaDeUsuarios usuariosUtil = new ListaDeUsuarios();
    private String [] values = new String[4];
    private JButton editarButton;
    private JButton cancelarButton;
    private JPanel panel;
    public EditarUsuario(JFrame parent ) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panel);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        String [] opciones = {"Admin", "Encargado de Ventas","Encargado de Deposito","Empleado de Ventas","Empleado de Deposito"};
        for(int i = 0; i < opciones.length; i++){
            cargos.addItem(opciones[i]);
        }
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                int id = Integer.parseInt(values[0]);
                String password = txtPassword.getText();
                String cargo = cargos.getSelectedItem().toString();
                Usuario toEdit = null;
                switch (cargo.toLowerCase()){
                    case "admin":
                        toEdit = new Usuario(id,username, password, 0);
                        break;
                    case "encargado de ventas":
                        toEdit = new Usuario(id,username,password, 1);
                        break;
                    case "encargado de deposito":
                        toEdit = new Usuario(id,username, password, 2);
                        break;
                    case "empleado de ventas":
                        toEdit = new Usuario(id,username,password,3);
                        break;
                    case "empleado de deposito":
                        toEdit = new Usuario(id,username,password,4);
                        break;
                }
                if( password.equals(txtConfirmPassword.getText())){
                    usuariosUtil.editarUsuario(toEdit);
                    dispose();
                    JOptionPane.showMessageDialog(null, "EDITADO CORRECTAMENTE");

                } else {
                    JOptionPane.showMessageDialog(null, "ERROR , LAS PASSWORD NO COINCIDEN");
                }
            }
        });


    }
    void llenarCampos(){
        txtUsername.setText(values[1]);
        txtPassword.setText(values[2]);
        txtConfirmPassword.setText(values[2]);
        cargos.setSelectedItem(values[3]);
    }
    void vaciarCampos(){
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
