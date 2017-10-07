package animal;

import java.util.ArrayList;

public class Needs {
	private ArrayList<Boolean> needs = new ArrayList<>();
	public Needs(Human human) {
		needs.add(false);
		needs.add(false);
	}
	public boolean isHungry(){
		return needs.get(0);
	}
	public void setHungry(boolean hungry){
		needs.set(0, hungry);
	}
	public boolean isSleep(){
		return(needs.get(1));
	}
	public void setSleep(boolean sleep){
		needs.set(1, sleep);
	}
	public int currentNeed(){
		for(int i=0;i<needs.size();i++){
			if(needs.get(i)){
				return i;
			}
		}
		return -1;
	}
}
