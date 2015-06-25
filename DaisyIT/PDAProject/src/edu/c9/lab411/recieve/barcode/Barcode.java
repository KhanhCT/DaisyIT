package edu.c9.lab411.recieve.barcode;
public class Barcode {
   public String barcode, skuCode;
   int goodID, unitIdx;
   boolean status;
	public Barcode() {
		// TODO Auto-generated constructor stub
	}
	public Barcode(String barcode, String skuCode, int goodID, int unitIdx, boolean status){		
		this.barcode = barcode;
		this.skuCode = skuCode;
		this.goodID =goodID;
		this.unitIdx =  unitIdx;
		this.status = status;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public int getGoodID() {
		return goodID;
	}
	public void setGoodID(int goodID) {
		this.goodID = goodID;
	}
	public int getUnitIdx() {
		return unitIdx;
	}
	public void setUnitIdx(int unitIdx) {
		this.unitIdx = unitIdx;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	

}
