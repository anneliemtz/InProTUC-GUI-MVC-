package Model.Update;

import Model.Sonstiges.Connection_DB;

import java.util.Observable;
import java.util.ArrayList;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Created by annelie on 13.06.16.
 */
public class IndexUpdatePersonModel extends Observable {

    private Connection conn;
    private PreparedStatement pst;

    public IndexUpdatePersonModel(){
        // Verbindung zum Datenbank hestellen.
        Connection_DB _connection = new Connection_DB();
        conn=_connection.getConnection();
    }

    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht nach alle extierend Student in der Tabelle Studen.
     * Die Rückgabewert ist ein Tablle, in der alle Studenten gespeichert wird.
     */
    public ArrayList<ArrayList<String>> ListeAufStudenten()
    {
        ArrayList<ArrayList<String>> listeStudent=new ArrayList<ArrayList<String>>();

        // Query
        String query ="select urz,vorname,name from student";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            ArrayList<String> zeile=new ArrayList<String>();
            while(rs.next())
            {
                String urz=rs.getString("urz");
                String vornameStudent=rs.getString("vorname");
                String nameStudent=rs.getString("name");
                // Speichern urz name und Vorname in einer Arraylist
                zeile.add(urz);
                zeile.add(vornameStudent);
                zeile.add( nameStudent);
                listeStudent.add(zeile);
                zeile=new ArrayList<String>();
            }
            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }
        return listeStudent;

    }


    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht nach alle Aktivitäten von einen Student.
     * @parm1: ist die Urz eines Student.
     */
    public ArrayList<ArrayList<String>> ListeAlleAktivitaeten(String urz)
    {
        ArrayList<ArrayList<String>> listeAktivitaeten=new ArrayList<ArrayList<String>>();
        // Query:
        String qurey ="select aktivitaet_name from m_a inner join s_m_a on m_a.id=s_m_a.id where s_m_a.urz='"+urz+"'";

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

    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht nach alle Status von einen Student.
     * @parm1: ist die Urz eines Student.
     */
    public ArrayList<ArrayList<String>> ListeAlleStatus(String urz)
    {
        ArrayList<ArrayList<String>> listeStatus=new ArrayList<ArrayList<String>>();
        String query="select status_typ from student_status where urz='"+urz+"'";
        // String query="select status_typ from student_status where urz='alma2'";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();
            ArrayList<String> zeile=new ArrayList<String>();
            while(rs.next())
            {
                String status=rs.getString("status_typ");
                // Speichern aktivitaetName in einer Arraylist
                zeile.add(status);
                listeStatus.add(zeile);
                zeile=new ArrayList<String>();
            }

            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }


        return listeStatus;
    }

    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht die Information über Student.
     * @parm1: ist die Urz eines Student.
     */
    public ArrayList<String> ListeInformationStudent(String urz)
    {
        ArrayList<String> zeile=new ArrayList<String>();
        String query="select name,vorname,geburtsdatum,fakultaet,telefon,email from student where urz='"+urz+"'";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            while(rs.next())
            {
                String name=rs.getString("name");
                String vorname=rs.getString("vorname");
                String geburtsdatum=rs.getString("geburtsdatum");
                String fakultaet=rs.getString("fakultaet");
                String telefon=rs.getString("telefon");
                String email=rs.getString("email");
                // Speichern aktivitaetName in einer Arraylist
                zeile.add(name);
                zeile.add(vorname);
                zeile.add(geburtsdatum);
                zeile.add(fakultaet);
                zeile.add(telefon);
                zeile.add(email);
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


    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht ob ein Student ein Bermerkung hat.
     * @parm1: ist die Urz eines Student.
     */

    public int StudentHatBermerkung(String urz){
        int anzahlBermerkung=0;
        String query="select Array1DLength('"+urz+"')";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            while(rs.next())
            {
                anzahlBermerkung=Integer.parseInt(rs.getString("array1dlength"));

            }
            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return anzahlBermerkung;
    }

    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht alle Bemerkungen von Student.
     * @parm1: ist die Urz eines Student.
     */

    public String[] ListBermerkung(String urz){
        String[] listBermerkung={""};
        String query="select bemerkungen from student where urz='"+urz+"'";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            while(rs.next())
            {
                Array array=rs.getArray("bemerkungen");
                listBermerkung=(String[])array.getArray();

            }
            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }

        return listBermerkung;
    }
    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht die urz von der ersten Student.
     */
    public String TopUrz(){
        String urz="";
        String query=" select urz from student limit 1";
        try{
            // ein Objekt der Klasse PrepareStatement wird  erzeugt.
            pst=conn.prepareStatement(query);
            // Query wird modifiziert
            ResultSet rs=pst.executeQuery();

            while(rs.next())
                urz=rs.getString("urz");
            /// Statement closed und ResultStatement closed
            rs.close();
            pst.close();
        }
        catch(SQLException exception ){
            System.out.println(exception);
        }
        return urz ;
    }


    /***
     * Dise funktion macht ein Abfrage zum daten bank dann sucht die Information über Student.
     * @parm1: ist die Urz eines Student.
     */
    public ArrayList<ArrayList<String>>ListeAktivitatZeitraum_Beschreibung (ArrayList<String> listeaktivität)
    {
        ArrayList<ArrayList<String>> liste=new ArrayList<ArrayList<String>>();

        int lengthliste=listeaktivität.size();
        for(int j=0; j<lengthliste; j++)
        { String aktivitatet=listeaktivität.get(j).toString();
            ArrayList<String> zeile=new ArrayList<String>();
            String query="select beschreibung,aktivitaet_name,zeitraum from aktivitaet where aktivitaet_name='"+aktivitatet+"'";
            try{
                System.out.println(query);
                // ein Objekt der Klasse PrepareStatement wird  erzeugt.
                pst=conn.prepareStatement(query);
                // Query wird modifiziert
                ResultSet rs=pst.executeQuery();

                while(rs.next())
                {
                    String aktivitatName=rs.getString("aktivitaet_name");
                    String beshreibung=rs.getString("beschreibung");
                    String zeitraum=rs.getString("zeitraum");

                    // Speichern aktivitaetName in einer Arraylist
                    zeile.add(aktivitatName);
                    zeile.add(beshreibung);
                    zeile.add(zeitraum);

                }

                /// Statement closed und ResultStatement closed
                rs.close();
                pst.close();
            }
            catch(SQLException exception ){
                System.out.println(exception);
            }
            liste.add(zeile);
        }

        return liste;
    }
}
