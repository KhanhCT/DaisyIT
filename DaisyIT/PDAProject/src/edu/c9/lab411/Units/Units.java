package edu.c9.lab411.Units;

public class Units {
	private int unitID;
	private char unitSymb, unitName;
	private boolean status;

	public Units() {
		// TODO Auto-generated constructor stub
	}

	public Units(int unitID, char unitSymb, char unitName, boolean status) {
		super();
		this.unitID = unitID;
		this.unitSymb = unitSymb;
		this.unitName = unitName;
		this.status = status;
	}

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public char getUnitSymb() {
		return unitSymb;
	}

	public void setUnitSymb(char unitSymb) {
		this.unitSymb = unitSymb;
	}

	public char getUnitName() {
		return unitName;
	}

	public void setUnitName(char unitName) {
		this.unitName = unitName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
