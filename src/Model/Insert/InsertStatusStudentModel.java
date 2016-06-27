package Model.Insert;

import Model.Sonstiges.Connection_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by annelie on 02.05.16.
 */
public class InsertStatusStudentModel extends Observable {
    Connection _conn;
    PreparedStatement pst, pst2;
    public String fehlerString;
    String urz;

    ArrayList<String> dataStudent;
    ArrayList<String> dataStatus;


    public InsertStatusStudentModel(){
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

    public String getErrorMessage(){return fehlerString; }

    public int insertValues(String urz, String status){
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

    public int insertValues2(String urz, String status, String status2){
        try{
            /**
             * Query 1
             */
            String query ="INSERT INTO student_status VALUES("+ status +", "+ urz +");";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);

            System.out.println("Query: " + pst);
            System.out.println("da");
            // ein Abfrage auf die Tabelle
            pst.executeUpdate();
            System.out.println("da2");
            pst.close();
            System.out.println("da3");


            /**
             * Query 2
             */
            String query2 ="INSERT INTO student_status VALUES(?, ?)";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst2 = _conn.prepareStatement(query2);
            pst2.setString(1, status2);
            pst2.setString(2, urz);

            System.out.println("Query: " + pst2);

            // ein Abfrage auf die Tabelle
            pst2.executeUpdate();

            /// Statement closed und ResultStatement closed
            pst2.close();
            System.out.println("Insert succeed");

            return 1;
        }

        catch(SQLException exception ){
            System.out.println(exception);

            fehlerString = exception.getMessage();
            System.out.println(fehlerString);

            return -1;
        }
    }

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

    public ArrayList<String> returnStatusName(){

        try {
            // Query 1
            String query ="select status_typ from status ORDER BY status_typ";
            // PrepareStatement wird  erzeugt.
            pst =_conn.prepareStatement(query);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            dataStatus = new ArrayList<String>();
            int columnCount = result.getMetaData().getColumnCount();

            //holt die Tupel
            while(result.next()){
                String status = result.getString("status_typ");
                dataStatus.add(status);

            }

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();

        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return dataStatus;
    }
}
