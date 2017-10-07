package animal;


import java.util.ArrayList;

import patfhinder.Move;
import world.Colisionable;

public abstract class BaseAnimal extends Colisionable{
	protected boolean sex;
	protected int hunger;
	protected int minHunger;
	protected int sleep;
	protected int minSleep;
	protected int maxNeed = 1000;
	protected boolean busy = false;
	protected ArrayList<Move>move;
	public BaseAnimal(int x, int y) {
		super(x,y);
	}
	public int getMaxNeed() {
		return maxNeed;
	}
	public abstract void update();
	public ArrayList<Move> getMove() {
		return move;
	}
	public boolean isBusy() {
		return busy;
	}
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	public void setMove(ArrayList<Move> move) {
		this.move = move;
	}
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public int getHunger() {
		return hunger;
	}
	public int getSleep() {
		return sleep;
	}
	public int getMinHunger() {
		return minHunger;
	}
	public int getMinSleep() {
		return minSleep;
	}

}
