/**
 * 
 */
package com.songoo.fonts;

import java.util.HashMap;

import com.songoo.util.Objet;


import android.graphics.*;

/**
 * @author Martinien
 *
 */
public class Font extends Objet
{
	protected HashMap<Character, Bitmap> characters;
	
	/**
	 * 
	 */
	public Font()
	{
		//
	}

	public void Load()
	{
		characters = new HashMap<Character, Bitmap>();
	}
	
	public void DrawText(Canvas canvas, String text, int x, int y)
	{
		DrawText(canvas, text, x, y, 1);
	}
	
	public void DrawText(Canvas canvas, String text, int x, int y, float scale)
	{
		Character key;
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
		RectF dst;
		
		char[] charArray = text.toCharArray();
		
		int nx = x;
		int ny = y;
		for (int i = 0; i < charArray.length; i++)
		{
			key = charArray[i];
			
			if (characters.containsKey(key))
			{
				Bitmap _char = characters.get(key);
				
				dst = new RectF(nx, ny, nx + _char.getWidth() * scale, ny + _char.getHeight() * scale);
				nx = (int)(nx + _char.getWidth() * scale);
				
				canvas.drawBitmap(characters.get(key), null, dst, paint);
			}
			
			if (key == '\n')
			{
				nx = x;
				ny = (int)(ny + characters.get('A').getHeight() * scale);
			}
		}
	}
}
