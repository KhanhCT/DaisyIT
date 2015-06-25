package edu.c9.lab411.partner;

public class PartnerInfor {
	private int partnerID;
	private String  fullName, shortName;
	public PartnerInfor() {
		// TODO Auto-generated constructor stub
	}
	
	public PartnerInfor(int partnerID, String fullName, String shortName) {
		super();
		this.partnerID = partnerID;
		this.fullName = fullName;
		this.shortName = shortName;
	}

	public int getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
