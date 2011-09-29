/**
 * 
 */
package com.songoo.view;

/**
 * @author Martinien
 *
 */
public interface IStepView
{
	void Start();
	void Stop();
	
	void SwithTo(IStepView step);
}
