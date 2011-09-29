/**
 * 
 */
package com.songoo.fonts;

import android.graphics.Bitmap;

import com.songoo.R;
import com.songoo.util.*;

/**
 * @author Martinien
 *
 */
public class FontJoeBeckerBig1 extends Font
{
	/**
	 * 
	 */
	public FontJoeBeckerBig1()
	{
		Load();
	}

	@Override
	public void Load()
	{
		super.Load();
		
		LoadMajiscules();
	}

	private void LoadMajiscules()
	{
		Bitmap ua1 = BitmapLoader.getBitmap(R.drawable.ua1);
		characters.put('A', Bitmap.createBitmap(ua1, 9, 0, 45, ua1.getHeight()));
		
		Bitmap ub1 = BitmapLoader.getBitmap(R.drawable.ub1);
		characters.put('B', Bitmap.createBitmap(ub1, 8, 0, 45, ub1.getHeight()));
		
		Bitmap uc1 = BitmapLoader.getBitmap(R.drawable.uc1);
		characters.put('C', Bitmap.createBitmap(uc1, 12, 0, 38, uc1.getHeight()));
	}
}
