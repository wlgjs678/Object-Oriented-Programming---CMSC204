import java.util.*;
/**
 * This class is a data structure of town and road elements.
 * @author Jiheon Kim
 */
public class Graph implements GraphInterface<Town, Road> {
	private Set<Town> towns;
	private Set<Road> roads;
	private HashMap<Town, LinkedHashSet<Town>> townMapping;
	private HashMap<Town, LinkedHashSet<Road>> roadMapping;

	/**
	 * A constructor.
	 */
	public Graph() {
		this.townMapping = new HashMap<Town, LinkedHashSet<Town>>();
		this.roadMapping = new HashMap<Town, LinkedHashSet<Road>>();
		this.towns = new HashSet<Town>();
		this.roads = new HashSet<Road>();
	}

	/**
	 * A method that returns an edge connecting source vertex to target vertex if such vertices and edge exist in this graph.
	 * @param source
	 * @param destination
	 * @return true if such vertices and edge exist in this graph, false otherwise.
	 */
	public Road getEdge(Town source, Town destination) {
		for(Road r : roads) {
			if((r.getSource().equals(source) || r.getSource().equals(destination)) &&
				(r.getDestination().equals(destination) || r.getDestination().equals(source))) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * A method that creates a new edge in this graph.
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 * @return r - new edge
	 */
	public Road addEdge(Town source, Town destination, int weight, String name) throws IllegalArgumentException, NullPointerException {
		Road r = new Road(source, destination, weight, name);
		if(!containsVertex(source)) {
			throw new IllegalArgumentException();
		}
		else if(source == null || destination == null) {
			throw new NullPointerException();
		}
		roads.add(r);
		roadMapping.get(source).add(r);
		roadMapping.get(destination).add(r);
		townMapping.get(source).add(destination);
		townMapping.get(destination).add(source);
		return r;
	}
	
	/**
	 * A method that removes an edge going from source to destination if such vertices and edge exist in this graph.
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 * @return the removed edge, null if unsuccessful.
	 */
	public Road removeEdge(Town source, Town destination, int weight, String name) {
		Road r = new Road(source, destination, weight, name);
			if(r.getWeight() >= 0 && r.getName() != null) {
				roads.remove(r);
				roadMapping.get(source).remove(r);
				roadMapping.get(destination).remove(r);
				townMapping.get(source).remove(destination);
				townMapping.get(destination).remove(source);
				return r;
			}
		return null;
	}
	
	/**
	 * A method that returns true if this graph contains an edge going from the source to destination.
	 * @param source
	 * @param destination
	 * @return true if this graph contains an edge going from the source to destination, false otherwise.
	 */
	public boolean containsEdge(Town source, Town destination) {
		if(getEdge(source, destination) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method that returns a set of the vertices present in this graph.
	 * @return a set of the vertices
	 */
	public Set<Town> vertexSet(){
		return townMapping.keySet();
	}
	
	/**
	 * A method that adds the specified vertex to this graph if not already present.
	 * @param town
	 * @return true if successfully added, false otherwise.
	 */
	public boolean addVertex(Town town) throws NullPointerException {
		LinkedHashSet<Road> road = new LinkedHashSet<Road>();
		if(town == null) {
			throw new NullPointerException();
		}
		if(!townMapping.containsKey(town)) {
			townMapping.put(town, town.getAdjacentTowns());
			roadMapping.put(town, road);
			return true;
		}
		return false;
	}
	
	/**
	 * A method that returns true if this graph contains the specified vertex.
	 * @param town
	 * @return true if this graph contians the specified vertex, false otherwise.
	 */
	public boolean containsVertex(Town town) {
		if(townMapping.containsKey(town)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method that returns a set of edges present in this graph.
	 * @return roads
	 */
	public Set<Road> edgeSet() {
		return roads;
	}
	
	/**
	 * A method that returns a set of all edges touching the specified vertex.
	 * @param town
	 * @return a set of all edges touching the specified vertex.
	 */
	public Set<Road> edgesOf(Town town) throws IllegalArgumentException, NullPointerException {
		if(!roadMapping.containsKey(town)) {
			throw new IllegalArgumentException();
		}
		else if(town == null) {
			throw new NullPointerException();
		}
		return roadMapping.get(town);
	}
	
	/**
	 * A method that removes the specified vertex from this graph including all its touching edges if present.
	 * @param town
	 * @return true if succssful, false otherwise.
	 */
	public boolean removeVertex(Town town) {
		if(townMapping.containsKey(town)) {
			 for(Town t : towns) {
				 roadMapping.get(t).removeAll(roadMapping.get(town));
			 }
			 townMapping.remove(town);
			 roadMapping.remove(town);
			 return true;
		}
		return false;
	}
	
	public HashMap<Town, LinkedHashSet<Road>> getRoadMapping() {
		return roadMapping;
	}
	
	private ArrayList<Town> endpoints;
	private Town[] visitedEndpoints;
	private int[] roadWeights;
	/**
	 * A method to find the shortest path from source to destination; it uses the Dijkstra's algorithm.
	 * @param source
	 * @param destination
	 * @return an ArrayList of Strings that describe the path from source to destination.
	 */
	public ArrayList<String> shortestPath(Town source, Town destination) {
		dijkstraShortestPath(source);
		Town t = destination;
		ArrayList<Integer> distance = new ArrayList<Integer>();
		ArrayList<String> town = new ArrayList<String>();
		ArrayList<Town> temp = new ArrayList<Town>();
		ArrayList<String> road = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		if(visitedEndpoints[endpoints.indexOf(t)] != null) {
			while(!t.equals(source)) {
				town.add(t.toString());
				temp.add(t);
				t = visitedEndpoints[endpoints.indexOf(t)];
			}
		}
		town.add(source.toString());
		temp.add(source);
		Collections.reverse(town);
		Collections.reverse(temp);
		for(int i = 0, p = 1; i < town.size(); i++, p++) {
			if(p == town.size()) {
				break;
			}
			road.add((getEdge(temp.get(i), temp.get(p)).toString()));
			distance.add((getEdge(temp.get(i), temp.get(p)).getWeight()));
		}
		for(int i = 0; i < town.size()-1; i++) {
			String data = "";
			data += town.get(i) + " via ";
			data += road.get(i) + " to ";
			data += town.get(i+1) + " ";
			data += distance.get(i) + " mi";
			result.add(data);
		}
		return result;
	}
	
	/**
	 * A method to be used by the shortestPath method to find the shortest path.
	 * @param source
	 */
	public void dijkstraShortestPath(Town source) {
		int size = vertexSet().size();
		endpoints = new ArrayList<Town>();
		roadWeights = new int[size];
		visitedEndpoints = new Town[size];
		for(int i = 0; i < roadWeights.length; i++) {
			roadWeights[i] = Integer.MAX_VALUE;
		}
		ArrayList<Town> settled = new ArrayList<Town>();
		ArrayList<Town> unsettled = new ArrayList<Town>();
		for(Town t : vertexSet()) {
			endpoints.add(t);
			unsettled.add(t);
		}
		roadWeights[endpoints.indexOf(source)] = 0;
		Town startingPoint;
		while(!unsettled.isEmpty()) {
			int shortest = endpoints.indexOf(unsettled.get(0));
			for(int i = 0; i < size; i++) {
				if(!settled.contains(endpoints.get(i)) && (roadWeights[i] < roadWeights[shortest])) {
					shortest = i;
				}
			}
			startingPoint = endpoints.get(shortest);
			calculateMinimumDistance(unsettled, startingPoint, source);
			settled.add(startingPoint);
			unsettled.remove(startingPoint);
		}
	}
	
	/**
	 * A method to be called by the dijkstra's algorithm method; it calculates the minimum distance (weight).
	 */
	private void calculateMinimumDistance(ArrayList<Town> unsettled, Town evaluationTown, Town source) {
		Town current;
		for(Town t : townMapping.get(evaluationTown)) {
			if(unsettled.contains(t)) {
				int i = endpoints.indexOf(t);
				Town previous = visitedEndpoints[i];
				if(endpoints.get(i) != source) {
					visitedEndpoints[i] = evaluationTown;
				}
				int totalDistance = 0;
				current = t;
				while(!current.equals(source)) {
					totalDistance += getEdge(visitedEndpoints[endpoints.indexOf(current)], current).getWeight();
					current = visitedEndpoints[endpoints.indexOf(current)];
				}
				if(totalDistance < roadWeights[i]) {
					roadWeights[i] = totalDistance;
					visitedEndpoints[i] = evaluationTown;
				}
				else {
					visitedEndpoints[i] = previous;
				}
			}
		}
	}
}
