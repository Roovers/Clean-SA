package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUsersForm extends JDialog{
    private JPanel AdminUsersForm;
    private JButton agregarUsuarioButton;
    private JButton consultarUsuarioButton;
    private JButton eliminarUnUsuarioButton;
    private JButton editarUnUsuarioButton;
    private JButton buscarUnUsuarioButton;
    private JButton volverButton;
    private JButton cerrarSesionButton;

    public AdminUsersForm (JFrame parent) {
        super(parent);
        setTitle("ADMINISTRAR USUARIOS");
        setContentPane(AdminUsersForm);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
