package pglp_5_personnel;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Dao_ConnectionBd;


public class JDBCAnnuaireNumDAO extends Dao_ConnectionBd<AnnuaireNum>{

	
    public JDBCAnnuaireNumDAO() throws SQLException {
		super();
	}
    
    public void createtable() throws SQLException { 
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
	        ResultSet rs = dbmd.getTables(null, null,"Telephones".toUpperCase(), null);
	        String createtel="CREATE TABLE Telephones("
                 + "id_num int,"
                 + "numero varchar(30),"
                 + "type varchar(30)," 
                 + "PRIMARY KEY (id_num)"
                 + ")";
	         Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.execute(createtel);
	            }	
	            System.out.println("Create table Telephones Successfully ");
	            rs.close();
	            stmt.close();
    }
    	@Override
	public AnnuaireNum create(AnnuaireNum obj) throws IOException, SQLException {
		       String insertnum= ("insert into Telephones"
	                        + " values (?, ?, ?)");
	                PreparedStatement prpstmt = getConnect().prepareStatement(insertnum);

	                prpstmt.setInt(1, obj.getId());
	            prpstmt.setString(2, obj.getType());
	                prpstmt.setString(3, obj.getNumero());

	                prpstmt.executeUpdate();
	                System.out.println("information insert Successfully");
	                
	              prpstmt.close();
	            return obj;
	        
	}

	@Override
	public AnnuaireNum find(int id) throws IOException, ClassNotFoundException, SQLException {
		AnnuaireNum num = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Telephones"
                            + " where id_num=" + id);
                    System.out.println("Research  table Telephones: \n");
                 System.out.println("id_num\t type\t numero");
	                
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s\t%s%n", rs.getInt("id_num"),
     	                           rs.getString("type"), rs.getString("numero"));
                        rs.close();
                        stmt.close();
                    }
       return num;
		  
	}

	@Override
	public AnnuaireNum update(AnnuaireNum obj) throws IOException, SQLException {
		Statement stmt = getConnect().createStatement();
	    ResultSet result = stmt.executeQuery("select * from Telephones where id_num=" + obj.getId());
	                if (result.next()) {
	                	this.delete(obj);
	                    this.create(obj);
	                    System.out.println("La mise à jour du table Telephones effectuée");
	                }
	                stmt.close();
	                return obj;
	}

	@Override
	public void delete(AnnuaireNum obj) throws SQLException {
		 Statement stmt = getConnect().createStatement();
         ResultSet rs = stmt.executeQuery("select * from Telephones where id_num=" + obj.getId());
             if (rs.next()) {
            	 stmt.executeUpdate("delete from Association where id_num="+obj.getId());
            	  stmt.executeUpdate("delete from Telephones where id_num="+obj.getId());
             	 System.out.printf("Ligne supprimée \n");
             rs.close();
             stmt.close();
	}
	}
	public void droptable() throws SQLException {
    	Statement stmt = getConnect().createStatement();
    	stmt.execute("DROP TABLE Association");
    	stmt.execute("DROP TABLE Telephones");
    	stmt.close();
    	System.out.println("Tables supprimées \n");
    	
    }
    public void displayTable() throws SQLException {
    	DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,"Telephones".toUpperCase(), null);
        Statement stmt = getConnect().createStatement();
        
    	rs = stmt.executeQuery("SELECT * FROM Telephones");

         System.out.println("Table Telephones: \n");
        
        System.out.println("id_num\t type\t numero");
         while (rs.next()) {
             System.out.printf("%d\t%s\t%s%n", rs.getInt("id_num"),
                    rs.getString("type"), rs.getString("numero"));
         }
         rs.close();
         stmt.close();
      
    }
}
