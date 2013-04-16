package com.voracious.data;

import com.voracious.graphics.components.Entity;

public class Monster {
	private String fileName;
	private String movement;
	private int hp, def,attk;
	private Entity E;
	
	public Monster(String name,int HP, int DEF,int ATTK,int width, int height,String move){//starting def= 7
		this.setDef(DEF);
		this.setFileName(name);
		this.setHp(HP);
		int[]i={1};
		this.setAttk(ATTK);
		this.setE(new Entity(width,height,i,this.getFileName()));
		this.setMovement(move);
	}
	
	public void takeDamage(int playerTotalPower){
		//if hp==0 then dead if less than 0 make it 0 to show dead
		int damage=this.calcdamage(playerTotalPower);
		int postHP=this.getHp()-damage;
		if(postHP<0)
			postHP=0;
		this.setHp(postHP);
	}
	
	public int calcdamage(int playerTotalPower){
		int totalDef=this.getDef();
		if( playerTotalPower-totalDef>0)
			return playerTotalPower-totalDef;
		return 0;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the def
	 */
	public int getDef() {
		return def;
	}

	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the e
	 */
	public Entity getE() {
		return E;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(Entity e) {
		E = e;
	}

	/**
	 * @return the movement
	 */
	public String getMovement() {
		return movement;
	}

	/**
	 * @param movement the movement to set
	 */
	public void setMovement(String movement) {
		this.movement = movement;
	}

	/**
	 * @return the attk
	 */
	public int getAttk() {
		return attk;
	}

	/**
	 * @param attk the attk to set
	 */
	public void setAttk(int attk) {
		this.attk = attk;
	}
	
}
