package edu.c9.lab411.stock;

import edu.c9.lab411.goods.GoodGrps;

public class Stock {
	private int stkID, nodeID, rItemID, placeID, dimension;
	private char stkCode, stkType, ownerType, ownerID, stkSymb, stkCap, stkName, stkAddr, ys, accountID;
	private char accCys, phone, fax, email, conPersion, location;
	private boolean isPhysical, isDummy,isBase, isPos, ispod,status;
	private String datetime;
	double longitude, latitude;
	GoodGrps a = new GoodGrps();
	

	public Stock() {
		// TODO Auto-generated constructor stub
	
	}
	public Stock(int stkID, int nodeID, int rItemID, int placeID,
			int dimension, char stkCode, char stkType, char ownerType,
			char ownerID, char stkSymb, char stkCap, char stkName,
			char stkAddr, char ys, char accountID, char accCys, char phone,
			char fax, char email, char conPersion, char location,
			boolean isPhysical, boolean isDummy, boolean isBase, boolean isPos,
			boolean ispod, boolean status, String datetime, double longitude,
			double latitude) {
		super();
		this.stkID = stkID;
		this.nodeID = nodeID;
		this.rItemID = rItemID;
		this.placeID = placeID;
		this.dimension = dimension;
		this.stkCode = stkCode;
		this.stkType = stkType;
		this.ownerType = ownerType;
		this.ownerID = ownerID;
		this.stkSymb = stkSymb;
		this.stkCap = stkCap;
		this.stkName = stkName;
		this.stkAddr = stkAddr;
		this.ys = ys;
		this.accountID = accountID;
		this.accCys = accCys;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.conPersion = conPersion;
		this.location = location;
		this.isPhysical = isPhysical;
		this.isDummy = isDummy;
		this.isBase = isBase;
		this.isPos = isPos;
		this.ispod = ispod;
		this.status = status;
		this.datetime = datetime;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public int getStkID() {
		return stkID;
	}
	public void setStkID(int stkID) {
		this.stkID = stkID;
	}
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public int getrItemID() {
		return rItemID;
	}
	public void setrItemID(int rItemID) {
		this.rItemID = rItemID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public char getStkCode() {
		return stkCode;
	}
	public void setStkCode(char stkCode) {
		this.stkCode = stkCode;
	}
	public char getStkType() {
		return stkType;
	}
	public void setStkType(char stkType) {
		this.stkType = stkType;
	}
	public char getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(char ownerType) {
		this.ownerType = ownerType;
	}
	public char getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(char ownerID) {
		this.ownerID = ownerID;
	}
	public char getStkSymb() {
		return stkSymb;
	}
	public void setStkSymb(char stkSymb) {
		this.stkSymb = stkSymb;
	}
	public char getStkCap() {
		return stkCap;
	}
	public void setStkCap(char stkCap) {
		this.stkCap = stkCap;
	}
	public char getStkName() {
		return stkName;
	}
	public void setStkName(char stkName) {
		this.stkName = stkName;
	}
	public char getStkAddr() {
		return stkAddr;
	}
	public void setStkAddr(char stkAddr) {
		this.stkAddr = stkAddr;
	}
	public char getYs() {
		return ys;
	}
	public void setYs(char ys) {
		this.ys = ys;
	}
	public char getAccountID() {
		return accountID;
	}
	public void setAccountID(char accountID) {
		this.accountID = accountID;
	}
	public char getAccCys() {
		return accCys;
	}
	public void setAccCys(char accCys) {
		this.accCys = accCys;
	}
	public char getPhone() {
		return phone;
	}
	public void setPhone(char phone) {
		this.phone = phone;
	}
	public char getFax() {
		return fax;
	}
	public void setFax(char fax) {
		this.fax = fax;
	}
	public char getEmail() {
		return email;
	}
	public void setEmail(char email) {
		this.email = email;
	}
	public char getConPersion() {
		return conPersion;
	}
	public void setConPersion(char conPersion) {
		this.conPersion = conPersion;
	}
	public char getLocation() {
		return location;
	}
	public void setLocation(char location) {
		this.location = location;
	}
	public boolean isPhysical() {
		return isPhysical;
	}
	public void setPhysical(boolean isPhysical) {
		this.isPhysical = isPhysical;
	}
	public boolean isDummy() {
		return isDummy;
	}
	public void setDummy(boolean isDummy) {
		this.isDummy = isDummy;
	}
	public boolean isBase() {
		return isBase;
	}
	public void setBase(boolean isBase) {
		this.isBase = isBase;
	}
	public boolean isPos() {
		return isPos;
	}
	public void setPos(boolean isPos) {
		this.isPos = isPos;
	}
	public boolean isIspod() {
		return ispod;
	}
	public void setIspod(boolean ispod) {
		this.ispod = ispod;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
