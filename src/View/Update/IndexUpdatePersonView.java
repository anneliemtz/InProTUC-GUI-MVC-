package View.Update;

import Controller.Update.IndexUpdatePersonController;
import Model.Sonstiges.Functions;
import Model.Update.IndexUpdatePersonModel;
import View.Renderer.JButtonEditor;
import View.Renderer.JButtonEditorAktivitatet;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Created by annelie on 13.06.16.
 */
public class IndexUpdatePersonView extends JFrame implements Observer {

    //TODO: Wofür?
    JButtonEditor buton;
    public  JButton table_modifer_button;
    public JButtonEditorAktivitatet btnAktivitat;
    public   JButton buttonAktivitatinofo ;

    /**
     * Variabeln setzen
     */
    JLabel lblEmailValue;
    JLabel lblFakultatValue;
    JLabel lblGeburstsdatumValue;
    JLabel lblNameValue;
    JLabel lblVornameValue;
    JLabel lblTelefonValue;
    public JTable tableStudent;
    /**
     * Bemerkung
     */
    public static JPanel panel_2;
    public static JPanel panel_9;
    JPanel panel_10;
    DefaultListModel listenModell;
    /**
     * Aktivitattabelle
     */
    public JTable tableAktivitaet;
    public DefaultTableModel modelAktivitaet;
    public String [] titleTabeleAkivitaet;
    /**
     * Status
     */
    public JTable tabelleStatus;
    public String[] titleTabelleStatus;
    public DefaultTableModel modelStatus;
    /**
     * Button Update
     */
    public JButton btnupdateDate;

    /**
     * Model & Controller
     */
    public  IndexUpdatePersonModel model;
    private IndexUpdatePersonController controller;

    Font texBold = new Font("Te X Gyre Heros", Font.BOLD, 15);
    Font tex = new Font("Te X Gyre Heros", Font.PLAIN, 15);

    String INSERT_STUDENT = "Neuer Student einfügen", INSERT_AKT_STU = "Aktivität eines Studenten eintragen", INSERT_STATUS_STUDENT = "Status eines Studenten eintragen", INSERT_AKT = "Neue Aktivität einfügen", INSERT_STATUS = "Neuer Status einfügen";
    String UPDATE_STUDENT = "Daten eines Studenten ändern oder löschen", UPDATE_AKTIVITAET = "Aktivität ändern oder löschen", UPDATE_STATUS = "Status ändern oder löschen";
    String SELECT_EINFACH = "Einfache Suche", SELECT_ERWEITERT = "Erweiterte Suche";
    String DATEI_DRUCKEN = "Drucken", DATEI_EXPO = "Exportieren", DATEI_PDF = "Datenbank als Pdf exportieren", DATEI_EXCEL = "Datenbank als Excel-Tabelle exportieren";


    /*---------------------------------------------------------------------------------------
     *Konstruktor: Setzt das MVC
     *
     * ---------------------------------------------------------------------------------------*/
    public IndexUpdatePersonView (IndexUpdatePersonModel model, String titel ){

        //MODEL
        this.model=model;
        this.model.addObserver(this);

        //CONTROLLER
        controller=new IndexUpdatePersonController(this.model,this);

        //VIEW
        setTitle(titel);

        //TODO: Wofür?
        btnAktivitat=new JButtonEditorAktivitatet(model, controller);
        buttonAktivitatinofo=btnAktivitat.button_table;

        makeView();
    }

    private void makeView(){

        /// ContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this. setResizable(false);
        this.setContentPane(panel());

        /// Fenster  mit login_controler als Listner
        addWindowListener(controller);
        setVisible(true);
    }
    /*-----------------------------------------------------------------------------------------
     *                                 update Information der Studen
     *                             @data enthält alle persönlichen Daten des Studenten
     * ---------------------------------------------------------------------------------------*/
    public void  updatePersonlicheInformation(String[] data)
    {
        lblNameValue.setText(data[0]);
        lblVornameValue.setText(data[1]);
        lblGeburstsdatumValue.setText(data[2]);
        lblFakultatValue.setText(data[3]);
        lblTelefonValue.setText(data[4]);
        lblEmailValue.setText(data[5]);
    }

    public void updateBemerkung(String[]listBemerkung){
        listenModell = new DefaultListModel();

        for(int i=0; i<listBemerkung.length; i++){
            listenModell.addElement(listBemerkung[i]);

            //DefaultListModell wird erzeugt
            //JList mit Einträgen wird erstellt
            JList themenAuswahl = new JList(listenModell);
            themenAuswahl.setEnabled(false);

            //JScrollPane für Jlist
            JScrollPane scrollPaneList=new JScrollPane(themenAuswahl);
            scrollPaneList.setPreferredSize(new Dimension(380,70));

            //JList wird Panel hinzugefügt
            panel_9.setVisible(true);
            panel_9.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel_9.setBounds(37, 414, 410, 130);
            panel_2.add(panel_9);
            panel_9.setLayout(null);
            panel_10 = new JPanel();
            panel_10.add(scrollPaneList,BorderLayout.CENTER);
            panel_10.setBounds(10, 11, 395, 120);
            panel_10.setBorder(new TitledBorder(null, "Bemerkung", TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), Color.GRAY));
            panel_9.add(panel_10);
        }
    }


    private JPanel panel(){



        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 676);
        panel.setLayout(null);
        JMenuBar menuBar = makeMenuBar();
        panel.add(menuBar);


        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person", TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), new Color(0, 0, 0)));
        panel_1.setBounds(26, 34,374,317);
        panel.add(panel_1);


        // Table Student
        Functions classFunction =new Functions() ;
        //Die Datenbank abfragen nach alle Studen und speichern die Ergbnisse in einer Tabelle.
        String[][] data=classFunction.ArrayListToArrayVonSring(this.model.ListeAufStudenten());
        String []title =new String[]{"Urz","Vorname","Name"};

        DefaultTableModel model=new DefaultTableModel();
        tableStudent=new JTable(model);

        // ActionListener für Tabelle Student.
        tableStudent.addMouseListener(controller);
        model.setDataVector(data, title);
        //tableStudent.getColumn(tableStudent.getColumnName(3)).setCellRenderer(new JButtonRenderer());
        // button hier ein Instanz von JButtonEditor
        //tableStudent.getColumn(tableStudent.getColumnName(3)).setCellEditor(buton);
        JScrollPane sPane=new JScrollPane(tableStudent);
        sPane.setPreferredSize(new Dimension(350,280));
        panel_1.add(sPane,BorderLayout.CENTER);

        panel_2 = new JPanel();
        String label = "Informationen zu Person";
        //label.setFont(tex);
        panel_2.setBorder(new TitledBorder(null, label, TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), Color.BLACK));
        panel_2.setBounds(439, 34, 635, 619);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_3.setBounds(37, 46, 588, 131);
        panel_2.add(panel_3);
        panel_3.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "Akivit\u00E4ten", TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), Color.BLACK));
        panel_4.setBounds(10, 11, 568, 109);
        String [][]dataAktivitaet=new String[][]{{"1","x"}};
        titleTabeleAkivitaet=new String[]{"ID","Aktivitätsname","Zeitraum","Durchfürung","Art"};
        modelAktivitaet=new DefaultTableModel();

        tableAktivitaet=new JTable(modelAktivitaet);
        modelAktivitaet.setDataVector(dataAktivitaet, titleTabeleAkivitaet);
        tableAktivitaet.getColumnModel().getColumn(0).setPreferredWidth(102);
        //tableAktivitaet.getColumnModel().getColumn(0).setPreferredWidth(15);
        //tableAktivitaet.getColumn(tableAktivitaet.getColumnName(0)).setCellRenderer(new JButtonRenderer());
        //tableAktivitaet.getColumn(tableAktivitaet.getColumnName(0)).setCellEditor(btnAktivitat);
        //tableAktivitaet.getColumn(tableAktivitaet.getColumnName(1)).setCellRenderer(new JButtonRenderer());

        JScrollPane spane2=new JScrollPane(tableAktivitaet);
        spane2.setPreferredSize(new Dimension(550,70));
        panel_4.add(spane2,BorderLayout.CENTER);
        panel_3.add(panel_4);

        JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_5.setBounds(451, 414, 174, 135);
        panel_2.add(panel_5);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Status", TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), new Color(0, 0, 0)));
        panel_6.setBounds(10, 8, 154, 122);
        // tabelle Status von Student
        titleTabelleStatus=new String[]{"Status"};
        String[][] dataStatus=new String[][] {{"1"}};
        modelStatus=new DefaultTableModel();
        modelStatus.setDataVector(dataStatus, titleTabelleStatus);
        tabelleStatus=new JTable(modelStatus);
        tabelleStatus.addMouseListener(controller);
        tabelleStatus.setEnabled(true);
        JScrollPane spanestatus=new JScrollPane(tabelleStatus);
        spanestatus.setPreferredSize(new Dimension(120,70));
        panel_6.add(spanestatus,BorderLayout.CENTER);
        panel_5.add(panel_6);

        JPanel panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_7.setBounds(37, 202, 588,180);
        panel_2.add(panel_7);

        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Persönliche Information", TitledBorder.LEADING, TitledBorder.TOP, new Font("Te X Gyre Heros", Font.PLAIN, 16), new Color(0, 0, 0)));
        panel_8.setBounds(60, 11, 474, 160);
        panel_7.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblVorname = new JLabel("Vorname:");
        lblVorname.setFont(texBold);
        lblVorname.setBounds(25, 31, 80, 14);
        panel_8.add(lblVorname);
        lblVornameValue = new JLabel("........................");
        lblVornameValue.setFont(tex);
        lblVornameValue.setBounds(110, 31, 300, 14);
        panel_8.add(lblVornameValue);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(texBold);
        lblName.setBounds(25, 51, 50, 17);
        panel_8.add(lblName);
        lblNameValue = new JLabel("........................");
        lblNameValue.setFont(tex);
        lblNameValue.setBounds(80, 51, 300, 14);
        panel_8.add(lblNameValue);

        JLabel lblGeburstsdatum = new JLabel("Geburstdatum:");
        lblGeburstsdatum.setFont(texBold);
        lblGeburstsdatum.setBounds(25, 71, 115, 20);
        panel_8.add(lblGeburstsdatum);
        lblGeburstsdatumValue = new JLabel("........................");
        lblGeburstsdatumValue.setBounds(145, 71, 200, 20);
        lblGeburstsdatumValue.setFont(tex);
        panel_8.add(lblGeburstsdatumValue);


        JLabel lblFakultat = new JLabel("Fakultät:");
        lblFakultat.setFont(texBold);
        lblFakultat.setBounds(25, 91, 70, 20);
        panel_8.add(lblFakultat);
        lblFakultatValue = new JLabel("........................");
        lblFakultatValue.setFont(tex);
        lblFakultatValue.setBounds(90, 91, 300, 20);
        panel_8.add(lblFakultatValue);




        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(25, 111, 50, 20);
        lblEmail.setFont(texBold);
        panel_8.add(lblEmail);
        lblEmailValue= new JLabel("................................");
        lblEmailValue.setFont(tex);
        lblEmailValue.setBounds(75, 111, 380, 20);
        panel_8.add(lblEmailValue);

        JLabel lblTelefon = new JLabel("Telefonnummer:");
        lblTelefon.setBounds(25, 137, 120, 14);
        lblTelefon.setFont(texBold);
        lblTelefonValue = new JLabel("................................");
        lblTelefonValue.setFont(tex);
        lblTelefonValue.setBounds(150, 137, 310, 14);
        panel_8.add(lblTelefonValue);
        panel_8.add(lblTelefon);
        panel_9 = new JPanel();

        // Darstellen die Information über die Erste Student
        controller.Daten_von_Erste_Student_Darstellen();
        //update Button
        btnupdateDate=new JButton("Update");

        btnupdateDate.setBounds(287, 573, 89,23);
        btnupdateDate.setFont(tex);
        btnupdateDate.addActionListener(controller);
        panel_2.add(btnupdateDate);

        return panel ;
    }

    public JPanel getPanel(){
        return this.panel_9;
    }

    /*---------------------------------------------------------------------------------------
   *     Desinstaliert MVC
   *
   * ---------------------------------------------------------------------------------------*/
    public void release(){
        //TODO: MVC desinstallieren
        /// Contorler Desinstalieren
        /// Model Desinstalieren

    }

    /*-----------------------------------------------------------------------------------------
     *   Ueberschreibt Interfacemehtode , legt Reaktion auf Aenderungen fest
     *
     * ---------------------------------------------------------------------------------------*/
    public void update(Observable arg0, Object arg1) {
        // TODO Automatisch generierter Methodenstub
    }

    private JMenuBar makeMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1100, 21);

        /**
         *  Menu Datei
         */
        JMenu dateiMnBar = new JMenu("Datei");
        dateiMnBar.setFont(tex);
        menuBar.add(dateiMnBar);

        JMenuItem druckenMn = new JMenuItem("Drucken");
        druckenMn.setFont(tex);
        druckenMn.addActionListener(controller);
        dateiMnBar.add(druckenMn);

        JMenu exportMnBar = new JMenu("Exportieren");
        exportMnBar.setFont(tex);
        dateiMnBar.add(exportMnBar);

        JMenuItem excelMn = new JMenuItem("Datenbank als Excel-Tabelle exportieren");
        excelMn.setFont(tex);
        excelMn.addActionListener(controller);
        exportMnBar.add(excelMn);

        JMenuItem pfdMn = new JMenuItem("Datenbank als Pdf exportieren");
        pfdMn.setFont(tex);
        pfdMn.addActionListener(controller);
        exportMnBar.add(pfdMn);

        /**
         * Menu Suchen
         */
        JMenu suchenMnBar = new JMenu("Suchen");
        suchenMnBar.setFont(tex);
        menuBar.add(suchenMnBar);

        JMenuItem einfacheSucheMn = new JMenuItem("Einfache Suche");
        einfacheSucheMn.setFont(tex);
        einfacheSucheMn.addActionListener(controller);
        suchenMnBar.add(einfacheSucheMn);

        JMenuItem optimierteSuche = new JMenuItem("Erweiterte Suche");
        optimierteSuche.setFont(tex);
        optimierteSuche.addActionListener(controller);
        suchenMnBar.add(optimierteSuche);

        /**
         * Menu Einfügen
         */
        JMenu einfügenMnBar = new JMenu("Einfügen");
        einfügenMnBar.setFont(tex);
        menuBar.add(einfügenMnBar);


        JMenuItem neuerStudentMn = new JMenuItem(INSERT_STUDENT);
        neuerStudentMn.setFont(tex);
        neuerStudentMn.addActionListener(controller);
        einfügenMnBar.add(neuerStudentMn);


        JMenuItem aktStudentMn  = new JMenuItem(INSERT_AKT_STU);
        aktStudentMn.setFont(tex);
        aktStudentMn.addActionListener(controller);
        einfügenMnBar.add(aktStudentMn);


        JMenuItem statusStudentMn  = new JMenuItem(INSERT_STATUS_STUDENT);
        statusStudentMn.setFont(tex);
        statusStudentMn.addActionListener(controller);
        einfügenMnBar.add(statusStudentMn);


        JMenuItem neueAktMn  = new JMenuItem(INSERT_AKT);
        neueAktMn.setFont(tex);
        neueAktMn.addActionListener(controller);
        einfügenMnBar.add(neueAktMn);


        JMenuItem neuerStatusMn  = new JMenuItem(INSERT_STATUS);
        neuerStatusMn.setFont(tex);
        neuerStatusMn.addActionListener(controller);
        einfügenMnBar.add(neuerStatusMn);

        /**
         * Menu Ändern
         */
        JMenu aendernMnBar = new JMenu("Ändern");
        aendernMnBar.setFont(tex);
        menuBar.add(aendernMnBar);

        JMenuItem aendernStuMn = new JMenuItem(UPDATE_STUDENT);
        aendernStuMn.setFont(tex);
        aendernStuMn.addActionListener(controller);
        aendernMnBar.add(aendernStuMn);

        JMenuItem aendernAktMn = new JMenuItem(UPDATE_AKTIVITAET);
        aendernAktMn.setFont(tex);
        aendernAktMn.addActionListener(controller);
        aendernMnBar.add(aendernAktMn);

        JMenuItem aendernStatusMn = new JMenuItem(UPDATE_STATUS);
        aendernStatusMn.setFont(tex);
        aendernStatusMn.addActionListener(controller);
        aendernMnBar.add(aendernStatusMn);

        return menuBar;
    }
}
