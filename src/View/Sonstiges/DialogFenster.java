package View.Sonstiges;

import javax.swing.*;
import java.awt.*;

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

    public void intfoFenster (JFrame view, String message)
    {
        JOptionPane.showMessageDialog(view, message );

    }

    public void erfolgDialog(JFrame view, String message){

        JLabel erfolgEingabeLbl = new JLabel("<html>" + message + "</html>");
        erfolgEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon erfolgIcon = new ImageIcon("images/yes2.png");


        //custom title, custom icon
        JOptionPane.showMessageDialog(view,
                erfolgEingabeLbl,
                "Eingabe erfolgreich",
                JOptionPane.PLAIN_MESSAGE,
                erfolgIcon);


    }

    public void errorDialog(JFrame view, String message){

        JLabel fehlerEingabeLbl = new JLabel("<html>" + message +" </html>");
        fehlerEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon fehlerIcon = new ImageIcon("images/nope2.png");

        //custom title, custom icon
        JOptionPane.showMessageDialog(this,
                fehlerEingabeLbl,
                "Eingabe nicht erfolgreich",
                JOptionPane.PLAIN_MESSAGE,
                fehlerIcon);
    }

    public void infoDialog(JFrame view, String message){

        JLabel fehlerEingabeLbl = new JLabel("<html>" + message +" </html>");
        fehlerEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon fehlerIcon = new ImageIcon("images/warning.png");

        //custom title, custom icon
        JOptionPane.showMessageDialog(this,
                fehlerEingabeLbl,
                "Information",
                JOptionPane.PLAIN_MESSAGE,
                fehlerIcon);
    }
}
