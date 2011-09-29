/**
 * 
 */
package com.songoo;

import android.os.Bundle;
import android.view.MotionEvent;

import com.songoo.activity.IStepActivity;
import com.songoo.activity.StepActivity;
import com.songoo.step.TrajetStep;

/**
 * @author Martinien
 *
 */
public class TrajetActivity extends StepActivity implements IStepActivity
{
	private TrajetStep _trajetStep;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        Initialize();
    }

	private void Initialize()
	{
		initializeTrajetActivity();
	}

	private void initializeTrajetActivity()
	{
		_trajetStep = new TrajetStep(this);
		addView(_trajetStep);
		
		_trajetStep.onStepNext = new Runnable()
		{	
			public void run()
			{
				GotoStep(GameActivity.class);
			}
		};
		
		_trajetStep.onStepPrevious = new Runnable()
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
    	
    	_trajetStep.Start();
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
