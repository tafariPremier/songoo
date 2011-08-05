/**
 * 
 */
package com.androidchallenge.songoo.util;

import java.util.HashMap;

/**
 * @author Martinien
 *
 */
public interface RunnableArg extends Runnable
{
	public HashMap<String, Object> Arguments = new HashMap<String, Object>();
}
