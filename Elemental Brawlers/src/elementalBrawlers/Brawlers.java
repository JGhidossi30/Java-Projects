package elementalBrawlers;

import java.util.ArrayList;

public class Brawlers 
{
	private String name, type;
	private Stats stats;
	private ArrayList<Ability> abilities;
	public Brawlers()
	{
		abilities = new ArrayList<Ability>();
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Stats getStats()
	{
		return this.stats;
	}
	public void setStats(Stats stats)
	{
		this.stats = stats;
	}
	public ArrayList<Ability> getAbilities()
	{
		return this.abilities;
	}
	public void setAbilities(ArrayList<Ability> abilities)
	{
		this.abilities = abilities;
	}
}