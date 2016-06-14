package Main;

import Model.*;
import View.*;

/**
 * Created by annelie on 18.04.16.
 */
public class Main {
    public static void main(String args[]){


        //IndexView fenster = new IndexView(new IndexModel(), "Index");
        //InsertStudentVIew fenster = new InsertStudentVIew(new InsertStudentModel(), "Index");
        //InsertAktStudentView fenster = new InsertAktStudentView(new InsertAktStudentModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
        //InsertAktivitaetView fenster = new InsertAktivitaetView(new InsertAktivitaetModel(), "InProTUC Datenbank | Aktivität eines Studenten eintragen");
        //InsertStatusStudentView fenster = new InsertStatusStudentView(new InsertStatusStudentModel(), "InProTUC Datenbank |");
        //InsertStatusView fenster = new InsertStatusView(new InsertStatusModel(), "Fenster");
        //BorderLayoutDemo fenster = new BorderLayoutDemo();

        IndexUpdatePersonView login =new IndexUpdatePersonView(new IndexUpdatePersonModel(),"login");




    }
}

