package HW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * basically a little driver class, we will read files from the system to do caculations.
 * makes a infixexpression to evaluate the lines.
 * 
 * @author austin cheng 4/25/17 Eclipse, Windows 10 
 */
public class stackTester
{
	public static Scanner userScanner = new Scanner(System.in);

	// YOU'RE NOT ALLOWED TO CHANGE THIS METHOD, AND YOU MUST USE IT:
	/**
	 * attempts to scan the given file
	 * @return the scanner for the file or null
	 */
	public static Scanner openInputFile()
	{
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);
		try
		{
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe)
		{
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	} // end openInputFile

	public static void testHW1()
	{ // testing InfixExpression more:
		InfixExpression infix1, infix2;
		infix1 = new InfixExpression(null);
		infix2 = new InfixExpression("( 234.5 * ( 5.6 + 7.0 ) ) / 100.2");
	} // end stackTester

	public static void main(String args[])
	{
//		Scanner scanalations = openInputFile(); // new scanner to use
//		String line; //a line of the text doc
//		InfixExpression hold; // just hold the final value to print
//		while (scanalations.hasNext())
//		{
//			line = scanalations.nextLine();
//			hold = new InfixExpression(line);
//			System.out.println(hold);
//		}
		testHW1();
	}
}
