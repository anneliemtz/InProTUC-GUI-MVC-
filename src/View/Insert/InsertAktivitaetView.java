package View.Insert;

import Controller.Insert.InsertAktivitaetController;
import Model.Insert.InsertAktivitaetModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by annelie on 23.04.16.
 */
public class InsertAktivitaetView extends JFrame implements Observer{

    private InsertAktivitaetModel _model;
    private InsertAktivitaetController _controller;

    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty;

    Font tex = new Font("Te X Gyre Heros", Font.PLAIN, 15);

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    String[] dataMassnahmeArray;

    public JRadioButton keineMassnahmeRBtn;
    public JRadioButton neueMassnahmeRBtn;
    public JRadioButton bestehendeMassnahmeRBtn;
    public JRadioButton gehoertRBtn;
    public JButton btnSenden;
    public JButton btnZurueck;
    public JComboBox bestehendeMassnahmeCb;
    public JTextField aktTextField;
    public JTextField neueMassnahmeTextField;
    public JTextField zeitraumTextField;
    public JTextField durchfuehrungTextField;
    public JTextField artTextField;
    public JTextArea bemerkungTextArea;


    public InsertAktivitaetView(InsertAktivitaetModel model, String name){
        super(name);
        /**
         * Model
         */
        this._model = model;
        this._model.addObserver(this);

        /**
         * Controller
         */
        _controller = new InsertAktivitaetController(this._model, this);

        /**
         * Set JFrame
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 774, 528);

        /**
         *  getContentPane().add(makeJpanel(), BorderLayout.CENTER);
         */
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(makeMenuBar());
        contentPane.add(makeJpanel());
        setLocationRelativeTo(null);
        setBackground(Color.darkGray);
        setVisible(true);


    }

    public JPanel makeJpanel() {
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();

        /**
         * Panels
         */
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(116, 70, 541, 363);
        controlPanel.setBorder(loweredetched);

        /**
         * Labels
         */
        JLabel infoLabel = new JLabel("Neue Aktivität einfügen");
        infoLabel.setFont(new Font("Te X Gyre Heros", Font.BOLD, 18));
        infoLabel.setBounds(116, 40, 242, 25);
        this.add(infoLabel);

        JLabel aktLbl = new JLabel("Name der Aktivität");
        aktLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        aktLbl.setBounds(22, 22, 150, 17);
        controlPanel.add(aktLbl);

        JLabel lblBeschreibung = new JLabel("Beschreibung");
        lblBeschreibung.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        lblBeschreibung.setBounds(22, 221, 116, 31);
        controlPanel.add(lblBeschreibung);

        JLabel lblZeitraum = new JLabel("Zeitraum");
        lblZeitraum.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        lblZeitraum.setBounds(22, 305, 84, 17);
        controlPanel.add(lblZeitraum);


        /**
         * Text Fields
         */
        aktTextField = new JTextField();
        aktTextField.setColumns(10);
        aktTextField.setBounds(242, 19, 261, 27);
        aktTextField.setBorder(loweredetched);
        controlPanel.add(aktTextField);


        neueMassnahmeTextField = new JTextField();
        neueMassnahmeTextField.setColumns(10);
        neueMassnahmeTextField.setBounds(242, 100, 261, 27);
        neueMassnahmeTextField.setBorder(loweredetched);
        neueMassnahmeTextField.setEditable(false);
        controlPanel.add(neueMassnahmeTextField);

        bemerkungTextArea = new JTextArea();
        bemerkungTextArea.setColumns(10);
        bemerkungTextArea.setRows(2);
        bemerkungTextArea.setLineWrap(true);
        bemerkungTextArea.setWrapStyleWord(true);
        bemerkungTextArea.setBounds(242, 221, 261, 51);
        bemerkungTextArea.setBorder(loweredetched);
        bemerkungTextArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(bemerkungTextArea);
        controlPanel.add(bemerkungTextArea);

        zeitraumTextField = new JTextField();
        zeitraumTextField.setColumns(10);
        zeitraumTextField.setBounds(242, 300, 261, 27);
        zeitraumTextField.setBorder(loweredetched);
        controlPanel.add(zeitraumTextField);


        /**
         * Combo Box
         */

        //String für die Namen der Massnahme wird hier erstellt
        /**
         *  ArrayList<String> dataMassnahme;
         dataMassnahme = _model.returnMassnahmeName();
         dataMassnahmeArray = dataMassnahme.toArray(new String[dataMassnahme.size()]);
         */

        //String für die Namen der Status wird hier erstellt
        ArrayList<String> dataMassnahme;
        dataMassnahme = _model.returnMassnahmeName();
        String[] dataMassnahmeArray = new String[dataMassnahme.size()+1];
        dataMassnahmeArray[0] = "-";
        int k = 0;

        for(int i = 1; i<= dataMassnahme.size(); i++){
            dataMassnahmeArray[i] = dataMassnahme.get(k);
            k++;
        }

        bestehendeMassnahmeCb = new JComboBox(dataMassnahmeArray);
        bestehendeMassnahmeCb.setBounds(242, 151, 261, 27);
        bestehendeMassnahmeCb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        bestehendeMassnahmeCb.setEnabled(false);
        controlPanel.add(bestehendeMassnahmeCb);

        //System.out.println(Arrays.toString(dataMassnahmeArray));


        /**
         * Radio Button
         */

        keineMassnahmeRBtn = new JRadioButton("Keine Maßnahme");
        keineMassnahmeRBtn.setBounds(33, 56, 160, 23);
        keineMassnahmeRBtn.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        keineMassnahmeRBtn.addActionListener(_controller);
        keineMassnahmeRBtn.addItemListener(_controller);
        keineMassnahmeRBtn.setSelected(true);
        controlPanel.add(keineMassnahmeRBtn);

        neueMassnahmeRBtn = new JRadioButton("Neue Maßnahme");
        neueMassnahmeRBtn.setBounds(30, 102, 177, 23);
        neueMassnahmeRBtn.addActionListener(_controller);
        neueMassnahmeRBtn.addItemListener(_controller);
        neueMassnahmeRBtn.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        controlPanel.add(neueMassnahmeRBtn);

        bestehendeMassnahmeRBtn = new JRadioButton("Bestehende Maßnahme");
        bestehendeMassnahmeRBtn.setBounds(32, 153, 203, 23);
        bestehendeMassnahmeCb.addActionListener(_controller);
        bestehendeMassnahmeRBtn.addItemListener(_controller);
        bestehendeMassnahmeRBtn.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        controlPanel.add(bestehendeMassnahmeRBtn);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(keineMassnahmeRBtn);
        group.add(neueMassnahmeRBtn);
        group.add(bestehendeMassnahmeRBtn);

        /**
         * Buttons
         */
        Icon sendenIcon = new ImageIcon("images/camera_test.png");
        btnSenden = new JButton(sendenIcon);
        btnSenden.setBounds(371, 445, 32, 32);
        btnSenden.setBorder(loweredetched);
        btnSenden.addActionListener(_controller);
        this.add(btnSenden);

        Icon zurueckIcon = new ImageIcon("images/undo-outline-48.png");
        btnZurueck = new JButton(zurueckIcon);
        btnZurueck.setBounds(686, 385, 49, 49);
        btnZurueck.setBorder(loweredetched);
        btnZurueck.addActionListener(_controller);
        this.add(btnZurueck);


        return controlPanel;
    }

    public void erfolgDialog(){

        JLabel erfolgEingabeLbl = new JLabel("<html>Die eingegebenen Daten wurden erfolgreich gespeichert </html>");
        erfolgEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon erfolgIcon = new ImageIcon("images/yes2.png");


        //custom title, custom icon
        JOptionPane.showMessageDialog(this,
                erfolgEingabeLbl,
                "Eingabe erfolgreich",
                JOptionPane.PLAIN_MESSAGE,
                erfolgIcon);

        // Clear Text Field
        this.aktTextField.setText("");
        this.bemerkungTextArea.setText("");
        this.zeitraumTextField.setText("");
        this.neueMassnahmeTextField.setText("");
        this.bestehendeMassnahmeCb.setSelectedIndex(0);


    }

    public void errorDialog(String message){

        JLabel fehlerEingabeLbl = new JLabel("<html>" + message +" </html>");
        fehlerEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon fehlerIcon = new ImageIcon("images/nope2.png");

        //custom title, custom icon
        JOptionPane.showMessageDialog(this,
                fehlerEingabeLbl,
                "Eingabe nicht erfolgreich",
                JOptionPane.PLAIN_MESSAGE,
                fehlerIcon);
    }

    public void infoDialog(String message){

        JLabel fehlerEingabeLbl = new JLabel("<html>" + message +" </html>");
        fehlerEingabeLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 16));
        Icon fehlerIcon = new ImageIcon("images/warning.png");

        //custom title, custom icon
        JOptionPane.showMessageDialog(this,
                fehlerEingabeLbl,
                "Information",
                JOptionPane.PLAIN_MESSAGE,
                fehlerIcon);
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
