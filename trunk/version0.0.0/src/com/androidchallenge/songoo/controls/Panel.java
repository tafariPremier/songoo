/**
 * 
 */
package com.androidchallenge.songoo.controls;

import com.androidchallenge.songoo.util.BitmapLoader;

import android.graphics.*;

/**
 * @author Martinien
 *
 */
public class Panel extends Control
{
	Bitmap _backgroundBitmap;

	/**
	 * @param parent
	 * @param name
	 */
	public Panel(Object parent, String name)
	{
		super(parent, name);

		Init();
	}

	private void Init()
	{
		//
	}
	
	public void setBackgroundPicture(int res)
	{
		_backgroundBitmap = BitmapLoader.getBitmap(res);
		
		if (_backgroundBitmap != null)
		{
			setSize(_backgroundBitmap.getWidth(), _backgroundBitmap.getHeight());
		}
	}
	
	public void setBackgroundPicture(Bitmap bitmap)
	{
		_backgroundBitmap = bitmap;
		
		if (_backgroundBitmap != null)
		{
			setSize(_backgroundBitmap.getWidth(), _backgroundBitmap.getHeight());
		}
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(_backgroundBitmap, getViewLeft(), getViewTop(), null);
		
		
		super.draw(canvas);
	}
}
