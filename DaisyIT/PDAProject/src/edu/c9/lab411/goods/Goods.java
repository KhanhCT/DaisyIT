package edu.c9.lab411.goods;

public class Goods {
	private int goodID, gsDivID, gsClassID, gsGrpID, gsAccClsID, famailyID,
			gPropID, gAGrpID, baseUnit;

	private String goodCode, shortName, fullName;
	private String gsImgIcon, gsImg01, gsImg02, gsImg03, gsImg04, gsImg05,
			gsImg06, gsDescription;
	private boolean status;

	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public Goods(int goodID, int gsDivID, int gsClassID, int gsGrpID,
			int gsAccClsID, int famailyID, int gPropID, int gAGrpID,
			int baseUnit, String goodCode, String shortName, String fullName,
			String gsImgIcon, String gsImg01, String gsImg02, String gsImg03,
			String gsImg04, String gsImg05, String gsImg06,
			String gsDescription, boolean status) {
		super();
		this.goodID = goodID;
		this.gsDivID = gsDivID;
		this.gsClassID = gsClassID;
		this.gsGrpID = gsGrpID;
		this.gsAccClsID = gsAccClsID;
		this.famailyID = famailyID;
		this.gPropID = gPropID;
		this.gAGrpID = gAGrpID;
		this.baseUnit = baseUnit;
		this.goodCode = goodCode;
		this.shortName = shortName;
		this.fullName = fullName;
		this.gsImgIcon = gsImgIcon;
		this.gsImg01 = gsImg01;
		this.gsImg02 = gsImg02;
		this.gsImg03 = gsImg03;
		this.gsImg04 = gsImg04;
		this.gsImg05 = gsImg05;
		this.gsImg06 = gsImg06;
		this.gsDescription = gsDescription;
		this.status = status;
	}

	public int getGoodID() {
		return goodID;
	}

	public void setGoodID(int goodID) {
		this.goodID = goodID;
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

	public int getFamailyID() {
		return famailyID;
	}

	public void setFamailyID(int famailyID) {
		this.famailyID = famailyID;
	}

	public int getgPropID() {
		return gPropID;
	}

	public void setgPropID(int gPropID) {
		this.gPropID = gPropID;
	}

	public int getgAGrpID() {
		return gAGrpID;
	}

	public void setgAGrpID(int gAGrpID) {
		this.gAGrpID = gAGrpID;
	}

	public int getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(int baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getGoodCode() {
		return goodCode;
	}

	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGsImgIcon() {
		return gsImgIcon;
	}

	public void setGsImgIcon(String gsImgIcon) {
		this.gsImgIcon = gsImgIcon;
	}

	public String getGsImg01() {
		return gsImg01;
	}

	public void setGsImg01(String gsImg01) {
		this.gsImg01 = gsImg01;
	}

	public String getGsImg02() {
		return gsImg02;
	}

	public void setGsImg02(String gsImg02) {
		this.gsImg02 = gsImg02;
	}

	public String getGsImg03() {
		return gsImg03;
	}

	public void setGsImg03(String gsImg03) {
		this.gsImg03 = gsImg03;
	}

	public String getGsImg04() {
		return gsImg04;
	}

	public void setGsImg04(String gsImg04) {
		this.gsImg04 = gsImg04;
	}

	public String getGsImg05() {
		return gsImg05;
	}

	public void setGsImg05(String gsImg05) {
		this.gsImg05 = gsImg05;
	}

	public String getGsImg06() {
		return gsImg06;
	}

	public void setGsImg06(String gsImg06) {
		this.gsImg06 = gsImg06;
	}

	public String getGsDescription() {
		return gsDescription;
	}

	public void setGsDescription(String gsDescription) {
		this.gsDescription = gsDescription;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
