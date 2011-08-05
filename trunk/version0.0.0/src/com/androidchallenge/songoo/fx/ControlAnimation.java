/**
 * 
 */
package com.androidchallenge.songoo.fx;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import com.androidchallenge.songoo.controls.Control;

/**
 * @author Martinien
 *
 */
public class ControlAnimation
{
	private Control _control;
	private FrameLife _frameLife;
	
	private int _nframes;
	private int _nfrm;
	
	private PointF _position;
	
	private Point _begin;
	private Point _end;
	
	/**
	 * 
	 */
	public ControlAnimation(Control control)
	{
		_control = control;
	}
	
	public int getf()
	{
		return _nframes;
	}

	public void Translate(Point begin, Point end, int duration)
	{
		_begin = begin;
		_end = end;
		
		_position = new PointF(begin.x, begin.y);
		
		_frameLife = new FrameLife(null, 24);
		_frameLife.Set(new FrameRunnable() {
			
			public void frame(Canvas canvas)
			{
				_nframes--;
				
				float x = _position.x + (_end.x - _begin.x) / (float)_nfrm;
				float y = _position.y + (_end.y - _begin.y) / (float)_nfrm;
				
				_position.set(x, y);
				
				_control.setPosition((int)_position.x, (int)_position.y);
				
				if (_nframes < 0) _frameLife.Stop();
			}
		});
		
		_nframes = (duration * _frameLife.getFramePerSecond()) / 1000;
		_nfrm = _nframes;
		
		_frameLife.Start();
	}
}
