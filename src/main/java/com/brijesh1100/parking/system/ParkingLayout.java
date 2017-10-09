package com.brijesh1100.parking.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ParkingLayout class is behave as Doubly Linked list {@value maxCapcity is a
 * max value of LinkedList} {@value occupideCapacity will tell how many slot are
 * full} {@value headSlot is first slot of parking layout} {@value tailSlot is
 * the last slot of parking layout}
 * */
public class ParkingLayout {

	private Integer maxCapacity = 0;
	private Integer occupideCapacity = 0;
	private Slot headSlot;
	private Slot tailSlot;
	private static ParkingLayout layoutInstance;
	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public Integer getOccupideCapacity() {
		return occupideCapacity;
	}

	public Slot getHeadSlot() {
		return headSlot;
	}

	public Slot getTailSlot() {
		return tailSlot;
	}

	/**
	 * @param capacity
	 *            is a max size of Parking layout {@value occupideCapacity is
	 *            initially 0 by default}
	 * */
	private ParkingLayout(int capacity) {
		this.maxCapacity = capacity;
		this.occupideCapacity = 0;
		this.headSlot = null;
		this.tailSlot = null;
	}

	/**
	 * @param vehicle
	 *            detail for parking This method is responsible for park the
	 *            vehicle at last position of previous occupied slot.
	 * @throws Exception
	 * */
	public void park(Vehicle vehicle) throws Exception {
		if (!addSlotIntoLayout(vehicle)) {
			throw new Exception();
		}
	}

	private boolean addSlotIntoLayout(Vehicle vehicle) {
		if (isSlotAvailable()) {
			occupideCapacity++;
			Slot slot = new Slot(occupideCapacity, vehicle, true, null, null);
			if (headSlot == null) {
				slot.isSlotFree = false;
				headSlot = slot;
				tailSlot = headSlot;
			} else {
				slot.isSlotFree = false;
				tailSlot.next = slot;
				slot.prev = tailSlot;
				tailSlot = slot;
			}
			System.out.println("Alocated Slot No: "+occupideCapacity);
			return true;
		}
		return false;
	}

	private boolean isSlotAvailable() {
		if (this.occupideCapacity >= this.maxCapacity) {
			return false;
		} else {
			return true;
		}
	}

	public void free(int slotNo) {
		if (occupideCapacity == 1) {
			headSlot = null;
			tailSlot = null;
			occupideCapacity--;
		}
		Slot slot = find(slotNo);
		if (slot != null) {
			if (headSlot.slotNumber == slot.slotNumber) {
				headSlot = slot.next;
				headSlot.prev = null;
				slot.next.prev = slot.prev;
				occupideCapacity--;
			} else if (tailSlot.slotNumber == slot.slotNumber) {
				tailSlot = slot.prev;
				tailSlot.next = null;
				occupideCapacity--;
			} else {
				Slot beforNode = slot.prev;
				Slot afterNode = slot.next;
				beforNode.next = beforNode.next.next;
				afterNode.prev = afterNode.prev.prev;
				occupideCapacity--;
			}
		}
	}

	private Slot find(int slotNo) {
		if (headSlot.slotNumber == slotNo) {
			return headSlot;
		}
		if (tailSlot.slotNumber == slotNo) {
			return tailSlot;
		}
		Slot slot = headSlot;
		while (slot.next != null) {
			if (slot.slotNumber == slotNo) {
				return slot;
			}
			slot = slot.next;
		}
		return null;
	}

	/**
	 * @return slotNo (if 0 that means tailSlot number is equal to
	 *         occupideCapacity) if not some slot in between is free.
	 * */
	public int findFreeSlot() {
		int slotNo = 0;
		if(this.occupideCapacity==0){
			return slotNo;
		}
		if (tailSlot.slotNumber == this.occupideCapacity) {
			return slotNo;
		} else {
			List<Integer> arr = getAllSlot();
			List<Integer> misingSlot = findMissingSlot(arr, 1, this.maxCapacity);
			Collections.sort(misingSlot);
			if (misingSlot.size() >= 1) {
				slotNo = misingSlot.get(0);
			} else {
				slotNo = 0;
			}
		}
		return slotNo;
	}

	private List<Integer> findMissingSlot(List<Integer> arr, int first,
			Integer last) {
		// numbers between first and a[0]-1
		List<Integer> listOfmissingSlot = new ArrayList<>();
		for (int i = first; i < arr.get(0); i++) {
			listOfmissingSlot.add(i);
		}
		// at index i, a number is missing if it is between
		// a[i-1]+1 and a[i]-1
		for (int i = 1; i < arr.size(); i++) {
			for (int j = 1 + arr.get(i - 1); j < arr.get(i); j++) {
				listOfmissingSlot.add(j);
			}
		}
		// numbers between a[a.length-1] and last
		for (int i = 1 + arr.get(arr.size() - 1); i <= last; i++) {
			listOfmissingSlot.add(i);
		}
		return listOfmissingSlot;
	}

	private List<Integer> getAllSlot() {
		List<Integer> list = new ArrayList<>();
		Slot current = headSlot;
		do {
			list.add(current.slotNumber);
			current = current.next;
		} while (current != null);
		Collections.sort(list);
		return list;
	}

	/**
	 * @param vehicle
	 *            detail for parking This method is responsible for park the
	 *            vehicle at position of given slotNo.
	 * @param slotNo
	 *            is where the vehicle will park. if slotNo is 1 then park the
	 *            vehicle at first slot in parking layout. else park at given
	 *            slot.
	 * 
	 * */
	public void parkAt(int slotNo, Vehicle vehicle) {
		if (slotNo == 1) {
			parkAtFirstSlot(vehicle);
			return;
		}
		Slot slot = new Slot(slotNo, vehicle, true, null, null);
		Slot ptr = headSlot;
		for (int i = 2; i <= occupideCapacity; i++) {
			if (i == slotNo) {
				Slot tmp = ptr.next;
				ptr.next = slot;
				slot.prev = ptr;
				slot.next = tmp;
				tmp.prev = slot;
			}
			ptr = ptr.next;
		}
		occupideCapacity++;
	}

	private void parkAtFirstSlot(Vehicle vehicle) {
		Slot slot = new Slot(1, vehicle, true, null, null);
		if (headSlot == null) {
			headSlot = slot;
			tailSlot = headSlot;
		} else {
			headSlot.prev = slot;
			slot.next = headSlot;
			headSlot = slot;
		}
		occupideCapacity++;
	}

	/**
	 * @param registrationNumber
	 * @return {@link Number} of occupied slot number with given registration
	 *         number.
	 * */
	public int findSlotNumberWithRegistrationNumber(String registrationNumber) {
		int slotNo = 0;
		if (occupideCapacity == 0) {
			return slotNo;
		}
		if (headSlot.next == null) {
			if (headSlot.vehicle.number.equals(registrationNumber)) {
				slotNo = headSlot.slotNumber;
			}
			return slotNo;
		}
		Slot current = headSlot;
		do {
			if (current.vehicle.number.equals(registrationNumber)) {
				slotNo = current.slotNumber;
			}
			current = current.next;
		} while (current != null);
		return slotNo;
	}

	/**
	 * @param vehicleColour
	 * @return {@link List} of occupied vehicle registration number with given
	 *         color.
	 * */
	public List<String> findRegistrationNoOfVehicleWithColour(
			String vehicleColour) {
		List<String> regNos = new ArrayList<>();
		if (occupideCapacity == 0) {
			return regNos;
		}
		if (headSlot.next == null) {
			if (headSlot.vehicle.colour.equals(vehicleColour)) {
				regNos.add(headSlot.vehicle.number);
			}
			return regNos;
		}
		Slot current = headSlot;
		do {
			if (current.vehicle.colour.equals(vehicleColour)) {
				regNos.add(current.vehicle.number);
			}
			current = current.next;
		} while (current != null);
		return regNos;
	}

	/**
	 * @param vehicleColour
	 * @return {@link List} of occupied slot with given color.
	 * */
	public List<Integer> findSlotNoOfVehicleWithColour(String vehicleColour) {
		List<Integer> regNos = new ArrayList<>();
		if (occupideCapacity == 0) {
			return regNos;
		}
		if (headSlot.next == null) {
			if (headSlot.vehicle.colour.equals(vehicleColour)) {
				regNos.add(headSlot.slotNumber);
			}
			return regNos;
		}
		Slot current = headSlot;
		do {
			if (current.vehicle.colour.equals(vehicleColour)) {
				regNos.add(current.slotNumber);
			}
			current = current.next;
		} while (current != null);
		return regNos;
	}

	public void display() {
		System.out.println("Slot No " + "\t" + "Registration No " + "\t"
				+ "Colour");
		System.out.println("----------------------------------------------------");
		if (occupideCapacity == 0) {
			System.out.println("empty");
			return;
		}
		if (headSlot.next == null) {
			System.out.println(headSlot.slotNumber + "\t\t"
					+ headSlot.vehicle.number + "\t\t" + headSlot.vehicle.colour
					);
			return;
		}
		Slot current = headSlot;
		do {
			System.out.println(current.slotNumber + "\t\t"
					+ current.vehicle.number + "\t\t" + current.vehicle.colour
					);
			current = current.next;
		} while (current != null);
	}

	public static ParkingLayout getParkingLayoutInstance(int capacity) {
		if (layoutInstance == null)
			synchronized (ParkingLayout.class) {
				if (layoutInstance == null)
					layoutInstance = new ParkingLayout(capacity);
			}
		return layoutInstance;
	}
}
