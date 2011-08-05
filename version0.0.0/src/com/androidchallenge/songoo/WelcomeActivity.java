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
public class WelcomeActivity extends StepActivity implements IStepActivity
{
	private WelcomeStep _welcomeStep;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
    }

	private void Initialize()
	{
		initializeWelcomeActivity();
	}

	private void initializeWelcomeActivity()
	{
		_welcomeStep = new WelcomeStep(this);
		addView(_welcomeStep);
		
		_welcomeStep.onStepNext = new Runnable()
		{	
			public void run()
			{
				GotoStep(LoadingActivity.class);
			}
		};
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
    	
    	_welcomeStep.Start();
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
