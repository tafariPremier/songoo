/**
 * 
 */
package com.songoo.fx;

import com.songoo.util.Objet;
import com.songoo.util.RunnableArg;

import android.graphics.*;
import android.os.*;

/**
 * @author Martinien
 *
 */
public class FrameLife extends Objet
{
	private Handler _handler;
	private Canvas _canvas;
	private int _fps;
	
	private FrameRunnable _frameRunnable;
	private RunnableArg _runnableArg;
	
	private Boolean _pause;
	private Boolean _live;
	
	/**
	 * 
	 */
	public FrameLife(Canvas canvas, int fps)
	{
		_handler = new Handler();
		_canvas = canvas;
		_fps = fps;
		
		_frameRunnable = null;
		_runnableArg = null;
		
		_pause = false;
		_live = false;
	}
	
	public void setFramePerSecond(int fps)
	{
		_fps = fps;
	}
	
	public int getFramePerSecond()
	{
		return _fps;
	}
	
	public void Set(FrameRunnable frameRunnable)
	{
		_frameRunnable = frameRunnable;
	}

	public void Start()
	{
		_live = true;
		_runnableArg = new RunnableArg() {
			
			public void run()
			{
				FrameRunnable frameR = (FrameRunnable)this.Arguments.get("frameRunnable");
				
				if (_live)
				{
					if (!_pause)
					{
						frameR.frame(_canvas);
					}
					
					_handler.postDelayed(this, _fps);
				}
			}
		};
		_runnableArg.Arguments.put("frameRunnable", _frameRunnable);
		
		_handler.post(_runnableArg);
	}
	
	public void Pause()
	{
		_pause = true;
	}
	
	public void Resume()
	{
		_pause = false;
	}
	
	public void Stop()
	{
		_live = false;
	}
}
