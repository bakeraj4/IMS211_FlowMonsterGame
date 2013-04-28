package com.voracious.data;

import java.util.ArrayList;


public class GameData {
	
	private boolean complete[][];
	private ArrayList<ArrayList<ArrayList<Monster>>> monsters;
	
	public GameData(){
		this.setMonsters(new ArrayList<ArrayList<ArrayList<Monster>>>());
		for(int i=0;i<5;i++){
			this.getMonsters().add(new ArrayList<ArrayList<Monster>>());
			for(int j=0;j<5;j++){
				this.getMonsters().get(i).add(new ArrayList<Monster>());
			}
		}
		//0,0
		this.getMonsters().get(0).get(0).add(new Monster("babyDragonV2.png", 10, 3, 7, 55, 49,"Close"));
		this.getMonsters().get(0).get(0).get(0).getE().setX(90);
		this.getMonsters().get(0).get(0).get(0).getE().setY(60);
		
		//0,1
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",11,3,8,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(0).getE().setX(90);
		this.getMonsters().get(0).get(1).get(0).getE().setY(60);
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",10,3,8,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(1).getE().setX(40);
		this.getMonsters().get(0).get(1).get(1).getE().setY(70);
		this.getMonsters().get(0).get(1).add(new Monster("bat.png",10,3,8,10,7,"Rand"));
		this.getMonsters().get(0).get(1).get(2).getE().setX(100);
		this.getMonsters().get(0).get(1).get(2).getE().setY(90);
		
		//0,2
		this.getMonsters().get(0).get(2).add(new Monster("bat.png",12,4,3,10,7,"Rand"));
		this.getMonsters().get(0).get(2).get(0).getE().setX(90);
		this.getMonsters().get(0).get(2).get(0).getE().setY(60);
		this.getMonsters().get(0).get(2).add(new Monster("bat.png",12,4,3,10,7,"Rand"));
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
		
		//0,4
		this.getMonsters().get(0).get(4).add(new Monster("skeleton.png",35,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(0).get(4).get(0).getE().setX(30);
		this.getMonsters().get(0).get(4).get(0).getE().setY(121-10);
		this.getMonsters().get(0).get(4).add(new Monster("skeleton.png",53,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(0).get(4).get(1).getE().setX(150);
		this.getMonsters().get(0).get(4).get(1).getE().setY(17-10);
		
		//1,0
		this.getMonsters().get(1).get(0).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(1).get(0).get(0).getE().setX(45);
		this.getMonsters().get(1).get(0).get(0).getE().setY(65);
		this.getMonsters().get(1).get(0).add(new Monster("antlion.png",10,3,7,15,10,"Rand"));
		this.getMonsters().get(1).get(0).get(1).getE().setX(105);
		this.getMonsters().get(1).get(0).get(1).getE().setY(105);
		
		//1,1
		this.getMonsters().get(1).get(1).add(new Monster("PancakeRay.png",15,7,8,100,90,"Close"));
		this.getMonsters().get(1).get(1).get(0).getE().setX(100);
		this.getMonsters().get(1).get(1).get(0).getE().setY(15);
		
		//1,2
		this.getMonsters().get(1).get(2).add(new Monster("babyDragonV2.png", 30, 8, 10, 55, 49,"Close"));
		this.getMonsters().get(1).get(2).get(0).getE().setX(90);
		this.getMonsters().get(1).get(2).get(0).getE().setY(60);
		
		//1,3
		this.getMonsters().get(1).get(3).add(new Monster("skeleton.png",35,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(1).get(3).get(0).getE().setX(30);
		this.getMonsters().get(1).get(3).get(0).getE().setY(121-10);
		this.getMonsters().get(1).get(3).add(new Monster("skeleton.png",53,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(1).get(3).get(1).getE().setX(150);
		this.getMonsters().get(1).get(3).get(1).getE().setY(17-10);
		this.getMonsters().get(1).get(3).add(new Monster("skeleton.png",53,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(1).get(3).get(2).getE().setX(100);
		this.getMonsters().get(1).get(3).get(2).getE().setY(121-10);
		this.getMonsters().get(1).get(3).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(1).get(3).get(3).getE().setX(45);
		this.getMonsters().get(1).get(3).get(3).getE().setY(65);
		this.getMonsters().get(1).get(3).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(1).get(3).get(3).getE().setX(105);
		this.getMonsters().get(1).get(3).get(3).getE().setY(105);
		
		//1,4
		this.getMonsters().get(1).get(4).add(new Monster("babyDragonV2.png", 30, 8, 10, 55, 49,"Close"));
		this.getMonsters().get(1).get(4).get(0).getE().setX(90);
		this.getMonsters().get(1).get(4).get(0).getE().setY(60);
		
		//2,0
		this.getMonsters().get(2).get(0).add(new Monster("tonberry.png",20,5,10,60,40,"Close"));
		this.getMonsters().get(2).get(0).get(0).getE().setX(100);
		this.getMonsters().get(2).get(0).get(0).getE().setY(29);
		
		//2,1
		this.getMonsters().get(2).get(1).add(new Monster("goblin.png",21,7,11,25,25,"Close"));
		this.getMonsters().get(2).get(1).get(0).getE().setX(130);
		this.getMonsters().get(2).get(1).get(0).getE().setY(20);
		this.getMonsters().get(2).get(1).add(new Monster("goblin.png",21,7,11,25,25,"Close"));
		this.getMonsters().get(2).get(1).get(1).getE().setX(130);
		this.getMonsters().get(2).get(1).get(1).getE().setY(130);
		this.getMonsters().get(2).get(1).add(new Monster("goblin.png",21,7,11,25,25,"Close"));
		this.getMonsters().get(2).get(1).get(2).getE().setX(40);
		this.getMonsters().get(2).get(1).get(2).getE().setY(20);
		this.getMonsters().get(2).get(1).add(new Monster("chocobo.png",10,8,6,20,30,"OutLineCCW"));
		this.getMonsters().get(2).get(1).get(3).getE().setX(130);
		this.getMonsters().get(2).get(1).get(3).getE().setY(121-15);

		//2,2
		this.getMonsters().get(2).get(2).add(new Monster("wasp.png",21,7,11,22,18,"Close"));
		this.getMonsters().get(2).get(2).get(0).getE().setX(90);
		this.getMonsters().get(2).get(2).get(0).getE().setY(20);
		this.getMonsters().get(2).get(2).add(new Monster("wasp.png",21,7,11,22,18,"Close"));
		this.getMonsters().get(2).get(2).get(1).getE().setX(90);
		this.getMonsters().get(2).get(2).get(1).getE().setY(130);
		this.getMonsters().get(2).get(2).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(2).get(2).get(2).getE().setX(50);
		this.getMonsters().get(2).get(2).get(2).getE().setY(40);
		this.getMonsters().get(2).get(2).add(new Monster("antlion.png",10,3,8,15,10,"Rand"));
		this.getMonsters().get(2).get(2).get(3).getE().setX(50);
		this.getMonsters().get(2).get(2).get(3).getE().setY(60);

		//2,3
		this.getMonsters().get(2).get(3).add(new Monster("tortle.png",16,5,11,35,24,"Rand"));
		this.getMonsters().get(2).get(3).get(0).getE().setX(80);
		this.getMonsters().get(2).get(3).get(0).getE().setY(30);
		this.getMonsters().get(2).get(3).add(new Monster("tortle.png",16,5,11,35,24,"Rand"));
		this.getMonsters().get(2).get(3).get(1).getE().setX(30);
		this.getMonsters().get(2).get(3).get(1).getE().setY(80);
		this.getMonsters().get(2).get(3).add(new Monster("ogre.png",16,5,11,20,20,"Close"));
		this.getMonsters().get(2).get(3).get(2).getE().setX(121);
		this.getMonsters().get(2).get(3).get(2).getE().setY(45);

		//2,4
		this.getMonsters().get(2).get(4).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(2).get(4).get(0).getE().setX(75);
		this.getMonsters().get(2).get(4).get(0).getE().setY(30);
		this.getMonsters().get(2).get(4).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(2).get(4).get(1).getE().setX(30);
		this.getMonsters().get(2).get(4).get(1).getE().setY(90);
		this.getMonsters().get(2).get(4).add(new Monster("ghost.png",16,5,11,10,10,"Fig8Knot"));
		this.getMonsters().get(2).get(4).get(2).getE().setX(150);
		this.getMonsters().get(2).get(4).get(2).getE().setY(100);
		this.getMonsters().get(2).get(4).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(2).get(4).get(3).getE().setX(120);
		this.getMonsters().get(2).get(4).get(3).getE().setY(70);
		this.getMonsters().get(2).get(4).add(new Monster("skeleton.png",35,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(2).get(4).get(4).getE().setX(30);
		this.getMonsters().get(2).get(4).get(4).getE().setY(121-10);
		this.getMonsters().get(2).get(4).add(new Monster("skeleton.png",53,5,12,20,20,"OutLineCW"));
		this.getMonsters().get(2).get(4).get(5).getE().setX(150);
		this.getMonsters().get(2).get(4).get(5).getE().setY(17-10);

		//3,0
		this.getMonsters().get(3).get(0).add(new Monster("PancakeRay.png",18,6,7,100,90,"Close"));
		this.getMonsters().get(3).get(0).get(0).getE().setX(90);
		this.getMonsters().get(3).get(0).get(0).getE().setY(60);
		
		//3,1
		this.getMonsters().get(3).get(1).add(new Monster("ogre.png",16,5,11,20,20,"Close"));
		this.getMonsters().get(3).get(1).get(0).getE().setX(121);
		this.getMonsters().get(3).get(1).get(0).getE().setY(45);
		this.getMonsters().get(3).get(1).add(new Monster("ogre.png",16,5,11,20,20,"Close"));
		this.getMonsters().get(3).get(1).get(1).getE().setX(34);
		this.getMonsters().get(3).get(1).get(1).getE().setY(45);
		this.getMonsters().get(3).get(1).add(new Monster("goblin.png",21,7,11,25,25,"Rand"));
		this.getMonsters().get(3).get(1).get(2).getE().setX(40);
		this.getMonsters().get(3).get(1).get(2).getE().setY(20);
		
		//3,2
		this.getMonsters().get(3).get(2).add(new Monster("hydra.png",21,7,11,35,30,"Rand"));
		this.getMonsters().get(3).get(2).get(0).getE().setX(40);
		this.getMonsters().get(3).get(2).get(0).getE().setY(20);
		this.getMonsters().get(3).get(2).add(new Monster("hydra.png",21,7,11,35,30,"Rand"));
		this.getMonsters().get(3).get(2).get(1).getE().setX(70);
		this.getMonsters().get(3).get(2).get(1).getE().setY(130);
		this.getMonsters().get(3).get(2).add(new Monster("hydra.png",21,7,11,35,30,"OutLineCCW"));
		this.getMonsters().get(3).get(2).get(2).getE().setX(130);
		this.getMonsters().get(3).get(2).get(2).getE().setY(121-15);
		this.getMonsters().get(3).get(2).add(new Monster("hydra.png",21,7,11,35,30,"OutLineCCW"));
		this.getMonsters().get(3).get(2).get(3).getE().setX(130);
		this.getMonsters().get(3).get(2).get(3).getE().setY(17-15);
		
		
		//3,3
		this.getMonsters().get(3).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(3).get(3).get(0).getE().setX(75);
		this.getMonsters().get(3).get(3).get(0).getE().setY(30);
		this.getMonsters().get(3).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(3).get(3).get(1).getE().setX(30);
		this.getMonsters().get(3).get(3).get(1).getE().setY(90);
		this.getMonsters().get(3).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Fig8Knot"));
		this.getMonsters().get(3).get(3).get(2).getE().setX(150);
		this.getMonsters().get(3).get(3).get(2).getE().setY(100);
		this.getMonsters().get(3).get(3).add(new Monster("ghost.png",16,5,11,10,10,"Rand"));
		this.getMonsters().get(3).get(3).get(3).getE().setX(120);
		this.getMonsters().get(3).get(3).get(3).getE().setY(70);
		this.getMonsters().get(3).get(3).add(new Monster("waterDragon.png",53,5,12,50,60,"Fig8Knot"));
		this.getMonsters().get(3).get(3).get(4).getE().setX(20);
		this.getMonsters().get(3).get(3).get(4).getE().setY(30);
		
		//3,4
		this.getMonsters().get(3).get(4).add(new Monster("fireBird.png",53,5,12,75,50,"Fig8Knot"));
		this.getMonsters().get(3).get(4).get(0).getE().setX(20);
		this.getMonsters().get(3).get(4).get(0).getE().setY(30);
		
		//4,0
		this.getMonsters().get(4).get(0).add(new Monster("waterDragon.png",53,5,12,50,60,"Close"));
		this.getMonsters().get(4).get(0).get(0).getE().setX(20);
		this.getMonsters().get(4).get(0).get(0).getE().setY(30);
		this.getMonsters().get(4).get(0).add(new Monster("waterDragon.png",53,5,12,50,60,"Close"));
		this.getMonsters().get(4).get(0).get(1).getE().setX(140);
		this.getMonsters().get(4).get(0).get(1).getE().setY(70);

		//4,1
		
		
		//4,2
		this.getMonsters().get(4).get(2).add(new Monster("fireBird.png",53,5,12,75,50,"Fig8Knot"));
		this.getMonsters().get(4).get(2).get(0).getE().setX(20);
		this.getMonsters().get(4).get(2).get(0).getE().setY(30);
		this.getMonsters().get(4).get(2).add(new Monster("waterDragon.png",53,5,12,50,60,"Close"));
		this.getMonsters().get(4).get(2).get(1).getE().setX(20);
		this.getMonsters().get(4).get(2).get(1).getE().setY(30);
		
		//4,3
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(0).getE().setX(40);
		this.getMonsters().get(4).get(3).get(0).getE().setY(60);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(1).getE().setX(40);
		this.getMonsters().get(4).get(3).get(1).getE().setY(100);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(2).getE().setX(100);
		this.getMonsters().get(4).get(3).get(2).getE().setY(100);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(3).getE().setX(90);
		this.getMonsters().get(4).get(3).get(3).getE().setY(90);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(4).getE().setX(50);
		this.getMonsters().get(4).get(3).get(4).getE().setY(30);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(5).getE().setX(100);
		this.getMonsters().get(4).get(3).get(5).getE().setY(20);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(6).getE().setX(50);
		this.getMonsters().get(4).get(3).get(6).getE().setY(100);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(7).getE().setX(40);
		this.getMonsters().get(4).get(3).get(7).getE().setY(70);
		this.getMonsters().get(4).get(3).add(new Monster("bat.png",10,0,1,10,7,"Rand"));
		this.getMonsters().get(4).get(3).get(8).getE().setX(100);
		this.getMonsters().get(4).get(3).get(8).getE().setY(90);
		
		//4,4
		this.getMonsters().get(4).get(4).add(new Monster("waterDragon.png",53,5,12,50,60,"Fig8Knot"));
		this.getMonsters().get(4).get(4).get(0).getE().setX(80);
		this.getMonsters().get(4).get(4).get(0).getE().setY(80);
		this.getMonsters().get(4).get(4).add(new Monster("waterDragon.png",53,5,12,50,60,"Rand"));
		this.getMonsters().get(4).get(4).get(1).getE().setX(100);
		this.getMonsters().get(4).get(4).get(1).getE().setY(40);
		this.getMonsters().get(4).get(4).add(new Monster("waterDragon.png",53,5,12,50,60,"Close"));
		this.getMonsters().get(4).get(4).get(2).getE().setX(60);
		this.getMonsters().get(4).get(4).get(2).getE().setY(30);
		
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
