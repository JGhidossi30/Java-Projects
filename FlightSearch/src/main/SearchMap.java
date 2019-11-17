import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * <h1>SearchMap</h1>
 * The SearchMap program implements an application that takes
 * an input of flights, creates a map of the flights, searches
 * through them for the possible connections through an origin
 * airport, and prints to an output file.
 * 
 * @author  Jordan Ghidossi
 * @version 1.0
 */
public class SearchMap
{
	/**
	 * Main method of the program. Used to start execution, create the map of flights and calls the search() method and prints out to output.txt file.
	 *
	 * @param args Argument paramter used for input and output text files.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		FlightMap flightmap = new FlightMap(args[0]);
		writeFile(flightmap.search());
	}
	/**
	 * Method used to print search results out to output.txt file.
	 *
	 * @param out This is the String that will be included on the output.txt file.
	 */
	public static void writeFile(String out)
	{
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
			writer.write(out);
		} 
		catch (IOException ex) {} 
		finally 
		{
			try {writer.close();} catch (Exception ex) {}
		}
	}
}