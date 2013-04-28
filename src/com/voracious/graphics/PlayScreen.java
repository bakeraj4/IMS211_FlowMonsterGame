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
 *	This class is used to display the action in the games and some controlling features.
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
	
	/**
	 * The constructor of the screen.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 */
	public PlayScreen(int width, int height) {
		super(width, height);

        s= new Sprite(200,150,"officalFloor.png");
        s.draw(this, 0, 0);
        
        this.setData(new GameData());

        shutAllDoors();
		player =new Player("Aaron");
		this.startTime=System.nanoTime();
	}
	
	/**
	 * This method is what draws the game.
	 */
	@Override
	public void render() {
		//System.out.println(this.getPlayer().getLoc().getFirst()+" "+this.getPlayer().getLoc().getSecond());
		
		//Draws the screen and the player.
		s.draw(this, 0, 0);
		player.getPlayerE().draw(this);
		
		//Draws the doors.
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

	    //Draws the player's actions is appropriate 
	    if(this.displaySpell){
	    	this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().draw(this);
	    }
	    if(this.displaySword){
	    	if(flip){
	    		this.getPlayer().getSwords().get(this.getPlayer().getSwordNum()).getE().draw(this, true, true, false);
	    	}
	    	else{
	    		this.getPlayer().getSwords().get(this.getPlayer().getSwordNum()).getE().draw(this);
	    	}
	    }
	    if(this.displaySheild){
	    	this.getPlayer().getSheilds().get(this.getPlayer().getSwordNum()).getE().draw(this);
	    }
	    
	    //Draws the creatures.
	    int x=this.player.getLoc().getFirst();
		int y=this.player.getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){
			this.getData().getMonsters().get(x).get(y).get(i).getE().draw(this);
		}
	}
	
	/**
	 * This is the tick method that moves the entities used in the game.
	 * The order:	player,
	 * 				mode changes,
	 * 				players actions,
	 * 				monster movements,
	 * 				pause menu stuff,
	 * 				cleared room stuff,
	 * 				changes in the players movement
	 */
	@Override
	public void tick() {
		player.getPlayerE().tick();
				
		canChangeMode();
		if(this.getMode().equals("magic")){
			castSpell();
		}
		else if(this.getMode().equals("sword"))
			swingSword();
		else if(this.getMode().equals("sheild"))
			defendShield();
		else{
		}
		
		//go through all of the elements at get(x).get(y) these are the monsters on that particular floor.
		int x=this.player.getLoc().getFirst();
		int y=this.player.getLoc().getSecond();
		moveMonsters(x,y);
		
		openPauseMenu();
		
		roomClearer();
		
		characterMover();	
	}
	
	/**
	 * This menu prepares the keys used in the Scren.
	 */
	@Override
	public void start() {
		InputHandler.registerKey(KeyEvent.VK_D);//move right
		InputHandler.registerKey(KeyEvent.VK_A);//move left
		InputHandler.registerKey(KeyEvent.VK_W);//move up
		InputHandler.registerKey(KeyEvent.VK_S);//move down
		InputHandler.registerKey(KeyEvent.VK_Q);//used for casting magic
		InputHandler.registerKey(KeyEvent.VK_RIGHT);//swing, cast, defend right
		InputHandler.registerKey(KeyEvent.VK_LEFT);//swing, cast, defend left
		InputHandler.registerKey(KeyEvent.VK_UP);//swing, cast, defend up
		InputHandler.registerKey(KeyEvent.VK_DOWN);//swing, cast, defend down
		InputHandler.registerKey(KeyEvent.VK_ESCAPE);//change to the pause menu
	}
	
	/**
	 * This method removes the listeners for the keys used in the screen. 
	 */
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
	}
	
	/**
	 * Gets the value determined in to switch the screens.
	 * @return The value determined in to switch the screens.
	 */
	public boolean getSwap() {
		return swap;
	}

	/**
	 * This method changes the value that is used to determine to switch to the pause menu.
	 */
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
	
	/**
	 * This method is used to change the doors such that it looks like that the next room is accessible.
	 */
	public void changeClearDoors(){
		shutAllDoors();
		if(this.getPlayer().getLoc().getFirst()<this.getData().getMonsters().size()-1)
			N_door=new Entity(22,15,i,"upN_Door.png");
		if(this.getPlayer().getLoc().getSecond()<this.getData().getMonsters().get(this.getPlayer().getLoc().getFirst()).size()-1)
        	E_door=new Entity(14,17,i,"upE_Door.png");
	}
	
	/**
	 * This method will change the doors such that it looks like they changed rooms and are in the next one.
	 */
	public void inNewRoom(){
		shutAllDoors();
		S_door=new Entity(22,15,i,"upS_Door.png");
		W_door=new Entity(14,17,i,"upW_Door.png");
	}
	
	/**
	 * This method shuts all of the doors in a room.
	 */
	public void shutAllDoors(){
		N_door=new Entity(22,15,i,"downN_door.png");
        S_door=new Entity(22,15,i,"downS_door.png");
        E_door=new Entity(14,17,i,"downE_door.png");
        W_door=new Entity(14,17,i,"downW_door.png");
        try{
        	if(this.getData().getComplete()[this.getPlayer().getLoc().getFirst()][this.getPlayer().getLoc().getSecond()])
        		this.setPointGiven(false);
        		//System.out.println(this.getPlayer().determineScore());
        }
        catch(NullPointerException e){
        	
        }
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

	/**
	 * This method gets the current player.
	 * @return The current player.
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * This method changes the current player.
	 * @param tmp The new player.
	 */
	public void setPlayer(Player tmp){
		this.player=tmp;
	}
	
	/**
	 * This method determines if a shield needs to be placed and performs hit tests.
	 */
	public void defendShield(){
		Sheild tmp=this.getPlayer().getSheilds().get(this.getPlayer().getSheildNum());
		double playerX=this.getPlayer().getPlayerE().getX();
		double playerY=this.getPlayer().getPlayerE().getY();
		if(InputHandler.isPressed(KeyEvent.VK_LEFT)){
			tmp.getE().setX(playerX-(tmp.getWidth()/2));
			tmp.getE().setY(playerY+(this.getPlayer().getHEIGHT()/2));
			this.displaySheild=true;
			this.hitTestSheild(tmp);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_UP)){
			tmp.getE().setX(playerX+tmp.getE().getWidth());
			tmp.getE().setY(playerY-(tmp.getE().getHeight()/2));
			this.displaySheild=true;
			this.hitTestSheild(tmp);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)){
			tmp.getE().setX(playerX+this.getPlayer().getWIDTH());
			tmp.getE().setY(playerY+(this.getPlayer().getHEIGHT()/2));
			this.displaySheild=true;
			this.hitTestSheild(tmp);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_DOWN)){
			tmp.getE().setX(playerX+tmp.getE().getWidth());
			tmp.getE().setY(playerY+this.getPlayer().getPlayerE().getHeight());
			this.displaySheild=true;
			this.hitTestSheild(tmp);
		}
		else{
			this.displaySheild=false;
		}
		this.displaySpell=false;
		this.displaySword=false;
	}
	
	/**
	 * This method determines if any of the monsters hit the shield.
	 * @param protector
	 */
	public void hitTestSheild(Sheild protector){
		Monster temp;
		int playerX=this.getPlayer().getLoc().getFirst();
		int playerY=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(playerX).get(playerY).size();i++){
			temp=this.getData().getMonsters().get(playerX).get(playerY).get(i);
			if(protector.getE().hitTest(temp.getE())){
				creatureReversal(temp);
			}
		}
	}
	
	/**
	 * This method determines if a sword is needed on the screen and performs hit tests.
	 */
	private boolean flip=false;
	public void swingSword(){
		int swordnum=this.getPlayer().getSwordNum();
		double playerX=this.getPlayer().getPlayerE().getX();
		double playerY=this.getPlayer().getPlayerE().getY();
		if(InputHandler.isPressed(KeyEvent.VK_LEFT)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX-(this.getPlayer().getSwords().get(swordnum).getE().getWidth()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY-(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2)+(this.getPlayer().getPlayerE().getHeight()/2));
			this.displaySword=true;
			this.hitTestSword(swordnum);
			flip=false;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_UP)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+(this.getPlayer().getWIDTH()/2)-(this.getPlayer().getSwords().get(swordnum).getE().getWidth()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY-(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2)-(this.getPlayer().getPlayerE().getHeight()/4));
			this.displaySword=true;
			this.hitTestSword(swordnum);
			flip=false;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+this.getPlayer().getWIDTH());
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY-(this.getPlayer().getSwords().get(swordnum).getE().getHeight()/2)+(this.getPlayer().getPlayerE().getHeight()/2));
			this.displaySword=true;
			this.hitTestSword(swordnum);
			flip=false;
		}
		else if(InputHandler.isPressed(KeyEvent.VK_DOWN)){
			this.getPlayer().getSwords().get(swordnum).getE().setX(playerX+(this.getPlayer().getWIDTH()/2)-(this.getPlayer().getSwords().get(swordnum).getE().getWidth()/2));
			this.getPlayer().getSwords().get(swordnum).getE().setY(playerY+this.getPlayer().getPlayerE().getHeight());
			this.displaySword=true;
			this.hitTestSword(swordnum);
			flip=true;
		}
		else{
			this.displaySword=false;
		}
		this.displaySpell=false;
		this.displaySheild=false;
	}
	/**
	 * This method does the hit test for the sword swinging.
	 * @param swordIndex The sword number in use.
	 */
	public void hitTestSword(int swordIndex){
		Monster temp;
		int playerX=this.getPlayer().getLoc().getFirst();
		int playerY=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(playerX).get(playerY).size();i++){
			//goes through all of the monsters in the x->y list
			temp=this.getData().getMonsters().get(playerX).get(playerY).get(i);
			if(this.getPlayer().getSwords().get(swordIndex).getE().hitTest(temp.getE())){
				//the monster was hit and will take damage and reacoil.
				temp.takeDamage(this.getPlayer().getSwords().get(swordIndex).getPower()+this.getPlayer().getStats()[1]);
				if(temp.getMovement().equals("OutLineCCW")){//switch it
					temp.setMovement("OutLineCW");
				}
				else if(temp.getMovement().equals("OutLineCW")){
					temp.setMovement("OutLineCCW");
				}
				else
					creatureReversal(temp);
			}
		}
	}
	
	/**
	 * This method will make a monster move in the opposite direction.
	 * The method will call the tick method of the monster to make it move.
	 * @param monst The monster being moved backwards.
	 */
	public void creatureReversal(Monster monst){
		monst.getE().setVx(monst.getE().getVx()*-2);
		monst.getE().setVy(monst.getE().getVy()*-2);
		for(int i=0;i<10;i++)//Maybe 5 times
			monst.getE().tick();
	}
	
	/**
	 * This method will make the player move in the opposite direction.
	 * The method will call the tick method of the player to make it move.
	 * @param play The player being moved backwards.
	 * @param monst The monster that hit the player
	 */
	public void playerReversal(Player play, Monster monst){
		for(int i=0;i<3&&play.isInRoom();i++){//Maybe 5 times
			play.getPlayerE().setVx(monst.getE().getVx());
			play.getPlayerE().setVy(monst.getE().getVy());
			play.getPlayerE().tick();
		}
	}
	
	/**
	 * This method will cast and handle the movement of spells.
	 */
	public void castSpell(){
		int cMP=this.getPlayer().getCurrentMp();
		int costMP=this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getCost();
		if(cMP >= costMP && !displaySpell){
			//Cast a spell if there is enough mp and a spell hasn't been cast yet.
			int magNum=this.getPlayer().getMagickNum();
			if(InputHandler.isPressed(KeyEvent.VK_LEFT)&&!displaySpell){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(this.getPlayer().getPlayerE().getX()-this.getPlayer().getMagicks().get(magNum).getE().getWidth());
				this.getPlayer().getMagicks().get(magNum).getE().setY(this.getPlayer().getPlayerE().getY()+(this.getPlayer().getPlayerE().getHeight()/2)-(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getHeight()/2));
				this.getPlayer().getMagicks().get(magNum).getE().setVx(-3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player
				this.displaySpell=true;
				
			}
			else if(InputHandler.isPressed(KeyEvent.VK_UP)&&!displaySpell){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(this.getPlayer().getPlayerE().getX()+(this.getPlayer().getWIDTH()/2)-(this.getPlayer().getMagicks().get(magNum).getE().getWidth()/2));
				this.getPlayer().getMagicks().get(magNum).getE().setY(this.getPlayer().getPlayerE().getY()-this.getPlayer().getMagicks().get(magNum).getE().getHeight());
				this.getPlayer().getMagicks().get(magNum).getE().setVy(-3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player

				this.displaySpell=true;
				
			}
			else if(InputHandler.isPressed(KeyEvent.VK_RIGHT)&&!displaySpell){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(this.getPlayer().getPlayerE().getX()+this.getPlayer().getWIDTH());
				this.getPlayer().getMagicks().get(magNum).getE().setY(this.getPlayer().getPlayerE().getY()+(this.getPlayer().getPlayerE().getHeight()/2)-(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getHeight()/2));
				this.getPlayer().getMagicks().get(magNum).getE().setVx(3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player
				
				this.displaySpell=true;
			}
			else if(InputHandler.isPressed(KeyEvent.VK_DOWN)&&!displaySpell){
				this.getPlayer().setCurrentMp(cMP-costMP);
				this.getPlayer().getMagicks().get(magNum).getE().setX(this.getPlayer().getPlayerE().getX()+(this.getPlayer().getWIDTH()/2)-(this.getPlayer().getMagicks().get(magNum).getE().getWidth()/2));
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().setY(this.getPlayer().getPlayerE().getY()+this.getPlayer().getHEIGHT());
				this.getPlayer().getMagicks().get(magNum).getE().setVy(3.0);
				//3.0 arbitraty but i want it to be faster than a monster or player

				this.displaySpell=true;
			}
		}
		else if(displaySpell){//If a spell was cast
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
			else{
				this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE().tick();//tick it
				isOffScreen(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()));
			}
		}
		else{
		}
		
		this.displaySheild=false;
		this.displaySword=false;
	}
	
	public void isOffScreen(Magic mag){
		if(!this.canMove(mag.getE())){
			displaySpell=false;
		}
	}
	
	/**
	 * This method determines if an entity can move. 
	 * It doesn't take in consideration that an entity could move down with the wall when x=wall's x vx=1 and vy=1.
	 * @param E The entity that will have its movement tested.
	 * @return If the entity can move or not.
	 */
	public boolean canMove(Entity E){
		//This section determines if the entity can move left or right.
		if(E.getX()+(E.getWidth()/2)+E.getVx()>172){
			return false;
		}
		else if(E.getX()+(E.getWidth()/2)-E.getVx()<35){
			return false;
		}
		
		//This section determines if an entity can move up or down.
		if(E.getY()+(E.getHeight()/2)-E.getVy()<17){//17
			return false;
		}
		else if(E.getY()+(E.getHeight()/2)+E.getVy()>122){//121
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to remove the spell from the screen.
	 */
	public void removeSpell(){
		this.displaySpell=false;
		zeroVelocity(this.getPlayer().getMagicks().get(this.getPlayer().getMagickNum()).getE());
	}
	
	/**
	 * This method determines the player's sprite's movement.
	 * The w,a,s,d keys are used to move up, left, down, and right.
	 * Then the method does hit tests for the player.
	 */
	public void characterMover(){
		//This section determines the x velocity of the player.
		if(InputHandler.isPressed(KeyEvent.VK_D) && player.canMoveRight()){
			player.getPlayerE().setVx(1.0);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_A) && player.canMoveLeft()){
			player.getPlayerE().setVx(-1.0);
		}
		else{
			player.getPlayerE().setVx(0);
		}
		
		//This section determines the y velocity of the player.
		if(InputHandler.isPressed(KeyEvent.VK_W)&& player.canMoveUp()){
			player.getPlayerE().setVy(-0.75);
		}
		else if(InputHandler.isPressed(KeyEvent.VK_S) && player.canMoveDown()){
			player.getPlayerE().setVy(0.75);
		}	
		else{
			player.getPlayerE().setVy(0);
		}
		characterHitTest();
	}
	/**
	 * This method controls when the pause menu is open.
	 * When the player taps the escape button the method changes a variable that is responsible for swapping to the pause screen.
	 */
	private void openPauseMenu() {
		if(InputHandler.isPressed(KeyEvent.VK_ESCAPE)){
			this.changeSwap();			
//these next two lines are kill lines to test the opening of the doors
/*		if(this.getData().getMonsters().get(0).get(0).size()!=0)
				this.getData().getMonsters().get(0).get(0).get(0).setHp(0);*/
		}
		
	}
	
	/**
	 * This method will stop the motion of an entity.
	 * The x and y velocities are set to 0.
	 * @param E The entity having its velocities being set to 0.
	 */
	private void zeroVelocity(Entity E){
		E.setVx(0.0);
		E.setVy(0.0);
	}
	
	/**
	 * This method is the hit test method for the player.
	 * It checks for collisions with monsters and the rooms doors.
	 */
	private void characterHitTest() {
		if(player.getPlayerE().hitTest(N_door)&& this.N_door.getFileName().equals("upN_Door.png")){
			//move the player up to the next room
			this.getPlayer().getPlayerE().setY(121);
			this.zeroVelocity(this.getPlayer().getPlayerE());
			this.getPlayer().getLoc().setFirst(this.getPlayer().getLoc().getFirst()+1);
			this.inNewRoom();
			testBacktrack=true;
		}
		else if(player.getPlayerE().hitTest(E_door)&& this.E_door.getFileName().equals("upE_Door.png")){
			//move the player up to the next room
			this.getPlayer().getPlayerE().setX(13);
			this.zeroVelocity(this.getPlayer().getPlayerE());
			this.getPlayer().getLoc().setSecond(this.getPlayer().getLoc().getSecond()+1);
			this.inNewRoom();
			testBacktrack=true;
		}
		else{

		}
		
		//This section does hit tests with the s and w doors to see if the player will return to the previous room.
		if(testBacktrack){
			if(player.getPlayerE().hitTest(S_door)&&this.S_door.getFileName().equals("upS_Door.png")&&InputHandler.isPressed(KeyEvent.VK_S)){
				this.getPlayer().getPlayerE().setY(17);
				this.zeroVelocity(this.getPlayer().getPlayerE());
				this.getPlayer().getLoc().setFirst(this.getPlayer().getLoc().getFirst()-1);
				this.changeClearDoors();
			}
			else if(player.getPlayerE().hitTest(W_door)&&this.W_door.getFileName().equals("upW_Door.png")&&InputHandler.isPressed(KeyEvent.VK_A)){
				this.getPlayer().getPlayerE().setX(174);
				this.zeroVelocity(this.getPlayer().getPlayerE());
				this.getPlayer().getLoc().setSecond(this.getPlayer().getLoc().getSecond()-1);
				this.changeClearDoors();
			}
			else if (player.getPlayerE().hitTest(S_door)&&this.S_door.getFileName().equals("upS_Door.png")&&(InputHandler.isPressed(KeyEvent.VK_W)||InputHandler.isPressed(KeyEvent.VK_A)||InputHandler.isPressed(KeyEvent.VK_D))||
					player.getPlayerE().hitTest(W_door)&&this.W_door.getFileName().equals("upW_Door.png")&&(InputHandler.isPressed(KeyEvent.VK_S)||InputHandler.isPressed(KeyEvent.VK_W)||InputHandler.isPressed(KeyEvent.VK_D))){
				this.shutAllDoors();
				testBacktrack=false;
				
			}
			else if(player.getPlayerE().hitTest(W_door)&&player.getPlayerE().hitTest(S_door)){
				this.shutAllDoors();
				testBacktrack=false;
			}
		}
		
		//This code block performs hit test on all of the monsters in the room with the player.
		int x=this.getPlayer().getLoc().getFirst();
		int y=this.getPlayer().getLoc().getSecond();
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){//goes through all of the monsters in the room
			Entity temp=this.getData().getMonsters().get(x).get(y).get(i).getE();
			if(this.getPlayer().getPlayerE().hitTest(temp)){//player is in contact with a monster
				this.playerReversal(this.getPlayer(),this.getData().getMonsters().get(x).get(y).get(i));
				this.getPlayer().takeDamage(this.getData().getMonsters().get(x).get(y).get(i).getAttk()/*+100*/);
			}
		}
	}

	/**
	 * This method serves as a control structure to determine how a specific monster will move.
	 * @param x The x coordinate of the room.
	 * @param y The y coordinate of the room.
	 */
	private void moveMonsters(int x, int y) {
		Monster tmp;
		for(int i=0;i<this.getData().getMonsters().get(x).get(y).size();i++){
			tmp=this.getData().getMonsters().get(x).get(y).get(i);
			boolean wasHit=false;
			for(int j=i;j<this.getData().getMonsters().get(x).get(y).size()-1;j++){
				Monster tmp2=this.getData().getMonsters().get(x).get(y).get(j+1);
				if(tmp.getE().hitTest(tmp2.getE()))
					wasHit=true;
			}
			if(wasHit){
				if(tmp.getMovement().equals("OutLineCCW")){//switch it
					tmp.setMovement("OutLineCW");
				}
				else if(tmp.getMovement().equals("OutLineCW")){
					tmp.setMovement("OutLineCCW");
				}
			}
			
			else{
				if(tmp.getMovement().equals("Close")){
					moveCloser(tmp);
				}
				else if(tmp.getMovement().equals("Rand")){
					moveRandom(tmp);

				}
				else if(tmp.getMovement().equals("Fig8Knot")){
					moveFig8Knot(tmp);
				}
				else if(tmp.getMovement().equals("OutLineCCW")){
					if(!this.canMove(tmp.getE())){
						tmp.setMovement("OutLineCW");
					}
					moveOutLineCounterClockWise(tmp);
				}
				else if(tmp.getMovement().equals("OutLineCW")){
					if(!this.canMove(tmp.getE())){
						System.out.println("[iouou");
					}
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
	}
	/**
	 * The monster will move closer to the player and will appear to chase them.
	 * @param tmp The monster that will chase the player.
	 */
	private void moveCloser(Monster tmp){
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
	
	/**
	 * The monster with this move type will move in a random fashion.
	 * @param tmp The monster that will be moving randomly.
	 */
	private void moveRandom(Monster tmp){
		int rand=(int) Math.ceil(Math.random()*4);
		int randMov=(int) Math.ceil(Math.random()*4);
		if(rand==1){//move left
			tmp.getE().setVx(-0.25*randMov);
		}
		else if(rand==2){//move up
			tmp.getE().setVy(-0.25*randMov);
		}
		else if(rand==3){//move right
			tmp.getE().setVx(0.25*randMov);
		}
		else if(rand==4){//move down
			tmp.getE().setVy(0.25*randMov);
		}
		else{//error
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
	
	
	/**
	 *  The fig8Knot is a mathematical knot and is a repeating shape.
	 *  This provides a path for the monsters to follow.
	 *  Formula found at http://en.wikipedia.org/wiki/Figure-eight_knot_%28mathematics%29.
	 * @param tmp The monster that will be moving.
	 */
	double temp=0;
	private void moveFig8Knot(Monster tmp){//%3 or 3billion.0
		temp+=(((System.nanoTime()-this.startTime)/(9000000000.0))/900.0);
		double trig=(2+Math.cos(2*temp));
		double x= trig*Math.cos(3*temp);
		double y= trig*Math.sin(3*temp);
		tmp.getE().setX(x*25+70);
		tmp.getE().setY(y*25+40);
		
	}

	/**
	 * The monster will move around on the outside of the room counter clockwise.
	 * @param tmp The monster moving.
	 */
	private void moveOutLineClockWise(Monster tmp){	
		if(tmp.getE().getX()+tmp.getE().getVx()+((tmp.getE().getWidth())/2)<171 &&tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==121){//moves accross the bottom to right
			tmp.getE().setVx(1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getX()+((tmp.getE().getWidth())/2)>36 && tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==17){//moves arccoss the top to the left
			tmp.getE().setVx(-1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getY()-tmp.getE().getVy()+ ((tmp.getE().getHeight())/2)> 17 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==171){//moves up right wall
			tmp.getE().setVy(-1.0);
			tmp.getE().setVx(0.0);
		}
		else if(tmp.getE().getY()+tmp.getE().getVy()+ ((tmp.getE().getHeight())/2)< 121 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==36){//moves down the left wall
			tmp.getE().setVy(1.0);
			tmp.getE().setVx(0.0);
		}
		else{
			tmp.getE().setVx(0.0);
			tmp.getE().setVy(0.0);
		}	
	}
	/**
	 * The monster will move around on the outside of the room counter counter-clockwise.
	 * @param tmp The monster moving.
	 */
	private void moveOutLineCounterClockWise(Monster tmp){
		if(tmp.getE().getX()+tmp.getE().getVx()+((tmp.getE().getWidth())/2)<171 &&tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==17){//moves accross the top to right
			tmp.getE().setVx(1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getX()+tmp.getE().getVx()+((tmp.getE().getWidth())/2)>36 && tmp.getE().getY()+ ((tmp.getE().getHeight())/2)==121){//moves arccoss the bottom to the left
			tmp.getE().setVx(-1.0);
			tmp.getE().setVy(0.0);
		}
		else if(tmp.getE().getY()+tmp.getE().getVy()+ ((tmp.getE().getHeight())/2)> 17 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==36){//moves up left wall
			tmp.getE().setVy(-1.0);
			tmp.getE().setVx(0.0);
		}
		else if(tmp.getE().getY()-tmp.getE().getVy()+ ((tmp.getE().getHeight())/2)< 121 && tmp.getE().getX()+((tmp.getE().getWidth())/2)==171){//moves down the right wall
			tmp.getE().setVy(1.0);
			tmp.getE().setVx(0.0);
		}
		else{
			tmp.getE().setVx(0.0);
			tmp.getE().setVy(0.0);
		}
	}
	/**
	 * This method will be used to determine if the current room is cleared.
	 * If the room is cleared it will reward the player.
	 */
	private int num10s=0;
	private void roomClearer() {
		if(this.getData().getMonsters().get(this.player.getLoc().getFirst()).get(this.player.getLoc().getSecond()).size()==0){
			//the room is cleared, the x->y array list is empty
			this.getData().setComplete(this.player.getLoc().getFirst(), this.player.getLoc().getSecond(), true);
			this.getPlayer().setNumRoomsCleared(this.player.getNumRoomsCleared()+1);
			this.getPlayer().fullHeal();
			if(!this.isPointGiven()){
				//determines if the player has received the points for upgrades yet.
				this.getPlayer().setNumPowerUps(this.getPlayer().getNumPowerUps()+1);
				this.setPointGiven(true);
				
				int val=this.getData().getMonsters().size();
				
				int cscore =this.getPlayer().determineScore();
				while(cscore-(val*num10s)>val){
					num10s++;
					if(val*num10s%val==0&&num10s!=0){//every 10 levels up a point
						if ((val*num10s)%3==0&&this.getPlayer().getSwordNum()<this.getPlayer().getSwords().size()-1){
							// and the sowrd num is less than the max size. there are 5 swords at 0,1,2,3,4
							this.getPlayer().setSwordNum(this.getPlayer().getSwordNum()+1);
						}
						else if ((val*num10s)%4==0 &&val%2==0 && this.getPlayer().getSheildNum()<this.getPlayer().getSheilds().size()-1){
							//and the sheild num is less than the max size. there are 3 shileds at 0,1,2
							this.getPlayer().setSheildNum(this.getPlayer().getSheildNum()+1);
						}
						else if ((val*num10s)%2==0 &&val%2!=0 && this.getPlayer().getSheildNum()<this.getPlayer().getSheilds().size()-1){
							//and the sheild num is less than the max size. there are 3 shileds at 0,1,2
							this.getPlayer().setSheildNum(this.getPlayer().getSheildNum()+1);
						}
						else if ((val*num10s%1)==0 && this.getPlayer().getMagickNum()<this.getPlayer().getMagicks().size()-1){ 
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
	}

	/**
	 * This method handles the switching of the modes of the player.
	 * The system will change the mode iff the q button is pressed.
	 */
	public void canChangeMode(){
		if(InputHandler.isPressed(KeyEvent.VK_Q)){
			switchMode();			
			while(InputHandler.isPressed(KeyEvent.VK_Q));
		}
	}
	
	/**
	 * This method changes the mode that the player is in.
	 * It switches between sword, magic, and shield in that order.
	 */
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
			//System.out.println("ERROR SWITCH");
		}
	}
	
	/**
	 * Gets the mode of the player.
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Changes the mode that the player is in.
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}