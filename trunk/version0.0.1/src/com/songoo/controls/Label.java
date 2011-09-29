/**
 * 
 */
package com.songoo.controls;

import com.songoo.data.*;

import android.graphics.*;
import android.graphics.Paint.Style;

/**
 * @author Martinien
 *
 */
public class Label extends Control
{
	private String _text;
	private float _size;
	private String _font;
	
	private int _color;
	private Integer _borderColor;
	private int _borderSize;

	/**
	 * @param parent
	 * @param name
	 */
	public Label(Object parent, String name)
	{
		super(parent, name);
		
		Init();
	}

	private void Init()
	{
		_text = "";
		_size = 10;
		_font = "JoeBeckerHeavyNormal";
		
		_color = Color.BLACK;
		_borderColor = null;
		_borderSize = 1;
	}
	
	public void setText(String text)
	{
		_text = text;
	}
	
	public String getText()
	{
		return _text;
	}
	
	public void setColor(int color)
	{
		_color = color;
	}
	
	public void setBorderColor(Integer color)
	{
		_borderColor = color;
	}
	
	public Integer getBorderColor()
	{
		return _borderColor;
	}
	
	public void setBorderSize(int size)
	{
		_borderSize = size;
	}
	
	public int getBorderSize()
	{
		return _borderSize;
	}
	
	public int getColor()
	{
		return _color;
	}
	
	public void setSize(float size)
	{
		_size = size;
	}
	
	public float getSize()
	{
		return _size;
	}
	
	public void setFont(String font)
	{
		_font = font;
	}
	
	public String getFont()
	{
		return _font;
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
		
		paint.setAntiAlias(true);
		paint.setTextSize(_size);
		paint.setTypeface(SessionData.GlobalData.fonts.get(_font));
		
		if (_borderColor != null)
		{
			paint.setColor(_borderColor);
			paint.setStyle(Style.FILL_AND_STROKE);
			paint.setStrokeWidth(_borderSize);
			canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		}
		
		paint.setColor(_color);
		paint.setStyle(Style.FILL);
		canvas.drawText(_text, getViewLeft(), getViewTop(), paint);
		
		
		/*Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setTextSize(50);
		paint.setTypeface(SessionData.GlobalData.fonts.get("JoeBeckerHeavyBold"));
		canvas.drawText("Songo'o", getLeft(), getTop(), paint);*/
		
		super.draw(canvas);
	}
}
