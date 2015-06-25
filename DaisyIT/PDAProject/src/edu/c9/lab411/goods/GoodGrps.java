package edu.c9.lab411.goods;

public class GoodGrps {
	private int ggrpID,gsDivID, gsClassID, gsGrpID, gsAccClsID, partnerID, grpLevel, taxID;
	private int gProID, gAGrpID;
	private char ggrpType, goodClsDes;
	private String image;
	private boolean lastLevel,status;
	
	public GoodGrps() {
		// TODO Auto-generated constructor stub
	}
	public GoodGrps(int ggrpID, int gsDivID, int gsClassID, int gsGrpID,
			int gsAccClsID, int partnerID, int grpLevel, int taxID, int gProID,
			int gAGrpID, char ggrpType, char goodClsDes, String image,
			boolean lastLevel, boolean status) {
		super();
		this.ggrpID = ggrpID;
		this.gsDivID = gsDivID;
		this.gsClassID = gsClassID;
		this.gsGrpID = gsGrpID;
		this.gsAccClsID = gsAccClsID;
		this.partnerID = partnerID;
		this.grpLevel = grpLevel;
		this.taxID = taxID;
		this.gProID = gProID;
		this.gAGrpID = gAGrpID;
		this.ggrpType = ggrpType;
		this.goodClsDes = goodClsDes;
		this.image = image;
		this.lastLevel = lastLevel;
		this.status = status;
	}
	public int getGgrpID() {
		return ggrpID;
	}
	public void setGgrpID(int ggrpID) {
		this.ggrpID = ggrpID;
	}
	public int getGsDivID() {
		return gsDivID;
	}
	public void setGsDivID(int gsDivID) {
		this.gsDivID = gsDivID;
	}
	public int getGsClassID() {
		return gsClassID;
	}
	public void setGsClassID(int gsClassID) {
		this.gsClassID = gsClassID;
	}
	public int getGsGrpID() {
		return gsGrpID;
	}
	public void setGsGrpID(int gsGrpID) {
		this.gsGrpID = gsGrpID;
	}
	public int getGsAccClsID() {
		return gsAccClsID;
	}
	public void setGsAccClsID(int gsAccClsID) {
		this.gsAccClsID = gsAccClsID;
	}
	public int getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	public int getGrpLevel() {
		return grpLevel;
	}
	public void setGrpLevel(int grpLevel) {
		this.grpLevel = grpLevel;
	}
	public int getTaxID() {
		return taxID;
	}
	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}
	public int getgProID() {
		return gProID;
	}
	public void setgProID(int gProID) {
		this.gProID = gProID;
	}
	public int getgAGrpID() {
		return gAGrpID;
	}
	public void setgAGrpID(int gAGrpID) {
		this.gAGrpID = gAGrpID;
	}
	public char getGgrpType() {
		return ggrpType;
	}
	public void setGgrpType(char ggrpType) {
		this.ggrpType = ggrpType;
	}
	public char getGoodClsDes() {
		return goodClsDes;
	}
	public void setGoodClsDes(char goodClsDes) {
		this.goodClsDes = goodClsDes;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isLastLevel() {
		return lastLevel;
	}
	public void setLastLevel(boolean lastLevel) {
		this.lastLevel = lastLevel;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
