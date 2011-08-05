/**
 * 
 */
package com.androidchallenge.songoo.controls;

import com.androidchallenge.songoo.GameActivity;
import com.androidchallenge.songoo.gameplay.Tableau;
import com.androidchallenge.songoo.util.*;
import com.androidchallenge.songoo.view.LevelStepView;

import android.graphics.*;

/**
 * @author Martinien
 *
 */
public class TableControl extends Control
{
	private CaseControl[] _casesControl;
	private Point[] _positions;
	private Size[] _sizes;
	
	private Tableau _tableau;
	
	/**
	 * @param parent
	 * @param name
	 */
	public TableControl(Object parent, String name, Tableau tableau)
	{
		super(parent, name);
	
		_tableau = tableau;
		
		Init();
	}
	
	public void setPositions(Point[] positions)
	{
		_positions = positions;
	}
	
	public Point getPosition(int index)
	{
		if (_positions[index] == null) _positions[index] = new Point(0, 0);
		
		return _positions[index];
	}
	
	public Size getSize(int index)
	{
		Size result = null;
		
		if (_sizes.length == 1)
		{
			if (_sizes[0] == null) _sizes[0] = new Size(10, 10);
			
			result = _sizes[0];
		}
		else
		{
			if (_sizes[index] == null) _sizes[index] = new Size(10, 10);
			
			result = _sizes[index];
		}

		return result;
	}
	
	private void Init()
	{
		_casesControl = new CaseControl[12];
		_positions = new Point[12];
		
		setSize(Screen.Width, Screen.Height - 70);
	}
	
	public CaseControl getCaseControl(int index)
	{
		if (_casesControl[index] == null)
		{
			_casesControl[index] = new CaseControl(this, "caseControl" + String.valueOf(index), _tableau.trou[index]);
			_casesControl[index].setNumber(index);
		}
		
		return _casesControl[index];
	}

	@Override
	public void draw(Canvas canvas)
	{
		setCases();
		
		super.draw(canvas);
	}
	
	private void setCases()
	{
		getControls().clear();
		
		if (_casesControl != null)
		for (int i = 0; i < _casesControl.length; i++)
		{	
			Point position = getPosition(i);
			getCaseControl(i).setPosition(position.x, position.y);
			
			Size size = getSize(i);
			getCaseControl(i).setSize(size.w, size.h);
			
			getCaseControl(i).setOnClickListener(new OnControlClickListener(){
				
				public void onClick(Control c)
				{
					CaseControl cct = (CaseControl)c;
					
					for (int j = 0; j < cct.getTrou().getNbreGraines(); j++)
					{
						((GameActivity)c.getViewParent().getContext()).PlaySound(2);
						Functions.delay(100);
					}
					
					_tableau.jouer(cct.getNumber());
					
					refresh();
					
					((LevelStepView)c.getViewParent()).setScorePlayer1(_tableau.getScore(0));
					((LevelStepView)c.getViewParent()).setScorePlayer2(_tableau.getScore(1));
				}
				
			});
			
			addControl(getCaseControl(i));
		}
	}
	
	public void refresh()
	{
		for (int i = 0; i < _casesControl.length; i++)
		{
			_casesControl[i].refresh();
		}
		
		super.refresh();
	}
	
	public void setCaseControlPionsCoord(String[] stringArray)
	{
		Point[] coords = new Point[6];
		for (int i = 0; i < stringArray.length; i++)
		{
			String s = stringArray[i];
			String[] r = s.split(":");
			
			Point c = new Point(Integer.valueOf(r[0]), Integer.valueOf(r[1]));
			coords[i] = c;
		}
		
		for (int i = 0; i < _casesControl.length; i++)
		{
			getCaseControl(i).setCaseControlPionsCoord(coords);
		}
	}
	
	public void setCaseControlSize(String[] stringArray)
	{
		Size[] sizes = new Size[stringArray.length];
		for (int i = 0; i < stringArray.length; i++)
		{
			String s = stringArray[i];
			String[] r = s.split(":");
			
			Size c = new Size(Integer.valueOf(r[0]), Integer.valueOf(r[1]));
			sizes[i] = c;
		}
		
		_sizes = sizes;
	}
	
	public void setCaseControlCoord(String[] stringArray)
	{
		Point[] coords = new Point[12];
		for (int i = 0; i < stringArray.length; i++)
		{
			String s = stringArray[i];
			String[] r = s.split(":");
			
			Point c = new Point(Integer.valueOf(r[0]), Integer.valueOf(r[1]));
			coords[i] = c;
		}
		
		_positions = coords;
	}
}
