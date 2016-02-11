
import java.util.Comparator;


public class DistComp implements Comparator<SpanTree> {
	@Override
	public int compare(SpanTree n1, SpanTree n2){
		if(n1.getCost() < n2.getCost())
			return -1;
		else if(n1.getCost() > n2.getCost())
			return 1;
		else
			return 1;
	}
}
