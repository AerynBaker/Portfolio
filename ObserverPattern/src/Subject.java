
public interface Subject {

	public void register(Observer o);
	public void unregister(int n);
	public void notifyObserver();
}
