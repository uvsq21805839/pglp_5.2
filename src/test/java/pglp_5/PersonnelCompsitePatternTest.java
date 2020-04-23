package pglp_5;

import static org.junit.Assert.*;

 
import java.util.Iterator;

import org.junit.Test;


import pglp_5_personnel.Annu;

import pglp_5_personnel.PersonnelCompsitePattern;




public class PersonnelCompsitePatternTest {
	
	public void testConstructeur() {
		PersonnelCompsitePattern x = new PersonnelCompsitePattern(); 
		Iterator<Annu> ip = x.iterator();
		assertFalse(ip.hasNext());
	}
	@Test

	public void testAjout() {
		PersonnelCompsitePattern x = new PersonnelCompsitePattern();
		x.add(new PersonnelCompsitePattern());
		Iterator<Annu> ip = x.iterator();
		assertTrue(ip.hasNext());
	}
	@Test

	public void testSuppression() {
		PersonnelCompsitePattern x = new PersonnelCompsitePattern();
		PersonnelCompsitePattern y = new PersonnelCompsitePattern();
		Annu i = y;
		x.add(y);
		x.remove(i);
		Iterator<Annu> ip = x.iterator();
		assertFalse(ip.hasNext());
	}
	
	
}
