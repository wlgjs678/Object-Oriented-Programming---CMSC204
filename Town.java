import java.util.LinkedHashSet;

/**
 * This class is a data element class for towns of a graph.
 * @author Jiheon Kim
 */
public class Town implements Comparable<Town> {
	private String name;
	private LinkedHashSet<Town> adjacent;
	
	/**
	 * A constructor.
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
		this.adjacent = new LinkedHashSet<Town>();
	}
	
	/**
	 * A copy constructor.
	 * @param templateTown
	 */
	public Town(Town templateTown) {
		this.name = templateTown.getName();
		this.adjacent = templateTown.getAdjacentTowns();
	}
	
	/**
	 * A getter for town names.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for a set of adjacent towns.
	 * @return adjacentTownSet
	 */
	public LinkedHashSet<Town> getAdjacentTowns() {
		return adjacent;
	}
	
	/**
	 * A method to determine whether two towns are the same.
	 * @return true if same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		Town t = (Town) obj;
		if(this.compareTo(t) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A hashing method.
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * A method to compare towns.
	 */
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}
	
	/**
	 * A method to display town names.
	 * @return name
	 */
	public String toString() {
		return name;
	}
}
