import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1>FlightMap</h1>
 * The FlightMap class is used to create a map of the flights from an origin.
 * This class also countains a search algorithm that is used to parse
 * through the flights from the origin and output a list of connecting flight
 * paths with the lowest cost.
 * 
 * @author  Jordan Ghidossi
 * @version 1.0
 */
public class FlightMap
{
	private City origin;
	private ArrayList<City> cities;
	ArrayList<String> pathNames;
	ArrayList<Integer> pathCosts;

	/**
	 * Constructor for the FlightMap. Takes the input and attaches newly creating airports based on flight availability.
	 *
	 * @param input Name of the input file.
	 * @throws IOException 
	 */
	public FlightMap(String input) throws IOException
	{
		File file = new File(input);
		BufferedReader br = new BufferedReader(new FileReader(file));
		origin = new City(br.readLine());
		cities = new ArrayList<City>();
		cities.add(origin);
		String line;
		while ((line = br.readLine()) != null)
		{
			String[] readLine = line.split(" ");
			boolean newCityA = true, newCityB = true;
			for (int i = 0; i < cities.size(); i++)
			{
				if (cities.get(i).getName().equals(readLine[0]))
					newCityA = false;
				if (cities.get(i).getName().equals(readLine[1]))
					newCityB = false;
			}
			if (newCityA)
				cities.add(new City(readLine[0]));
			if (newCityB)
				cities.add(new City(readLine[1]));
			int indexA = -1, indexB = -1;
			for (int i = 0; i < cities.size(); i++)
			{
				if (cities.get(i).getName().equals(readLine[0]))
					indexA = i;
				else if (cities.get(i).getName().equals(readLine[1]))//Could be a bug
					indexB = i;
			}
			cities.get(indexA).addDestination(cities.get(indexB), Integer.parseInt(readLine[2]));
		}
		br.close();
	}

	/**
	 * Utility method used in to assist the search() method.
	 *
	 * @param current Current city in depth search.
	 * @param path Current String of path taken.
	 * @param cost Current cost of path taken.
	 */
	public void searchUtil(City current, String path, int cost)
	{
		for (int i = 0; i < current.getDestinations().size(); i++)
		{
			boolean visited = path.contains(current.getDestinations().get(i).getDestination().getName());
			if (!visited)
			{
				String pathCopy = path;
				int costCopy = cost;
				pathCopy += "," + current.getDestinations().get(i).getDestination().getName();
				costCopy += current.getDestinations().get(i).getCost();

				pathNames.add(pathCopy);
				pathCosts.add(costCopy);
				searchUtil(current.getDestinations().get(i).getDestination(), pathCopy, costCopy);
			}
		}
	}

	/**
	 * Algorithm that searches through the FlightMap to find the optimal flight plan.
	 */
	public String search() 
	{
		pathNames = new ArrayList<String>();
		pathCosts = new ArrayList<Integer>();
		String out = String.format("%-20s	%-20s	%-20s\n","Destination", "Flight Route from P", "Total Cost");
		for (int i = 0; i < origin.getDestinations().size(); i++)
		{
			String path = origin.getName();
			path += "," + origin.getDestinations().get(i).getDestination().getName();
			int cost = origin.getDestinations().get(i).getCost();
			pathNames.add(path);
			pathCosts.add(cost);
			searchUtil(origin.getDestinations().get(i).getDestination(), path, cost);
		}
		for (int i = 0; i < pathNames.size(); i++)
		{
			for (int j = 0; j < pathNames.size(); j++)
			{
				if (i != j && pathNames.get(i).substring(pathNames.get(i).length() - 1).equals(pathNames.get(j).substring(pathNames.get(j).length() - 1)))
				{
					if (pathCosts.get(i) > pathCosts.get(j))
					{
						pathNames.remove(i);
						pathCosts.remove(i);
					}
					else
					{
						pathNames.remove(j);
						pathCosts.remove(j);
					}
					i--;
					j--;
				}
			}
		}
		for (int i = 0; i < pathNames.size(); i++)
		{
			out += String.format("%-20s	%-20s	%-20s\n", pathNames.get(i).substring(pathNames.get(i).length() - 1), pathNames.get(i), "$" +Integer.toString(pathCosts.get(i)));
		}
		return out;
	}
}