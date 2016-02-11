
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.TableModel;



public class PanelTable extends JTable {

	public PanelTable(TableModel model) {
		
		super(model);
		setRowHeight(25);
		setShowGrid(true);
		setPreferredScrollableViewportSize(new Dimension(200, 440));     
		getColumnModel().getColumn(0).setPreferredWidth(40);
		getColumnModel().getColumn(1).setPreferredWidth(80);
		getColumnModel().getColumn(2).setPreferredWidth(80);
		getTableHeader().setReorderingAllowed(false);
	
	}
	
}
