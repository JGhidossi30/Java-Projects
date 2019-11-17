package csci201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Util 
{
	public static String startTime, endTime;
	public static double[] durations = new double[Diner.food.getCustomers().size()];
	public static String printMessage(String message) 
	{
		Calendar cal = Calendar.getInstance();
		String datetime = "" + cal.get(Calendar.HOUR_OF_DAY);
		datetime += ":" + cal.get(Calendar.MINUTE);
		datetime += ":" + cal.get(Calendar.SECOND);
		datetime += "." + cal.get(Calendar.MILLISECOND);
		System.out.println(datetime + " " + message);
		if (startTime == null)
			startTime = datetime;
		endTime = datetime;
		return datetime;
	}
	public static void outputDatabase()
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/DinerOrders?user=root&password=Gihadi8g&useSSL=false");
			st = conn.createStatement();
			for (int i = 0; i < durations.length; i++)
			{
				String statement = "INSERT INTO Orders (duration) ";
				statement += "VALUES (" + Double.toString(durations[i]) + ")";
				st.executeUpdate(statement);
			}
			String statement = "INSERT INTO Times (startTime, endTime) ";
			statement += "VALUES (\"" + startTime + "\", \"" + endTime + "\")";
			st.executeUpdate(statement);
		} 
		catch (SQLException e) 
		{
			System.out.println ("SQLException: " + e.getMessage());
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println ("ClassNotFoundException: " + e.getMessage());
		} 
		finally 
		{
			try 
			{
				if (rs != null) 
				{
					rs.close();
				}
				if (st != null) 
				{
					st.close();
				}
				if (ps != null) 
				{
					ps.close();
				}
				if (conn != null) 
				{
					conn.close();
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("SQLException: " + e.getMessage());
			}
		}
	}
}