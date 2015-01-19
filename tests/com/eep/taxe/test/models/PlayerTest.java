package com.eep.taxe.test.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Age;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.TrainSpeedModifier;
import com.eep.taxe.models.Age.Ages;
import com.eep.taxe.models.Game.Difficulty;

public class PlayerTest {

	//The game
	private Game game;
	
	//The player
	private Player playerOne, playerTwo;
	
	//The ages of the test
	private Ages steamAge, futureAge;
	
	// The trains of the test
	private Train steamTrainOne, steamTrainTwo;
	private Train futureTrain;
	
	//The usable resources
	private TrainSpeedModifier steamBoost, steamSlowdown;
	
	@Before
	public void setUp() throws Exception {
		
		//1. Create a game
		game 				= new Game("Test", Difficulty.EASY);
		
		//2. Create a player
		playerOne 			= new Player();
		playerTwo			= new Player();	
		
		//3. Create two ages
		steamAge = Ages.FIRST;
		futureAge = Ages.SECOND;
		
		//4. Create some trains
		steamTrainOne 		= new Train("Steam", null, 20, 30, steamAge, 100, null);
		steamTrainTwo 		= new Train("Steam", null, 20, 30, steamAge, 100, null);
		futureTrain			= new Train("Future", null, 400, 300, futureAge, 500, null);

		//5. Create some usable resources
		steamBoost 			= new TrainSpeedModifier("Booster", "", 100, 0, steamAge, 2);	//Doubles speed
		steamSlowdown 		= new TrainSpeedModifier("Slowdown", "", 200, 0, steamAge, 0.5f);	//Doubles speed
		
		
		
	}

	@Test
	public void testValidBuyFutureTrain() {
		//Give player One 500 gold and 500 metal
		playerOne.addGold(500);
		playerOne.addMetal(500);
		
		//Player One should be able to buy future train
		if (! playerOne.canBuyTrain(futureTrain)){
			fail("Player one has 500 gold and metal - train only costs 400 gold and 300 metal");
		}
		
		//Buy train
		playerOne.buyTrain(futureTrain);
		
		if (! playerOne.getTrains().contains(futureTrain)){
			fail("Player should have train");
		}
		
		//Test gold and metal levels
		if (playerOne.getGoldQuantity() != 100){
			fail("Player should only have 100 gold left");
		}
		
		if (playerOne.getMetalQuantity() != 200){
			fail("Player should only have 200 metal left");
		}
		
	}
	
	@Test
	public void testInvalidBuyFutureTrain() {
		//Give player Two 300 gold and 200 metal
		playerTwo.addGold(200);
		playerTwo.addMetal(100);
		
		//Player Two should not be able to buy future train
		if (playerTwo.canBuyTrain(futureTrain)){
			fail("Player two has 200 gold and 100 metal - train costs 400 gold and 300 metal");
		}
		
		//Buy train
		playerTwo.buyTrain(futureTrain);
		
		if (playerTwo.getTrains().contains(futureTrain)){
			fail("Player should not have train");
		}
		
		//Test gold and metal levels
		if (playerTwo.getGoldQuantity() != 200){
			fail("Player should still have 200 gold left");
		}
		
		if (playerTwo.getMetalQuantity() != 100){
			fail("Player should still have 100 metal left");
		}
		
	}
	
	@Test
	public void testExtremeBuyFutureTrain() {
		//Give player One 400 gold and 300 metal
		playerOne.addGold(400);
		playerOne.addMetal(300);
		
		//Player One should be able to buy future train
		if (! playerOne.canBuyTrain(futureTrain)){
			fail("Player one has 400 gold and 300 metal - train only costs 400 gold and 300 metal");
		}
		
		//Buy train
		playerOne.buyTrain(futureTrain);
		
		if (! playerOne.getTrains().contains(futureTrain)){
			fail("Player should have train");
		}
		
		//Test gold and metal levels
		if (playerOne.getGoldQuantity() != 0){
			fail("Player should only have 0 gold left");
		}
		
		if (playerOne.getMetalQuantity() != 0){
			fail("Player should only have 0 metal left");
		}
		
	}
	
	@Test
	public void testValidBuyBooster(){
		//Give player One 500 gold
		playerOne.addGold(500);
		
		//Player One should be able to buy steam booster
		if (! playerOne.canBuy(steamBoost) ){
			fail("Player one has 500 gold, steam booster cost 100");
		}
		
		//Buy booster
		playerOne.buy(steamBoost);
		
		//Test if player One has resource
		if (! playerOne.hasResource(steamBoost) ){
			fail("Player should have resource");
		}
		
	}
	
	@Test
	public void testInvalidBuyBooster(){
		//Give player One 50 gold
		playerOne.addGold(50);
		
		//Player One should not be able to buy steam booster
		if (playerOne.canBuy(steamBoost) ){
			fail("Player one has 50 gold, steam booster cost 100");
		}
		
		//Buy booster
		playerOne.buy(steamBoost);
		
		//Test if player One does not have resource
		if (playerOne.hasResource(steamBoost) ){
			fail("Player should not have resource");
		}
		
	}
	
	@Test
	public void testValidBuySlowdownAndUseOnOtherPlayer(){
		//Give both players spendable resources
		playerOne.addGold(500);
		playerTwo.addGold(500);
		playerTwo.addMetal(500);
		
		//PlayerOne buys steam slowdown
		playerOne.buy(steamSlowdown);
		
		//PlayerTwo buys steam train
		playerTwo.buyTrain(steamTrainTwo);
		
		//PlayerOne uses steam slowdown on player two's train
		steamSlowdown.useOnTrain(steamTrainTwo);
		
		//Player one's steam slowdown should have halved the speed of player two's steam train
		if (steamTrainTwo.getBaseSpeed() / 2 != steamTrainTwo.getSpeed() ){
			fail("Steam slowdown has not been used on other player");
		}
		
	}
	
	@Test
	public void testInvalidBuySlowdownAndUseOnSelf(){
		//Give both players spendable resources
		playerOne.addGold(1000);
		playerOne.addMetal(500);
		
		//PlayerOne buys steam slowdown
		playerOne.buy(steamSlowdown);
			
		//PlayerOne buys steam train
		playerOne.buyTrain(steamTrainOne);
			
		//PlayerOne uses steam slowdown on player one's train
		steamSlowdown.useOnTrain(steamTrainOne);
			
		//Player one's steam slowdown should not have halved the speed of player one's steam train
		if (steamTrainOne.getBaseSpeed() != steamTrainOne.getSpeed() ){
			fail("Steam slowdown should not have been used on their own train");
		}	
		
	}
	
	@Test
	public void testInvalidBuySpeedBoostAndUseOnOtherPlayer(){
		//Give both players spendable resources
		playerOne.addGold(500);
		playerTwo.addGold(500);
		playerTwo.addMetal(500);
		
		//PlayerOne buys steam boost
		playerOne.buy(steamBoost);
			
		//PlayerTwo buys steam train
		playerTwo.buyTrain(steamTrainTwo);
			
		//PlayerOne uses steam boost on player one's train
		steamBoost.useOnTrain(steamTrainTwo);
			
		//Player one's steam boost should not have been used on Player Two's steam train
		if (steamTrainTwo.getBaseSpeed() != steamTrainTwo.getSpeed() ){
			fail("Steam boost should not have been used on opponent's train");
		}	
		
	}
	
	
}
