/**
 * 
 */
package com.songoo.step;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;

import com.songoo.R;
import com.songoo.*;
import com.songoo.controls.*;
import com.songoo.listener.OnControlClickListener;
import com.songoo.util.*;
import com.songoo.view.*;

/**
 * @author Martinien
 *
 */
public class TrajetStep extends StepView implements IStepView
{
	private ImageButton _play;
	private ImageButton _back;
	
	private Bitmap _backTrajet;
	
	/**
	 * @param context
	 */
	public TrajetStep(Context context)
	{
		super(context);
		
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public TrajetStep(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		InitStep();
	}
	
	private void InitStep()
	{
		_backTrajet = BitmapLoader.getBitmap(R.drawable.backtrajet000);
		
		_play = new ImageButton(this, "playButton", R.drawable.playfr000, R.drawable.playfr001);
		_play.setPosition(540, Screen.Height - _play.getHeight() - 70);
		_play.setOnClickListener(new OnControlClickListener()
		{	
			public void onClick(Control c)
			{
				((TrajetActivity)_context).PlaySound(1);
				
				StepNext();
			}
		});
		addControl(_play);
		
		_back = new ImageButton(this, "playButton", R.drawable.backfr000, R.drawable.backfr001);
		_back.setPosition(60, Screen.Height - _back.getHeight() - 75);
		_back.setOnClickListener(new OnControlClickListener()
		{	
			public void onClick(Control c)
			{
				((TrajetActivity)_context).PlaySound(1);
				
				StepPrevious();
			}
		});
		addControl(_back);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(_backTrajet, 0, 0, null);
		
		super.onDraw(canvas);
	}

}
