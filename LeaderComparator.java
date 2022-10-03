import java.util.Comparator;

public class LeaderComparator implements Comparator<Leader>{
	@Override
	public int compare(Leader leader1, Leader leader2) {
		 return (int) (leader1.time-leader2.time);
	}
}
