package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by annelie on 13.06.16.
 */
public class GeneralSqlAbfragen {

    public GeneralSqlAbfragen(){
        Connection_DB _connection=new Connection_DB();
        conn=_connection.getConnection();
    }

    Connection conn=null;
    PreparedStatement pst=null;
    Statement stmt = null;
    /**
     *   diese Funktion löscht ein Zeile von einer Tabelle.
     * @param tabelleName ist die Name der Tabelle.
     * @param spalteName ist die Name der Spalte.
     * @param value Wert der Spalte.
     * @return boolean
     */
    public boolean ZeileVonTabelleLöschen (String tabelleName,String spalteName,String value  )
    {
        int i=0;
        String query="DELETE FROM "+tabelleName+" WHERE "+spalteName+" = '"+value+"';";
        System.out.println(query);
        try{

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            stmt=conn.createStatement();
            // Query wird modifiziert
            stmt.executeUpdate(query);

            /// Statement closed und ResultStatement closed
            stmt.close();
            conn.close();
            i=1;
        }

        catch(SQLException exception ){
            System.out.println(exception);
        }
        if(i==0)
            return false;
        else
            return true;
    }



    public boolean UpdateZeileVonTabelle (String query  )
    {
        try{

            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            stmt=conn.createStatement();
            // Query wird modifiziert
            stmt.executeUpdate(query);
            /// Statement closed und ResultStatement closed
            stmt.close();
            conn.close();
            return true;
        }

        catch(SQLException exception ){
            System.out.println(exception);
        }

        return false;


    }
}
