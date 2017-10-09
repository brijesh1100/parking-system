package com.brijesh1100.parking.system;

public class ParkingConstant {

	public static final String create_parking_lot = "create_parking_lot";
	public static final String park = "park";
	public static final String leave = "leave";
	public static final String status = "status";
	public static final String registration_numbers_for_cars_with_colour = "registration_numbers_for_cars_with_colour";
	public static final String slot_numbers_for_cars_with_colour = "slot_numbers_for_cars_with_colour";
	public static final String slot_number_for_registration_number = "slot_number_for_registration_number";

	public static int findCommand(String command) {
		int value = 8;
		if (ParkingConstant.create_parking_lot.equals(command)) {
			return 1;
		} else if (ParkingConstant.park.equals(command)) {
			return 2;
		} else if (ParkingConstant.status.equals(command)) {
			return 3;
		} else if (ParkingConstant.leave.equals(command)) {
			return 4;
		} else if (ParkingConstant.registration_numbers_for_cars_with_colour
				.equals(command)) {
			return 5;
		} else if (ParkingConstant.slot_number_for_registration_number.equals(command)) {
			return 6;
		} else if (ParkingConstant.slot_numbers_for_cars_with_colour.equals(command)) {
			return 7;
		} else {
			return value;
		}
	}
}
