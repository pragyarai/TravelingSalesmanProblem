
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class GuiPanel extends JPanel {
    
	private IOPanel leftPane; 
	private CityCoordTable cordModel;
	private int totalNodes,maxNodes;
	private CitySet citySet;
	private NodePlotPanel sPlot; 
	private FinalPathPaint pathPaint;
	   	   
	
	public GuiPanel(int numNodes, int lim) {
		   
		  setLayout(new BorderLayout());
	      
	      maxNodes = lim;
	      totalNodes = numNodes;
	      cordModel = new CityCoordTable(totalNodes);
	      leftPane = new IOPanel(this, cordModel);
	      pathPaint = new FinalPathPaint();
	      sPlot = new NodePlotPanel();
	      
	      pathPaint.setPreferredSize(new Dimension(300,300));
		  sPlot.setPreferredSize(new Dimension(300,300));
	      sPlot.setBackground(new Color(190,190,190)); 
	      

	      ArrayList<City> cityList = new ArrayList<City>(totalNodes);
	       for(int i =0; i< totalNodes; i++)
	    	   cityList.add(new City(maxNodes));
	     
	      citySet = new CitySet(cityList, this);
	      for(int i = 0; i < totalNodes; i++) {
	    	  cordModel.setValueAt(citySet.get(i).getX(), i, 1);
	    	  cordModel.setValueAt(citySet.get(i).getY(), i, 2);
	      }
	      
	
	      cordModel.addTableModelListener(new TableModelListener() {
	    	  public void tableChanged(TableModelEvent e) {
	    		  citySet = new CitySet(cordModel.getAllCity(), GuiPanel.this);
	    		  sPlot.repaint();
		  }}); 
	      add(leftPane, BorderLayout.WEST);
	      add(new Panel(pathPaint, sPlot), BorderLayout.CENTER);
	      
	   
	}

	public NodePlotPanel getScatterPlot() {
		return sPlot;
	}
	public FinalPathPaint getGlass() {
		return pathPaint;
	}
	public CityCoordTable getModel() {
		return cordModel;
	}
	public IOPanel getIOPanel() {
		return leftPane;
	}
	
	public CitySet getNodeSet() {
		
		return citySet;
	}
	public void setNodeSet(CitySet nSet) {
		citySet = nSet;   
	}
	public void clearTable() {
		
		cordModel.setValueAt((Double)0.0, 0, 1);
		cordModel.setValueAt((Double)0.0, 0, 2);
		
		for(int i = 1; i<totalNodes; i++) {
			cordModel.setValueAt(null, i, 1);
			cordModel.setValueAt(null, i, 2);
		}
	}
	
	public class NodePlotPanel extends JPanel {
		
		private Graphics2D g2;
		private double min, max;
		
		public void paintComponent(Graphics g) {  
			
			super.paintComponent(g);
			g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(0));
			
			min =  0;
			max = maxNodes + 0.5;    
			
			int count = cordModel.getRowCount();
			for (int i = 0; i < count; i++) {
				Double x = (Double)cordModel.getValueAt(i, 1);
				Double y = (Double)cordModel.getValueAt(i, 2);
				if (x != null && y != null) {
					if (x < min)
						min = x - min/10;
					else if (x > max)
						max = x + max/10;
					if (y < min)
						min = y - min/10;
					else if (y > max)
						max = y + max/10;
				}
			}
			
		    g2.translate(getWidth()/2,getHeight()/2);
			g2.scale(getWidth()/(max-min), -getHeight()/(max-min));
			g2.translate(-(max+min)/2, -(max+min)/2);

			
			double pixelWidth = (max-min)/getWidth(); 
			double pixelHeight = (max-min)/getHeight(); 

			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke((float)max/500));
	        g2.draw( new Line2D.Double(min,0,max,0));
	        g2.draw( new Line2D.Double(0,min,0,max));
	        
	        
	        if (max - min < 500) {
	        	int tick = (int)min;
	        	while (tick <= max) {
	        		g2.draw(new Line2D.Double(tick,0,tick,3*pixelHeight));
	        		g2.draw(new Line2D.Double(0,tick,3*pixelWidth,tick));
	        		tick+=2;
	        	}
	        }
			
			Double x,y;
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke((float)max/250));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
			for (int i = 0; i < citySet.size(); i++) {

				x = (Double)citySet.get(i).getX();
				y = (Double)citySet.get(i).getY();
				x=x*5;
				y=y*5;
				g2.draw(new Ellipse2D.Double(x-1*pixelWidth,y-1*pixelHeight,2*pixelWidth,2*pixelHeight));	
			}
			
		}
		
		public double getMax() {
			return max;
		}
		public double getMin() {
			return min;
		}
	}

	public class FinalPathPaint extends JComponent {
		
		private Graphics2D g2;
		
		public void paintComponent(Graphics g) {  
			
			super.paintComponent(g);
			g2 = (Graphics2D)g;
			
			double max = sPlot.getMax();
			double min = sPlot.getMin();
			double x,y ,xprev,yprev;
			
			g2.translate(getWidth()/2,getHeight()/2);
			g2.scale(getWidth()/(max-min), -getHeight()/(max-min));
			g2.translate(-(max+min)/2, -(max+min)/2);
			g2.setStroke(new BasicStroke((float) (max/250)));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
			ArrayList<City> path = citySet.getFinalPath();
			
			g2.setColor(Color.blue);
			for(int i = 1; i<path.size(); i++)
			{
				xprev=5*path.get(i-1).getX();
				yprev=5*path.get(i-1).getY();
				x=5*path.get(i).getX();
				y=5*path.get(i).getY();
				g2.draw(new Line2D.Double(xprev, yprev, x, y));
		
			}
		}
	}
}

	