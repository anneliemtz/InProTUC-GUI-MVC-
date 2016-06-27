package View.Select;

import Controller.Select.SelectEinfachController;
import Model.Select.SelectEinfachModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by annelie on 06.05.16.
 */
public class SelectEinfachView extends JFrame implements Observer {

    private SelectEinfachModel _model;
    private SelectEinfachController _controller;

    public JButton senden;
    public JButton zurueck;
    public JComboBox attributCB;
    public  JTextField wertAttTextField;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    Font tex = new Font("Sans Serif", Font.PLAIN, 14);

    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty;

    String[] attString;

    public SelectEinfachView(SelectEinfachModel model,String name){
        super(name);
        /**
         * Model
         */
        this._model = model;
        this._model.addObserver(this);


        /**
         * Controller
         */
        _controller = new SelectEinfachController(this._model, this);


        /**
         * Set JFrame
         */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 774, 528);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(makeJpanel());
        setLocationRelativeTo(null);
        setBackground(Color.darkGray);
        setVisible(true);

    }

    public JPanel makeJpanel() {
        JMenuBar menuBar = makeMenuBar();
        /**
         * Panels
         */
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        controlPanel.setBounds(0, 0, 774, 528);
        controlPanel.setLayout(null);
        controlPanel.add(menuBar);

        JPanel inhaltPanel = new JPanel();
        inhaltPanel.setBounds(116, 70, 541, 363);
        inhaltPanel.setLayout(null);
        controlPanel.add(inhaltPanel);

        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        inhaltPanel.setBorder(loweredetched);


        /**
         * Labels
         */
        JLabel infoLabel = new JLabel("Einfache Suche");
        infoLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        infoLabel.setBounds(116, 40, 242, 25);
        controlPanel.add(infoLabel);

        JLabel attLbl = new JLabel("Nach welchem Attribut möchten Sie suchen?");
        attLbl.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        attLbl.setBounds(84, 67, 369, 24);
        inhaltPanel.add(attLbl);

        JLabel wertLbl = new JLabel("Geben Sie den Wert für den o.g. Attribut ein");
        wertLbl.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        wertLbl.setBounds(84, 199, 369, 29);
        inhaltPanel.add(wertLbl);


        /**
         * Combo Box
         */
        attString = new String[]{"-", "Student - Nachname", "Student - Vorname", "Fakultät", "Urz (TUC)", "Status", "Aktivität - Name", "Maßnahme - Name"};
        attributCB = new JComboBox(attString);
        attributCB.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        attributCB.addActionListener(_controller);
        attributCB.setBounds(84, 114, 369, 24);
        inhaltPanel.add(attributCB);


        /**
         * Text Fields
         */
        wertAttTextField = new JTextField();
        wertAttTextField.setBounds(84, 246, 369, 29);
        inhaltPanel.add(wertAttTextField);

        /**
         * Buttons
         */
        Icon sendenIcon = new ImageIcon("images/camera_test.png");
        senden = new JButton("ok");
        senden.setFont(tex);
        senden.setBounds(371, 445, 34, 34);
        senden.addActionListener(_controller);
        senden.setBorder(loweredetched);
        controlPanel.add(senden);

        Icon zurueckIcon = new ImageIcon("images/undo-outline-48.png");
        zurueck = new JButton("<html>zur<br>ück</html>");
        zurueck.setFont(tex);
        zurueck.setBounds(686, 385, 49, 49);
        zurueck.addActionListener(_controller);
        zurueck.setBorder(loweredetched);
        controlPanel.add(zurueck);


    return controlPanel;
    }

    private JMenuBar makeMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 774, 21);

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
