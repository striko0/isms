package hr.ante.isms.connection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

public class DataFromDatabase {

	public String[] getComboItemsFromDB(String tableName) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItems(tableName);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {};

	}

	public String[] getComboItemsFromDB(String tableName, String whereStatement) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsWithWhere(tableName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {};

	}

	public String[] getComboItemsFromDB(String tableName,
			String whereStatement, boolean valid) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {
			// if(type=="threat")
			return con.getComboItemsThreatOrVulnerability(tableName,
					whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {};

	}


	public String[] getThreatVulnerabilityItemsFromDB(String tableName,String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsThreatOrVulnerability(tableName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[]{};

	}

	public String[] getControlItemsFromDB(String tableName,String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getComboItemsControls(tableName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[]{};

	}

	public String getDesiredColumnFromDB(String tableName, String columnName, String whereStatement){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getContentFromDesiredColumn(tableName, columnName, whereStatement);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String();

	}

	public void insertDataInDB(String tableName, Hashtable data, String updateOrInsert, String type, String id) throws Exception{
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			if (type == "ImpactAnalysis") {
				if (updateOrInsert == "insert")
					con.insertImpactAnalysisData(tableName, data);
				if (updateOrInsert == "update")
					con.updateImpactAnalysisData(tableName, data, id);
			}

			if (type == "RiskAssessment") {
				if (updateOrInsert == "insert")
					con.insertRiskAssessmentData(tableName, data);
				if (updateOrInsert == "update")
					con.updateRiskAssessmentData(tableName, data, id);
			}

			if(type=="MeasureEvaluation"){
//				if (updateOrInsert == "insert")
//					con.insertMeasureEvaluationData(tableName, data);
//				if (updateOrInsert == "update")
					con.updateMeasureEvaluationData(tableName, data, id);
			}

			if(type=="Vulnerability"){
				if (updateOrInsert == "insert")
					con.insertVulnerabilityData(tableName, data);
				if (updateOrInsert == "update")
					con.updateVulnerabilityData(tableName, data, id);

			}

			if(type=="Threat"){
				if (updateOrInsert == "insert")
					con.insertThreatData(tableName, data);
				if (updateOrInsert == "update")
					con.updateThreatData(tableName, data, id);

			}

			if(type=="SuggestMeasures"){
				if (updateOrInsert == "insert")
					con.insertSuggestMeasuresData(tableName, data);
				if (updateOrInsert == "update")
					con.updateSuggestMeasuresData(tableName, data, id);

			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

	public HashMap<String, String> getContentFromDB(String tableName, String tableModel){
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();


		try {

			return con.getContentForTable(tableName, tableModel, "");


		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new HashMap<String, String>();

	}


	public HashMap<String, String> getContentFromDB(String tableName,
			String vrsta, String assetId) {
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			return con.getContentForTable(tableName, vrsta, assetId);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new HashMap<String, String>();

	}


	public void deleteDataFromDB(String tableName, String idField, String id) throws Exception{
		DatabaseConnection con = new DatabaseConnection();
		con.doConnection();

		try {

			con.deleteData(tableName,idField, id);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		System.out.println("Connection : " + con.doConnection());
		try {
			con.connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

}
