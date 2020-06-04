package com.test.model;

public class CycleBuilder {
	private Wheel wheel;
	private Frame frame;
	private Seat seat;
	private HandleBar handleBar;
	private Chain chain;

	public CycleBuilder setWheel(Wheel wheel) {
		this.wheel = wheel;
		return this;
	}

	public CycleBuilder setFrame(Frame frame) {
		this.frame = frame;
		return this;
	}

	public CycleBuilder setSeat(Seat seat) {
		this.seat = seat;
		return this;
	}

	public CycleBuilder setHandleBar(HandleBar handleBar) {
		this.handleBar = handleBar;
		return this;
	}

	public CycleBuilder setChain(Chain chain) {
		this.chain = chain;
		return this;
	}
	
	public Cycle getCycle() {
		return new Cycle(wheel, frame, seat, handleBar, chain);
	}
}
