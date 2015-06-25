package edu.sfsu.cs.orange.goodssearch;

public class Product {
    int  id;
	String id_ware, name_ware, id_group, name_group, id_mer, name_mer, icon;
	int sold, reserver, stock_tranfer, liq;
	public Product(int id, String id_ware, String name_ware, String id_group, String name_group, String id_mer,String name_mer,
			int sold, int reserver, int stock_tranfer, int liq, String icon) {
		this.id = id;
		this.id_ware = id_ware;
		this.name_ware = name_ware;
		this.id_group = id_group;
		this.name_group = name_group;
		this.id_mer = id_mer;
		this.name_mer = name_mer;
		this.sold = sold;
		this.reserver = reserver;
		this.stock_tranfer = stock_tranfer;
		this.liq = liq;	
		this.icon = icon;
	}
	public Product(){
		
	}
	/**
	 * this function create product contain truong in sqlite database 
	 * @param id_ware
	 * @param name_ware
	 * @param id_group
	 * @param name_group
	 * @param id_mer
	 * @param name_mer
	 * @param sold
	 * @param reserver
	 * @param stock_tranfer
	 * @param liq
	 * @param icon
	 */
	public Product(String id_ware, String name_ware, String id_group, String name_group, String id_mer,String name_mer,
			int sold, int reserver, int stock_tranfer, int liq, String icon){
		this.id_ware = id_ware;
		this.name_ware = name_ware;
		this.id_group = id_group;
		this.name_group = name_group;
		this.id_mer = id_mer;
		this.name_mer = name_mer;
		this.sold = sold;
		this.reserver = reserver;
		this.stock_tranfer = stock_tranfer;
		this.liq = liq;	
		this.icon = icon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getId_ware() {
		return id_ware;
	}
	public void setId_ware(String id_ware) {
		this.id_ware = id_ware;
	}
	public String getName_ware() {
		return name_ware;
	}
	public void setName_ware(String name_ware) {
		this.name_ware = name_ware;
	}
	public String getId_group() {
		return id_group;
	}
	public void setId_group(String id_group) {
		this.id_group = id_group;
	}
	public String getName_group() {
		return name_group;
	}
	public void setName_group(String name_group) {
		this.name_group = name_group;
	}
	public String getId_mer() {
		return id_mer;
	}
	public void setId_mer(String id_mer) {
		this.id_mer = id_mer;
	}
	public String getName_mer() {
		return name_mer;
	}
	public void setName_mer(String name_mer) {
		this.name_mer = name_mer;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getReserver() {
		return reserver;
	}
	public void setReserver(int reserver) {
		this.reserver = reserver;
	}
	public int getStock_tranfer() {
		return stock_tranfer;
	}
	public void setStock_tranfer(int stock_tranfer) {
		this.stock_tranfer = stock_tranfer;
	}
	public int getLiq() {
		return liq;
	}
	public void setLiq(int liq) {
		this.liq = liq;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}	
	
}
