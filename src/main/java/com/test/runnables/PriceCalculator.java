package com.test.runnables;

import com.test.model.Cycle;
import com.test.utils.TaskResource;

public class PriceCalculator implements Runnable{

	Cycle cycle;
	TaskResource taskResource;
	
	
	public PriceCalculator(TaskResource taskResource) {
		this.taskResource = taskResource;
	}

	public void run() {
		cycle = taskResource.getCycle();
		System.out.println("Cycle price :"+cycle.getPrice());
		System.out.println("Chain price :"+cycle.getChain().getPrice());
		System.out.println("Frame price :"+cycle.getFrame().getPrice());
		System.out.println("HandlerBar price :"+cycle.getHandleBar().getPrice());
		System.out.println("Seat price :"+cycle.getSeat().getPrice());
		System.out.println("Wheel price :"+cycle.getWheel().getPrice());	
	}

}
