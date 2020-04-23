package pglp_5_personnel;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class PersonnelDao implements Dao<Personnel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8471981916494119888L;
	private ArrayList<Personnel> list;
	public PersonnelDao() {
		list = new ArrayList<Personnel>(); 
	}

	public void add(Personnel object) {
		// TODO Auto-generated method stub 
		list.add(object);
		}

	public Personnel get(int id) {
		// TODO Auto-generated method stub
		for (Personnel x : list) {
			if(x.getId() == id) {
				return x;
			}
		}
		return null;
	}

	public ArrayList<Personnel> getAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Personnel>) list.clone();
	}

	public void update(Personnel object, Map<String, Object> params) {
		
		// TODO Auto-generated method stub
		 if (list.remove(object)) {
	            String nom = "";
	            if (params.containsKey("nom")) {
	                nom = (String) params.get("nom");
	            } else {
	                nom = object.getNom();
	            }
	            String prenom = "";
	            if (params.containsKey("prenom")) {
	                prenom = (String) params.get("prenom");
	            } else {
	                prenom = object.getPrenom();
	            }
	            LocalDate dateNaissance;
	            if (params.containsKey("dateNaissance")) {
	                dateNaissance = (LocalDate) params.get("dateNaissance");
	            } else {
	                dateNaissance = object.getDateNaissance();
	            }
	            String numTelephone = ""; 
	            if (params.containsKey("numTelephone")) {
	            	numTelephone= (String)  params.get("numTelephone");
	               
	            } else {
	            	numTelephone = object.getNumTelephone();
	            }
	            Personnel x = new Personnel.Builder(
	                nom, prenom, dateNaissance,numTelephone).build();
	            list.add(x);
	        }
	}
 
	public void remove(Personnel object) {
		// TODO Auto-generated method stub
		 list.remove(object);
		
	}

}
