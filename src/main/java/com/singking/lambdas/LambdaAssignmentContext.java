package com.singking.lambdas;

/**
 * http://www.java2s.com/Tutorials/Java/Java_Lambda/0060__Java_Lambda_Expression_Context.htm
 * 
 * @author kingt
 *
 */
public class LambdaAssignmentContext {

	public static void main(String[] argv) {
		Calculator iCal = (x, y) -> x + y;// from www.j a v a 2s .c o m
		System.out.println(iCal.calculate(100, 22));
	}

}

@FunctionalInterface
interface Calculator {
	int calculate(int x, int y);
}
