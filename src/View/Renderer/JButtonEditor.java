package View.Renderer;

import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;

/**
 * Created by annelie on 02.06.16.
 */
public class JButtonEditor extends AbstractCellEditor implements TableCellEditor{
    String text;
    public JButton button_table ;
    public UpdateInformationPersonModell _indexModel;
    /// zum View gehoeriger Controller
    private UpadateInformationPersonController _indexControler;

    // das ist mien Konstruktor und hier wird alle Button
    public  JButtonEditor (){

        button_table=new JButton();

        button_table.setOpaque(true);
        button_table.addActionListener(_indexControler);

    }

    public JButtonEditor(UpdateInformationPersonModell model ,UpadateInformationPersonController controler)
    {  super();
        _indexModel=model;

        _indexControler=controler;

        button_table=new JButton();

        button_table.setOpaque(true);
        button_table.addActionListener(_indexControler);

    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        button_table.setText("x");
        return button_table;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    public boolean stopCellEditing(){
        return true;
    }
}
