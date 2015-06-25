package edu.sfsu.cs.orange.goodssearch;

public class WareGoods {

	int sold, reserver, stock_tranfer, liq;
	String  icon, create_at, update_at;
	Groups groups;
	WareHouse ware_house;
	Goods  goods;
	
	public WareGoods(Goods goods, int sold, int reserver, int stock_tranfer, int liq, String icon, String create_at,String updated_at,WareHouse ware_house, Groups group) {
		// TODO Auto-generated constructor stub
		super();
		this.goods = goods;
		this.sold = sold;
		this.reserver = reserver;
		this.stock_tranfer = stock_tranfer;
		this.liq = liq;
		this.icon = icon;
		this.create_at = create_at;
		this.update_at = updated_at;
		this.groups = group;
		this.ware_house = ware_house;
	}
	public WareGoods() {
		// TODO Auto-generated constructor stub
	}
	public WareGoods( Groups group) {
		// TODO Auto-generated constructor stub
		super();
		this.groups = group;
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
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public WareHouse getWare_house() {
		return ware_house;
	}
	public void setWare_house(WareHouse ware_house) {
		this.ware_house = ware_house;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	

}
