/**
 * 
 */
package com.songoo.gameplay;

import java.util.LinkedList;

/**
 * @author Martinien
 *
 */
public class BackGame
{
	private Tableau _tableau;
	private LinkedList<Tableau> _pile;
	
	/**
	 * 
	 */
	public BackGame()
	{
		Init();
	}

	public void Init()
	{
		initStart();
	}
	
	private void initStart()
	{
		_tableau = new Tableau();
		_pile = new LinkedList<Tableau>();
	}
	
	public Tableau getTableau()
	{
		return _tableau;
	}
	
	public void Reset()
	{
		initStart();
		
		_pile.clear();
	}
	
	public void Undo()
	{
		if (!_pile.isEmpty())
		{
			_tableau = _pile.removeFirst();
		}
	}
	
	public int JeuCase(int numCase)
	{
		if (_tableau.getPosition() == 1)
		{
			Tableau tabTampon = _tableau.recopier();
			_pile.addFirst(tabTampon);
		}
		
		int jeu = _tableau.jouer(numCase);
		
		
		int test = testFinPartie();
		if (test != 0)
		{
			return test;
		}
		else
		{
			if (jeu == 1)
			{
				// possibilité de jeu
				return 1;
			}
			else if (jeu == 2)
			{
				// adversaire affamé, à nourrir
				return 2;
			}
			else if (jeu == 3)
			{
				// adversaire affamé, impossible de le nourrir
				_tableau.isEndGame();
				return 3;
			}
		}
		
		// coup impossible
		return 0;
	}
	
	private int testFinPartie()
	{
		if ((_tableau.nbreGraines() < 7) || (_tableau.getScore(0) > 24) || (_tableau.getScore(1) > 24))
		{
			// redistribution des graines
			_tableau.isEndGame();
			
			if (_tableau.getScore(0) < _tableau.getScore(1))
			{
				// gagnant : joueur 2
				return 4;
			}
			else if (_tableau.getScore(0) > _tableau.getScore(1))
			{
				// gagnant : joueur 1
				return 5;
			}
			else
			{
				// gagnant : jeu nul
				return 6;
			}
		}
		
		// pas de fin de partie
		return 0;
	}
}
