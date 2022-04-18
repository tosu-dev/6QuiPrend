package Tests;

import static org.junit.Assert.*;

import Cartes.Carte;
import org.junit.Test;

import Jeu.Jeu;

public class JeuTest {
	
	@Test
	public void testGetNombreJoueurs() {
		Jeu jeu = new Jeu();
		assertTrue(jeu.getNombreJoueurs() > 2 && jeu.getNombreJoueurs() <= 10);
	}

	@Test
	public void testTriJoueurs() {
		Jeu jeu = new Jeu();
		for (int i = 0; i < jeu.getNombreJoueurs(); ++i) {
			jeu.getJoueur(i).setCarteChoisi(new Carte(104-i));
		}
		jeu.triJoueurs();
		for (int i = 0; i < jeu.getNombreJoueurs()-1; ++i) {
			assertTrue(jeu.getJoueur(i).getCarteChoisi().getNumero() < jeu.getJoueur(i+1).getCarteChoisi().getNumero());
		}
	}

}
