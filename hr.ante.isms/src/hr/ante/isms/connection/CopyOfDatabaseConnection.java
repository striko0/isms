package hr.ante.isms.connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import org.mihalis.opal.notify.Notifier;

import com.ibm.icu.util.Calendar;

public class CopyOfDatabaseConnection {
    public Connection connection = null;
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://192.168.0.76"/*192.168.0.70*/;
    String username = "sa"; // You should modify this.
    String password = "sa"; // You should modify this.

    public CopyOfDatabaseConnection() {
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
        //stmt.setMaxRows(5);
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
    public String getCiaDescription(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";


        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
        ResultSet rs = stmt.executeQuery();
        String item= new String();
        while (rs.next()) {
                       // int comboID = rs.getInt(1); // or rs.getString("NAME");
                       item= rs.getString(3);
                       // String desc = rs.getString(3);




        }
        rs.close();
        stmt.close();
        return item;

        }

    public String getAssetDescription(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";


        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
        ResultSet rs = stmt.executeQuery();
        String item= new String();
        while (rs.next()) {
                       // int comboID = rs.getInt(1); // or rs.getString("NAME");
                       item= rs.getString(7);
                       // String desc = rs.getString(3);




        }
        rs.close();
        stmt.close();
        return item;

        }


    public String[] getComboItemsWithWhere(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
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

    public String getTextWithWhere(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
        ResultSet rs = stmt.executeQuery();
        String text= new String();

        while (rs.next()) {
                        int comboID = rs.getInt(1); // or rs.getString("NAME");
                        String name= rs.getString(2);
                       // String desc = rs.getString(3);
                        text= comboID+"-"+name;



        }
        rs.close();
        stmt.close();
//		String[] itemsString= new String[items.size()];
//		int count=0;
//	      for(String x : items ) {
//	    	  itemsString[count++]=x;
//	         }

        return text;

        }


    public String getContentFromDesiredColumn (String tableName, String columnName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT "+columnName+" FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
        ResultSet rs = stmt.executeQuery();
        String text= new String();

        while (rs.next()) {
//			            int comboID = rs.getInt(1); // or rs.getString("NAME");
                        String name= rs.getString(1);
                       // String desc = rs.getString(3);
                        text= name;



        }
        rs.close();
        stmt.close();
//		String[] itemsString= new String[items.size()];
//		int count=0;
//	      for(String x : items ) {
//	    	  itemsString[count++]=x;
//	         }

        return text;

        }





    public HashMap<String, String> getContentForTable(String tableName, String tableModel, String assetid) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+"";
        if(assetid!=null && assetid.length()>0 && !tableName.equals("view_control_risk"))
            query += " WHERE asset_id='"+assetid+"'";

        if(assetid!=null && assetid.length()>0 && tableName.equals("view_control_risk"))
            query += " WHERE risk_id='"+assetid+"'";




        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
        ResultSet rs = stmt.executeQuery();
        boolean found=false;
        ResultSetMetaData rsmd = rs.getMetaData();
        HashMap<String, String> content = new HashMap<String, String>();
//		Vector<HashMap<String, String>> items = new Vector<HashMap<String, String>>();
        int rowCount=1;

        if (tableName == "view_asset" && tableModel=="ListAssetASKTableModel") {
            rowCount=1;
            content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(2 + "/" + 0, "Naziv");
            content.put(3 + "/" + 0, "Podkategorija");
            content.put(4 + "/" + 0, "Vlasnik");
            content.put(5 + "/" + 0, "Povjerljivost");
            content.put(6 + "/" + 0, "Cjelovitost");
            content.put(7 + "/" + 0, "Raspoloživost");
            content.put(8 + "/" + 0, "Poslovni Utjecaj");
            content.put(9 + "/" + 0, "Vrijednost");
            content.put("@@brojac", rowCount+"");
            while (rs.next()) {

                content.put(1 + "/" + rowCount,rs.getInt(1)+"");
//				content.put(1 + "/" + rowCount, rs.getInt(3)+"");
                content.put(2 + "/" + rowCount,rs.getString(4));
                content.put(3 + "/" + rowCount,rs.getString(5));
                content.put(4 + "/" + rowCount,rs.getString(6));
                //Povjerljivost
                content.put(5 + "/" + rowCount,rs.getInt(9)+"");

//                int povjerljivost = Integer.parseInt(rs.getString(9));
//                switch(povjerljivost){
//                case 1:
//                    content.put(5 + "/" + rowCount,"1-Vrlo niska (javno)");
//                    break;
//                case 2:
//                    content.put(5 + "/" + rowCount,"2-Niska (ograniceno)");
//                    break;
//                case 3:
//                    content.put(5 + "/" + rowCount,"3-Srednja (povjerljivo)");
//                    break;
//                case 4:
//                    content.put(5 + "/" + rowCount,"4-Visoka (tajno)");
//                    break;
//                case 5:
//                    content.put(5 + "/" + rowCount,"5-Vrlo visoka (vrlo tajno)");
//                    break;
//                    default:
//                        content.put(5 + "/" + rowCount,"");
//                }

                //Cjelovitost
                content.put(6 + "/" + rowCount,rs.getInt(10)+"");
//                int cjelovitost = Integer.parseInt(rs.getString(10));
//                switch(cjelovitost){
//                case 1:
//                    content.put(6 + "/" + rowCount,"1-Vrlo niska");
//                    break;
//                case 2:
//                    content.put(6 + "/" + rowCount,"2-Niska");
//                    break;
//                case 3:
//                    content.put(6 + "/" + rowCount,"3-Srednja");
//                    break;
//                case 4:
//                    content.put(6 + "/" + rowCount,"4-Visoka");
//                    break;
//                case 5:
//                    content.put(6 + "/" + rowCount,"5-Vrlo visoka");
//                    break;
//                    default:
//                        content.put(6 + "/" + rowCount,"");
//                }

                //Raspolozivost
                content.put(7 + "/" + rowCount,rs.getInt(11)+"");
//                int raspolozivost = Integer.parseInt(rs.getString(11));
//                switch(raspolozivost){
//                case 1:
//                    content.put(7 + "/" + rowCount,"1-Nije važna (do 72 sata)");
//                    break;
//                case 2:
//                    content.put(7 + "/" + rowCount,"2-Nije vrlo važna (do 48 sati)");
//                    break;
//                case 3:
//                    content.put(7 + "/" + rowCount,"3-Važna (do 8 sati)");
//                    break;
//                case 4:
//                    content.put(7 + "/" + rowCount,"4-Vrlo važna ( do 1 sat)");
//                    break;
//                case 5:
//                    content.put(7 + "/" + rowCount,"5-Ekstremno važna (kritièna bez odgode)");
//                    break;
//                    default:
//                        content.put(7 + "/" + rowCount,"");
//                }

                //Raspolozivost
                content.put(8 + "/" + rowCount,rs.getInt(12)+"");
//                int putjecaj = Integer.parseInt(rs.getString(12));
//                switch(putjecaj){
//                case 1:
//                    content.put(8 + "/" + rowCount,"1-Nije važna");
//                    break;
//                case 2:
//                    content.put(8 + "/" + rowCount,"2-Nije vrlo važna");
//                    break;
//                case 3:
//                    content.put(8 + "/" + rowCount,"3-Važna");
//                    break;
//                case 4:
//                    content.put(8 + "/" + rowCount,"4-Kritièna");
//                    break;
//                case 5:
//                    content.put(8 + "/" + rowCount,"5-Vrlo Kritièna");
//                    break;
//                    default:
//                        content.put(8 + "/" + rowCount,"");
//                }

                //Asset_Value
                content.put(9 + "/" + rowCount,rs.getString(13));
                //Asset Id
                content.put(10 + "/" + rowCount, rs.getInt(1)+"");
                content.put("@@brojac", rowCount+"");

                rowCount++;


            }
        }


        if (tableName == "view_risk" && tableModel=="ListRiskASKTableModel") {
            rowCount=1;
            content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(2 + "/" + 0, "Naziv Rizika");
            content.put(3 + "/" + 0, "Naziv Imovine");
            content.put(4 + "/" + 0, "Vlasnik Imovine");
            content.put(5 + "/" + 0, "Prijetnja");
            content.put(6 + "/" + 0, "Ranjivost");
            content.put(7 + "/" + 0, "RIZIK");
            content.put("@@brojac", rowCount+"");
            while (rs.next()) {

            	if ((rs.getString(5) != null && rs.getString(5).length() > 0 )
							&& (rs.getString(6) != null && rs.getString(6).length() > 0)
							&& (rs.getInt(14)+"" != null && rs.getInt(14)+"".length() > 0)
							&& (rs.getInt(16)+"" != null && rs.getInt(16)+"".length() > 0)
							)
            	{

                content.put(1 + "/" + rowCount,rs.getString(2));
                content.put(2 + "/" + rowCount,rs.getString(7));
                content.put(3 + "/" + rowCount,rs.getString(28));
                content.put(4 + "/" + rowCount,rs.getString(8));
                content.put(5 + "/" + rowCount,rs.getString(26));
                content.put(6 + "/" + rowCount,rs.getString(27));
                content.put(7 + "/" + rowCount,rs.getString(1));
                //Risk_probability
                content.put(8 + "/" + rowCount,rs.getInt(14)+"");
                //Detection_Probability
                if(rs.getInt(9)!=0)
                	content.put(9 + "/" + rowCount,rs.getInt(9)+"");
                //Risk_probability_description
                content.put(10 + "/" + rowCount,rs.getString(29));
                //Detection_Probability_Description
                content.put(11 + "/" + rowCount,rs.getString(10));
                //Impact
                if(rs.getInt(16)!=0)
                	content.put(12 + "/" + rowCount,rs.getInt(16)+"");
                //Impact_Description
                content.put(13 + "/" + rowCount,rs.getString(30));
                //Exposure_Factor
                content.put(14 + "/" + rowCount,rs.getDouble(11)+"");
                //Financial_impact
                content.put(15 + "/" + rowCount,rs.getDouble(13)+"");
                //Exposure_Factor_Description
                content.put(16 + "/" + rowCount,rs.getString(12));
                //mitigation_id
                if(rs.getInt(31)!=0)
                	content.put(17 + "/" + rowCount,rs.getInt(31)+"");



                content.put("@@brojac", rowCount+"");

                rowCount++;
            	}


            }
        }

        if(tableName == "view_vulnerability" && tableModel== "ListVulnerabilityASKTableModel"){
        	 rowCount=1;
             //content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
             content.put(1 + "/" + 0, "Naziv");
             content.put(2 + "/" + 0, "Vrsta Ranjivosti");
             content.put(3 + "/" + 0, "Posljedica");
             content.put("@@brojac", rowCount+"");
             while (rs.next()) {

             	if ((rs.getString(3) != null && rs.getString(3).length() > 0 ))	{


                 content.put(1 + "/" + rowCount,rs.getString(3));
                 content.put(2 + "/" + rowCount,rs.getString(8));
                 content.put(3 + "/" + rowCount,rs.getString(9));
                 //ID
                 content.put(4 + "/" + rowCount,rs.getInt(1)+"");
                 //Description
                 content.put(5 + "/" + rowCount,rs.getString(4));
                 //Example
                 content.put(7 + "/" + rowCount,rs.getString(7));
                 //Active
                 if(rs.getInt(5)!=0)
                	 content.put(8 + "/" + rowCount,rs.getInt(5)+"");
                 content.put("@@brojac", rowCount+"");

                 rowCount++;
             	}


             }
        }

        if(tableName == "view_threat" && tableModel== "ListThreatASKTableModel"){
        	rowCount=1;
            //content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(1 + "/" + 0, "Naziv");
            content.put(2 + "/" + 0, "Izvor");
            content.put(3 + "/" + 0, "Vrsta Prijetnje");
            content.put(4 + "/" + 0, "Vjerojatnost");
            content.put("@@brojac", rowCount+"");
            while (rs.next()) {

            	if ((rs.getString(3) != null && rs.getString(3).length() > 0 ))	{


					content.put(1 + "/" + rowCount, rs.getString(3));
					content.put(2 + "/" + rowCount, rs.getString(14));
					content.put(3 + "/" + rowCount, rs.getString(13));
					content.put(4 + "/" + rowCount, rs.getString(19));
					// ID
					content.put(5 + "/" + rowCount, rs.getInt(1) + "");
					// Intention
					content.put(6 + "/" + rowCount, rs.getString(16));
					// Description
					content.put(7 + "/" + rowCount, rs.getString(7));
					// Motivation
					content.put(8 + "/" + rowCount, rs.getString(8));
					// Origin
					content.put(9 + "/" + rowCount, rs.getString(15));
					// Frequency
					content.put(10 + "/" + rowCount, rs.getString(18));
					// Impact
					content.put(11 + "/" + rowCount, rs.getString(17));
					// Source
					content.put(12 + "/" + rowCount, rs.getString(14));
					// //Active
					// if(rs.getInt(5)!=0)
					// content.put(8 + "/" + rowCount,rs.getInt(5)+"");
					content.put("@@brojac", rowCount + "");

					rowCount++;
				}

            }
       }

        if(tableName == "view_control" && tableModel== "ListControlASKTableModel"){

        	rowCount=1;
            //content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(1 + "/" + 0, "ID");
            content.put(2 + "/" + 0, "Vrsta");
            content.put(3 + "/" + 0, "Tip");
            content.put(4 + "/" + 0, "Naziv");
            content.put(3 + "/" + 0, "Oznaka");
            content.put(3 + "/" + 0, "Status");
            content.put(3 + "/" + 0, "Odgovorna osoba");
            content.put(3 + "/" + 0, "Cijena");
            content.put("@@brojac", rowCount+"");
            while (rs.next()) {

            	if ((rs.getString(2) != null && rs.getString(2).length() > 0 )
            			&& (rs.getString(18) != null && rs.getString(18).length() > 0 )
            			&& (rs.getString(10) != null && rs.getString(10).length() > 0 )
            			)	{


					content.put(1 + "/" + rowCount, rs.getInt(1)+"");
					content.put(2 + "/" + rowCount, rs.getInt(4)+"");
					content.put(3 + "/" + rowCount, rs.getInt(3)+"");
					content.put(4 + "/" + rowCount, rs.getString(2));
					content.put(5 + "/" + rowCount, rs.getString(10));
					content.put(6 + "/" + rowCount, rs.getInt(8)+"");
					content.put(7 + "/" + rowCount, rs.getInt(11)+"");
					//Cijena
					content.put(8 + "/" + rowCount, rs.getDouble(20)+"");
					// Description
					content.put(9 + "/" + rowCount, rs.getString(6));




//
//
//
//					// Intention
//					content.put(6 + "/" + rowCount, rs.getString(16));

//					// Motivation
//					content.put(8 + "/" + rowCount, rs.getString(8));
//					// Origin
//					content.put(9 + "/" + rowCount, rs.getString(15));
//					// Frequency
//					content.put(10 + "/" + rowCount, rs.getString(18));
//					// Impact
//					content.put(11 + "/" + rowCount, rs.getString(17));
//					// Source
//					content.put(12 + "/" + rowCount, rs.getString(14));
//					// //Active
//					// if(rs.getInt(5)!=0)
//					// content.put(8 + "/" + rowCount,rs.getInt(5)+"");
					content.put("@@brojac", rowCount + "");

					rowCount++;
				}

            }
       }


        if (tableName == "view_control_risk"
				&& tableModel == "ListControlRiskASKTableModel") {

			// content.put(1 + "/" + 0,
			// /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
			content.put(1 + "/" + 0, "Oznaka Kontrole");
			content.put(2 + "/" + 0, "Naziv Kontrole");
			content.put(3 + "/" + 0, "Status Kontrole");
			content.put("@@brojac", rowCount + "");
			while (rs.next()) {

				if ((rs.getString(2) != null && rs.getString(2).length() > 0)) {

					content.put(1 + "/" + rowCount, rs.getString(8));
					content.put(2 + "/" + rowCount, rs.getString(7));
					content.put(3 + "/" + rowCount, rs.getString(10));
					// ID
					content.put(4 + "/" + rowCount, rs.getInt(1) + "");
					// Application
					content.put(5 + "/" + rowCount, rs.getString(6));
					// ControlId
					content.put(6 + "/" + rowCount, rs.getInt(2) + "");
					// VulnerabilityId
					content.put(7 + "/" + rowCount, rs.getInt(4) + "");
					// ThreatId
					content.put(8 + "/" + rowCount, rs.getInt(5) + "");
					content.put("@@brojac", rowCount + "");

					rowCount++;
				}

			}
		}

        if (tableName == "view_risk" && tableModel=="ThreatIdentification") {
            rowCount =1;
            content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(2 + "/" + 0, "Prijetnja");
            content.put("@@brojac", rowCount+"");


            while (rs.next()) {

                content.put(1 + "/" + rowCount,rs.getInt(4)+"");
                content.put(2 + "/" + rowCount, rs.getString(26)+"");
                content.put(3 + "/" + rowCount, rs.getString(2));

                content.put("@@brojac", rowCount+"");

                rowCount++;
            }

        }

        if (tableName == "view_risk" && tableModel=="VulnerabilityIdentification") {
            rowCount =1;

            content.put(1 + "/" + 0, "Prijetnja");
            content.put(2 + "/" + 0, "Ranjivost");
            content.put("@@brojac", rowCount+"");


            while (found = rs.next()) {

				if (found) {

					if (rs.getString(26) != null
							&& rs.getString(26).length() > 0) {

						content.put(1 + "/" + rowCount, rs.getString(26) + "");
						content.put(2 + "/" + rowCount, rs.getString(27));
						content.put(3 + "/" + rowCount, rs.getInt(3) + "");
						content.put(4 + "/" + rowCount, rs.getString(4));
						content.put(5 + "/" + rowCount, rs.getString(2));

						content.put("@@brojac", rowCount + "");

						rowCount++;
					}
				}
            }

        }


        if (tableName == "view_risk" && tableModel=="Probability") {
            rowCount =1;
            content.put(1 + "/" + 0, "Prijetnja");
            content.put(2 + "/" + 0, "Ranjivost");
            content.put(3 + "/" + 0, "Vjerojatnost");
            content.put("@@brojac", rowCount+"");


            while (found = rs.next()) {

				if (found) {

					if ((rs.getString(27) != null && rs.getString(27).length() > 0 )&& (rs.getString(26) != null && rs.getString(26).length() > 0)) {
						content.put(1 + "/" + rowCount, rs.getString(26) + "");
						content.put(2 + "/" + rowCount, rs.getString(27));
						// Risk_probability
						if( rs.getInt(14)!=0)
							content.put(3 + "/" + rowCount, rs.getInt(14)+"");
						// Detection_probability
						if( rs.getInt(9)!=0)
							content.put(4 + "/" + rowCount, rs.getInt(9) + "");

						// Vulnerability_Id
						content.put(5 + "/" + rowCount, rs.getInt(3) + "");
						// Threat_Id
						content.put(6 + "/" + rowCount, rs.getString(4));
						// Risk_Id
						content.put(7 + "/" + rowCount, rs.getString(2));
						content.put("@@brojac", rowCount + "");

						rowCount++;

					}

				}

			}

		}

        if (tableName == "view_risk" && tableModel=="ImpactAnalysis") {
            rowCount =1;
            content.put(1 + "/" + 0, "Prijetnja");
            content.put(2 + "/" + 0, "Ranjivost");
            content.put(3 + "/" + 0, "Vjerojatnost");
            content.put(4 + "/" + 0, "Utjecaj");
            content.put("@@brojac", rowCount+"");


            while (found = rs.next()) {

				if (found) {

					if ((rs.getString(27) != null && rs.getString(27).length() > 0 )
							&& (rs.getString(26) != null && rs.getString(26).length() > 0)
							&& (rs.getInt(14)+"" != null && rs.getInt(14)+"".length() > 0)
							) {
						content.put(1 + "/" + rowCount, rs.getString(26) + "");
						content.put(2 + "/" + rowCount, rs.getString(27));
						// Risk_probability
						content.put(3 + "/" + rowCount, rs.getInt(14)+"");
						// Impact
						if( rs.getInt(16)!=0)
							content.put(4 + "/" + rowCount, rs.getInt(16) + "");
						// Vulnerability_Id
						content.put(5 + "/" + rowCount, rs.getInt(3) + "");
						// Threat_Id
						content.put(6 + "/" + rowCount, rs.getString(4));
						// Risk_Id
						content.put(7 + "/" + rowCount, rs.getString(2));
						content.put("@@brojac", rowCount + "");

						rowCount++;

					}

				}

			}

		}

        if (tableName == "view_control" && tableModel=="MeasureEvaluation") {
            rowCount=1;
            content.put(1 + "/" + 0, /*rsmd.getColumnLabel(2).toLowerCase()*/"ID");
            content.put(2 + "/" + 0, "Oznaka Kontrole");
            content.put(3 + "/" + 0, "Naziv Kontrole");
            content.put(4 + "/" + 0, "Status Kontrole");
            content.put(5 + "/" + 0, "Prijetnja");
            content.put(6 + "/" + 0, "Ranjivost");
            content.put(7 + "/" + 0, "RIZIK");
            content.put("@@brojac", rowCount+"");
            while (rs.next()) {

            	if ((rs.getString(5) != null && rs.getString(5).length() > 0 )
							&& (rs.getString(6) != null && rs.getString(6).length() > 0)
							&& (rs.getInt(14)+"" != null && rs.getInt(14)+"".length() > 0)
							&& (rs.getInt(16)+"" != null && rs.getInt(16)+"".length() > 0)
							)
            	{

                content.put(1 + "/" + rowCount,rs.getString(2));
                content.put(2 + "/" + rowCount,rs.getString(7));
                content.put(3 + "/" + rowCount,rs.getString(28));
                content.put(4 + "/" + rowCount,rs.getString(8));
                content.put(5 + "/" + rowCount,rs.getString(26));
                content.put(6 + "/" + rowCount,rs.getString(27));
                content.put(7 + "/" + rowCount,rs.getString(1));
                //Risk_probability
                content.put(8 + "/" + rowCount,rs.getInt(14)+"");
                //Detection_Probability
                if(rs.getInt(9)!=0)
                	content.put(9 + "/" + rowCount,rs.getInt(9)+"");
                //Risk_probability_description
                content.put(10 + "/" + rowCount,rs.getString(29));
                //Detection_Probability_Description
                content.put(11 + "/" + rowCount,rs.getString(10));
                //Impact
                if(rs.getInt(16)!=0)
                	content.put(12 + "/" + rowCount,rs.getInt(16)+"");
                //Impact_Description
                content.put(13 + "/" + rowCount,rs.getString(30));
                //Exposure_Factor
                content.put(14 + "/" + rowCount,rs.getDouble(11)+"");
                //Financial_impact
                content.put(15 + "/" + rowCount,rs.getDouble(13)+"");
                //Exposure_Factor_Description
                content.put(16 + "/" + rowCount,rs.getString(12));


                content.put("@@brojac", rowCount+"");

                rowCount++;
            	}


            }
        }



        rs.close();
        stmt.close();

        return content;

        }

//	public String[] getComboItemsThreatName(String tableName, String whereStatement) throws SQLException{
//		PreparedStatement stmt = null;
//
//		String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";
//
//		stmt = connection.prepareStatement(query);
//		stmt.setMaxRows(5);
//		ResultSet rs = stmt.executeQuery();
//		Vector<String> items= new Vector<String>();
//		while (rs.next()) {
//			            int comboID = rs.getInt(1); // or rs.getString("NAME");
//		                String name= rs.getString(3);
//		               // String desc = rs.getString(3);
//		                items.add(comboID+"-"+name);
//
//
//
//		}
//		rs.close();
//		stmt.close();
//		String[] itemsString= new String[items.size()];
//		int count=0;
//	      for(String x : items ) {
//	    	  itemsString[count++]=x;
//	         }
//
//		return itemsString;
//
//		}

    public String[] getComboItemsThreatOrVulnerability(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
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


    public String[] getComboItemsControls(String tableName, String whereStatement) throws SQLException{
        PreparedStatement stmt = null;

        String query = "SELECT * FROM ASISMS.dbo."+tableName+" "+whereStatement+"";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);
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

    public void insertAssetData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;

        String query = "INSERT INTO ASISMS.dbo."+tableName+" (assettype_id, name, category, owner, description, confidentiality_level, integrity_level, accessibility_level, businessimpact_level,bi_description) VALUES (?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("assettype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("category"));
        stmt.setObject(counter++, data.get("owner"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("confidentiality_level"));
        stmt.setObject(counter++, data.get("integrity_level"));
        stmt.setObject(counter++, data.get("accessibility_level"));
        stmt.setObject(counter++, data.get("businessimpact_level"));
        stmt.setObject(counter++, data.get("bi_description"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateAssetData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET assettype_id=?, name=?, category=?, owner=?, description=?, confidentiality_level=?, integrity_level=?, accessibility_level=?, businessimpact_level=?,bi_description=? WHERE asset_id=?";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("assettype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("category"));
        stmt.setObject(counter++, data.get("owner"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("confidentiality_level"));
        stmt.setObject(counter++, data.get("integrity_level"));
        stmt.setObject(counter++, data.get("accessibility_level"));
        stmt.setObject(counter++, data.get("businessimpact_level"));
        stmt.setObject(counter++, data.get("bi_description"));
        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertThreatIdentData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;

        String query = "INSERT INTO ASISMS.dbo."+tableName+" (threat_id,asset_id,name,assetsubcateg_id,owner,asset_value,confidentiality_level,integrity_level,accessibility_level,businessimpact_level,date_of_assessement) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        //stmt.setObject(counter++, data.get("threat_name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateThreatIdentData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threat_id=?,asset_id=?,name=?,assetsubcateg_id=?,owner=?,asset_value=?,confidentiality_level=?,integrity_level=?,accessibility_level=?,businessimpact_level=?,date_of_assessement=? WHERE risk_id=?";
//		String query = "UPDATE ASISMS.dbo."+tableName+" SET assettype_id=?, name=?, category=?, owner=?, description=?, confidentiality_level=?, integrity_level=?, accessibility_level=?, businessimpact_level=? WHERE asset_id=?";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        //stmt.setObject(counter++, data.get("threat_name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, id);

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertVulnerabilityIdentData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;


        String query = "INSERT INTO ASISMS.dbo."+tableName+" (threat_id,vulnerability_id,asset_id,name,assetsubcateg_id,owner,asset_value,confidentiality_level,integrity_level,accessibility_level,businessimpact_level,date_of_assessement) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        //stmt.setObject(counter++, data.get("threat_name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateVulnerabilityIdentData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threat_id=?,vulnerability_id=?,asset_id=?,name=?,assetsubcateg_id=?,owner=?,asset_value=?,confidentiality_level=?,integrity_level=?,accessibility_level=?,businessimpact_level=?,date_of_assessement=? WHERE risk_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        //stmt.setObject(counter++, data.get("threat_name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, id);

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertProbabilityData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;


        String query = "INSERT INTO ASISMS.dbo."+tableName+" " +
        		"(threat_id,vulnerability_id,asset_id," +
        		"name,assetsubcateg_id,owner,asset_value," +
        		"confidentiality_level,integrity_level,accessibility_level," +
        		"businessimpact_level,date_of_assessement," +
        		"risk_probability,detection_probability," +
        		"description_risk_probability,description_detection_probability) " +
        		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
//        if(data.get("detection_probability").substring(0,1)==null)
//        	 stmt.setObject(counter++," ");
        stmt.setObject(counter++, data.get("detection_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_risk_probability"));
        stmt.setObject(counter++, data.get("description_detection_probability"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateProbabilityData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threat_id=?,vulnerability_id=?,asset_id=?,name=?,assetsubcateg_id=?,owner=?,asset_value=?,confidentiality_level=?,integrity_level=?,accessibility_level=?,businessimpact_level=?,date_of_assessement=?,risk_probability=?,detection_probability=?,description_risk_probability=?,description_detection_probability=? WHERE risk_id=?";
//		String query = "UPDATE ASISMS.dbo."+tableName+" SET assettype_id=?, name=?, category=?, owner=?, description=?, confidentiality_level=?, integrity_level=?, accessibility_level=?, businessimpact_level=? WHERE asset_id=?";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
      	stmt.setObject(counter++, data.get("detection_probability").substring(0, 1));
		stmt.setObject(counter++, data.get("description_risk_probability"));
		stmt.setObject(counter++, data.get("description_detection_probability"));
        stmt.setObject(counter++, id);

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertImpactAnalysisData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;


        String query = "INSERT INTO ASISMS.dbo."+tableName+" (threat_id,vulnerability_id,asset_id,name," +
        		"assetsubcateg_id,owner,asset_value,confidentiality_level," +
        		"integrity_level,accessibility_level,businessimpact_level," +
        		"date_of_assessement,risk_probability,description_risk_probability," +
        		"impact,description_impact) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_risk_probability"));
        //        if(data.get("detection_probability").substring(0,1)==null)
//        	 stmt.setObject(counter++,"");
        stmt.setObject(counter++, data.get("impact").substring(0,1));
        stmt.setObject(counter++, data.get("description_impact"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateImpactAnalysisData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threat_id=?,vulnerability_id=?,asset_id=?,name=?,assetsubcateg_id=?,owner=?,asset_value=?,confidentiality_level=?,integrity_level=?,accessibility_level=?,businessimpact_level=?,date_of_assessement=?,risk_probability=?,description_risk_probability=?,impact=?,description_impact=? WHERE risk_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
		stmt.setDate(counter++, new Date(Calendar.getInstance()
				.getTimeInMillis()));
		stmt.setObject(counter++, data.get("risk_probability").substring(0, 1));
		stmt.setObject(counter++, data.get("description_risk_probability"));
		// if(data.get("detection_probability").substring(0,1)==null)
		// stmt.setObject(counter++,"");
		stmt.setObject(counter++, data.get("impact").substring(0, 1));
		stmt.setObject(counter++, data.get("description_impact"));
		stmt.setObject(counter++, id);

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }


    public void insertRiskAssessmentData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;


        String query = "INSERT INTO ASISMS.dbo."+tableName+" (threat_id,vulnerability_id,asset_id,name," +
        		"assetsubcateg_id,owner,asset_value,confidentiality_level," +
        		"integrity_level,accessibility_level,businessimpact_level," +
        		"date_of_assessement,risk_probability,description_risk_probability," +
        		"detection_probability,description_detection_probability," +
        		"impact,description_impact,exposure_factor,description_exposure_factor," +
        		"financial_impact) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_risk_probability"));
        stmt.setObject(counter++, data.get("detection_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_detection_probability"));
        //        if(data.get("detection_probability").substring(0,1)==null)
//        	 stmt.setObject(counter++,"");
        stmt.setObject(counter++, data.get("impact").substring(0,1));
        stmt.setObject(counter++, data.get("description_impact"));
        stmt.setObject(counter++, data.get("exposure_factor"));
        stmt.setObject(counter++, data.get("description_exposure_factor"));
        stmt.setObject(counter++, data.get("financial_impact"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }


    public void updateRiskAssessmentData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threat_id=?," +
        		"vulnerability_id=?,asset_id=?,name=?," +
        		"assetsubcateg_id=?,owner=?,asset_value=?,confidentiality_level=?," +
        		"integrity_level=?,accessibility_level=?,businessimpact_level=?," +
        		"date_of_assessement=?,risk_probability=?,description_risk_probability=?," +
        		"detection_probability=?,description_detection_probability=?," +
        		"impact=?,description_impact=?," +
        		"exposure_factor=?,description_exposure_factor=?,financial_impact=? WHERE risk_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("asset_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("assetsubcateg_id").substring(0,4));
        stmt.setObject(counter++, data.get("owner").substring(0,5));
        stmt.setObject(counter++, data.get("asset_value"));
        stmt.setObject(counter++, data.get("confidentiality_level").substring(0,1));
        stmt.setObject(counter++, data.get("integrity_level").substring(0,1));
        stmt.setObject(counter++, data.get("accessibility_level").substring(0,1));
        stmt.setObject(counter++, data.get("businessimpact_level").substring(0,1));
        stmt.setDate(counter++, new Date(Calendar.getInstance().getTimeInMillis()));
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_risk_probability"));
//        if(data.get("detection_probability").substring(0,1)==null)
//        	stmt.setObject(counter++,"");

        stmt.setObject(counter++, data.get("detection_probability").substring(0,1));
        stmt.setObject(counter++, data.get("description_detection_probability"));
        stmt.setObject(counter++, data.get("impact").substring(0,1));
        stmt.setObject(counter++, data.get("description_impact"));
        stmt.setObject(counter++, data.get("exposure_factor"));
        stmt.setObject(counter++, data.get("description_exposure_factor"));
        stmt.setObject(counter++, data.get("financial_impact"));
        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }


    public void updateMeasureEvaluationData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;
        String query = "UPDATE ASISMS.dbo."+tableName+" SET risk_probability=?," +
        		"impact=?,mitigation_id=? WHERE risk_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("risk_probability").substring(0,1));
        stmt.setObject(counter++, data.get("impact").substring(0,1));
        stmt.setObject(counter++, data.get("mitigation_id").substring(0,1));
        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertVulnerabilityData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;
        String query = "INSERT INTO ASISMS.dbo."+tableName+" (vulnerabilitytype_id,name," +
        		"description,active,vulnerability_level,example) VALUES (?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("vulnerabilitytype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("active"));
        stmt.setObject(counter++, data.get("vulnerability_level"));
        stmt.setObject(counter++, data.get("example"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateVulnerabilityData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET vulnerabilitytype_id=?,name=?,description=?," +
        		"active=?,vulnerability_level=?,example=? WHERE vulnerability_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("vulnerabilitytype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("active"));
        stmt.setObject(counter++, data.get("vulnerability_level"));
        stmt.setObject(counter++, data.get("example"));
//        if(data.get("detection_probability").substring(0,1)==null)
//        	stmt.setObject(counter++,"");

        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void insertThreatData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;
        String query = "INSERT INTO ASISMS.dbo."+tableName+" (threattype_id,name," +
        		"source,origin,intention,description,motivation,impact_level,frequency,probability) VALUES (?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threattype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("source"));
        stmt.setObject(counter++, data.get("origin"));
        stmt.setObject(counter++, data.get("intention"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("motivation"));
        stmt.setObject(counter++, data.get("impact_level"));
        stmt.setObject(counter++, data.get("frequency"));
        stmt.setObject(counter++, data.get("probability"));
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateThreatData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET threattype_id=?,name=?,source=?," +
        		"origin=?,intention=?,description=?,motivation=?," +
        		"impact_level=?,frequency=?,probability=? WHERE threat_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("threattype_id"));
        stmt.setObject(counter++, data.get("name"));
        stmt.setObject(counter++, data.get("source"));
        stmt.setObject(counter++, data.get("origin"));
        stmt.setObject(counter++, data.get("intention"));
        stmt.setObject(counter++, data.get("description"));
        stmt.setObject(counter++, data.get("motivation"));
        stmt.setObject(counter++, data.get("impact_level"));
        stmt.setObject(counter++, data.get("frequency"));
        stmt.setObject(counter++, data.get("probability"));
//        if(data.get("detection_probability").substring(0,1)==null)
//        	stmt.setObject(counter++,"");

        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }


    public void insertSuggestMeasuresData(String tableName, Hashtable<String, String> data) throws Exception{
        PreparedStatement stmt = null;
        String query = "INSERT INTO ASISMS.dbo."+tableName+" (control_id,risk_id," +
        		"vulnerability_id,threat_id,application) VALUES (?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("control_id"));
        stmt.setObject(counter++, data.get("risk_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("application"));

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }

    public void updateSuggestMeasuresData(String tableName, Hashtable<String, String> data, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "UPDATE ASISMS.dbo."+tableName+" SET control_id=?,risk_id=?,vulnerability_id=?," +
        		"threat_id=?,application=? WHERE ascontrolrisk_id=?";

        stmt = connection.prepareStatement(query);
        int counter=1;
        stmt.setObject(counter++, data.get("control_id"));
        stmt.setObject(counter++, data.get("risk_id"));
        stmt.setObject(counter++, data.get("vulnerability_id"));
        stmt.setObject(counter++, data.get("threat_id"));
        stmt.setObject(counter++, data.get("application"));

        stmt.setObject(counter++, id);
        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();


        }






    public void deleteData (String tableName, String idField, String id) throws Exception{
        PreparedStatement stmt = null;

        String query = "DELETE FROM ASISMS.dbo."+tableName+" WHERE "+idField+"='"+id+"'";

        stmt = connection.prepareStatement(query);
        //stmt.setMaxRows(5);

        int rs = stmt.executeUpdate();
        if (rs!=1){
            connection.rollback();
            throw new Exception();
        }
        else
            connection.commit();



        stmt.close();

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
