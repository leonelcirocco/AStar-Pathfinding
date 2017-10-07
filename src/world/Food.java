package world;

public class Food extends BaseResource{
	private int quantity = 100;
	public Food(int x, int y) {
		super(x, y);
	}

	@Override
	public int getNeed() {
		quantity=quantity-5;
		return 5;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
