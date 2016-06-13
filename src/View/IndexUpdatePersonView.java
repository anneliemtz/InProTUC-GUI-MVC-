package View;

import Controller.IndexUpdatePersonController;
import Model.Functions;
import Model.IndexUpdatePersonModel;
import View.Renderer.JButtonEditor;
import View.Renderer.JButtonEditorAktivitatet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.TileObserver;
import java.util.Observable;
import java.util.Observer;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * Created by annelie on 13.06.16.
 */
public class IndexUpdatePersonView extends JFrame implements Observer {

    // Deklaration von Variablen
    JButtonEditor buton;

    public  JButton table_modifer_button;

    public JButtonEditorAktivitatet btnAktivitat;
    public   JButton buttonAktivitatinofo ;

    /// zum View gehoeriger Model
    public  IndexUpdatePersonModel model;
    /// zum View gehoeriger Controller
    private IndexUpdatePersonController controller;



    /*---------------------------------------------------------------------------------------
     * Konstruktur zum Setzung von MVC
     * @parm mode Login_Model: welches dargestellt werden soll .
     * @parm titel String: Name von der Fenster.
     *
     * ---------------------------------------------------------------------------------------*/
    public IndexUpdatePersonView (IndexUpdatePersonModel model, String titel ){
        //MODEL
        this.model=model;
        this.model.addObserver(this);
        //CONTROLLER
        controller=new IndexUpdatePersonController(this.model,this);
        //VIEW
        super.setTitle("index");
        btnAktivitat=new JButtonEditorAktivitatet(model, controller);
        buttonAktivitatinofo=btnAktivitat.button_table;
        // Hier informier ich mein Class ButtonEditor, das er ein Model und Konroll hat
        //--->  buton=new JButtonEditor(model,controller);  ---> funktioniert
        // hier werden eine Kopie von die Button , die das Class ButtonEditor besitzt gemacht , um das
        // Kontroler dieses Klass auf diese Button zugreifen kann .
        //  table_modifer_button=buton.button_table;
        makeView();
    }

    /*---------------------------------------------------------------------------------------
	 *     Desinstaliert MVC
	 *
	 * ---------------------------------------------------------------------------------------*/
    public void release(){
        /// Contorler Desinstalieren
        //to do enchala
        /// Model Desinstalieren
        // to do enchlala
    }

    /*-----------------------------------------------------------------------------------------
     *   Ueberschreibt Interfacemehtode , legt Reaktion auf Aenderungen fest
     *
     * ---------------------------------------------------------------------------------------*/
    public void update(Observable arg0, Object arg1) {
        // TODO Automatisch generierter Methodenstub

    }

    /*-----------------------------------------------------------------------------------------
     *                                 update Information der Studen
     *                             @param1:enthält alle Information über Student
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

    public void updateBemerkung(String[]listBemerkung)
    {listenModell = new DefaultListModel();
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
            panel_9.setBounds(37, 414, 410, 115);
            panel_2.add(panel_9);
            panel_9.setLayout(null);
            panel_10 = new JPanel();
            panel_10.add(scrollPaneList,BorderLayout.CENTER);
            panel_10.setBounds(10, 11, 390, 100);
            panel_10.setBorder(new TitledBorder(null, "Bemerkung", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
            panel_9.add(panel_10);
        }
    }

	    /*-----------------------------------------------------------------------------------------
		 *                                 make View
		 *
		 * ---------------------------------------------------------------------------------------*/

    JLabel lblEmailValue;
    JLabel lblFakultatValue;
    JLabel lblGeburstsdatumValue;
    JLabel lblNameValue;
    JLabel lblVornameValue;
    JLabel lblTelefonValue;
    public JTable tableStudent;
    // Bemerkung
    public static JPanel panel_2;
    public static JPanel panel_9;
    JPanel panel_10;
    DefaultListModel listenModell;
    // Aktivitattabelle:
    public JTable tableAktivitaet;
    public DefaultTableModel modelAktivitaet;
    public String [] titleTabeleAkivitaet;
    // Status
    public JTable tabelleStatus;
    public String[] titleTabelleStatus;
    public DefaultTableModel modelStatus;
    //Buton Update:
    public JButton btnupdateDate;


    private JPanel panel(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1084, 676);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
        panel_2.setBorder(new TitledBorder(null, "Informationen zu Person", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panel_2.setBounds(439, 34, 635, 619);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_3.setBounds(37, 46, 588, 131);
        panel_2.add(panel_3);
        panel_3.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "Akivit\u00E4ten", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panel_4.setBounds(10, 11, 568, 109);
        String [][]dataAktivitaet=new String[][]{{"1","x"}};
        titleTabeleAkivitaet=new String[]{"ID","Aktivitätsname","Zeitraum"};
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
        panel_5.setBounds(451, 414, 174, 115);
        panel_2.add(panel_5);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_6.setBounds(10, 8, 154, 100);
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
        panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Persnliche Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_8.setBounds(60, 11, 474, 160);
        panel_7.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblVorname = new JLabel("Vorname:");
        lblVorname.setBounds(25, 31, 80, 14);
        panel_8.add(lblVorname);
        lblVornameValue = new JLabel("........................");
        lblVornameValue.setBounds(110, 31, 300, 14);
        panel_8.add(lblVornameValue);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(25, 51, 50, 14);
        panel_8.add(lblName);
        lblNameValue = new JLabel("........................");
        lblNameValue.setBounds(80, 51, 300, 14);
        panel_8.add(lblNameValue);

        JLabel lblGeburstsdatum = new JLabel("Geburstdatum:");
        lblGeburstsdatum.setBounds(25, 71, 115, 14);
        panel_8.add(lblGeburstsdatum);
        lblGeburstsdatumValue = new JLabel("........................");
        lblGeburstsdatumValue.setBounds(145, 71, 300, 14);
        panel_8.add(lblGeburstsdatumValue);


        JLabel lblFakultat = new JLabel("Fakultät:");
        lblFakultat.setBounds(25, 91, 70, 14);
        panel_8.add(lblFakultat);
        lblFakultatValue = new JLabel("........................");
        lblFakultatValue.setBounds(90, 91, 300, 14);
        panel_8.add(lblFakultatValue);




        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(25, 111, 50, 14);
        panel_8.add(lblEmail);
        lblEmailValue= new JLabel("................................");
        lblEmailValue.setBounds(75, 111, 375, 14);
        panel_8.add(lblEmailValue);

        JLabel lblTelefon = new JLabel("Telefonnummer:");
        lblTelefon.setBounds(25, 133, 120, 14);
        lblTelefonValue = new JLabel("................................");
        lblTelefonValue.setBounds(150, 133, 310, 14);
        panel_8.add(lblTelefonValue);
        panel_8.add(lblTelefon);
        panel_9 = new JPanel();
        // Darstellen die Information über die Erste Student
        controller.Daten_von_Erste_Student_Darstellen();
        //update Button
        btnupdateDate=new JButton("Update");

        btnupdateDate.setBounds(287, 573, 89,23);
        btnupdateDate.addActionListener(controller);
        panel_2.add(btnupdateDate);

        return panel ;
    }

    /*---------------------------------------------------------------------------------------
   *      Erzeugt View ,baut die Oeuberflaeche auf .
   *
   * ----------------------------------------------------------------------------------*/
    private void makeView()
    {
        /// ContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this. setResizable(false);
        this.setContentPane(panel());
        /// Fenster  mit login_controler als Listner
        addWindowListener(controller);
        setVisible(true);
    }
    public JPanel getPanel(){
        return this.panel_9;
    }
}
