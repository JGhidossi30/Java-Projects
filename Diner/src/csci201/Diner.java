package csci201;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class Diner
{
	public static Food food;
	public static void main(String[] args)
	{
		//JSON File I/O
		Scanner scan = new Scanner(System.in);
		String filename;
		do
		{
			System.out.print("Please enter a filename: ");
			filename = scan.nextLine();
		} while (!inputFile(filename));
		scan.close();
		//Thread Start
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i = 0; i < food.getCustomers().size(); i++)
		{
			AddAnOrder aao = new AddAnOrder(food.getCustomers().get(i).getOrder(), i + 1);
			executors.execute(aao);
		}
		executors.shutdown();
		while (!executors.isTerminated())
		{
			Thread.yield();
		}
		System.out.println("All orders complete!");
		//Connect to Database
		Util.outputDatabase();
	}
	public static boolean inputFile(String filename)
	{
		BufferedReader br;
		try 
		{
			BufferedReader test = new BufferedReader(new FileReader(filename));
			if (test.readLine() == null)
			{
				System.out.println("That file is empty.\n");
				test.close();
				return false;
			}
			test.close();
			br = new BufferedReader(new FileReader(filename));
			Gson gson = new Gson();
			food = gson.fromJson(br, Food.class);
			System.out.println();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("That file could not be found.\n");
			return false;
		}
		catch (Exception e)
		{
			System.out.println("That file is not a well-formed JSON file.\n");
			return false;
		}
		return true;
	}
}