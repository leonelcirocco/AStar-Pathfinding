package world;

public class Bed extends BaseResource{

	public Bed(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNeed() {
		return 5;
	}

}
