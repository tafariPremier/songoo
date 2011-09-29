/**
 * 
 */
package com.songoo.util;

import java.io.InputStream;

import android.content.Context;
import android.graphics.*;


/**
 * @author Martinien
 *
 */
public class BitmapLoader extends Objet
{
	private static InputStream _inputStream;
	public static Context _context;
	
	/**
	 * 
	 */
	public BitmapLoader()
	{
		//
	}
	
	public static void setContext(Context context)
	{
		_context = context;
	}

	public static Bitmap getBitmap(int resource)
	{
		_inputStream = _context.getResources().openRawResource(resource);
		if (_inputStream == null) return null;
		
		return BitmapFactory.decodeStream(_inputStream);
	}
}
