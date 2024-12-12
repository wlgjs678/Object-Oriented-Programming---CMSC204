import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
	private Town[] town;
	
	@Before
	public void setUp() throws Exception {
		town = new Town[5];
		for(int i = 1; i < 5; i++) {
			town[i] = new Town("Town_" + i);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		
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
