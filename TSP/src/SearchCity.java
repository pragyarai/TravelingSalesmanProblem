
public class SearchCity {
	@Override
	public String toString() {
		return "searchNode [cityId=" + cityId + ", cost=" + cost + "]";
	}

	int cityId;
	Double cost;
	
	public SearchCity(int cId, Double c){
		this.cityId = cId;
		this.cost = c;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
}
