import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFlight
{
	@Test
	void test()
	{
		Flight tester = new Flight(new City("Los Angeles"), 3000);
		assertEquals(tester.getDestination(), "Los Angeles");
		assertEquals(tester.getCost(), 3000)
	}
}
