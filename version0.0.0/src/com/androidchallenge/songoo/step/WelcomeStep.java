/**
 * 
 */
package com.androidchallenge.songoo.step;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;

import com.androidchallenge.songoo.*;
import com.androidchallenge.songoo.util.*;
import com.androidchallenge.songoo.view.*;

/**
 * @author Martinien
 *
 */
public class WelcomeStep extends StepView implements IStepView
{
	private Bitmap _otResource;
	
	/**
	 * @param context
	 */
	public WelcomeStep(Context context)
	{
		super(context);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public WelcomeStep(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	/*public WelcomeStep(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		InitStep();
	}*/

	private void InitStep()
	{
		((WelcomeActivity)_context).LoadBitmap(R.drawable.olymattnjuliussovo000);
	}
	
	public void Start()
	{
		super.Start();
		
		_advancedThreadFactory.newThread(new Runnable()
		{	
			public void run()
			{
				Functions.delay(3000);
				StepNext();
			}
		}).start();
		
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawRGB(255, 255, 255);
		
		_otResource = ((WelcomeActivity)_context).GetBitmap(R.drawable.olymattnjuliussovo000);
		
		int x = (canvas.getWidth() - _otResource.getWidth()) / 2;
		int y = (canvas.getHeight() - _otResource.getHeight()) / 2;
		canvas.drawBitmap(_otResource, x, y, null);
		
		super.onDraw(canvas);
	}
}
