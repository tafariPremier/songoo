/**
 * 
 */
package com.songoo.controls;

import com.songoo.GameActivity;
import com.songoo.R;
import com.songoo.fx.ControlAnimation;
import com.songoo.gameplay.Tableau;
import com.songoo.listener.*;
import com.songoo.util.*;
import com.songoo.view.LevelStepView;

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
	private PionControl[] _pionsAnimated;
	
	private Tableau _tableau;
	private int _caseNumber;
	
	private int _ngraines;
	private GameActivity _gameActivity;
	
	private Point[] _prochainsCoupsPositions;
	private int _coupsuivant;
	
	
	/**
	 * @param parent
	 * @param name
	 */
	public TableControl(Object parent, String name, Tableau tableau)
	{
		super(parent, name);
	
		_tableau = tableau;
		_gameActivity = (GameActivity)getViewParent().getContext();
		
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
						
					if (!(cct.getTrou().isEmpty()) && (_tableau.getPosition() == cct.getTrou().getPosition()))
					{
						_caseNumber = cct.getNumber();
						AnimatePionsTranslation();
					}
				}
				
			});
			
			addControl(getCaseControl(i));
		}
	}
	
	protected void refreshScores()
	{
		((LevelStepView)getViewParent()).setScorePlayer1(_tableau.getScore(0));
		((LevelStepView)getViewParent()).setScorePlayer2(_tableau.getScore(1));
	}

	protected int[] getNumerosCasesDistribution(int fromCase)
	{
		int ngraines = getCaseControl(fromCase).getTrou().getNbreGraines();
		
		int[] result = new int[ngraines];
		
		int n = fromCase;
		int i = 0;
		
		while (ngraines > 0)
		{
			n++;
			
			if (n == 12)
			{
				n = 0;
			}
			
			result[i] = n;
			
			i++;
			ngraines--;
		}
		
		return result;
	}
	
	protected Point[] getProchainsCoups(int fromCase, int nombreGraines)
	{
		Point[] result = new Point[nombreGraines];
		int[] numCazDistribution = getNumerosCasesDistribution(fromCase);
		
		for (int i = 0; i < numCazDistribution.length; i++)
		{
			int k = numCazDistribution[i];
			
			Point coord = getCaseControl(k).CoordonneesProchainCoup();
			coord.x += _positions[k].x;
			coord.y += _positions[k].y;
			
			result[i] = coord;
		}
		
		return result;
	}
	
	protected void TranslatePion()
	{
		int pionNumber = (_ngraines - 1) % 6;
		
		PionControl pionControl = new PionControl(this.getViewParent(), "pionControl1000" + String.valueOf(_ngraines));
		_pionsAnimated[_ngraines - 1] = pionControl;
		pionControl.setPosition(getCaseControl(_caseNumber).getPions()[pionNumber].getViewPosition());
		
		pionControl.setImage(R.drawable.pion000);
		this.getViewParent().addControl(pionControl);
		
		if (_ngraines - 1 < 6)
		{
			getCaseControl(_caseNumber).getPions()[_ngraines - 1].setVisibility(INVISIBLE);
		}
		
		ControlAnimation controlAnimation = new ControlAnimation(pionControl);
		controlAnimation.setOnAnimationBeginListener(new OnAnimationBeginListener() {
			
			public void run(ControlAnimation controlAnimation)
			{
				getCaseControl(_caseNumber).setNbrPionsDiplayed(_ngraines - 1);	
			}
		});
		
		controlAnimation.setOnAnimationWhileListener(new OnAnimationWhileListener(){
			
			public void run(ControlAnimation controlAnimation)
			{
				//
			}
			
		});
		
		controlAnimation.setOnAnimationEndListener(new OnAnimationEndListener()
		{
			public void run(ControlAnimation controlAnimation)
			{
				_ngraines--;
			
				_gameActivity.PlaySound(2);
				
				if (_ngraines > 0)
				{
					TranslatePion();
				}
				else
				{
					_tableau.jouer(_caseNumber);
					refresh();
					
					for (int i = 0; i < _pionsAnimated.length; i++)
					{
						if (_pionsAnimated[i] != null)
						{
							int index = _pionsAnimated[i].childIndex;
							getViewParent().removeControl(index);
							_pionsAnimated[i].dispose();
						}
					}
					
					if (_tableau.getPosition() == 2)
					{
						//_tableau.alphabeta(1, -100, 100);
						//_caseNumber = Tableau.meilleur_coup;
						_caseNumber = _tableau.meilleurCoup();
						AnimatePionsTranslation();
					}
				}
				
				controlAnimation.dispose();
			}
		});
		
		controlAnimation.Translate(pionControl.getPosition(), _prochainsCoupsPositions[_coupsuivant], 500);
		_coupsuivant++;
	}
	
	protected void AnimatePionsTranslation()
	{
		_ngraines = getCaseControl(_caseNumber).getTrou().getNbreGraines();		
		
		if (_ngraines > 0)
		{
			_coupsuivant = 0;
			_prochainsCoupsPositions = getProchainsCoups(_caseNumber, _ngraines);
			
			_pionsAnimated = new PionControl[_ngraines];
			
			TranslatePion();
		}
	}

	public void refresh()
	{
		refreshScores();
		
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
