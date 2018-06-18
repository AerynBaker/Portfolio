import java.util.ArrayList;
import java.util.Random;

public class TargetingAI {

	private Heap targetStack;
	private int difficulty;
	private int[][] trackedDensities;
	private int pickRange;
	private Random rnd;
	private int parity;
	private Position currentTarget;
	
	private ArrayList<String> output;
	
	public TargetingAI(int difficulty) {
		targetStack = new Heap();
		output = new ArrayList<String>();
		this.difficulty = difficulty;
		trackedDensities = new int[10][10];
		pickRange = 5 + (difficulty*5);
		rnd = new Random();
		parity = 1;
		initDensities();
	}
	
	private void initDensities() {
		output.add("initializing densities");
		//set quadrant 1
//		grid[0][0].setDensity(8.0);
//		
//		grid[0][1].setDensity(11.5);
//		grid[1][0].setDensity(11.5);
//		
//		grid[1][1].setDensity(14.3);
//		grid[0][2].setDensity(14.3);
//		grid[2][0].setDensity(14.3);
//		
//		grid[3][0].setDensity(15.9);
//		grid[0][3].setDensity(15.9);
//		
//		grid[1][2].setDensity(16.6);
//		grid[2][1].setDensity(16.6);
//		
//		grid[4][0].setDensity(16.7);
//		grid[0][4].setDensity(16.7);
//		
//		grid[1][3].setDensity(17.8);
//		grid[3][1].setDensity(17.8);
//		
//		grid[2][2].setDensity(18.4);
//		grid[4][1].setDensity(18.4);
//		grid[1][4].setDensity(18.4);
//		
//		grid[2][3].setDensity(19.4);
//		grid[3][2].setDensity(19.4);
//		
//		grid[4][2].setDensity(19.9);
//		grid[2][4].setDensity(19.9);
//		
//		grid[3][3].setDensity(20.3);
//		
//		grid[4][3].setDensity(20.8);
//		grid[3][4].setDensity(20.8);
//		
//		grid[4][4].setDensity(21.4);
//		//mirror on x
//		for(int i=0; i<5; i++) {
//			for(int j=0; j<5; j++) {
//				grid[9-i][j].setDensity(grid[i][j].getDensity());
//				grid[i][9-j].setDensity(grid[i][j].getDensity());
//				grid[9-i][9-j].setDensity(grid[i][j].getDensity());
//			}
//		}
		//mirror on y
		output.add("...densities initialized");
	}
	
	private void populateStack(Position p, Board targetBoard) {
		//add to the stack each adjacent target in order of density
		//called after successful hit
		targetStack.add(populate(p));
		
		//sort stack by densities
		targetStack.sift(trackedDensities);
	}
	
	public Position target(Board targetBoard) {
		if(targetStack.size()>0) {
			currentTarget = targetStack.peek();
			output.add("next target: ");
		}
		else {
			currentTarget = hunt(targetBoard);
		}
		return currentTarget;
	}
	
	public Position hunt(Board targetBoard) {
		//no calculation, return one of 10 highest density
		checkParity(targetBoard);
		Heap searchStack = new Heap();

		for(int i=0; i<10; i++) { //fill with all positions
			for(int j=0; j<10; j++) {
				searchStack.add(new Position(i,j));
			}
		}
		searchStack.sift(trackedDensities); //sift highest to top
		int pick = Math.abs(rnd.nextInt()%pickRange);
		return searchStack.get(pick);
	}

	private void checkParity(Board targetBoard) {
		//check and set parity to largest possible value
		int n = 99;
		for(Ship s: targetBoard.getShips()) {
			if(s.getSize()<n)
				n=s.getSize();
		}
		this.parity = n;
	}
	
	private void calculateNegDensity(Position p, Board targetBoard) {
		int x = p.getX();
		int y = p.getY();
		trackedDensities[x][y]=0;
		for(Ship s : targetBoard.getShips()) {
			int len = s.getSize();
			for(int i=1; i<len; i++) {
				if(y<(9-i)) trackedDensities[x][y+i]-=(len-i);
				if(y>(0+i)) trackedDensities[x][y-i]-=(len-i);
				if(x<(9-i)) trackedDensities[x+i][y]-=(len-i);
				if(x>(0+i)) trackedDensities[x-i][y]-=(len-i);
			}
		}	
	}
	
	public void calculate(boolean hit, Board targetBoard) {
		if(hit)
			populateStack(currentTarget, targetBoard);
		calculateNegDensity(currentTarget, targetBoard);
	}
	
	private ArrayList<Position> populate(Position hit) {
		ArrayList<Position> set = new ArrayList<Position>();
		int x = hit.getX();
		int y = hit.getY();
		set.add(new Position(x,y-1));
		set.add(new Position(x,y+1));
		set.add(new Position(x-1,y));
		set.add(new Position(x+1,y));
		for(Position p: set) {
			if(p.getX()<0 || p.getX()>10 || p.getY()<0 || p.getY()>10 || 
					((p.getX()+p.getY())%parity)==0 || trackedDensities[p.getX()][p.getY()]==0)
				set.remove(p);
		}
		return set;
	}
}
