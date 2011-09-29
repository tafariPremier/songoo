/**
 * 
 */
package com.songoo.fx;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import com.songoo.controls.Control;
import com.songoo.listener.*;
import com.songoo.util.Objet;

/**
 * @author Martinien
 *
 */
public class ControlAnimation extends Objet
{
	private Control _control;
	private FrameLife _frameLife;
	
	private int _nframes;
	private int _nfrm;
	
	private PointF _position;
	
	private Point _begin;
	private Point _end;
	
	private OnAnimationBeginListener _onAnimationBeginListener;
	private OnAnimationEndListener _onAnimationEndListener;
	private OnAnimationWhileListener _onAnimationWhileListener;
	
	private ControlAnimation _controlAnimation = this;
	
	/**
	 * 
	 */
	public ControlAnimation(Control control)
	{
		_control = control;
	}
	
	public void setOnAnimationBeginListener(OnAnimationBeginListener onAnimationBeginListener)
	{
		_onAnimationBeginListener = onAnimationBeginListener;
	}
	
	public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener)
	{
		_onAnimationEndListener = onAnimationEndListener;
	}
	
	public void setOnAnimationWhileListener(OnAnimationWhileListener onAnimationWhileListener)
	{
		_onAnimationWhileListener = onAnimationWhileListener;
	}
	
	public int getframes()
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
				
				if (_onAnimationWhileListener != null)
				{
					_onAnimationWhileListener.run(_controlAnimation);
				}
				
				if (_nframes == 0)
				{
					_frameLife.Stop();
					
					if (_onAnimationEndListener != null)
					{
						_onAnimationEndListener.run(_controlAnimation);
					}
					
					_frameLife.dispose();
				}
			}
		});
		
		_nframes = (duration * _frameLife.getFramePerSecond()) / 1000;
		_nfrm = _nframes;
		
		
		if (_onAnimationBeginListener != null)
		{
			_onAnimationBeginListener.run(_controlAnimation);
		}
		
		_frameLife.Start();
	}
}
