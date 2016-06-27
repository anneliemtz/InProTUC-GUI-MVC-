package Model.Insert;

import Model.Sonstiges.Connection_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Random;

/**
 * Created by annelie on 18.04.16.
 */
public class InsertStudentModel extends Observable {
    Connection _conn;
    PreparedStatement pst;
    String fehlerString;

    public InsertStudentModel(){
        /**
         * Konstruktor:
         * Verbindung zur Datenbank herstellen
         */

        Connection_DB _connection = new Connection_DB();
        _conn=_connection.getConnection();

    }

    public String cat(String a, String b){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);

        a = a.substring(0, a.length() - (a.length()/2));
        b = b.substring(0, b.length() - (b.length()/2));

        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        sb.append(randomInt);
        String ergebnis  = sb.toString();


        return ergebnis;

    }

    public int insertValues(String nachnameStr, String vornameStr, String gebDatStr, String fakuStr, String telStr, String emailStr, String urzTUCStr, String bemeStr){
        try{

            String urz = cat(vornameStr, nachnameStr);

            // Query
            String query ="INSERT INTO student VALUES(?, ?, ?, ?, ?, ?, ?, '{" + bemeStr + "}',? )";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            pst.setString(1, nachnameStr);
            pst.setString(2, vornameStr);
            pst.setString(3, gebDatStr);
            pst.setString(4, fakuStr);
            pst.setString(5, telStr);
            pst.setString(6, emailStr);
            pst.setString(7, urz);
            pst.setString(8, urzTUCStr);

            System.out.println("Query: " + pst);

            // ein Abfrage auf die Tabelle
            pst.executeUpdate();

            /// Statement closed und ResultStatement closed
            System.out.println("Insert succeed");
            pst.close();

            return 1;
        }

        //TODO: Dass eventuell Fehler in einem JDialog angezeigt werden
        catch(SQLException exception ){
            System.out.println(exception);

            fehlerString = exception.getMessage();
            System.out.println(fehlerString);

            return -1;
        }

    }

    public String getErrorMessage(){return fehlerString; }
}
