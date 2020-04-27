 package pglp_5_personnel;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import bd.Dao_ConnectionBd;
import bd.JDBCPersonnelDao;

public class AffichageGroupDao extends Dao_ConnectionBd<AffichageGroup> {
	
private Dao_ConnectionBd<Personnel> persoJDBC;
	 
	public AffichageGroupDao() throws SQLException, IOException {
		super(); 
		 persoJDBC = new DAOFactoryJDBC().getJDBCPersonnelDao();
	} 
	 

	//private static String JDBC_URL = BdConnection.JDBC_URL; 

	public void createtable() throws SQLException {
		 DatabaseMetaData bd = getConnect().getMetaData();
	        ResultSet rs = bd.getTables(null, null,
	                "Groupe".toUpperCase(), null);

	        Statement stmt = getConnect().createStatement();
	            if (!rs.next()) {
	            	stmt.executeUpdate("Create Table Groupe"
	                        + " (id_grp int primary key, nom varchar(30))");
	            }	
	            System.out.println("Table   Telephones Create Successfully Groupe ");
	            rs.close();
	            stmt.close();
	}
	
	
	public void createpersoingroup(int idgrp,int idperso) throws SQLException {
		DatabaseMetaData bd = getConnect().getMetaData();
       ResultSet rs = bd.getTables(null, null,
               "group_personne".toUpperCase(), null);

       Statement stmt = getConnect().createStatement();
           if (!rs.next()) {
               stmt.executeUpdate("Create table"
                       + " group_personne"
                       + " (id_grp int,"
                       + "id_pers int, "
                       + "primary key (id_grp, id_pers),"
                       + "foreign key (id_grp) references"
                       + " Groupe(id_grp), "
                       + "foreign key (id_pers)"
                       + "references Personnes(id_pers))");
           }
               stmt.executeUpdate("insert into"
                       + " group_personne values ("
                       + idgrp + "," + idperso + ")");
                   rs.close();
                   stmt.close();
	}


	public void creategrpingrp(int idgrp,int idsousgrp) throws SQLException {
		DatabaseMetaData bd = getConnect().getMetaData();
       ResultSet rs = bd.getTables(null, null,
               "groupeingrp".toUpperCase(), null);

       Statement stmt = getConnect().createStatement();
           if (!rs.next()) {
               stmt.executeUpdate("Create table"
                       + " groupeingrp"
                       + "( id_grp int,"
                       + "id_sousgrp int, "
                       + "primary key (id_grp, id_sousgrp),"
                       + "foreign key (id_grp) references"
                       + " Groupe(id_grp), "
                       + "foreign key (id_sousgrp)"
                       + " references Groupe(id_grp))");
           }
               stmt.executeUpdate("insert into"
                       + " groupeingrp values ("
                       + idgrp + "," + idsousgrp + ")");
                   rs.close();
                   stmt.close();
	}
	@Override
	public AffichageGroup create(AffichageGroup obj) throws IOException, SQLException {
		 String insertgrp = ("insert into Groupe values ("
                + "?, ? )");
        PreparedStatement update =getConnect().prepareStatement(insertgrp);
        update.setInt(1, obj.getId());
        update.setString(2, obj.getNom());
        update.executeUpdate();
        update.close();
        
        Personnel p;
        AffichageGroup gp;
       for (Annu comp : obj.getPersonnes()) {
           if (comp instanceof Personnel) {
                p = (Personnel) comp;
                ((JDBCPersonnelDao) persoJDBC).createtable();
                persoJDBC.create(p);
                this.createpersoingroup(obj.getId(),p.getId());
              } else {
                gp = (AffichageGroup) comp;
                this.create(gp);
               this.creategrpingrp(obj.getId(), gp.getId());
                   }
        }
	        return obj;
	}

	@Override
	public AffichageGroup find(int id) throws IOException, ClassNotFoundException, SQLException {
		AffichageGroup grp = null;
       Statement stmt = getConnect().createStatement();
       ResultSet rs = stmt.executeQuery("select * from Groupe"
                           + " where id_grp=" + id);
                   System.out.println("Research table Groupe: \n");
	                System.out.println("id_grp\t nom");
                       if (rs.next()) {
                       	 System.out.printf("%d\t%s%n", rs.getInt("id_grp"),
    	                            rs.getString("nom"));
                       rs.close();
                       stmt.close();
                   }
      return grp;
          
	}

	@Override
	public AffichageGroup update(AffichageGroup obj) throws IOException, SQLException {
		 Statement stmt = getConnect().createStatement();
	     ResultSet result = stmt.executeQuery("select *"
	                    + "from Groupe where id_grp="
	                    + obj.getId());
	                if (result.next()) {
	                	this.delete(obj);
	                    this.create(obj);
	                    System.out.println("Table Updated Groupe Successfully");
	                }
	                stmt.close();
	               
       return obj;
	}

	@Override
	public void delete(AffichageGroup obj) throws SQLException {
		Statement stmt = getConnect().createStatement();
		
       ResultSet rs = stmt.executeQuery("select * from Groupe where id_grp=" + obj.getId());
           if(rs.next()) {
           	stmt.executeUpdate("delete from groupeingrp where id_grp="+obj.getId());
           	stmt.executeUpdate("delete from group_personne where id_grp="+obj.getId());
          	  stmt.executeUpdate("delete from Groupe where id_grp="+obj.getId());
           	 System.out.printf("Ligne supprimée \n");
          
           } 
           rs.close();
           stmt.close();
	}
	public void droptable() throws SQLException {
		Statement stmt = getConnect().createStatement();
		stmt.execute("DROP TABLE group_personne");
		stmt.execute("DROP TABLE groupeingrp");
       stmt.execute("DROP TABLE Groupe");
   	stmt.close();
   	System.out.println("Tables supprimées \n");
		
	}
	public void affichetable() throws SQLException {
		 DatabaseMetaData bd = getConnect().getMetaData();

	        ResultSet rs = bd.getTables(null, null,"Groupe".toUpperCase(), null);
	        Statement stmt = getConnect().createStatement();
	        
	    	rs = stmt.executeQuery("SELECT * FROM Groupe");

	         System.out.println("Table Groupe: \n");
	         System.out.println("id_grp\t nom");
	         while (rs.next()) {
	             System.out.printf("%d\t%s%n", rs.getInt("id_grp"),
	                     rs.getString("nom"));
	         }
	         rs.close();
	         stmt.close();
	}
	public void affichegrpparsousgrp() throws SQLException {
		 DatabaseMetaData bd = getConnect().getMetaData();
        ResultSet rsEx = bd.getTables(null, null,"groupeingrp".toUpperCase(),
                null);
        if (rsEx.next()) {
            Statement stmt = getConnect().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *"
                        + " FROM groupeingrp");
                    System.out.println("Table groupeingrp:\n");
                    System.out.println("id_grp\t id_sousgrp\t");
                    while (rs.next()) {
                        System.out.printf("%d\t%s%n",
                                rs.getInt("id_grp"),
                                rs.getInt("id_sousgrp"));
                    }
                    
                    rs.close();
                }	
		
	}
	public void displayPersoByGrp() throws SQLException {
		 DatabaseMetaData bd = getConnect().getMetaData();
        ResultSet rsEx = bd.getTables(null, null,"group_personne".toUpperCase(),
                null);
        if (rsEx.next()) {
            Statement stmt = getConnect().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *"
                        + " FROM group_personne");
                    System.out.println("Table group_personne:\n");
                    System.out.println("id_grp\t id_pers\t");
                    while (rs.next()) {
                        System.out.printf("%d\t%s%n",
                                rs.getInt("id_grp"),
                                rs.getInt("id_pers"));
                    }
                    
                    rs.close();
                }	
	}




}
