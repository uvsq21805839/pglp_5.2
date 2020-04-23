package pglp_5_personnel;



import java.io.Serializable;

public final class Personnel implements Annu, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 730629534334772359L;
	 
	private final String  nom;
	public String getNom(){return nom;}
	
	private final String prenom;
	 public String getPrenom() {
	        return prenom;   }
		
	private final java.time.LocalDate dateNaissance;
	 public java.time.LocalDate getDateNaissance() {
	        return dateNaissance;
	    }
	    
	private final String numTelephone;
//	@SuppressWarnings("unchecked")
	public String getNumTelephone() {
        return  numTelephone;
    }
	  private final int id;
	     public int getId() {
	        return id;
	    }

	public static class Builder {
		private final String  nom;
		private final String prenom;
		private final java.time.LocalDate dateNaissance;
		private String numTelephone;
		 private static int idNext = 1;
	       private final int id;
		
		public Builder(final String nom, final String prenom, final java.time.LocalDate dateNaissance,final String numTelephone) {
			this.nom = nom;
			this.prenom = prenom; 
			this.dateNaissance = dateNaissance;
			this.numTelephone = numTelephone;
			this.id=idNext+1; 
		
		}
		
		public Personnel build() {
			return new Personnel(this);
		}
	}
	
	private Personnel(final Builder builder) {
		nom = builder.nom;
		prenom = builder.prenom;
		dateNaissance = builder.dateNaissance;
		numTelephone = builder.numTelephone;
		id = builder.id;
	 
	}
	public void print() {
		
		System.out.print(prenom + " " + nom	+" , naissance : " + dateNaissance + ", Numero de Telephone : " +  numTelephone );
	
		
		
	}
	public void printAnnuaireName() {
		// TODO Auto-generated method stub
		
	}
	
}
