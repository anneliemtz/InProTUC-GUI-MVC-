package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by annelie on 06.05.16.
 */
public class SelectEinfachController implements ActionListener,KeyListener {
    private SelectEinfachModel _model;
    private SelectEinfachView _view;


    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    String wahlAttr, wert;

    public SelectEinfachController(SelectEinfachModel model, SelectEinfachView view){
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
            if (actionEvent.getSource().equals(_view.attributCB)){
                JComboBox cb = (JComboBox) actionEvent.getSource();
                wahlAttr = (String) cb.getSelectedItem();
            }
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

                wert = _view.wertAttTextField.getText();
                System.out.println("Attribut: " + wahlAttr);
                System.out.println("Wert: " + wert);

                /**
                 * model_student = _model.getTableModel_Student(wahlAttr, wert);
                 model_aktvitaet = _model.getTableModel_Aktivitaet(wahlAttr, wert);
                 model_status = _model.getTableModel_Status(wahlAttr, wert);
                 */

                //_view.setVisible(false);
                ErgebnisTableView table_student = new ErgebnisTableView(new ErgebnisTableModel(), "InProTUC Datenbank | Ergebnistabelle", wahlAttr, wert);

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
