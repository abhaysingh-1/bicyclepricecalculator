package com.test.utils;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.model.Chain;
import com.test.model.Cycle;
import com.test.model.Frame;
import com.test.model.HandleBar;
import com.test.model.Seat;
import com.test.model.Tyre;
import com.test.model.Wheel;

public class CycleUtilTest {
	
	private static Properties property = new Properties();
	private static final double DELTA = 1e-15;
	@BeforeClass
	public static void setUp() throws IOException {
			FileInputStream propertyFile = new FileInputStream("./src/test/resources/price.properties");
			property.load(propertyFile);
	}

	@Test
	public void testIsOldDate() {
		assertTrue(CycleUtil.isOldDate("Jan 2016 - Nov 2016"));	
	}
	
	@Test
	public void testIsOldDate_negative() {
		assertFalse(CycleUtil.isOldDate("Dec 2016"));	
	}
	
	@Test
	public void testSetTyrePrice_forTubeTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tube", true);
		assertEquals(300, tyre.getPrice(), DELTA);
	}
	
	@Test
	public void testSetTyrePrice_forTubelessTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tubeless", true);
		assertEquals(200, tyre.getPrice(), DELTA);
	}
	
	@Test
	public void testSetTyrePrice_forNewDateRangeForTubeless() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tubeless", false);
		assertEquals(300, tyre.getPrice(), DELTA);
	}
	
	@Test
	public void testSetTyrePrice_forNewDateRangeForTubeTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tube", false);
		assertEquals(380, tyre.getPrice(), DELTA);
	}
	
	@Test
	public void testSetWheelPrice_forTubelessTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tubeless", true);
		Wheel wheel = CycleUtil.setWheelPrice(tyre, property, true);
		assertEquals(450, wheel.getPrice(), DELTA);
	}
	
	@Test
	public void testSetWheelPrice_forTubeTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tube", true);
		Wheel wheel = CycleUtil.setWheelPrice(tyre, property, true);
		assertEquals(550, wheel.getPrice(), DELTA);
	}
	
	@Test
	public void testSetWheelPrice_forNewDateRangeWithTubeTyre() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tube", false);
		Wheel wheel = CycleUtil.setWheelPrice(tyre, property, false);
		assertEquals(700, wheel.getPrice(), DELTA);
	}
	
	@Test
	public void testSetWheelPrice_forNewDateRangeWithTubeless() {
		Tyre tyre = CycleUtil.setTyrePrice(property, "tubeless", false);
		Wheel wheel = CycleUtil.setWheelPrice(tyre, property, false);
		assertEquals(620, wheel.getPrice(), DELTA);
	}
	
	@Test
	public void testSetFramePrice_forNormal_oldPrice() {
		Frame frame = CycleUtil.setFramePrice(property, "Normal", true);
		assertEquals(200, frame.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetFramePrice_forSteel_oldPrice() {
		Frame frame = CycleUtil.setFramePrice(property, "Steel", true);
		assertEquals(400, frame.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetFramePrice_forNormal_newPrice() {
		Frame frame = CycleUtil.setFramePrice(property, "Normal", false);
		assertEquals(250, frame.getPrice(), DELTA);
	}
	
	@Test
	public void testSetFramePrice_forSteel_newPrice() {
		Frame frame = CycleUtil.setFramePrice(property, "Steel", false);
		assertEquals(450, frame.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetHandleBarPrice_oldPrice() {
		HandleBar handleBar = CycleUtil.setHandleBarPrice(property, true);
		assertEquals(150, handleBar.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetHandleBarPrice_newPrice() {
		HandleBar handleBar = CycleUtil.setHandleBarPrice(property, false);
		assertEquals(250, handleBar.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetChainPrice_oldPrice() {
		Chain chain = CycleUtil.setChainPrice(property, 4, true);
		assertEquals(600, chain.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetChainPrice_newPrice() {
		Chain chain = CycleUtil.setChainPrice(property, 4, false);
		assertEquals(850, chain.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetSeatPrice_oldPrice() {
		Seat seat = CycleUtil.setSeatPrice(property, true);
		assertEquals(200, seat.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetSeatPrice_newPrice() {
		Seat seat = CycleUtil.setSeatPrice(property, false);
		assertEquals(250, seat.getPrice(), DELTA);	
	}
	
	@Test
	public void testSetCyclePrice() {
		Wheel wheel = new Wheel();
		wheel.setPrice(200);
		Frame frame = new Frame();
		frame.setPrice(100);
		Seat seat = new Seat();
		seat.setPrice(400);
		HandleBar handleBar = new HandleBar();
		handleBar.setPrice(500);
		Chain chain = new Chain();
		chain.setPrice(150);
		Cycle cycle = new Cycle(wheel, frame, seat, handleBar, chain);
		cycle = CycleUtil.setCyclePrice(cycle);
		
		assertEquals(1350, cycle.getPrice(), DELTA);
	}
}
