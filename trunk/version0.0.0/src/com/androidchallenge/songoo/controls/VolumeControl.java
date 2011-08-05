/**
 * 
 */
package com.androidchallenge.songoo.controls;

import com.androidchallenge.songoo.R;
import com.androidchallenge.songoo.util.*;

import android.graphics.*;
import android.view.MotionEvent;

/**
 * @author Martinien
 *
 */
public class VolumeControl extends Control
{
	private Bitmap _volumeResource0;
	private Bitmap _volumeResource1;
	private Bitmap _volumeResource2;
	private Bitmap _volumeResource3;
	private Bitmap _volumeResource4;
	private Bitmap _volumeResource5;
	private Bitmap _volumeResource6;
	
	private int _volume;
	public static final int VOLUMEMAX = 7;
	
	private int _displayedNumber;
	private Bitmap _displayed;
	
	private int alpha;
	
	/**
	 * @param context
	 */
	public VolumeControl(Object parent, String name)
	{
		super(parent, name);
		
		Init();
	}
	

	private void Init()
	{
		_volumeResource0 = BitmapLoader.getBitmap(R.drawable.volume002);
		_volumeResource1 = BitmapLoader.getBitmap(R.drawable.volume003);
		_volumeResource2 = BitmapLoader.getBitmap(R.drawable.volume004);
		_volumeResource3 = BitmapLoader.getBitmap(R.drawable.volume005);
		_volumeResource4 = BitmapLoader.getBitmap(R.drawable.volume006);
		_volumeResource5 = BitmapLoader.getBitmap(R.drawable.volume007);
		_volumeResource6 = BitmapLoader.getBitmap(R.drawable.volume008);
		
		_displayed = _volumeResource0;
		_displayedNumber = 0;
		
		_volume = VOLUMEMAX;
		
		setWidth(_volumeResource0.getWidth());
		setHeight(_volumeResource0.getHeight());
		
		alpha = 180;
	}
	
	@Override
	public Boolean onClick(Control control, MotionEvent event)
	{
		switch (_displayedNumber)
		{
			case 0:
			{
				_displayedNumber = 1;
				_displayed = _volumeResource1;
				_volume = 1;
				break;
			}
			
			case 1:
			{
				_displayedNumber = 2;
				_displayed = _volumeResource2;
				_volume = 2;
				break;
			}
			
			case 2:
			{
				_displayedNumber = 3;
				_displayed = _volumeResource3;
				_volume = 3;
				break;
			}
			
			case 3:
			{
				_displayedNumber = 4;
				_displayed = _volumeResource4;
				_volume = 4;
				break;
			}
			
			case 4:
			{
				_displayedNumber = 5;
				_displayed = _volumeResource5;
				_volume = 5;
				break;
			}
			
			case 5:
			{
				_displayedNumber = 6;
				_displayed = _volumeResource6;
				_volume = 6;
				break;
			}
			
			case 6:
			{
				_displayedNumber = 0;
				_displayed = _volumeResource0;
				_volume = 7;
				break;
			}
		}

		return super.onClick(control, event);
	}

	@Override
	public void draw(Canvas canvas)
	{
		if (_displayed != null)
		{
			Paint paintVolumeResource0 = new Paint();
			paintVolumeResource0.setAlpha(alpha);
			canvas.drawBitmap(_displayed, getViewLeft(), getViewTop(), paintVolumeResource0);
		}
		
		super.draw(canvas);
	}


	public void setVolume(int volume)
	{
		_volume = volume;
		
		switch(volume)
		{
			case 1:
			{
				_displayedNumber = 1;
				_displayed = _volumeResource1;
				break;
			}
			
			case 2:
			{
				_displayedNumber = 2;
				_displayed = _volumeResource2;
				break;
			}
			
			case 3:
			{
				_displayedNumber = 3;
				_displayed = _volumeResource3;
				break;
			}
			
			case 4:
			{
				_displayedNumber = 4;
				_displayed = _volumeResource4;
				break;
			}
		
			case 5:
			{
				_displayedNumber = 5;
				_displayed = _volumeResource5;
				break;
			}
		
			case 6:
			{
				_displayedNumber = 6;
				_displayed = _volumeResource6;
				break;
			}
			
			case 7:
			{
				_displayedNumber = 0;
				_displayed = _volumeResource0;
				break;
			}
		}
	}
	
	public int getVolume()
	{
		return _volume;
	}
}
