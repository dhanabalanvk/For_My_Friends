/**
 * 
 */
package com.dhana.uthir;

/**
 * @author DhanabalanV
 *
 */
public class EffectiveTermVO {
	private String effDt;
	private String termDt;

	/**
	 * @return the effDt
	 */
	public String getEffDt() {
		return effDt;
	}

	/**
	 * @param effDt
	 *            the effDt to set
	 */
	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}

	/**
	 * @return the termDt
	 */
	public String getTermDt() {
		return termDt;
	}

	/**
	 * @param termDt
	 *            the termDt to set
	 */
	public void setTermDt(String termDt) {
		this.termDt = termDt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EffectiveTermVO [effDt=");
		builder.append(effDt);
		builder.append(", termDt=");
		builder.append(termDt);
		builder.append("]");
		return builder.toString();
	}

}
