package pglp_5_personnel;


import java.sql.Connection;

public final class DaoFactory {
	   public static Dao<Personnel> PersonnelDao(Connection conn){
	        return new PersonnelDao();
	    }

	    public static Dao<PersonnelCompsitePattern> getCompositePerso(Connection conn){
	        return new PersonnelCompsitePatternDao();
	    }
	}