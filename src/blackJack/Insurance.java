package blackJack;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Insurance {

    public static boolean choice()
    {
        boolean insure = false;
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Do you want Insurance?", "Insurance",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION)
        {
            insure = false;
        }
        else if (response == JOptionPane.YES_OPTION)
        {
            insure = true;
        }
        else if (response == JOptionPane.CLOSED_OPTION)
        {
            insure = false;
        }
        return insure;

    }
}

