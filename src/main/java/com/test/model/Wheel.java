package com.test.model;

public final class Wheel {
	private double price;
	private double spokesPrice;
	private double rimPrice;
	private Tyre tyre;

	public double getPrice() {
		return price;
	}

	public double getSpokesPrice() {
		return spokesPrice;
	}

	public double getRimPrice() {
		return rimPrice;
	}

	public Tyre getTyre() {
		return tyre;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSpokesPrice(double spokesPrice) {
		this.spokesPrice = spokesPrice;
	}

	public void setRimPrice(double rimPrice) {
		this.rimPrice = rimPrice;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

}
