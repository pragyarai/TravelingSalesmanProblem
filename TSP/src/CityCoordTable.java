
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

public class CityCoordTable extends AbstractTableModel {

	   private Double[] xCoord;
	   private Double[] yCoord;
	   
	   @Override
	public String toString() {
		return "CityCoordTable [xCoord=" + Arrays.toString(xCoord) + ", yCoord=" + Arrays.toString(yCoord) + "]";
	}

	public CityCoordTable(int arg) {
		   xCoord = new Double[arg];
		   yCoord = new Double[arg];
		  
	   }
	   
	   public int getColumnCount() {
		   return 3;
	   }
	   public int getRowCount() {
		   return xCoord.length;
	   }

	   public Double[] getXCoordArray() {
		   return xCoord;
	   }
	   public Double[] getYCoordArray() {
		   return yCoord;
	   }
   
	   public Object getValueAt(int row, int col) {  
		   if (col == 0)
			   return (row+1);   
		   else if (col == 1)
			   return xCoord[row];   
		   else
			   return yCoord[row];   
	   }
	   public Class<?> getColumnClass(int col) {
		   if (col == 0)
			   return Integer.class;
		   else
			   return Double.class;   
	   }
	   
	   public String getColumnName(int col) {
		   if (col == 0) return "#";
		   else if (col == 1) return "X";
		   else return "Y";
	   }  
	   
	   public boolean isCellEditable(int row, int col) {
		   return col>0;
	   }
	   
	   public void setValueAt(Object obj, int row, int col) { 

		   if (col == 1) xCoord[row] = (Double)obj;
		   else if (col == 2) yCoord[row] = (Double)obj;
		  
		   fireTableCellUpdated(row, col);
		   
	   }
	  
	   
	   public ArrayList<City> getAllCity() {
		   
		   ArrayList<City> points = new ArrayList<City>(getXCoordArray().length);
		   
		   for(int i = 0; i<getXCoordArray().length;i++)
				 if(getXCoordArray()[i] != null && getYCoordArray()[i] != null)
					points.add(new City(getXCoordArray()[i],getYCoordArray()[i]));
		   
		   return points;
		   
	   }
	  
 
   
}  