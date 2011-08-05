/**
 * 
 */
package com.androidchallenge.songoo.util;

/**
 * @author Martinien
 *
 */
public class Size
{
	public int w;
	public int h;
	
	/**
	 * 
	 */
	public Size()
	{
		w = 0;
		h = 0;
	}
	
	public Size(int width, int height)
	{
		set(width, height);
	}
	
	public void set(int width, int height)
	{
		w = width;
		h = height;
	}

	public int getWidth()
	{
		return w;
	}
	
	public void setWidth(int value)
	{
		w = value;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public void setHeight(int value)
	{
		h = value;
	}
}
