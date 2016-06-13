package Controller;

import Model.Functions;
import Model.IndexUpdatePersonModel;
import Model.UpdatePersonModel;
import View.IndexUpdatePersonView;
import View.UpdatePersonView;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Created by annelie on 13.06.16.
 */
public class IndexUpdatePersonController extends WindowAdapter implements MouseListener,KeyListener,ActionListener{

    /// Modle Atribute
    private IndexUpdatePersonModel model_index_page;
    /// View Atribute
    private IndexUpdatePersonView index_page;

    /*---------------------------------------------------------------------------------------
     * Konstruktur zum Setzung von MVC
     * @parm model Login_Model : das Model des Controlers  .
     * @parm view  login : das View des Controlers.
     *
     * ---------------------------------------------------------------------------------------*/
    public IndexUpdatePersonController(IndexUpdatePersonModel model,IndexUpdatePersonView view){
        this.model_index_page=model;
        this.index_page=view;
    }

    private String urz="";
    public void actionPerformed(ActionEvent arg0) {
        UpdatePersonView student = null;
        if (arg0.getSource() == index_page.btnupdateDate)
        {
            if(urz!="")
                System.out.println(urz);
            else {
                urz=model_index_page.TopUrz();
                System.out.println("hamdulelah"+urz);
            }
            UpdatePersonModel modelupdadeStudent = new UpdatePersonModel();
            modelupdadeStudent.SetUpdateUrz(urz);
            UpdatePersonView  x =new UpdatePersonView(modelupdadeStudent,"index");
            x.innit();
        }



        // TODO Automatisch generierter Methodenstub

    }


    /****************************************************************************
     * Ich bekomme alle Aktivitaten von ein Studen nach dem Clik in der Tabelle.
     ****************************************************************************/
    public void mouseClicked(MouseEvent arg0) {

        Functions classfunction=new Functions();
        int zeile=this.index_page.tableStudent.getSelectedRow();
        JTable traget=(JTable)arg0.getSource();
        String nameSpalte=traget.getColumnName(0);
        if(nameSpalte=="Urz")
        {
            urz= this.index_page.tableStudent.getValueAt(zeile, 0).toString();
        }
        //Tabelle Akivitaten:
        ArrayList<ArrayList<String>> list_aktivitat=this.model_index_page.ListeAlleAktivitaeten(urz);
        if(list_aktivitat.size()>0)
        {
            Functions TowDListeTo1d=new Functions();
            ArrayList<String> listeAkti=TowDListeTo1d.TowDarrayListeTo1DArrayListe(list_aktivitat);

            ArrayList<ArrayList<String>>listeAktiviZei=model_index_page.ListeAktivitatZeitraum_Beschreibung(listeAkti);
            String [][]aktivitat=classfunction.ArrayListToArrayVonSring(listeAktiviZei);
            this.index_page.modelAktivitaet.setDataVector(aktivitat, this.index_page.titleTabeleAkivitaet);
        }
        else
        {
            String [][]aktivitat=new String[][]{};
            this.index_page.modelAktivitaet.setDataVector(aktivitat, this.index_page.titleTabeleAkivitaet);
        }
        //Tabelle Status:
        ArrayList<ArrayList<String>> listStatus=this.model_index_page.ListeAlleStatus(urz);
        if(listStatus.size()>0){
            String[][] status=classfunction.ArrayListToArrayVonSring(listStatus);
            this.index_page.modelStatus.setDataVector(status, this.index_page.titleTabelleStatus);
        }
        else {
            String [][]status=new String[][]{};
            this.index_page.modelStatus.setDataVector(status, this.index_page.titleTabelleStatus);
            System.out.println("hat kein Status");
        }
        //Information über Student
        String[] dataperson =classfunction.ArrayList1DToSting(this.model_index_page.ListeInformationStudent(urz));
        index_page.updatePersonlicheInformation(dataperson);
        //Bermerkung
        int anzahl=model_index_page.StudentHatBermerkung(urz);
        index_page.panel_9.setVisible(false);
        if(anzahl>0)
        {   String[] listeBemerkung=model_index_page.ListBermerkung(urz);
            index_page.updateBemerkung(listeBemerkung);

        }
    }




    public void Daten_von_Erste_Student_Darstellen(){
        //Bestimmen den Nutzername von ersten Student
        String urz=model_index_page.TopUrz();
        //Bestimmen alle Information von eiene Student
        Functions classfunction=new Functions();
        String[] dataperson =classfunction.ArrayList1DToSting(this.model_index_page.ListeInformationStudent(urz));
        index_page.updatePersonlicheInformation(dataperson);
        //Tabelle Status:
        ArrayList<ArrayList<String>> listStatus=this.model_index_page.ListeAlleStatus(urz);
        if(listStatus.size()>0){
            String[][] status=classfunction.ArrayListToArrayVonSring(listStatus);
            this.index_page.modelStatus.setDataVector(status, this.index_page.titleTabelleStatus);
        }
        else {
            System.out.println("hat kein Status");
        }

        //Tabelle Akivitaten:
        ArrayList<ArrayList<String>> list_aktivitat=this.model_index_page.ListeAlleAktivitaeten(urz);
        if(list_aktivitat.size()>0)
        {
            Functions TowDListeTo1d=new Functions();
            ArrayList<String> listeAkti=TowDListeTo1d.TowDarrayListeTo1DArrayListe(list_aktivitat);

            ArrayList<ArrayList<String>>listeAktiviZei=model_index_page.ListeAktivitatZeitraum_Beschreibung(listeAkti);
            String [][]aktivitat=classfunction.ArrayListToArrayVonSring(listeAktiviZei);
            this.index_page.modelAktivitaet.setDataVector(aktivitat, this.index_page.titleTabeleAkivitaet);
            // index_page.tableAktivitaet.getColumn(index_page.tableAktivitaet.getColumnName(2)).setCellRenderer(new JButtonRendererAktivitaet());
            //index_page.tableAktivitaet.getColumn(index_page.tableAktivitaet.getColumnName(2)).setCellEditor(index_page.btnAktivitat);
        }/*
		//Tabelle Akivitaten:
		ArrayList<ArrayList<String>> list_aktivitat=this.model_index_page.ListeAlleAktivitaeten(urz);
		if(list_aktivitat.size()>0)
		{
			String [][]aktivitat=classfunction.ArrayListToArrayVonSring(list_aktivitat);
			this.index_page.modelAktivitaet.setDataVector(aktivitat, this.index_page.titleTabeleAkivitaet);
		}*/
        else
            System.out.println("hat kein Aktivitäten");
        //Bermerkung
        int anzahl=model_index_page.StudentHatBermerkung(urz);
        index_page.panel_9.setVisible(false);
        if(anzahl>0)
        {   String[] listeBemerkung=model_index_page.ListBermerkung(urz);
            index_page.updateBemerkung(listeBemerkung);
        }
    }

    public void keyPressed(KeyEvent arg0) {
        // TODO Automatisch generierter Methodenstub

    }

    public void keyReleased(KeyEvent arg0) {
        // TODO Automatisch generierter Methodenstub

    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Automatisch generierter Methodenstub

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Automatisch generierter Methodenstub

    }

    public void mousePressed(MouseEvent e) {
        JTable traget=(JTable)e.getSource();
        int row = traget.rowAtPoint(e.getPoint());
        int column=traget.columnAtPoint(e.getPoint());
        System.out.println(row);
        String NameSpalten=traget.getColumnName(0);
        if(NameSpalten=="Status")
        {
            System.out.println( traget.getValueAt(row, column));

        }
        // TODO Automatisch generierter Methodenstub

    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Automatisch generierter Methodenstub

    }


}
