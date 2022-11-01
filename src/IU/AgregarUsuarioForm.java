package IU;

import javax.swing.*;
import java.awt.*;

public class AgregarUsuarioForm extends JDialog {
    private JPanel AgregarUsuarioForm;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton cerrarSesionButton;
    private JButton agregarUsuarioButton;

    public AgregarUsuarioForm (JFrame parent) {
        super(parent);
        setTitle("AGREGAR USUARIOS");
        setContentPane(AgregarUsuarioForm);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
