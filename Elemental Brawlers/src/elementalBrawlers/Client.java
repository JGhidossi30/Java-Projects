package elementalBrawlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread
{
	private Socket s;
	private Scanner scan;
	private PrintWriter pw;
	private BufferedReader br;
	private Game game;
	public Client() throws IOException
	{
		scan = new Scanner(System.in);
		while (!connect());
		while (!makeAChoice());
		pw = new PrintWriter(s.getOutputStream());
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.start();
		while (true)
		{
			String line = scan.nextLine();
			pw.println("Ghidossi: " + line);
			pw.flush();
		}
	}
	public void run()
	{
		try
		{
			String line = br.readLine();
			System.out.println(line);
		}
		catch (IOException e)
		{
			System.out.println("IOException in Client.run();:" + e.getMessage());
		}
	}
	public boolean connect()
	{
		try
		{
			System.out.println("Please enter an IP address:");
			String hostname = scan.nextLine();
			System.out.println("\nPlease enter a port:");
			int port = Integer.parseInt(scan.nextLine());
			s = new Socket(hostname, port);
			return true;
		}
		catch (IOException e)
		{
			System.out.println("\nUnable to Connect!");
			return false;
		}
	}
	public boolean makeAChoice()
	{
		System.out.println("\nPlease make a choice:\n1) Start Game\n2) Join Game");
		int choice = Integer.parseInt(scan.nextLine());
		switch (choice)
		{
		case 1:
			while (!startGame());
			break;
		case 2:
			while (!joinGame());
			break;
		default:
			System.out.println("");
			return false;
		}
		return true;
	}
	@SuppressWarnings("resource")
	public boolean startGame()
	{
		System.out.println("\nWhat will you name your game?");
		String game = scan.nextLine();
		FileInputStream fis;
		ObjectInputStream ois;
		try 
		{
			fis = new FileInputStream("threads.tmp");
			ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			ArrayList<String> games = (ArrayList<String>)ois.readObject();
			for (int i = 0; i < games.size(); i++)
			{
				if (games.get(i).equals(game))
				{
					System.out.println("This game already exists!");
					return false;
				}
			}
			games.add(game);
			System.out.println("\nHow many players?\n1 or 2?");
			int players = Integer.parseInt(scan.nextLine());
			FileInputStream fnis = new FileInputStream("filename.tmp");
			ObjectInputStream fnois = new ObjectInputStream(fnis);
			String filename = (String)fnois.readObject();
			switch (players)
			{
			case 1:
				System.out.println("Starting game...");
				this.game = new Game(filename, 1);
				break;
			case 2:
				System.out.println("Waiting for players to connect...");
				this.game = new Game(filename, 2);
				break;
			default:
				return false;
			}
			FileOutputStream fos = new FileOutputStream("threads.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(games);
			oos.close();
		} 
		catch (ClassNotFoundException e) {} 
		catch (IOException e) {}
		return true;
	}
	public boolean joinGame()
	{
		return true;
	}
	public static void main(String[] args) throws IOException
	{	
		new Client();
	}
}