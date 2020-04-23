package pglp_5_personnel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class PersonnelCompsitePatternDao implements Dao<PersonnelCompsitePattern>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114502759451742134L;
	
	 private ArrayList<PersonnelCompsitePattern> list;
	  
	    public PersonnelCompsitePatternDao() { 
	        list = new ArrayList<PersonnelCompsitePattern>();
	    }

	public void add(PersonnelCompsitePattern object) { 
		list.add(object);		
	}

	public PersonnelCompsitePattern get(final int id) { 
		 for (PersonnelCompsitePattern a : list) {
	            if (a.getId() == id) {
	                return a;
	            }
	        }
		
		return null;
	}

	public ArrayList<PersonnelCompsitePattern> getAll() { 
		 return (ArrayList<PersonnelCompsitePattern>) list.clone(); 
		
	}

	public void update(PersonnelCompsitePattern object, Map<String, Object> params) {
		// TODO Auto-generated method stub
		if (list.contains(object)) {
            if (params.containsKey("personnel")) {
                ArrayList<Annu> remplace =
                (ArrayList<Annu>)
                params.get("personnel");
                object.reset();
                for (Annu a : remplace) {
                    object.add(a);
                }
            } 
        }
	}

	public void remove(PersonnelCompsitePattern object) {
		// TODO Auto-generated method stub
		 list.remove(object);
	}

}
