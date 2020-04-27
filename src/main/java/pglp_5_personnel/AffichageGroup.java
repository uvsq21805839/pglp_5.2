package pglp_5_personnel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;


public class AffichageGroup implements Annu  {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 3090464133771701830L;
	private  ArrayList<Annu> personnes = new ArrayList<Annu>();
	
	public ArrayList<Annu> getPersonnes() {  
		return personnes;
	}
	private String nom; 
     
	private int id;
	
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public AffichageGroup( int id, String nom) {
        this.nom = nom;
        this.id=id;
    }
   
    
	public void print() {
	
		for(Annu composant: personnes) {
			composant.print();
		}
	}
	
	public void add(Annu composant) {
		personnes.add(composant);
	}
	
	public void remove(Annu composant) {
		personnes.remove(composant);
	}
	 public String getNom() {
	        return nom;
	    }
	 public void hierarchique() {
	        Iterator<Annu> iterator = personnes.iterator();
	        System.out.println(this.getNom() + ":    ");
	        while (iterator.hasNext()) {
	            Annu annu = iterator.next();
	            annu.print();
	        }
	 } 
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        AffichageGroup autre = (AffichageGroup) obj;
	        if (personnes == null) {
	            if (autre.personnes != null)
	                return false;
	        } else if (!personnes.equals(autre.personnes))
	            return false;
	        if (nom == null) {
	            if (autre.nom != null) 
	                return false;
	        } else if (!nom.equals(autre.nom))
	            return false;
	        return true;
	    }
	 
}