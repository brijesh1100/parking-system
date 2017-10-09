package com.brijesh1100.parking.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingEngine {

	private static ParkingLayout layout;

	public static void main(String[] args) {
		if(args.length>0 && args[0]!=null){
			usingFileInput(args[0]);
		}else{
			usingCommand();
		}
	}

	private static void usingFileInput(String filePath) {
		try {
			File file = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> list = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			run(list);
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filePath);
		    e.printStackTrace();
		}
	}

	private static void run(List<String> list) {
		for (String input : list) {
			String[] arr = input.split(" +");
			execuiteOption(arr);
		}
	}

	private static void usingCommand() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			String[] arr = input.split(" +");
			execuiteOption(arr);
		}
	}

	private static void execuiteOption(String[] arr) {
		int option = ParkingConstant.findCommand(arr[0]);
		switch (option) {
		case 1:
			if (arr.length == 2) {
				createLayout(arr[1]);
			} else {
				System.out.println("\nInvalid option");
			}
			break;
		case 2:
			if (arr.length == 3) {
				int slotNo = layout.findFreeSlot();
				if (slotNo == 0) {
					parkVehicleAtEnd(arr[1], arr[2]);
				} else {
					parkVehicleInBetween(slotNo, arr[1], arr[2]);
				}

			} else {
				System.out.println("\nInvalid Option");
			}
			break;
		case 3:
			display();
			break;
		case 4:
			if (arr.length == 2) {
				exitVehicle(arr[1]);
			} else {
				System.out.println("\nInvalid Option");
			}
			break;
		case 5:
			if (arr.length == 2) {
				findRegistrationNoOfVehicleWithColour(arr[1]);
			} else {
				System.out.println("\nInvalid Option");
			}
			break;
		case 6:
			if (arr.length == 2) {
				findSlotNumberWithRegistrationNumber(arr[1]);
			} else {
				System.out.println("\nInvalid Option");
			}
			break;
		case 7:
			if (arr.length == 2) {
				findSlotNoOfVehicleWithColour(arr[1]);
			} else {
				System.out.println("\nInvalid Option");
			}
			break;
		default:
			System.out.println("\nInvalid option!");
		}
	}

	private static void parkVehicleInBetween(int slotNo, String number,
			String colour) {
		if (number == null && colour == null) {
			System.out.println("\nInvalid option");
			return;
		}
		Car car = new Car(number, colour);
		layout.parkAt(slotNo, car);
		System.out.println("Allocated slot number: " + slotNo);
	}

	private static void findSlotNoOfVehicleWithColour(String colour) {
		List<Integer> slots = layout.findSlotNoOfVehicleWithColour(colour);
		if (slots.size() > 0) {
			System.out.println(slots);
		} else {
			System.out.println("Not Found");
		}
	}

	private static void findSlotNumberWithRegistrationNumber(
			String registrationNumber) {
		int slot = layout
				.findSlotNumberWithRegistrationNumber(registrationNumber);
		if (slot > 0) {
			System.out.println(slot);
		} else {
			System.out.println("Not Found");
		}
	}

	private static void findRegistrationNoOfVehicleWithColour(String colour) {
		List<String> regNos = layout
				.findRegistrationNoOfVehicleWithColour(colour);
		if (regNos.size() > 0) {
			System.out.println(regNos);
		} else {
			System.out.println("Not Found");
		}
	}

	private static void exitVehicle(String slotNo) {
		layout.free(Integer.parseInt(slotNo));
		System.out.println("Slot No " + slotNo + " is free");
	}

	private static void display() {
		layout.display();
	}

	private static void parkVehicleAtEnd(String vehicleNumber, String colour) {
		if (vehicleNumber == null && colour == null) {
			System.out.println("\nInvalid option");
			return;
		}
		Car car = new Car(vehicleNumber, colour);
		try {
			layout.park(car);
		} catch (Exception e) {
			System.out.println("Sorry, parking lot is full");
		}
	}

	private static void createLayout(String capacity) {
		if (capacity == null) {
			System.out.println("\nInvalid option");
			return;
		}
		layout = ParkingLayout.getParkingLayoutInstance(new Integer(capacity));
		System.out.println("Created a parking lot with " + capacity + " slots");
	}
}
