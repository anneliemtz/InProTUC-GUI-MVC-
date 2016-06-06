package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by annelie on 02.05.16.
 */
public class InsertStatusModel extends Observable {
    Connection _conn;
    PreparedStatement pst;
    String fehlerString;
    String urz;

    ArrayList<String> dataStudent;

    public InsertStatusModel(){
        /**
         * Konstruktor:
         * Verbindung zur Datenbank herstellen
         */

        Connection_DB _connection = new Connection_DB();
        _conn=_connection.getConnection();

    }

    public String findUrz(String name){
        try{
            // Query 1
            String query ="select urz from student where name = '"+ name +"';";

            // PrepareStatement wird  erzeugt.
            pst =_conn.prepareStatement(query);
            //pst.setString(1, name);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            //holt die Tupel
            while (result.next()){
                urz = result.getString("urz");
            }

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();

        }

        catch(SQLException exception ){
            System.out.println(exception);

            fehlerString = exception.getMessage();
            System.out.println(fehlerString);
        }

        return urz;
    }

    public int insertValues(String status){
        try{
            // Query
            String query ="INSERT INTO status VALUES(?)";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            pst.setString(1, status);

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

    public int insertValues2(String urz, String status){
        try{
            // Query
            String query ="INSERT INTO student_status VALUES(?, ?)";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            pst.setString(1, status);
            pst.setString(2, urz);

            System.out.println("Query: " + pst);

            // ein Abfrage auf die Tabelle
            pst.executeUpdate();

            /// Statement closed und ResultStatement closed
            System.out.println("Insert succeed");
            pst.close();

            return 1;

        }

        catch(SQLException exception ){
            System.out.println(exception);

            fehlerString = exception.getMessage();
            System.out.println("Fehler String: " + fehlerString);

            return -1;
        }

    }

    public String getErrorMessage(){return fehlerString; }
    public ArrayList<String> returnStundentName(){

        try {
            // Query 1
            String query ="select name, vorname from student ORDER BY name";
            // PrepareStatement wird  erzeugt.
            pst =_conn.prepareStatement(query);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            dataStudent = new ArrayList<String>();
            int columnCount = result.getMetaData().getColumnCount();

            //holt die Tupel
            while(result.next()){
                String vorname = result.getString("name");
                String nachname = result.getString("vorname");
                dataStudent.add(vorname);
                dataStudent.add(nachname);
            }

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();

        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return dataStudent;
    }
}
