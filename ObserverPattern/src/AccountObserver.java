
public class AccountObserver implements Observer{

	private int vidCount;
	private int viewCount;
	private int subCount;
	private int observerID;
	private boolean sleep;
	
	private static int observerIDTracker = 0;
	
	private Subject accountGrabber;
	
	public AccountObserver(Subject gbr){
		this.accountGrabber = gbr;
		this.observerID = ++observerIDTracker;
		
		System.out.println("New observer " + this.observerID);
		accountGrabber.register(this);
	}
	
	@Override
	public void update(int subCount, int vidCount, int viewCount) {
		// TODO Auto-generated method stub
		this.vidCount = vidCount;
		this.viewCount = viewCount;
		this.subCount = subCount;
		printInfo();
	}
	
	public void printInfo() {
		if(sleep)
			return;
		System.out.println(observerID);
		System.out.println(" Videos: " + vidCount);
		System.out.println(" Viewers: " + viewCount);
		System.out.println(" Subscribers: " + subCount);
	}
	
	public String toggleSleep(){
		if(this.sleep){
			this.sleep = false;
			return "is awake.";
		}
		this.sleep = true;
		return "is sleeping.";
		
	}
}
