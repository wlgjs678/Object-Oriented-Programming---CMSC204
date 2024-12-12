import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
/**
 * This class is a data manager class that will hold an object of Graph class
 * @author Jiheon Kim
 */
public class TownGraphManager implements TownGraphManagerInterface {
	Graph g;
	
	/**
	 * A constructor
	 */
	TownGraphManager() {
		g = new Graph();
	}
	
	/**
	 * A method that adds a road with two towns and a road name.
	 * @param source
	 * @param destination
	 * @param weight
	 * @param name
	 * @return true if successful, false otherwise.
	 */
	public boolean addRoad(String source, String destination, int weight, String name) {
		Town endpointA = new Town(source);
		Town endpointB = new Town(destination);
		if(g.addEdge(endpointA, endpointB, weight, name) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method that returns the name of the road that both towns are connected through.
	 * @param source
	 * @param destination
	 * @return name of road if the two towns are in the same road, null otherwise.
	 */
	public String getRoad(String source, String destination) {
		Town endpointA = new Town(source);
		Town endpointB = new Town(destination);
		for(Road r : g.getRoadMapping().get(endpointA)) {
			if((r.getSource().equals(endpointA) || r.getSource().equals(endpointB)) &&
					(r.getDestination().equals(endpointB) || r.getDestination().equals(endpointA))) {
				return r.toString();
				}
			}
		return null;
	}
	
	/**
	 * A method to add a town to the graph.
	 * @param name
	 * @return true if successful, false otherwise.
	 */
	public boolean addTown(String name) {
		Town t = new Town(name);
		if(g.addVertex(t)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method to get a town with a given name.
	 * @param name
	 * @return town
	 */
	public Town getTown(String name) {
		Town t = new Town(name);
		if(t.getName().equals(name)) {
			return t;
		}
		else {
			return null;
		}
	}
	
	/**
	 * A method that determines if a town is already in the graph.
	 * @param name
	 * @return true if found, false otherwise.
	 */
	public boolean containsTown(String name) {
		Town t = new Town(name);
		if(g.containsVertex(t)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method to determine if a road is in the graph.
	 * @param source
	 * @param destination
	 * @return true if found, false otherwise.
	 */
	public boolean containsRoadConnection(String source, String destination) {
		Town endpointA = new Town(source);
		Town endpointB = new Town(destination);
		HashSet<Road> roads;
		if(g.getRoadMapping().get(endpointA).size() < g.getRoadMapping().get(endpointB).size()) {
			roads = g.getRoadMapping().get(endpointA);
		}
		else {
			roads = g.getRoadMapping().get(endpointB);
		}
		for(Road r : roads) {
			if(r.contains(endpointA) && r.contains(endpointB)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A method that creates an ArrayList of all road titles in sorted order by road name.
	 * @return roads
	 */
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<String>();
		for(Road r : g.edgeSet()) {
			roads.add(r.getName());
		}
		Collections.sort(roads);
		return roads;
	}
	
	/**
	 * A method that deletes a road from the graph.
	 * @param source
	 * @param destination
	 * @param name
	 * @return true if successful, false otherwise.
	 */
	public boolean deleteRoadConnection(String source, String destination, String name) {
		Town endpointA = new Town(source);
		Town endpointB = new Town(destination);
		if(g.removeEdge(endpointA, endpointB, 0, name) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method to delete a town from the graph.
	 * @param name
	 * @return true if successful, false otherwise.
	 */
	public boolean deleteTown(String name) {
		Town t = new Town(name);
		if(g.removeVertex(t)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method that creates an ArrayList of all towns in alphabetical order.
	 * @return towns
	 */
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<String>();
		for(Town t : g.vertexSet()) {
			towns.add(t.getName());
		}
		Collections.sort(towns);
		return towns;
	}
	
	/**
	 * A method that returns the shortest path from source to destination.
	 * @param source
	 * @param destination
	 * @return the shortest path if successful, null otherwise.
	 */
	public ArrayList<String> getPath(String source, String destination) {
		Town endpointA = new Town(source);
		Town endpointB = new Town(destination);
		ArrayList<String> path = new ArrayList<String>();
		if(g.shortestPath(endpointA, endpointB) != null) {
			path = g.shortestPath(endpointA, endpointB);
			return path;
		}
		else {
			return null;
		}
	}
	
	/**
	 * A method that reads a file and populates the graph.
	 * @param input
	 * @throws IOException
	 */
	public void populateTownGraph(File input) throws IOException {
		try {
			String str = "";
			String[] arr = {};
			String source, destination;
			BufferedReader reader = new BufferedReader(new FileReader(input));
			while((str = reader.readLine()) != null) {
				arr = str.split(";");
				source = arr[1]; destination = arr[2];
				addTown(source); 
				addTown(destination);
				addRoad(source, destination, Integer.parseInt(arr[0].split(",")[1]), arr[0].split(",")[0]);
			}
			reader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
