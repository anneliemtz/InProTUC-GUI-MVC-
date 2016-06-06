package Model;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by annelie on 06.05.16.
 */
public class SelectEinfachModel extends Observable {
    Connection _conn;
    PreparedStatement pst;
    String fehlerString;
    DefaultTableModel model;
    Vector<Object> columnnames = new Vector<Object>();
    Vector<Object> data = new Vector<Object>();
    int columns;

    public SelectEinfachModel(){
        /**
         * Konstruktor:
         * Verbindung zur Datenbank herstellen
         */

        Connection_DB _connection = new Connection_DB();
        _conn=_connection.getConnection();

    }


}
