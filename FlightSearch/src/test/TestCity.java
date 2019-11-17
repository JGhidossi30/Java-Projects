import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCity
{
	@Test
	void test()
	{
		City tester = new City("Los Angeles");
		assertEquals(tester.getName(), "Los Angeles");
	}
}
