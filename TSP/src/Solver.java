
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;


public class Solver {
	private PriorityQueue<SearchCity> openList;
	private ArrayList<SearchCity> deleteList;
	private Comparator<SearchCity> comparator;
	private int[] path;
	private int[] finalPath;
	double finalTotalCost;
	
	private int nodeListVisit[];
	public Solver(Double [][] cost, int numOfCities, int startCity){
		this.path = new int[numOfCities];
		this.nodeListVisit = new int[numOfCities];
		for(int i = 0; i < numOfCities; i++){
			this.nodeListVisit[i] = 0;
		}
		
		comparator = new CostComp();
		openList = new PriorityQueue<SearchCity>(numOfCities, comparator);
		
		this.solveAstar(cost, numOfCities, startCity);
	}
	public Solver(Double [][] cost, int numOfCities, int startCity,int maxEval){
		int eval=0;
		maxEval=(numOfCities/2);
		Random r = new Random();
		finalTotalCost = 0.0;
		while(eval<maxEval)
		{
			this.path = new int[numOfCities];
			this.nodeListVisit = new int[numOfCities];
			for(int i = 0; i < numOfCities; i++){
				this.nodeListVisit[i] = 0;
			}
			startCity = r.nextInt(numOfCities-1) + 1;
			comparator = new CostComp();
			this.solveHill(cost, numOfCities, startCity);
			eval++;
		}
	}
	
	private void solveAstar(Double [][] cost, int numOfCities, int startCity){
		int numOfVisited = 1;
		int currentCity = startCity;
		SearchCity searchRemove;
		nodeListVisit[currentCity] = numOfVisited++;
		while(numOfVisited <= numOfCities){
			for(int i = 0; i < numOfCities; i++){
				if(i != currentCity){
					
					if(nodeListVisit[i] == 0){				
						MinimumSpanTree tempSpanTree = new MinimumSpanTree(cost, nodeListVisit, numOfCities);
						int hCost = tempSpanTree.getTotalCost();
						SearchCity tempSearchNode = new SearchCity(i, (hCost + cost[currentCity][i]) );
						openList.add(tempSearchNode);
					}
				}
			}
			currentCity = openList.poll().cityId;
			Iterator it = openList.iterator();
			deleteList= new ArrayList<SearchCity>();
			   while (it.hasNext()){
				   searchRemove= (SearchCity) it.next();
				   if(searchRemove.getCityId()==currentCity)
				   {
					   deleteList.add(searchRemove);
				   }
				  
			   }
			   openList.removeAll(deleteList); 
			nodeListVisit[currentCity] = numOfVisited++;
		}
		this.calPath(cost);
	}
	private void solveHill(Double [][] cost, int numOfCities, int startCity){
		int numOfVisited = 1;
		int currentCity = startCity;
		nodeListVisit[currentCity] = numOfVisited++;
		Double costHill=0.0;
		while(numOfVisited <= numOfCities){
			openList = new PriorityQueue<SearchCity>(numOfCities, comparator);
			for(int i = 0; i < numOfCities; i++){
				if(i != currentCity ){
					if(nodeListVisit[i] == 0){				
						MinimumSpanTree tempSpanTree = new MinimumSpanTree(cost, nodeListVisit, numOfCities);
						int hCost = tempSpanTree.getTotalCost();
						SearchCity tempSearchNode = new SearchCity(i, (hCost+costHill) );
						openList.add(tempSearchNode);
					}
				}
			}
			currentCity = openList.poll().cityId;
			nodeListVisit[currentCity] = numOfVisited++;
		}
		
		this.calPath(cost);
	}
	
	
	private void calPath(Double cost[][]){
		
		
		double tempTotalCost = 0.0;
		int x,y;
		
		for(int i = 1; i <= this.nodeListVisit.length; i++){
			for(int j = 0; j < this.nodeListVisit.length; j++)
			{
				if(nodeListVisit[j] == i){
					this.path[i-1] = j ;
				}

			}
		}	
		for(int i = 0; i < this.nodeListVisit.length-1; i++){
			x=path[i];
			y=path[i+1];
			tempTotalCost = tempTotalCost+ cost[x][y];
			
		}
		if(finalTotalCost==0.0)
		{
			finalTotalCost=tempTotalCost;
			finalPath=path;
		}
		else if(finalTotalCost>tempTotalCost)
		{
			finalTotalCost=tempTotalCost;
			finalPath=path;
		}
		
	}
	public Double getFinalCost() {
		return this.finalTotalCost;
	}
	public int[] getPath()
	{
		return this.finalPath;
	}
	public void printPath(){
		for(int i = 0; i < this.finalPath.length; i++){
			System.out.print(finalPath[i] + " ");
		}
		System.out.println("(" + this.finalTotalCost + ")");
	}
}