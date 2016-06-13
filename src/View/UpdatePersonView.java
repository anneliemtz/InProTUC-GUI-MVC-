package View;

import Controller.UpdatePersonController;
import Model.UpdatePersonModel;
import View.Renderer.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Created by annelie on 13.06.16.
 */
public class UpdatePersonView extends JFrame implements Observer  {


    // Model.
    private UpdatePersonModel model;
    // Controller.
    private UpdatePersonController controller;

    //JButtonEditor
    JButtonEditor btn ;
    JButtonEditorStatus btnStatus ;
    JButtonEditorBemerkung btnBemerkung ;
    public JButton btnUpdate;

    public JTextField  textvorname;
    public JTextField textName;
    public JTextField textFakult;
    public JTextField textEmail;
    public JTextField textGDatum;
    public JTextField textTelfonne;


    //Button für löschen eine Aktivitaet:
    public JButton btndelAkt; 	public JButton btndelStatus;

    public JTable table ; public JTable tableStatus;
    public DefaultTableModel modelaktivitat;
    public DefaultTableModel modelStatus;

    // Bermerkung Vraiable
    DefaultListModel listenModell;
    public  JPanel panel_9;
    JPanel panel_10;JPanel panel_1;
    public JButton btndelBermerkung;
    public JTable tableBemerkung;
    DefaultTableModel modelBemerkung;

    /**
     *
     * @param _model: model der Klasse UpdateInformationPersonModell
     * @param _titel: Name der Fenster als String.
     */
    public UpdatePersonView(UpdatePersonModel _model,String _titel)
    {
        model=_model;
        model.addObserver(this);
        //Controller Intializiern.
        controller=new UpdatePersonController(this.model,this);
        //zuweisen der Name der Fenster.
        super.setTitle("INPROTUC");
        //

        // Hier informier ich mein Class ButtonEditor, das er ein Model und Konroll hat
        btn = new JButtonEditor(model,controller );
        btnStatus= new JButtonEditorStatus(model,controller);
        // hier werden eine Kopie von die Button , die das Class ButtonEditor und ButtonEditorStatus besitzt gemacht , um das
        // Kontroler dieses Klass auf diese Button zugreifen kann .
        btndelAkt=btn.button_table;
        btndelStatus=btnStatus.button_table;
        //Bemerkung Tabelle
        btnBemerkung=new JButtonEditorBemerkung(model, controller);
        btndelBermerkung=btnBemerkung.button_table;
        MakeView();

    }


    public void update(Observable o, Object arg) {
        // TODO Automatisch generierter Methodenstub

    }

    /**
     *
     * @return ein Panel, in dem alle Komponanten dargestellt werden.
     */
    private JPanel  make_contentPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 775, 646);
        panel.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBounds(10, 11, 755, 624);
        panel_1.setBorder(new TitledBorder(null, "Daten Bearbeiten", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(panel_1);
        panel_1.setLayout(null);

        // Panel Bearbeiten Aktivitaet
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_3.setBounds(53, 30, 234, 131);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "Aktivit\u00E4ten", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
        panel_4.setBounds(10, 11, 214, 109);
        panel_3.add(panel_4);



        //////////////JTable Aktiviataet////////////////
        // Die Daten für das Table
        String[][] data = new String[][]{
                {"a", "b", "c" },
                {"e", "f", "g" },
                {"i", "j", "k"}
        };

        // Die Column-Titles
        String[] title = new String[]{
                "A", "B", "C", "Button"
        };

        // Das JTable initialisieren
        modelaktivitat =new DefaultTableModel();

        table = new JTable(modelaktivitat);

        table.addMouseListener(controller);

        //Button_intialisieren_Table();
        modelaktivitat.setDataVector(data,title);
        //table.getColumn(table.getColumnName(3)).setCellRenderer(new JButtonRenderer());
        // button hier ein Instanz von JButtonEditor
        //table.getColumn(table.getColumnName(3)).setCellEditor(btn);
        JScrollPane sPane=new JScrollPane(table);
        sPane.setPreferredSize(new Dimension(200,70));
        JPanel panelTabAktivitat = new JPanel(new BorderLayout());
        panelTabAktivitat.add(sPane,BorderLayout.CENTER );
        /////////////////////////////
        panel_4.add(panelTabAktivitat);


        //Pane Bearbeiten Status:
        JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_5.setBounds(455, 30, 244, 131);
        panel_1.add(panel_5);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_6.setBounds(10, 11, 224, 109);
        panel_5.add(panel_6);

//////////////JTable Status ////////////////
        // Die Daten für das Table
        String[][] dataStatus = new String[][]{
                {"1", "b", "c" },
                {"2", "f", "g" },
                {"3", "j", "k"}
        };

        // Die Column-Titles
        String[] titleStatus = new String[]{
                "A", "B", "C", "Button"
        };

        // Das JTable initialisieren
        modelStatus =new DefaultTableModel();

        tableStatus = new JTable(modelStatus);

        tableStatus.addMouseListener(controller);
        //Button_intialisieren_Table();
        modelStatus.setDataVector(dataStatus,titleStatus);

        JScrollPane sPaneStatus=new JScrollPane(tableStatus);
        sPaneStatus.setPreferredSize(new Dimension(200,70));
        JPanel panelTabStatus = new JPanel(new BorderLayout());
        panelTabStatus.add(sPaneStatus,BorderLayout.CENTER );
        /////////////////////////////
        panel_6.add(panelTabStatus);


        // Persönliche Information über Student:
        JPanel panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel_7.setBounds(53, 188, 646, 269);
        panel_1.add(panel_7);


        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Persönliche Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_8.setBounds(10, 11, 626, 247);
        panel_7.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblVorname = new JLabel("Vorname:");
        lblVorname.setBounds(25, 41, 58, 24);
        panel_8.add(lblVorname);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(25, 76, 46, 14);
        panel_8.add(lblName);

        JLabel lblEtc = new JLabel("Geburtsdatum:");
        lblEtc.setBounds(25, 111, 99, 14);
        panel_8.add(lblEtc);

        JLabel lblFakultt = new JLabel("Fakult\u00E4t:");
        lblFakultt.setBounds(25, 146, 83, 14);
        panel_8.add(lblFakultt);

        JLabel lblEmail = new JLabel("E-Mail:");
        lblEmail.setBounds(25, 181, 83, 14);
        panel_8.add(lblEmail);

        JLabel lblTelefonnummr = new JLabel("Telefonnummer:");
        lblTelefonnummr.setBounds(25, 216, 99, 14);
        panel_8.add(lblTelefonnummr);

        textvorname = new JTextField();
        textvorname.setBounds(124, 43, 141, 20);
        textvorname.setColumns(10);
        textvorname.setText("amine");
        //textvorname.setEditable(false);
        panel_8.add(textvorname);


        textName = new JTextField();
        textName.setColumns(10);
        textName.setBounds(124, 73, 141, 20);
        //textName.setEditable(false);
        panel_8.add(textName);

        textFakult = new JTextField();
        textFakult.setColumns(10);
        textFakult.setBounds(124, 140, 141, 20);
        //textFakult.setEditable(false);
        panel_8.add(textFakult);

        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(124, 178, 254, 20);
        //textEmail.setEditable(false);
        //textEmail.addActionListener(controller);
        panel_8.add(textEmail);

        textTelfonne = new JTextField();
        textTelfonne.setColumns(10);
        textTelfonne.setBounds(124, 213, 141, 20);
        //textTelfonne.setEditable(false);
        panel_8.add(textTelfonne);

        textGDatum = new JTextField();
        //textGDatum.setEditable(false);
        textGDatum.setColumns(10);
        textGDatum.setBounds(124, 109, 141, 20);
        panel_8.add(textGDatum);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(608, 588, 89, 23);
        //ActionListener
        btnUpdate.addActionListener(controller);
        panel_1.add(btnUpdate);

        JButton btnReturn = new JButton("return");
        btnReturn.setBounds(53, 588, 89, 23);
        //ActionListener
        btnReturn.addActionListener(controller);
        panel_1.add(btnReturn );


        return panel;
    }

    public void innit()
    {
        controller.inialilView();
    }



    /**
     *  Erzeugt View ,baut die Oeuberflaeche auf .
     */
    private void MakeView()
    {
        /// ContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(810, 706);
        this.setLocationRelativeTo(null);
        this. setResizable(false);
        this.setContentPane(make_contentPanel());
        /// Fenster  mit login_controler als Listner
        addWindowListener( controller);
        setVisible(true);
    }



    /*-----------------------------------------------------------------------------------------
	 *                                 update Information der Studen
	 *                             @param1:enthält alle Information über Student
	 * ---------------------------------------------------------------------------------------*/
    public void  updatePersonlicheInformation(String[] data)
    {
        textName.setText(data[0]);
        textvorname.setText(data[1]);
        textGDatum.setText(data[2]);
        textFakult.setText(data[3]);
        textTelfonne.setText(data[4]);
        textEmail.setText(data[5]);
    }

    /**
     *
     * @param dataStatus zelle der Table
     * @param tabelModel DefaultTableModel
     * @param table JTable
     * @param tableName name der Table
     */
    public void  setDatavector(String [][] dataStatus,DefaultTableModel tabelModel,JTable table,String tableName,String[]title )
    {

        tabelModel.setDataVector(dataStatus,title);

        // button hier ein Instanz von JButtonEditor
        if(tableName=="Status")
        {
            table.getColumn(table.getColumnName(1)).setCellRenderer(new JButtonRendererStatus());
            table.getColumn(tableStatus.getColumnName(1)).setCellEditor(btnStatus);
        }
        else if  (tableName=="Aktivitat")
        {
            table.getColumn(table.getColumnName(1)).setCellRenderer(new JButtonRenderer());
            table.getColumn(table.getColumnName(1)).setCellEditor(btn);
        }
        else
        {

            table.getColumn(table.getColumnName(1)).setCellRenderer(new JButtonRenderBemerkung());
            table.getColumn(table.getColumnName(1)).setCellEditor(btnBemerkung);
        }

    }


    public void updateBemerkung(String[]listBemerkung)
    {


        //////////////JTable Aktiviataet////////////////
        // Die Daten für das Table
        String[][] data = new String[1000][1];

        // Die Column-Titles
        String[] title = new String[]{
                "A","Button"
        };

        // Das JTable initialisieren
        modelBemerkung =new DefaultTableModel();
        tableBemerkung = new JTable(modelBemerkung);
        tableBemerkung.addMouseListener(controller);
        modelBemerkung.setDataVector(data,title);
        String [][] dataBemerkung=new String[listBemerkung.length][1];
        for(int i=0; i<listBemerkung.length; i++){
            dataBemerkung[i][0]=listBemerkung[i];
        }
        String [] titleT={"Bemerkung","Action"};
        setDatavector(dataBemerkung,modelBemerkung,tableBemerkung,"tableBemerkung",titleT );

        JScrollPane scrollPaneList=new JScrollPane(tableBemerkung);
        scrollPaneList.setPreferredSize(new Dimension(580,50));

        //JList wird Panel hinzugefügt
        panel_9 = new JPanel();
        panel_9.setVisible(true);
        panel_9.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_9.setBounds(53, 470, 626, 103);
        panel_1.add(panel_9);
        panel_9.setLayout(null);
        panel_10 = new JPanel();
        panel_10.add(scrollPaneList,BorderLayout.CENTER);
        panel_10.setBounds(10, 11, 600, 98);
        panel_10.setBorder(new TitledBorder(null, "Bemerkung", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
        panel_9.add(panel_10);
        // }
    }
}
