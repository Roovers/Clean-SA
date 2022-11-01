package IU;

import javax.swing.*;
import java.awt.*;

public class AdminProducstForm extends JDialog {

    private JButton agregarUnProductoButton;
    private JPanel AdminProducstForm;
    private JButton consultarUnProductoButton;
    private JButton eliminarUnProductoButton;
    private JButton editarUnProductoButton;
    private JButton buscarUnProductoButton;
    private JButton volverButton;
    private JButton cerrarSesionButton;
    private JLabel AdminProductsForm;

    public AdminProducstForm (JFrame parent) {
        super(parent);
        setTitle("ADMINISTRAR PRODUCTOS");
        setContentPane(AdminProducstForm);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

}
