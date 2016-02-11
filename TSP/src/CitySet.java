
import java.util.*;


public class CitySet {

	private final ArrayList<City> uCity;
	private final int total_number_nodes;
	private final IOPanel lPanel;
	private ArrayList<City> finalPath;
	private  Double finalCost=0.0;
	private ArrayList<City> cityList;
	private City firstNode;
	private GuiPanel nPanel;
	
	
	

	@Override
	public String toString() {
		return "CitySet [uCity=" + uCity + ", total_number_nodes=" + total_number_nodes + ", lPanel=" + lPanel
				+ ", finalPath=" + finalPath + ", cityList=" + cityList + ", firstNode=" + firstNode + ", nPanel="
				+ nPanel + "]";
	}
	public CitySet(ArrayList<City> nodes, GuiPanel spt) {
		
		uCity = nodes;
		total_number_nodes = nodes.size() + 1;
		nPanel = spt;
		lPanel = nPanel.getIOPanel();
		reset();
		
	}
	public void minSpanningTree() {
		
		cityList = new ArrayList<City>(uCity);
		int position;
		Double [][] cost = new Double[uCity.size()][uCity.size()];
		int []path;
		for(int i = 0; i < uCity.size(); i++)
		{
			for(int j = 0; j < uCity.size() ; j++){
					cost[i][j] = distance(cityList.get(i), cityList.get(j));
					
			}
			
		}
		Solver minSolver = new Solver(cost, uCity.size(), 0);
		finalPath = new ArrayList<City>();
		System.out.println("Using A* Search:");
		minSolver.printPath();
		path=minSolver.getPath();
		for(int i = 0; i<path.length; i++)	
		{	
			position=path[i];
			finalPath.add(cityList.get(position));
		}
		finalCost=minSolver.getFinalCost();
		nPanel.getGlass().repaint();
				
	}
	public void hillClimbing() {
		int maxEval=10;
		cityList = new ArrayList<City>(uCity);
		int position;
		Double [][] cost = new Double[uCity.size()][uCity.size()];
		int []path;
		for(int i = 0; i < uCity.size(); i++)
			for(int j = 0; j < uCity.size() ; j++){
					cost[i][j] = distance(cityList.get(i), cityList.get(j));
				
			}
		Solver hillSolver = new Solver(cost, uCity.size(), 0,maxEval);
		finalPath = new ArrayList<City>();
		System.out.println("Using Hill Climbing:");
		hillSolver.printPath();
		path=hillSolver.getPath();
		
		for(int i = 0; i<path.length; i++)	
		{	
			
			position=path[i];
			finalPath.add(cityList.get(position));
			
		}
		finalCost=hillSolver.getFinalCost();
		nPanel.getGlass().repaint();
			
	}
	public static Double distance(City city1, City city2)
	{
		Double dist,ycoord,xcoord;
		ycoord = Math.abs (city1.getY() - city2.getY());
	    xcoord = Math.abs (city1.getX() - city2.getX());    
	    dist = (Double) Math.sqrt((ycoord)*(ycoord) +(xcoord)*(xcoord));
		
		return dist;
	}
	public int size() {
		return uCity.size();
	}
	
	public City get(int i) {
		return uCity.get(i);
	}
	public Double getFinalCost() {
		return finalCost;
	}
	
	public void reset() {
		finalPath = new ArrayList<City>(uCity.size());
		firstNode = uCity.get(0);
		finalPath.add(firstNode);
		finalPath.add(firstNode);
	}	
	
	public ArrayList<City> getFinalPath() {
		return finalPath;
	}	
	


}
