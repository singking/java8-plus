package com.singking.streams.reduce;

public class RiskDataAddition {

	/*
	 * over-simplification
	 */
	public static RiskData compact(RiskData r1, RiskData r2) {
		r1.betCount += r2.betCount;
		r1.liability += r2.liability;
		r1.stake += r2.stake;
		return r1;
	}
}