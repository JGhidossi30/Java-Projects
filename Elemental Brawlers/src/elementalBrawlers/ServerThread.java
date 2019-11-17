package elementalBrawlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread
{
	private Socket s;
	private Server server;
	private BufferedReader br;
	private PrintWriter pw;
	public ServerThread(Socket s, Server server)
	{
		this.s = s;
		this.server = server;
		try
		{
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.pw = new PrintWriter(s.getOutputStream());
			this.start();
		}
		catch (IOException e)
		{
			System.out.println("IOException in ServerThread constructor: " + e.getMessage());
		}
	}
	public void sendMessage(String message)
	{
		pw.println(message);
		pw.flush();
	}
	public void run()
	{
		try
		{
			while (true)
			{
				String line = br.readLine();
				server.broadcast(line, this);
			}
		}
		catch (IOException e)
		{
			System.out.println("IOException in ServerThread.run(): " + e.getMessage());
		}
		finally
		{
			try
			{
				server.removeServerThread(this);
				br.close();
				pw.close();
				s.close();
			}
			catch (IOException e)
			{
				
			}
		}
	}
}