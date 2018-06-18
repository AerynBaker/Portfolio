
public class DynamicProg2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start(12,5);
	}
	
	static int list[];
	static int table[][];
	
	public static void start(int n, int mod) {
		list = new int[n];
		table = new int[n][n];
		for(int i=0; i<n; i++)
			list[i] = i;
		System.out.println(""+minMod(0, list, mod));
	}
	public static int minMod(int pos, int[] list, int mod) {
		if(pos>=list.length)
			return getMod(list,mod);
		if(table[pos][pos+1]!=0) {
			switch(table[pos][pos+1]) {
			case 1:
			list[pos]++;
			list[pos+1]++;
			break;
			case 2:
			list[pos]++;
			list[pos+1]--;
			break;
			case 3:
			list[pos]--;
			list[pos+1]--;
			break;
			case 4:
			list[pos]--;
			list[pos+1]++;
			break;
			}
		return minMod(pos+2, list, mod);
		}
		else {
		int[] a = list;
		a[pos]++;
		a[pos+1]++;
		int aMod = getMod(a,mod);
		int[] b = list;
		b[pos]++;
		b[pos+1]--;
		int bMod = getMod(b,mod);
		int[] c = list;
		c[pos]--;
		c[pos+1]--;
		int cMod = getMod(c,mod);
		int[] d = list;
		d[pos]--;
		d[pos+1]++;
		int dMod = getMod(d,mod);
		
		if( (aMod<=bMod) && (aMod<=cMod) && (aMod<=dMod) ) {
			table[pos][pos+1] = 1;
			return minMod(pos+2,a,mod);
		}
		if( (bMod<=cMod) && (aMod<=dMod) ) {
			table[pos][pos+1] = 2;
			return minMod(pos+2,b,mod);
		}
		if( (cMod>=dMod) ) {
			table[pos][pos+1] = 3;
			return minMod(pos+2,c,mod);
		}
		table[pos][pos+1] = 4;
		return minMod(pos+2,d,mod);
		}
	}
	
	public static int getMod(int[] list, int mod) {
		int val = 0;
		for(int i=0; i<list.length; i++)
			val+= list[i];
		return (val%mod);
	}

}
