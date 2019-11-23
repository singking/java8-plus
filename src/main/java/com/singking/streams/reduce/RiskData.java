package com.singking.streams.reduce;

public class RiskData {

	// Key
	private long outcomeId;
	private int lineId;

	//
	// getter/setters get in the way - the calculation is clearer without them
	//

	// risk data
	int liability;
	int betCount;
	int stake;

	public RiskData() {
	}

	public RiskData(int lineId, long outcomeId) {
		super();
		this.outcomeId = outcomeId;
		this.lineId = lineId;
	}

	/**
	 * Fluent Builder pattern could be incorporated.
	 *
	 */
	public RiskData(int lineId, long outcomeId, int betCount, int liability, int stake) {
		super();
		this.outcomeId = outcomeId;
		this.lineId = lineId;
		this.betCount = betCount;
		this.liability = liability;
		this.stake = stake;
	}

	public long getOutcomeId() {
		return outcomeId;
	}

	public int getLineId() {
		return lineId;
	}

	public RiskKey getKey() {
		return new RiskKey(outcomeId, lineId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineId;
		result = prime * result + (int) (outcomeId ^ (outcomeId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RiskData other = (RiskData) obj;
		if (lineId != other.lineId) {
			return false;
		}
		if (outcomeId != other.outcomeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RiskData [outcomeId=" + outcomeId + ", lineId=" + lineId + ", liability=" + liability + ", betCount="
				+ betCount + ", stake=" + stake + "]";
	}
}
