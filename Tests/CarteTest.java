package Tests;

import Cartes.Carte;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarteTest {

    @Test
    public void testGetNumero() {
        for (int i = 1; i <= 104; ++i) {
            assertEquals(i, new Carte().getNumero());
        }
    }

    @Test
    public void testGetTeteDeBoeuf() {
        assertEquals(1, new Carte(23).getTeteDeBoeuf());
        assertEquals(2, new Carte(35).getTeteDeBoeuf());
        assertEquals(3, new Carte(70).getTeteDeBoeuf());
        assertEquals(5, new Carte(99).getTeteDeBoeuf());
        assertEquals(7, new Carte(55).getTeteDeBoeuf());
    }
    
    @Test
    public void testClone() {
    	Carte test = new Carte(11);
    	assertEquals(test.getNumero(), test.clone().getNumero());
    	assertEquals(test.getTeteDeBoeuf(), test.clone().getTeteDeBoeuf());
    }
    
    @Test
    public void testToString() {
    	Carte test1 = new Carte(15);
    	Carte test2 = new Carte(60);
    	Carte test3 = new Carte(77);
    	Carte test4 = new Carte(55);
    	assertEquals(new Carte(37).toString(), String.valueOf(new Carte(37).toString()));
    	assertEquals(test1.toString(), test1.getNumero() + " (" + test1.getTeteDeBoeuf() + ")");
    	assertEquals(test2.toString(), test2.getNumero() + " (" + test2.getTeteDeBoeuf() + ")");
    	assertEquals(test3.toString(), test3.getNumero() + " (" + test3.getTeteDeBoeuf() + ")");
    	assertEquals(test4.toString(), test4.getNumero() + " (" + test4.getTeteDeBoeuf() + ")");
    }
}