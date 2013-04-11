package com.voracious.data;

import com.voracious.graphics.components.Entity;

public class Sword {

	private String fileName;
	private int power,imgWidth,imgHeight;
	private Entity E;
	
	/**
	 * 
	 * @param name the file name
	 * @param pow the attack power of the sword
	 * @param width the width of the image pixels
	 * @param height the height of the image pixels
	 */
	public Sword(String name, int pow,int width, int height) {
		this.setFileName(name);
		this.setPower(pow);
		this.setImgWidth(width);
		this.setImgHeight(height);
		int[]i={1};
		this.setE(new Entity(this.getImgWidth(),this.getImgHeight(),i,fileName));
		
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
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the imgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * @param imgWidth the imgWidth to set
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @return the imgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param imgHeight the imgHeight to set
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
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
