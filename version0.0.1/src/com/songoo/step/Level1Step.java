/**
 * 
 */
package com.songoo.step;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;

import com.songoo.R;
import com.songoo.backgame.Level1BackGame;
import com.songoo.controls.*;
import com.songoo.util.*;
import com.songoo.view.*;

/**
 * @author Martinien
 *
 */
public class Level1Step extends LevelStepView implements IStepView
{
	private Bitmap _backgroundBitmap;
	private TableControl _tableJeu;
	
	
	/**
	 * @param context
	 */
	public Level1Step(Context context)
	{
		super(context);
		
		InitStep();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Level1Step(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		InitStep();
	}

	private void InitStep()
	{
		setBackGame(new Level1BackGame());
		
		_backgroundBitmap = BitmapLoader.getBitmap(R.drawable.table000);
		
		_tableJeu = new TableControl(this, "tableJeu", getBackGame().getTableau());
		_tableJeu.setCaseControlPionsCoord(getContext().getResources().getStringArray(R.array.levelClassicPionsCoordonnees1));
		_tableJeu.setCaseControlSize(getContext().getResources().getStringArray(R.array.levelClassicCaseSize1));
		_tableJeu.setCaseControlCoord(getContext().getResources().getStringArray(R.array.levelClassicCaseCoordonnees1));
		_tableJeu.setSize(Screen.Width, Screen.Height - 70);
		_tableJeu.refresh();
		addControl(_tableJeu);
	}

	@Override
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(_backgroundBitmap, 0, 0, null);
		
		super.draw(canvas);
	}
}
