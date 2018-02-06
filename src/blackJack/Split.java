package blackJack;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Split {

    public static boolean choice()
    {
        boolean splitYN = false;
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Split?", "Split?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION)
        {
            splitYN = false;
        }
        else if (response == JOptionPane.YES_OPTION)
        {
            splitYN = true;
        }
        else if (response == JOptionPane.CLOSED_OPTION)
        {
            splitYN = false;
        }
        return splitYN;

    }
}

