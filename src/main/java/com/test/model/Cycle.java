package com.test.model;

public class Cycle {
	private double price;
	private Wheel wheel;
	private Frame frame;
	private Seat seat;
	private HandleBar handleBar;
	private Chain chain;

	public Cycle(Wheel wheel, Frame frame, Seat seat, HandleBar handleBar, Chain chain) {
		super();
		this.wheel = wheel;
		this.frame = frame;
		this.seat = seat;
		this.handleBar = handleBar;
		this.chain = chain;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public Frame getFrame() {
		return frame;
	}

	public Seat getSeat() {
		return seat;
	}

	public HandleBar getHandleBar() {
		return handleBar;
	}

	public Chain getChain() {
		return chain;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public void setHandleBar(HandleBar handleBar) {
		this.handleBar = handleBar;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
