import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFlightMap
{
	@Test
	void test()
	{
		FlightMap tester = new FlightMap("input.txt");
		assertEquals(tester.search(), "Destination         	Flight Route from P 	Total Cost          
W                   	P,W                 	$200                
S                   	P,W,S               	$450                
T                   	P,W,S,T             	$750                
Y                   	P,W,Y               	$700                
Z                   	P,W,Y,Z             	$1150               
R                   	P,R                 	$300                
X                   	P,R,X               	$500                ");
	}
}