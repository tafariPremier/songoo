/**
 * 
 */
package com.androidchallenge.songoo.step;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.androidchallenge.songoo.R;
import com.androidchallenge.songoo.ADCActivity;
import com.androidchallenge.songoo.util.Functions;
import com.androidchallenge.songoo.view.IStepView;
import com.androidchallenge.songoo.view.StepView;

/**
 * @author Martinien
 *
 */
public class ADCStep extends StepView implements IStepView
{

	private Bitmap _adcResource;
	
	/**
	 * @param context
	 */
	public ADCStep(Context context)
	{
		super(context);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ADCStep(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	/*public ADCStep(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		InitStep();
	}*/

	private void InitStep()
	{
		((ADCActivity)_context).LoadBitmap(R.drawable.mobile001);
	}
	
	public void Start()
	{
		super.Start();
		
		loadSounds();
		
		_advancedThreadFactory.newThread(new Runnable()
		{	
			public void run()
			{
				Functions.delay(2000);
				StepNext();
			}
		}).start();
		
		invalidate();
		
		((ADCActivity)_context).PlaySound(0);
	}
	
	private void loadSounds()
	{
		((ADCActivity)_context).LoadSound(0, R.raw.intro);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawRGB(255, 255, 255);
		
		_adcResource = ((ADCActivity)_context).GetBitmap(R.drawable.mobile001);
		
		int x = (canvas.getWidth() - _adcResource.getWidth()) / 2;
		int y = (canvas.getHeight() - _adcResource.getHeight()) / 2;
		canvas.drawBitmap(_adcResource, x, y, null);
		
		super.onDraw(canvas);
	}

}
