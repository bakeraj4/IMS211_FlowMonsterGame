package com.voracious.data;

import java.util.ArrayList;

import com.voracious.graphics.components.Entity;


public class Player {
	private String name;
	private Pair<Integer,Integer> Loc;//first = x, second= y
	private ArrayList<Sword> swords=new ArrayList<Sword>();
	private ArrayList<Sheild> sheilds=new ArrayList<Sheild>();
	private ArrayList<Magic> magicks=new ArrayList<Magic>();
	private int[] stats={0,0,0,0};//hp,attack,def,mp
	private int currentHp,currentMp;
	private String fileName="playerV2.png";//"smallPlayer.png";
	//the filename is here but the actual image is store in the com.vocariuos.grpahics.componets
	private final int HEIGHT=14;//15;
	private final int WIDTH=12;//20;
	
	private int numPowerUps=5;
	
	private Entity playerE;
	private int i[]={1};
	
	private int swordNum=0;
	private int magickNum=0;
	private int sheildNum=0;
	
	private int numKills=0;
	private int NumRoomsCleared=0;
	
	public Player(String nam){
		this.setName(nam);
		this.setPlayerE(new Entity(this.getWIDTH(),this.getHEIGHT(),i,fileName));
		this.getPlayerE().setX(15);
		this.getPlayerE().setY(118);
		//give a starting sword and other items
		this.initMagicks();
		this.initSwords();
		this.initSheilds();
		int s[]={10,3,4,10};
		this.setStats(s);
		this.setCurrentHp(10);
		this.setCurrentMp(1);
		this.setLoc(new Pair<Integer,Integer>(0, 0));
	}
	
	public int determineScore(){
		return 3*this.getLoc().getFirst() + this.getLoc().getSecond();
	}
	
	private void initSheilds() {
		//TODO make sure the demensions are correct for the shields
		this.getSheilds().add(new Sheild("basicSheild.png",3,4,3));
		this.getSheilds().add(new Sheild("mediumSheild.png",7,4,6));
		this.getSheilds().add(new Sheild("hyruleShield.png",15,8,9));
		
	}

	private void initSwords() {
		this.getSwords().add(new Sword("smallSword.png",1,5,8));
		this.getSwords().add(new Sword("medSword.png",5,5,16));
		this.getSwords().add(new Sword("iceSword.png",10,5,16));
		this.getSwords().add(new Sword("kisameSword.png",15,5,17));
		this.getSwords().add(new Sword("excalibur.png",15,7,19));
	}

	/**
	 * This initializes the fire spells that the player has access to.
	 */
	private void initMagicks() {
		this.getMagicks().add(new Magic("smallFire.png",1,2,10,10));
		this.getMagicks().add(new Magic("medFire.png",3,5,40,40));
		this.getMagicks().add(new Magic("largeFire.png",5,12,60,60));
		this.getMagicks().add(new Magic("xLargeFire.png",6,20,100,100));
	}
	
	//TODO the shield adds to deffense, but if in use and touches oppontes acts like a block and takes no dammage

	/**
	 * This method determines the damage that the player will take from another scource. The differance between the total attackers power
	 * and the total defense.
	 * @param otherPower the scource's power
	 * @return the damage taken.
	 */
	public int calcDamage(int otherPower){
		int totalDef=this.getStats()[2]+this.getSheilds().get(this.getSheildNum()).getDef();
		
		if( otherPower-totalDef>0)
			return otherPower-totalDef;
		return 0;
	}
	
	public void takeDamage(int otherPower){
		//if hp==0 then dead if less than 0 make it 0 to show dead
		int damage=this.calcDamage(otherPower);
		int postHP=this.getCurrentHp()-damage;
		if(postHP<0)
			postHP=0;
		
		this.setCurrentHp(postHP);
	}
	
	private void setLoc(Pair<Integer, Integer> pair) {
		this.Loc=pair;
	}

	//121 is the bottom normal bound
	//170 is the right bound
	//17 is upper bound
	//54 is the left bound
		
	public boolean canMoveDown(){
		if(this.getPlayerE().getY()>121){
			return false;
		}
		return true;
	}
	
	public boolean canMoveUp(){
		if(this.getPlayerE().getY()<17){
			return false;
		}
		return true;
	}
	
	public boolean canMoveRight(){
		if(this.getPlayerE().getX()>174){
			return false;
		}
		return true;
	}
	
	public boolean canMoveLeft(){
		if(this.getPlayerE().getX()<15){
			return false;
		}
		return true;
	}

	
	public void setStat(int index, int value){
		this.stats[index]=value;
	}

	public int getStat(int index){
		return stats[index];
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pair<Integer,Integer> getLoc() {
		return Loc;
	}

	public void setLoc(int first, int second) {
		Loc.setFirst(first);
		Loc.setSecond(second);
	}

	public ArrayList<Sword> getSwords() {
		return swords;
	}

	public void setSwords(ArrayList<Sword> swords) {
		this.swords = swords;
	}

	public ArrayList<Sheild> getSheilds() {
		return sheilds;
	}

	public void setSheilds(ArrayList<Sheild> sheilds) {
		this.sheilds = sheilds;
	}

	public ArrayList<Magic> getMagicks() {
		return magicks;
	}

	public void setMagicks(ArrayList<Magic> magicks) {
		this.magicks = magicks;
	}

	public int[] getStats() {
		return stats;
	}

	public void setStats(int[] stats) {
		this.stats = stats;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Entity getPlayerE() {
		return playerE;
	}

	public void setPlayerE(Entity playerE) {
		this.playerE = playerE;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getNumPowerUps() {
		return numPowerUps;
	}

	public void setNumPowerUps(int numPowerUps) {
		this.numPowerUps = numPowerUps;
	}

	public int getNumKills() {
		return numKills;
	}

	public void setNumKills(int numKills) {
		this.numKills = numKills;
	}

	public int getNumRoomsCleared() {
		return NumRoomsCleared;
	}

	public void setNumRoomsCleared(int numRoomsCleared) {
		NumRoomsCleared = numRoomsCleared;
	}

	/**
	 * @return the swordNum
	 */
	public int getSwordNum() {
		return swordNum;
	}

	/**
	 * @param swordNum the swordNum to set
	 */
	public void setSwordNum(int swordNum) {
		this.swordNum = swordNum;
	}

	/**
	 * @return the sheildNum
	 */
	public int getSheildNum() {
		return sheildNum;
	}

	/**
	 * @param sheildNum the sheildNum to set
	 */
	public void setSheildNum(int sheildNum) {
		this.sheildNum = sheildNum;
	}

	/**
	 * @return the magickNum
	 */
	public int getMagickNum() {
		return magickNum;
	}

	/**
	 * @param magickNum the magickNum to set
	 */
	public void setMagickNum(int magickNum) {
		this.magickNum = magickNum;
	}

	/**
	 * @return the currentMp
	 */
	public int getCurrentMp() {
		return currentMp;
	}

	/**
	 * @param currentMp the currentMp to set
	 */
	public void setCurrentMp(int currentMp) {
		this.currentMp = currentMp;
	}

	/**
	 * @return the currentHp
	 */
	public int getCurrentHp() {
		return currentHp;
	}

	/**
	 * @param currentHp the currentHp to set
	 */
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	/**
	 * Fully Heals the player's hp and mp
	 */
	public void fullHeal(){
		this.setCurrentHp(this.getStats()[0]);
		this.setCurrentMp(this.getStats()[3]);
	}
}

