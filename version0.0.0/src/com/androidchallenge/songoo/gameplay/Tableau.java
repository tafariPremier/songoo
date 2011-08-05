package com.androidchallenge.songoo.gameplay;


public class Tableau {
	
	private final int N=12; 			//nombre de trou toujours pair
	private final int N_PAR_CASE=4; 	//nombre de Graines par case

	private int[] score={0,0};		
	private int position=1;				//qui joue à présent! par défaut le joueur du bas
	
	public Trou[] trou= new Trou[N];	//tableau de N cases. N/2 de chaque côté (N pair)
	
	public static int meilleur_coup=5;
	
		
	
	public Tableau()			//Constructeur
	{								
		for(int i=0;i<(N/2);++i){
			this.trou[i]=new Trou(i,N_PAR_CASE,1);
		}		
		
		for(int i=(N/2);i<N;++i){
			this.trou[i]=new Trou(i,N_PAR_CASE,2); 
		}
	}
	
/*********** Getter et Setter ********************************/	
	public int getScore(int num)
	{
		return this.score[num];
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public void setScore(int score_0, int score_1)
	{
		this.score[0]=score_0;
		this.score[1]=score_1;
	}
	public void setPosition(int position)
	{
		this.position=position;
	}

/************************************************************/
	
	public int semer(int num)
	{		
		int i=num%N;
		
		int nbre=this.trou[i].ramasseGraines();
	
		while(nbre>0)
		{
			i=(i+1)%N;
			
			this.trou[i].ajouteGraines();
			nbre--;				
		}
		return i;
	}
	
	public boolean recolter(int num)
	{ 													//y'a t-il possibiliter de manger? 
		int player=this.position;
		int other=3-this.position;
		int i1=(other-1)*6;
		
		num=num%N;
						
		int nbre=trou[num].getNbreGraines();
		
		if(nbre==2 || nbre==3) //on mange si on a 2 ou 3 Graines
		{											
			if(num<(i1+6) && num>=i1) //si player et côté other on prend les points
			{ 										
				score[player-1]+=trou[num].ramasseGraines();
				return true;
			}
			/*if(this.position==2 && num<6 && num>=0){
				score[1]+=trou[num].ramasseGraines();
				return true;
			}*/
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param numCase
	 * @return 0 	//si non possibilité de jouer
	 * @return 1	//si possibilité de jouer
	 * @return 2	//si adversaire affamer, possibilité de le nourrir et refus de nourrir
	 * @return 3	//si adversaire affamer et pas de possibilité de le nourrir
	 * 
	 * */
	public int jouer(int num)
	{
		if(this.isOtherAffamer())		//si l'adversaire est affamer
		{	
			if(this.possibiliteNourrir())	//s'il y'a possibilité de le nourrir
			{ 
				return 2;
			}
			else
				return 3;
		}
		
		if(!this.trou[num].isEmpty() && this.position==this.trou[num].getPosition()) 
		{
			int i = this.semer(num);

			int eat=0;							//on empêche le joueur de tchop sur plus de 4 coups
			while(this.recolter(i) && eat<4)
			{
				i--;
				if(i==-1){i=(N-1);}				//module un nombre négatif
				eat++;
			}

			this.setPosition(3-this.getPosition());
			
			return 1;
		}
		
		return 0;
	}
	
	public int nbreGraines() //renvoie le nombre de Graines restant sur le tableau
	{	
		int nbre=0;
		for(int i=0;i<N;++i)
			nbre+=this.trou[i].getNbreGraines();
		return nbre;
	}
	
	public int trouFinal(int num) //le numéro du trou finale si le coup est jouer
	{ 		
		//int num=num;
		int nbre=this.trou[num].getNbreGraines();
		
		for(int i=0;i<nbre;++i)
		{
			num++;
		}
		/**
		 * TODO à 24 Graines trouver une bonne résolution à ce problème
		 */
		if(nbre>11 || nbre>23)
		{
			num++;
		}
		
		return (num%N)+1;
	}
	
	public int isEndGame()
	{		
		if(this.score[0]>24 || this.score[1]>24)
		{
			for(int i=0;i<(N/2);++i)			//à la fin du jeu chacun récupère le nombre de Graines dans son camp
			{
				this.score[0]+=this.trou[i].ramasseGraines();
			}
			
			for(int i=6;i<N;++i)
			{
				this.score[1]+=this.trou[i].ramasseGraines();
			}
			
			return 1;
		}
		return 0;			
	}
	
	
	public int meilleurCoup()
	{
		int player, i1, point;
		int bestCoup=-1;
		
		Tableau t;
		
		player = this.position;
		//other = 3-player;
		i1 = (player-1)*6;
		
		point=this.score[player-1];
		
		for(int i=i1;i<(i1+6);i++)
		{
			t=this.recopier();
			
			int test = t.jouer(i);
			
			if(test==1)
			{
				if(t.score[player-1]>point)
				{
					point=t.score[player-1];
					bestCoup=i;
				}
			}
		}
		
		if(bestCoup==-1)
		{
			int numTrou;
			do 
			{
				numTrou=(int)(Math.random()*6+i1);
			}while(this.trou[numTrou].isEmpty());
			
			return numTrou;
		}
		return bestCoup;
	}
	
	
	public int alphabeta(int profondeur, int alpha, int beta)
	{
		int player=this.position;
		int other=3-player;
		
		int i1=(player-1)*6;
		
		if(profondeur<=0)
		{
			return this.score[player-1]-this.score[other-1];
		}
		//int meilleur_score=-100;
		
		for(int i=i1;i<(i1+6);++i)
		{
			Tableau t=this.recopier();
			
			int test=t.jouer(i);
			if(test==1)
			{
				int point=-(t.alphabeta(profondeur - 1,-beta, -alpha));
				
				if(point>=alpha)
				{
					alpha = point;
					Tableau.meilleur_coup=i;
					if(alpha>=beta)
						break;
				}
			}
		}		
		return alpha;
	}
	
		
	public int minmax(int profondeur) 
	{		
		int player=this.position;
		int other=3-player;
		
		int i1=(player-1)*6;
		
		if(profondeur<=0)
		{
			return this.score[player-1]-this.score[other-1];
		}
		int meilleur_score=-100;
		
		for(int i=i1;i<(i1+6);++i)
		{
			Tableau t=this.recopier();
			
			int test=t.jouer(i);
			if(test==1)
			{
				int point=-(t.minmax(profondeur - 1));
				
				if(point>=meilleur_score)
				{
					meilleur_score = point;
					Tableau.meilleur_coup=i;
				}
			}
		}		
		return meilleur_score;
	} 

	public Tableau recopier() //recopie le tableau
	{ 
		Tableau t = new Tableau();
		 
		for(int i=0;i<(N);++i)
		{
			t.trou[i]=this.trou[i].copie();
		}
		t.score[1]=this.score[1];
		t.score[0]=this.score[0];
		
		t.setPosition(this.position);
		return t;
	}
	
	public boolean isOtherAffamer() //vérifie si l'adversaire n'a pas de Graines
	{	
		int player=this.position;
		int other=3-player;
		
		int i1=(other-1)*6;
		
		for(int i=i1;i<(i1+6);++i)
		{
			if(!this.trou[i].isEmpty())
					return false;
		}
		return true;
	}
	
	public boolean isCoupAffameOther(int numTrou)
	{ 	//Vérifie si le coup du joueur n	'affamme pas l'adversaire
		Tableau copySongo=this.recopier();				//
		
		copySongo.recolter(copySongo.jouer(numTrou));
		
		return copySongo.isOtherAffamer();
	}
	
	
	
	public boolean possibiliteNourrir()		//vérifie si le joueur à la possibilité de nourrir
	{		
		
		int player=this.position;
		//int other=3-player;
		
		int i1=(player-1)*6;
		
		for(int i=i1; i<(i1+6);++i){
			if(this.trouFinal(i)>=((i1+6)%6))
				return true;
		}
		
		return false;
	}	
		
}
