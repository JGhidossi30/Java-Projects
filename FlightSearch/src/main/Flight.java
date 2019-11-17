/**
 * <h1>Flight</h1>
 * Object designed to represent a flight to a city,
 * including the cost of the flight.
 * 
 * @author  Jordan Ghidossi
 * @version 1.0
 */
public class Flight
{
	private City city;
	private int cost;
	
	/**
	 * Constructor to make a flight.
	 *
	 * @param name Name of the destination city.
	 * @param cost Cost of the flight to the city.
	 */
	public Flight(City city, int cost)
	{
		this.city = city;
		this.cost = cost;
	}
	
	/**
	 * Getter for the destination.
	 */
	public City getDestination()
	{
		return this.city;
	}
	
	/**
	 * Getter for the cost.
	 */
	public int getCost()
	{
		return this.cost;
	}
}