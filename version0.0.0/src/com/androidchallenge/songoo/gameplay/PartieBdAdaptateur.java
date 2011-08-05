package com.androidchallenge.songoo.gameplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class PartieBdAdaptateur {
	private static final int BASE_VERSION = 1;
	private static final String BASE_NOM = "songo.db";
	private static final String TABLE_PARTIE = "partie";

	//private static final String COLONNE_ID = "id";
	private static final int ID_ID = 0;
	//private static final int NOM_JOUEUR_ID = 1;
	private static final int SCORE_0_ID = 2;	
	private static final int SCORE_1_ID = 3;
	/*private static final int CASE_0_ID=4;
	private static final int CASE_1_ID=5;
	private static final int CASE_2_ID=6;
	private static final int CASE_3_ID=7;
	private static final int CASE_4_ID=8;
	private static final int CASE_5_ID=9;
	private static final int CASE_6_ID=10;
	private static final int CASE_7_ID=11;
	private static final int CASE_8_ID=12;
	private static final int CASE_9_ID=13;
	private static final int CASE_10_ID=14;
	private static final int CASE_11_ID=15;*/
	private static final int PLAYER=16;
	
	
	// La requête de création de la structure de la base de données.
	
	private static final String REQUETE_CREATION_BD = 	"create table partie " +
												"(" +
												"id 		integer	primary	key autoincrement," +
												"nom_joueur	varchar(50) 	not null" +
												"score_0	integer			not null" +
												"score_1	integer			not null" +
												"case_0		integer			not null" +
												"case_1		integer			not null" +
												"case_2		integer			not null" +
												"case_3		integer			not null" +
												"case_4		integer			not null" +
												"case_5		integer			not null" +
												"case_6		integer			not null" +
												"case_7		integer			not null" +
												"case_8		integer			not null" +
												"case_9		integer			not null" +
												"case_10	integer			not null" +
												"case_11	integer			not null" +
												"player		integer			not null);";
	
	
	// L’instance de la base qui sera manipulée au travers de cette classe.
	private SQLiteDatabase maBaseDonnees;
	private MaBdOpenHelper baseHelper;
	
	public PartieBdAdaptateur(Context ctx)
	{
		baseHelper = new MaBdOpenHelper(ctx, BASE_NOM, null, BASE_VERSION);
	}
	
	/**
	* Ouvre la base de données en écriture.
	*/
	public SQLiteDatabase open()
	{
		maBaseDonnees = baseHelper.getWritableDatabase();
		return maBaseDonnees;
	}
	
	/**
	* Ferme la base de données.
	*/
	public void close()
	{
		maBaseDonnees.close();
	}
	
	public SQLiteDatabase getBaseDonnees()
	{
		return maBaseDonnees;
	}
	
	public Tableau getPartie(int id)
	{
		Cursor c = maBaseDonnees.query(TABLE_PARTIE, new String[] {
				"nom_joueur","score_0", "score_1", "case_0","case_1","case_2","case_3","case_4","case_5","case_6","case_7", "case_8", "case_9", "case_10", "case_11"}, 
				null, null, null,
				"id=" + id, null);
				return cursorToPartie(c);
	}
	
	
	public boolean removePartie(int id)
	{
		if(maBaseDonnees.delete(TABLE_PARTIE, "id="+id, null)!=0)
			return true;
		return false;
	}
	
	public boolean viderPartie(int i)
	{
		if(maBaseDonnees.delete(TABLE_PARTIE, "1", null)!=0)
			return true;
		return false;
	}
	
	public long insertTableau(Tableau tab)
	{
		ContentValues valeurs = new ContentValues();
		
		//valeurs.put("nom_joueur", tab.getNomJoueurs(0));
		
		valeurs.put("score_0", tab.getScore(0));
		valeurs.put("score_1", tab.getScore(1));
		
		valeurs.put("case_0", tab.trou[0].getNbreGraines());
		valeurs.put("case_1", tab.trou[1].getNbreGraines());
		valeurs.put("case_2", tab.trou[2].getNbreGraines());
		valeurs.put("case_3", tab.trou[3].getNbreGraines());
		valeurs.put("case_4", tab.trou[4].getNbreGraines());
		valeurs.put("case_5", tab.trou[5].getNbreGraines());
		valeurs.put("case_6", tab.trou[6].getNbreGraines());
		valeurs.put("case_7", tab.trou[7].getNbreGraines());
		valeurs.put("case_8", tab.trou[8].getNbreGraines());
		valeurs.put("case_9", tab.trou[9].getNbreGraines());
		valeurs.put("case_10", tab.trou[10].getNbreGraines());
		valeurs.put("case_11", tab.trou[11].getNbreGraines());
		
		valeurs.put("player", tab.getPosition());
		
		return maBaseDonnees.insert(TABLE_PARTIE, null, valeurs);
	}
	
	
	public int updateTableau(int id, Tableau tab)
	{
		ContentValues valeurs = new ContentValues();
		
		//valeurs.put("nom_joueur", tab.getNomJoueurs(0));
		
		valeurs.put("score_0", tab.getScore(0));
		valeurs.put("score_1", tab.getScore(1));
		
		valeurs.put("case_0", tab.trou[0].getNbreGraines());
		valeurs.put("case_1", tab.trou[1].getNbreGraines());
		valeurs.put("case_2", tab.trou[2].getNbreGraines());
		valeurs.put("case_3", tab.trou[3].getNbreGraines());
		valeurs.put("case_4", tab.trou[4].getNbreGraines());
		valeurs.put("case_5", tab.trou[5].getNbreGraines());
		valeurs.put("case_6", tab.trou[6].getNbreGraines());
		valeurs.put("case_7", tab.trou[7].getNbreGraines());
		valeurs.put("case_8", tab.trou[8].getNbreGraines());
		valeurs.put("case_9", tab.trou[9].getNbreGraines());
		valeurs.put("case_10", tab.trou[10].getNbreGraines());
		valeurs.put("case_11", tab.trou[11].getNbreGraines());
		
		valeurs.put("player", tab.getPosition());
		
		return maBaseDonnees.update(TABLE_PARTIE, valeurs, "id="+id, null);
	}
	
	private Tableau cursorToPartie(Cursor c) {//transforme un Cursor en objet de type Tableau
		// TODO Auto-generated method stub
		if(c.getCount()==0)
			return null;
		
		Tableau retT = new Tableau();
		
		
		retT.setScore(c.getInt(SCORE_0_ID), c.getInt(SCORE_1_ID));
		retT.setPosition(c.getInt(PLAYER));
		
		retT.trou[0].setNbreGraines(c.getInt(ID_ID));
		
		for(int i=0; i<12;++i)
		{
			retT.trou[i].setNbreGraines(c.getInt(i+4));
		}
		
		c.close(); //fermeture du curseur pour libérer les ressources
		
		return retT;	
	}
	
	
/**						Classe MaBdOpenHelper										*/

	private class MaBdOpenHelper extends SQLiteOpenHelper {

		public MaBdOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}	
}