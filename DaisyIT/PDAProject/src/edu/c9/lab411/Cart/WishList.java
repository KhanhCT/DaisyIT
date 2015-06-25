package edu.c9.lab411.Cart;

public class WishList {
	public String id_product, name_product;
	public WishList() {
		// TODO Auto-generated constructor stub
	}
	public WishList(String id_product, String name_product) {
		// TODO Auto-generated constructor stub
		this.id_product = id_product;
		this.name_product = name_product;
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

}
