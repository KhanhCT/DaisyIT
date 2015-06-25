package edu.sfsu.cs.orange.goodssearch;

public class Goods {
	String id_goods, name_goods;
	public Goods(){
		
	}
  public Goods(String id_goods, String name_goods){
		this.id_goods = id_goods;
		this.name_goods = name_goods;
 }

	public String getId_good() {
		return id_goods;
	}

	public void setId_good(String id_goods) {
		this.id_goods = id_goods;
	}

	public String getName_good() {
		return name_goods;
	}

	public void setName_goods(String name_goods) {
		this.name_goods = name_goods;
	}

	

}
