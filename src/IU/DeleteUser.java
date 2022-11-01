package IU;

import javax.swing.*;
import java.awt.*;

public class DeleteUser extends JDialog{
    private JPanel DeleteUserForm;

    public DeleteUser (JFrame parent) {
        super(parent);
        setTitle("ELIMINAR USERS");
        setContentPane(DeleteUserForm);
        setMinimumSize(new Dimension(500, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
