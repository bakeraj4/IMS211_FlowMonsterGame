package com.voracious.graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.voracious.graphics.components.Screen;

/**
 * Allows Screens to register and unregister themselves to receive keyboard events
 * 
 * @author Andrew Kallmeyer
 */
public class InputHandler extends KeyAdapter {
    
    private static ArrayList<Screen> screens = new ArrayList<Screen>();
    private static Hashtable<Integer, Boolean> keys = new Hashtable<Integer, Boolean>(); 
    
    public static boolean isPressed(int keyCode){
    	Boolean isPressed = keys.get(keyCode);
    	if(isPressed == null) throw new IllegalArgumentException("Key code (" + keyCode + ") was not registered.");
    	
    	return isPressed.booleanValue();
    }
    
    public static void registerKey(int keyCode){
    	keys.put(keyCode, false);
    }
    
    public static void deregisterKey(int keyCode){
    	keys.remove(keyCode);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        
        Boolean isPressed = keys.get(e.getKeyCode());
        if(isPressed != null)
        	keys.put(e.getKeyCode(), true);
        
        Iterator<Screen> it = screens.iterator();
        while(it.hasNext()){
            it.next().keyPressed(e);
            
            
            
            //when <- or a pressed try to move move the character left
            //when -> or d pressed try to move move the character right
            //when ^ or w pressed try to move move the character up
            //when \/ or s pressed try to move move the character down
            
            //when <<space>> pressed menu
            //when 1 or j pressed action 1
            //when 2 or k pressed action 2
            
            //in pause the num keys are used to up the corellating stat
            //in pause w, s, m to cylce through the wepon, sheild, and magic equiped
            //need to choose them some how
            
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        
        Boolean isPressed = keys.get(e.getKeyCode());
        if(isPressed != null)
        	keys.put(e.getKeyCode(), false);
        
        Iterator<Screen> it = screens.iterator();
        while(it.hasNext()){
            it.next().keyReleased(e);
        }
    }
    
    public static void register(Screen screen){
        screens.add(screen);
    }
    
    public static void unregister(Screen screen){
        screens.remove(screen);
    }
}
