package bd;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import pglp_5_personnel.AffichageGroup;
import pglp_5_personnel.AffichageGroupDao;
import pglp_5_personnel.AnnuaireNum;
import pglp_5_personnel.DAOFactoryJDBC;
import pglp_5_personnel.Personnel;
import pglp_5_personnel.JDBCAnnuaireNumDAO;
import pglp_5_personnel.Personnel.Builder;;
public class Main {



	  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException  {
		  Dao_ConnectionBd<AnnuaireNum> numjdbc;
		   AnnuaireNum cell =new AnnuaireNum(1,"0022373080580", "cell");
		   AnnuaireNum cell1 =new AnnuaireNum(1,"0751078000", "cell");
		 
		   numjdbc = DAOFactoryJDBC.getJDBCAnnuaireNumDAO();
		  ((JDBCAnnuaireNumDAO) numjdbc).createtable(); 
		  
		  
		   
		  @SuppressWarnings("unused")
		Dao_ConnectionBd<Personnel> persojdbc;
		    Builder b = new Builder(1,"sekou", "Diawara", "employee",LocalDate.of(1991, 9, 12));
		   persojdbc=DAOFactoryJDBC.getJDBCPersonnelDao();
		   b.numtelephone(cell);
		
		   Personnel employee=b.build();
		
		   Dao_ConnectionBd<AffichageGroup> grpJDBC =new DAOFactoryJDBC().getGroupe();
		
	       
		   AffichageGroup departement =new AffichageGroup(1,"Departement");
		   @SuppressWarnings("unused")
		AffichageGroup service =new AffichageGroup(2, "Service");
	        departement.add(employee);
	        ((AffichageGroupDao) grpJDBC).createtable();
	        grpJDBC.create(departement);
	      
	        ((AffichageGroupDao) grpJDBC).affichetable();
	        ((AffichageGroupDao) grpJDBC).displayPersoByGrp(); 


		   
	  }
}