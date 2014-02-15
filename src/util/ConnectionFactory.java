package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException{
	
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/npj","postgres","041055@");
		
		return con;
	}		
}


























