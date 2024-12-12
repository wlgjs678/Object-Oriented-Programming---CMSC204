import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	private Town[] town;
	private GraphInterface<Town, Road> graph;
	
	@Before
	public void setUp() throws Exception {
		town = new Town[8];
		graph = new Graph();
		
		for(int i = 1; i < 8; i++) {
			town[i] = new Town("Town_" + i);
			graph.addVertex(town[i]);
		}
		
		graph.addEdge(town[1], town[2], 6, "Road_1");
		graph.addEdge(town[1], town[3], 8, "Road_2");
		graph.addEdge(town[2], town[4], 2, "Road_3");
		graph.addEdge(town[3], town[5], 1, "Road_4");
		graph.addEdge(town[3], town[4], 5, "Road_5");
		graph.addEdge(town[4], town[6], 6, "Road_6");
		graph.addEdge(town[5], town[6], 7, "Road_7");
		graph.addEdge(town[5], town[7], 4, "Road_8");
		graph.addEdge(town[6], town[7], 5, "Road_9");
	}
	
	@After
	public void tearDown() throws Exception {
		graph = null;
	}
	
	@Test
	public void testGedEdge() {
		assertEquals(new Road(town[2], town[4],2, "Road_3"), graph.getEdge(town[2], town[4]));
		assertEquals(new Road(town[5], town[6],7, "Road_7"), graph.getEdge(town[5], town[6]));
		assertEquals(new Road(town[4], town[6],6, "Road_6"), graph.getEdge(town[4], town[6]));
	}
	
	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[1], town[4]));
		graph.addEdge(town[1], town[4], 3, "Road_10");
		assertEquals(true, graph.containsEdge(town[1], town[4]));
	}
	
	@Test
	public void testAddVertex() {
		Town t = new Town("Town_8");
		assertEquals(false, graph.containsVertex(t));
		graph.addVertex(t);
		assertEquals(true, graph.containsVertex(t));
	}
	
	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[5], town[6]));
		assertEquals(true, graph.containsEdge(town[1], town[3]));
		assertEquals(false, graph.containsEdge(town[3], town[7]));
	}
	
	@Test
	public void testEdgeSet() {
		Set<Road> road = graph.edgeSet();
		ArrayList<String> roadList = new ArrayList<String>();
		for(Road r : road) {
			roadList.add(r.getName());
		}
		Collections.sort(roadList);
		assertEquals("Road_1", roadList.get(0));
		assertEquals("Road_3", roadList.get(2));
		assertEquals("Road_6", roadList.get(5));
	}
	
	@Test
	public void testEdgesOf() {
		Set<Road> road = graph.edgeSet();
		ArrayList<String> roadList = new ArrayList<String>();
		for(Road r : road) {
			roadList.add(r.getName());
		}
		Collections.sort(roadList);
		assertEquals("Road_4", roadList.get(3));
		assertEquals("Road_6", roadList.get(5));
		assertEquals("Road_8", roadList.get(7));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[4], town[6]));
		graph.removeEdge(town[4], town[6], 6, "Road_6");
		assertEquals(false, graph.containsEdge(town[4], town[6]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[6]));
		graph.removeVertex(town[6]);
		assertEquals(false, graph.containsVertex(town[6]));
	}
	
	@Test
	public void testVertexSet() {
		Set<Town> road = graph.vertexSet();
		assertEquals(true, road.contains(town[3]));
		assertEquals(true, road.contains(town[5]));
	}
	
	@Test
	public void testTown_1ToTown_7() {
		String source = "Town_1", destination = "Town_7";
		Town start = null, end = null;
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {
			Town t = iterator.next();
			if(t.getName().equals(source)) {
				start = t;
			}
			else if(t.getName().equals(destination)) {
				end = t;
			}
		}
		if(start != null && end != null) {
			ArrayList<String> path = graph.shortestPath(start, end);
			assertNotNull(path);
			assertTrue(path.size() > 0);
			assertEquals("Town_1 via Road_2 to Town_3 8 mi", path.get(0));
			assertEquals("Town_3 via Road_4 to Town_5 1 mi", path.get(1));
			assertEquals("Town_5 via Road_8 to Town_7 4 mi", path.get(2));
		}
		else {
			fail("Failed.");
		}
	}
}
