import java.util.ArrayList;
import java.util.Scanner;

public class GrabAccounts {
	
	static Scanner in;
	static ArrayList<AccountObserver> accounts;
	static Grabber grabber;

	public static void main() {
		
		in = new Scanner(System.in);
		grabber = new Grabber();
		accounts = new ArrayList<AccountObserver>();
		
		while(printMenu()!=0);
		System.out.println("Exiting...");
	}
	
	public static void createAccount() {
		accounts.add(new AccountObserver(grabber));
	}
	
	public static void toggleSleep() {
		System.out.println("Which account ID would you like to toggle sleep on?");
		int n = in.nextInt();
		System.out.println("Account n is " + accounts.get(n).toggleSleep());
	}
	
	public static void editStats() {
		System.out.println("Which information are you changing?");
		System.out.println("1. Subscriber Count");
		System.out.println("2. Video Count");
		System.out.println("3. View Count");
		int n = in.nextInt();
		System.out.print("What are you changing it to? (int)");
		int m = in.nextInt();
		switch(n){
		case 1:
			grabber.setSubCount(n);
			break;
		case 2:
			grabber.setVidCount(n);
			break;
		case 3:
			grabber.setViewCount(n);
			break;
		}
	}
	
	public static void deleteAccount() {
		System.out.print("Which account ID are you deleting?");
		int n = in.nextInt();
		grabber.unregister(n);
		System.out.print("Account deleted");
	}
	
	public static int printMenu() {
		
		System.out.println("1. Create Account");
		System.out.println("2. Toggle Sleep Account");
		System.out.println("3. Edit Account Information");
		System.out.println("4. Delete Account");
		System.out.println("5. Exit");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			createAccount();
			break;
		case 2:
			toggleSleep();
			break;
		case 3:
			editStats();
			break;
		case 4:
			deleteAccount();
			break;
		case 5:
			return 0;
		}
		return 1;
	}
}
