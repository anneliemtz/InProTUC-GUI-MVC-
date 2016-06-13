package View;

import Controller.InsertAktStudentController;
import Controller.InsertStudentController;
import Model.InsertAktStudentModel;
import View.InsertAktStudentView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by annelie on 23.04.16.
 */
public class InsertAktStudentView extends JFrame implements Observer{
    private InsertAktStudentModel _model;
    private InsertAktStudentController _controller;

    public JPanel controlPanel1;
    public JButton senden, zurueck;
    public JButton neuerStudentbtn;
    public JButton neueAktivitaetbtn;
    public JTextField studentTextField;
    public JTextField AktivitätTextField;
    public JTextField semesterTextField;
    public JTextField artTextField;
    public JTextArea bemerkungTextArea;
    public JComboBox studentCb;
    public JComboBox aktivitaetCb;
    public JLabel artLbl;
    public JLabel durchfuehrungLbl;
    public JLabel jaLbl;
    public JLabel neinLbl;
    public JRadioButton jaRBtn;
    public JRadioButton neinRBtn;
    public int bool;

    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty;

    Font tex = new Font("Te X Gyre Heros", Font.PLAIN, 15);

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    String[] nameStudent;


    public InsertAktStudentView(InsertAktStudentModel model, String name){
        super(name);
        /**
         * Model
         */
        this._model = model;
        this._model.addObserver(this);

        /**
         * Controller
         */
        _controller = new InsertAktStudentController(this._model, this);

        /**
         * Set JFrame
         */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 774, 578);
        getContentPane().add(makeJpanel(), BorderLayout.CENTER);

        /**
         * JPanel contentPane = new JPanel();
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(contentPane);
         contentPane.setLayout(null);
         contentPane.add(makeMenuBar());
         contentPane.add(makeJpanel());
         */
        setLocationRelativeTo(null);
        setBackground(Color.darkGray);
        setVisible(true);

    }

    private void adjustScrollBar(JComboBox box) {
        if (box.getItemCount() == 0) return;
        Object comp = box.getUI().getAccessibleChild(box, 0);
        if (!(comp instanceof JPopupMenu)) {
            return;
        }
        JPopupMenu popup = (JPopupMenu) comp;
        JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
        scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void adjustPopupWidth(JComboBox box) {
        if (box.getItemCount() == 0) return;
        Object comp = box.getUI().getAccessibleChild(box, 0);
        if (!(comp instanceof JPopupMenu)) {
            return;
        }
        JPopupMenu popup = (JPopupMenu) comp;
        JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
        Object value = box.getItemAt(0);
        Component rendererComp = box.getRenderer().getListCellRendererComponent(null, value, 0, false, false);
        if (rendererComp instanceof JTable) {
            scrollPane.setColumnHeaderView(((JTable) rendererComp).getTableHeader());
        }


        Dimension prefSize = rendererComp.getPreferredSize();
        Dimension size = scrollPane.getPreferredSize();
        size.width = Math.max(size.width, prefSize.width);
        scrollPane.setPreferredSize(size);
        scrollPane.setMaximumSize(size);
        scrollPane.revalidate();
    }

    public JPanel makeJpanel() {
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        this.add(makeMenuBar());

        /**
         * Panels
         */
        JPanel contentPane = new JPanel();
        contentPane.setBounds(100, 100, 774, 578);
        contentPane.setLayout(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(145, 68, 484, 303);
        controlPanel.setLayout(null);
        controlPanel.setBorder(loweredetched);
        contentPane.add(controlPanel);

        controlPanel1 = new JPanel();
        controlPanel1.setBounds(145, 383, 484, 112);
        controlPanel1.setLayout(null);
        controlPanel1.setBorder(loweredetched);
        controlPanel1.setEnabled(false);
        contentPane.add(controlPanel1);

        /**
         * Titled Border
         */
        TitledBorder title, title1;

        title = BorderFactory.createTitledBorder(
                loweredetched, "Für Aktivitäten der Art Mobilität");
        title.setTitleJustification(TitledBorder.LEFT);
        title.setTitleFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        controlPanel1.setBorder(title);


        /**
         *  Buttons
         */
        Icon sendenIcon = new ImageIcon("images/camera_test.png");
        senden = new JButton(sendenIcon);
        senden.setBounds(375, 507, 32, 32);
        senden.setBorder(loweredetched);
        senden.addActionListener(_controller);
        this.add(senden);

        Icon zurueckIcon = new ImageIcon("images/undo-outline-48.png");
        zurueck = new JButton(zurueckIcon);
        zurueck.setBounds(687, 446, 49, 49);
        zurueck.setBorder(loweredetched);
        zurueck.addActionListener(_controller);
        this.add(zurueck);

        Icon neuIcon = new ImageIcon("images/document_text_add-32.png");
        neuerStudentbtn = new JButton(neuIcon);
        neuerStudentbtn.setBounds(407, 60, 34, 34);
        neuerStudentbtn.setBorder(loweredetched);
        neuerStudentbtn.addActionListener(_controller);
        controlPanel.add(neuerStudentbtn);

        neueAktivitaetbtn = new JButton(neuIcon);
        neueAktivitaetbtn.setBounds(407, 143, 34,34);
        neueAktivitaetbtn.setBorder(loweredetched);
        neueAktivitaetbtn.addActionListener(_controller);
        controlPanel.add(neueAktivitaetbtn);

        /**
         * Radio Buttons
         */

        jaRBtn = new JRadioButton("ja");
        jaRBtn.setBounds(168, 36, 52, 23);
        jaRBtn.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        jaRBtn.setVisible(false);
        controlPanel1.add(jaRBtn);

        neinRBtn = new JRadioButton("nein");
        neinRBtn.setBounds(240, 36, 65, 23);
        neinRBtn.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neinRBtn.setVisible(false);
        controlPanel1.add(neinRBtn);

        ButtonGroup groupDurchfuehrungRBtn = new ButtonGroup();
        groupDurchfuehrungRBtn.add(jaRBtn);
        groupDurchfuehrungRBtn.add(neinRBtn);


        /**
         *  Labels
         */
        JLabel infoLabel = new JLabel("Aktivität eines Studenten eintragen");
        infoLabel.setFont(new Font("Te X Gyre Heros", Font.BOLD, 18));
        infoLabel.setBounds(145, 38, 317, 25);
        this.add(infoLabel);

        JLabel studentLbl = new JLabel("Student*");
        studentLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        studentLbl.setBounds(35, 21, 89, 27);
        controlPanel.add(studentLbl);

        JLabel semesterLbl = new JLabel("Semester");
        semesterLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        semesterLbl.setBounds(31, 191, 103, 27); 
        controlPanel.add(semesterLbl);

        JLabel bemerkungLbl = new JLabel("Bemerkung");
        bemerkungLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        bemerkungLbl.setBounds(34, 234, 89, 27);
        controlPanel.add(bemerkungLbl);

        JLabel aktivitaetLbl = new JLabel("Aktivität*");
        aktivitaetLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        aktivitaetLbl.setBounds(35, 104, 89, 27);
        controlPanel.add(aktivitaetLbl);

        JLabel neuStudentLbl = new JLabel("Neuer Student");
        neuStudentLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neuStudentLbl.setBounds(296, 69, 103, 15);
        controlPanel.add(neuStudentLbl);

        JLabel neuAktLbl = new JLabel("Neue Aktivität");
        neuAktLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neuAktLbl.setBounds(296, 153, 103, 15);
        controlPanel.add(neuAktLbl);

        artLbl = new JLabel("Art");
        artLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        artLbl.setBounds(33, 73, 39, 27);
        artLbl.setEnabled(false);
        controlPanel1.add(artLbl);

        durchfuehrungLbl = new JLabel("Durchführung");
        durchfuehrungLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        durchfuehrungLbl.setBounds(33, 34, 121, 27);
        durchfuehrungLbl.setEnabled(false);
        controlPanel1.add(durchfuehrungLbl);

        jaLbl = new JLabel("ja");
        jaLbl.setBounds(192, 40, 39, 20);
        jaLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        jaLbl.setEnabled(false);
        controlPanel1.add(jaLbl);

        neinLbl = new JLabel("nein");
        neinLbl.setBounds(268, 40, 39, 20);
        neinLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neinLbl.setEnabled(false);
        controlPanel1.add(neinLbl);


        /**
         *  Combo Box
         */

        //String für die Namen der Studenten wird hier erstellt
        ArrayList<String> dataStudent;
        dataStudent = _model.returnStundentName();

        String[] dataStudentArray = dataStudent.toArray(new String[dataStudent.size()]);
        int size = dataStudentArray.length;
        int j = 0;
        String[] ergebnisArray = new String[size/2];

        for (int i = 0; i< size/2; i++){
            String vollName = dataStudentArray[j] + ", " + dataStudentArray[j+1];
            ergebnisArray[i] = vollName;
            j+=2;
        }
        //System.out.println(Arrays.toString(ergebnisArray));

        //String für die Namen der Aktivitaeten wird hier erstellt
        ArrayList<String> dataAktivitaet;
        dataAktivitaet = _model.returnAktivitaetName();
        String[] dataAktivitaetArray = dataAktivitaet.toArray(new String[dataAktivitaet.size()]);

        //System.out.println(Arrays.toString(dataAktivitaetArray));


        studentCb = new JComboBox(ergebnisArray);
        studentCb.setBounds(155, 21, 284, 27);
        studentCb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        studentCb.addActionListener(_controller);
        controlPanel.add(studentCb);

        aktivitaetCb = new JComboBox(dataAktivitaetArray);
        aktivitaetCb.setBounds(155, 104, 284, 27);
        aktivitaetCb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        adjustScrollBar(aktivitaetCb);
        //adjustPopupWidth(aktivitaetCb);
        aktivitaetCb.addActionListener(_controller);
        controlPanel.add(aktivitaetCb);

        // Auswahl wird ausgegeben
        String a = (String) aktivitaetCb.getSelectedItem();//get the selected item
        System.out.println(a);

        // Es wird in der Datenbank überprüft ob die Aktivitat einer der Art Mobilität ist
        bool = _model.returnMobilitaet(a);

        // Wenn die Funktion Mob 1 zurückgibt ist es eine Mobilität-Aktivität und der zusätzliche Panel wid editierbar
        if(bool == 1){
            System.out.println("Mob einschlaten");
            controlPanel1.setEnabled(true);
            artLbl.setEnabled(true);
            durchfuehrungLbl.setEnabled(true);
            jaLbl.setEnabled(true);
            neinLbl.setEnabled(true);
            artTextField.setEditable(true);
            jaRBtn.setVisible(true);
            neinRBtn.setVisible(true);
        }

        /**
         *  Text Fields
         */
        semesterTextField = new JTextField();
        semesterTextField.setColumns(10);
        semesterTextField.setBounds(156, 196, 284, 27);
        semesterTextField.setBorder(loweredetched);
        controlPanel.add(semesterTextField);

        artTextField = new JTextField();
        artTextField.setColumns(10);
        artTextField.setBounds(165, 73, 284, 27);
        artTextField.setBorder(loweredetched);
        artTextField.setEditable(false);
        controlPanel1.add(artTextField);

        bemerkungTextArea = new JTextArea();
        bemerkungTextArea.setBounds(155, 234, 284, 51);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        bemerkungTextArea.setBorder(loweredetched);
        bemerkungTextArea.setEditable(true);
        controlPanel.add(bemerkungTextArea);

        return contentPane;
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
