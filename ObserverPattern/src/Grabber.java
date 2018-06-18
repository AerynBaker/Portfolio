import java.util.ArrayList;

public class Grabber implements Subject{

	private ArrayList<Observer> subscribers;
	private int viewCount;
	private int vidCount;
	private int subCount;
	
	public Grabber() {
		subscribers = new ArrayList<Observer>();
	}
	
	@Override
	public void register(Observer o) {
		// TODO Auto-generated method stub
		this.subscribers.add(o);
	}

	@Override
	public void unregister(int index) {
		// TODO Auto-generated method stub
		subscribers.remove(index);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(Observer o : subscribers) {
			o.update(subCount, vidCount, viewCount);
		}
	}
	
	public void incrementViewCount() {
		this.viewCount++;
	}
	public void incrementVidCount() {
		this.vidCount++;
	}
	public void incrementSubCount() {
		this.subCount++;
	}
	public void setViewCount(int n) {
		this.viewCount = n;
	}
	public void setVidCount(int n) {
		this.vidCount = n;
	}
	public void setSubCount(int n) {
		this.subCount = n;
	}

}
