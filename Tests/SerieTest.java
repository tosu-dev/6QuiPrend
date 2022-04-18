package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Cartes.Carte;
import Cartes.Serie;

public class SerieTest {
	
	@Test
	public void testGetSerie() {
		Serie s = new Serie();
		ArrayList<Carte> test = new ArrayList<>();
		
		assertEquals(s.getSerie(), new ArrayList<Carte>());
		for (int i = 0; i < s.getNbCartesMax(); i++) {
			s.ajouterCarte(new Carte(i));
			test.add(new Carte(i));
			assertEquals(s.getSerie().get(i).getNumero(), test.get(i).getNumero());
		}
	}
	
	@Test
	public void testGetCarte() {
		Serie s = new Serie();
		ArrayList<Carte> test = new ArrayList<>();
		
		for (int i = 0; i < s.getNbCartesMax(); i++) {
			s.ajouterCarte(new Carte(i));
			test.add(new Carte(i));
			assertEquals(s.getSerie().get(i).getNumero(), test.get(i).getNumero());
		}
	}
	
	@Test
	public void testGetNbCarteMax() {
		Serie s = new Serie();
		
		assertEquals(s.getNbCartesMax(), 5);
	}
	
	@Test 
	public void testGetDerniereCarte() {
		Serie s = new Serie();
		Carte c = new Carte(17);
		
		for (int i = 0; i < s.getNbCartesMax()-1; i++) {
			s.ajouterCarte(new Carte(i));
		}
		s.ajouterCarte(c);
		assertEquals(s.getDerniereCarte().getNumero(), c.getNumero());
	}
	
	@Test
	public void testAjouterCarte() {
		Serie s = new Serie();
		
		for (int i = 0; i < s.getNbCartesMax(); i++) {
			s.ajouterCarte(new Carte(i));
			assertEquals(s.getSerie().get(i).getNumero(), new Carte(i).getNumero());
		}
	}
	
	@Test
	public void testVider() {
		Serie s = new Serie();
		
		for (int i = 0; i < s.getNbCartesMax(); i++) {
			s.ajouterCarte(new Carte(i));
		}
		assertEquals(s.getSerie().size(), s.getNbCartesMax());
		for (int i = 0; i < s.getNbCartesMax()-1; i++) {
			s.vider();
		}
		assertEquals(s.getSerie().size(), 0);
	}
	
	@Test
	public void testToString() {
		Serie s = new Serie();
		
		s.ajouterCarte(new Carte(54));
		assertEquals(s.toString(), s.getSerie().get(0).toString());
		s.ajouterCarte(new Carte(77));
		assertEquals(s.toString(), s.getSerie().get(0).toString() + ", " + s.getSerie().get(1).toString());
		s.ajouterCarte(new Carte(55));
		s.ajouterCarte(new Carte(17));
		s.ajouterCarte(new Carte(20));
		assertEquals(s.toString(), s.getSerie().get(0).toString() + ", " + s.getSerie().get(1).toString() + ", " + 
		s.getSerie().get(2).toString() + ", " + s.getSerie().get(3).toString() + ", " + s.getSerie().get(4).toString());
	}
}
