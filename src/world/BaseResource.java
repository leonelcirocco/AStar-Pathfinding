package world;

public abstract class BaseResource extends Colisionable{

	public BaseResource(int x, int y) {
		super(x, y);
	}
	public abstract int getNeed();

}
