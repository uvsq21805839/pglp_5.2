package pglp_5_personnel;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Iterator;


public class AffichageIterator implements Iterable<Annu>, Serializable   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3090464133771701830L;
	private ArrayDeque<Annu> c = new ArrayDeque<Annu>();
    private final int id;
    private static int idNext = 1;
    
    public  AffichageIterator() {
    	c = new ArrayDeque<Annu>();
        id = idNext+1;
    }
    public int getId() {
        return id;
    }
   
	
	public void parcoursLargeur(final Annu ip) {
		if (ip.getClass() == PersonnelCompsitePattern.class) {
			Annu y, z;
			PersonnelCompsitePattern tmp;
			c = new ArrayDeque<Annu>();
			ArrayDeque<Annu> d =
				new ArrayDeque<Annu>();
			d.add(ip);
			while (!d.isEmpty()) {
				y = d.pollFirst();
				c.add(y);
				if (y.getClass() == PersonnelCompsitePattern.class) {
					tmp = (PersonnelCompsitePattern) y;
					Iterator<Annu> ite =
						tmp.iterator();
					while (ite.hasNext()) {
						z = ite.next();
						if (!d.contains(z) && !c.contains(z)) {
							d.add(z);
						}
					}
				}
			}
		}
	}
	
	public Iterator<Annu> iterator() {
		return c.iterator();
	}
	
	public void print() {
		PersonnelCompsitePattern tmp;
		
		for (Annu c2 : c) {
			if (c2.getClass() == PersonnelCompsitePattern.class) {
				tmp = (PersonnelCompsitePattern) c2;
				System.out.println(tmp.getId());
			} else {
				c2.print();
			}
		}
	}
	 public void add(final Annu ip) {
	        c.add(ip);
	    }
	  
	    public void reset() {
	        while (!c.isEmpty()) {
	            c.removeFirst();
	        }
	    }
	   
}