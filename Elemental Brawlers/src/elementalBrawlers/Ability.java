package elementalBrawlers;

public class Ability 
{
	private String name, type;
	private int damage;
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
	public int getDamage()
	{
		return this.damage;
	}
	public void setDamge(int damage)
	{
		this.damage = damage;
	}
}