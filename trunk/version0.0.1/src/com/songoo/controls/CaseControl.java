/**
 * 
 */
package com.songoo.controls;

import com.songoo.R;
import com.songoo.gameplay.Trou;

import android.graphics.*;

/**
 * @author Martinien
 *
 */
public class CaseControl extends Control
{
	private PionControl[] _pionsControl;
	private Point[] _positions;
	
	private int _number;
	private Trou _trou;
	
	private Label _lblNbrPions;

	/**
	 * @param parent
	 * @param name
	 */
	public CaseControl(Object parent, String name, Trou trou)
	{
		super(parent, name);
		
		_trou = trou;
		
		Init();
	}
	
	private void Init()
	{
		_pionsControl = new PionControl[6];
		_positions = new Point[6];
		
		setAllPionResource(R.drawable.pion000);
	}
	
	public void setTrou(Trou trou)
	{
		_trou = trou;
	}
	
	public Trou getTrou()
	{
		return _trou;
	}
	
	public void setNumber(int number)
	{
		_number = number;
	}
	
	public int getNumber()
	{
		return _number;
	}
	
	public void setPionsDisposition(Point[] positions)
	{
		_positions = positions;
	}
	
	public void setPionResource(int index, int res)
	{
		if (_pionsControl[index] == null) _pionsControl[index] = new PionControl(this, "pionControl" + String.valueOf(index));
		
		_pionsControl[index].setImage(res);
	}
	
	public void setAllPionResource(int res)
	{
		for (int i = 0; i < _pionsControl.length; i++)
		{
			setPionResource(i, res);
		}
	}
	
	public Point getPosition(int index)
	{
		if (_positions[index] == null) _positions[index] = new Point(0, 0);
		
		return _positions[index];
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		setPions();
		
		super.draw(canvas);
	}
	
	public PionControl[] getPions()
	{
		return _pionsControl;
	}
	
	public void setPions()
	{		
		getControls().clear();
		
		int nbrGraines = _trou.getNbreGraines();
		
		if (_pionsControl != null)
		for (int i = 0; i < _pionsControl.length; i++)
		{
			Point position = getPosition(i);
			_pionsControl[i].setPosition(position.x, position.y);
			
			addControl(_pionsControl[i]);
		}
		
		_lblNbrPions = new Label(this, "_lblNbrPions");
		_lblNbrPions.setColor(Color.WHITE);
		_lblNbrPions.setBorderColor(Color.BLACK);
		_lblNbrPions.setSize(30);
		_lblNbrPions.setFont("JoeBeckerHeavyBold");
		_lblNbrPions.setVisibility(VISIBLE);
		_lblNbrPions.setText(String.valueOf(nbrGraines));
		_lblNbrPions.setPosition(80, 160);
		
		addControl(_lblNbrPions);
	}
	
	public void setNbrPionsDiplayed(int value)
	{
		_lblNbrPions.setText(String.valueOf(value));
	}
	
	public void hideNbrPions()
	{
		_lblNbrPions.setVisibility(INVISIBLE);
	}
	
	public void setCaseControlPionsCoord(Point[] coords)
	{
		_positions = coords;
	}
	
	public Point CoordonneesProchainCoup()
	{
		Point result = new Point(0, 0);
		
		int n = getTrou().getNbreGraines() + 1;
		while (n > 6) n -= 6; n--;
		
		result = new Point(_positions[n]);
		
		return result;
	}
	
	public void refresh()
	{
		int nbrGraines = _trou.getNbreGraines();
		
		int n = (nbrGraines > 6)? 6 : nbrGraines;
		int ni = 6 - n;
		
		int i = 0;
		for (; i < n ; i++)
		{
			_pionsControl[i].setVisibility(Control.VISIBLE);
		}
		
		for (; i < n + ni ; i++)
		{
			_pionsControl[i].setVisibility(Control.INVISIBLE);
		}
		
		super.refresh();
	}
}
