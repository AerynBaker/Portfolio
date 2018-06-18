
public class CaeserCipher {

	static double[] Expected = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 
			0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8,
			1.0, 2.4, 0.2, 2.0, 0.1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!");
		System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
	}
	
	public static int toInt(char c) {
		return (int)(c-97);
	}
	
	public static char toChar(int i) {
		return (char)(i+97);
	}
	
	public static char shift(int i, char c) {
		i=(i+toInt(c)) % 26;
		return toChar(i);
	}

	public static String decode(int i, String c) {
		String p = "";
		for(int n=0; n<c.length(); n++)
			p = p + shift(26-i, c.charAt(n));
		return p;
	}
	
	public static String encode(int i, String p) {
		String c = "";
		for(int n=0; n<p.length(); n++)
			c = c + shift(i, p.charAt(n));
		return c;
	}
	
	public static double numLowers(String s) {
		double num=0;
		for(int n=0; n<s.length(); n++) {
			if(toInt(s.charAt(n))>=0 && toInt(s.charAt(n))<=25)
				num++;
		}
		return num;
	}
	
	public static int countOf(char c, String s) {
		int num=0;
		for(int n=0; n<s.length(); n++) {
			if(s.charAt(n)==c)
				num++;
		}
		return num;
	}
	
	public static double ratioOf(char c, String s) {
		return (countOf(c,s)/numLowers(s));
	}
	
	public static double[] freq(String s) {
		double[] table = new double[26];
		for(int n=0; n<26; n++) {
			table[n]=ratioOf(toChar(n),s);
		}
		return table;
	}
	
	public static double[] rotate(int i, double[] table) {
		double[] newTable = new double[26];
		for(int n=0; n<26; n++) {
			newTable[n] = table[((n+i)%26)];
		}
		return newTable;
	}
	
	public static double chisqr(double[] table) {
		double chi=0;
		for(int n=0; n<26; n++) {
			chi+=( ((table[n]-Expected[n])*(table[n]-Expected[n])) / (Expected[n]));
		}
		return chi;
	}
	
	public static int position(double d, double[] table) {
		for(int n=0; n<26; n++) {
			if(table[n]==d)
				return n;
		}
		return -1;
	}
	
	public static String crack(String s) {
		double[] table = freq(s);
		double bestChi = 1000000000;
		int bestChiIndex = -1;
		double myChi;
		for(int n=0; n<26; n++) {
			myChi=chisqr(rotate(n, table));
			if(myChi<bestChi) {
				bestChi=myChi;
				bestChiIndex=n;
			}
		}
		return decode(bestChiIndex, s);	
	}
}
