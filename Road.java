/**
 * This class is a data element class for roads of a graph.
 * @author Jiheon Kim
 */
public class Road implements Comparable<Road> {
	private Town endpointA, endpointB;
	private int distance;
	private String name;
	
	/**
	 * A constructor.
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.endpointA = source;
		this.endpointB = destination;
		this.distance = weight;
		this.name = name;
	}
	
	/**
	 * A constructor with weight preset at 1.
	 * @param source
	 * @param destination
	 * @param name
	 */
	public Road(Town source, Town destination, String name) {
		this.endpointA = source;
		this.endpointB = destination;
		this.name = name;
		this.distance = 1;
	}
	
	/**
	 * A getter for source.
	 * @return endpointA
	 */
	public Town getSource() {
		return endpointA;
	}
	
	/**
	 * A getter for destination.
	 * @return endpointB
	 */
	public Town getDestination() {
		return endpointB;
	}
	
	/**
	 * A getter for road name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for road distance.
	 * @return distance
	 */
	public int getWeight() {
		return distance;
	}
	
	/**
	 * A method that returns true if the edge contains the given town.
	 * @param town
	 * @return true if the edge contains the given town, false otherwise.
	 */
	public boolean contains(Town town) {
		if(endpointA.equals(town) || endpointB.equals(town)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method that returns true if each of the ends of the road is the same as the ends of this road.
	 * @return true if each of the ends of the road is the same as the ends of this road, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		Road r = (Road) obj;
		if(this == r) {
			return true;
		}
		else if(endpointA.equals(r.getSource()) || endpointA.equals(r.getDestination()) && endpointB.equals(r.getDestination()) || endpointB.equals(r.getSource())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method to compare roads.
	 * @return 0 if same, 1 otherwise.
	 */
	public int compareTo(Road o) {
		return name.compareTo(o.getName());
	}
	
	/**
	 * A hashing method.
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * A method to display the name of the road.
	 * @return name
	 */
	@Override
	public String toString() {
		return name;
	}
}
