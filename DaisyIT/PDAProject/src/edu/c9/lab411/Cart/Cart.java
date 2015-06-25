package edu.c9.lab411.Cart;

public class Cart {

	private String id_product, name_product, id_ware;
	private int quantity;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public Cart(String id_product, String name_product, String id_ware, int quantity){
		this.id_product = id_product;
		this.name_product = name_product;
		this.id_ware = id_ware;
		this.quantity = quantity;
	}
	public String getId_product() {
		return id_product;
	}
	public void setId_product(String id_product) {
		this.id_product = id_product;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getId_ware() {
		return id_ware;
	}
	public void setId_ware(String id_ware) {
		this.id_ware = id_ware;
	}
	

}
