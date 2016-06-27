package View.Sonstiges;

import Controller.Sonstiges.LoginController;
import Model.Sonstiges.LoginModel;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JPasswordField;

/**
 * Created by annelie on 13.06.16.
 */
public class LoginView extends JFrame implements Observer   {

    /// zum View gehoeriger Model
    public LoginModel _loginModel;
    /// zum View gehoeriger Controller
    private LoginController _loginControler;
    /*---------------------------------------------------------------------------------------
     * Konstruktur zum Setzung von MVC
     * @parm mode Login_Model: welches dargestellt werden soll .
     * @parm titel String: Name von der Fenster.
     *
     * ---------------------------------------------------------------------------------------*/
    public LoginView (LoginModel model,String titel ){
        //MODEL
        this._loginModel=model;
        this._loginModel.addObserver(this);
        //CONTROLLER
        _loginControler=new LoginController(this._loginModel,this);
        //VIEW
        super.setTitle("login");

        makeView();
    }


    /*---------------------------------------------------------------------------------------
     *    Dise funktion erzeugt ein Controler ,Empfaenger fuer Ereignis
     *
	 * ---------------------------------------------------------------------------------------
      private  LoginController makeControler(){
    	  LoginController _logincontroler =
      	  return _logincontroler;
      	  }
	/*---------------------------------------------------------------------------------------
	 *     Desinstaliert MVC
	 *
	 * ---------------------------------------------------------------------------------------*/
    public void release(){
        /// Contorler Desinstalieren
        //to do enchala
        /// Model Desinstalieren
        // to do enchlala
    }
    /*-----------------------------------------------------------------------------------------
  	 *   Ueberschreibt Interfacemehtode , legt Reaktion auf Aenderungen fest
  	 *
  	 * ---------------------------------------------------------------------------------------*/
    public void update(Observable m , Object o )
    {
        /// to do
    }
    /*---------------------------------------------------------------------------------------
  	 *
  	 *
  	 *---------------------------------------------------------------------------------------*/
    public static final String  ACTION_LOGIN="login";
    public JPasswordField _password_user;
    public JTextField userTextfield;
    public JLabel lblNewLabel;
    public JLabel label_user_password;
    private JPanel  make_contentPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(31, 89, 393, 139);
        panel.setLayout(null);
        panel.setSize(200, 300);

        label_user_password = new JLabel("User : ");
        label_user_password.setBounds(20, 38, 150, 30);
        panel.add(label_user_password);
        userTextfield = new JTextField();
        _password_user=new JPasswordField() ;
        userTextfield.setBounds(91, 41, 148, 25);
        _password_user.setBounds(91, 41, 148, 25);
        _password_user.addActionListener(_loginControler);
        _password_user.addKeyListener(_loginControler);
        _password_user.setVisible(false);
        userTextfield.addKeyListener( _loginControler );
        panel.add(userTextfield);
        panel.add(_password_user);
        lblNewLabel = new JLabel("Eingage  Leider nicht erfolgreich ");
        lblNewLabel.setEnabled(false);
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(91, 78, 253, 14);
        panel.add(lblNewLabel);

        JButton _btnnext = new JButton(ACTION_LOGIN);
        _btnnext.addActionListener(_loginControler);
        _btnnext.setBounds(250, 42, 89, 23);
        panel.add(_btnnext);


        return panel;
    }
    /*---------------------------------------------------------------------------------------
     *      Erzeugt View ,baut die Oeuberflaeche auf .
     *
     * ----------------------------------------------------------------------------------*/
    private void makeView()
    {
        /// ContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this. setResizable(false);
        this.setContentPane(make_contentPanel());
        /// Fenster  mit login_controler als Listner
        addWindowListener( _loginControler);
        setVisible(true);
    }
}
