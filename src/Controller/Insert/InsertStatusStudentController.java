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
public class InsertStatusStudentController implements ActionListener,KeyListener {
    private InsertStatusStudentModel _model;
    private InsertStatusStudentView _view;

    String wahlStudent, wahlStatus, wahlStatus1, wahlStatus2;
    String urz;
    int ergebnis, ergebnis1;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";


    public InsertStatusStudentController(InsertStatusStudentModel model, InsertStatusStudentView view){
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
                wahlStudent = (String) cb.getSelectedItem();
                System.out.println(wahlStudent);

            }

            if (actionEvent.getSource().equals(_view.statusCb)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlStatus = (String) cb.getSelectedItem();
                System.out.println(wahlStatus);

            }

            if (actionEvent.getSource().equals(_view.status1Cb)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlStatus1 = (String) cb.getSelectedItem();
                System.out.println(wahlStatus1);

            }

            if (actionEvent.getSource().equals(_view.status2Cb)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlStatus2 = (String) cb.getSelectedItem();
                System.out.println(wahlStatus2);

            }

        }

        /**
         * Betätigung Button
         */
        if (source instanceof JButton){
            System.out.println("button");

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
            // TODO: Redundante Variabeln?
            if(source.equals(_view.sendenBtn)){
                System.out.println("User will senden");
                System.out.println(wahlStudent + wahlStatus + wahlStatus1);

                if(wahlStudent != null){ // Student muss angegeben wurden

                    //Nachname wird aus dem kompletten Namen gesplittet
                    String[] name = wahlStudent.split(",");
                    String nachname = name[0];
                    System.out.println("getrimt:" + nachname);

                    //urz des Studenten wird anhand des Namens geholt
                    urz = _model.findUrz(nachname);
                    System.out.println("urz:" + urz);

                    /**
                     * Ein Insert-Statement: wahlStatus
                     */
                    if((wahlStatus != null || !wahlStatus.equals("-"))  && (wahlStatus1 == null || wahlStatus1.equals("-"))){

                        //Daten werden eingefügt
                         ergebnis = _model.insertValues(urz, wahlStatus);
                         System.out.println("ergebnis:" + ergebnis);

                        /**
                         * Insert Suceed
                         */
                        if(ergebnis == 1){
                            _view.erfolgDialog();
                            return;
                        }

                        /**
                         * Fehler beim Insert
                         */
                        else if(ergebnis != 1){
                            //TODO: Error Dialogs immer so implentieren

                            _view.errorDialog("Ein Fehler bei Status ist aufgetreten");
                            String fehlerString = _model.getErrorMessage();
                            _view.infoDialog(fehlerString);
                            return;
                        }

                    }

                    /**
                     * Ein Insert-Statement: wahlStatus1
                     */
                    if((wahlStatus == null || wahlStatus.equals("-")) && (wahlStatus1 != null || !wahlStatus1.equals("-"))){

                        //Daten werden eingefügt
                        ergebnis = _model.insertValues(urz, wahlStatus1);
                        System.out.println("ergebnis:" + ergebnis);

                        if(ergebnis == 1){
                            _view.erfolgDialog();
                            return;
                        }

                        else{
                            _view.errorDialog("Ein Fehler ist aufgetreten");
                            String fehlerString = _model.getErrorMessage();
                            _view.infoDialog(fehlerString);
                            return;
                        }


                    }



                    /**
                     * Zwei Insert-Statements
                     */
                    if((wahlStatus != null || wahlStatus != "-") && (wahlStatus1 != null || wahlStatus1 != "-")){
                    System.out.println("Zwei Insert-Statements");

                        if(wahlStatus.equals(wahlStatus1) && (!wahlStatus.equals("-"))){
                            _view.errorDialog("Es wurde zwei mal den gleichen Status für einen Student eingegeben");
                            return;
                        }

                        //Daten werden eingefügt
                        ergebnis = _model.insertValues(urz, wahlStatus);
                        System.out.println("ergebnis:" + ergebnis);

                        /**
                         * ergebnis = _model.insertValues2(urz, wahlStatus, wahlStatus1);
                         System.out.println("ergebnis:" + ergebnis);
                         */

                        ergebnis1 = _model.insertValues(urz, wahlStatus1);
                        System.out.println("ergebnis 1:" + ergebnis1);

                        if(ergebnis == 1 && ergebnis1 ==1){
                            _view.erfolgDialog();
                            return;
                        }

                        else{
                            _view.errorDialog("Ein Fehler ist aufgetreten");
                            String fehlerString = _model.getErrorMessage();
                            _view.infoDialog(fehlerString);
                            return;
                        }


                    }

                    /**
                     * Kein Status angegeben
                     */
                    else if((wahlStatus == null || wahlStatus.equals("-")) && (wahlStatus1 == null || wahlStatus1.equals("-"))){
                        _view.errorDialog("Bitte geben Sie mindestens einen Status ein");
                        return;
                    }
                }

                else{
                    _view.errorDialog("Bitte geben Sie einen Student ein");
                }

            }

            /**
             * Betätigung vom NeuerStudent-Button
             */
            if(source.equals(_view.neuerStudentbtn)){
                System.out.println("User will Student neu einfügen");
                _view.setVisible(false);
                InsertStudentVIew fensterNeuStu = new InsertStudentVIew(new InsertStudentModel(), "InProTUC Datenbank | Student neu einfügen");

            }

            /**
             * Betätigung vom NeuerStatus-Button
             */
            if(source.equals(_view.neuerStatusBtn)){
                System.out.println("User will Satus neu einfügen");
                _view.setVisible(false);
                //InsertStatusView fensterInsertStatus = new InsertStatusView("InProTUC Datenbank | Status neu einfügen");


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
