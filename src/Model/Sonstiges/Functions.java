package Model.Sonstiges;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Created by annelie on 13.06.16.
 */
public class Functions {

    public Functions(){}
    /***
     * Diese Function konveriert ein ArrayList von Arraylist in array von array von String.
     * @parm1: ist ein ArrayList
     * RückgabeWert: ist ein Array von String.
     */

    public String[][] ArrayListToArrayVonSring(ArrayList<ArrayList<String>> listarray){

        int zeillenAnzahl= listarray.size(); int i=0; int j=0;

        int spaltenAnzahl=listarray.get(0).size();
        String[][] array=new String[zeillenAnzahl][spaltenAnzahl];
        for(i=0;i<zeillenAnzahl;i++)
        {
            for(j=0;j<spaltenAnzahl;j++)
            {
                if(listarray.get(i).get(j) != null)
                array[i][j]=listarray.get(i).get(j).toString();

                else
                    array[i][j] = "";


            }
        }

        return array;
    }

    /***
     * Diese Function konveriert ein Arraylist in array von String.
     * @parm1: ist ein ArrayList
     * RückgabeWert: ist ein Array von String.
     */

    public String[] ArrayList1DToSting(ArrayList<String> listarray){

        int zeillenAnzahl= listarray.size(); int i=0;
        String[] array=new String[zeillenAnzahl];
        for(i=0;i<zeillenAnzahl;i++)
        {
            array[i]=listarray.get(i).toString();
        }

        return array;
    }


    /***
     * Diese Function prüfen, Ob ResultSet leer ist oder nicht.
     * @parm1: name der Tablle
     * RückgabeWert:True falls ein Tabelle nicht leer ist, sonnst false.
     */
    public boolean TestObQueryLeer(String tableName){
        String query = "SELECT COUNT(*) FROM "+tableName;
        Connection_DB classconnection=new Connection_DB();
        Connection con =classconnection.getConnection();
        try {
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            int anzhahlZeilen=rs.getInt(1);
            if(anzhahlZeilen<=0){
                return false ;
            }
        }
        catch (SQLException exeception){
            System.out.println(exeception);
        }

        return true ;
    }


    /**
     * diese Funkton konvertiert 2-D Object Liste in 1-D Object Liste.
     * @param liste2d  array in 2 D
     * @return
     */
    public ArrayList<String> TowDarrayListeTo1DArrayListe(ArrayList<ArrayList<String>> liste2d){
        ArrayList<String>liste=new ArrayList<String>();

        for(int j=0; j<liste2d.size();j++)
        {
            for(int i=0;i<liste2d.get(j).size();i++)
            {
                liste.add(liste2d.get(j).get(i));
            }
        }
        return liste;
    }

}
