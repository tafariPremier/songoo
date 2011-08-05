/**
 * 
 */
package com.androidchallenge.songoo;

import android.os.Bundle;
import android.view.MotionEvent;

import com.androidchallenge.songoo.activity.*;
import com.androidchallenge.songoo.step.ADCStep;

/**
 * @author Martinien
 *
 */
public class ADCActivity extends StepActivity implements IStepActivity
{

	private ADCStep _adcStep;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
    }

	private void Initialize()
	{
		initializeADCActivity();
	}

	private void initializeADCActivity()
	{
		_adcStep = new ADCStep(this);
		addView(_adcStep);
		
		_adcStep.onStepNext = new Runnable()
		{	
			public void run()
			{
				GotoStep(WelcomeActivity.class);
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
    	
    	_adcStep.Start();
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
