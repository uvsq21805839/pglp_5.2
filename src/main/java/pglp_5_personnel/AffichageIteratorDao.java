 package pglp_5_personnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class AffichageIteratorDao implements Dao<AffichageIterator>, Serializable {

	private static final long serialVersionUID = -3333895780097726315L;
	   private ArrayList<AffichageIterator> list;
	    public AffichageIteratorDao() {
	        list = new ArrayList<AffichageIterator>();
	    }
	

	public void add(final AffichageIterator object) {
		// TODO Auto-generated method stub
		list.add(object);	
	}

	public AffichageIterator get(final int id) {
		        for (AffichageIterator i : list) {
		            if (i.getId() == id) {
		                return i;
		            }
		        }
		return null;
	}

	public ArrayList<AffichageIterator> getAll() {
		return (ArrayList<AffichageIterator>) list.clone();
		 
	}

	public void update(final AffichageIterator object, final Map<String, Object> params) {
		if (list.contains(object)) {
            ArrayList<Annu> remplace =
            (ArrayList<Annu>) params.get("c");
            if (params.containsKey("c")) {
                object.reset();
                for (Annu ip : remplace) {
                    object.add(ip);
                }
            }
        }
		
	}

	public void remove(final AffichageIterator object) {
		// TODO Auto-generated method stub
		list.remove(object);
	}

}
