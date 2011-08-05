/**
 * 
 */
package com.androidchallenge.songoo.view;

import com.androidchallenge.songoo.GameActivity;
import com.androidchallenge.songoo.R;
import com.androidchallenge.songoo.controls.*;
import com.androidchallenge.songoo.gameplay.BackGame;
import com.androidchallenge.songoo.physics.BasePhysics;
import com.androidchallenge.songoo.util.OnControlClickListener;
import com.androidchallenge.songoo.util.Screen;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * @author Martinien
 *
 */
public class LevelStepView extends StepView
{
	private BackGame _backGame;
	
	private Label _lblPlayer1;
	private Label _lblPlayer2;
	
	private ImageButton _menu;
	
	private BasePhysics _physicWorld;
	
	/**
	 * @param context
	 */
	public LevelStepView(Context context)
	{
		super(context);
		
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LevelStepView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		InitStep();
	}
	
	public BasePhysics getWorld()
	{
		return _physicWorld;
	}
	
	public void setWorld(BasePhysics world)
	{
		_physicWorld = world;
	}
	
	private void InitStep()
	{
		_lblPlayer1 = new Label(this, "_lblPlayer1");
		_lblPlayer1.setColor(Color.WHITE);
		_lblPlayer1.setBorderColor(Color.BLACK);
		_lblPlayer1.setSize(30);
		_lblPlayer1.setFont("JoeBeckerHeavyBold");
		_lblPlayer1.setText("Joueur 1 : 0");
		_lblPlayer1.setPosition(15, 30);
		
		_lblPlayer2 = new Label(this, "_lblPlayer2");
		_lblPlayer2.setColor(Color.WHITE);
		_lblPlayer2.setBorderColor(Color.BLACK);
		_lblPlayer2.setSize(30);
		_lblPlayer2.setFont("JoeBeckerHeavyBold");
		_lblPlayer2.setText("Joueur 2 : 0");
		_lblPlayer2.setPosition(Screen.Width / 2, 30);
		
		addControl(_lblPlayer1);
		addControl(_lblPlayer2);
		
		
		
		
		
		_menu = new ImageButton(this, "menuButton", R.drawable.menu001, R.drawable.menu002);
		_menu.setPosition((Screen.Width - _menu.getWidth()) / 2, Screen.Height - _menu.getHeight());
		_menu.setOnClickListener(new OnControlClickListener()
		{	
			public void onClick(Control c)
			{
				//
			}
		});
		addControl(_menu);
		
		loadSounds();
	}
	
	private void loadSounds()
	{
		((GameActivity)_context).LoadSound(2, R.raw.pion000);
	}
	
	public void setScorePlayer1(int score)
	{
		_lblPlayer1.setText("Joueur 1 : " + String.valueOf(score));
	}
	
	public void setScorePlayer2(int score)
	{
		_lblPlayer2.setText("Joueur 2 : " + String.valueOf(score));
	}

	public void setBackGame(BackGame backGame)
	{
		_backGame = backGame;
	}
	
	public BackGame getBackGame()
	{
		return _backGame;
	}
}
