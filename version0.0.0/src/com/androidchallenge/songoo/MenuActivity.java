/**
 * 
 */
package com.androidchallenge.songoo;

import android.os.Bundle;
import android.view.MotionEvent;

import com.androidchallenge.songoo.activity.*;
import com.androidchallenge.songoo.step.*;

/**
 * @author Martinien
 *
 */
public class MenuActivity extends StepActivity implements IStepActivity
{
	private MenuStep _menuStep;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
    }

	private void Initialize()
	{
		initializeMenuActivity();
	}

	private void initializeMenuActivity()
	{
		_menuStep = new MenuStep(this);
		addView(_menuStep);
		
		_menuStep.onStepNext = new Runnable()
		{	
			public void run()
			{
				GotoStep(TrajetActivity.class);
			}
		};
		
		_menuStep.onStepPrevious = new Runnable()
		{
			public void run()
			{
				//GotoStep(_menuStep, _loadingStep);
			}
		};
	}
	
	public void exitApplication()
	{
		finish();
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
    	
    	_menuStep.Start();
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
    
    // --------------------------------------------------------------
}
