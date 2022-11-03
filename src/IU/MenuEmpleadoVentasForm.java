package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleadoVentasForm extends JDialog {
    private JButton buscarUnProductoButton;
    private JButton hacerUnaVentaButton;
    private JButton consultarInventarioButton;
    private JPanel panelEmplVentas;
    private JButton cerrarSesionButton;

    public MenuEmpleadoVentasForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEmplVentas);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm lf = new LoginForm(null);
            }
        });
        setVisible(true);
    }
}
