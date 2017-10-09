package com.brijesh1100.parking.system;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.junit.Test;

import com.brijesh1100.parking.system.Car;
import com.brijesh1100.parking.system.ParkingLayout;

public class ParkingLayoutTest {

	@Test
	public void testMaxCapacity() {
		int capacity = 5;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		assertEquals(capacity, layout.getMaxCapacity().longValue());
	}

	@Test
	public void testOccupiedCapacity() {
		int capacity = 5;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car1 = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		try {
			layout.park(car1);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			assertEquals(4, layout.getOccupideCapacity().longValue());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testFreeOccupideSlot() {
		int capacity = 5;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car1 = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		try {
			layout.park(car1);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			assertEquals(4, layout.getOccupideCapacity().longValue());
			int slotNo = 2;
			layout.free(slotNo);
			assertEquals(3, layout.getOccupideCapacity().longValue());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testToFindFreeSlot() {
		int capacity = 5;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		try {
			layout.park(car);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			layout.free(2);
			int slot = layout.findFreeSlot();
			assertNotEquals(0, slot);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testToFindFreeSlotAndAddToThoseSlot() {
		int capacity = 5;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		Car car5 = new Car("KA­-01-­HH-­1210", "blue");
		try {
			layout.park(car);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			layout.free(2);
			int slot = layout.findFreeSlot();
			assertNotEquals(0, slot);
			layout.parkAt(slot, car5);
			assertEquals(4, layout.getOccupideCapacity().longValue());
			assertEquals(slot,
					layout.findSlotNumberWithRegistrationNumber("KA­-01-­HH-­1210"));
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testToFindRegistrationNoForVehicleWithColour() {
		int capacity = 6;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car1 = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		Car car5 = new Car("KA­-01-­HH-­1210", "blue");
		Car car6 = new Car("KA­-01-­HH-­1211", "red");
		String colour = "yellow";
		List<String> expected = new ArrayList<>();
		expected.add("KA­-01-­HH-1101");
		try {
			layout.park(car1);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			layout.park(car5);
			layout.park(car6);
			assertThat(layout.findRegistrationNoOfVehicleWithColour(colour), hasItem("KA­-01-­HH-1101"));
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testToFindSlotNoOfVehicleWithColour() {
		int capacity = 6;
		ParkingLayout layout = ParkingLayout.getParkingLayoutInstance(capacity);
		Car car1 = new Car("KA­-01-­HH-1100", "red");
		Car car2 = new Car("KA­-01-­HH-1101", "yellow");
		Car car3 = new Car("KA­-01-­HH-1102", "black");
		Car car4 = new Car("KA­-01-­HH-1103", "white");
		Car car5 = new Car("KA­-01-­HH-­1210", "blue");
		Car car6 = new Car("KA­-01-­HH-­1211", "red");
		String colour = "black";
		try {
			layout.park(car1);
			layout.park(car2);
			layout.park(car3);
			layout.park(car4);
			layout.park(car5);
			layout.park(car6);
			assertThat(layout.findSlotNoOfVehicleWithColour(colour), hasItem(3));
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}
