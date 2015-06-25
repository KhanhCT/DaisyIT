package edu.c9.lab411.partner;

public class PartnerLocation {
	private String PLoca_Code, PLoca_Type,PLoca_Name, PLoca_Address;
	private int PLoca_ID, Partner_ID, Place_ID;
	private double  Longitude, Latitude;
	private boolean Status;
	public PartnerLocation() {
		// TODO Auto-generated constructor stub
	}
	public PartnerLocation(int PLoca_ID,
			String PLoca_Code,int  Partner_ID, String PLoca_Type,
			String PLoca_Name, String PLoca_Address, int Place_ID,
			double Longitude, double Latitude, boolean Status){
		this.PLoca_ID = PLoca_ID;
		this.PLoca_Code = PLoca_Code;
		this.Partner_ID = Partner_ID;
		this.PLoca_Type = PLoca_Type;
		this.PLoca_Name = PLoca_Name;
		this.PLoca_Address = PLoca_Address;
		this.Place_ID = Place_ID;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.Status = Status;
		
	}
	public String getPLoca_Code() {
		return PLoca_Code;
	}
	public void setPLoca_Code(String pLoca_Code) {
		PLoca_Code = pLoca_Code;
	}
	public String getPLoca_Type() {
		return PLoca_Type;
	}
	public void setPLoca_Type(String pLoca_Type) {
		PLoca_Type = pLoca_Type;
	}
	public String getPLoca_Name() {
		return PLoca_Name;
	}
	public void setPLoca_Name(String pLoca_Name) {
		PLoca_Name = pLoca_Name;
	}
	public String getPLoca_Address() {
		return PLoca_Address;
	}
	public void setPLoca_Address(String pLoca_Address) {
		PLoca_Address = pLoca_Address;
	}
	public int getPLoca_ID() {
		return PLoca_ID;
	}
	public void setPLoca_ID(int pLoca_ID) {
		PLoca_ID = pLoca_ID;
	}
	public int getPartner_ID() {
		return Partner_ID;
	}
	public void setPartner_ID(int partner_ID) {
		Partner_ID = partner_ID;
	}
	public int getPlace_ID() {
		return Place_ID;
	}
	public void setPlace_ID(int place_ID) {
		Place_ID = place_ID;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	

}
