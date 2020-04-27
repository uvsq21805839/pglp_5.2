package pglp_5_personnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class PersonnelCompsitePattern
implements Annu, Iterable<Annu>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 646780528690206325L;

	private ArrayList<Annu> personnels;
	
	private final int id;
	
	private static int idNext = 1;
	 
	public PersonnelCompsitePattern() {
		id = idNext+1;
		personnels = new ArrayList<Annu>(); 
	}
	
	public void print() {
		System.out.println("Id : " + id);
		for (Annu ip : personnels) {
			ip.print();
		}
	}
		
	public PersonnelCompsitePattern add(final Annu ip) {
		if (!personnels.contains(ip)) {
			personnels.add(ip);
		}
		return this;
	}
	
	public PersonnelCompsitePattern remove(final Annu ip) {
		if (personnels.contains(ip)) {
			personnels.remove(ip);
		}
		return this;
	}
	
	public Iterator<Annu> iterator() {
		return personnels.iterator();
	}
	
	public final int getId() {
		return id;
	}
	public void printAnnuaireName() {
		// TODO Auto-generated method stub
		
	}
	public void reset() {
        personnels.clear();
    }
}