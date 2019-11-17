package csci201;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddAnOrder extends Thread
{
	private static Register register = new Register();
	private ArrayList<String> order;
	private int numOrder;
	public AddAnOrder(ArrayList<String> order, int numOrder)
	{
		this.order = order;
		this.numOrder = numOrder;
	}
	public void run()
	{
		register.order(order, numOrder);
	}
}

class Register
{
	private Lock lock = new ReentrantLock();
	public void order(ArrayList<String> order, int numOrder)
	{
		lock.lock();
		String startTime = "";
		try
		{
			Thread.sleep(1500);
			startTime = startingOrder(numOrder);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException: " + e.getMessage());
		}
		finally
		{
			lock.unlock();
		}
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i = 0; i < order.size(); i++)
		{
			CookFood cf = new CookFood(order.get(i));
			executors.execute(cf);
		}
		executors.shutdown();
		while (!executors.isTerminated())
		{
			Thread.yield();
		}
		completedOrder(numOrder, startTime);
	}
	public String startingOrder(int numOrder)
	{
		return Util.printMessage("Starting order " + numOrder + "!");
	}
	public void completedOrder(int numOrder, String startTime)
	{
		String endTime = Util.printMessage("Completed order " + numOrder + "!");
		double start1 = Double.parseDouble(startTime.substring(0, 2));
		double start2 = Double.parseDouble(startTime.substring(startTime.indexOf(":") + 1, startTime.indexOf(":") + 3));
		double start3 = Double.parseDouble(startTime.substring(startTime.lastIndexOf(":") + 1));
		
		double end1 = Double.parseDouble(endTime.substring(0, 2));
		double end2 = Double.parseDouble(endTime.substring(endTime.indexOf(":") + 1, endTime.indexOf(":") + 3));
		double end3 = Double.parseDouble(endTime.substring(endTime.lastIndexOf(":") + 1));
		
		double start = start1 * 60000;
		start += start2 * 1000;
		start += start3;
		double end = end1 * 60000;
		end += end2 * 1000;
		end += end3;
		
		double difference = end - start;
		Util.durations[numOrder - 1] = difference;
	}
}