package world;

import org.newdawn.slick.Animation;

public  class Colisionable {
	protected int x;
	protected int y;
	protected Animation animation;
	public Colisionable(int x, int y) {
		this.x=x*32;
		this.y=y*32;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public Animation getAnimation() {
		return animation;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

}
