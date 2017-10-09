package com.brijesh1100.parking.system;

public class Slot implements Comparable<Slot> {

	protected int slotNumber = 0;

	/**
	 * {@value isSlotFree=true} Initially slot is free.
	 * */
	protected boolean isSlotFree = true;
	protected Slot next;
	protected Slot prev;
	protected Vehicle vehicle;

	public Slot(Integer slotNo, Vehicle vehicle, boolean isSlotFree, Slot next,
			Slot prev) {
		this.slotNumber = slotNo;
		this.isSlotFree = isSlotFree;
		this.vehicle = vehicle;
		this.next = next;
		this.prev = prev;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public int compareTo(Slot slot) {
		if (this.slotNumber > slot.slotNumber) {
			return 1;
		} else if (this.slotNumber < slot.slotNumber) {
			return -1;
		}
		return 0;
	}
}
