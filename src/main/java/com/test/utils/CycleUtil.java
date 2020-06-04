package com.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import com.test.model.Chain;
import com.test.model.Cycle;
import com.test.model.Frame;
import com.test.model.HandleBar;
import com.test.model.Seat;
import com.test.model.Tyre;
import com.test.model.Wheel;

public class CycleUtil {

	public static Tyre setTyrePrice(Properties property, String tyerType, boolean oldDate) {
		Tyre tyre = new Tyre();
		tyre.setTyreType(tyerType);
		if (oldDate) {
			if (tyre != null && tyre.getTyreType().equalsIgnoreCase("Tube")) {

				tyre.setPrice(Double.parseDouble(property.getProperty("tube"))
						+ Double.parseDouble(property.getProperty("tyre")));

			} else if (tyre != null && tyre.getTyreType().equalsIgnoreCase("Tubeless")) {

				tyre.setPrice(Double.parseDouble(property.getProperty("tubeless")));
			}
		} else {
			if (tyre != null && tyre.getTyreType().equalsIgnoreCase("Tube")) {

				tyre.setPrice(Double.parseDouble(property.getProperty("newTube"))
						+ Double.parseDouble(property.getProperty("newTyre")));

			} else if (tyre != null && tyre.getTyreType().equalsIgnoreCase("Tubeless")) {

				tyre.setPrice(Double.parseDouble(property.getProperty("newTubeless")));
			}
		}
		return tyre;

	}

	public static Wheel setWheelPrice(Tyre tyre, Properties property, boolean oldDate) {
		Wheel wheel = new Wheel();
		if (oldDate) {
			wheel.setSpokesPrice(Double.parseDouble(property.getProperty("spokePrice")));
			wheel.setRimPrice(Double.parseDouble(property.getProperty("rimPrice")));
			wheel.setPrice(tyre.getPrice() + wheel.getRimPrice() + wheel.getSpokesPrice());
		} else {
			wheel.setSpokesPrice(Double.parseDouble(property.getProperty("newSpokePrice")));
			wheel.setRimPrice(Double.parseDouble(property.getProperty("newRimPrice")));
			wheel.setPrice(tyre.getPrice() + wheel.getRimPrice() + wheel.getSpokesPrice());
		}
		return wheel;
	}

	public static Frame setFramePrice(Properties property, String frameType, boolean oldDate) {
		Frame frame = new Frame();
		frame.setType(frameType);
		if (oldDate) {
			if (frame.getType().equalsIgnoreCase("Normal")) {
				frame.setPrice(Double.parseDouble(property.getProperty("normalFrame")));
			} else if (frame.getType().equalsIgnoreCase("Steel")) {
				frame.setPrice(Double.parseDouble(property.getProperty("steelFrame")));
			}
		} else {
			if (frame.getType().equalsIgnoreCase("Normal")) {
				frame.setPrice(Double.parseDouble(property.getProperty("newNormalFrame")));
			} else if (frame.getType().equalsIgnoreCase("Steel")) {
				frame.setPrice(Double.parseDouble(property.getProperty("newSteelFrame")));
			}
		}
		return frame;
	}

	public static HandleBar setHandleBarPrice(Properties property, boolean oldDate) {
		HandleBar handleBar = new HandleBar();
		if (oldDate) {
			handleBar.setPrice(Double.parseDouble(property.getProperty("handleBar"))
					+ Double.parseDouble(property.getProperty("break")));
		} else {
			handleBar.setPrice(Double.parseDouble(property.getProperty("newHandleBar"))
					+ Double.parseDouble(property.getProperty("newBreak")));
		}
		return handleBar;
	}

	public static Chain setChainPrice(Properties property, int gear, boolean oldDate) {
		Chain chain = new Chain();
		if (oldDate) {
			chain.setGearPrice(Double.parseDouble(property.getProperty("perGear")) * gear);
			chain.setPrice(Double.parseDouble(property.getProperty("chain")) + chain.getGearPrice());
		} else {
			chain.setGearPrice(Double.parseDouble(property.getProperty("newPerGear")) * gear);
			chain.setPrice(Double.parseDouble(property.getProperty("newChain")) + chain.getGearPrice());
		}
		return chain;
	}
	
	public static Seat setSeatPrice(Properties property, boolean oldDate) {
		Seat seat = new Seat();
		if(oldDate) {
		seat.setPrice(Double.parseDouble(property.getProperty("seat")));
		}else {
			seat.setPrice(Double.parseDouble(property.getProperty("newSeat")));
		}
		return seat;
	}
	

	public static Cycle setCyclePrice(Cycle cycle) {
		cycle.setPrice(cycle.getChain().getPrice() + cycle.getFrame().getPrice() + cycle.getHandleBar().getPrice()
				+ cycle.getWheel().getPrice() + cycle.getSeat().getPrice());
		return cycle;
	}

	public static boolean isOldDate(String dateRange) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
		try {
			Date endDate = null;
			Date beginDate = null;
			String[] stringDate = dateRange.split("-");
			beginDate = formatter.parse(dateRange.split("-")[0].trim());
			if (stringDate.length > 1) {
				endDate = formatter.parse(dateRange.split("-")[1].trim());
			} else {
				LocalDate currentdate = LocalDate.now();
				endDate = formatter.parse(currentdate.getMonth() + " " + currentdate.getYear());
			}
			if (beginDate.compareTo(formatter.parse("Jan 2016")) == 0
					&& endDate.compareTo(formatter.parse("Nov 2016")) == 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
