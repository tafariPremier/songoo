/**
 * 
 */
package com.songoo.util;

/**
 * @author Martinien
 *
 */
public class Functions extends Objet
{
	/**
	 * 
	 */
	public Functions()
	{
		//
	}

	public static void delay(long millis)
	{
		long dateTime = System.currentTimeMillis() + millis;
		while (System.currentTimeMillis() < dateTime) { }
	}
	
	public static Boolean isSubClassOf(Class<?> clss, Class<?> prnt)
	{
		Boolean result = false;
		
		Class<?> p = clss.getSuperclass();
		
		if (p != null)
		{
			if (p == prnt)
			{
				result = true;
			}
			else
			{
				result = isSubClassOf(p, prnt);
			}
		}
		
		return result;
	}
}
