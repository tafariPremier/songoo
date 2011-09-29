/**
 * 
 */
package com.songoo.step;

import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;

import com.songoo.R;
import com.songoo.*;
import com.songoo.util.*;
import com.songoo.view.*;

import android.graphics.*;
import android.media.MediaPlayer;

/**
 * @author Martinien
 *
 */
public class LoadingStep extends StepView implements IStepView
{
	private Bitmap _logoResource;
	
	private Bitmap _loadingResource;
	private Bitmap _loadingResourceL;
	private Bitmap _loadingResourceM;
	private Bitmap _loadingResourceR;
	private Bitmap _loadingResourceText;
	
	private int _loadingPercent;
	private int _alphaLoadingResourceText;
	private int _alphaLoadingResourceTextSens;
	
	private Bitmap _cloudResource0;
	private int _cloud0x;
	private Bitmap _cloudResource1;
	private int _cloud1x;
	
	private MediaPlayer _mediaPlayer;
	
	
	/**
	 * @param context
	 */
	public LoadingStep(Context context)
	{
		super(context);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LoadingStep(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	/*public LoadingStep(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		InitStep();
	}*/

	private void InitStep()
	{
		//
	}
	
	public void Start()
	{
		super.Start();
		
		loadBackground();
		loadCloudAnimation();
		loadLogo();
		loadNowLoadingAnimation();
		
		_timerAnimation.schedule(new cloud0TaskAction(), 0, 500);
		_timerAnimation.schedule(new cloud1TaskAction(), 0, 200);
		_timerAnimation.schedule(new loadingResourceTextTaskAction(), 0, 1);
		
		_advancedThreadFactory.newThread(new Runnable()
		{	
			public void run()
			{
				_loadingPercent = 10;
				
				Functions.delay(2000);
				((LoadingActivity)_context).LoadSound(1, R.raw.clic000);
				
				Functions.delay(3000);
				
				((LoadingActivity)_context).LoadBitmap(R.drawable.menu000);
				_loadingPercent = 100;
				
				Functions.delay(5000);
				StepNext();
			}
		}).start();
		
		invalidate();
	}
	
	class loadingResourceTextTaskAction extends TimerTask
	{
		public void run()
		{
			if (_alphaLoadingResourceText == 255) _alphaLoadingResourceTextSens = -1;
			if (_alphaLoadingResourceText == 100) _alphaLoadingResourceTextSens = 1;
			
			_alphaLoadingResourceText += _alphaLoadingResourceTextSens;
		}
	}
	
	class cloud0TaskAction extends TimerTask
	{
		public void run()
		{
			_cloud0x -= 1;
		}
	}
	
	class cloud1TaskAction extends TimerTask
	{
		public void run()
		{
			_cloud1x -= 1;
		}
	}
	
	public void Stop()
	{
		super.Stop();
		
		if (_mediaPlayer != null)
		{
			_mediaPlayer.release();
			_mediaPlayer = null;
		}
	}

	private void loadNowLoadingAnimation()
	{
		_loadingResource = BitmapLoader.getBitmap(R.drawable.loading000);
		_loadingResourceL = BitmapLoader.getBitmap(R.drawable.loading001);
		_loadingResourceM = BitmapLoader.getBitmap(R.drawable.loading002);
		_loadingResourceR = BitmapLoader.getBitmap(R.drawable.loading003);
		
		_loadingResourceText = BitmapLoader.getBitmap(R.drawable.loading004_fr);
		
		_alphaLoadingResourceText = 255;
	}
	
	private void drawLoadingStatus(Canvas canvas, int percent, int left, int top)
	{
		int largeur = (376 * percent) / 100;
		
		canvas.drawBitmap(_loadingResourceL, left, top, null);
		canvas.drawBitmap(_loadingResourceM, null, new Rect(left + _loadingResourceL.getWidth(), top, left + _loadingResourceL.getWidth() + largeur, top + _loadingResourceM.getHeight()), null);
		canvas.drawBitmap(_loadingResourceR, left + _loadingResourceL.getWidth() + largeur, top, null);
	}

	private void loadLogo()
	{
		_logoResource = BitmapLoader.getBitmap(R.drawable.songoo000);
	}

	private void loadBackground()
	{
		//
	}

	private void loadCloudAnimation()
	{
		/*Drawable _drawableCloudResource0 = context.getResources().getDrawable(R.drawable.cloud000);
		_drawableCloudResource0.setBounds(0, 0, _drawableCloudResource0.getIntrinsicWidth(), _drawableCloudResource0.getIntrinsicHeight());
		
		Animation an0 = new TranslateAnimation(50, -800, 0, 0);
        an0.setDuration(240000);
        an0.setRepeatCount(-1);
        an0.initialize(_drawableCloudResource0.getIntrinsicWidth(), _drawableCloudResource0.getIntrinsicHeight(), 800, 480);
        
        _cloudResource0 = new AnimateDrawable(_drawableCloudResource0, an0);
        an0.startNow();
		
        
        
        Drawable _drawableCloudResource1 = context.getResources().getDrawable(R.drawable.cloud001);
		_drawableCloudResource1.setBounds(0, 0, _drawableCloudResource1.getIntrinsicWidth(), _drawableCloudResource1.getIntrinsicHeight());
		
		Animation an1 = new TranslateAnimation(20, -800, 30, 30);
        an1.setDuration(160000);
        an1.setRepeatCount(-1);
        an1.initialize(_drawableCloudResource1.getIntrinsicWidth(), _drawableCloudResource1.getIntrinsicHeight(), 800, 480);
        
        _cloudResource1 = new AnimateDrawable(_drawableCloudResource1, an1);
        an1.startNow();*/
		
		_cloudResource0 = BitmapLoader.getBitmap(R.drawable.cloud000);
		_cloud0x = 50;
		
		_cloudResource1 = BitmapLoader.getBitmap(R.drawable.cloud001);
		_cloud1x = 20;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// on dessine l'arrière plan
		canvas.drawRGB(212, 232, 252);
		
		canvas.drawBitmap(_cloudResource0, _cloud0x, 0, null);
		canvas.drawBitmap(_cloudResource1, _cloud1x, 3, null);
		//_cloudResource0.draw(canvas);
		//_cloudResource1.draw(canvas);
		
		canvas.drawBitmap(_logoResource, (canvas.getWidth() - _logoResource.getWidth()) / 2, 50, null);
		
		Paint paintLoadingResourceText = new Paint();
		paintLoadingResourceText.setAlpha(_alphaLoadingResourceText);
		canvas.drawBitmap(_loadingResourceText, (canvas.getWidth() - _loadingResourceText.getWidth()) / 2, 315, paintLoadingResourceText);
		
		canvas.drawBitmap(_loadingResource, (canvas.getWidth() - _loadingResource.getWidth()) / 2, 360, null);
		drawLoadingStatus(canvas, _loadingPercent, (canvas.getWidth() - _loadingResource.getWidth()) / 2 + 9, 360 + 9);
		
		
		super.onDraw(canvas);
	}
}
