package edu.c9.lab411.Units;

public class MercUnit {
	private char skuCode, packUnit, unitDesc;
	private int goodId, unitIdx, unitConv;
	private boolean rtUnit, whUnit, pcUnit, status;

	public MercUnit() {
		// TODO Auto-generated constructor stub
	}

	public MercUnit(char skuCode, char packUnit, char unitDesc, int goodId,
			int unitIdx, int unitConv, boolean rtUnit, boolean whUnit,
			boolean pcUnit, boolean status) {
		super();
		this.skuCode = skuCode;
		this.packUnit = packUnit;
		this.unitDesc = unitDesc;
		this.goodId = goodId;
		this.unitIdx = unitIdx;
		this.unitConv = unitConv;
		this.rtUnit = rtUnit;
		this.whUnit = whUnit;
		this.pcUnit = pcUnit;
		this.status = status;
	}

	public char getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(char skuCode) {
		this.skuCode = skuCode;
	}

	public char getPackUnit() {
		return packUnit;
	}

	public void setPackUnit(char packUnit) {
		this.packUnit = packUnit;
	}

	public char getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(char unitDesc) {
		this.unitDesc = unitDesc;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public int getUnitIdx() {
		return unitIdx;
	}

	public void setUnitIdx(int unitIdx) {
		this.unitIdx = unitIdx;
	}

	public int getUnitConv() {
		return unitConv;
	}

	public void setUnitConv(int unitConv) {
		this.unitConv = unitConv;
	}

	public boolean isRtUnit() {
		return rtUnit;
	}

	public void setRtUnit(boolean rtUnit) {
		this.rtUnit = rtUnit;
	}

	public boolean isWhUnit() {
		return whUnit;
	}

	public void setWhUnit(boolean whUnit) {
		this.whUnit = whUnit;
	}

	public boolean isPcUnit() {
		return pcUnit;
	}

	public void setPcUnit(boolean pcUnit) {
		this.pcUnit = pcUnit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
