/**
 * 
 */
package com.songoo.util;

/**
 * @author Martinien
 *
 */
public class Objet
{
	/**
	 * 
	 */
	public Objet()
	{
		// TODO Auto-generated constructor stub
	}

	public void dispose()
	{
		try
		{
			finalize();
		}
		catch (Throwable e) { }
	}
}
