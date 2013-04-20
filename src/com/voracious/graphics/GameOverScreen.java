package com.voracious.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	
	public void readScores(){
		highScores=new ArrayList<Pair<String,Integer>>(); 
		try {
			ObjectInputStream in =new ObjectInputStream(new FileInputStream("/scores.bin"));
			Pair<String,Integer> obj=(Pair<String, Integer>) in.readObject();
			while((obj=(Pair<String, Integer>) in.readObject())!=null){
				highScores.add(obj);
				//i am assuming that the previous scores are already sorted.
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			//file doesn't exist so no high scores exist yet
		} 
		catch (IOException e) {
			System.out.println("THERE WAS SOME ERROR IN THE FILE READING!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("THERE WAS NO INSTANCES OF PAIR!");
		}
	}
	
	public void writeScores(){
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("/scores.bin"));
			for(int i=0;i<highScores.size();i++){
				out.writeObject(highScores.get(i));
			}
			out.close();
		} 
		catch (FileNotFoundException e) {
			//file is in the project 
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
