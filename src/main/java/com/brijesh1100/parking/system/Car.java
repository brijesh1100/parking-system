package com.brijesh1100.parking.system;

public class Car extends Vehicle implements Comparable<Car>{

	public Car(String number, String colour) {
		super(number, colour, VehicleType.CAR);
	}

	@Override
	public int compareTo(Car o) {
		if (this.colour.compareTo(o.colour) > 1) {
			return 1;
		} else if (this.colour.compareTo(o.colour) < 1) {
			return -1;
		}
		return 0;
	}

}
