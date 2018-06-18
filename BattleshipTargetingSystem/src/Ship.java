
public class Ship {
	private int size;
	private boolean sunk;
	private Position position;
	private boolean isVertical;
	
	public Ship(int size) {
		this.size = size;
		this.sunk = false;
		this.position = new Position(0,0);
		this.isVertical = false;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isSunk() {
		return sunk;
	}
	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}
	public int getX() {
		return position.getX();
	}
	public int getY() {
		return position.getY();
	}
	public void setX(int x) {
		position.setX(x);
	}
	public void setY(int y) {
		position.setY(y);
	}
	public boolean isVertical() {
		return isVertical;
	}
	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
}
