package com.eep.taxe.test.resources;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eep.taxe.models.Age;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.TrainSpeedModifier;


public class SpeedModifierTest {
	//Age 	steamAge					= new Age(0); //HOW DOES THIS WORK?????????
	float				baseSpeed, boostSpeedFactor, slowSpeedFactor;
	Train			 	steamTrain, futureTrain;
	TrainSpeedModifier	steamBoost, steamSlowdown;
	
	
	@Before
	public void setUp() throws Exception {
		
		//Set base speed
		baseSpeed = 100;
		
		//Set modifier values (speed factors)
		boostSpeedFactor = 2;
		slowSpeedFactor = 0.5f;
		
		//Create train
		steamTrain 			= new Train("Steam", 20, 30, null, baseSpeed);
		futureTrain			= new Train("Future", 100, 50, null, baseSpeed);
		
		//Create modifier resources
		steamBoost			= new TrainSpeedModifier("Booster", "", 5, 0, null, boostSpeedFactor);	//Doubles speed
		steamSlowdown		= new TrainSpeedModifier("Slowdown", "", 5, 0, null, slowSpeedFactor);	//Halves speed
		
	}

	@Test
	public void testSpeedWithoutModifier() {
		
		if (steamTrain.getSpeed() != baseSpeed){
			fail("Speed of train without modifier is not base speed");
		}
	}
	
	@Test
	public void testSpeedWithBoost(){
		steamBoost.useOnTrain(steamTrain);
		float expectedSpeed = baseSpeed * boostSpeedFactor;
		float actualSpeed = steamTrain.getSpeed();
		
		if (actualSpeed != expectedSpeed){
			fail("Actual speed of train does not equal the value expected");
		}
	}
	
	@Test
	public void testSpeedWithSlowdown(){
		steamSlowdown.useOnTrain(steamTrain);
		float expectedSpeed = baseSpeed * slowSpeedFactor;
		float actualSpeed = steamTrain.getSpeed();
		
		
		if (actualSpeed != expectedSpeed){
			fail("Actual speed of train does not equal the value expected");
		}
	}
	
	@Test
	public void testSpeedAfterRemovingBoost(){
		steamBoost.useOnTrain(steamTrain);
		steamBoost.stopUseOnTrain();
		
		float expectedSpeed = baseSpeed;
		float actualSpeed = steamTrain.getSpeed();
		
		if (actualSpeed != expectedSpeed){
			fail("Actual speed of train does not equal the value expected");
		}
	}
	
	@Test
	public void testSpeedAfterRemovingSlowdown(){
		steamSlowdown.useOnTrain(steamTrain);
		steamSlowdown.stopUseOnTrain();
		
		float expectedSpeed = baseSpeed;
		float actualSpeed = steamTrain.getSpeed();
		
		if (actualSpeed != expectedSpeed){
			fail("Actual speed of train does not equal the value expected");
		}
	}
	
	@Test
	public void testBoostOnMultipleTrains(){
		steamBoost.useOnTrain(steamTrain);
		steamBoost.useOnTrain(futureTrain);
		
		float expectedSpeed = baseSpeed;
		float actualSpeed = futureTrain.getSpeed();
		
		if (actualSpeed != expectedSpeed){
			fail("Actual speed of future train should not be changed as boost is already in use");
		}
	}

}
