/**
 * 
 */
package com.androidchallenge.songoo.controls;

import java.util.HashMap;

import com.androidchallenge.songoo.util.*;
import com.androidchallenge.songoo.view.StepView;

import android.graphics.*;
import android.view.*;


/**
 * @author Martinien
 *
 */
public class Control
{
	public static int INVISIBLE = 0;
	public static int VISIBLE = 1;
	
	private int _width;
	private int _height;
	private int _top;
	private int _left;
	
	private int _visibility;
	
	private String _name;
	
	private Bitmap _render;
	private Object _parent;
	
	public int xClic;
	public int yClic;
	
	public Boolean isTouchDown;
	public Boolean isKeyDown;
	
	public Boolean _docked;
	
	private OnControlClickListener _onControlClickListener;
	
	private HashMap<Integer, Control> _controls;
	
	/**
	 * @param context
	 */
	public Control(Object parent, String name)
	{
		_name = name;
		_parent = parent;
		_controls = new HashMap<Integer, Control>();
		
		_docked = false;
		
		Init();
	}
	
	public void setVisibility(int value)
	{
		_visibility = value;
	}
	
	public int getVisibility()
	{
		return _visibility;
	}
	
	public void setOnClickListener(OnControlClickListener onControlClickListener)
	{
		_onControlClickListener = onControlClickListener;
	}
	
	public StepView getViewParent()
	{
		StepView result = null;
		
		if (_parent != null)
		{
			if (Functions.isSubClassOf(_parent.getClass(), StepView.class))
			{
				result = (StepView)_parent;
			}
			else
			{
				result = ((Control)_parent).getViewParent();
			}
		}
		
		return result;
	}
	
	public Object getParent()
	{
		return _parent;
	}
	
	public HashMap<Integer, Control> getControls()
	{
		if (_controls == null) _controls = new HashMap<Integer, Control>();
		
		return _controls;
	}

	private void Init()
	{
		isTouchDown = false;
		isKeyDown = false;
		xClic = -1000;
		yClic = -1000;
		
		setVisibility(Control.VISIBLE);
		setSize(100, 40);
		setPosition(0, 0);
	}
	
	public Boolean isDocked()
	{
		return _docked;
	}
	
	public void setDock()
	{
		_docked = true;
	}
	
	public void removeDock()
	{
		_docked = false;
	}
	
	public void setxyClic(int x, int y)
	{
		xClic = x;
		yClic = y;
	}
	
	public void setTouchDownState(Boolean state)
	{
		isTouchDown = state;
	}
	
	public boolean inControl(MotionEvent event)
	{
		int left = getViewLeft();
		int top = getViewTop();
		
		int width = getWidth();
		int height = getHeight();
		
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		if ((x >= left) && (x <= left + width) && (y >= top) && (y <= top + height))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addControl(Control control)
	{
		getControls().put(_controls.size(), control);
	}
	
	public void onTouchDown(Control control, MotionEvent event)
	{
		//
	}
	
	public void onTouchUp(Control control, MotionEvent event)
	{
		//
	}

	public boolean onLongClick(Control control)
	{
		//
		return true;
	}
	
	public void onKeyUp(Control control, int keyCode, KeyEvent event)
	{
		//
	}
	
	public void onKeyDown(Control control, int keyCode, KeyEvent event)
	{
		//
	}

	public boolean onKey(Control control, int keyCode, KeyEvent event)
	{
		//
		return true;
	}

	public boolean onTouch(Control control, MotionEvent event)
	{
		//
		return true;
	}
	
	private Boolean _childClic;
	private MotionEvent _event;
	public Boolean onClick(Control control, MotionEvent e)
	{
		_childClic = false;
		_event = e;
		
		doForVisibleChilds(new RunnableArg()
		{	
			public void run()
			{
				Control c = ((Control)Arguments.get("Control"));
				
				if ((c.getLeft() <= _event.getX()) && (c.getLeft() + c.getWidth() >= _event.getX()) &&
					(c.getTop() <= _event.getY()) && (c.getTop() + c.getHeight() >= _event.getY()))
				{
					if (c.onClick(c, _event))
					{
						_childClic = true;
					}
				}
			}
		});
		
		if (!_childClic)
		if (_onControlClickListener != null)
		{
			_onControlClickListener.onClick(this);
			return true;
		}
		
		return false;
	}

	public String getName()
	{
		return _name;
	}
	
	public void setRender(Bitmap bitmap)
	{
		_render = bitmap;
	}
	
	public Bitmap getRender()
	{
		return _render;
	}

	public void setWidth(int width)
	{
		_width = width;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public void setHeight(int height)
	{
		_height = height;
	}
	
	public int getHeight()
	{
		return _height;
	}
	
	public void setSize(int width, int height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public void setTop(int top)
	{
		_top = top;
	}
	
	public int getTop()
	{
		return _top;
	}
	
	public int getViewTop()
	{
		return getViewTop(null);
	}
	
	public int getViewTop(Integer value)
	{
		int top = _top;
		
		if (value != null) top = value;
		
		if (_parent != null)
		{
			if (!Functions.isSubClassOf(_parent.getClass(), View.class))
			{
				top += ((Control)_parent).getTop();
			}
		}
		
		return top;
	}
	
	public void setLeft(int left)
	{
		_left = left;
	}
	
	public int getLeft()
	{
		return _left;
	}
	
	public int getViewLeft()
	{
		return getViewLeft(null);
	}

	public int getViewLeft(Integer value)
	{
		int left = _left;
		
		if (value != null) left = value;
		
		if (_parent != null)
		{
			if (!Functions.isSubClassOf(_parent.getClass(), View.class))
			{
				left += ((Control)_parent).getLeft();
			}
		}
		
		return left;
	}
	
	public void setPosition(int left, int top)
	{
		setLeft(left);
		setTop(top);
	}
	
	public void setPosition(Point point)
	{
		setLeft(point.x);
		setTop(point.y);
	}
	
	public void draw(Canvas canvas)
	{
		if (_render != null)
		{
			//canvas.drawBitmap(_render, getViewLeft(), getViewTop(), null);
			
			Rect src = new Rect(0, 0, _render.getWidth(), _render.getHeight());
			Rect dst = null;
			
			if(_docked)
			{
				if (_parent.getClass() == View.class)
				{
					View v = (View)_parent;
					dst = new Rect(getViewLeft(0), getViewTop(0), v.getWidth(), v.getHeight());
				}
				else if (_parent.getClass() == Control.class)
				{
					Control c = (Control)_parent;
					dst = new Rect(getViewLeft(0), getViewTop(0), c.getWidth(), c.getHeight());
				} 
			}
			else
			{
				dst = new Rect(getViewLeft(), getViewTop(), getWidth(), getHeight());
			}
			
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			
			canvas.drawBitmap(_render, src, dst, paint);
		}
			
		drawChilds(canvas);
	}
	
	public void Alert(String text)
	{
		getViewParent().Alert(text);
	}
	
	private void drawChilds(Canvas canvas)
	{
		HashMap<Integer, Control> cs = getControls();
		for(int i = 0; i < cs.size(); i++)
		{
			Control c = cs.get(i);
			
			if (c.getVisibility() == Control.VISIBLE)
			{
				c.draw(canvas);
			}
		}
	}
	
	private void doForVisibleChilds(RunnableArg runnable)
	{
		for(int i = 0; i < _controls.size(); i++)
		{
			Control c = _controls.get(i);
			
			if (c.getVisibility() == Control.VISIBLE)
			{
				RunnableArg.Arguments.put("Control", c);
				runnable.run();
			}
		}
	}
	
	public void refresh()
	{
		//
	}
}
