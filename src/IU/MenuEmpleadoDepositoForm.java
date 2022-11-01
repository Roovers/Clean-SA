package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleadoDepositoForm extends JDialog{
    private JButton editarUnProductoButton;
    private JButton buscarUnProductoButton;
    private JButton consultarInventarioButton;
    private JButton ingresarUnProductoAlButton;
    private JPanel panelEmplDeposito;
    private JButton cerrarSesionButton;
    public MenuEmpleadoDepositoForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(panelEmplDeposito);
        setMinimumSize(new Dimension(600, 300));
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
