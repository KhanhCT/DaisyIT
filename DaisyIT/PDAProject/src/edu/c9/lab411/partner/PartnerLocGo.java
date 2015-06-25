package edu.c9.lab411.partner;

public class PartnerLocGo {

	private  int partnerID, ploco_id, place_id;
	public PartnerLocGo() {
		// TODO Auto-generated constructor stub
	}
	public PartnerLocGo(int partnerID, int ploco_id, int place_id) {
		super();
		this.partnerID = partnerID;
		this.ploco_id = ploco_id;
		this.place_id = place_id;
	}
	public int getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	public int getPloco_id() {
		return ploco_id;
	}
	public void setPloco_id(int ploco_id) {
		this.ploco_id = ploco_id;
	}
	public int getPlace_id() {
		return place_id;
	}
	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}

}
