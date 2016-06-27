package Controller.Update;

import Model.Insert.*;
import Model.Select.SelectEinfachModel;
import Model.Sonstiges.Functions;
import Model.Update.IndexUpdateAktivitaetModel;
import Model.Update.IndexUpdatePersonModel;
import Model.Update.UpdatePersonModel;
import View.Insert.*;
import View.Select.SelectEinfachView;
import View.Sonstiges.DialogFenster;
import View.Update.IndexUpdateAktivitaetView;
import View.Update.IndexUpdatePersonView;
import View.Update.UpdatePersonView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by annelie on 13.06.16.
 */
public class IndexUpdatePersonController extends WindowAdapter implements MouseListener,KeyListener,ActionListener{

    /// Model
    private IndexUpdatePersonModel model;
    /// View
    private IndexUpdatePersonView view;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";


    /*---------------------------------------------------------------------------------------
     * Konstruktor setzt das MVC
     *
     * ---------------------------------------------------------------------------------------*/
    public IndexUpdatePersonController(IndexUpdatePersonModel model, IndexUpdatePersonView view){
        this.model = model;
        this.view = view;
    }

    private String urz="";

    public void actionPerformed(ActionEvent actionEvent) {
        UpdatePersonView student = null;
        Object source = actionEvent.getSource();

        if (source instanceof JButton) {
            System.out.println("button");
        }

        if (actionEvent.getSource().equals(view.btnupdateDate)) {
            System.out.println("button");

            if(urz!=""){
                System.out.println(urz);

                UpdatePersonModel modelupdadeStudent = new UpdatePersonModel();
                modelupdadeStudent.SetUpdateUrz(urz);
                UpdatePersonView  x = new UpdatePersonView(modelupdadeStudent,"InProTUC Datenbank | Daten eines Studenten ändern");
                x.innit();
            }


            else {
                //Fehlermeldung
                DialogFenster dialog = new DialogFenster();
                dialog.infoDialog(view, "Bitte wählen Sie einen Studenten aus");

            }

        }

        /**
         * Auswahl eines Elements des Menus
         */
        if(source instanceof JMenuItem){
            JMenuItem sourceMenu = (JMenuItem)(actionEvent.getSource());
            String commandMenu = sourceMenu.getText();

            if(commandMenu.equals(INSERT_STUDENT)){
                view.setVisible(false);
                InsertStudentVIew fensterInsert = new InsertStudentVIew(new InsertStudentModel(),"InProTUC Datenbank | Student neu einfügen");
            }

            if(commandMenu.equals(INSERT_AKT_STU)){
                view.setVisible(false);
                InsertAktStudentView fensterInsertAktStu = new InsertAktStudentView(new InsertAktStudentModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
            }

            if(commandMenu.equals(INSERT_STATUS_STUDENT)){
                view.setVisible(false);
                InsertStatusStudentView fensterInsertStatusStu = new InsertStatusStudentView(new InsertStatusStudentModel(), "InProTUC Datenbank | Status eines Studenten eintragen");
            }

            if(commandMenu.equals(INSERT_AKT)){
                view.setVisible(false);
                InsertAktivitaetView fensterInsertAkt = new InsertAktivitaetView(new InsertAktivitaetModel(), "InProTUC Datenbank | Aktivität neu einfügen");
            }

            if(commandMenu.equals(INSERT_STATUS)){
                view.setVisible(false);
                InsertStatusView fensterInsertStatus = new InsertStatusView(new InsertStatusModel(), "InProTUC Datenbank | Status neu einfügen");
            }

            if(commandMenu.equals(SELECT_EINFACH)){
                view.setVisible(false);
                SelectEinfachView fensterSucheEinfach = new SelectEinfachView(new SelectEinfachModel(), "InProTUC Datenbank | Einfache Suche");

            }

            if(commandMenu.equals(UPDATE_STUDENT)){
                view.setVisible(false);
                IndexUpdatePersonView fensterUpdatePerson = new IndexUpdatePersonView(new IndexUpdatePersonModel(), "InProTUC Datenbank | Person ändern");
            }

            if(commandMenu.equals(UPDATE_AKTIVITAET)){
                view.setVisible(false);
                IndexUpdateAktivitaetView fensterUpdateAktivitaet = new IndexUpdateAktivitaetView(new IndexUpdateAktivitaetModel(), "InProTUC Datenbank | Aktivität ändern");
            }
        }


    }


    /****************************************************************************
     * Ich bekomme alle Aktivitaten von ein Studen nach dem Clik in der Tabelle.
     ****************************************************************************/
    public void mouseClicked(MouseEvent arg0) {

        Functions classfunction=new Functions();
        int zeile=this.view.tableStudent.getSelectedRow();
        JTable traget=(JTable)arg0.getSource();
        String nameSpalte=traget.getColumnName(0);

        if(nameSpalte=="Urz"){
            urz= this.view.tableStudent.getValueAt(zeile, 0).toString();
        }

        /**
         * Tabelle Aktivität
         */
        ArrayList<ArrayList<String>> list_aktivitat=this.model.ListeAlleAktivitaeten(urz);
        if(list_aktivitat.size()>0)
        {
            Functions TowDListeTo1d=new Functions();
            ArrayList<String> listeAkti=TowDListeTo1d.TowDarrayListeTo1DArrayListe(list_aktivitat);

            ArrayList<ArrayList<String>>listeAktiviZei=model.ListeAktivitatZeitraum_Beschreibung(listeAkti);
            String [][]aktivitat=classfunction.ArrayListToArrayVonSring(listeAktiviZei);
            this.view.modelAktivitaet.setDataVector(aktivitat, this.view.titleTabeleAkivitaet);
        }
        else
        {
            String [][]aktivitat=new String[][]{};
            this.view.modelAktivitaet.setDataVector(aktivitat, this.view.titleTabeleAkivitaet);
        }
        /**
         * Tabelle Status
         */
        ArrayList<ArrayList<String>> listStatus=this.model.ListeAlleStatus(urz);
        if(listStatus.size()>0){
            String[][] status=classfunction.ArrayListToArrayVonSring(listStatus);
            this.view.modelStatus.setDataVector(status, this.view.titleTabelleStatus);
        }
        else {
            String [][]status=new String[][]{};
            this.view.modelStatus.setDataVector(status, this.view.titleTabelleStatus);
            System.out.println("hat kein Status");
        }
        //Information über Student
        String[] dataperson =classfunction.ArrayList1DToSting(this.model.ListeInformationStudent(urz));
        view.updatePersonlicheInformation(dataperson);
        //Bermerkung
        int anzahl=model.StudentHatBermerkung(urz);
        view.panel_9.setVisible(false);
        if(anzahl>0)
        {   String[] listeBemerkung=model.ListBermerkung(urz);
            view.updateBemerkung(listeBemerkung);

        }
    }


    public void Daten_von_Erste_Student_Darstellen(){
        //Bestimmen den Nutzername von ersten Student
        String urz=model.TopUrz();
        //Bestimmen alle Information von eiene Student
        Functions classfunction=new Functions();
        String[] dataperson =classfunction.ArrayList1DToSting(this.model.ListeInformationStudent(urz));
        view.updatePersonlicheInformation(dataperson);
        //Tabelle Status:
        ArrayList<ArrayList<String>> listStatus=this.model.ListeAlleStatus(urz);
        if(listStatus.size()>0){
            String[][] status=classfunction.ArrayListToArrayVonSring(listStatus);
            this.view.modelStatus.setDataVector(status, this.view.titleTabelleStatus);
        }
        else {
            System.out.println("hat kein Status");
        }

        //Tabelle Akivitaten:
        ArrayList<ArrayList<String>> list_aktivitat=this.model.ListeAlleAktivitaeten(urz);
        if(list_aktivitat.size()>0)
        {
            Functions TowDListeTo1d=new Functions();
            ArrayList<String> listeAkti=TowDListeTo1d.TowDarrayListeTo1DArrayListe(list_aktivitat);

            ArrayList<ArrayList<String>>listeAktiviZei=model.ListeAktivitatZeitraum_Beschreibung(listeAkti);
            String [][]aktivitat=classfunction.ArrayListToArrayVonSring(listeAktiviZei);
            this.view.modelAktivitaet.setDataVector(aktivitat, this.view.titleTabeleAkivitaet);
        }

        else
            System.out.println("hat kein Aktivitäten");
        //Bermerkung
        int anzahl=model.StudentHatBermerkung(urz);
        view.panel_9.setVisible(false);
        if(anzahl>0)
        {   String[] listeBemerkung=model.ListBermerkung(urz);
            view.updateBemerkung(listeBemerkung);
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
