package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by annelie on 23.04.16.
 */
public class InsertAktivitaetController implements ActionListener, KeyListener, ItemListener {

    private InsertAktivitaetModel _model;
    private InsertAktivitaetView _view;
    String massnahmeWahl, aktivitaetEingabe, massnahmeEingabe, beschreibungEingabe, zeitraumEingabe;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";


    public InsertAktivitaetController(InsertAktivitaetModel model, InsertAktivitaetView view){
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

            if (actionEvent.getSource().equals(_view.bestehendeMassnahmeCb)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                massnahmeWahl = (String) cb.getSelectedItem();
                System.out.println(massnahmeWahl);

            }

        }

        /**
         * Betätigung vom Senden-Button
         */
        if(source.equals(_view.btnSenden)) {
            aktivitaetEingabe = _view.aktTextField.getText();
            massnahmeEingabe = _view.neueMassnahmeTextField.getText();
            beschreibungEingabe = _view.bemerkungTextArea.getText();
            zeitraumEingabe = _view.zeitraumTextField.getText();

            if(_view.keineMassnahmeRBtn.isSelected()){
                System.out.println("keine Massnahme ");

                if (!aktivitaetEingabe.equals("")) {
                    int ergebnis = _model.insertValuesAktivtaet(aktivitaetEingabe, zeitraumEingabe, beschreibungEingabe);

                    if (ergebnis == 1) {
                        _view.erfolgDialog();

                    } else if (ergebnis == -1) {
                        _view.errorDialog("Ein Fehler beim Einfügen ist aufgetreten");
                        String fehlerString = _model.getErrorMessage();
                        _view.infoDialog(fehlerString);
                        return;

                    }
                }

                else{
                    _view.errorDialog("Geben Sie eine Aktivität zum Einfügen ein");
                }
            }

            if(_view.neueMassnahmeRBtn.isSelected()){
                System.out.println("neue Massnahme ");

                if (!aktivitaetEingabe.equals("") && (!massnahmeEingabe.equals(""))) {
                    int ergebnis = _model.insertValuesAktivtaetundMassnahme(aktivitaetEingabe, zeitraumEingabe, beschreibungEingabe, massnahmeEingabe);

                    if (ergebnis == 1) {
                        _view.erfolgDialog();
                    } else if (ergebnis == -1) {
                        _view.errorDialog("Ein Fehler beim Einfügen ist aufgetreten");
                        String fehlerString = _model.getErrorMessage();
                        _view.infoDialog(fehlerString);
                        return;

                    }
                }

                else if(aktivitaetEingabe.equals("")){
                    _view.errorDialog("Geben Sie eine Aktivität zum Einfügen ein");
                }

                else if(massnahmeEingabe.equals("")){
                    _view.errorDialog("Geben Sie eine Maßnahme zum Einfügen ein");
                }
            }

            if(_view.bestehendeMassnahmeRBtn.isSelected()){
                System.out.println("bestehende Massnahme ");

                if(massnahmeWahl==null){
                    _view.errorDialog("Wählen Sie eine Maßnahme zum Einfügen aus");
                    return;
                }

                if (!aktivitaetEingabe.equals("") && (!massnahmeWahl.equals("-"))) {
                    int ergebnis = _model.insertValuesAktivtaetundAddMassnahme(aktivitaetEingabe, zeitraumEingabe, beschreibungEingabe, massnahmeWahl);

                    if (ergebnis == 1) {
                        _view.erfolgDialog();
                    } else if (ergebnis == -1) {
                        _view.errorDialog("Ein Fehler beim Einfügen ist aufgetreten");
                        String fehlerString = _model.getErrorMessage();
                        _view.infoDialog(fehlerString);
                        return;

                    }
                }

                else if(aktivitaetEingabe.equals("")){
                    _view.errorDialog("Geben Sie eine Aktivität zum Einfügen ein");
                }

            }

            /**
             * System.out.println("neue Aktivitaet: " + aktivitaetEingabe);
             System.out.println("neue Massnahme: " + massnahmeEingabe);
             System.out.println("bestehende Massnahme: " + massnahmeWahl);
             System.out.println("zeitraum: " + beschreibungEingabe);
             System.out.println("beschreibung: " + zeitraumEingabe);
             */

        }

        /**
         * Betätigung vom Zurück-Button
         */
        if(source.equals(_view.btnZurueck)){
            System.out.println("User will zurueck");
            _view.setVisible(false);
            IndexView fenster = new IndexView(new IndexModel(), "InProTUC Datenbank | Index");

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
    public void itemStateChanged(ItemEvent itemEvent){
        Object source = itemEvent.getItemSelectable();

        /**
         * Reagiert auf die Radio Buttons
         */
        if (source == _view.bestehendeMassnahmeRBtn){
            if(_view.bestehendeMassnahmeRBtn.isSelected()){
                //System.out.println("bestehende Maßnahme");
                _view.bestehendeMassnahmeCb.setEnabled(true);
            }
        }

        if (source == _view.neueMassnahmeRBtn){
            if (_view.neueMassnahmeRBtn.isSelected()){
                //System.out.println("neue Maßnahme");
                _view.neueMassnahmeTextField.setEditable(true);
                _view.bestehendeMassnahmeCb.setEnabled(false);
            }

            else
                _view.neueMassnahmeTextField.setEditable(false);


        }

        if (source == _view.keineMassnahmeRBtn){
            if(_view.keineMassnahmeRBtn.isSelected()){
                //System.out.println("keine Maßnahme");
                _view.neueMassnahmeTextField.setEditable(false);
                _view.bestehendeMassnahmeCb.setEnabled(false);
            }


        }

        if (source == _view.gehoertRBtn){
            if(_view.gehoertRBtn.isSelected()){
                _view.durchfuehrungTextField.setEditable(true);
                _view.artTextField.setEditable(true);
            }

            else{
                _view.durchfuehrungTextField.setEditable(true);
                _view.artTextField.setEditable(true);
            }

        }

        if (itemEvent.getStateChange() == ItemEvent.DESELECTED){
            //System.out.println("desselect");
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
