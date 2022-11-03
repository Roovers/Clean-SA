package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdminForm extends JDialog {
    private JButton btnAdministrarProductos;
    private JButton btnAdministrarUsuarios;
    private JButton btnCerrarSesion;
    private JPanel MenuAdminForm;

    public MenuAdminForm (JFrame parent) {
        super(parent);
        setTitle("MENU ADMINISTRADOR");
        setContentPane(MenuAdminForm);
        setMinimumSize(new Dimension(300, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        btnAdministrarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUsersForm adminUsersForm = new AdminUsersForm(null);
            }
        });
        btnAdministrarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminProducstForm adminProducstForm = new AdminProducstForm(null);
            }
        });
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                LoginForm lf = new LoginForm(null);
            }
        });
        setVisible(true);
    }
}
