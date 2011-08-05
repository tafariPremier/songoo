/**
 * 
 */
package com.androidchallenge.songoo.data;

/**
 * @author Martinien
 *
 */
public class SessionData
{
	public static GlobalData GlobalData;
	
	public static WelcomeVSD WelcomeVSD;
	public static LoadingVSD LoadingVSD;
	public static MenuVSD MenuVSD;
	public static TrajetVSD TrajetVSD;
	public static GameVSD GameVSD;
	
	/**
	 * 
	 */
	public static void ResetData()
	{
		GlobalData = new GlobalData();
		
		WelcomeVSD = new WelcomeVSD();
		LoadingVSD = new LoadingVSD();
		MenuVSD = new MenuVSD();
		TrajetVSD = new TrajetVSD();
		GameVSD = new GameVSD();
	}

	public static void Release()
	{
		WelcomeVSD.Release();
		GlobalData.Release();
		LoadingVSD.Release();
		MenuVSD.Release();
		TrajetVSD.Release();
		GameVSD.Release();
	}

}
