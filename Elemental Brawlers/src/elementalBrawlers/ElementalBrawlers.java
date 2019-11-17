package elementalBrawlers;

import java.util.ArrayList;

public class ElementalBrawlers 
{
	private ArrayList<Brawlers> Brawlers;
	public ElementalBrawlers()
	{
		Brawlers = new ArrayList<Brawlers>();
	}
	public ArrayList<Brawlers> getBrawlers()
	{
		return this.Brawlers;
	}
	public void setBrawlers(ArrayList<Brawlers> brawlers)
	{
		this.Brawlers = brawlers;
	}
}