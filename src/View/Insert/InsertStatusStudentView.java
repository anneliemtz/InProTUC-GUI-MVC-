package View.Insert;

import Controller.Insert.InsertStatusStudentController;
import Model.Insert.InsertStatusStudentModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by annelie on 02.05.16.
 */
public class InsertStatusStudentView extends JFrame implements Observer {
    private InsertStatusStudentModel _model;
    private InsertStatusStudentController _controller;

    public JButton sendenBtn;
    public JButton zurueckBtn;
    public JButton neuerStudentbtn;
    public JButton neuerStatusBtn;
    public JComboBox studentCb;
    public JComboBox statusCb;
    public JComboBox status1Cb;
    public JComboBox status2Cb;

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";

    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty;

    Font tex = new Font("Te X Gyre Heros", Font.PLAIN, 15);

    public InsertStatusStudentView(InsertStatusStudentModel model, String name){
        super(name);
        /**
         * Model
         */
        this._model = model;
        this._model.addObserver(this);

        /**
         * Controller
         */
        _controller = new InsertStatusStudentController(this._model, this);

        /**
         * Set JFrame
         */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 774, 528);
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
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(145, 70, 484, 361);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        controlPanel.setBorder(loweredetched);


        /**
         * Labels
         */
        JLabel infoLabel = new JLabel("Status eines Studenten eintragen");
        infoLabel.setFont(new Font("Te X Gyre Heros", Font.BOLD, 18));
        infoLabel.setBounds(145, 38, 302, 25);
        this.add(infoLabel);

        JLabel studentLbl = new JLabel("Student*");
        studentLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        studentLbl.setBounds(35, 24, 89, 27);
        controlPanel.add(studentLbl);

        JLabel statusLbl = new JLabel("Status*");
        statusLbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        statusLbl.setBounds(35, 131, 89, 27);
        controlPanel.add(statusLbl);

        JLabel neuerStudentLbl = new JLabel("Neuer Student");
        neuerStudentLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neuerStudentLbl.setBounds(296, 69, 103, 15);
        controlPanel.add(neuerStudentLbl);

        JLabel neueAktLbl = new JLabel("Neuer Status");
        neueAktLbl.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 14));
        neueAktLbl.setBounds(296, 180, 103, 15);
        controlPanel.add(neueAktLbl);

        JLabel status1Lbl = new JLabel("Status 1");
        status1Lbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
        status1Lbl.setBounds(35, 233, 89, 27);
        controlPanel.add(status1Lbl);

        /**
         * JLabel status2Lbl = new JLabel("Status 2");
         status2Lbl.setFont(new Font("Te X Gyre Heros", Font.BOLD, 16));
         status2Lbl.setBounds(35, 299, 89, 27);
         controlPanel.add(status2Lbl);
         */


        /**
         * Combo Box
         */

        //String für die Namen der Studenten wird hier erstellt
        ArrayList<String> dataStudent;
        dataStudent = _model.returnStundentName();

        String[] dataStudentArray = dataStudent.toArray(new String[dataStudent.size()]);
        int size = dataStudentArray.length;
        int j = 0;
        String[] ergebnisArray = new String[size/2];
        ergebnisArray[0] = "-";

        for (int i = 1; i< size/2; i++){
            String vollName = dataStudentArray[j] + ", " + dataStudentArray[j+1];
            ergebnisArray[i] = vollName;
            j+=2;
        }
        //System.out.println(Arrays.toString(ergebnisArray));

        //String für die Namen der Status wird hier erstellt
        ArrayList<String> dataStatus;
        dataStatus = _model.returnStatusName();
        String[] dataStatusArray = new String[dataStatus.size()+1];
        dataStatusArray[0] = "-";
        int k = 0;

        for(int i = 1; i<= dataStatus.size(); i++){
            dataStatusArray[i] = dataStatus.get(k);
            k++;
        }


        studentCb = new JComboBox(ergebnisArray);
        studentCb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        studentCb.addActionListener(_controller);
        studentCb.setBounds(155, 20, 284, 27);
        controlPanel.add(studentCb);

        statusCb = new JComboBox(dataStatusArray);
        statusCb.addActionListener(_controller);
        statusCb.setBounds(155, 131, 284, 27);
        statusCb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        controlPanel.add(statusCb);

        status1Cb = new JComboBox(dataStatusArray);
        status1Cb.addActionListener(_controller);
        status1Cb.setBounds(155, 233, 284, 27);
        status1Cb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
        controlPanel.add(status1Cb);

        /**
         * status2Cb = new JComboBox(dataStatusArray);
         status2Cb.addActionListener(_controller);
         status2Cb.setBounds(155, 299, 284, 27);
         status2Cb.setFont(new Font("Te X Gyre Heros", Font.PLAIN, 15));
         controlPanel.add(status2Cb);
         */

        /**
         * Buttons
         */
        Icon sendenIcon = new ImageIcon("images/camera_test.png");
        sendenBtn = new JButton(sendenIcon);
        sendenBtn.setBounds(371, 443, 32, 32);
        sendenBtn.setBorder(loweredetched);
        sendenBtn.addActionListener(_controller);
        this.add(sendenBtn);

        Icon zurueckIcon = new ImageIcon("images/undo-outline-48.png");
        zurueckBtn = new JButton(zurueckIcon);
        zurueckBtn.setBounds(680, 383, 49, 49);
        zurueckBtn.setBorder(loweredetched);
        zurueckBtn.addActionListener(_controller);
        this.add(zurueckBtn);

        Icon neuIcon = new ImageIcon("images/document_text_add-32.png");
        neuerStudentbtn = new JButton(neuIcon);
        neuerStudentbtn.setBounds(405, 59, 36, 36);
        neuerStudentbtn.setBorder(loweredetched);
        neuerStudentbtn.addActionListener(_controller);
        controlPanel.add(neuerStudentbtn);

        neuerStatusBtn = new JButton(neuIcon);
        neuerStatusBtn.setBounds(405, 170, 36, 36);
        neuerStatusBtn.setBorder(loweredetched);
        neuerStatusBtn.addActionListener(_controller);
        controlPanel.add(neuerStatusBtn);

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

        // Clear Combo Boxes
        this.studentCb.setSelectedIndex(0);
        this.statusCb.setSelectedIndex(0);
        this.status1Cb.setSelectedIndex(0);

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
