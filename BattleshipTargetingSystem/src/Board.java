import java.util.ArrayList;
import java.util.Random;

public class Board {

	private Box[][] grid;
	private ArrayList<Ship> ships;
	private int paridy;
	private Random rnd;
	
	public Board() {
		this.grid = new Box[10][10];
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				grid[i][j] = new Box();
			}
		}
		this.ships = new ArrayList<Ship>();
		addShip(new Ship(2));
		addShip(new Ship(3));
		addShip(new Ship(3));
		addShip(new Ship(4));
		addShip(new Ship(5));

		this.rnd = new Random();
	}
	
	public boolean shoot(Position p) {
		if(hasShip(p)) {
			this.hit(p);
			return true;
		}
		this.miss(p);
		return false;
	}
	
	private boolean hasShip(Position p) {
		for(Ship s : this.ships) {
			if(s.getX()==p.getX() && s.getY()==p.getY())
				return true;
		}
		return false;
	}
	
	public void hit(Position p) {
		this.grid[p.getX()][p.getY()].hit();
	}
	
	public void miss(Position p) {
		this.grid[p.getX()][p.getY()].miss();
	}
	
	public void addShip(Ship s) {
		this.ships.add(s);
	}
	
	public void removeShip(int n) {
		if( n < (ships.size()-1) )
			this.ships.remove(n);
	}
	
	public Box get(int i, int j) {
		return this.grid[i][j];
	}
	
	public Box get(Position p) {
		return this.grid[p.getX()][p.getY()];
	}
	
	public ArrayList<Ship> getShips() {
		return this.ships;
	}
}
