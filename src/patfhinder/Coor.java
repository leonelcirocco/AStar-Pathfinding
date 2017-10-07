package patfhinder;

public class Coor {
	private int x;
	private int y;
	private int movementCost;
	private int euristic;
	private int totalCost;
	public Coor(int x, int y, int movementCost, int euristic) {
		this.x=x;
		this.y=y;
		this.movementCost=movementCost;
		this.euristic=euristic;
		totalCost=euristic+movementCost;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getMovementCost() {
		return movementCost;
	}
	public int getEuristic() {
		return euristic;
	}
	public int getTotalCost() {
		return totalCost;
	}
	

}
