package com.singking.lambdas.ranges;

import java.util.stream.IntStream;

public class MainModulus {

	public static void main(String[] args) {
		int RANGE_END = 10;
		IntStream.range(1, RANGE_END).forEach(System.out::println);
		IntStream.rangeClosed(1, RANGE_END).average().ifPresent(System.out::println);
		IntStream.rangeClosed(1, RANGE_END).max().ifPresent(System.out::println);

		boolean anyMatch = IntStream.rangeClosed(1, RANGE_END).anyMatch(x -> x == 99);
		System.out.println(anyMatch);
		anyMatch = IntStream.rangeClosed(1, RANGE_END).anyMatch(x -> x == 5);
		System.out.println(anyMatch);
		long count = IntStream.rangeClosed(1, RANGE_END).count();
		System.out.println(count);

	}

}
