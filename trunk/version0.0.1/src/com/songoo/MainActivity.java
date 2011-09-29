package com.songoo;

import com.songoo.R;
import com.songoo.activity.StepActivity;
import com.songoo.data.*;
import com.songoo.fonts.FontJoeBeckerBig1;
import com.songoo.step.*;
import com.songoo.util.BitmapLoader;

import android.app.Activity;
import android.content.*;
import android.graphics.*;
import android.os.Bundle;
import android.os.Process;



public class MainActivity extends Activity
{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
        
        // on passe au chargement du songo'o
        LoadSongoo();
    }
    
    public void TransfertToActivity(Class<?> clss)
    {
    	StepActivity previousActivity = SessionData.GlobalData.currentActivity;
    	
    	Intent intention = new Intent(this, clss);
    	
    	startActivity(intention);
    	overridePendingTransition(R.anim.step_enter, R.anim.step_exit);
    	
    	if (previousActivity != null) previousActivity.Stop();
    }
    
    private void LoadSongoo()
    {
    	TransfertToActivity(SponsorActivity.class);
	}

	private void Initialize()
    {
		BitmapLoader.setContext(this);
		
    	SessionData.ResetData();
    	
    	SessionData.GlobalData.mainActivity = this;
    	
    	SessionData.GlobalData.fonts.put("JoeBeckerHeavyBold", Typeface.createFromAsset(getAssets(), "JoeBeckerHeavyBold.ttf"));
    	SessionData.GlobalData.fonts.put("JoeBeckerHeavyNormal", Typeface.createFromAsset(getAssets(), "JoeBeckerHeavyNormal.ttf"));
    	
    	SessionData.GlobalData.fontsBitmap.put("JoeBeckerBig1", new FontJoeBeckerBig1());
	}
    
    // --------------------------------------------------------------
    
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    	
    	SessionData.Release();
    	
    	Process.killProcess(Process.myPid());
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
}