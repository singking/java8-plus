package com.singking.lambdas.ranges;

import java.math.BigDecimal;

public class TestNpe {
	public static void main(String[] args) {

		double v = 2.1;
		v = -0.00000001;
		BigDecimal x = new BigDecimal("" + v);
		System.out.println("value: " + v);
	}
}
