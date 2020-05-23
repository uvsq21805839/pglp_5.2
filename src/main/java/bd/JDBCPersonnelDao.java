package bd;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pglp_5_personnel.JDBCAnnuaireNumDAO;
import pglp_5_personnel.AnnuaireNum;
import pglp_5_personnel.DAOFactoryJDBC;
import pglp_5_personnel.Personnel;

public class JDBCPersonnelDao extends Dao_ConnectionBd<Personnel>{
	
	private Dao_ConnectionBd<AnnuaireNum> numJDBC;
	
	public JDBCPersonnelDao() throws SQLException, IOException {
		super();
		numJDBC = new DAOFactoryJDBC().getJDBCAnnuaireNumDAO();
	}
	
	public void createtable() throws SQLException { 
   	 DatabaseMetaData bd = getConnect().getMetaData();
	        ResultSet rs = bd.getTables(null, null,"Personnes".toUpperCase(), null);
	        String createperso="CREATE TABLE Personnes("
                + "id_pers int,"
                + "nom varchar(30),"
                + "prenom varchar(30),"
                +"fonction varchar(30),"
                +"date_naissance varchar(30),"
                + "PRIMARY KEY (id_pers)"
               
                + ")";
	      
	         Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.execute(createperso);
	            	
	            }	
	            
	            System.out.println("Table Personnes Create Successfully");
	            rs.close();
	            stmt.close();
   }
	 
	 public void createassoc(int obj,int objt) throws SQLException {
	   	 DatabaseMetaData bd = getConnect().getMetaData();
	        ResultSet rs = bd.getTables(null, null,"Association".toUpperCase(), null);
		 String assoc="Create Table Association"
                  + " (id_pers int,"
                  + " id_num int, "
                  + "primary key (id_pers, id_num),"
                  + "foreign key (id_pers) references"
                  + "Personnes(id_pers),"
                  + "foreign key (id_num) references"
                  + "Telephones(id_num))";
		  Statement stmt = getConnect().createStatement();
          if (!rs.next()) {
		  stmt.execute(assoc);
          }
          stmt.executeUpdate("insert into Association values ("
                  + obj + "," + objt + ")");
          rs.close();
          stmt.close();
	 }

	public Personnel create(Personnel obj) throws IOException, SQLException{
		 String insertperso =("insert into Personnes"
                 + " values (?, ?, ?,?,?)");
         PreparedStatement prpstmt = getConnect().prepareStatement(insertperso);

         prpstmt.setInt(1, obj.getId());
         prpstmt.setString(2, obj.getNom());
         prpstmt.setString(3, obj.getPrenom());
         prpstmt.setString(4, obj.getFonction());
         prpstmt.setDate(5, Date.valueOf(obj.getdateNaissance()));
         prpstmt.executeUpdate();
         prpstmt.close();
         for (AnnuaireNum num : obj.getAnnuaireNum()) {
        	 ((JDBCAnnuaireNumDAO) numJDBC).createtable();
             numJDBC.create(num);
             this.createassoc(obj.getId(), num.getId());
         }
          return obj;
	}

	public Personnel find(int id) throws IOException, ClassNotFoundException, SQLException{
		Personnel perso = null;
        Statement stmt = getConnect().createStatement();
        ResultSet rs = stmt.executeQuery("select * from Personnes"
                            + " where id_pers=" + id);
                    System.out.println("La ligne rechercher dans la table Personnes: \n");
	                System.out.println("id_pers\t nom\t prenom\t fonction\t date naissance");
                        if (rs.next()) {
                        	 System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_pers"),
            	                     rs.getString("nom"), rs.getString("prenom"),
            	                     rs.getString("fonction"),rs.getString("date_naissance"));
            	         
                        rs.close();
                        stmt.close();
                    }
       return perso;
        
    }
	

	public Personnel update(Personnel obj) throws IOException, SQLException {
		Statement stmt = getConnect().createStatement();
	    ResultSet result = stmt.executeQuery("select * from Personnes where id_pers=" + obj.getId());
	                if (result.next()) {
	                	this.delete(obj);
	                    this.create(obj);
	                    System.out.println("La mise à jour du table Personnes effectuée");
	                }
	                stmt.close();
	                return obj;
	}

	public void delete(Personnel obj) throws SQLException {
		Statement stmt = getConnect().createStatement();
		int id_num;
        ResultSet rs = stmt.executeQuery("select * from Association where id_pers=" + obj.getId());
            if(rs.next()) {
            	id_num = rs.getInt("id_num");
              stmt.executeUpdate("delete from Association where id_pers="+obj.getId()+"and id_num="+id_num);
              stmt.executeUpdate("delete from persoingroupe where id_pers="+obj.getId());
              stmt.executeUpdate("delete from Telephones where id_num="+id_num);
           	  stmt.executeUpdate("delete from Personnes where id_pers="+obj.getId());
            	 System.out.printf("Ligne supprimée \n");
           
            }
            rs.close();
            stmt.close();
	}
	 public void droptable() throws SQLException {
	    	Statement stmt = getConnect().createStatement();
	    	stmt.execute("DROP TABLE Association");
	    	stmt.execute("DROP TABLE persoingroupe");
	    	stmt.execute("DROP TABLE Personnes");
	    	stmt.close();
	    	System.out.println("Tables supprimées \n");
	    	
	    }
	 public void affichetable() throws SQLException {
	    	DatabaseMetaData bd = getConnect().getMetaData();
	        ResultSet rs = bd.getTables(null, null,"Personnes".toUpperCase(), null);
	        Statement stmt = getConnect().createStatement();
	        
	    	rs = stmt.executeQuery("SELECT * FROM Personnes");

	         System.out.println("Table Personnes: \n");
	         System.out.println("id_pers\t nom\t prenom\t fonction\t date naissance");
	         while (rs.next()) {
	             System.out.printf("%d\t%s \t%s\t %s\t %s%n", rs.getInt("id_pers"),
	                     rs.getString("nom"), rs.getString("prenom"),
	                     rs.getString("fonction"),rs.getString("date_naissance"));
	         }
	         rs.close();
	         stmt.close();
	      
	    }
	 public void afficheassoc() throws SQLException {
		 DatabaseMetaData bd = getConnect().getMetaData();
	            ResultSet rsEx = bd.getTables(null, null,"Association".toUpperCase(),
	                    null);
	            if (rsEx.next()) {
	                Statement stmt = getConnect().createStatement();
	                    ResultSet rs = stmt.executeQuery("SELECT *"
	                            + " FROM Association");
	                        System.out.println("Table Association:\n");
	                        System.out.println("id_pers\t id_num\t");
	                        while (rs.next()) {
	                            System.out.printf("%d\t\t%d\t%n",
	                                    rs.getInt("id_pers"),
	                                    rs.getInt("id_num")); 
	                        }
	                        
	                        rs.close();
	                    }
	                
	          
	 }
}



