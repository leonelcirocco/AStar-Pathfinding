package world;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import animal.BaseAnimal;
import animal.Human;
import patfhinder.AStar;
import patfhinder.Move;

public class World extends BasicGameState{
	private ArrayList<Human> humans = new ArrayList<>();
	private Food food;
	private Bed bed;
	private int hour = 0;
	private int mins = 0;
	private ArrayList<Colisionable>walls;
	private int squareSize=32;
	private AStar pathfinding;
	private Timer turns;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		humans.add(new Human(5, 5));
		walls = new ArrayList<>();
		walls.add(new Wall(4, 4));
		walls.add(new Wall(4, 5));
		walls.add(new Wall(4, 6));
		walls.add(new Wall(4, 7));
		walls.add(new Wall(4, 8));
		walls.add(new Wall(5, 8));
		walls.add(new Wall(6, 8));
		walls.add(new Wall(5, 4));
		walls.add(new Wall(6, 4));
		walls.add(new Wall(6, 5));
		walls.add(new Wall(7, 5));
		walls.add(new Wall(7, 4));
		walls.add(new Wall(7, 3));
		walls.add(new Wall(4, 2));
		food = new Food(3, 3);
		bed = new Bed(1, 1);
		
		
		turns = new Timer();
		turns.schedule(new TimerTask() {
			
			@Override
			public void run() {
				time();
				for(int i=0;i<humans.size();i++){
					humans.get(i).update();
					if(humans.get(i).getMove().isEmpty()&&!humans.get(i).isBusy()){
						if(humans.get(i).getCurrentNeed()==-1){
							Random random = new Random();
							int x = random.nextInt(5-0+1)+0;
							int y = random.nextInt(5-0+1)+0;
							boolean occupied = true;
							while(occupied){
								occupied = false;
								int high = humans.get(i).getX()/squareSize+4;
								int low = humans.get(i).getX()/squareSize-3;
								x = (random.nextInt(high-low)+low)*squareSize;
								y = (random.nextInt(high-low)+low)*squareSize;
								for(int l=0;l<walls.size();l++){
									if(walls.get(i).getX()==x&&walls.get(i).getY()==y){
										occupied = true;
									}
								}
							}
							System.out.println("asda");
							Move wander = new Move(x, y);
							humans.get(i).setMove(findPath(humans.get(i),wander));
						}
						if(humans.get(i).getCurrentNeed()==0){
							humans.get(i).setMove(findPath(humans.get(i), new Move(food.getX(),food.getY())));
						}
						if(humans.get(i).getCurrentNeed()==1){
							humans.get(i).setMove(findPath(humans.get(i), new Move(bed.getX(),bed.getY())));
						}
					}
					
					if(humans.get(i).getMove().size()>0){
						humans.get(i).setX(humans.get(i).getMove().get(0).getX());
						humans.get(i).setY(humans.get(i).getMove().get(0).getY());
						humans.get(i).getMove().remove(0);
					}
					searchNeeds(i);
				}
				
			}
		}, 0,300);
	}
	private void searchNeeds(int i){
		if(humans.get(i).getX()==food.getX()&&humans.get(i).getY()==food.getY()){
			humans.get(i).setBusy(true);
			if(humans.get(i).getHunger()<humans.get(i).getMaxNeed()){
				humans.get(i).setHunger(humans.get(i).getHunger()+food.getNeed());
			}
			if(humans.get(i).getHunger()>humans.get(i).getMinHunger()){
				humans.get(i).setBusy(false);
			}
		}
		if(humans.get(i).getX()==bed.getX()&&humans.get(i).getY()==bed.getY()){
			humans.get(i).setBusy(true);
			if(humans.get(i).getSleep()<humans.get(i).getMaxNeed()){
				humans.get(i).setSleep(humans.get(i).getSleep()+bed.getNeed());
			}
			if(humans.get(i).getSleep()>humans.get(i).getMinSleep()){
				humans.get(i).setBusy(false);
			}
		}
	}
	private void time(){
		if(mins<59){
			mins++;
		}else{
			mins=0;
			hour++;
		}
		if(hour==25){
			hour=0;
		}
	}
	public ArrayList<Move> findPath(BaseAnimal animal, Move target){
		pathfinding = new AStar(new Move(animal.getX(), animal.getY()), target,walls);
		ArrayList<Move>move = new ArrayList<>();
		for(int i=0;i<pathfinding.getUsed().size();i++){
			move.add(new Move(pathfinding.getUsed().get(i).getX(), pathfinding.getUsed().get(i).getY()));
		}
		return move;
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		humans.get(0).getAnimation().draw(humans.get(0).getX(), humans.get(0).getY());
		for(int i=0;i<walls.size();i++){
			arg2.draw(new Rectangle(walls.get(i).getX(), walls.get(i).getY(), squareSize, squareSize));
		}
		arg2.draw(new Rectangle(food.getX(), food.getY(), squareSize, squareSize));
		arg2.draw(new Circle(bed.getX(), bed.getY(), 12));
		arg2.drawString("hambre: " + Integer.toString(humans.get(0).getHunger()), 700, 30);
		arg2.drawString("sueño: " + Integer.toString(humans.get(0).getSleep()), 700, 50);
		arg2.drawString("hora: " + Integer.toString(hour) + ":" + Integer.toString(mins), 700, 70);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return 0;
	}
	
	public int roundNumber(int number){
		return number*squareSize;
	}
	public void pathfinder(Colisionable start, Colisionable end){
		
	}

}
