package com.singking.lambdas.reduce;

import java.util.Arrays;

public class ReduceMain {

	public static void main(String[] args) {

		int[] array = new int[] { 1, 2, 3, 4, 5 };

		int sum = Arrays.stream(array).reduce(0, StatisticsUtility::addIntData);
		System.out.println(sum);

	}
}

class StatisticsUtility {
	public static int addIntData(int num1, int num2) {
		return num1 + num2;
	}
}
