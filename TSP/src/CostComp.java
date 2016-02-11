
import java.util.Comparator;


public class CostComp implements Comparator<SearchCity> {

	@Override
	public int compare(SearchCity sNode1, SearchCity sNode2) {
		if(sNode1.cost > sNode2.cost)
			return 1;
		else
			return -1;
	}

}
