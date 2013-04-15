package com.voracious.data;

import com.voracious.graphics.components.Entity;

public class Sheild {
	private String fileName;
	private int def, width, height;
	private Entity E;
	
	public Sheild(String filename,int deff,int widthImg,int heightImg){
		this.setFileName(filename);
		this.setDef(deff);
		this.setWidth(widthImg);
		this.setHeight(heightImg);
		int i[]={1};
		this.setE(new Entity(widthImg,heightImg,i,filename));
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
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	
}
