/**
 * 
 */
package com.songoo.controls;

import com.songoo.gameplay.*;
import com.songoo.util.*;

import android.graphics.*;
import android.view.MotionEvent;

/**
 * @author Martinien
 *
 */
public class PionControl extends Control
{
	private Bitmap _imagePion;
	private Pion _pion;
	
	/**
	 * @param parent
	 * @param name
	 */
	public PionControl(Object parent, String name)
	{
		super(parent, name);
		
		Init();
	}
	
	private void Init()
	{
		//
	}
	
	public void setImage(int res)
	{
		_imagePion = BitmapLoader.getBitmap(res);
		
		if (_imagePion != null)
		{
			setSize(_imagePion.getWidth(), _imagePion.getHeight());
		}
	}
	
	public void setPion(Pion p)
	{
		_pion = p;
	}
	
	public Pion getPion()
	{
		return _pion;
	}

	@Override
	public void draw(Canvas canvas)
	{
		if (_imagePion != null)
		{
			canvas.drawBitmap(_imagePion, getViewLeft(), getViewTop(), null);
		}
		
		super.draw(canvas);
	}
	
	@Override
	public Boolean onClick(Control control, MotionEvent event)
	{
		setVisibility(Control.INVISIBLE);
		
		return super.onClick(control, event);
	}
}
