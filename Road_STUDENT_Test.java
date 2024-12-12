import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	private Town[] town;
	private Road road_1, road_2, road_3, road_4;
	
	@Before
	public void setUp() throws Exception {
		town = new Town[5];
		for(int i = 1; i < 5; i++) {
			town[i] = new Town("Town_" + i);
		}
		road_1 = new Road(town[1], town[2], 3, "Road_1");
		road_2 = new Road(town[2], town[3], 6, "Road_2");
		road_3 = new Road(town[3], town[4], 4, "Road_3");
		road_4 = new Road(town[2], town[4], 1, "Road_4");
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testContains() {
		assertEquals(true, road_1.contains(town[1]));
		assertEquals(false, road_2.contains(town[4]));
	}
	
	@Test
	public void testEquals() {
		assertEquals(true, town[1].equals(town[1]));
		assertEquals(false, town[4].equals(town[1]));
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, town[2].compareTo(town[2]));
		assertEquals(1, town[3].compareTo(town[2]));
	}
}
