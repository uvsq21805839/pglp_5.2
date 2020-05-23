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
import pglp_5_personnel.Personnel.Builder;
import bd.Vue;
public class Main {



	  @SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException  {
		  Dao_ConnectionBd<AnnuaireNum> numjdbc;
		   AnnuaireNum cell =new AnnuaireNum(1,"0022373080580", "cell");
		
		 
		   numjdbc = DAOFactoryJDBC.getJDBCAnnuaireNumDAO();
		  ((JDBCAnnuaireNumDAO) numjdbc).createtable();  
		 
		 
		  
		   
		  @SuppressWarnings("unused")
		Dao_ConnectionBd<Personnel> persojdbc;
		    Builder b = new Builder(1,"Sekou", "Diawara", "Administrateur",LocalDate.of(1991, 9, 12));
		    Builder b1 = new Builder(2,"Mamby", "Diawara", "programmeur",LocalDate.of(1989, 11, 14));
		   persojdbc=DAOFactoryJDBC.getJDBCPersonnelDao();
		   b.numtelephone(cell);
		
		   Personnel employee=b.build();
		   Personnel employee1=b1.build(); 
		
		   Dao_ConnectionBd<AffichageGroup> grpJDBC =new DAOFactoryJDBC().getAffichageGroup();
		
	       
		   AffichageGroup societe =new AffichageGroup(1,"Societe");
		  // @SuppressWarnings("unused")
	//	AffichageGroup service =new AffichageGroup(2, "Service");
	        societe.add(employee);
	     //   societe.add(employee1);
	        ((AffichageGroupDao) grpJDBC).createtable();
	       // grpJDBC.create(societe);
	      
	       //((AffichageGroupDao) grpJDBC).displayTable();
	        ((AffichageGroupDao) grpJDBC).displayPersoByGrp();




	       
  }
}