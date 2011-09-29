/**
 * 
 */
package com.songoo;

import android.os.Bundle;
import android.view.MotionEvent;

import com.songoo.activity.*;
import com.songoo.step.*;

/**
 * @author Martinien
 *
 */
public class LoadingActivity extends StepActivity implements IStepActivity
{
	private LoadingStep _loadingStep;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
    }

	private void Initialize()
	{
		initializeLoadingStep();
	}

	private void initializeLoadingStep()
	{
		_loadingStep = new LoadingStep(this);
		addView(_loadingStep);
		
        _loadingStep.onStepNext = new Runnable()
        {
			public void run()
			{
				GotoStep(MenuActivity.class);
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
    	
    	_loadingStep.Start();
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
