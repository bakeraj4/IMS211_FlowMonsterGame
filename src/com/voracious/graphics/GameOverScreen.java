package com.voracious.graphics;

import java.util.ArrayList;

import com.voracious.data.Pair;
import com.voracious.data.Player;
import com.voracious.graphics.components.Screen;

public class GameOverScreen extends Screen {
	private ArrayList<Pair<String,Integer>> highScores;//will be in a bin file
	public GameOverScreen(int width, int height,Player play) {
		super(width, height);
		//draw the back ground image
		//inits array list frombin file
		//add the player's score and get the name
			//sorted by high to low scoress
		//resaves the bin file
		//shows the top 10 scores
	}

}
