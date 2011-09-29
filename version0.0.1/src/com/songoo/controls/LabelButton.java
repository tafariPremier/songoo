/**
 * 
 */
package com.songoo.controls;

import com.songoo.data.SessionData;
import com.songoo.util.Screen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

/**
 * @author Martinien
 *
 */
public class LabelButton extends Control
{
	private String _text;
	
	public static final int STYLE1 = 0;
	public static final int STYLE2 = 1;
	public static final int STYLE3 = 2;
	
	private int _style;
	private float _size;
	private float _nsize;
	
	/**
	 * @param parent
	 * @param name
	 */
	public LabelButton(Object parent, String name)
	{
		super(parent, name);
		
		Init();
	}
	
	private void Init()
	{
		_text = "";
		_size = 10;
		_nsize = 72;
	}
	
	public void setSize(float size)
	{
		_size = size;
	}
	
	public float getSize()
	{
		return _size;
	}
	
	public void setText(String text)
	{
		_text = text;
	}
	
	public String getText()
	{
		return _text;
	}
	
	public void drawStyle1(Canvas canvas)
	{
		Paint paint = new Paint();
		
		paint.setAntiAlias(true);
		paint.setTextSize(_size);
		paint.setTypeface(SessionData.GlobalData.fonts.get("JoeBeckerHeavyBold"));
		
		
		paint.setColor(Color.rgb(255, 174, 1));
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setStrokeWidth(9 * _size / _nsize);
		canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		
		paint.setColor(Color.rgb(141, 60, 17));
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setStrokeWidth(5 * _size / _nsize);
		canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		
		paint.setColor(Color.rgb(38, 25, 18));
		paint.setStyle(Style.FILL);
		paint.setStrokeWidth(0);
		canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		
		paint.setColor(Color.rgb(25, 18, 13));
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(3 * _size / _nsize);
		canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		
		
		Path pathCanvas = new Path();
		pathCanvas.addRect(0, 0, Screen.Width-1, Screen.Height-1, null);
		
		Path pathText = new Path();
		paint.getTextPath(_text, 1, _text.length(), getViewLeft(), getViewTop(), pathText);
		canvas.clipPath(pathText);
		
		paint.setColor(Color.rgb(255, 255, 255));
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10 * _size / _nsize);
		canvas.drawText(_text, getViewLeft() + 10, getViewTop() + 10, paint);
		
		canvas.clipPath(pathCanvas);
	}
	
	public void drawStyle2(Canvas canvas)
	{
		//
	}
	
	public void drawStyle3(Canvas canvas)
	{
		//
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		switch(_style)
		{
			case STYLE1:
			{
				drawStyle1(canvas);
				break;
			}
			
			case STYLE2:
			{
				drawStyle2(canvas);
				break;
			}
			
			case STYLE3:
			{
				drawStyle3(canvas);
				break;
			}
		}
	}

}
