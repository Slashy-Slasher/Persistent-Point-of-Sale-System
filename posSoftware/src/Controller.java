import javax.naming.ldap.Control;
import javax.swing.*;

public class Controller
{
    public Model backend = new Model();
    public static void main(String[] args)
    {
        Controller col = new Controller();
        col.init();
    }

    public void init()
    {
        View test = new View();
        SwingUtilities.invokeLater(() -> test.createAndShowGUI());
    }

}
