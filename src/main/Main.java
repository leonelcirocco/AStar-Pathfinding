package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import world.World;


public class Main extends StateBasedGame{

	public Main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private static int HEIGHT = 800;
	private static int WIDTH = 600;
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer((Game) new Main("name"));
		app.setDisplayMode(HEIGHT, WIDTH, false);
		app.setAlwaysRender(true);
		app.setShowFPS(true);
		app.setTargetFrameRate(60);
		app.setVSync(false);
		app.start();
		// TODO Auto-generated method stub

	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new World());
		
	}

}
