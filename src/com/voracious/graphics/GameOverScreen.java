package com.voracious.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.voracious.data.Pair;
import com.voracious.data.Player;
import com.voracious.graphics.components.Screen;
import com.voracious.graphics.components.Sprite;

public class GameOverScreen extends Screen {
	private ArrayList<Pair<String,Integer>> highScores;//will be in a bin file
	private Sprite s;
	private Player p;
	private ArrayList<String> leaders=new ArrayList<String>();
	public GameOverScreen(int width, int height,Player play) {
		super(width, height);
		s=new Sprite(200,150,"gameOver.png");
		s.draw(this, -1, -1);
		p=play;
	}
	
	public void onDisplay(boolean winner,int crossProductDimensions){
		int score=p.determineScore();
		if(winner){
			changeSprite();
			score+=crossProductDimensions;
		}
		tick();
		String name=(String)JOptionPane.showInputDialog(this, "Type name or username.");
		this.readScores();
		int loc=0;
		Pair<String, Integer> tmp = new Pair<String,Integer>(name,score);
		if(highScores.size()!=0){
			for(int i=0;i<highScores.size() && score<highScores.get(i).getSecond();i++){
				loc=i;
			}
		}
		highScores.add(loc, tmp);
		this.writeScores();
		//shows the top 10 scores
		for(int i=0;i<10&&i<highScores.size();i++){
			leaders.add(highScores.get(i).getFirst()+"................"+highScores.get(i).getSecond());
		}
	}
	
	public void changeSprite(){
		s=new Sprite(200,150,"Winner.png");
		s.draw(this,-1,-1);
	}
	
	@SuppressWarnings("unchecked")
	public void readScores(){
		highScores=new ArrayList<Pair<String,Integer>>(); 
		try {
			ObjectInputStream in =new ObjectInputStream(new FileInputStream("scores.bin"));
			Pair<String,Integer> obj;
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
			//System.out.println("THERE WAS SOME ERROR IN THE FILE READING!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("THERE WAS NO INSTANCES OF PAIR!");
		}
	}
	
	public void writeScores(){
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("scores.bin"));
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
	
	public void start(){
		
	}
	
	public void stop(){
		
	}
	
	public void tick(){
		
	}
	
	public void render(){
	        
	}
	
	public ArrayList<String> getRanks(){
		return this.leaders;
	}

	public Sprite getS() {
		return this.s;
	}
}
