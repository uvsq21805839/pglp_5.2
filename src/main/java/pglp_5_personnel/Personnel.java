package pglp_5_personnel;



import java.io.Serializable;

	
	
	import java.time.DateTimeException;
	import java.time.LocalDate;
	import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Personnel implements Annu,Serializable{
		
	private static final long serialVersionUID = 1L;
 	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }       
        if (getClass() != obj.getClass()) {
            return false;
        }
        Personnel other = (Personnel) obj;
        if (dateNaissance == null) {
            if (other.dateNaissance != null) {
                return false;
            }        
        } else if (!dateNaissance.equals(other.dateNaissance)) {       
            return false;
        }
        if (fonction == null) {
            if (other.fonction != null) {
                return false;
            }
        } else if (!fonction.equals(other.fonction)) {
            return false;
        }
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (numtelephone == null) {
            if (other.numtelephone != null) {
                return false;
            }
        } else if (!numtelephone.equals(other.numtelephone))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }
 	
	private final String nom;
     private final String prenom;
     public int getId() {
		return id;
	}

	private final String fonction;
     private final LocalDate dateNaissance;
     private final ArrayList<AnnuaireNum> numtelephone;
     private final int id;
     
     public static class Builder{
    	 private final int id;
    	 private final String nom;
         private final String prenom;
         private String fonction;
         private LocalDate dateNaissance;
         private ArrayList<AnnuaireNum> numtelephone;
        
         public Builder(final int id,final String nom,final String prenom,final String fonction,final LocalDate dateNaissance) {
        	 this.id=id;
        	 this.nom=nom;
        	 this.prenom=prenom;
        	 this.fonction = fonction;
 			 this.dateNaissance= dateNaissance;
 			 this.numtelephone= new ArrayList<AnnuaireNum>();
         }
         
         public Builder numtelephone(AnnuaireNum numtelephone) {
        	  
              this.numtelephone.add(numtelephone);
        	  return this;
         }
       public Personnel build() {
    	   return new Personnel(this);
       }
     }
     private Personnel(Builder builder) {
 		id=builder.id;
    	nom=builder.nom;
 		prenom=builder.prenom;
 		fonction=builder.fonction;
 		dateNaissance = builder.dateNaissance;
 		numtelephone=builder.numtelephone;
 	}
     
     public String getNom() {
 		return nom;
 	}

 	
 	public String getPrenom() {
 		return prenom;
 	}


 	public String getFonction() {
 		return fonction;
 	}


 	public LocalDate getdateNaissance() {
 		return dateNaissance;
 	}

 	
 	public ArrayList<AnnuaireNum> getAnnuaireNum() {
 		return numtelephone;
 	}
 	public void print() {
		System.out.println(this.nom + " " + this.prenom + ": \nfonction: " 
		+ this.fonction + "\ndate de naissance: "+this.dateNaissance + "\n");	
	}
 	
	
}
