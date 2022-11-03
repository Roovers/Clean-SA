package IU;

import domain.EncargadoDeVentas;
import negocios.ListaDeUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog {
    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JButton btnIniciarSesion;
    private JButton btnSalir;
    private JPanel LoginPanel;

    private ListaDeUsuarios listaUsers =  new ListaDeUsuarios();

    public LoginForm (JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (login()) {
                    case 0:
                        dispose();
                        MenuAdminForm adminForm = new MenuAdminForm(null);
                        break;
                    case 1:
                        dispose();
                        MenuEncargadoVentasForm encVentasForm = new MenuEncargadoVentasForm(null);
                        break;
                    case 2:
                        dispose();
                        MenuEncargadoDepositoForm encDepositoForm = new MenuEncargadoDepositoForm(null);
                        break;
                    case 3:
                        dispose();
                        MenuEmpleadoVentasForm menuEmpleadoVentasForm = new MenuEmpleadoVentasForm(null);
                        break;
                    case 4:
                        dispose();
                        MenuEmpleadoDepositoForm menuEmpleadoDepositoForm = new MenuEmpleadoDepositoForm(null);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Alguno de los datos ingresados no es valido");
                        break;
                }

            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        setVisible(true);

    }

    public int login(){
        int usuario = listaUsers.login(tfUsuario.getText(),tfPassword.getText());
        return usuario;
    }

}
