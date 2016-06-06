package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by annelie on 18.04.16.
 */
public class InsertStudentController implements ActionListener,KeyListener{

    private InsertStudentModel _model;
    private InsertStudentVIew _view;

    public int ergebnis;

    String wahlTag, wahlMonat, wahlJahr, gebDatString;
    String nachnameString, vornameString, urzString, fakuString, telString, emailString, bemerkString;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";


    public InsertStudentController(InsertStudentModel model, InsertStudentVIew view) {
        this._model = model;
        this._view = view;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        /**
         * Combo Box
         * Speichert die Asuwahl des Combobox in einer Variabel
         */
        if (source instanceof JComboBox) {

            if (actionEvent.getSource().equals(_view.tagCb)) {

                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlTag = (String) cb.getSelectedItem();
                System.out.println(wahlTag);

                //TODO: Fehleransage falls Datum nicht vollständig angegeben wird

            }

            if (actionEvent.getSource().equals(_view.monatCb)) {

                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlMonat = (String) cb.getSelectedItem();
                System.out.println(wahlMonat);

                //TODO: Fehleransage falls Datum nicht vollständig angegeben wird
            }

            if (actionEvent.getSource().equals(_view.jahrCb)) {

                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlJahr = (String) cb.getSelectedItem();
                System.out.println(wahlJahr);

                //TODO: Fehleransage falls Datum nicht vollständig angegeben wird
            }

            gebDatString = wahlTag + "." + wahlMonat + "." + wahlJahr;
            System.out.println(gebDatString);

        }

        /**
         * Betätigung vom Zurück-Button
         */
        if (source instanceof JButton) {
            if (source.equals(_view.zurueck)) {
                System.out.println("User will zurueck");
                _view.setVisible(false);
                IndexView indexFenster = new IndexView(new IndexModel(), "InProTUC Datenbank | Index");
            }

            /**
             * Betätigung vom Senden-Button
             */
            if (source.equals(_view.senden)) {
                System.out.println("User will senden");
                nachnameString = _view.nachnameTextField.getText();
                vornameString = _view.vornameTextField.getText();
                urzString = _view.urzTextField.getText();
                fakuString = _view.fakuTextField.getText();
                telString = _view.telTextField.getText();
                emailString = _view.emailTextField.getText();
                bemerkString = _view.bemerkungTextArea.getText();

                //TODO: Sql exception ans Controller übergeben?
                /**
                 * Die Daten werden ans Model geschickt
                 */
                ergebnis = _model.insertValues(nachnameString, vornameString, gebDatString, fakuString, telString, emailString, urzString, bemerkString);
                System.out.println(ergebnis);

                /**
                 * Eingabe nicht erfolgreich
                 */
                if(ergebnis == -1){
                    _view.errorDialog();
                }

                /**
                 * Eingabe erfolgreich
                 */
                else if(ergebnis == 1){
                    _view.erfolgDialog();
                }
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
    public void keyTyped (KeyEvent keyEvent){

    }

    @Override
    public void keyPressed (KeyEvent keyEvent){

    }

    @Override
    public void keyReleased (KeyEvent keyEvent){

    }
}





