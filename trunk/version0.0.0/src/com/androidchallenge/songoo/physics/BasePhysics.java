/**
 * 
 */
package com.androidchallenge.songoo.physics;

import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

/**
 * @author Martinien
 *
 */
public class BasePhysics
{
	public int targetFPS = 24;
	public int timeStep = (1000 / targetFPS);
	
	public int velocityIterations = 6;
	public int positionIterations = 2;
	
	protected Body[] bodies;  
	protected int count = 0; 
	
	protected AABB worldAABB;  
	protected World world; 
    
	/**
	 * 
	 */
	public BasePhysics()
	{
		//
	}

	public void Initialize()
	{
		//
	}
	
	public void Update()
	{
		world.step(timeStep, velocityIterations, positionIterations);
	}
	
	public PhysicObject[] getPhysicsObjects()
	{
		PhysicObject[] array = new PhysicObject[count];
		
		for(int i = 0; i < count; i++)
		{
			Vec2 position = bodies[count].getPosition();
			float angle = bodies[count].getAngle();
			
			array[i] = new PhysicObject();
			array[i].setPosition((int)position.x, (int)position.y);
			array[i].setAngle(angle);
		}
		
		return array;
	}
}
