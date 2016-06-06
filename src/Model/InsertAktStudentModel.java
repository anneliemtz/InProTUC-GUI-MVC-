package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by annelie on 23.04.16.
 */
public class InsertAktStudentModel extends Observable {
    Connection _conn;
    PreparedStatement pst, pst2;

    ArrayList<String> dataStudent;
    ArrayList<String> dataAktivitaet;
    int bool;
    public String fehlerString;
    String urz, id_s_m_a, id_m_a;

    public InsertAktStudentModel(){
        /**
         * Konstruktor:
         * Verbindung zur Datenbank herstellen
         */

        Connection_DB _connection = new Connection_DB();
        _conn=_connection.getConnection();

    }

    public String findId_m_a(String aktivitaetBeschreibung){
        try{
            // Query 1
            String query ="SELECT id FROM m_a " +
                    "INNER JOIN aktivitaet ON aktivitaet.aktivitaet_name = m_a.aktivitaet_name " +
                    "WHERE beschreibung = '"+ aktivitaetBeschreibung +"';";

            // PrepareStatement wird  erzeugt.
            pst =_conn.prepareStatement(query);
            //pst.setString(1, name);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            //holt die Tupel
            while (result.next()){
                id_m_a = result.getString("id");
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

        return id_m_a;
    }

    public String findId_s_m_a(String aktivitaetBeschreibung, String urzStudent){
        try{
            // Query 1
            String query ="SELECT s_m_a.id FROM s_m_a " +
                    "INNER JOIN m_a ON m_a.id = id_m_a " +
                    "INNER JOIN aktivitaet ON aktivitaet.aktivitaet_name = m_a.aktivitaet_name " +
                    "WHERE beschreibung = '" + aktivitaetBeschreibung + "' " +
                    "AND urz = '" + urzStudent + "';";

            // PrepareStatement wird  erzeugt.
            pst =_conn.prepareStatement(query);
            //pst.setString(1, name);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            //holt die Tupel
            while (result.next()){
                id_s_m_a = result.getString("s_m_a.id");
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

        return id_s_m_a;
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

    public ArrayList<String> returnAktivitaetName(){
        try {
            // Query 1
            String query = "select beschreibung from aktivitaet ORDER BY beschreibung";
            // PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            dataAktivitaet = new ArrayList<String>();
            int columnCount = result.getMetaData().getColumnCount();

            //holt die Tupel
            while (result.next()) {
                //String name = result.getString("aktivitaet_name");
                String beschreibung = result.getString("beschreibung");
                //dataAktivitaet.add(name);
                dataAktivitaet.add(beschreibung);
            }

            result.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return dataAktivitaet;
    }

    public int returnMobilitaet(String aktivitaet){

        try {
            // Query 1
            String query = "SELECT mob('" + aktivitaet +"');";
            // PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);

            // ein Abfrage auf die Tabelle
            ResultSet result = pst.executeQuery();

            // Der Ergebnis der Funktion wird hier geholt
            while(result.next()){
                bool = result.getInt(1);
                System.out.println("Return Value ist: " + bool);
            }
            result.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return bool;
    }

    //TODO: insertValues Methode

    //TODO: Datenbank Fremdschlüssel anpassen:
    /**
     *
     */
}