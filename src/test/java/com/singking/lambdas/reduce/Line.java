package com.singking.lambdas.reduce;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private int length;

	public Line(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public static void main(String[] args) {
		List<Line> list = new ArrayList<>();
		list.add(new Line(1));
		list.add(new Line(2));
		list.add(new Line(3));
		list.add(new Line(4));
		list.add(new Line(5));

		/*
		 * Sum using Stream.reduce() with BiFunction and BinaryOperator To get
		 * the sum of values we can use Stream.reduce() with BiFunction as
		 * accumulator and BinaryOperator as combiner in parallel processing.
		 *
		 * Here 0 is an identity. Identity is operated using BinaryOperator to
		 * each and every element of stream. If identity is 0, then it results
		 * into the sum of elements of stream in our example.
		 */
		int sum = list.parallelStream().reduce(0, (output, ob) -> output + ob.getLength(), (a, b) -> a + b);
		System.out.println(sum);

	}
}
