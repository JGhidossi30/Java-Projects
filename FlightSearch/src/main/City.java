import java.util.ArrayList;

/**
 * <h1>City</h1>
 * Object designed to store data and connecting
 * flights to a single destination.
 * 
 * @author  Jordan Ghidossi
 * @version 1.0
 */
public class City
{
	private String name;
	private ArrayList<Flight> destinations;
	
	/**
	 * Constructor to make a city.
	 *
	 * @param name Name of the city.
	 */
	public City(String name)
	{
		destinations = new ArrayList<Flight>();
		this.name = name;
	}
	
	/**
	 * Adds a flight to the inherent city.
	 *
	 * @param city City of the destination.
	 * @param cost Cost of the flight.
	 */
	public void addDestination(City city, int cost)
	{
		destinations.add(new Flight(city, cost));
	}
	
	/**
	 * Getter for the String name.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter for the destination list.
	 */
	public ArrayList<Flight> getDestinations()
	{
		return this.destinations;
	}
}