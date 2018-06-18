
public class Box {
	
	private boolean hit;
	private boolean miss;
	
	public Box() {
		hit=false;
		miss=false;
	}
	public boolean isShot() {
		return (hit||miss);
	}
	public boolean isHit() {
		return hit;
	}
	public boolean isMiss() {
		return miss;
	}
	public void hit() {
		this.hit = true;
	}
	public void miss() {
		this.miss = true;
	}
	public void reset() {
		this.miss = false;
		this.hit = false;
	}
}
