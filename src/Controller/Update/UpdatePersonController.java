package Controller.Update;

import Model.Sonstiges.Functions;
import Model.Sonstiges.GeneralSqlAbfragen;
import Model.Update.IndexUpdatePersonModel;
import Model.Update.UpdatePersonModel;
import View.Sonstiges.DialogFenster;
import View.Update.UpdatePersonView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Created by annelie on 13.06.16.
 */
public class UpdatePersonController extends WindowAdapter implements ActionListener, KeyListener,MouseListener  {

    //Modell:
    private UpdatePersonModel model;
    //View:
    private UpdatePersonView view;

    /**
     *
     * @param _model ist der Model gehört zu diesem Klasse.
     * @param _view ist der View gehört zu diesem Klasse.
     */
    public UpdatePersonController(UpdatePersonModel _model,UpdatePersonView _view)
    {
        model=_model;
        view=_view;

    }





    public void keyPressed(KeyEvent e) {
        // TODO Automatisch generierter Methodenstub

    }

    public void keyReleased(KeyEvent e) {
        // TODO Automatisch generierter Methodenstub

    }

    public void keyTyped(KeyEvent e) {
        // TODO Automatisch generierter Methodenstub

    }

    public void actionPerformed(ActionEvent arg0) {
        Functions classfunction=new Functions();
        GeneralSqlAbfragen updateAgfrage=new GeneralSqlAbfragen();

        /**
         * Update Person
         */
        if(arg0.getSource()==view.btnUpdate)
        {
            DialogFenster information= new DialogFenster();
            int result=information.dialogFensterFragen(view, "Die Daten der Person werden geändert","Daten Person ändern");
            if(result==0)
            {
                int result2=information.dialogFensterFragen(view, "Möchten Sie die Daten der Person wirklich ändern?","Daten Person ändern");
                if(result2==0)
                {



                    String name=view.textName.getText();
                    String vorname=view.textvorname.getText();
                    String gD=view.textGDatum.getText();
                    String email=view.textEmail.getText();
                    String telefon=view.textTelfonne.getText();
                    String fk=view.textFakult.getText();
                    String urz=model.Geturz();
                    if((!name.equals(""))&&(!vorname.equals(""))&&(!telefon.equals(""))&&(!gD.equals(""))&&(!fk.equals(""))&&(!email.equals("")))
                    {

                        String query="UPDATE student SET name= '"+name+"',vorname= '"+vorname+"',geburtsdatum= '"+gD+"',fakultaet= '"+fk+"',telefon= '"+telefon+"',email= '"+email+"' where urz='"+urz+"'";

                        System.out.println(query);
                        boolean statusAbfrage=updateAgfrage.UpdateZeileVonTabelle(query);
                        if(statusAbfrage)
                        {
                            JOptionPane.showMessageDialog(view, "Die Daten der Person wurden erfolgreich geändert");
                            inialilView();
                        }

                    }
                    else
                    {

                        JOptionPane.showMessageDialog(view, "Es kam ein Fehler vor. Bitte überprüfen Sie die eingegebenen Daten.");
                    }


                }
            }

        }

        /**
         * Löschen Person
         */
        if(arg0.getSource()== view.btndelAkt)
        {
            int row =view.table.getSelectedRow();
            String selectedRow=view.table.getValueAt(row, 0).toString();
            System.out.println(view.table.getValueAt(row, 0));
            DialogFenster infoFenster= new DialogFenster();

            int res=infoFenster.dialogFensterFragen(view, "Möchten Sie die Person wirklich löschen?","Person löschen");
            if(res==0)
            {   GeneralSqlAbfragen sqlAbrage=new GeneralSqlAbfragen();
                String urz =model.Geturz();

                String query="select s_m_a.id from m_a inner join s_m_a on m_a.id=s_m_a.id_m_a where s_m_a.urz='"+urz+"'and m_a.aktivitaet_name='"+selectedRow+"'";
                ArrayList<String> id=model.Select(query, "id");
                String[] idgelöschtAktivitat=classfunction.ArrayList1DToSting(id);
                //+++++++++++to do sql loöschen   var selectedRow and urz
                System.out.println(idgelöschtAktivitat[0]);
                boolean statusAbfrage=sqlAbrage.ZeileVonTabelleLöschen("s_m_a", "id",idgelöschtAktivitat[0] );
                if(statusAbfrage==true)
                {
                    JOptionPane.showMessageDialog(view, "Die Person wurde erfolgreich gelöscht");
                    IndexUpdatePersonModel _model=new IndexUpdatePersonModel();
                    String [] title={"Aktivitätsname","Button"};
                    ArrayList<ArrayList<String>> list_aktivitat=_model.ListeAlleAktivitaeten(urz);
                    String [][]aktivitat=null;
                    System.out.println("Size:"+list_aktivitat.size());

                    if(list_aktivitat.size()>0)
                    {
                        aktivitat=classfunction.ArrayListToArrayVonSring(list_aktivitat);
                        String[] titleT={"Aktivität","Action"};
                        view.setDatavector(aktivitat,view.modelaktivitat,view.table,"Aktivitat" ,titleT);
                    }
                    else

                    {   String[] titleT={"Aktivität","Action"};
                        String [][]aktivitat2=new String[][]{{"hat ein ","BANI"}};
                        view.setDatavector(aktivitat2,view.modelaktivitat,view.table,"Aktivitat" ,titleT);
                        System.out.println("Hat Kein Akivität");
                    }


                }
                //+++++++++++to do this View new laden
            }



        }

        /**
         * Löschen Status Person
         */
        if(arg0.getSource()==view.btndelStatus)
        {
            int row=view.tableStatus.getSelectedRow();
            int column=0;

            String selectedRow=view.tableStatus.getValueAt(row, column).toString();

            DialogFenster infoFenster= new DialogFenster();
            //Object[] possibilities = {"löschen", "blockieren"};

            int res=infoFenster.dialogFensterFragen(view, "Möchten den Status wirklich löschen? ","Status löschen");
            if(res==0)
            {
                //+++++++++++to do sql loöschen   var selectedRow and urz
                System.out.println("löschen");
                //+++++++++++to do this View new laden
            }

        }

        /**
         * Löschen Bemerkung Person
         */
        if(arg0.getSource()==view.btndelBermerkung)
        {
            int row=view.tableBemerkung.getSelectedRow();
            int column=0;
            String selectedRow=view.tableBemerkung.getValueAt(row, column).toString();
            DialogFenster infoFenster= new DialogFenster();
            int res=infoFenster.dialogFensterFragen(view, "Möchten Sie die Bemerkung wirklich löschen?","Bemerkung löschen");
            if(res==0)
            {
                //+++++++++++to do sql loöschen   var selectedRow and urz
                System.out.println("löschen");
                //+++++++++++to do this View new laden
            }

        }




        //update Table Aktiviataet:

        //Update Button Drucken
        if(arg0.getSource()==view.btnUpdate)
        {
            //fdf
        }
    }


    public void  inialilView()
    {

        IndexUpdatePersonModel _model=new IndexUpdatePersonModel();
        Functions classfunction=new Functions();
        String urz =model.Geturz();
        String [] title={"Aktivitätsname","Button"};
        ArrayList<ArrayList<String>> list_aktivitat=_model.ListeAlleAktivitaeten(urz);
        if(list_aktivitat.size()>0)
        {
            String [][]aktivitat=classfunction.ArrayListToArrayVonSring(list_aktivitat);
            String[] titleT={"Aktivität","Action"};
            view.setDatavector(aktivitat,view.modelaktivitat,view.table,"Aktivitat" ,titleT);
        }
        else
        {
            System.out.println("Hat Kein Akivität");
        }

        //table satatus
        ArrayList<ArrayList<String>> listStatus=_model.ListeAlleStatus(urz);
        if(listStatus.size()>0)
        {
            String[][] statuslist=classfunction.ArrayListToArrayVonSring(listStatus);
            //view.modelStatus.setDataVector(statuslist, title);
            String[] titleT={"Status","Action"};
            view.setDatavector(statuslist,view.modelStatus,view.tableStatus,"Status",titleT );

        }
        else
        {
            System.out.println("hat kein Status");
        }



        //Information über Student
        String[] dataperson =classfunction.ArrayList1DToSting(_model.ListeInformationStudent(urz));
        view.updatePersonlicheInformation(dataperson);

        // Bemerkung Tabelle


        int anzahl=_model.StudentHatBermerkung(urz);
        if(anzahl>0)
        {   String[] listeBemerkung=_model.ListBermerkung(urz);
            view.updateBemerkung(listeBemerkung);
        }


    }



    public void mouseClicked(MouseEvent e) {
        JTable traget=(JTable)e.getSource();
        GeneralSqlAbfragen updateAgfrage=new GeneralSqlAbfragen();
        String SpaltenAktivitat=traget.getColumnName(0);
        int row = traget.rowAtPoint(e.getPoint());
        int coloumn=traget.columnAtPoint(e.getPoint());
        if(SpaltenAktivitat=="Status")
        {
            if((row>=0)&&(coloumn>=0)){

                String altWerte=traget.getValueAt(row, coloumn).toString();
                DialogFenster dialog=new DialogFenster();

                String message="Möchten Sie "+ traget.getValueAt(row, coloumn) + " ändern?";
                int choice=dialog.dialogFensterFragen(view, message, "Status ändern");

                if(choice==0){

                    DialogFenster dialog2=new DialogFenster();
                    String message2 = "Zu ersetzen: " + traget.getValueAt(row, coloumn) + ".\nGeben Sie neuen Werte ein.";

                    //++++++++++++++++++to do alle Status von Daten bank abfragen
                    ArrayList<String> listeStatus= model.Select("select DISTINCT status_typ from status", "status_typ");
                    Object[]   possibilities=listeStatus.toArray();
                    String defaultoption=traget.getValueAt(row, coloumn).toString();

                    String neueValue = dialog2.dialogFensterInput(view , possibilities, defaultoption,message2,"Status ändern");

                    if(neueValue!=defaultoption){
                        //++++ To do Update tablle studentStatus  student=urz, status= defaultoption neueStatus=neueValue
                        String urz=model.Geturz();
                        String query="update student_status SET status_typ='"+neueValue+"' where urz='"+urz+"' and status_typ='"+altWerte+"'";
                        boolean statusSql=updateAgfrage.UpdateZeileVonTabelle(query);
                        System.out.println("bin ich in der Tabelle Status und hier bearbeite ich Daten ");

                        if(statusSql){
                            dialog.erfolgDialog(view, "Die Daten wurden erfolgreich bearbeitet");
                            JOptionPane.showMessageDialog(view, "Die Daten wurden erfolgreich bearbeitet");
                            inialilView();
                        }
                        else
                            dialog.errorDialog(view, "Die Daten konnten nicht verändert werden.");


                    }
                }
            }
        }

        if(SpaltenAktivitat=="Aktivität")
        {
            if((row>=0)&&(coloumn>=0))
            {
                String altWerte=traget.getValueAt(row, coloumn).toString();
                DialogFenster dialog3=new DialogFenster();
                String message="Möchten Sie  " + altWerte + " ändern?";
                int choice=dialog3.dialogFensterFragen(view, message, "Aktivität ändern");

                if(choice==0)
                {	  DialogFenster dialog2=new DialogFenster();
                    String message2 = "Zu ersetzen: "+traget.getValueAt(row, coloumn)+".\nGeben Sie neuen Werte ein.";
                    //++++++++++++++++++to do alle Status von Daten bank abfragen
                    ArrayList<String> listeAkivitat= model.Select("select DISTINCT  aktivitaet_name from aktivitaet", "aktivitaet_name");
                    Object[]   possibilities=listeAkivitat.toArray();
                    String defaultoption=traget.getValueAt(row, coloumn).toString();
                    String neueValue= dialog2.dialogFensterInput(view , possibilities, defaultoption,message2,"Aktivität ändern");
                    if(neueValue!=defaultoption){

                        //System.out.println
                        //++++ To do Update tablle studentStatus  student=urz, status= defaultoption neueStatus=neueValue


                    }
                }
            }

        }
        if(SpaltenAktivitat=="Bemerkung")
        {
            if((row>=0)&&(coloumn>=0))
            {
                DialogFenster dialog3=new DialogFenster();
                String message="Möchten Sie  "+traget.getValueAt(row, coloumn)+" ändern?";
                int choice=dialog3.dialogFensterFragen(view, message, "Bemerkung ändern");
                if(choice==0)
                {	  DialogFenster dialog2=new DialogFenster();
                    String message2 = "Zu erstzen: "+traget.getValueAt(row, coloumn)+".\nGeben Sie neue Werte ein.";
                    //++++++++++++++++++to do alle Bemerkung von Daten bank abfragen

                    String defaultoption=traget.getValueAt(row, coloumn).toString();
                    String neueValue= dialog2.dialogFensterInputText(view,message2,"Bemerkung ändern");
                    if(neueValue!=defaultoption)
                    {
                        //++++ To do Update tablle studentStatus  student=urz, bemerkung= defaultoption neueBemerkung=neueValue Tabelel Studnet
                        System.out.println("Status"+neueValue);
                    }
                }
            }
        }
        // TODO Automatisch generierter Methodenstub

    }





    public void mouseEntered(MouseEvent e) {
        // TODO Automatisch generierter Methodenstub

    }





    public void mouseExited(MouseEvent e) {
        // TODO Automatisch generierter Methodenstub

    }





    public void mousePressed(MouseEvent e) {
        // TODO Automatisch generierter Methodenstub

    }





    public void mouseReleased(MouseEvent e) {
        // TODO Automatisch generierter Methodenstub

    }

}
