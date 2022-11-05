package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEncargadoVentasForm extends JDialog {
    private JPanel EncVentas;
    private JButton buscarProductoButton;
    private JButton consultarStockButton;
    private JButton hacerUnaVentaButton;
    private JButton verRegistroDeVentasButton;
    private JButton cerrarSesionButton;

    public MenuEncargadoVentasForm(JFrame parent) {
        super(parent);
        setTitle("INICIAR SESION");
        setContentPane(EncVentas);
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
        hacerUnaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HacerVenta formVentas = new HacerVenta(null);
            }
        });
        setVisible(true);
    }
}
