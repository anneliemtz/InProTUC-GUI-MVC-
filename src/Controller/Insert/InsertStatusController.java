package Controller.Insert;

import Model.Insert.*;
import Model.Select.SelectEinfachModel;
import Model.Sonstiges.IndexModel;
import Model.Update.IndexUpdateAktivitaetModel;
import Model.Update.IndexUpdatePersonModel;
import View.Insert.*;
import View.Select.SelectEinfachView;
import View.Sonstiges.IndexView;
import View.Update.IndexUpdateAktivitaetView;
import View.Update.IndexUpdatePersonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by annelie on 02.05.16.
 */
public class InsertStatusController implements ActionListener,KeyListener {

    private InsertStatusModel _model;
    private InsertStatusView _view;
    String statusEingabe, statusStudentEingabe, studentWahl;
    String urz;
    public int ergebnis, ergebnis1, ergebnis2;
    public boolean cbActivated = false;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";



    public InsertStatusController(InsertStatusModel model, InsertStatusView view){
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

            if (actionEvent.getSource().equals(_view.studentCb)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                studentWahl = (String) cb.getSelectedItem();
                System.out.println(studentWahl);
                cbActivated = true;

            }


        }

        if (source instanceof JButton){
            System.out.println("Button");

            /**
             * Betätigung vom Zurück-Button
             */
            if(source.equals(_view.zurueckBtn)){
                System.out.println("User will zurueck");
                _view.setVisible(false);
                IndexView indexFenster = new IndexView(new IndexModel(), "InProTUC Datenbank | Index");
            }

            /**
             * Betätigung vom Senden-Button
             */
            if(source.equals(_view.sendenBtn)) {

                System.out.println("senden 1");

                statusEingabe = _view.statusTextField.getText();
                System.out.println("Status: " + statusEingabe);

                if (!statusEingabe.equals("")) {
                    System.out.println("leer");

                    /**
                     * Die Daten werden ans Model geschickt
                     */
                    ergebnis = _model.insertValues(statusEingabe);
                    System.out.println(ergebnis);

                    /**
                     * Insert Suceed
                     */
                    if (ergebnis == 1) {
                        _view.erfolgDialog();
                        return;
                    }

                    /**
                     * Fehler beim Insert
                     */
                    else if (ergebnis != 1) {
                        //TODO: Error Dialogs immer so implentieren

                        _view.errorDialog("Ein Fehler bei Status ist aufgetreten");
                        String fehlerString = _model.getErrorMessage();
                        _view.infoDialog(fehlerString);
                        return;
                    }

                }

                else{
                    _view.errorDialog("Geben Sie ein Status zum Einfügen ein");
                }


            }

            if(source.equals(_view.sendenBtn1)) {
                System.out.println("senden 2");

                statusStudentEingabe = _view.statusTextField1.getText();
                System.out.println("Status:" + statusStudentEingabe);

                if(!cbActivated || studentWahl == null){
                    _view.errorDialog("Wählen Sie eine Person zum Einfügen aus");
                    return;
                }

                if (!statusStudentEingabe.equals("") && (!studentWahl.equals("-"))){
                    System.out.println("alles da");


                    //Nachname wird aus dem kompletten Namen gesplittet
                    String[] name = studentWahl.split(",");
                    String nachname = name[0];

                    //urz des Studenten wird anhand des Namens geholt
                    urz = _model.findUrz(nachname);
                    System.out.println("urz:" + urz);

                    /**
                     * Die Daten werden ans Model geschickt
                     */
                    ergebnis1 = _model.insertValues(statusStudentEingabe);
                    ergebnis2 = _model.insertValues2(urz, statusStudentEingabe);


                    /**
                     * Insert Suceed
                     */
                    if (ergebnis1 == 1 && ergebnis2 == 1) {
                        _view.erfolgDialog();
                        return;
                    }


                    /**
                     * Fehler beim Insert
                     */
                    else if (ergebnis1 != 1 || ergebnis2 == -1) {

                        _view.errorDialog("Ein Fehler bei Status ist aufgetreten");
                        String fehlerString = _model.getErrorMessage();
                        _view.infoDialog(fehlerString);
                        return;
                    }


                }

                else if(statusStudentEingabe.equals("")){
                 _view.errorDialog("Geben Sie ein Status zum Einfügen ein");
                 }

                 else if(studentWahl.equals("-")){
                 _view.errorDialog("Wählen Sie einen Studenten zum Einfügen aus");
                 }

                else{
                    _view.errorDialog("Ein Fehler bei Status ist aufgetreten");
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

            if(commandMenu.equals(SELECT_EINFACH)){
                _view.setVisible(false);
                SelectEinfachView fensterSucheEinfach = new SelectEinfachView(new SelectEinfachModel(), "InProTUC Datenbank | Einfache Suche");

            }

            if(commandMenu.equals(UPDATE_STUDENT)){
                _view.setVisible(false);
                IndexUpdatePersonView fensterUpdatePerson = new IndexUpdatePersonView(new IndexUpdatePersonModel(), "InProTUC Datenbank | Person ändern");
            }

            if(commandMenu.equals(UPDATE_AKTIVITAET)){
                _view.setVisible(false);
                IndexUpdateAktivitaetView fensterUpdateAktivitaet = new IndexUpdateAktivitaetView(new IndexUpdateAktivitaetModel(), "InProTUC Datenbank | Aktivität ändern");
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
