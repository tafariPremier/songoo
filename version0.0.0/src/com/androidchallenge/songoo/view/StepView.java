/**
 * 
 */
package com.androidchallenge.songoo.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidchallenge.songoo.R;
import com.androidchallenge.songoo.controls.*;
import com.androidchallenge.songoo.data.SessionData;
import com.androidchallenge.songoo.util.*;

import java.util.*;

/**
 * @author Martinien
 *
 */
public class StepView extends LinearLayout implements IStepView
{
	private Bitmap _wadcResource;
	private HashMap<String, Object> _arguments;
	
	protected Timer _timerAnimation;
	protected AdvancedThreadFactory _advancedThreadFactory;
	protected Context _context;
	
	private VolumeControl _volumeControl;
	
	private HashMap<Integer, Control> _controls;
	
	
	/**
	 * @param context
	 */
	public StepView(Context context)
	{
		super(context);
		Initialize(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public StepView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		Initialize(context);
	}

	public void addControl(Control control)
	{
		_controls.put(_controls.size(), control);
	}
	
	/**
	 * Initialisation du contrôle
	 * @param context
	 */
	private void Initialize(Context context)
	{
		setWillNotDraw(false);
		setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
		
		_context = context;
		
		_controls = new HashMap<Integer, Control>();
		_arguments = new HashMap<String, Object>();
		
		initControls();
		
		_timerAnimation = new Timer();
		_advancedThreadFactory = new AdvancedThreadFactory();
		_wadcResource = BitmapLoader.getBitmap(R.drawable.mobile000);
		
				
		setOnLongClickListener(new OnLongClickListener()
		{	
			public boolean onLongClick(View v)
			{
				/*MotionEvent event = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, _xClic, _yClic, 0);
				
				if (Control.this.inControl(event))
				{
					return Control.this.onLongClick(Control.this);
				}
				else
				{
					return false;
				}*/
				
				return true;
			}
		});
	}

	private void initControls()
	{
		_volumeControl = new VolumeControl(this, "_volumeControl");
		_volumeControl.setPosition(10, Screen.Height - _volumeControl.getHeight() - 10);
		_volumeControl.setVolume(SessionData.GlobalData.volume);
		_volumeControl.setOnClickListener(new OnControlClickListener() {
			
			public void onClick(Control control)
			{
				SessionData.GlobalData.volume = _volumeControl.getVolume();
			}
		});
		
		addControl(_volumeControl);
	}

	public Runnable onStepOver;
	public void StepOver()
	{
		if (onStepOver != null) onStepOver.run();
	}
	
	public Runnable onStepNext;
	public void StepNext()
	{
		if (onStepNext != null) onStepNext.run();
	}
	
	public Runnable onStepPrevious;
	public void StepPrevious()
	{
		if (onStepPrevious != null) onStepPrevious.run();
	}
	

	@Override
	protected void onDraw(Canvas canvas)
	{
		Paint paintADCResource = new Paint();
		paintADCResource.setAlpha(180);
		canvas.drawBitmap(_wadcResource, Screen.Width - _wadcResource.getWidth() - 30, Screen.Height - _wadcResource.getHeight() - 10, paintADCResource);
		
		/*Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setTextSize(50);
		paint.setTypeface(SessionData.GlobalData.fonts.get("JoeBeckerHeavyBold"));
		canvas.drawText("Songo'o", 550, 100, paint);*/
		
		//SessionData.GlobalData.fontsBitmap.get("JoeBeckerBig1").DrawText(canvas, "BACAB", 20, 20);
		
		drawChilds(canvas);
		
		super.onDraw(canvas);
		invalidate();
	}
	
	private void drawChilds(Canvas canvas)
	{
		_arguments.clear();
		_arguments.put("canvas", canvas);
		
		doForVisibleChilds(new RunnableArg()
		{	
			public void run()
			{
				((Control)Arguments.get("Control")).draw((Canvas)_arguments.get("canvas"));				
			}
		});
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

	public void Start()
	{
		//
	}

	public void Stop()
	{
		//
	}

	public void SwithTo(IStepView step)
	{
		//
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		_arguments.clear();
		_arguments.put("keycode", keyCode);
		_arguments.put("event", event);
		
		doForVisibleChilds(new RunnableArg()
		{	
			public void run()
			{
				Control c = ((Control)Arguments.get("Control"));
				c.onKeyDown(c, (Integer)_arguments.get("keycode"), (KeyEvent)_arguments.get("event"));			
			}
		});
		
		//return super.onKeyDown(keyCode, event);
		
		return true;
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		_arguments.clear();
		_arguments.put("keycode", keyCode);
		_arguments.put("event", event);
		
		doForVisibleChilds(new RunnableArg()
		{	
			public void run()
			{
				Control c = ((Control)Arguments.get("Control"));
				c.onKeyUp(c, (Integer)_arguments.get("keycode"), (KeyEvent)_arguments.get("event"));			
			}
		});
		
		//return super.onKeyDown(keyCode, event);
		
		return true;
	}
	
	public void Alert(String text)
	{
		Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		_arguments.clear();
		_arguments.put("event", event);
		
		doForVisibleChilds(new RunnableArg()
		{	
			public void run()
			{
				Control c = ((Control)Arguments.get("Control"));
				MotionEvent e = ((MotionEvent)_arguments.get("event"));
				
				if (c.inControl(e))
				{
					if (c.xClic == -1000) c.setxyClic((int)e.getX(), c.yClic);
					if (c.yClic == -1000) c.setxyClic(c.xClic, (int)e.getY());
					
					
					
					switch(e.getAction())
					{
						case MotionEvent.ACTION_DOWN :
						{
							c.onTouchDown(c, e);
							c.setTouchDownState(true);
							
							break;
						}
						
						case MotionEvent.ACTION_UP :
						{
							c.onTouchUp(c, e);
							
							if (c.isTouchDown)
							{
								c.setTouchDownState(false);
								c.onClick(c, e);
								
								c.setxyClic(-1000, -1000);
							}
							
							break;
						}
					}
					
					c.onTouch(c, e);
				}
			}
		});
		
		//return super.onTouchEvent(event);
		
		return true;
	}
}
