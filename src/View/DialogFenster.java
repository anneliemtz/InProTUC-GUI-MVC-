package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Created by annelie on 02.06.16.
 */
public class DialogFenster extends JFrame{
    public DialogFenster(){}

    /**  Yes no Option Panel
     *
     * @param view
     * @param message
     * @return
     */
    public int dialogFensterFragen ( JFrame view, String message,String title ){
        int n = JOptionPane.showConfirmDialog(
                view,
                message,
                title,
                JOptionPane.YES_NO_OPTION);
        return n;
    }
    /**
     *
     * @param view
     * @param possibilities
     * @param defaultoption
     * @param message
     * @param title
     * @return
     */
    public String dialogFensterInput(JFrame view,Object[]  possibilities,String defaultoption ,String message,String title)
    {

        String s = (String)JOptionPane.showInputDialog(
                view,message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                defaultoption);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return "fehler";
    }


    public String dialogFensterInputText(JFrame view ,String message,String title)
    {

        String s = (String)JOptionPane.showInputDialog(
                view,message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return "fehler";
    }
}
