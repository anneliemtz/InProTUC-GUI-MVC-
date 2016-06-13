package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by annelie on 13.06.16.
 */
public class LoginModel extends Observable  {

    /// to do enchala
    Connection  _conn;
    PreparedStatement pst;
    private boolean  exist_user=false,exist_password=false;
    /// Konstuktor einlogen zu Daten Bank
    public LoginModel(){
        // Verbindung zum Datenbank hestellen.
        Connection_DB _connection=new Connection_DB();
        _conn=_connection.getConnection();
    }
    /***
     * Dise funktion macht ein Abfrage zum daten bank und sucht ob das user in diese Daten Bank existieret oder nicht
     * @param user:String , mit diesem Parameter versuche ich ob  ein User in daten Bank hat das gleich name wie user falls ja :
     * dann werden die  boolean Variabele exist_user auf true ersetzen .
     */
    public  void   find_user(String user)
    {
        try{
            // Query
            String query ="SELECT username FROM users WHERE username=?";
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=_conn.prepareStatement(query);
            // Query wird modifiziert
            pst.setString(1,user);
            // ein Abfrage auf die Tabelle Users_password erzeugen .
            ResultSet rs=pst.executeQuery();
            /// hier ueberpruefen ob das user existiet oder nicht
            while(rs.next())
                exist_user=true;
            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }
    }
    /***
     *  diese Funktion ist eine Get-Methode return ein boolean (ob ein Datenwert NUll ist oder nicht )
     * @return
     */
    public boolean get_user(){
        return exist_user;
    }

    /***
     * Dise funktion macht ein Abfrage zum daten bank und sucht ob das Password  in diese Daten Bank existieret oder nicht
     * //@param user :String , mit diesem Parameter �berpr�fe  ich ob  ein Password  in daten Bank gleich ist als password  falls ja :
     * dann werden die  boolean Variabele exist_password  auf true ersetzen .
     */

    public void   find_Password(String password)
    {
        try{
            // Query vorbereiten
            String query="SELECT passwort FROM users WHERE passwort=?";
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=_conn.prepareStatement(query);
            // Query wird modifiziert
            pst.setString(1,password);
            // ein Abfrage auf die Tabelle Users_password erzeugen .
            ResultSet rs=pst.executeQuery();
            /// hier �berpr�fen ob das user existiet oder nicht
            while( rs.next())
                exist_password=true;

            /// Statement  und Resultstatement closed
            pst.close();
            rs.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }
    }
    /***
     *  diese Funktion ist eine Get-Methode return ein boolean (ob ein Datenwert NUll ist oder nicht )
     * @return
     */
    public boolean get_Password(){
        return exist_password;
    }
}
