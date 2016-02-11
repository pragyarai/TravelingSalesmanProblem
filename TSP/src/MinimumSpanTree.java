
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class MinimumSpanTree {
	private ArrayList<SpanTree> tree;
	private Comparator<SpanTree> comparator;
	private PriorityQueue<SpanTree> q;
	
	public MinimumSpanTree(Double [][] cost, int visited[], int numOfCities){
		tree = new ArrayList<SpanTree>();
		comparator = new DistComp();
		q = new PriorityQueue<SpanTree>(numOfCities, comparator);
		for(int i = 0; i < numOfCities; i++){								
			for(int j = i + 1; j < numOfCities; j++){
				if(visited[i] == 0 && visited[j] == 0){
					SpanTree tempEdge = new SpanTree(i, j, cost[i][j]);
					q.add(tempEdge);
				} 
			}
		}
		for(int i = 0, tempNum = numOfCities; i < tempNum; i++){
			if(visited[i] > 0)
				numOfCities--;
		}
		this.calMinSpanTree(cost, numOfCities);
	}
	
	private void calMinSpanTree(Double [][] cost, int numOfCities){
		SpanTree tempEdge;
		int i = 0;
		while((tempEdge = q.poll()) != null  && i < numOfCities-1){
			
			if( !this.isCycle(tempEdge)){
				tree.add(tempEdge);
				i++;
			}
		}
	}
	
	private boolean isCycle(SpanTree newEdge){
		boolean node1Match, node2Match;
		node1Match = node2Match = false;
		Iterator<SpanTree> navi = this.tree.iterator();
		while(navi.hasNext()){
			SpanTree tempEdge = navi.next();
			if(!node1Match && (newEdge.getFromNode() == tempEdge.getFromNode() || newEdge.getFromNode() == tempEdge.getToNode())){
				node1Match = true;
			}
			if(!node2Match && (newEdge.getToNode() == tempEdge.getFromNode() || newEdge.getToNode() == tempEdge.getToNode())){
				node2Match = true;
			}
		}
		if(node1Match && node2Match)
			return true;
		else
			return false;
	}
	
	public int getTotalCost(){
		int totalCost = 0;
		Iterator<SpanTree> navi = this.tree.iterator();
		while(navi.hasNext()){
			SpanTree tempEdge = navi.next();
			totalCost += tempEdge.getCost();
		}
		return totalCost;
	}
}