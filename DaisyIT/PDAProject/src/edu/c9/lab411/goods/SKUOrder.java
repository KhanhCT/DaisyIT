package edu.c9.lab411.goods;

public class SKUOrder {
	String skuCode, productName;
	int quantity;
	public SKUOrder() {
		
	}
	public SKUOrder(String skuCode, String productName, int quantity) {
		super();
		this.skuCode = skuCode;
		this.productName = productName;
		this.quantity = quantity;
	}

	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
