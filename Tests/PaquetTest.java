package Tests;

import Cartes.Paquet;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaquetTest {
	
	private Paquet p = new Paquet();

    @Test
    public void testGetNombreCartes() {
        assertEquals(104, p.getNombreCartes());
    }

    @Test
    public void testGetDerniereCarte() {
        assertEquals(104, p.enlever().getNumero());
        assertEquals(103, p.enlever().getNumero());
        assertEquals(102, p.getDerniereCarte().getNumero());
    }

    @Test
    public void testEstVide() {
        while(!p.estVide()) {
            p.enlever();
        }
        assertEquals(0, p.getNombreCartes());
    }
    
    @Test
    public void testEnlever() {
    	Paquet p = new Paquet();
    	
    	assertEquals(104, p.getNombreCartes());
    	for (int i = 0; i < 42; i++) {
    		p.enlever();
    	}
    	assertEquals(62, p.getNombreCartes());
    }
}