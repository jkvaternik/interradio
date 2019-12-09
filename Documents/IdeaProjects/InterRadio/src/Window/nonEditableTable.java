package Window;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 Created by jaimekvaternik on 12/18/16.
 */
public class nonEditableTable extends DefaultTableModel {
   public nonEditableTable(Object[][] data, Object[] columns){
      super();
   }
   public boolean isCellEditable(int row, int column){
      return false;
   }
}
