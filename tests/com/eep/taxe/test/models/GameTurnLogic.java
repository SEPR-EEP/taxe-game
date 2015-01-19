package com.eep.taxe.test.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Game;
import com.eep.taxe.models.Goal;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.Age.Ages;
import com.eep.taxe.models.Game.Difficulty;

public class GameTurnLogic {

	//The game
	private Game game;
	
	//The player
	private Player masterPlayer, slavePlayer;
	
	//The ages of the test
	private Ages steamAge, futureAge;
	
	// The trains of the test
	private Train steamTrainOne, steamTrainTwo;
	private Train futureTrain;
	
	@Before
	public void setUp() throws Exception {
		
		//1. Create a game
		game 				= new Game("Test", Difficulty.EASY);
		
		//2. Get players
		masterPlayer		= game.getMasterPlayer();
		slavePlayer			= game.getSlavePlayer();
		
		//3. Create two ages
		steamAge = Ages.FIRST;
		futureAge = Ages.SECOND;
		
		//4. Create some trains
		steamTrainOne 		= masterPlayer.getTrains().get(0);
		steamTrainTwo 		= slavePlayer.getTrains().get(0);
		
		
	}

	@Test
	public void testDoesPlayerHaveGoalAtFirstTurn() {
		
		if (! (masterPlayer.getCurrentGoals().size() == 1) ){
			fail("Master player should have only one starting goal");
		}
		
		if (! (slavePlayer.getCurrentGoals().size() == 1) ){
			fail("Slave player should have only one starting goal");
		}
	}
	
	@Test
	public void testDoesPlayerGetNewGoalAtNewTurn(){
		
		//End turn #1
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #2
		if (! (slavePlayer.getCurrentGoals().size() == 2) ){
			fail("Slave player should have two starting goals");
		}
		
		if (! (masterPlayer.getCurrentGoals().size() == 2) ){
			fail("Master player should have two starting goals");
		}
		
		//End turn #2
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #3
		
		if (! (slavePlayer.getCurrentGoals().size() == 3) ){
			fail("Slave player should have two starting goals");
		}
		
		if (! (masterPlayer.getCurrentGoals().size() == 3) ){
			fail("Master player should have two starting goals");
		}
	}
	
	@Test
	public void testPlayerShouldNotExceedThreeCurrentGoals(){
		
		//Iterate to turn #5
		for (int turnNo = 1; turnNo < 5; turnNo++){
			game.endTurn();	//Slave turn ends
			game.endTurn(); //Master turns ends
		}
		
		if (slavePlayer.getCurrentGoals().size() > 3){
			fail("Slave player should not exceed 3 current goals");
		}
		
		if (masterPlayer.getCurrentGoals().size() > 3){
			fail("Master player should not exceed 3 current goals");
		}
	}
	
	@Test
	public void testPlayerGetsTwoNewResourceAtNewTurn(){
		
		//End turn #1
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #2
		if (! (slavePlayer.getInventory().size() == 2) ){
			fail("Slave player should have two new resources in inventory");
		}
		
		if (! (masterPlayer.getInventory().size() == 2) ){
			fail("Master player should have two new resources in inventory");
		}
		
		//End turn #2
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #3
		if (! (slavePlayer.getInventory().size() == 4) ){
			fail("Slave player should have two new resources in inventory");
		}
		
		if (! (masterPlayer.getInventory().size() == 4) ){
			fail("Master player should have two new resources in inventory");
		}
	}
	
	@Test
	public void testPlayerShouldNotExceedSevenResources(){
		
		//Iterate to turn #5
		for (int turnNo = 1; turnNo < 5; turnNo++){
			game.endTurn();	//Slave turn ends
			game.endTurn(); //Master turns ends
		}
		
		//The actual limit is 5, as gold and metal count as 2
		
		if (slavePlayer.getInventory().size() > 5){
			fail("Slave player should not exceed 5 useable resources");
		}
		
		if (masterPlayer.getInventory().size() > 5){
			fail("Master player should not exceed 5 usable resources");
		}
	}

}
