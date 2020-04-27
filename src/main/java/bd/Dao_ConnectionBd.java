package bd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author sekou
 *
 *Connection a la base
 *
 *
 */

public abstract class Dao_ConnectionBd  <T>{
	
	  public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	  public static final String JDBC_URL = "jdbc:derby:MyBD; create = true"; 
	  
	  Connection conn;   
	  
	 public  Dao_ConnectionBd() {
		 try {  
			 this.conn = DriverManager.getConnection(JDBC_URL); 
			 
			 if (this.conn !=null) {
				 
				 System.out.println("connexion avec succès");
			 }
		 }catch(SQLException e) {
			 System.out.println("Echec de connexion");
	  }
		 
	  
	  
    }
    /**
     * creation
     */
   



public abstract T create(T obj) throws IOException, SQLException;

public abstract T find(int id) throws IOException, ClassNotFoundException, SQLException;

public abstract T update(T obj) throws IOException, SQLException;

public abstract void delete(T obj)throws SQLException;

public Object deserialize(final byte[] bytes) throws ClassNotFoundException,
IOException {
    ByteArrayInputStream b = new ByteArrayInputStream(bytes);
    ObjectInputStream o = new ObjectInputStream(b);
    return o.readObject();
}

public byte[] serialize(final Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
 public Connection getConnect() {
        return conn;
    }
}
