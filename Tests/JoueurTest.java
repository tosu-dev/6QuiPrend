package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Cartes.Carte;
import Joueurs.Joueur;

public class JoueurTest {
	
	@Test
	public void testGetNom() {
		Joueur j = new Joueur("Linki");
		
		assertEquals(j.getNom(), "Linki");
	}
	
	@Test
	public void testGetTeteBoeuf() {
		Joueur j = new Joueur("Béboo");
		
		assertEquals(j.getTeteBoeuf(), 0);
		j.ajouterTeteBoeuf(7);
		assertEquals(j.getTeteBoeuf(), 7);
	}
	
	@Test
	public void testGetCartes() {
		Joueur j = new Joueur("Bebou");
		ArrayList<Carte> c = new ArrayList<>();
		
		assertEquals(j.getCartes(), c);
		for (int i = 0; i < 10; i++) {
			j.getCartes().add(new Carte(42+i));
			c.add(new Carte(42+i));
			assertEquals(j.getCartes().get(i).getNumero(), c.get(i).getNumero());
		}
	}
	
	@Test
	public void testEstVide() {
		Joueur j = new Joueur("Bebow");
		
		assertTrue(j.estVide());
		j.getCartes().add(new Carte(42));
		assertFalse(j.estVide());
	}
	
	@Test
	public void testClone() {
		Joueur j1 = new Joueur("UwU");
		Joueur j2;
		
		for (int i = 0; i < 5; i++) {
			j1.getCartes().add(new Carte(87+i));
		}
		
		j2 = j1.clone();
		
		assertEquals(j1.getNom(), j2.getNom());
		assertEquals(j1.getCartes().get(3).getNumero(), j1.getCartes().get(3).getNumero());
	}
	
	@Test
	public void testToString() {
		Joueur j = new Joueur("-_-");
		
		assertEquals(j.toString(), "-_-");
	}
}
