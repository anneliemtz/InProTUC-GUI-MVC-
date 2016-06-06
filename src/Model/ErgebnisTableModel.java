package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by annelie on 09.05.16.
 */
public class ErgebnisTableModel extends Observable {

    Connection _conn;
    PreparedStatement pst;
    String fehlerString;
    DefaultTableModel model_student, model_aktivitaet, model_status;
    Vector<Object> columnnames = new Vector<Object>();
    Vector<Object> data = new Vector<Object>();
    int columns;

    public ErgebnisTableModel(){
        /**
         * Konstruktor:
         * Verbindung zur Datenbank herstellen
         */

        Connection_DB _connection = new Connection_DB();
        _conn=_connection.getConnection();
    }

    public DefaultTableModel getTableModel_Student(String attribut, String wert){
        try{
            // Query
            String query ="SELECT DISTINCT student.urz, name, vorname FROM student WHERE name = 'Ahmad';";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            System.out.println("Statement: " + pst);

            // ein Abfrage auf die Tabelle login
            ResultSet result = pst.executeQuery();

            //Die Tupeln und Metadaten der Tabelle werden aufgerufen und gespeichert
            ResultSetMetaData meta = result.getMetaData();
            columns = meta.getColumnCount();

            //holt die Namen der Spalten
            for(int i = 1; i<= columns; i++){
                columnnames.addElement(meta.getColumnName(i));
            }

            //holt die Tupel
            while(result.next()){
                Vector<Object> zeile = new Vector<Object>(columns);

                for(int i = 1; i<= columns; i++){
                    zeile.addElement(result.getObject(i));
                }

                data.addElement(zeile);
            }

            System.out.println("Daten geholt.");

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        model_student = new DefaultTableModel(data, columnnames){

            @Override
            public Class getColumnClass(int columns){

                for(int row = 0; row < getRowCount(); row++){
                    Object o = getValueAt(row, columns);

                    if(o != null){
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };

        System.out.println("Abstrakte Tabelle erstellt.");


        return model_student;
    }

    public DefaultTableModel getTableModel_Aktivitaet(String attribut, String wert){
        try{
            // Query
            String query ="SELECT DISTINCT m_a.aktivitaet_name, zeitraum, beschreibung, massnahme_name FROM student" +
                    "INNER JOIN s_m_a ON student.urz = s_m_a.urz" +
                    "INNER JOIN m_a ON s_m_a.id_m_a = m_a.id" +
                    "INNER JOIN aktivitaet ON m_a.aktivitaet_name = aktivitaet.aktivitaet_name" +
                    "WHERE name = 'Ahmad';";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            System.out.println("Statement: " + pst);

            // ein Abfrage auf die Tabelle login
            ResultSet result = pst.executeQuery();

            //Die Tupeln und Metadaten der Tabelle werden aufgerufen und gespeichert
            ResultSetMetaData meta = result.getMetaData();
            columns = meta.getColumnCount();

            //holt die Namen der Spalten
            for(int i = 1; i<= columns; i++){
                columnnames.addElement(meta.getColumnName(i));
            }

            //holt die Tupel
            while(result.next()){
                Vector<Object> zeile = new Vector<Object>(columns);

                for(int i = 1; i<= columns; i++){
                    zeile.addElement(result.getObject(i));
                }

                data.addElement(zeile);
            }

            System.out.println("Daten geholt.");

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        model_aktivitaet = new DefaultTableModel(data, columnnames){

            @Override
            public Class getColumnClass(int columns){

                for(int row = 0; row < getRowCount(); row++){
                    Object o = getValueAt(row, columns);

                    if(o != null){
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };

        System.out.println("Abstrakte Tabelle erstellt.");


        return model_aktivitaet;
    }

    public DefaultTableModel getTableModel_Status(String attribut, String wert){
        try{
            // Query
            String query ="SELECT DISTINCT status_typ FROM student" +
                    "INNER JOIN student_status ON student.urz = student_status.urz" +
                    "WHERE name = 'Ahmad';";

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst = _conn.prepareStatement(query);
            System.out.println("Statement: " + pst);

            // ein Abfrage auf die Tabelle login
            ResultSet result = pst.executeQuery();

            //Die Tupeln und Metadaten der Tabelle werden aufgerufen und gespeichert
            ResultSetMetaData meta = result.getMetaData();
            columns = meta.getColumnCount();

            //holt die Namen der Spalten
            for(int i = 1; i<= columns; i++){
                columnnames.addElement(meta.getColumnName(i));
            }

            //holt die Tupel
            while(result.next()){
                Vector<Object> zeile = new Vector<Object>(columns);

                for(int i = 1; i<= columns; i++){
                    zeile.addElement(result.getObject(i));
                }

                data.addElement(zeile);
            }

            System.out.println("Daten geholt.");

            /// Statement closed und ResultStatement closed
            result.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        model_status = new DefaultTableModel(data, columnnames){

            @Override
            public Class getColumnClass(int columns){

                for(int row = 0; row < getRowCount(); row++){
                    Object o = getValueAt(row, columns);

                    if(o != null){
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };

        System.out.println("Abstrakte Tabelle erstellt.");


        return model_status;
    }
}
