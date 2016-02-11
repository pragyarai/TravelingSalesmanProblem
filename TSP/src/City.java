import java.util.Random;

public class City {

	Double x,y;
	Double max,min;
	Random random = new Random();
	
	public City(int limit) {
		max=1.0;
		min=0.0;
		this.x=random.nextDouble();
		this.y=random.nextDouble();
		
	}
	public City() {
		
	}
	public City(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public  void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "City [x=" + x + ", y=" + y + "]";
	}
	
}
