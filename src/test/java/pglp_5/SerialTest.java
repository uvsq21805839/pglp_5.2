package pglp_5;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import  pglp_5_personnel.Personnel;

public class SerialTest {

    Personnel persoSave;
    Personnel persoGet;

    ObjectOutputStream oos;
    ObjectInputStream ois; 

    @Before
    public void setup() {

        persoSave= new Personnel.Builder("Sekou", "Diawara", LocalDate.of(1991, 9, 12),"07 51 07 80 00").build();

        oos = null;



    }

    @Test
    public void test() {

      

        try {

            FileOutputStream fichier = new FileOutputStream("personnel.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(persoSave);
            oos.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close(); 
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

       
        try {
            FileInputStream fichier = new FileInputStream("personnel.ser");
            ois = new ObjectInputStream(fichier);
            persoGet = (Personnel) ois.readObject();
            persoGet.print();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}