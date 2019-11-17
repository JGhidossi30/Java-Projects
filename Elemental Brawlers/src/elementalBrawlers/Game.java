package elementalBrawlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;

public class Game 
{
	private static ElementalBrawlers brawlers;
	int[] choices = new int[3];
	public Game(String filename, int players)
	{
		while (!loadFile(filename))
		{
			System.out.println("Please input valid file name.");
		}
		while (!chooseBrawlers());
		System.out.println("\nExcellent!");
		if (players == 1)
		{
			System.out.println("You send  " + brawlers.getBrawlers().get(choices[0] - 1).getName() + "!");
			System.out.println("Your opponent plays " + brawlers.getBrawlers().get((int)(Math.random() * 3 + 1)).getName());
			System.out.println("\nChoose a move:");
		}
		else if (players == 2)
		{

		}
	}
	public boolean chooseBrawlers()
	{
		try
		{
			System.out.println("\nChoose 3 brawlers:");
			showBrawlers();
			Scanner scan = new Scanner(System.in);
			String in = scan.nextLine();
			choices[0] = in.charAt(0) - '0';
			choices[1] = in.charAt(2) - '0';
			choices[2] = in.charAt(4) - '0';
			for (int i = 0; i < 3; i++)
			{
				if (choices[i] > brawlers.getBrawlers().size() || choices[i] <= 0)
				{
					return false;
				}
			}
			return true;
		}
		catch (NullPointerException e) 
		{
			System.out.println("\nInvalid!");
			return false;
		}
	}
	public String showBrawlers()
	{
		String s = "";
		for (int i = 0; i < brawlers.getBrawlers().size(); i++)
			System.out.println(i + 1 + ") " + brawlers.getBrawlers().get(i).getName());
		return s;
	}
	public static boolean loadFile(String filename)
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
			brawlers = gson.fromJson(br, ElementalBrawlers.class);
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
	public static void main(String[] args)
	{
		new Game("test.json", 1);
	}
}