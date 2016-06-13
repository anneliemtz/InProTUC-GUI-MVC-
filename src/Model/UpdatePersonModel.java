package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;


/**
 * Created by annelie on 13.06.16.
 */
public class UpdatePersonModel extends Observable  {

    Connection conn;
    PreparedStatement pst;

    /**
     * Konstruktor der Klasse
     */

    private  String updateUrz="";


    public UpdatePersonModel(){
        // Verbindung zum Datenbank hestellen.
        Connection_DB _connection = new Connection_DB();
        conn=_connection.getConnection();
    }

    /**
     *
     * @param _urz
     */
    public void  SetUpdateUrz(String _urz)
    {
        updateUrz=_urz;

    }
    /**
     *
     * @return urz ;
     */
    public String Geturz()
    {
        return updateUrz;
    }

    /** ;
     *
     * @param urz in der wird die gesuchte Urz von ein Student gespeichert.
     * @return
     */
    public ArrayList<ArrayList<String>> ListeAlleAktivitaeten(String urz)
    {
        ArrayList<ArrayList<String>> listeAktivitaeten=new ArrayList<ArrayList<String>>();
        // Query:
        //  String qurey ="select aktivitaet_name from m_a inner join s_m_a on m_a.id=s_m_a.id where s_m_a.urz='"+urz+"'";
        String qurey ="select aktivitaet_name from m_a inner join s_m_a on m_a.id=s_m_a.id_m_a where s_m_a.urz'"+urz+"'";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(qurey);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();
            ArrayList<String> zeile=new ArrayList<String>();
            while(rs.next())
            {
                String aktivitaetName=rs.getString("aktivitaet_name");
                // Speichern aktivitaetName in einer Arraylist
                zeile.add(aktivitaetName);
                listeAktivitaeten.add(zeile);
                zeile=new ArrayList<String>();
            }

            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return listeAktivitaeten;
    }
    /**
     *
     * @param query the query
     * @param Columnname
     * @return
     */
    public ArrayList<String> Select(String query,String Columnname)
    {
        ArrayList<String> zeile=new ArrayList<String>();

        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            while(rs.next())
            {
                String name=rs.getString(Columnname);
                // Speichern aktivitaetName in einer Arraylist
                zeile.add(name);

            }

            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }


        return zeile;
    }
}
