package elementalBrawlers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server
{
	private Vector<ServerThread> serverThreads;
	ServerSocket ss;
	private static Scanner scan;
	public Server(String filename) throws IOException
	{
		while (!connectPort());
		serverThreads = new Vector<ServerThread>();
		while (true)
		{
			Socket s = ss.accept();
			ServerThread st = new ServerThread(s, this);
			serverThreads.add(st);
//			FileInputStream fis;
//			ObjectInputStream ois;
//			try
//			{
//				fis = new FileInputStream("threads.tmp");
//				ois = new ObjectInputStream(fis);
//				names = (ArrayList<String>)ois.readObject();
//				ois.close();
//			}
//			catch (ClassNotFoundException e) {}
//			FileOutputStream fos = new FileOutputStream("threads.tmp");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			for (int i = 0; i < names.size(); i++)
//				names.add(serverThreads.get(i).getName());
//			oos.writeObject(names);
//			oos.close();
		}
	}
	public boolean connectPort()
	{
		try
		{
			System.out.println("Please enter a valid port:");
			int port = Integer.parseInt(scan.nextLine());
			ss = new ServerSocket(port);
			System.out.println("\nSuccess!");
			return true;
		}
		catch (IOException e)
		{
			System.out.println("\nInvalid Port");
			return false;
		}
	}
	public void removeServerThread(ServerThread st)
	{
		serverThreads.remove(st);
	}
	public void broadcast(String message, ServerThread st)
	{
		System.out.println(message);
		for (ServerThread servert : serverThreads)
		{
			if (servert != st)
			{
				servert.sendMessage(message);
			}
		}
	}
	public static void main(String[] args) throws IOException
	{
		scan = new Scanner(System.in);
		System.out.println("Please enter a valid file:");
		String filename = scan.nextLine();
		FileOutputStream fos = new FileOutputStream("filename.tmp");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(filename);
		oos.close();
		System.out.println();
		new Server(filename);
	}
}