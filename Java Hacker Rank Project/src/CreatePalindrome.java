import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreatePalindrome 
{
	
	/**
	 * Create a list of letters to create palindromes from
	 * @param str = string to create letter bank from
	 * @return
	 * 				= an array of string objects representing the possible
	 * 				   combinations of letters to make palindrome with
	 */
	public static String[] createLetterBank(String str)
	{
		String[] letterBank = null;
		int size = 0;
		int startPos = 1;
		int charPos = startPos;
		
		// how big is the letter bank going to be?
		for (int position = str.length(); position > 0; position--)
			size += position;
		
		letterBank = new String[size];
		int i = 0;
		
		// fill letter Bank
		for (int j = 0; j < size; j++)
		{
			letterBank[j] = str.substring(i, charPos++);
						
			if (charPos == str.length())
			{
				letterBank[++j] = str.substring(i, charPos);
				i++;
				charPos = ++startPos;
			}			
		}
		
		
		return letterBank;
	}
	
	/**
	 * Reverses the string 
	 * Ex. deron -> nored
	 * @param str = string to reverse
	 * @return
	 * 				= reversed version of str
	 */
	public static String reverse(String str)
	{
		String reverse = "";
		
		if (str.length() == 1)
			return str;
		
		else if (str.length() > 1)
			for (int i = str.length() - 1; i >= 0; i--)
				reverse += str.charAt(i);
		
		return reverse;
	}
	
	/**
	 * Method to determine if a string is a palindrome
	 * @param str = string to determine is a palindrome or not
	 * @return
	 * 				= true if str is a palindrome
	 * 				= false otherwise
	 */
	public static boolean isPalindrome(String str)
	{
		// get the length of the string
		int length = str.length();
		
		// holds the two parts of the string
		String part1 = null;
		String part2 = null;
		
		// make sure the string is all lowercase
		str = str.toLowerCase();
		
		// even length string
		if (length % 2 == 0)
			{
				// divide the string into two
				part1 = str.substring(0, length / 2);
				part2 = str.substring(length / 2);
			}
		
		// odd length string
		else
		{
			// divide the string into two excluding the middle character
			part1 = str.substring(0, length / 2);
			part2 = str.substring(length / 2 + 1);
		}
			
			// reverse one
			part1 = reverse(part1);
			
			// check if they are the same
			if (part1.equals(part2))
				return true;
		
		return false;
	}
	
	/**
	 * Method to build a palindrome if possible from 
	 * two string parameters given
	 * @param a = one string to make palindrome from
	 * @param b = another string to make palindrome from
	 * @return
	 * 				= the palindrome formed
	 * 				= "-1" if palindrome isn't possible
	 */
	public static String buildAPalindrome(String a, String b)
	{
		// will hold the substrings of input variables that will form
		// a palindrome when concatenated together
		String aSubstr = null;
		String bSubstr = null;
		ArrayList<String> palindromes = new ArrayList<String>();
		String palindrome = null;
		
		// we want the palindrome to be of maximum length
		// if there are more than one possible palindrome then 
		// prioritize the string that comes first alphabetically
		
		
		// create list of all possible letter pairs (without changing order)
		String[]	aLetterBank = createLetterBank(a);
		String[] bLetterBank =createLetterBank(b);
		
		// find list of palindromes
		for (int i = 0; i < aLetterBank.length; i++)
		{
			for (int j = 0; j < bLetterBank.length; j++)
			{
				// concatenate the various possibilities and add to 
				// list if concatenation is a palindrome
				if (isPalindrome(aLetterBank[i] + bLetterBank[j]))
					palindromes.add(aLetterBank[i] + bLetterBank[j]);
			}
		}
		
		// determine which palindrome is the longest string 
		// and if they are equal length alphabetical order wins
		if (palindromes.isEmpty() == true)
			return "-1";
			
		palindrome = palindromes.get(0); // added

			
			for (int i = 1; i < palindromes.size(); i++)
			{
				if (palindrome.length() > palindromes.get(i).length())
					continue;
				else if (palindrome.length() < palindromes.get(i).length())
				{
					palindrome = palindromes.get(i);
				}
				else if (palindrome.compareTo(palindromes.get(i)) > 0)
					palindrome = palindromes.get(i);
					
			}
		
		if (palindrome != null)
			return palindrome;
	
		
		return "-1";
	}
	
	
	public static void main(String[] args) 
	{
		File inFile = new File("input.txt");
		File outFile = new File("output.txt");
		Scanner scan = null;
		PrintWriter writer = null;
		String a = null;
		String b = null;
		int queries = 0;
		
		buildAPalindrome("abcdef", "edcba");
		
		try
		{
			// instantiate read and write files
			scan = new Scanner(inFile);
			writer = new PrintWriter(outFile);
			
			if (scan.hasNext())
				queries = Integer.valueOf(scan.nextLine());
			else
				System.out.print("\nThere are no queries in the file! \n");
				
			while (scan.hasNext())
			{
				// read first strings from line in input file
				a = scan.nextLine();
				b = scan.nextLine();
				
				// find and write palindrome
				writer.print(buildAPalindrome(a, b) + "\n");
				
			}
				
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("\nFile is not found ");
		}
		finally
		{
			scan.close();
			writer.close();
		}
		
	}

}
