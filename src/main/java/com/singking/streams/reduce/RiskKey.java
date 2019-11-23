package com.singking.streams.reduce;

public class RiskKey {
	private long outcomeId;
	private int lineId;

	public long getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(long outcomeId) {
		this.outcomeId = outcomeId;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public RiskKey(long outcomeId, int lineId) {
		super();
		this.outcomeId = outcomeId;
		this.lineId = lineId;
	}

	@Override
	public String toString() {
		return "RiskKey [outcomeId=" + outcomeId + ", lineId=" + lineId + "]";
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
		RiskKey other = (RiskKey) obj;
		if (lineId != other.lineId) {
			return false;
		}
		if (outcomeId != other.outcomeId) {
			return false;
		}
		return true;
	}
}
