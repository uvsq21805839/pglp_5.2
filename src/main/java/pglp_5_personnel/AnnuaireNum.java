package pglp_5_personnel;

import java.io.Serializable;

public class AnnuaireNum implements Serializable{

	
	  
		private static final long serialVersionUID = 1L;
		private int id;
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		private String type;
		private String numero;
		
		public void setType(String type) {
			this.type = type;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public AnnuaireNum(int id,String string,String numero) {
		this.type=string;
			this.numero=numero;
			this.id=id;
		}
		
		public String getType() {
			return type;
		}
		
		public String getNumero() {
			return numero;
		}
		
		 @Override
		    public boolean equals(Object obj) {
		        if (this == obj)
		            return true;
		        if (obj == null)
		            return false;
		        if (getClass() != obj.getClass())
		            return false;
		        AnnuaireNum autre = (AnnuaireNum) obj;
		       if (type == null) {
		            if (autre.type != null)
		                return false;
		        } else if (!type.equals(autre.type))
		            return false;
		        if (numero == null) {
		            if (autre.numero != null)
		                return false;
		        } else if (!numero.equals(autre.numero))
		            return false;
		        return true;
		    }

		public void affiche() {
			System.out.println( " \nType: "
	               + this.type + "\nNumerp: "
	                + this.numero + "\n");
		}
		
	}
