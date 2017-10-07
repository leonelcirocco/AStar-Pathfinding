package patfhinder;

import java.util.ArrayList;

import world.Colisionable;

public class AStar {
	private ArrayList<Coor> open = new ArrayList<>();
	private ArrayList<Coor>used = new ArrayList<>();
	private ArrayList<Coor>usedClosed = new ArrayList<>();
	private ArrayList<Coor> closed = new ArrayList<>();
	private ArrayList<Colisionable> walls = new ArrayList<>();
	public AStar(Move start, Move end, ArrayList<Colisionable>walls) {
		this.walls=walls;
		boolean found = false;
		int lessMovementCost = 0;
		open.add(new Coor(start.getX(), start.getY(), 0, 10000));
		while(!found){
			for(int i=0;i<open.size();i++){
				if(open.size()>lessMovementCost){
					if(open.get(i).getTotalCost()<open.get(lessMovementCost).getTotalCost()){
						lessMovementCost=i;
					}
				}else{
					return;
				}
				if(open.get(i).getX()==end.getX()&&open.get(i).getY()==end.getY()){
					found=true;
				}
			}
			findNextTile(open,used, open.get(lessMovementCost),end);
			used.add(open.get(lessMovementCost));
			open.remove(lessMovementCost);
		}
		found = false;
		closed.add(new Coor(end.getX(),end.getY(),0,1000));
		//TODO rehacer closed
		lessMovementCost = 0;
		while(!found){
			for(int i=0;i<closed.size();i++){
				if(closed.get(i).getTotalCost()<closed.get(lessMovementCost).getTotalCost()){
					lessMovementCost=i;
				}
				if(closed.get(i).getX()==start.getX()&&closed.get(i).getY()==start.getY()){
					found=true;
				}
			}
			findNextTile(closed,usedClosed,used, closed.get(lessMovementCost),start);
			usedClosed.add(closed.get(lessMovementCost));
			closed.remove(lessMovementCost);
			for(int i=0;i<closed.size();i++){
			}
		}
		for(int i=0;i<used.size();i++){
			boolean delete=true;
			for(int l=0;l<usedClosed.size();l++){
				if(used.get(i).getX()==usedClosed.get(l).getX()&&used.get(i).getY()==usedClosed.get(l).getY()){
					delete=false;
				}
			}
			if(delete){
				used.remove(i);
				i=0;
			}
		}
	}
	private void findNextTile(ArrayList<Coor>coor,ArrayList<Coor>used, Coor currentCoor, Move target){
		boolean north = true;
		boolean south = true;
		boolean west = true;
		boolean east = true;
		for(int i=0;i<walls.size();i++){
			if(walls.get(i).getX()==currentCoor.getX()-32&&(walls.get(i).getY()==currentCoor.getY())){
				west=false;
			}
			if(walls.get(i).getX()==currentCoor.getX()+32&&(walls.get(i).getY()==currentCoor.getY())){
				east=false;
				
			}
			if(walls.get(i).getX()==currentCoor.getX()&&(walls.get(i).getY()==currentCoor.getY()-32)){
				north=false;
			}
			if(walls.get(i).getX()==currentCoor.getX()&&(walls.get(i).getY()==currentCoor.getY()+32)){
				south=false;
			}
		}
		for(int i=0;i<used.size();i++){
			if(used.get(i).getX()==currentCoor.getX()-32&&(used.get(i).getY()==currentCoor.getY())){
				west=false;
			}
			if(used.get(i).getX()==currentCoor.getX()+32&&(used.get(i).getY()==currentCoor.getY())){
				east=false;
			}
			if(used.get(i).getX()==currentCoor.getX()&&(used.get(i).getY()==currentCoor.getY()-32)){
				north=false;
			}
			if(used.get(i).getX()==currentCoor.getX()&&(used.get(i).getY()==currentCoor.getY()+32)){
				south=false;
			}
		}
		if(west){
			coor.add(new Coor(currentCoor.getX()-32, currentCoor.getY(), 10, pitagoras(currentCoor.getX()-32, currentCoor.getY(),target)));
		}
		if(east){
			coor.add(new Coor(currentCoor.getX()+32, currentCoor.getY(), 10, pitagoras(currentCoor.getX()+32, currentCoor.getY(),target)));
		}
		if(north){
			coor.add(new Coor(currentCoor.getX(), currentCoor.getY()-32, 10, pitagoras(currentCoor.getX(), currentCoor.getY()-32,target)));
		}
		if(south){
			coor.add(new Coor(currentCoor.getX(), currentCoor.getY()+32, 10, pitagoras(currentCoor.getX(), currentCoor.getY()+32,target)));
		}
	}
	private void findNextTile(ArrayList<Coor>coor,ArrayList<Coor>used,ArrayList<Coor>base, Coor currentCoor,Move target){
		boolean north = true;
		boolean south = true;
		boolean west = true;
		boolean east = true;
		for(int i=0;i<walls.size();i++){
			if(walls.get(i).getX()==currentCoor.getX()-32&&(walls.get(i).getY()==currentCoor.getY())){
				west=false;
			}
			if(walls.get(i).getX()==currentCoor.getX()+32&&(walls.get(i).getY()==currentCoor.getY())){
				east=false;
				
			}
			if(walls.get(i).getX()==currentCoor.getX()&&(walls.get(i).getY()==currentCoor.getY()-32)){
				north=false;
			}
			if(walls.get(i).getX()==currentCoor.getX()&&(walls.get(i).getY()==currentCoor.getY()+32)){
				south=false;
			}
		}
		for(int i=0;i<used.size();i++){
			if(used.get(i).getX()==currentCoor.getX()-32&&(used.get(i).getY()==currentCoor.getY())){
				west=false;
			}
			if(used.get(i).getX()==currentCoor.getX()+32&&(used.get(i).getY()==currentCoor.getY())){
				east=false;
			}
			if(used.get(i).getX()==currentCoor.getX()&&(used.get(i).getY()==currentCoor.getY()-32)){
				north=false;
			}
			if(used.get(i).getX()==currentCoor.getX()&&(used.get(i).getY()==currentCoor.getY()+32)){
				south=false;
			}
		}
		boolean existNorth = false;
		boolean existSouth = false;
		boolean existWest = false;
		boolean existEast = false;
		for(int i=0;i<base.size();i++){
			if(base.get(i).getX()==currentCoor.getX()-32&&(base.get(i).getY()==currentCoor.getY())){
				existWest=true;
			}
			if(base.get(i).getX()==currentCoor.getX()+32&&(base.get(i).getY()==currentCoor.getY())){
				existEast=true;
			}
			if(base.get(i).getX()==currentCoor.getX()&&(base.get(i).getY()==currentCoor.getY()-32)){
				existNorth=true;
			}
			if(base.get(i).getX()==currentCoor.getX()&&(base.get(i).getY()==currentCoor.getY()+32)){
				existSouth=true;
			}
		}
		if(west&&existWest){
			coor.add(new Coor(currentCoor.getX()-32, currentCoor.getY(), 10, pitagoras(currentCoor.getX()-32, currentCoor.getY(),target)));
		}
		if(east&&existEast){
			coor.add(new Coor(currentCoor.getX()+32, currentCoor.getY(), 10, pitagoras(currentCoor.getX()+32, currentCoor.getY(),target)));
		}
		if(north&&existNorth){
			coor.add(new Coor(currentCoor.getX(), currentCoor.getY()-32, 10, pitagoras(currentCoor.getX(), currentCoor.getY()-32,target)));
		}
		if(south&&existSouth){
			coor.add(new Coor(currentCoor.getX(), currentCoor.getY()+32, 10, pitagoras(currentCoor.getX(), currentCoor.getY()+32,target)));
		}
		
	}
	private int pitagoras(int x, int y, Move target){
		float deltaX = Math.abs(x-target.getX());
		float deltaY = Math.abs(y-target.getY());
		return (int) Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
	}
	public ArrayList<Coor> getOpen() {
		return open;
	}
	public ArrayList<Coor> getUsed() {
		return used;
	}
	public ArrayList<Coor> getUsedClosed() {
		return usedClosed;
	}
}
