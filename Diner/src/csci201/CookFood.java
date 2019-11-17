package csci201;

import java.util.concurrent.Semaphore;

public class CookFood extends Thread
{
	private static DeepFryer deepFryer = new DeepFryer();
	private static Grill grill = new Grill();
	private static MilkshakeMaker milkshakeMaker = new MilkshakeMaker();
	private static DrinkMachine drinkMachine = new DrinkMachine();
	private String appliance;
	public CookFood(String food)
	{
		for (int i = 0; i < Diner.food.getMenu().getDeepFryer().size(); i++)
		{
			if (food.equals(Diner.food.getMenu().getDeepFryer().get(i)))
			{
				appliance = "deepFryer";
				return;
			}
		}
		for (int i = 0; i < Diner.food.getMenu().getGrill().size(); i++)
		{
			if (food.equals(Diner.food.getMenu().getGrill().get(i)))
			{
				appliance = "grill";
				return;
			}
		}
		for (int i = 0; i < Diner.food.getMenu().getMilkshakeMaker().size(); i++)
		{
			if (food.equals(Diner.food.getMenu().getMilkshakeMaker().get(i)))
			{
				appliance = "milkshakeMaker";
				return;
			}
		}
		for (int i = 0; i < Diner.food.getMenu().getDrinkMachine().size(); i++)
		{
			if (food.equals(Diner.food.getMenu().getDrinkMachine().get(i)))
			{
				appliance = "drinkMachine";
				return;
			}
		}
		System.out.println(food);
	}
	public void run()
	{
		switch (appliance)
		{
		case "deepFryer":
			deepFryer.cook();
			break;
		case "grill":
			grill.cook();
			break;
		case "milkshakeMaker":
			milkshakeMaker.cook();
			break;
		case "drinkMachine":
			drinkMachine.cook();
			break;
		}
	}
}

class DeepFryer
{
	private Semaphore semaphore = new Semaphore(4);
	public void cook()
	{
		try
		{
			semaphore.acquire();
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException: " + e.getMessage());
		}
		finally
		{
			semaphore.release();
		}
	}
}
class Grill
{
	private Semaphore semaphore = new Semaphore(5);
	public void cook()
	{
		try
		{
			semaphore.acquire();
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException: " + e.getMessage());
		}
		finally
		{
			semaphore.release();
		}
	}
}
class MilkshakeMaker
{
	private Semaphore semaphore = new Semaphore(2);
	public void cook()
	{
		try
		{
			semaphore.acquire();
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException: " + e.getMessage());
		}
		finally
		{
			semaphore.release();
		}
	}
}
class DrinkMachine
{
	private Semaphore semaphore = new Semaphore(2);
	public void cook()
	{
		try
		{
			semaphore.acquire();
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			System.out.println("InterruptedException: " + e.getMessage());
		}
		finally
		{
			semaphore.release();
		}
	}
}