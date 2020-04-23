package pglp_5;

import static org.junit.Assert.*;

import java.time.LocalDate;
 

import org.junit.Test;

import pglp_5_personnel.Personnel;


public class PersonnelTest {
	@Test
	
	public void test() {
		
        Personnel p = new Personnel.Builder("Sekou", "Diawara", LocalDate.of(1991, 9, 12),"07 51 07 80 00").build();
        assertTrue(p.getNom().equals("Sekou") && p.getPrenom() == "Diawara" &&
                p.getDateNaissance().equals(LocalDate.of(1991, 9, 12))&&
                p.getNumTelephone().contains("07 51 07 80 00"));
         
        Personnel p1 = new Personnel.Builder("Mamby", "Diawara", LocalDate.of(1989, 11, 14),"06 56 37 50 80").build();
        assertTrue(p1.getNom().equals("Mamby") && p.getPrenom() == "Diawara" &&
        		p1.getDateNaissance().equals(LocalDate.of(1989, 11, 14)) &&
        		 p1.getNumTelephone().contains("06 56 37 50 80")); 
        		
       
        Personnel p2 = new Personnel.Builder("Oumou", "Diawara", LocalDate.of(1987, 12, 18),"09 97 07 50 02").build();
        assertTrue(p2.getNom().equals("Oumou") && p.getPrenom() == "Diawara" &&
        		p2.getDateNaissance().equals(LocalDate.of(1987, 12, 18))&&
           		p2.getNumTelephone().contains("09 97 07 50 02")); 
	 
	}
	
}
