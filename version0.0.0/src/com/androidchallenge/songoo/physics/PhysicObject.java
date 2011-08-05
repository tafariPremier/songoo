/**
 * 
 */
package com.androidchallenge.songoo.physics;

import com.androidchallenge.songoo.util.Size;

import android.graphics.*;

/**
 * @author Martinien
 *
 */
public class PhysicObject
{
	public Point position;
	public Size size;
	public float angle;
	
	/**
	 * 
	 */
	public PhysicObject()
	{
		//
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void setPosition(Point p)
	{
		position = p;
	}
	
	public void setPosition(int left, int top)
	{
		position.set(left, top);
	}
	
	public int getLeft()
	{
		return position.x;
	}
	
	public void setLeft(int left)
	{
		position.x = left;
	}
	
	public int getTop()
	{
		return position.y;
	}
	
	public void setTop(int top)
	{
		position.y = top;
	}
	
	public float getAngle()
	{
		return angle;
	}
	
	public void setAngle(float angl)
	{
		angle = angl;
	}

}
