import java.io.*;

public class PolicyExtraction
{
	private final static double gamma = 0.9;
	private final static double epsilon = 0.1;
	private static double[][] reward, utility;
	private static char[][] policy;
	public static void main(String[] args) throws Exception
	{
		readFile();
		valueIteration();
		policyExtraction();
		writeFile();
	}
	public static void readFile() throws Exception
	{
		File file = new File("input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int gridSize = Integer.parseInt(br.readLine());
		reward = new double[gridSize][gridSize];
		utility = new double[gridSize][gridSize];
		policy = new char[gridSize][gridSize];
		int numObstacles = Integer.parseInt(br.readLine());
		for (int i = 0; i < numObstacles; i++)
		{
			String location = br.readLine();
			String[] point = location.split(",");
			reward[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = -101;
			utility[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = -101;
			policy[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = 'o';
		}
		String destination = br.readLine();
		br.close();
		String[] point = destination.split(",");
		reward[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = 99;
		utility[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = 99;
		policy[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = '.';
		for (int i = 0; i < reward.length; i++)
			for (int j = 0; j < reward[i].length; j++)
				if (reward[i][j] == 0)
					reward[i][j] = -1;
	}
	public static void valueIteration()
	{
		double delta;
		double[][] utilityPrime = new double[utility.length][utility[0].length];
		copyUtility(utilityPrime, utility);
		do {
			copyUtility(utility, utilityPrime);
			delta = 0;
			for (int i = 0; i < utility.length; i++)
			{
				for (int j = 0; j < utility[i].length; j++)
				{
					if (reward[i][j] != 99)
					{
						utilityPrime[i][j] = reward[i][j] + (gamma * maxAction(i, j));
						if (Math.abs(utilityPrime[i][j] - utility[i][j]) > delta)
							delta = Math.abs(utilityPrime[i][j] - utility[i][j]);
					}
				}
			}
		} while (delta >= epsilon * (1 - gamma) / gamma);
		for (int i = 0; i < utility.length; i++)
			for (int j = 0; j < utility[i].length; j++)
				if (reward[i][j] == -101)
					utility[i][j] = -101;
	}
	public static void policyExtraction()
	{
		for (int i = 0; i < policy.length; i++)
		{
			for (int j = 0; j < policy[i].length; j++)
			{
				if (policy[i][j] != '.' && policy[i][j] != 'o')
				{
					policy[i][j] = maxPolicy(i, j);
				}
			}
		}
	}
	public static void writeFile()
	{
		String pol = "";
		for (int i = 0; i < policy.length; i++)
		{
			for (int j = 0; j < policy[i].length; j++)
			{
				pol += policy[j][i];
			}
			if (i + 1 != policy.length)
				pol += '\n';
		}
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
			writer.write(pol);
		} 
		catch (IOException ex) {} 
		finally 
		{
			try {writer.close();} catch (Exception ex) {}
		}
	}
	public static double maxAction(int i, int j)
	{
		double max = -Double.MAX_VALUE;
		double north, south, east, west;
		try {north = utility[i][j - 1];}
		catch (ArrayIndexOutOfBoundsException e) {north = utility[i][j];}
		try {south = utility[i][j + 1];}
		catch (ArrayIndexOutOfBoundsException e) {south = utility[i][j];}
		try {east = utility[i + 1][j];}
		catch (ArrayIndexOutOfBoundsException e) {east = utility[i][j];}
		try {west = utility[i - 1][j];}
		catch (ArrayIndexOutOfBoundsException e) {west = utility[i][j];}
		for (int dir = 0; dir < 4; dir++)
		{
			double[] prob = {0.1, 0.1, 0.1, 0.1};
			prob[dir] = 0.7;
			double iteration = (prob[0] * north) + (prob[1] * south) + (prob[2] * east) + (prob[3] * west);
			if (iteration > max)
				max = iteration;
		}
		return max;
	}
	public static char maxPolicy(int i, int j)
	{
		char direction = '^';
		double max = -Double.MAX_VALUE;
		double north, south, east, west;
		try {north = utility[i][j - 1];}
		catch (ArrayIndexOutOfBoundsException e) {north = utility[i][j];}
		try {south = utility[i][j + 1];}
		catch (ArrayIndexOutOfBoundsException e) {south = utility[i][j];}
		try {east = utility[i + 1][j];}
		catch (ArrayIndexOutOfBoundsException e) {east = utility[i][j];}
		try {west = utility[i - 1][j];}
		catch (ArrayIndexOutOfBoundsException e) {west = utility[i][j];}
		for (int dir = 0; dir < 4; dir++)
		{
			double[] prob = {0.1, 0.1, 0.1, 0.1};
			prob[dir] = 0.7;
			double iteration = (prob[0] * north) + (prob[1] * south) + (prob[2] * east) + (prob[3] * west);
			if (iteration > max)
			{
				max = iteration;
				switch (dir)
				{
				case 0:
					direction = '^';
					break;
				case 1:
					direction = 'v';
					break;
				case 2:
					direction = '>';
					break;
				case 3:
					direction = '<';
					break;
				}
			}
		}
		return direction;
	}
	public static void copyUtility(double[][] utility, double[][] utilityPrime)
	{
		for (int i = 0; i < utility.length; i++)
		{
			for (int j = 0; j < utility[i].length; j++)
			{
				utility[i][j] = utilityPrime[i][j];
			}
		}
	}
}
