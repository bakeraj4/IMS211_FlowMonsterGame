package com.voracious.data;

import java.util.ArrayList;


public class GameData {
	
	private boolean complete[][];
	private ArrayList<ArrayList<ArrayList<Monster>>> monsters;
	
	public GameData(){
		this.setMonsters(new ArrayList<ArrayList<ArrayList<Monster>>>());
		for(int i=0;i<10;i++){
			this.getMonsters().add(new ArrayList<ArrayList<Monster>>());
			for(int j=0;j<10;j++){
				this.getMonsters().get(i).add(new ArrayList<Monster>());
			}
		}
		this.getMonsters().get(0).get(0).add(new Monster("babyDragon.png", 10, 10, 8, 80, 60,"Close"));
		this.getMonsters().get(0).get(0).get(0).getE().setX(90);
		this.getMonsters().get(0).get(0).get(0).getE().setY(60);
		//this.getMonsters().get(0).get(0).get(0).getE().setY(121-(((this.getMonsters().get(0).get(0).get(0).getE().getHeight())/2)));
		//this.getMonsters().get(0).get(0).get(0).getE().setY(17-((this.getMonsters().get(0).get(0).get(0).getE().getHeight())/2));
		
		
		
		this.getMonsters().get(1).get(0).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		
		//Fig8Knot
		
		int length=this.getMonsters().size();
		boolean[][]tmp=new boolean[length][length];
		for(int i=0;i<tmp.length;i++){
			for(int j=0;j<tmp[i].length;j++){
				tmp[i][j]=false;
			}
		}
		this.setComplete(tmp);
	}

	public boolean[][] getComplete() {
		return complete;
	}

	public void setComplete(boolean complete[][]) {
		this.complete = complete;
	}
	public void setComplete(int x, int y,boolean val) {
		this.complete[x][y]=val;
	}

	/**
	 * @return the monsters
	 */
	public ArrayList<ArrayList<ArrayList<Monster>>> getMonsters() {
		return monsters;
	}

	/**
	 * @param monsters the monsters to set
	 */
	public void setMonsters(ArrayList<ArrayList<ArrayList<Monster>>> monsters) {
		this.monsters = monsters;
	}

}
