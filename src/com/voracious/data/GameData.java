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
		//0,0
		this.getMonsters().get(0).get(0).add(new Monster("babyDragonV2.png", 10, 3, 7, 55, 49,"Close"));
		this.getMonsters().get(0).get(0).get(0).getE().setX(90);
		this.getMonsters().get(0).get(0).get(0).getE().setY(60);
		
		//1,0
		this.getMonsters().get(1).get(0).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(1).get(0).get(0).getE().setX(45);
		this.getMonsters().get(1).get(0).get(0).getE().setY(65);
		this.getMonsters().get(1).get(0).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(1).get(0).get(1).getE().setX(105);
		this.getMonsters().get(1).get(0).get(1).getE().setY(105);
		
		//0,1
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(0).getE().setX(90);
		this.getMonsters().get(0).get(1).get(0).getE().setY(60);
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(1).getE().setX(40);
		this.getMonsters().get(0).get(1).get(1).getE().setY(70);
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(2).getE().setX(100);
		this.getMonsters().get(0).get(1).get(2).getE().setY(90);
		
		//1,1
		this.getMonsters().get(1).get(1).add(new Monster("PancakeRay.png",10,3,7,100,90,"Close"));
		this.getMonsters().get(1).get(1).get(0).getE().setX(90);
		this.getMonsters().get(1).get(1).get(0).getE().setY(60);
		
		//2,0
		this.getMonsters().get(2).get(0).add(new Monster("tonberry.png",20,5,10,60,40,"Close"));
		this.getMonsters().get(2).get(0).get(0).getE().setX(100);
		this.getMonsters().get(2).get(0).get(0).getE().setY(29);
		
		//0,2
		this.getMonsters().get(0).get(2).add(new Monster("bat.png",12,1,3,10,7,"Rand"));
		this.getMonsters().get(0).get(2).get(0).getE().setX(90);
		this.getMonsters().get(0).get(2).get(0).getE().setY(60);
		this.getMonsters().get(0).get(2).add(new Monster("bat.png",12,1,3,10,7,"Rand"));
		this.getMonsters().get(0).get(2).get(1).getE().setX(40);
		this.getMonsters().get(0).get(2).get(1).getE().setY(70);
		this.getMonsters().get(0).get(2).add(new Monster("antlion.png",10,3,8,15,10,"Close"));
		this.getMonsters().get(0).get(2).get(0).getE().setX(85);
		this.getMonsters().get(0).get(2).get(0).getE().setY(95);
		
		//0,3
		this.getMonsters().get(0).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(0).get(3).get(0).getE().setX(75);
		this.getMonsters().get(0).get(3).get(0).getE().setY(30);
		this.getMonsters().get(0).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(0).get(3).get(1).getE().setX(30);
		this.getMonsters().get(0).get(3).get(1).getE().setY(90);
		this.getMonsters().get(0).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Fig8Knot"));
		this.getMonsters().get(0).get(3).get(2).getE().setX(150);
		this.getMonsters().get(0).get(3).get(2).getE().setY(100);
		this.getMonsters().get(0).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(0).get(3).get(3).getE().setX(120);
		this.getMonsters().get(0).get(3).get(3).getE().setY(70);
		
		
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
