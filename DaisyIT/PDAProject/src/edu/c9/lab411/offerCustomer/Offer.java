package edu.c9.lab411.offerCustomer;

public class Offer {

	private String skuCode, productName, name, address;
	private int quantity, phone;
	public Offer() {
		// TODO Auto-generated constructor stub
	}
	public Offer(String skuCode, String productName, String name,
			String address, int quantity, int phone) {
		super();
		this.skuCode = skuCode;
		this.productName = productName;
		this.name = name;
		this.address = address;
		this.quantity = quantity;
		this.phone = phone;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}


}
