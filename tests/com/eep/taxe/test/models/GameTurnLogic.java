package com.eep.taxe.test.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.GameClient;
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
		
		assertEquals("Slave player should have only one starting goal", 1, slavePlayer.getCurrentGoals().size(), 0);
		
		assertEquals("Master player should have only one starting goal", 1, masterPlayer.getCurrentGoals().size(), 0);
	}
	
	@Test
	public void testDoesPlayerGetNewGoalAtNewTurn(){
		
		//End turn #1
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #2
		assertEquals("Slave player should have two starting goal", 2, slavePlayer.getCurrentGoals().size(), 0);
		
		assertEquals("Master player should have two starting goal", 2, masterPlayer.getCurrentGoals().size(), 0);
		
		//End turn #2
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #3
		
		assertEquals("Slave player should have three starting goal", 3, slavePlayer.getCurrentGoals().size(), 0);
		
		assertEquals("Master player should have three starting goal", 3, masterPlayer.getCurrentGoals().size(), 0);
	}
	
	@Test
	public void testPlayerShouldNotExceedThreeCurrentGoals(){
		
		//Iterate to turn #5
		for (int turnNo = 1; turnNo < 5; turnNo++){
			game.endTurn();	//Slave turn ends
			game.endTurn(); //Master turns ends
		}
		
		
		assertFalse("Slave player should not exceed 3 current goals", slavePlayer.getCurrentGoals().size() > 3);
		
		assertFalse("Master player should not exceed 3 starting goal", masterPlayer.getCurrentGoals().size() > 3);
		
	}
	
	@Test
	public void testPlayerGetsTwoNewResourceAtNewTurn(){
		
		//End turn #1
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #2
		
		assertEquals("Slave player should have two new resources in inventory", 2, slavePlayer.getInventory().size(), 0);
		
		assertEquals("Master player should have two new resources in inventory", 2, masterPlayer.getInventory().size(), 0);
		
		//End turn #2
		game.endTurn();	//Slave turn ends
		game.endTurn(); //Master turns ends
		
		//Start of turn #3
		
		assertEquals("Slave player should have two new resources in inventory", 4, slavePlayer.getInventory().size(), 0);
		
		assertEquals("Master player should have two new resources in inventory", 4, masterPlayer.getInventory().size(), 0);
	}
	
	@Test
	public void testPlayerShouldNotExceedSevenResources(){
		
		//Iterate to turn #5
		for (int turnNo = 1; turnNo < 5; turnNo++){
			game.endTurn();	//Slave turn ends
			game.endTurn(); //Master turns ends
		}
		
		//The actual limit is 5, as gold and metal count as 2
		
		assertFalse("Slave player should not exceed 5 useable resources", slavePlayer.getInventory().size() > 5);
		
		assertFalse("Master player should not exceed 5 usable resources", masterPlayer.getInventory().size() > 5);
	}
	
	@Test
	public void testPlayerSetsNickname(){
		String nicknameOne = "Test Player 1";
		String nicknameTwo = "Test Player 2";
		slavePlayer.setNickname(nicknameOne);
		masterPlayer.setNickname(nicknameTwo);
		
		assertSame("Slave player could not set nickanme", nicknameOne, slavePlayer.getNickname());
		
		assertSame("Master player could not set nickanme", nicknameTwo, masterPlayer.getNickname());
	}
	
	@Test
	public void testRoundRobinTurns(){
		
		//Test for 5 turns
		for (int turnNo = 1; turnNo < 5; turnNo++){
			//At start of game current turn should be slave player
			assertTrue("First player is not slave player", game.getCurrentRole() == GameClient.Role.SLAVE);
			
			//End slave player's turn
			game.endTurn();
			
			assertTrue("Second player is not master player", game.getCurrentRole() == GameClient.Role.MASTER);
			
			//End master player's turn
			game.endTurn();
		}
		
		
	}

}
