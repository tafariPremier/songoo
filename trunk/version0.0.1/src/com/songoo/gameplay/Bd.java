package com.songoo.gameplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class Bd extends SQLiteOpenHelper {

	/*private static final String TABLE_PARTIE= "table_partie";
	private static final String COLONNE_ID="id";
	private static final String COLONNE_JOUEUR="nom_joueur";*/
	//private static final String COLONNE_TABLEAU="id";
	
	private static final String REQUETE_CREATION_BD = 	"create table partie " +
			"											(id integer	primary key autoincrement," +
														"nom_joueur	varchar(50) not null" +
														"score_0	int			not null" +
														"score_1	int			not null" +
														"case_2		int			not null" +
														"case_3		int			not null" +
														"case_4		int			not null" +
														"case_5		int			not null" +
														"case_6		int			not null" +
														"case_7		int			not null" +
														"case_8		int			not null" +
														"case_9		int			not null" +
														"case_10	int			not null" +
														"case_11	int			not null" +
														"player		int			not null";
														
	
	
	public Bd(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Bd.REQUETE_CREATION_BD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
}