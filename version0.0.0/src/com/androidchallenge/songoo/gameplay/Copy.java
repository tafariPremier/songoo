package com.androidchallenge.songoo.gameplay;


import java.util.*;

public class Copy extends LinkedList<Tableau> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Tableau> list = new LinkedList<Tableau> ();
	
	
	public void save(Tableau objet){
		list.addFirst(objet);
	}
	
	public Object get(){
		return (Tableau)list.removeFirst();
	}
	
	public boolean estVide(){
		return list.isEmpty();
	}
	
}
