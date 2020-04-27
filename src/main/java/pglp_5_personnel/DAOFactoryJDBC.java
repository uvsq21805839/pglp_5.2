package pglp_5_personnel;

import java.io.IOException;
import java.sql.SQLException;

import bd.*;

public class DAOFactoryJDBC {
	
	 public static Dao_ConnectionBd<AnnuaireNum> getJDBCAnnuaireNumDAO() throws IOException, SQLException{
		return new JDBCAnnuaireNumDAO();
		}
	 
	public static Dao_ConnectionBd<Personnel> getJDBCPersonnelDao() throws IOException, SQLException{  
			return new JDBCPersonnelDao(); 
		}
	
    public static Dao_ConnectionBd<AffichageGroup> getGroupe() throws IOException, SQLException{
		        return new AffichageGroupDao();
		        
		 }
    
}
