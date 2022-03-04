import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.out;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private Random rand=new Random();
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState(boolean rain) {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors

		if (rain){
			if (currentState>0)
				nextState=currentState-1;
			else
			if (!neighbors.isEmpty() && neighbors.get(0).getState()>0)
				nextState=6;
		}

		else {
			if (currentState == 0) {
				if (aliveNeighborsNum() == 3)
					nextState = 1;
				else
					nextState = 0;
			} else {
				if (aliveNeighborsNum() == 2 || aliveNeighborsNum() == 3)
					nextState = 1;
				else
					nextState = 0;
			}
		}
	}

	public void changeState() {
		currentState = nextState;
	}

	public void clearNeighbors(){
		neighbors.clear();
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point

	private int aliveNeighborsNum() {
		int counter=0;
		for (Point point:neighbors){
			if (point.getState()==1){
				counter++;
			}
		}
		return counter;
	}

	public void drop(){
		if (rand.nextInt(100)<=5){
			currentState=6;
		}
	}
}
