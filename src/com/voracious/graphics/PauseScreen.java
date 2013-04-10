package com.voracious.graphics;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.voracious.data.Player;
import com.voracious.graphics.components.Screen;
import com.voracious.graphics.components.Sprite;

public class PauseScreen extends Screen {
	//near top, middle show boxes to represent how many points to be used
	//hp,attack,def,mp
	//heart
	//pow block
	//armor
	//green poition
	//them are images that can be clciked
	//then a block disapers and the players
	//correlated stat ++
	Sprite s;//background img
	Player plays;
	ArrayList<Sprite> powerUps=new ArrayList<Sprite>();
	Sprite[] pows={new Sprite(45,40,"hearts.png"), new Sprite(45,40,"power.png"),
			new Sprite(45,40,"dfence.png"),new Sprite(45,40,"mpPotion.png")};
	private boolean swap=false;
	
	public PauseScreen(int width, int height,Player play) {
		super(width, height);
        s= new Sprite(200,150,"pauseMenu.png");
        s.draw(this, 0, 0);
        plays=play;		
	}
	
	
	@Override
	public void render() {
		s.draw(this, 0, 0);
		
		//draw power ups
		for(int i=0,buff=50;i<pows.length;i++){
			pows[i].draw(this, 10+buff*i, 10);
			
		}
		//banner
		new Sprite(200,50,"names.png").draw(this, 0, 50);
		//draws the power up points 
		for(int i=0,j=0,k=0;i<plays.getNumPowerUps();i++){
			if(i%2==0)
				powerUps.add(new Sprite(20,15,"orange.png"));
			else
				powerUps.add(new Sprite(20,15,"purple.png"));
			if(i%135==0){
				k=0;
				j+=5;
			}
			else{
				k++;
			}
			powerUps.get(i).draw(this, 20+k, 80+j);
		}
		//TODO draw the eqipment currently attached.
	}
	
	@Override
	public void tick(){
		swapHelper();
		
		powerUpUse();
	}
	
	private void powerUpUse() {
	//use power ups
		if(plays.getNumPowerUps()>0){
			if(InputHandler.isPressed(KeyEvent.VK_Q)){
				plays.setNumPowerUps(plays.getNumPowerUps()-1);
				powerUps.remove(powerUps.size()-1);
				plays.setStat(0, plays.getStat(0)+1);//hp @ 0
				while(InputHandler.isPressed(KeyEvent.VK_Q));
				//early attempt to make the player have to press the button multiple times to spend the point
			}
			else if(InputHandler.isPressed(KeyEvent.VK_W)){
				plays.setNumPowerUps(plays.getNumPowerUps()-1);
				powerUps.remove(powerUps.size()-1);
				plays.setStat(1, plays.getStat(1)+1);//attack @ 1
				while(InputHandler.isPressed(KeyEvent.VK_W));
				//early attempt to make the player have to press the button multiple times to spend the point
			}
			else if(InputHandler.isPressed(KeyEvent.VK_E)){
				plays.setNumPowerUps(plays.getNumPowerUps()-1);
				powerUps.remove(powerUps.size()-1);
				plays.setStat(2, plays.getStat(2)+1);//d-fence @ 2
				while(InputHandler.isPressed(KeyEvent.VK_E));
				//early attempt to make the player have to press the button multiple times to spend the point
			}
			else if(InputHandler.isPressed(KeyEvent.VK_R)){
				plays.setNumPowerUps(plays.getNumPowerUps()-1);
				powerUps.remove(powerUps.size()-1);
				plays.setStat(3, plays.getStat(3)+1);//mp @ 3
				while(InputHandler.isPressed(KeyEvent.VK_R));
				//early attempt to make the player have to press the button multiple times to spend the point
			}
		}		
	}


	@Override
	public void start() {
		InputHandler.registerKey(KeyEvent.VK_ESCAPE);
		
		InputHandler.registerKey(KeyEvent.VK_Q);
		InputHandler.registerKey(KeyEvent.VK_W);
		InputHandler.registerKey(KeyEvent.VK_E);
		InputHandler.registerKey(KeyEvent.VK_R);		
	}
	
	@Override
	public void stop() {

		InputHandler.deregisterKey(KeyEvent.VK_ESCAPE);
		
		InputHandler.deregisterKey(KeyEvent.VK_Q);
		InputHandler.deregisterKey(KeyEvent.VK_W);
		InputHandler.deregisterKey(KeyEvent.VK_E);
		InputHandler.deregisterKey(KeyEvent.VK_R);
	}

	public void swapHelper(){
	//escape for back to prev menu
		if(InputHandler.isPressed(KeyEvent.VK_ESCAPE)){
			this.changeSwap();
		}
	}

	public boolean getSwap() {
		return swap;
	}


	public void changeSwap() {
		this.swap = !this.swap;
	}
}
