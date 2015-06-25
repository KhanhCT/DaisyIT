package edu.sfsu.cs.orange.goodssearch;

public class WareHouse {
 String id_ware, name_ware;
	public WareHouse() {
		
	}
	public WareHouse(String id_ware, String name_ware){
		super();
		this.id_ware = id_ware;
		this.name_ware = name_ware;
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

}
