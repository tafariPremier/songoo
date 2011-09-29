/**
 * 
 */
package com.songoo.gameplay;

/**
 * @author tnjulius
 *
 */
public class Trou 
{
	//private Pion[] pions;
	
	private int nbreGraines;
	private int num;		//numero du trou
	private int position;
	
	
	public Trou() 
	{
		// TODO Auto-generated constructor stub
		this.num=0;
		this.nbreGraines=4;
	}
	
	public Trou(int num, int nbreGraines, int position)
	{ //initinialisation des cases au debut du jeu
		this.num=num;
		this.nbreGraines=nbreGraines;
		this.position=position;
	}
	
	public boolean isEmpty() //Teste si la case est vide
	{ 			
		return this.getNbreGraines()==0;
	}
	
	public void ajouteGraines()
	{
		this.nbreGraines++;
	}
	
	public int ramasseGraines()
	{ 								//quand on ramasse des Graines
		int n=this.nbreGraines;		//il ne reste plus rien dans la case
		this.nbreGraines=0;
		return n;					//on retourne le nombre de Graines ramassé
	}
	
	public int getNbreGraines()
	{		
		return this.nbreGraines;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void setNbreGraines(int nbreGraines)
	{
		this.nbreGraines=nbreGraines;
	}
	
	public Trou copie()
	{
		Trou c = new Trou();
		c.position=this.position;
		c.nbreGraines=this.nbreGraines;
		c.num=this.num;
		
		return c;
	}
}
