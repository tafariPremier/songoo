/**
 * 
 */
package com.songoo.step;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.songoo.R;
import com.songoo.SponsorActivity;
import com.songoo.util.Functions;
import com.songoo.view.IStepView;
import com.songoo.view.StepView;

/**
 * @author Martinien
 *
 */
public class SponsorStep extends StepView implements IStepView
{

	private Bitmap _adcResource;
	
	/**
	 * @param context
	 */
	public SponsorStep(Context context)
	{
		super(context);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SponsorStep(Context context, AttributeSet attrs)
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
		((SponsorActivity)_context).LoadBitmap(R.drawable.barcamp001);
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
		
		((SponsorActivity)_context).PlaySound(0);
	}
	
	private void loadSounds()
	{
		((SponsorActivity)_context).LoadSound(0, R.raw.intro);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawRGB(255, 255, 255);
		
		_adcResource = ((SponsorActivity)_context).GetBitmap(R.drawable.barcamp001);
		
		int x = (canvas.getWidth() - _adcResource.getWidth()) / 2;
		int y = (canvas.getHeight() - _adcResource.getHeight()) / 2;
		canvas.drawBitmap(_adcResource, x, y, null);
		
		super.onDraw(canvas);
	}

}
