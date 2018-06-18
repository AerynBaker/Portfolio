
public class DynamicProg1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
		
	}
	
	public static void start() {
		System.out.println(""+pathFind(0,0,false));
		System.out.println(""+bPaths);
	}
	static int bPaths;
	static int[][] table = new int[5][5];
	
	public static int pathFind(int x, int y, boolean bonus) {
		int sum = 0;
		if(x==4 && y==4) {
			if(bonus) return 2;
			return 1;
		}
		if(x==1 && y==3)
			bonus = true;
		if( (x==2 && y==1) || (x==3 && y==3))
			return 0;
		if(x>4 || y>4)
			return 0;
		if(table[x][y]==0) {
			sum = pathFind(x+1,y,bonus) + pathFind(x,y+1,bonus);
			table[x][y] = sum;
			return sum;
		}
		return table[x][y];
	}
}
