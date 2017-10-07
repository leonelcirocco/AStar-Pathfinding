package animal;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Human extends BaseAnimal{
	private Needs needs;
	private int currentNeed=-1;
	public Human(int x, int y) throws SlickException {
		super(x,y);
		move = new ArrayList<>();
		hunger=1000;
		sleep=1000;
		minHunger=950;
		minSleep=900;
		needs = new Needs(this);
		animation = new Animation();
		SpriteSheet spriteSheet = new SpriteSheet("data/Personaje.png", 9, 10);
		animation.addFrame(spriteSheet.getSubImage(0, 0).getScaledCopy(2), 100);
	}
	@Override
	public void update() {
		if(hunger<minHunger){
			needs.setHungry(true);
		}else{
			needs.setHungry(false);
		}
		if(sleep<minSleep){
			needs.setSleep(true);
		}else{
			needs.setSleep(false);
		}
		hunger--;
		sleep--;
		currentNeed=needs.currentNeed();
	}
	public Needs getNeeds() {
		return needs;
	}
	public int getCurrentNeed() {
		return currentNeed;
	}
	
}
