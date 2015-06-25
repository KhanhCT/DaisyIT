package edu.c9.lab411.partner;

public class Partner {
    private int partnerID, partCtgID, placeID, countryID;
    private String partnerCode, deptCode, partnerType, fullName, shortName, billAddress, businessNo;
    private String  taxNo;
    boolean status;
	public Partner(){
	}
	public Partner(int partnerID, int partCtgID, int placeID, int countryID,
			String partnerCode, String deptCode, String partnerType,
			String fullName, String shortName, String billAddress,
			String businessNo, String taxNo, boolean status) {
		super();
		this.partnerID = partnerID;
		this.partCtgID = partCtgID;
		this.placeID = placeID;
		this.countryID = countryID;
		this.partnerCode = partnerCode;
		this.deptCode = deptCode;
		this.partnerType = partnerType;
		this.fullName = fullName;
		this.shortName = shortName;
		this.billAddress = billAddress;
		this.businessNo = businessNo;
		this.taxNo = taxNo;
		this.status = status;
	}
	public int getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	public int getPartCtgID() {
		return partCtgID;
	}
	public void setPartCtgID(int partCtgID) {
		this.partCtgID = partCtgID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
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
	public String getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
