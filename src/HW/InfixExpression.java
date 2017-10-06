package HW;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Programming HW Assignment 1 We basically code a caluclator! this class will
 * take a string of tokens that represent an equasion, split them up, and
 * properly calculate the result based off order of operations
 * 
 * @author austin cheng 4/25/17 Eclipse, Windows 10 windows 10, eclipse
 */
public class InfixExpression
{
	private String wholeExpr; // stores our entire expression
	private ArrayList<String> tokens; // has seperate parts of the equasion,
										// either a number or operand.
	private double result; // the result of the equasion
	private boolean divByZero = false; // if /0, set true

	/**
	 * Simple constructior, sets things to base values.
	 */
	public InfixExpression()
	{
		wholeExpr = "";
		tokens = new ArrayList<String>();
		result = 0;
	}

	/**
	 * Constructor that passes a string to parse.
	 * 
	 * @param given
	 */
	public InfixExpression(String given)
	{
		this();
		if (given == null)
			return;
		setWholeExpr(given);
		tokenize();
		Evaluate();
	}

	/**
	 * changes the string into a giant array of tokens for calculation
	 */
	private void tokenize()
	{
		String[] tokenArray = wholeExpr.split("[ \t]+");
		tokens.clear(); // clear the ArrayList
		for (int i = 0; i < tokenArray.length; ++i)
		{
			tokens.add(tokenArray[i]); // add the next token to the ArrayList
		}
	} // end tokenize

	/**
	 * Execute: does the top instruction
	 * 
	 * @param opStack
	 *            the operators
	 * @param valStack
	 *            the values
	 */
	private void execute(Stack<String> opStack, Stack<Double> valStack)
	{
		String op = opStack.pop();
		double temp = 0;
		if (valStack.isEmpty())
		{
			return;
		}
		// System.out.println("pulled " + valStack.peek() + " into right, size :
		// " + valStack.size());
		double rightOperand = valStack.pop();
		if (valStack.isEmpty())
		{
			return;
		}
		// System.out.println("pulled " + valStack.peek() + " into left, size :
		// " + valStack.size());
		double leftOperand = valStack.pop();
		switch (op)
		{
			case "+":
				temp = leftOperand + rightOperand;
				break;
			case "-":
				temp = leftOperand - rightOperand;
				break;
			case "*":
				temp = leftOperand * rightOperand;
				break;
			case "/":
				if (rightOperand == 0.0)
				{
					divByZero = true;
					break;
				}
				temp = leftOperand / rightOperand;
				break;
		}
		valStack.push(temp);
	}

	/**
	 * Evaluate the equasion. Basically, takes the tokens and splits them into
	 * two stacks, one for op and another for numbers, then calculates the vales
	 * and saves it to the result. ADDED /0 TO RETURN 0.0, NOT SURE IF THIS OR
	 * INFINITY if the equasion isnt valid, give back 0 instead
	 */
	public void Evaluate()
	{
		System.out.println("done");
		Stack<String> opStack = new Stack<>(); // hold operands
		Stack<Double> valStack = new Stack<>(); // hold valstack
		String hold; // just hold the one current token to make this code easier
						// to read.
		outerloop: for (int i = 0; i < tokens.size(); i++)
		{
			hold = tokens.get(i);
			// System.out.println(hold);
			if (hold.equals("+") || hold.equals("-") || hold.equals("*") || hold.equals("/"))
			{
				if (opStack.isEmpty())
				{
					opStack.push(hold);
				} else if (precedence(hold) > precedence(opStack.peek()))
				{
					opStack.push(hold);
				} else
				{
					while (!opStack.isEmpty() && precedence(hold) <= precedence(opStack.peek()))
					{
						// System.out.println("while");
						execute(opStack, valStack);
						if (divByZero)
						{
							break outerloop;
						}
					}
					opStack.push(hold);
				}
				// System.out.println("pushed " + opStack.peek() + " into stack.
				// Size: " + opStack.size());
			} else if (hold.equals("("))
			{
				opStack.push(hold);
				// System.out.println("pushed " + opStack.peek() + " into stack.
				// Sizee: " + opStack.size());
			} else if (hold.equals(")"))
			{
				// System.out.println(opStack.size() + " " + valStack.size());
				while (!opStack.peek().equals("("))
				{
					execute(opStack, valStack);
					if (divByZero)
					{
						break outerloop;
					}
				}
				opStack.pop();
			} else
			{
				valStack.push(Double.parseDouble(hold));
				// System.out.println("pushed " + valStack.peek() + " into
				// stack, size : " + valStack.size());
			}
		}
		while (!opStack.isEmpty())
		{
			execute(opStack, valStack);
			if (divByZero)
			{
				break;
			}
		}

		if (valStack.size() == 1 && divByZero == false)
		{
			result = (double) valStack.pop();
		} else
		{
			result = 0;
		}
	}

	/**
	 * @param op
	 *            the given operator
	 * @return how much priority it has
	 */
	public int precedence(String op)
	{
		if (op.equals("/") || op.equals("*"))
		{
			return 2;
		} else if (op.equals("-") || op.equals("+"))
		{
			return 1;
		}
		return 0;
	}

	/**
	 * get the expression
	 * 
	 * @return the whole expression
	 */
	public String getWholeExpr()
	{
		return wholeExpr;
	}

	/**
	 * set the expression
	 * 
	 * @param wholeExpr
	 *            the expression
	 */
	public void setWholeExpr(String wholeExpr)
	{
		this.wholeExpr = wholeExpr;
	}

	/**
	 * gets the result
	 * 
	 * @return the resukt
	 */
	public double getResult()
	{
		return result;
	}

	/**
	 * override to string to just simply print it.
	 */
	public String toString()
	{
		return "Exp: " + wholeExpr + " Res: " + result;
	}

}
