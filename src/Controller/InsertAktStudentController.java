package Controller;


import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by annelie on 23.04.16.
 */

public class InsertAktStudentController implements ActionListener, KeyListener{

    private InsertAktStudentModel _model;
    private InsertAktStudentView _view;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";



    public InsertAktStudentController(InsertAktStudentModel model, InsertAktStudentView view){
        this._model = model;
        this._view = view;

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        /**
         * Betätigung von Button
         */
        if (source instanceof JButton){
            System.out.println("button");

            /**
             * Betätigung vom Zurück-Button
             */
            if(source.equals(_view.zurueck)){
                System.out.println("User will zurueck");
                _view.setVisible(false);
                IndexView indexFenster = new IndexView(new IndexModel(),"InProTUC Datenbank | Index");
            }

            /**
             * Betätigung vom Senden-Button
             */
            if(source.equals(_view.senden)){
                System.out.println("User will senden");
                String studentName = (String) _view.studentCb.getSelectedItem();//get the selected item
                System.out.println("Name des Students: " + studentName);
                String urz = _model.findUrz(studentName);

                String aktivitaetBeschreibung = (String) _view.aktivitaetCb.getSelectedItem();//get the selected item
                System.out.println("Aktivitaet: " + aktivitaetBeschreibung);

                String id_m_a = _model.findId_m_a(aktivitaetBeschreibung);
                System.out.println("Id Aktivität: " + id_m_a);

                /**
                 * String id_s_m_a = _model.findId_s_m_a(aktivitaetBeschreibung, urz);
                 System.out.println("Id Student: " + id_s_m_a);
                 */


            }

            /**
             * Betätigung vom NeuerStudent-Button
             */
            if(source.equals(_view.neuerStudentbtn)){
                System.out.println("User will Student neu einfügen");
                _view.setVisible(false);
                InsertStudentVIew fensterNeuStu = new InsertStudentVIew(new InsertStudentModel(),"InProTUC Datenbank | Student neu einfügen");

            }

            /**
             * Betätigung vom NeueAktivität-Button
             */
            if(source.equals(_view.neueAktivitaetbtn)){
                System.out.println("User will Aktivität neu einfügen");
                _view.setVisible(false);
                //InsertAktivitaetView fensterInsertAkt = new InsertAktivitaetView("InProTUC Datenbank | Aktivität neu einfügen");


            }
        }

        /**
         * Auswahl Combo Box
         */
        if (source instanceof JComboBox){
            System.out.println("Combo Breaker!");

            // Auswahl wird ausgegeben
            String a = (String) _view.aktivitaetCb.getSelectedItem();//get the selected item
            System.out.println(a);

            _view.bool = _model.returnMobilitaet(a);

            if(_view.bool == 1){
                System.out.println("Mob einschlaten");
                _view.controlPanel1.setEnabled(true);
                _view.artLbl.setEnabled(true);
                _view.durchfuehrungLbl.setEnabled(true);
                _view.jaLbl.setEnabled(true);
                _view.neinLbl.setEnabled(true);
                _view.artTextField.setEditable(true);
                _view.jaRBtn.setVisible(true);
                _view.neinRBtn.setVisible(true);
            }

            else{
                System.out.println("Mob ausschlaten");
                _view.controlPanel1.setEnabled(false);
                _view.artLbl.setEnabled(false);
                _view.durchfuehrungLbl.setEnabled(false);
                _view.jaLbl.setEnabled(false);
                _view.neinLbl.setEnabled(false);
                _view.artTextField.setEditable(false);
                _view.jaRBtn.setVisible(false);
                _view.neinRBtn.setVisible(false);
            }



        }


        /**
         * Auswahl eines Elements des Menus
         */
        if(source instanceof JMenuItem){
            JMenuItem sourceMenu = (JMenuItem)(actionEvent.getSource());
            String commandMenu = sourceMenu.getText();

            if(commandMenu.equals(INSERT_STUDENT)){
                _view.setVisible(false);
                InsertStudentVIew fensterInsert = new InsertStudentVIew(new InsertStudentModel(),"InProTUC Datenbank | Student neu einfügen");
            }

            if(commandMenu.equals(INSERT_AKT_STU)){
                _view.setVisible(false);
                InsertAktStudentView fensterInsertAktStu = new InsertAktStudentView(new InsertAktStudentModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
            }

            if(commandMenu.equals(INSERT_STATUS_STUDENT)){
                _view.setVisible(false);
                InsertStatusStudentView fensterInsertStatusStu = new InsertStatusStudentView(new InsertStatusStudentModel(), "InProTUC Datenbank | Status eines Studenten eintragen");
            }

            if(commandMenu.equals(INSERT_AKT)){
                _view.setVisible(false);
                InsertAktivitaetView fensterInsertAkt = new InsertAktivitaetView(new InsertAktivitaetModel(), "InProTUC Datenbank | Aktivität neu einfügen");
            }

            if(commandMenu.equals(INSERT_STATUS)){
                _view.setVisible(false);
                InsertStatusView fensterInsertStatus = new InsertStatusView(new InsertStatusModel(), "InProTUC Datenbank | Status neu einfügen");
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
