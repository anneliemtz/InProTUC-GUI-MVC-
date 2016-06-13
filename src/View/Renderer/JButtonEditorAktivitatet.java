package View.Renderer;

import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;

/**
 * Created by annelie on 13.06.16.
 */
public class JButtonEditorAktivitatet extends AbstractCellEditor implements TableCellEditor{

    String text;
    public JButton button_table ;
    private Index_Protuc_Modell _indexModel;
    /// zum View gehoeriger Controller
    private Index_Protuc_Controller _indexControler;

    // das ist mien Konstruktor und hier wird alle Button
    public  JButtonEditorAktivitatet (){

        button_table=new JButton();

        button_table.setOpaque(true);
        button_table.addActionListener(_indexControler);

    }

    public JButtonEditorAktivitatet(Index_Protuc_Modell model ,Index_Protuc_Controller controler)
    {  super();
        _indexModel=model;

        _indexControler=controler;

        button_table=new JButton();

        button_table.setOpaque(true);
        button_table.addActionListener(_indexControler);

    }

    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }


    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelcted, int row, int column) {
        button_table.setText("x");
        return button_table;

    }




    public boolean stopCellEditing(){
        return true;
    }
}
