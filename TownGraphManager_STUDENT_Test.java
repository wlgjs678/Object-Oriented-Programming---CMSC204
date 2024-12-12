import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	private String[] town;
	private TownGraphManagerInterface graph;
	
	@Before
	public void setUp() throws Exception {
		town = new String[8];
		graph = new TownGraphManager();
		
		for(int i = 1; i < 8; i++) {
			town[i] = "Town_" + i;
			graph.addTown(town[i]);
		}
		
		graph.addRoad(town[1], town[2], 6, "Road_1");
		graph.addRoad(town[1], town[3], 8, "Road_2");
		graph.addRoad(town[2], town[4], 2, "Road_3");
		graph.addRoad(town[3], town[5], 1, "Road_4");
		graph.addRoad(town[3], town[4], 5, "Road_5");
		graph.addRoad(town[4], town[6], 6, "Road_6");
		graph.addRoad(town[5], town[6], 7, "Road_7");
		graph.addRoad(town[5], town[7], 4, "Road_8");
		graph.addRoad(town[6], town[7], 5, "Road_9");
	}
	
	@After
	public void tearDown() throws Exception {
		graph = null;
	}
	
	@Test
	public void testAddRoad() {
		ArrayList<String> road = graph.allRoads();
		assertEquals("Road_1", road.get(0));
		assertEquals("Road_2", road.get(1));
		assertEquals("Road_3", road.get(2));
		graph.addRoad(town[2], town[6], 8, "Road_10");
		road = graph.allRoads();
		assertEquals("Road_1", road.get(0));
		assertEquals("Road_10", road.get(1));
		assertEquals("Road_2", road.get(2));
	}
	
	@Test
	public void testGetRoad() {
		assertEquals("Road_7", graph.getRoad(town[5], town[6]));
		assertEquals("Road_4", graph.getRoad(town[3], town[5]));
		assertEquals("Road_9", graph.getRoad(town[6], town[7]));
	}
	
	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		assertEquals(true, graph.containsTown("Town_7"));
		assertEquals(false, graph.containsTown("Town_9"));
	}
	
	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[3]));
		assertEquals(true, graph.containsRoadConnection(town[4], town[6]));
		assertEquals(false, graph.containsRoadConnection(town[4], town[7]));
	}
	
	@Test
	public void testAllRoads() {
		ArrayList<String> road = graph.allRoads();
		assertEquals("Road_1", road.get(0));
		assertEquals("Road_2", road.get(1));
		assertEquals("Road_3", road.get(2));
	}
	
	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[3]));
		graph.deleteRoadConnection(town[1], town[3], "Road_2");
		assertEquals(false, graph.containsRoadConnection(town[1], town[3]));
	}
	
	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		graph.deleteTown(town[5]);
		assertEquals(false, graph.containsTown("Town_5"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> road = graph.allTowns();
		assertEquals("Town_3", road.get(2));
		assertEquals("Town_4", road.get(3));
		assertEquals("Town_5", road.get(4));
	}
	
	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1], town[4]);
		assertNotNull(path);
		assertTrue(path.size() > 0);
		System.out.println(path);
		assertEquals("Town_1 via Road_1 to Town_2 6 mi", path.get(0).trim());
		assertEquals("Town_2 via Road_3 to Town_4 2 mi", path.get(1).trim());
		}	

	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_8"));
		graph.addTown("Town_8");
		ArrayList<String> path = graph.getPath(town[1], "Town_8");
		assertFalse(path.size() > 0);
	}
}
