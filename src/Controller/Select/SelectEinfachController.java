package Controller.Select;

import Model.Insert.*;
import Model.Select.SelectEinfachModel;
import Model.Select.SuchTableModel;
import Model.Sonstiges.IndexModel;
import Model.Update.IndexUpdateAktivitaetModel;
import Model.Update.IndexUpdatePersonModel;
import View.Insert.*;
import View.Select.SelectEinfachView;
import View.Select.SuchTableView;
import View.Sonstiges.IndexView;
import View.Update.IndexUpdateAktivitaetView;
import View.Update.IndexUpdatePersonView;

import javax.swing.*;
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

    String[] AttributeKomplett = {"name", "vorname", "fakultaet", "", ""};
    String attribut;
    String query;

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
                _view.setVisible(false);

                wert = _view.wertAttTextField.getText();
                System.out.println("Attribut: " + wahlAttr);
                System.out.println("Wert: " + wert);

                if(wahlAttr.equals("Student - Nachname")){
                    String attribut = "name";
                    query = "select name, vorname, urztuc, urz from student where name = '"+ wert +"' ORDER BY name;";
                    SuchTableView fenster = new SuchTableView(new SuchTableModel(), "fenster", query, attribut, wert);
                }

                if(wahlAttr.equals("Student - Vorname")){
                    String attribut = "vorname";
                    query = "select name, vorname, urztuc, urz from student where vorname = '"+ wert +"' ORDER BY name;";
                    SuchTableView fenster = new SuchTableView(new SuchTableModel(), "fenster", query, attribut, wert);

                }

                if(wahlAttr.equals("Fakultät")){
                    String attribut = "fakultaet";
                    query = "select name, vorname, urztuc, urz from student where fakultaet = '"+ wert +"' ORDER BY name;";
                    SuchTableView fenster = new SuchTableView(new SuchTableModel(), "fenster", query, attribut, wert);

                }

                if(wahlAttr.equals("Status")){
                    //TODO
                    String attribut = "status";
                    query = "select name, vorname, urztuc, student.urz from student inner join student_status on student_status.urz = student.urz  where status_typ = '"+wert+"' ORDER BY name;  ";
                    SuchTableView fenster = new SuchTableView(new SuchTableModel(), "fenster", query, attribut, wert);

                }

                if(wahlAttr.equals("Aktivität - Name")){
                    //TODO
                    String attribut = "aktivitaet_name";
                    query = "select name, vorname, urztuc, student.urz from student  " +
                            "inner join s_m_a on student.urz = s_m_a.urz  " +
                            "inner join m_a on s_m_a.id_m_a = m_a.id  " +
                            "inner join aktivitaet on aktivitaet.aktivitaet_name = m_a.aktivitaet_name  " +
                            "where aktivitaet.aktivitaet_name = '"+wert+"' ORDER BY name;";
                    SuchTableView fenster = new SuchTableView(new SuchTableModel(), "fenster", query, attribut, wert);

                }

                if(wahlAttr.equals("Maßnahme - Name")){
                    //TODO
                    String attribut;

                }

                /**
                 * model_student = _model.getTableModel_Student(wahlAttr, wert);
                 model_aktvitaet = _model.getTableModel_Aktivitaet(wahlAttr, wert);
                 model_status = _model.getTableModel_Status(wahlAttr, wert);
                 */

                //_view.setVisible(false);
                //ErgebnisTableView table_student = new ErgebnisTableView(new ErgebnisTableModel(), "InProTUC Datenbank | Ergebnistabelle", wahlAttr, wert);

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
