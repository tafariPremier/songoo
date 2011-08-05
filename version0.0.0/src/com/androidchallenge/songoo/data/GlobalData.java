/**
 * 
 */
package com.androidchallenge.songoo.data;

import java.util.HashMap;

import com.androidchallenge.songoo.MainActivity;
import com.androidchallenge.songoo.activity.*;
import com.androidchallenge.songoo.controls.*;
import com.androidchallenge.songoo.fonts.*;

import android.graphics.*;
import android.media.*;

/**
 * @author Martinien
 *
 */
public class GlobalData extends BaseData
{
	public MediaPlayer[] mediaPlayers;
	public HashMap<Integer, Bitmap> bitmapResources;
	
	public HashMap<String, Typeface> fonts;
	
	public StepActivity currentActivity;
	public MainActivity mainActivity;
	
	public HashMap<String, Font> fontsBitmap;
	
	public int volume;
	
	/**
	 * 
	 */
	public GlobalData()
	{
		mediaPlayers = new MediaPlayer[5];
		bitmapResources = new HashMap<Integer, Bitmap>();
		fonts = new HashMap<String, Typeface>();
		fontsBitmap = new HashMap<String, Font>();
		
		currentActivity = null;
		mainActivity = null;
		
		volume = VolumeControl.VOLUMEMAX;
	}

	public void Release()
	{
		super.Release();
		
		for (MediaPlayer mediaPlayer : mediaPlayers)
		{
			if (mediaPlayer != null)
			{
				mediaPlayer.release();
				mediaPlayer = null;
			} 
		}
	}

}
