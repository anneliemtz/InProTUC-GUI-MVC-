package View;

import Controller.ErgebnisTableController;
import Model.ErgebnisTableModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by annelie on 09.05.16.
 */
public class ErgebnisTableView extends JFrame implements Observer {
    private ErgebnisTableModel _model;
    private ErgebnisTableController _controller;
    DefaultTableModel tableModel_student, tableModel_aktvitaet, tableModel_status;

    public JButton zurueck;
    public String wahlAttribut, wert;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    Font tex = new Font("Te X Gyre Heros", Font.PLAIN, 15);

    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty;

    public ErgebnisTableView(ErgebnisTableModel model, String name, String wahlAttr, String wertEingabe){
        super(name);
        /**
         * Model
         */
        this._model = model;
        this._model.addObserver(this);

        /**
         * Controller
         */
        _controller = new ErgebnisTableController(this._model, this);

        /**
         * Set JFrame
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1050, 628);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(makeJpanel());
        contentPane.add(makeMenuBar());
        setLocationRelativeTo(null);
        setBackground(Color.darkGray);
        setVisible(true);

        this.wahlAttribut = wahlAttr;
        this.wert = wertEingabe;

    }

    public JPanel makeJpanel() {

        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        tableModel_student = _model.getTableModel_Student(wahlAttribut, wert);
        tableModel_aktvitaet = _model.getTableModel_Aktivitaet(wahlAttribut, wert);
        tableModel_status = _model.getTableModel_Status(wahlAttribut, wert);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 1050, 628);
        controlPanel.setLayout(null);
        //MigLayout layout0 = new MigLayout("", "[][][]", "[][][]");
        //controlPanel.setLayout(layout0);

        JTable table_student = new JTable(tableModel_student);
        JScrollPane scrollpane_student = new JScrollPane(table_student); //creating a scroll pane that serves as a container for a table
        table_student.setFillsViewportHeight(true);
        scrollpane_student.setBorder(raisedetched);
        scrollpane_student.setBounds(36, 22, 380, 542);
        controlPanel.add(scrollpane_student, "");

        JTable table_aktivitaet = new JTable(tableModel_aktvitaet);
        JScrollPane scrollpane_aktivitaet = new JScrollPane(table_aktivitaet); //creating a scroll pane that serves as a container for a table
        table_aktivitaet.setFillsViewportHeight(true);
        scrollpane_aktivitaet.setBorder(raisedetched);
        scrollpane_aktivitaet.setBounds(439, 23, 549, 213);
        controlPanel.add(scrollpane_aktivitaet, "");

        JTable table_status = new JTable(tableModel_status);
        JScrollPane scrollpane_status = new JScrollPane(table_student); //creating a scroll pane that serves as a container for a table
        table_status.setFillsViewportHeight(true);
        scrollpane_status.setBorder(raisedetched);
        scrollpane_status.setBounds(439, 266, 282, 298);
        controlPanel.add(scrollpane_status, "");

        /**
         * Labels
         */
        JLabel infoLabel = new JLabel("Einfache Suche");
        infoLabel.setFont(new Font("Te X Gyre Heros", Font.BOLD, 18));

        /**
         * Buttons
         */
        Icon zurueckIcon = new ImageIcon("images/undo-outline-48.png");
        zurueck = new JButton(zurueckIcon);
        zurueck.addActionListener(_controller);
        zurueck.setBorder(loweredetched);

        //controlPanel.add(infoLabel, "cell 1 0, align left"); // "cell column row width height"
        controlPanel.add(zurueck, "cell 2 1, gapleft 25, bottom");

        return  controlPanel;
    }

    private JMenuBar makeMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1074, 21);

        /**
         *  Menu Datei
         */
        JMenu dateiMnBar = new JMenu("Datei");
        dateiMnBar.setFont(tex);
        menuBar.add(dateiMnBar);

        JMenuItem druckenMn = new JMenuItem("Drucken");
        druckenMn.setFont(tex);
        druckenMn.addActionListener(_controller);
        dateiMnBar.add(druckenMn);

        JMenu exportMnBar = new JMenu("Exportieren");
        exportMnBar.setFont(tex);
        dateiMnBar.add(exportMnBar);

        JMenuItem excelMn = new JMenuItem("Datenbank als Excel-Tabelle exportieren");
        excelMn.setFont(tex);
        excelMn.addActionListener(_controller);
        exportMnBar.add(excelMn);

        JMenuItem pfdMn = new JMenuItem("Datenbank als Pdf exportieren");
        pfdMn.setFont(tex);
        pfdMn.addActionListener(_controller);
        exportMnBar.add(pfdMn);

        /**
         * Menu Suchen
         */
        JMenu suchenMnBar = new JMenu("Suchen");
        suchenMnBar.setFont(tex);
        menuBar.add(suchenMnBar);

        JMenuItem einfacheSucheMn = new JMenuItem("Einfache Suche");
        einfacheSucheMn.setFont(tex);
        einfacheSucheMn.addActionListener(_controller);
        suchenMnBar.add(einfacheSucheMn);

        JMenuItem optimierteSuche = new JMenuItem("Erweiterte Suche");
        optimierteSuche.setFont(tex);
        optimierteSuche.addActionListener(_controller);
        suchenMnBar.add(optimierteSuche);

        /**
         * Menu Einfügen
         */
        JMenu einfügenMnBar = new JMenu("Einfügen");
        einfügenMnBar.setFont(tex);
        menuBar.add(einfügenMnBar);


        JMenuItem neuerStudentMn = new JMenuItem(INSERT_STUDENT);
        neuerStudentMn.setFont(tex);
        neuerStudentMn.addActionListener(_controller);
        einfügenMnBar.add(neuerStudentMn);


        JMenuItem aktStudentMn  = new JMenuItem(INSERT_AKT_STU);
        aktStudentMn.setFont(tex);
        aktStudentMn.addActionListener(_controller);
        einfügenMnBar.add(aktStudentMn);


        JMenuItem statusStudentMn  = new JMenuItem(INSERT_STATUS_STUDENT);
        statusStudentMn.setFont(tex);
        statusStudentMn.addActionListener(_controller);
        einfügenMnBar.add(statusStudentMn);


        JMenuItem neueAktMn  = new JMenuItem(INSERT_AKT);
        neueAktMn.setFont(tex);
        neueAktMn.addActionListener(_controller);
        einfügenMnBar.add(neueAktMn);


        JMenuItem neuerStatusMn  = new JMenuItem(INSERT_STATUS);
        neuerStatusMn.setFont(tex);
        neuerStatusMn.addActionListener(_controller);
        einfügenMnBar.add(neuerStatusMn);

        /**
         * Menu Ändern
         */
        JMenu aendernMnBar = new JMenu("Ändern");
        aendernMnBar.setFont(tex);
        menuBar.add(aendernMnBar);

        JMenuItem aendernStuMn = new JMenuItem(UPDATE_STUDENT);
        aendernStuMn.setFont(tex);
        aendernStuMn.addActionListener(_controller);
        aendernMnBar.add(aendernStuMn);

        JMenuItem aendernAktMn = new JMenuItem(UPDATE_AKTIVITAET);
        aendernAktMn.setFont(tex);
        aendernAktMn.addActionListener(_controller);
        aendernMnBar.add(aendernAktMn);

        JMenuItem aendernStatusMn = new JMenuItem(UPDATE_STATUS);
        aendernStatusMn.setFont(tex);
        aendernStatusMn.addActionListener(_controller);
        aendernMnBar.add(aendernStatusMn);

        return menuBar;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
