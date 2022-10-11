package com.wileyedge.dto;

import java.math.BigDecimal;

public class Change {

	public enum CoinType {
		PENNY, NICKEL, DIME, QUARTER
	}

	private int countPennies;
	private int countNickels;
	private int countDimes;
	private int countQuarters;
	private BigDecimal change;

	public Change() {

	}

	public void doDenomination(BigDecimal change) {
		this.setChange(change);
		change = change.multiply(new BigDecimal("100")); // convert to pennies

		while (change.intValueExact() > 0) {
			while (change.intValueExact() >= 25) {
				countCoin(CoinType.QUARTER);
				change = change.subtract(new BigDecimal("25"));
			}
			while (change.intValueExact() >= 10) {
				countCoin(CoinType.DIME);
				change = change.subtract(new BigDecimal("10"));
			}
			while (change.intValueExact() >= 5) {
				countCoin(CoinType.NICKEL);
				change = change.subtract(new BigDecimal("5"));
			}
			while (change.intValueExact() >= 1) {
				countCoin(CoinType.PENNY);
				change = change.subtract(new BigDecimal("1"));
			}
		}
	}

	private void countCoin(CoinType type) {
		switch (type) {
		case QUARTER:
			countQuarters++;
			break;
		case DIME:
			countDimes++;
			break;
		case NICKEL:
			countNickels++;
			break;
		case PENNY:
			countPennies++;
			break;
		}
	}

	public int getCountPennies() {
		return countPennies;
	}

	public void setCountPennies(int countPennies) {
		this.countPennies = countPennies;
	}

	public int getCountNickels() {
		return countNickels;
	}

	public void setCountNickels(int countNickels) {
		this.countNickels = countNickels;
	}

	public int getCountDimes() {
		return countDimes;
	}

	public void setCountDimes(int countDimes) {
		this.countDimes = countDimes;
	}

	public int getCountQuarters() {
		return countQuarters;
	}

	public void setCountQuarters(int countQuarters) {
		this.countQuarters = countQuarters;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

}
