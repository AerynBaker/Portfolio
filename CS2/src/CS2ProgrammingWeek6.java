import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return("Baker,Andrew,a3655443");
	}
	
	//	Problem #1
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
		int count = 0;
		//through the string
		for(int i = 0; i<str.length(); i++)
		{
			//where there is a y or z
			if(((str.substring(i, i+1).equals("y")) || (str.substring(i, i+1).equals("z"))))
			{
				//if the next char is not a letter count +
				if(!Character.isLetter(str.charAt(i+1)))
					count++;
			}
		}
		return count;
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		//through the string
		for(int i = 0; i< (base.length()-remove.length()); i++) {
			//if there is a sub matching the remove
			if(base.substring(i, i+remove.length()).equals(remove)) {
				//remove the sub
				base = base.substring(0,i) + base.substring(i+remove.length());
			}
		}
		return base;
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true
	
	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
		int not = 0;
		int is = 0;
		//find 'not's
		for(int i = 0; i< (str.length()-3); i++) {
			if(str.substring(i,i+3).equals("not"))
				not++;
		}
		//find 'is's
		for(int i = 0; i< (str.length()-2); i++) {
			if(str.substring(i,i+2).equals("is"))
				is++;
		}
		//if they are equal
		if(not==is)
			return true;
		
		return false;
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
		//through the string
		for(int i = 0; i< str.length(); i++) {
			//if the char is 'g'
			if(str.substring(i,i+1).equals("g")) {
				//if the char on either side are both not 'g' then the whole check is false
				if( !(str.substring(i-1,i).equals("g")) && !(str.substring(i+1,i+2).equals("g")) )
					return false;
			}
		}
		//otherwise its true
		return true;
	}
	
	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
		int triples = 0;
		//through the string, from the 3rd char
		for(int i = 2; i<str.length(); i++) {
			//if the previos two match, triple count +
			if( (str.substring(i,i+1).equals(str.substring(i-1,i))) && (str.substring(i,i+1).equals(str.substring(i-2,i-1))) )
				triples++;
		}
		return triples;
	}
	
	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
		int sum = 0;
		//through the string
		for(int i = 0; i<str.length(); i++) {
			//if its a digit add it to the sum
			if(Character.isDigit(str.charAt(i)))
				sum += Integer.parseInt(str.substring(i,i+1));
		}
		return sum;
	}
	
	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	static String beginningAndEndOfString(String string) 
	{
		String clone = "";
		int j = (string.length()/2); // at halfway point
		//through the string
		for(int i = 0; i<(string.length()/2); i++) {
			//if the beginning char matches the halfway char, count +
			if(string.substring(i,i+1).equals(string.substring(j,j+1)))
				clone = clone + string.substring(i,i+1);
			else //or if they no longer match
				return clone;
			//increment the counters i via loop, j here.
			j++;
		}
		return clone;
	}
	
	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
		String clone = "";
		int i = 0;
		int j = string.length(); //the end of the string
		//while the counters have not met (in the center)
		while(i<j) {
			//if the first matches the last char add to matching string
			if(string.substring(i,i+1).equals(string.substring(j-1,j)))
				clone += string.substring(i,i+1);
			else //or if they no longer match
				return clone;
			//move towards the center
			i++;
			j--;
		}
		return clone;
	}
	
	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
		int largest = 0;
		int running = 1;
		//through string from 2nd char
		for(int i = 1; i<str.length(); i++) {
			//if char matches previous increment running total
			if(str.substring(i,i+1).equals(str.substring(i-1,i)))
				running++;
			else { //when block ends, see if it is the largest found yet
				if(running>=largest)
					largest = running;
				//reset running to 1 (any char matches itself)
				running = 1;
			}
		}
		return largest;
	}
	
	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
		int sum = 0;
		int running = 0;
		//through string
		for(int i=0; i<str.length(); i++) {
			//if its a digit running total +
			if(Character.isDigit(str.charAt(i)))
				running++;
			else { //at end of digits and the number to sum
				sum += Integer.parseInt(str.substring(i-running,i));
				running = 0;
			}
		}
		return sum;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
	}
	
}