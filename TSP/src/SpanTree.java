

public class SpanTree {
	
	@Override
	public String toString() {
		return "SpanTree [fromCity=" + fromCity + ", toCity=" + toCity + ", cost=" + cost + "]";
	}
	private int fromCity;
	private int toCity;
	private Double cost;
	
	public SpanTree(int from, int to, Double cost){
		this.fromCity = from;
		this.toCity = to;
		this.cost = cost;
	}
	
	public Double getCost(){
		return this.cost;
	}
	public int getFromNode(){
		return this.fromCity;
	}
	public int getToNode(){
		return this.toCity;
	}
}
