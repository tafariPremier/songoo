/**
 * 
 */
package com.androidchallenge.songoo.controls;

import com.androidchallenge.songoo.util.*;

import android.graphics.*;
import android.view.MotionEvent;

/**
 * @author Martinien
 *
 */
public class ImageButton extends Control
{
	private Bitmap _image1;
	private Bitmap _image2;

	private Bitmap _current;
	
	/**
	 * @param parent
	 * @param name
	 */
	public ImageButton(Object parent, String name, int upState, int downState)
	{
		super(parent, name);

		Init(upState, downState);
	}

	private void Init(int upState, int downState)
	{
		_image1 = BitmapLoader.getBitmap(upState);
		_image2 = BitmapLoader.getBitmap(downState);
		
		setWidth(Math.max(_image1.getWidth(), _image2.getWidth()));
		setHeight(Math.max(_image1.getHeight(), _image2.getHeight()));
		
		_current = _image1;
	}
	
	@Override
	public void onTouchDown(Control control, MotionEvent event)
	{
		_current = _image2;
		
		super.onTouchDown(control, event);
	}
	
	@Override
	public void onTouchUp(Control control, MotionEvent event)
	{
		_current = _image1;

		super.onTouchUp(control, event);
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(_current, getViewLeft(), getViewTop(), null);
		
		
		super.draw(canvas);
	}
}
