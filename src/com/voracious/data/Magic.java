package com.voracious.data;

import com.voracious.graphics.components.Entity;

public class Magic {
	private String fileName;
	private int cost, pow, width, height;
	private Entity E;
	
	public Magic(String name,int mpCost,int Power,int widthParam, int heightParam){
		this.setFileName(name);
		this.setWidth(widthParam);
		this.setHeight(heightParam);
		this.setCost(mpCost);
		this.setPow(Power);
		int []i={0};
		this.setE(new Entity(widthParam,heightParam,i,name));
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
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}


	/**
	 * @return the pow
	 */
	public int getPow() {
		return pow;
	}


	/**
	 * @param pow the pow to set
	 */
	public void setPow(int pow) {
		this.pow = pow;
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
