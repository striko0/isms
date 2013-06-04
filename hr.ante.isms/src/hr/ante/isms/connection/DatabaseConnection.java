package hr.ante.isms.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DatabaseConnection {
	public Connection connection = null;
//	String driverName = "oracle.jdbc.driver.OracleDriver"; // for Oracle
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	// String driverName = “com.mysql.jdbc.Driver”; //for MySql
//	String serverName = "ginger.umd.edu"; // Use this server.
//	String portNumber = "1521";
//	String sid = "dbclass1";
//	String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":"
//			+ sid; // for Oracle

	String url = "jdbc:sqlserver://192.168.0.76"/*192.168.0.70*/;
	// uri =”jdbc:mysql://server ip or address:port/database name”; //for Mysql
	String username = "sa"; // You should modify this.
	String password = "sa"; // You should modify this.

	public DatabaseConnection() {
	}

	public boolean doConnection() {
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : " + e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	public void printAssets(String asset) throws SQLException{
		PreparedStatement stmt = null;
		String query = "SELECT * FROM ASISMS.dbo.view_asset WHERE assettype_id='"+asset+"'";
		stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
		                String assetType = rs.getString(2); // or rs.getString("NAME");
		                String name= rs.getString(4);
		                String category = rs.getString(5);
		                String owner = rs.getString(6);
		                String desc = rs.getString(7);
		                int confident = rs.getInt(9);
		                int integrity = rs.getInt(10);
		                int accessibility = rs.getInt(11);
		                int bimpact = rs.getInt(12);
		                int assetValue = rs.getInt(13);

			System.out.println(" Assetype : " + assetType);
			System.out.println(" Name : " + name);
			System.out.println(" Category : " + category);
			System.out.println(" Owner : " + owner);
			System.out.println(" Confidentiality : " + confident);
			System.out.println(" Integrity : " + integrity);
			System.out.println(" Accessibility : " + accessibility);
			System.out.println(" Business Impact : " + bimpact);
			System.out.println(" ASSET VALUE : " + assetValue);
		}
		rs.close();
		stmt.close();
		}

	public String[] getComboItems(String tableName) throws SQLException{
		PreparedStatement stmt = null;

		String query = "SELECT * FROM ASISMS.dbo."+tableName+"";

		stmt = connection.prepareStatement(query);
		stmt.setMaxRows(5);
		ResultSet rs = stmt.executeQuery();
		Vector<String> items= new Vector<String>();
		while (rs.next()) {
			            int comboID = rs.getInt(1); // or rs.getString("NAME");
		                String name= rs.getString(2);
		               // String desc = rs.getString(3);
		                items.add(comboID+"-"+name);



		}
		rs.close();
		stmt.close();
		String[] itemsString= new String[items.size()];
		int count=0;
	      for(String x : items ) {
	    	  itemsString[count++]=x;
	         }

		return itemsString;

		}


	public String[] getComboItemsWithWhere(String tableName, String whereStatement) throws SQLException{
		PreparedStatement stmt = null;

		String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

		stmt = connection.prepareStatement(query);
		stmt.setMaxRows(5);
		ResultSet rs = stmt.executeQuery();
		Vector<String> items= new Vector<String>();
		while (rs.next()) {
			            int comboID = rs.getInt(1); // or rs.getString("NAME");
		                String name= rs.getString(2);
		               // String desc = rs.getString(3);
		                items.add(comboID+"-"+name);



		}
		rs.close();
		stmt.close();
		String[] itemsString= new String[items.size()];
		int count=0;
	      for(String x : items ) {
	    	  itemsString[count++]=x;
	         }

		return itemsString;

		}

	public String[] getComboItemsThreatName(String tableName, String whereStatement) throws SQLException{
		PreparedStatement stmt = null;

		String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

		stmt = connection.prepareStatement(query);
		stmt.setMaxRows(5);
		ResultSet rs = stmt.executeQuery();
		Vector<String> items= new Vector<String>();
		while (rs.next()) {
			            int comboID = rs.getInt(1); // or rs.getString("NAME");
		                String name= rs.getString(3);
		               // String desc = rs.getString(3);
		                items.add(comboID+"-"+name);



		}
		rs.close();
		stmt.close();
		String[] itemsString= new String[items.size()];
		int count=0;
	      for(String x : items ) {
	    	  itemsString[count++]=x;
	         }

		return itemsString;

		}

	public String[] getComboItemsThreatOrVulnerability(String tableName, String whereStatement) throws SQLException{
		PreparedStatement stmt = null;

		String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

		stmt = connection.prepareStatement(query);
		stmt.setMaxRows(5);
		ResultSet rs = stmt.executeQuery();
		Vector<String> items= new Vector<String>();
		while (rs.next()) {
			            int comboID = rs.getInt(1); // or rs.getString("NAME");
		                String name= rs.getString(3);
		               // String desc = rs.getString(3);
		                items.add(comboID+"-"+name);



		}
		rs.close();
		stmt.close();
		String[] itemsString= new String[items.size()];
		int count=0;
	      for(String x : items ) {
	    	  itemsString[count++]=x;
	         }

		return itemsString;

		}






	public String getTextContent(String tableName, String columnName, String argument, String value) throws SQLException{
		PreparedStatement stmt = null;

		String query = "SELECT "+columnName+" FROM ASISMS.dbo."+tableName+" WHERE "+argument+"="+value+"";
		stmt = connection.prepareStatement(query);
		String content = new String();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			            content= rs.getString(1);


		}
		rs.close();
		stmt.close();
		return content;

		}


}