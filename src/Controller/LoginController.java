package Controller;

import Model.IndexUpdatePersonModel;
import Model.LoginModel;
import View.IndexUpdatePersonView;
import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.util.*;
import javax.swing.JLabel;

/**
 * Created by annelie on 13.06.16.
 */
public class LoginController extends WindowAdapter implements ActionListener,KeyListener{

    /// Modle Atribute
    private LoginModel _loginmodel;
    /// View Atribute
    private LoginView _loginview ;

    /*---------------------------------------------------------------------------------------
     * Konstruktur zum Setzung von MVC
     * @parm model Login_Model : das Model des Controlers  .
     * @parm view  login : das View des Controlers.
     *
     * ---------------------------------------------------------------------------------------*/
    public LoginController(LoginModel model,LoginView view){
        this._loginmodel=model;
        this._loginview=view;
    }

 /*---------------------------------------------------------------------------------------
  *      Verarbeiten von Eingaben
  *
  * ---------------------------------------------------------------------------------------*/

    public void actionPerformed(ActionEvent arg0) {
        String command =arg0.getActionCommand();
        /// Best�tigen das Button login  mit AktionListner falls das Boutton gedruckt wird
        if(command.equals(_loginview.ACTION_LOGIN))

            if(_loginview.label_user_password.getText().equals("User : "))
            {  // Datenbank abfragen .
                _loginmodel.find_user(_loginview.userTextfield.getText());
                if(_loginmodel.get_user()){
                    _loginview.label_user_password.setText("Password:");
                    _loginview._password_user.setVisible(true);
                    _loginview.userTextfield.setVisible(false);
                    if(_loginview.lblNewLabel.isEnabled()==true)
                        _loginview.lblNewLabel.setEnabled(false);
                }
                else
                {
                    _loginview._password_user.setText("");
                    _loginview.lblNewLabel.setEnabled(true);

                }
            }
            else if(_loginview.label_user_password.getText().equals("Password:"))
            {
                //password  abfragen.
                _loginmodel.find_Password(_loginview._password_user.getText());
                /// falls der User ein richtig Eingabe schreibt , dann eine neue Fenster wird eröfnnet
                if(_loginmodel.get_Password())
                {
                    _loginview.setVisible(false);
                    IndexUpdatePersonView index = new IndexUpdatePersonView(new IndexUpdatePersonModel(),"index");
                }
                else
                {
                    _loginview._password_user.setText("");
                    _loginview.lblNewLabel.setEnabled(true);
                }
            }

    }
    // Falls der User auf Enter drucken .
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {

            if(_loginview.label_user_password.getText().equals("User : "))
            {  // Datenbank abfragen .
                _loginmodel.find_user(_loginview.userTextfield.getText());
                if(_loginmodel.get_user()){
                    _loginview.label_user_password.setText("Password:");
                    _loginview._password_user.setVisible(true);
                    _loginview.userTextfield.setVisible(false);
                    if(_loginview.lblNewLabel.isEnabled()==true)
                        _loginview.lblNewLabel.setEnabled(false);
                }
                else
                {
                    _loginview._password_user.setText("");
                    _loginview.lblNewLabel.setEnabled(true);

                }
            }
            else if(_loginview.label_user_password.getText().equals("Password:"))
            {
                //password  abfragen.
                _loginmodel.find_Password(_loginview._password_user.getText());
                /// falls der User ein richtig Eingabe schreibt , dann eine neue Fenster wird eröfnnet
                if(_loginmodel.get_Password())
                {
                    _loginview.setVisible(false);
                    IndexUpdatePersonView index = new IndexUpdatePersonView(new IndexUpdatePersonModel(),"index");
                    //Index index =new Index(new IndexModel(),"index" );

                }
                else
                {
                    _loginview._password_user.setText("");
                    _loginview.lblNewLabel.setEnabled(true);
                }
            }



        }

    }
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
