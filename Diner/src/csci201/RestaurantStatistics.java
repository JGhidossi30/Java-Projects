package csci201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class RestaurantStatistics 
{
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/DinerOrders?user=root&password=Gihadi8g&useSSL=false");
			st = conn.createStatement();
			ResultSet times = st.executeQuery("SELECT startTime, endTime FROM Times");
			times.next();
			String startTime = times.getString("startTime");
			String endTime = times.getString("endTime");
			System.out.println("Execution started at " + startTime);
			String statement = "SELECT id, duration FROM Orders";
			ResultSet rs = st.executeQuery(statement);
			while (rs.next())
			{ 
				System.out.println("Order " + rs.getInt("id") + " took 00:00:0" + rs.getDouble("duration") + " to complete.");
			}
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
			System.out.printf("Execution took 00:00:%.2f to complete.\n", difference);
			System.out.println("Execution ended at " + endTime);
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