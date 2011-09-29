/**
 * 
 */
package com.songoo.activity;


import com.songoo.R;
import com.songoo.data.SessionData;
import com.songoo.util.*;

import android.app.Activity;
import android.graphics.*;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.*;
import android.widget.LinearLayout;

/**
 * @author Martinien
 *
 */
public class StepActivity extends Activity implements IStepActivity
{
	private LinearLayout _layout;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        _layout = new LinearLayout(this);
        _layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        
        setContentView(_layout);
        
        SuperInitialize();
    }
	
	public void addView(View view)
	{
		_layout.addView(view);
	}

	private void SuperInitialize()
	{
		BitmapLoader.setContext(this);
		
		SessionData.GlobalData.currentActivity = this;
	}

	@Override
    public boolean onTouchEvent(MotionEvent event)
    {
    	return super.onTouchEvent(event);
    }
	
	// --------------------------------------------------------------
    
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    }
    
    @Override
    protected void onStart()
    {
    	super.onStart();
    }
    
    @Override
    protected void onStop()
    {
    	super.onStop();
    	
    	Activity current = SessionData.GlobalData.currentActivity;
    	if ((current == null) || (current.isFinishing()))
    	{
    		SessionData.GlobalData.mainActivity.finish();
    	}
    }
    
    @Override
    protected void onRestart()
    {
    	super.onRestart();
    }
    
    @Override
    protected void onPause()
    {
    	super.onPause();
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
    	super.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	super.onRestoreInstanceState(savedInstanceState);
    }
    
    // --------------------------------------------------------------

    public void LoadSound(int player, int resource)
	{
		SessionData.GlobalData.mediaPlayers[player] = MediaPlayer.create(this, resource);
		SessionData.GlobalData.mediaResources[player] = resource;
	}

	public void PlaySound(int player)
	{
		if (SessionData.GlobalData.mediaPlayers[player] != null) 
		{
			SessionData.GlobalData.mediaPlayers[player].start();
		}
	}
	
	public void PlaySoundNewPlayer(int player)
	{
		if (SessionData.GlobalData.mediaPlayers[player] != null) 
		{
			MediaPlayer mediaPlayer = MediaPlayer.create(this, SessionData.GlobalData.mediaResources[player]);
			mediaPlayer.start();
		}
	}
	
	public void PlaySound(int player, int resource)
	{
		SessionData.GlobalData.mediaPlayers[player] = MediaPlayer.create(this, resource);
		SessionData.GlobalData.mediaPlayers[player].start();
	}

	public void StopSound(int player)
	{
		if (SessionData.GlobalData.mediaPlayers[player] != null)
		{
			SessionData.GlobalData.mediaPlayers[player].stop();
		}
	}

	public void ReleaseSound(int player)
	{
		if (SessionData.GlobalData.mediaPlayers[player] != null)
		{
			SessionData.GlobalData.mediaPlayers[player].release();
			SessionData.GlobalData.mediaPlayers[player] = null;
		}
	}
	
	
	public void LoadBitmap(int resource)
	{
		SessionData.GlobalData.bitmapResources.put(resource, BitmapLoader.getBitmap(resource));
	}
	
	public Bitmap GetBitmap(int resource)
	{
		return SessionData.GlobalData.bitmapResources.get(resource);
	}
	
	public void Start()
	{
		//
	}
	
	public void Stop()
	{
		finish();
		overridePendingTransition(R.anim.step_enter, R.anim.step_exit);
	}
	
	public void GotoStep(Class<?> step)
	{
		SessionData.GlobalData.mainActivity.TransfertToActivity(step);
	}
}
