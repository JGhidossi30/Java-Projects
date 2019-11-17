package csci201;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Food
{
	@SerializedName("Menu") private Menu menu;
	@SerializedName("Customers") private ArrayList<Order> customers;
	public Menu getMenu()
	{
		return this.menu;
	}
	public ArrayList<Order> getCustomers()
	{
		return this.customers;
	}
}
class Menu
{
	@SerializedName("deep fryer") private ArrayList<String> deepFryer;
	@SerializedName("grill") private ArrayList<String> grill;
	@SerializedName("milkshake maker") private ArrayList<String> milkshakeMaker;
	@SerializedName("drink machine") private ArrayList<String> drinkMachine;
	public ArrayList<String> getDeepFryer()
	{
		return this.deepFryer;
	}
	public ArrayList <String> getGrill()
	{
		return this.grill;
	}
	public ArrayList<String> getMilkshakeMaker()
	{
		return this.milkshakeMaker;
	}
	public ArrayList<String> getDrinkMachine()
	{
		return this.drinkMachine;
	}
}
class Order
{
	private ArrayList<String> order;
	public ArrayList<String> getOrder()
	{
		return this.order;
	}
}