/**
 * 
 */
package com.songoo.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author Martinien
 *
 */
public class AdvancedThreadFactory implements ThreadFactory
{
	/**
	 * 
	 */
	public AdvancedThreadFactory()
	{
		//
	}

	public Thread newThread(Runnable r)
	{
		Thread _newThread = new Thread(r);
		return _newThread;
	}

}
