package bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;



public class Vue {
public static final String SQL_T = "select * from personnes";
public static void main(String[] args) throws SQLException {
	
	Connection conn = DriverManager.getConnection(JDBCPersonnelDao.JDBC_URL); 
	Statement statement = conn.createStatement();
	ResultSet resultSet = statement.executeQuery(SQL_T);
	ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
	int columnCount = resultSetMetaData.getColumnCount();
	for (int x = 1; x <= columnCount; x++ ) System.out.format("%20s", resultSetMetaData.getColumnName(x)+"|");
	while(resultSet.next()) {
		System.out.println();
		for (int x = 1; x <= columnCount; x++ ) System.out.format("%20s",resultSet.getString(x)+"|");
	}
	if (statement != null) statement.close(); 
	if(conn != null)conn.close();
	
}
}
