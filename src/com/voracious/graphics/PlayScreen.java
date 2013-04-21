package com.voracious.graphics;

import java.awt.event.KeyEvent;

import com.voracious.data.GameData;
import com.voracious.data.Magic;
import com.voracious.data.Monster;
import com.voracious.data.Player;
import com.voracious.data.Sheild;
import com.voracious.data.Sword;
import com.voracious.graphics.components.Entity;
import com.voracious.graphics.components.Screen;
import com.voracious.graphics.components.Sprite;

/**
 * 
 * @author Aaron Baker
 *
 */

public class PlayScreen extends Screen {
	private Player player;
	Sprite s;
	private boolean pointGiven=false;
	private boolean swap;
	private GameData data;
	
	private boolean displaySpell=false;
	private boolean displaySword=false;
	private boolean displaySheild=false;
	
	private boolean testBacktrack=false;
	
	private String mode="sword";
	
	long startTime;
	
	int i[]={1};
	Entity N_door,S_door,E_door,W_door;
	
	
	public PlayScreen(int width, int height) {

		super(width, height);

        s= new Sprite(200,150,"officalFloor.png");
        s.draw(this, 0, 0);
        
        this.setData(new GameData());

        shutAllDoors();
  
		player =new Player("Aaron");
		
		this.startTime=System.nanoTime();
	}
	
	@Override
	public void render() {
		s.draw(this, 0, 0);
		player.getPlayerE().draw(this);
		
						
		N_door.setX(90);
	    N_door.setY(2);
	    N_door.draw(this);
	    
	    S_door.setX(90);
	    S_door.setY(134);
	    S_door.draw(this);
	    
	    W_door.setX(1);
	    W_door.setY(67);
	    W_door.draw(this);
	    
	    E_door.setX(186);
	    E_door.setY(67);
	    E_door.draw(this);

	    
	    if(this.displaySpell){
	    	this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().draw(this);
	    }
	    if(this.displaySword){
	    	this.getPlayer().getSwords().get(this.getPlayer().getSwordNum()).getE().draw(this);
	    }
	    if(this.displaySheild){
	    	this.getPlayer().getSheilds().get(this.getPlayer().getSwordNum()).getE().draw(this);
	    }
	    
	    int x=this.player.getLoc().getFirst();
		int y=this.player.getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){
			//this.getData().getMonsters().get(x).get(y).get(i).getE().setY(40);
			//this.getData().getMonsters().get(x).get(y).get(i).getE().setX(100);
			this.getData().getMonsters().get(x).get(y).get(i).getE().draw(this);
		}
	}
	
	@Override
	public void tick() {
		player.getPlayerE().tick();
		/*System.out.println(this.player.getStat(0)
				+", "+this.player.getStat(1)
				+", "+this.player.getStat(2)
				+", "+this.player.getStat(3));*/
		
		/*System.out.println(this.getData().getMonsters().get(0).get(0).get(0).getE().getVx()+", "
				+this.getData().getMonsters().get(0).get(0).get(0).getE().getVy()+", "
				+this.getData().getMonsters().get(0).get(0).get(0).getE().getX()+", "
				+this.getData().getMonsters().get(0).get(0).get(0).getE().getY());*/
		
		/*System.out.println(player.getPlayerE().getX()+", "
				+player.getPlayerE().getY());*/
		

		//TODO move existing fire balls
		
		canChangeMode();
//		System.out.println(this.getMode());
		if(this.getMode().equals("magic")){
			castSpell();
		}
		else if(this.getMode().equals("sword"))
			swingSword();
		else if(this.getMode().equals("sheild"))
			defendShield();
		else{
			//System.out.println("Error in the tick's actions");
		}
		
		//go through all of the elements at get(x).get(y) these are the monsters on that particular floor.
		int x=this.player.getLoc().getFirst();
		int y=this.player.getLoc().getSecond();
		moveMonsters(x,y);
		
		
		openPauseMenu();
		
		roomClearer();
			
		//TODO after moving into the next room and the doors shut make the isPointGiven var= false
		
		characterMover();
		
	}

	

	@Override
	public void start() {
		InputHandler.registerKey(KeyEvent.VK_D);
		InputHandler.registerKey(KeyEvent.VK_A);
		InputHandler.registerKey(KeyEvent.VK_W);
		InputHandler.registerKey(KeyEvent.VK_S);

		InputHandler.registerKey(KeyEvent.VK_Q);//used for casting magic
		
		InputHandler.registerKey(KeyEvent.VK_RIGHT);
		InputHandler.registerKey(KeyEvent.VK_LEFT);
		InputHandler.registerKey(KeyEvent.VK_UP);
		InputHandler.registerKey(KeyEvent.VK_DOWN);
		

		InputHandler.registerKey(KeyEvent.VK_ESCAPE);
		

		InputHandler.registerKey(KeyEvent.VK_SPACE);
		
	}
	
	@Override
	public void stop() {
		InputHandler.deregisterKey(KeyEvent.VK_D);
		InputHandler.deregisterKey(KeyEvent.VK_A);
		InputHandler.deregisterKey(KeyEvent.VK_W);
		InputHandler.deregisterKey(KeyEvent.VK_S);
		
		InputHandler.deregisterKey(KeyEvent.VK_RIGHT);
		InputHandler.deregisterKey(KeyEvent.VK_LEFT);
		InputHandler.deregisterKey(KeyEvent.VK_UP);
		InputHandler.deregisterKey(KeyEvent.VK_DOWN);
		

		InputHandler.deregisterKey(KeyEvent.VK_ESCAPE);
		
		
		InputHandler.registerKey(KeyEvent.VK_SPACE);
	
	}
	
	public boolean getSwap() {
		return swap;
	}

	public void changeSwap() {
		this.swap = !this.swap;
	}

	/**
	 * @return the data
	 */
	public GameData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(GameData data) {
		this.data = data;
	}
	
	public void changeClearDoors(){
		shutAllDoors();
		N_door=new Entity(22,15,i,"upN_Door.png");
        E_door=new Entity(14,17,i,"upE_Door.png");
	}
	
	public void inNewRoom(){
		shutAllDoors();
		S_door=new Entity(22,15,i,"upS_Door.png");
		W_door=new Entity(14,17,i,"upW_Door.png");
	}
	
	public void shutAllDoors(){
		N_door=new Entity(22,15,i,"downN_door.png");
        S_door=new Entity(22,15,i,"downS_door.png");
        E_door=new Entity(14,17,i,"downE_door.png");
        W_door=new Entity(14,17,i,"downW_door.png");
	}

	/**
	 * @return the pointGiven
	 */
	public boolean isPointGiven() {
		return pointGiven;
	}

	/**
	 * @param pointGiven the pointGiven to set
	 */
	public void setPointGiven(boolean pointGiven) {
		this.pointGiven = pointGiven;
	}

	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setPlayer(Player tmp){
		this.player=tmp;
	}
	
	public void defendShield(){
		Sheild tmp=this.getPlayer().getSheilds().get(this.getPlayer().getSheildNum());
		double playerX=this.getPlayer().getPlayerE().getX();
		double playerY=this.getPlayer().getPlayerE().getY();
		if(InputHandler.isPressed(KeyEvent.VK_LEFT)){
			tmp.getE().setX(playerX-(tmp.getWidth()/2));
			tmp.getE().setY(playerY+(this.getPlayer().getHEIGHT()/2));
			this.displaySheild=true;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_UP)){
			tmp.getE().setX(playerX+tmp.getE().getWidth());
			tmp.getE().setY(playerY-(tmp.getE().getHeight()/2));
			this.displaySheild=true;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)){
			tmp.getE().setX(playerX+this.getPlayer().getWIDTH());
			tmp.getE().setY(playerY+(this.getPlayer().getHEIGHT()/2));
			this.displaySheild=true;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_DOWN)){
			tmp.getE().setX(playerX+tmp.getE().getWidth());
			tmp.getE().setY(playerY+this.getPlayer().getPlayerE().getHeight());
			this.displaySheild=true;
		}
		else{
			this.displaySheild=false;
		}
	}
	
	public void hitTestSheild(Sheild protector){//TODO START HERE
		Monster temp;
		int playerX=this.getPlayer().getLoc().getFirst();
		int playerY=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(playerX).get(playerY).size();i++){
			temp=this.getData().getMonsters().get(playerX).get(playerY).get(i);
			if(protector.getE().hitTest(temp.getE())){
				System.out.println("sh hit");
				creatureReversal(temp);
			}
		
		}
	}
	
	public void swingSword(){
		int swordnum=this.getPlayer().getSwordNum();
		double playerX=this.getPlayer().getPlayerE().getX();
		double playerY=this.getPlayer().getPlayerE().getY();
		if(InputHandler.isPressed(KeyEvent.VK_LEFT)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX-(this.getPlayer().getSwords().get(swordnum).getE().getWidth()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY+(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2));
			this.displaySword=true;
			this.hitTestSword(swordnum);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_UP)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+(this.getPlayer().getWIDTH()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY-(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2));
			this.displaySword=true;
			this.hitTestSword(swordnum);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+this.getPlayer().getWIDTH());
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY+(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2));
			this.displaySword=true;
			this.hitTestSword(swordnum);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_DOWN)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+(this.getPlayer().getWIDTH()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY+this.getPlayer().getSwords().get(swordnum).getE().getHeight());
			this.displaySword=true;
			this.hitTestSword(swordnum);
		}
		else{
			this.displaySword=false;
		}
	}
	
	public void hitTestSword(int swordIndex){
		Monster temp;
		int playerX=this.getPlayer().getLoc().getFirst();
		int playerY=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(playerX).get(playerY).size();i++){
			temp=this.getData().getMonsters().get(playerX).get(playerY).get(i);
			if(this.getPlayer().getSwords().get(swordIndex).getE().hitTest(temp.getE())){
				temp.takeDamage(this.getPlayer().getSwords().get(swordIndex).getPower()+this.getPlayer().getStats()[1]);
				creatureReversal(temp);
			}
		
		}
	}
	
	public void creatureReversal(Monster monst){
		monst.getE().setVx(monst.getE().getVx()*-2);
		monst.getE().setVy(monst.getE().getVy()*-2);
		for(int i=0;i<10;i++)//Maybe 5 times
			monst.getE().tick();
	}
	
	public void castSpell(){//TODO make sure after clearing a room that the current hp and mp = the array vals, player.fullheal() in roomclearer
		int cMP=this.getPlayer().getCurrentMp();
		int costMP=this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getCost();
		if(cMP >= costMP && !displaySpell){
			int magNum=this.getPlayer().getMagickNum();
			if(InputHandler.isPressed(KeyEvent.VK_LEFT)){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(
						this.getPlayer().getPlayerE().getX());
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY(
						this.getPlayer().getPlayerE().getY()+this.getPlayer().getHEIGHT()/2);
				this.getPlayer().getMagicks().get(magNum).getE().setVx(-3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player

				System.out.println(this.getPlayer().getMagicks().get(magNum).getFileName()+
						", x: "+this.getPlayer().getMagicks().get(magNum).getE().getX()+
						", y: "+this.getPlayer().getMagicks().get(magNum).getE().getY());
				this.displaySpell=true;
				
			}
			else if(InputHandler.isPressed(KeyEvent.VK_UP)){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(
						this.getPlayer().getPlayerE().getX()+(this.getPlayer().getWIDTH()/2));
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY(
						this.getPlayer().getPlayerE().getY());
				this.getPlayer().getMagicks().get(magNum).getE().setVy(-3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player

				System.out.println(this.getPlayer().getMagicks().get(magNum).getFileName()+
						", x: "+this.getPlayer().getMagicks().get(magNum).getE().getX()+
						", y: "+this.getPlayer().getMagicks().get(magNum).getE().getY());
				this.displaySpell=true;
				
			}
			else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(
						this.getPlayer().getPlayerE().getX()+this.getPlayer().getWIDTH());
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY(
						this.getPlayer().getPlayerE().getY()+this.getPlayer().getHEIGHT()/2);
				this.getPlayer().getMagicks().get(magNum).getE().setVx(3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player
				
				System.out.println(this.getPlayer().getMagicks().get(magNum).getFileName()+
						", x: "+this.getPlayer().getMagicks().get(magNum).getE().getX()+
						", y: "+this.getPlayer().getMagicks().get(magNum).getE().getY());
				this.displaySpell=true;
			}
			else if(InputHandler.isPressed(KeyEvent.VK_DOWN)){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(
						this.getPlayer().getPlayerE().getX()+(this.getPlayer().getWIDTH()/2));
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY(
						this.getPlayer().getPlayerE().getY()+this.getPlayer().getHEIGHT());
				this.getPlayer().getMagicks().get(magNum).getE().setVy(3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player

				System.out.println(this.getPlayer().getMagicks().get(magNum).getFileName()+
						", x: "+this.getPlayer().getMagicks().get(magNum).getE().getX()+
						", y: "+this.getPlayer().getMagicks().get(magNum).getE().getY());
				this.displaySpell=true;
			}
		}
		else if(displaySpell){
			{//if hit any of the currently displayed monsters
				for(int i=0;i<this.getData().getMonsters().get(this.getPlayer().getLoc().getFirst()).get(this.getPlayer().getLoc().getSecond()).size();i++){
					Monster temp=this.getData().getMonsters().get(this.getPlayer().getLoc().getFirst()).get(this.getPlayer().getLoc().getSecond()).get(i);
					if(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().hitTest(temp.getE())){
						temp.takeDamage(this.getPlayer().getMagicks().get(this.player.getMagickNum()).getPow()+this.getPlayer().getStats()[1]);
						this.removeSpell();
					}
				
				}
			}
			if(this.canMove(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE()))//check walls
				removeSpell();
			else
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().tick();//tick it
		}
		else{
			//System.out.println("NO MP");
		}
	}
	
	public boolean canMove(Entity E){
		if(E.getX()+(E.getWidth()/2)+E.getVx()>=171){
			return false;
		}
		else if(E.getX()+(E.getWidth()/2)-E.getVx()<=36){
			return false;
		}

		if(E.getY()+(E.getHeight()/2)-E.getVy()<=17){//17
			return false;
		}
		else if(E.getY()+(E.getHeight()/2)+E.getVy()>=121){//121
			return false;
		}
		return true;
	}
	
	public void removeSpell(){
		this.displaySpell=false;
		zeroVelocity(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE());
		//this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setX((Double) null);
		//this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY((Double) null);
	}
	
	
	public void characterMover(){
		if((InputHandler.isPressed(KeyEvent.VK_D) /*||
				InputHandler.isPressed(KeyEvent.VK_RIGHT)*/) && player.canMoveRight()){
			player.getPlayerE().setVx(1.0);
		}
		else if((InputHandler.isPressed(KeyEvent.VK_A)/*||
				InputHandler.isPressed(KeyEvent.VK_LEFT)*/) && player.canMoveLeft()){
			player.getPlayerE().setVx(-1.0);
		}
		else{
			player.getPlayerE().setVx(0);
		}
		
		if((InputHandler.isPressed(KeyEvent.VK_W)/*||
				InputHandler.isPressed(KeyEvent.VK_UP)*/) && player.canMoveUp()){
			player.getPlayerE().setVy(-0.75);
		}
		else if((InputHandler.isPressed(KeyEvent.VK_S)/*||
				InputHandler.isPressed(KeyEvent.VK_DOWN)*/) && player.canMoveDown()){
			player.getPlayerE().setVy(0.75);
		}	
		else{
			player.getPlayerE().setVy(0);
		}

		characterHitTest();
	}
	
	private void openPauseMenu() {
		if(InputHandler.isPressed(KeyEvent.VK_ESCAPE)){
			this.changeSwap();			
//these next two lines are kill lines to test the opening of the doors
		if(this.getData().getMonsters().get(0).get(0).size()!=0)
				this.getData().getMonsters().get(0).get(0).get(0).setHp(0);
		}
		
	}
	
	private void zeroVelocity(Entity E){
		E.setVx(0.0);
		E.setVy(0.0);
	}
	
	private void characterHitTest() {
		if(player.getPlayerE().hitTest(N_door)&& this.N_door.getFileName().equals("upN_Door.png")){
			//move the player up to the next room
			this.getPlayer().getPlayerE().setY(121);//as of now the player must slow move up by lightly tapping up and to stay incontact w/ the door
			this.zeroVelocity(this.getPlayer().getPlayerE());
			this.getPlayer().getLoc().setFirst(this.getPlayer().getLoc().getFirst()+1);
			this.inNewRoom();
			testBacktrack=true;
			//set the y to value so touching the Sdoor and if up then close the door. if the player moves down on the sdoor and not shut move it back down
		}
		else if(player.getPlayerE().hitTest(E_door)&& this.E_door.getFileName().equals("upE_Door.png")){
			//move the player up to the next room
			this.getPlayer().getPlayerE().setX(16);//as of now the player must slow move up by lightly tapping up and to stay incontact w/ the door
			this.zeroVelocity(this.getPlayer().getPlayerE());
			this.getPlayer().getLoc().setSecond(this.getPlayer().getLoc().getSecond()+1);
			this.inNewRoom();
			testBacktrack=true;
			//set the y to value so touching the Wdoor and if up then close the door. if the player moves down on the sdoor and not shut move it back down
		}
		else{

		}
		if(testBacktrack){
			if(player.getPlayerE().hitTest(S_door)&&this.S_door.getFileName().equals("upS_Door.png")&&InputHandler.isPressed(KeyEvent.VK_S)){
				this.getPlayer().getPlayerE().setY(17);
				this.zeroVelocity(this.getPlayer().getPlayerE());
				this.getPlayer().getLoc().setFirst(this.getPlayer().getLoc().getFirst()-1);
				this.changeClearDoors();
			}
			else if(player.getPlayerE().hitTest(W_door)){
				this.getPlayer().getPlayerE().setX(174);
				this.zeroVelocity(this.getPlayer().getPlayerE());
				this.getPlayer().getLoc().setSecond(this.getPlayer().getLoc().getSecond()-1);
				this.changeClearDoors();
			}
			else{
				//TODO need to shut the doors but when???
				//this.shutAllDoors();
				//testBacktrack=false;
			}
		}

		int x=this.getPlayer().getLoc().getFirst();
		int y=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){//goes through all of the monsters in the room
			Entity temp=this.getData().getMonsters().get(x).get(y).get(i).getE();
			if(this.getPlayer().getPlayerE().hitTest(temp)){//player is in contact with a monster
				this.getPlayer().takeDamage(this.getData().getMonsters().get(x).get(y).get(i).getAttk()/*+100*/);
			}
			/*
			 else if(sheild.hitTest(monsetr)){
			 	set the monster's velocity to 3 but in opposite direction. if hit with vx=1,vy=-1 then vx=-3,vy=3
			 	call the monster's tick
			 }
			 */
		}
		
		
	}

	private void moveMonsters(int x, int y) {
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){
			Monster tmp=this.getData().getMonsters().get(x).get(y).get(i);
			
			if(tmp.getMovement().equals("Close")){
				moveCloser(tmp);
			}
			else if(tmp.getMovement().equals("Rand")){
				moveRandom(tmp);
				
			}
			else if(tmp.getMovement().equals("Fig8Knot")){//vertical figure 8
				moveFig8Knot(tmp);
			}
			else if(tmp.getMovement().equals("OutLineCCW")){
				moveOutLineCounterClockWise(tmp);
			}
			else if(tmp.getMovement().equals("OutLineCW")){
				moveOutLineClockWise(tmp);
			}
			

			
			//check the health status to see if it needs to be removed
			if(tmp.getHp()<=0){
				this.getData().getMonsters().get(x).get(y).remove(tmp);
				this.player.setNumKills(this.player.getNumKills()+1);
			}
			else
				tmp.getE().tick();
		}
	}
	
	private void moveCloser(Monster tmp){//vx and vy were at 0.5
		//TODO make sure the movement is in the play space before and after the movement
		if(tmp.getE().getX()
				>player.getPlayerE().getX()){//go left to player
			tmp.getE().setVx(-0.35);
		}
		else if(tmp.getE().getX()
				<player.getPlayerE().getX()){//go right to player
			tmp.getE().setVx(0.35);
		}
		else{
			tmp.getE().setVx(0.0);
		}
		
		if(tmp.getE().getY()
				>player.getPlayerE().getY()){//go up to player
			tmp.getE().setVy(-0.35);
		}
		else if(tmp.getE().getY()
				<player.getPlayerE().getY()){//go down to player
			tmp.getE().setVy(0.35);
		}
		else{
			tmp.getE().setVy(0.0);
		}		
	}
	
	private void moveRandom(Monster tmp){//TODO fix the distribution
		int rand=(int) Math.ceil(Math.random()*4);
		if(rand==1){//move left
			tmp.getE().setVx(-0.25);
		}
		else if(rand==2){//move up
			tmp.getE().setVy(-0.25);
		}
		else if(rand==3){//move right
			tmp.getE().setVx(0.25);
		}
		else if(rand==4){//move down
			tmp.getE().setVx(0.25);
		}
		else{//error
			System.out.println("ERROR RAND NOT IN [1,4]");
		}
		if(!this.canMove(tmp.getE())){
			if(tmp.getE().getY()<=17){
				tmp.getE().setVy(1.0);
			}
			else if(tmp.getE().getY()>=121){
				tmp.getE().setVy(-1.0);				
			}
			if(tmp.getE().getX()<=36){
				tmp.getE().setVx(1.0);
			}
			else if(tmp.getE().getX()>=171){
				tmp.getE().setVx(-1.0);
			}
		}
		
	}
	
	double temp=0;
	private void moveFig8Knot(Monster tmp){//%3 or 3billion.0
		temp+=((System.nanoTime()-this.startTime)/(9000000000.0));
		
		double trig=(2+Math.cos(2*temp));
		
		double x= trig*Math.cos(3*temp);
		double y= trig*Math.sin(3*temp);
		
		
		//TODO reset the offsets and make sure in the room
		
		tmp.getE().setX(x*25+70);
		tmp.getE().setY(y*25+40);
		
	}

	private void moveOutLineClockWise(Monster tmp){	
		if(tmp.getE().getX()+((tmp.getE().getWidth())/2)<171 &&tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==121){//moves accross the bottom to right
			tmp.getE().setVx(1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getX()+((tmp.getE().getWidth())/2)>36 && tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==17){//moves arccoss the top to the left
			tmp.getE().setVx(-1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getY()+ ((tmp.getE().getHeight())/2)> 17 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==171){//moves up right wall
			tmp.getE().setVy(-1.0);
			tmp.getE().setVx(0.0);
		}
		else if(tmp.getE().getY()+ ((tmp.getE().getHeight())/2)< 121 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==36){//moves down the left wall
			tmp.getE().setVy(1.0);
			tmp.getE().setVx(0.0);
		}
		else{
			tmp.getE().setVx(0.0);
			tmp.getE().setVy(0.0);
			//System.out.println(tmp.getE().getX()+((tmp.getE().getWidth())/2)+", "+tmp.getE().getY()+ ((tmp.getE().getHeight())/2));
		}	
	}

	private void moveOutLineCounterClockWise(Monster tmp){	
		if(tmp.getE().getX()+((tmp.getE().getWidth())/2)<171 &&tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==17){//moves accross the top to right
			tmp.getE().setVx(1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getX()+((tmp.getE().getWidth())/2)>36 && tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==121){//moves arccoss the bottom to the left
			tmp.getE().setVx(-1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getY()+ ((tmp.getE().getHeight())/2)> 17 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==36){//moves up left wall
			tmp.getE().setVy(-1.0);
			tmp.getE().setVx(0.0);
		}
		else if(tmp.getE().getY()+ ((tmp.getE().getHeight())/2)< 121 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==171){//moves down the right wall
			tmp.getE().setVy(1.0);
			tmp.getE().setVx(0.0);
		}
		else{
			tmp.getE().setVx(0.0);
			tmp.getE().setVy(0.0);
			//System.out.println(tmp.getE().getX()+((tmp.getE().getWidth())/2)+", "+tmp.getE().getY()+ ((tmp.getE().getHeight())/2));
		}	
	}
	
	private void roomClearer() {
		if(this.getData().getMonsters().get(this.player.getLoc().getFirst()).get(this.player.getLoc().getSecond()).size()==0){//the room is cleared, the x->y array list is empty
			this.getData().setComplete(this.player.getLoc().getFirst(), this.player.getLoc().getSecond(), true);
			this.getPlayer().setNumRoomsCleared(this.player.getNumRoomsCleared()+1);
			this.getPlayer().fullHeal();
			if(!this.isPointGiven()){
				this.getPlayer().setNumPowerUps(this.getPlayer().getNumPowerUps()+1);
				this.setPointGiven(!this.isPointGiven());
				
				int score =this.getPlayer().determineScore();
				if(score%10==0){//every 10 levels up a point
					if (score%3==0&&this.getPlayer().getSwordNum()<this.getPlayer().getSwords().size()-1){
						// and the sowrd num is less than the max size. there are 5 swords at 0,1,2,3,4
						this.getPlayer().setSwordNum(this.getPlayer().getSwordNum()+1);
					}
					else if (score%4==0 && this.getPlayer().getSheildNum()<this.getPlayer().getSheilds().size()-1){
						//and the sheild num is less than the max size. there are 3 shileds at 0,1,2
						this.getPlayer().setSheildNum(this.getPlayer().getSheildNum()+1);
					}
					else if (score%1==0 && this.getPlayer().getMagickNum()<this.getPlayer().getMagicks().size()-1){ 
						//and the magic num is less than the max size. there are 4 magicks at 0,1,2,3
						this.getPlayer().setMagickNum(this.getPlayer().getMagickNum()+1);
					}
					else{ 
						//add to the num power ups by 3, b/c out of equipment upgrades and still want to give help to the player
						this.getPlayer().setNumPowerUps(this.getPlayer().getNumPowerUps()+3);
					}
				}
			}
		}
	}

	public void canChangeMode(){
		if(InputHandler.isPressed(KeyEvent.VK_Q)){
			switchMode();			
			while(InputHandler.isPressed(KeyEvent.VK_Q));
		}
	}
	
	public void switchMode(){
		if(this.getMode().equals("sword")){
			this.setMode("magic");
		}
		else if(this.getMode().equals("magic")){
			this.setMode("sheild");
		}
		else if(this.getMode().equals("sheild")){
			this.setMode("sword");
		}
		else{
			System.out.println("ERROR SWITCH");
		}
	}
	
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}