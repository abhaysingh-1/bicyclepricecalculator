package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.model.Chain;
import com.test.model.Cycle;
import com.test.model.CycleBuilder;
import com.test.model.Frame;
import com.test.model.HandleBar;
import com.test.model.Seat;
import com.test.model.Tyre;
import com.test.model.Wheel;

public class TaskResource {
	String tyerType;
	String frameType;
	String numberOfGear;
	String dateRange;

	public TaskResource(String tyerType, String frameType, String numberOfGear, String dateRange) {
		super();
		this.tyerType = tyerType;
		this.frameType = frameType;
		this.numberOfGear = numberOfGear;
		this.dateRange = dateRange;
	}
	public Cycle getCycle() {
		Properties property = new Properties();
		try {
			FileInputStream propertyFile = new FileInputStream("./src/main/resources/price.properties");
			property.load(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean isOld = CycleUtil.isOldDate(dateRange);
		Tyre tyre = CycleUtil.setTyrePrice(property, tyerType, isOld);
		Wheel wheel = CycleUtil.setWheelPrice(tyre, property, isOld);
		Frame frame = CycleUtil.setFramePrice(property, frameType, isOld);
		Seat seat = CycleUtil.setSeatPrice(property, isOld);
		HandleBar handleBar = CycleUtil.setHandleBarPrice(property, isOld);
		Chain chain = CycleUtil.setChainPrice(property, Integer.parseInt(numberOfGear), isOld);
		Cycle cycle = new CycleBuilder().setChain(chain).setFrame(frame).setHandleBar(handleBar).setSeat(seat).setWheel(wheel).getCycle();
		cycle = CycleUtil.setCyclePrice(cycle);
		return cycle;
	}
}
