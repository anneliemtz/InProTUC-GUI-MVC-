package Main;

import Model.Insert.InsertStudentModel;
import Model.Select.SelectEinfachModel;
import Model.Sonstiges.IndexModel;
import Model.Update.IndexUpdateAktivitaetModel;
import Model.Update.IndexUpdatePersonModel;
import Model.Update.UpdateAktivitaetModel;
import View.Insert.InsertStudentVIew;
import View.Select.SelectEinfachView;
import View.Sonstiges.IndexView;
import View.Update.IndexUpdateAktivitaetView;
import View.Update.IndexUpdatePersonView;
import View.Update.UpdateAktivitaetView;

/**
 * Created by annelie on 18.04.16.
 */
public class Main {
    /**
     * Create a jar file
     */
    //http://introcs.cs.princeton.edu/java/85application/jar/jar.html
    //http://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html
    //n


    public static void main(String args[]){


        //IndexView fenster = new IndexView(new IndexModel(), "Index");

        InsertStudentVIew fenster = new InsertStudentVIew(new InsertStudentModel(), "Index");
        //InsertAktStudentView fenster = new InsertAktStudentView(new InsertAktStudentModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
        //InsertAktivitaetView fenster = new InsertAktivitaetView(new InsertAktivitaetModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
        //InsertStatusStudentView fenster = new InsertStatusStudentView(new InsertStatusStudentModel(), "InProTUC Datenbank |");
        //InsertStatusView fenster = new InsertStatusView(new InsertStatusModel(), "Fenster");
        //BorderLayoutDemo fenster = new BorderLayoutDemo();

        //IndexUpdatePersonView login = new IndexUpdatePersonView(new IndexUpdatePersonModel(),"login");

        //TableView fenster = new TableView(new TableModel(), "suche");

        //SelectEinfachView fenster = new SelectEinfachView(new SelectEinfachModel(), "fenster");

        //IndexUpdatePersonView fenster = new IndexUpdatePersonView(new IndexUpdatePersonModel(), "fenster");

        //IndexUpdateAktivitaetView fnster = new IndexUpdateAktivitaetView(new IndexUpdateAktivitaetModel(), "fenster");





    }
}

