package pglp_5;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import pglp_5_personnel.AffichageIterator;
import pglp_5_personnel.Annu;
import pglp_5_personnel.Personnel;
import pglp_5_personnel.PersonnelCompsitePattern;



public class AffichageIteratorTest {

	@Test 
	public void test() {
		PersonnelCompsitePattern c1 = new PersonnelCompsitePattern();
		PersonnelCompsitePattern c2 = new PersonnelCompsitePattern();
		PersonnelCompsitePattern c3 = new PersonnelCompsitePattern();
    	PersonnelCompsitePattern c4 = new PersonnelCompsitePattern();
    	PersonnelCompsitePattern c5 = new PersonnelCompsitePattern();
    	PersonnelCompsitePattern c6 = new PersonnelCompsitePattern();
    	PersonnelCompsitePattern c7 = new PersonnelCompsitePattern();
    
    	 @SuppressWarnings("unused")
		Personnel p = new Personnel.Builder("Sekou", "Diawara", LocalDate.of(1991, 9, 12),"07 51 07 80 00").build();
    	 @SuppressWarnings("unused")
		Personnel p1 = new Personnel.Builder("Mamby", "Diawara", LocalDate.of(1989, 11, 14),"09 51 07 80 00").build();
        c4.add(c6);
        c4.add(c7);
        c3.add(c4);
        c3.add(c5);
        c1.add(c2);
        c1.add(c3);
        AffichageIterator apg = new AffichageIterator();
        apg.parcoursLargeur(c1);
        Iterator<Annu> tmp = apg.iterator();
        
        ArrayList<Annu> list = new ArrayList<Annu>();
        ArrayList<Annu> list1 = new ArrayList<Annu>();
        
        for (; tmp.hasNext(); list.add(tmp.next()));
        list1.add(c1);
        list1.add(c2);
        list1.add(c3);
        list1.add(c4);
        list1.add(c5);
        list1.add(c6);
        list1.add(c7);
        
        assertTrue(list.toString().equalsIgnoreCase(list1.toString()));
	}
	
	 
}
