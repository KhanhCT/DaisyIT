package edu.c9.lab411.goods;

public class SKUItems {
	private String skuCode, productCode, productName, saleBlock, oplLimit,
			odrRight;

	private int goodID, rank;
	private double purcPrice;
	private boolean status;

	public SKUItems() {
		// TODO Auto-generated constructor stub
	}

	public SKUItems(String skuCode, String productCode, String productName,
			String saleBlock, String oplLimit, String odrRight, int goodID, int rank,
			double purcPrice, boolean status) {
		super();
		this.skuCode = skuCode;
		this.productCode = productCode;
		this.productName = productName;
		this.saleBlock = saleBlock;
		this.oplLimit = oplLimit;
		this.odrRight = odrRight;
		this.goodID = goodID;
		this.rank = rank;
		this.purcPrice = purcPrice;
		this.status = status;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSaleBlock() {
		return saleBlock;
	}

	public void setSaleBlock(String saleBlock) {
		this.saleBlock = saleBlock;
	}

	public String getOplLimit() {
		return oplLimit;
	}

	public void setOplLimit(String oplLimit) {
		this.oplLimit = oplLimit;
	}

	public String getOdrRight() {
		return odrRight;
	}

	public void setOdrRight(String odrRight) {
		this.odrRight = odrRight;
	}

	public int getGoodID() {
		return goodID;
	}

	public void setGoodID(int goodID) {
		this.goodID = goodID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public double getPurcPrice() {
		return purcPrice;
	}

	public void setPurcPrice(double purcPrice) {
		this.purcPrice = purcPrice;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
