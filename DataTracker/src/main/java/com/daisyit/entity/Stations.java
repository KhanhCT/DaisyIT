package com.daisyit.entity;
// Generated Mar 7, 2018 6:20:40 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stations generated by hbm2java
 */
@Entity
@Table(name="STATIONS"
    ,catalog="NSRP_CATER"
)
public class Stations  implements java.io.Serializable {


     private short id;
     private String type;
     private String nodeId;
     private String posAlias;
     private String wsAlias;
     private String name;
     private String fullname;
     private String tempdir;
     private byte disable;
     private short badlogin;
     private byte login;
     private byte prnport;
     private byte prnname;
     private String posprn;
     private byte scnport;
     private String comset;
     private byte csdport;
     private byte cdrport;
     private byte sclport;
     private byte pinport;
     private String docprn;
     private String barprn;
     private String posId;
     private short userId;
     private String menustat;
     private byte chk;
     private byte status;
     private int os;
     private String srn;

    public Stations() {
    }

    public Stations(short id, String type, String nodeId, String posAlias, String wsAlias, String name, String fullname, String tempdir, byte disable, short badlogin, byte login, byte prnport, byte prnname, String posprn, byte scnport, String comset, byte csdport, byte cdrport, byte sclport, byte pinport, String docprn, String barprn, String posId, short userId, String menustat, byte chk, byte status, int os, String srn) {
       this.id = id;
       this.type = type;
       this.nodeId = nodeId;
       this.posAlias = posAlias;
       this.wsAlias = wsAlias;
       this.name = name;
       this.fullname = fullname;
       this.tempdir = tempdir;
       this.disable = disable;
       this.badlogin = badlogin;
       this.login = login;
       this.prnport = prnport;
       this.prnname = prnname;
       this.posprn = posprn;
       this.scnport = scnport;
       this.comset = comset;
       this.csdport = csdport;
       this.cdrport = cdrport;
       this.sclport = sclport;
       this.pinport = pinport;
       this.docprn = docprn;
       this.barprn = barprn;
       this.posId = posId;
       this.userId = userId;
       this.menustat = menustat;
       this.chk = chk;
       this.status = status;
       this.os = os;
       this.srn = srn;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false, precision=3, scale=0)
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }

    
    @Column(name="TYPE", nullable=false, length=2)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="NODE_ID", nullable=false, length=3)
    public String getNodeId() {
        return this.nodeId;
    }
    
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    
    @Column(name="POS_ALIAS", nullable=false, length=2)
    public String getPosAlias() {
        return this.posAlias;
    }
    
    public void setPosAlias(String posAlias) {
        this.posAlias = posAlias;
    }

    
    @Column(name="WS_ALIAS", nullable=false, length=30)
    public String getWsAlias() {
        return this.wsAlias;
    }
    
    public void setWsAlias(String wsAlias) {
        this.wsAlias = wsAlias;
    }

    
    @Column(name="NAME", nullable=false, length=30)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="FULLNAME", nullable=false, length=30)
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    
    @Column(name="TEMPDIR", nullable=false, length=30)
    public String getTempdir() {
        return this.tempdir;
    }
    
    public void setTempdir(String tempdir) {
        this.tempdir = tempdir;
    }

    
    @Column(name="DISABLE", nullable=false)
    public byte getDisable() {
        return this.disable;
    }
    
    public void setDisable(byte disable) {
        this.disable = disable;
    }

    
    @Column(name="BADLOGIN", nullable=false, precision=3, scale=0)
    public short getBadlogin() {
        return this.badlogin;
    }
    
    public void setBadlogin(short badlogin) {
        this.badlogin = badlogin;
    }

    
    @Column(name="LOGIN", nullable=false)
    public byte getLogin() {
        return this.login;
    }
    
    public void setLogin(byte login) {
        this.login = login;
    }

    
    @Column(name="PRNPORT", nullable=false, precision=2, scale=0)
    public byte getPrnport() {
        return this.prnport;
    }
    
    public void setPrnport(byte prnport) {
        this.prnport = prnport;
    }

    
    @Column(name="PRNNAME", nullable=false, precision=2, scale=0)
    public byte getPrnname() {
        return this.prnname;
    }
    
    public void setPrnname(byte prnname) {
        this.prnname = prnname;
    }

    
    @Column(name="POSPRN", nullable=false, length=50)
    public String getPosprn() {
        return this.posprn;
    }
    
    public void setPosprn(String posprn) {
        this.posprn = posprn;
    }

    
    @Column(name="SCNPORT", nullable=false, precision=2, scale=0)
    public byte getScnport() {
        return this.scnport;
    }
    
    public void setScnport(byte scnport) {
        this.scnport = scnport;
    }

    
    @Column(name="COMSET", nullable=false, length=12)
    public String getComset() {
        return this.comset;
    }
    
    public void setComset(String comset) {
        this.comset = comset;
    }

    
    @Column(name="CSDPORT", nullable=false, precision=2, scale=0)
    public byte getCsdport() {
        return this.csdport;
    }
    
    public void setCsdport(byte csdport) {
        this.csdport = csdport;
    }

    
    @Column(name="CDRPORT", nullable=false, precision=2, scale=0)
    public byte getCdrport() {
        return this.cdrport;
    }
    
    public void setCdrport(byte cdrport) {
        this.cdrport = cdrport;
    }

    
    @Column(name="SCLPORT", nullable=false, precision=2, scale=0)
    public byte getSclport() {
        return this.sclport;
    }
    
    public void setSclport(byte sclport) {
        this.sclport = sclport;
    }

    
    @Column(name="PINPORT", nullable=false, precision=2, scale=0)
    public byte getPinport() {
        return this.pinport;
    }
    
    public void setPinport(byte pinport) {
        this.pinport = pinport;
    }

    
    @Column(name="DOCPRN", nullable=false, length=50)
    public String getDocprn() {
        return this.docprn;
    }
    
    public void setDocprn(String docprn) {
        this.docprn = docprn;
    }

    
    @Column(name="BARPRN", nullable=false, length=50)
    public String getBarprn() {
        return this.barprn;
    }
    
    public void setBarprn(String barprn) {
        this.barprn = barprn;
    }

    
    @Column(name="POS_ID", nullable=false, length=12)
    public String getPosId() {
        return this.posId;
    }
    
    public void setPosId(String posId) {
        this.posId = posId;
    }

    
    @Column(name="USER_ID", nullable=false, precision=3, scale=0)
    public short getUserId() {
        return this.userId;
    }
    
    public void setUserId(short userId) {
        this.userId = userId;
    }

    
    @Column(name="MENUSTAT", nullable=false, length=128)
    public String getMenustat() {
        return this.menustat;
    }
    
    public void setMenustat(String menustat) {
        this.menustat = menustat;
    }

    
    @Column(name="CHK", nullable=false)
    public byte getChk() {
        return this.chk;
    }
    
    public void setChk(byte chk) {
        this.chk = chk;
    }

    
    @Column(name="STATUS", nullable=false)
    public byte getStatus() {
        return this.status;
    }
    
    public void setStatus(byte status) {
        this.status = status;
    }

    
    @Column(name="OS", nullable=false)
    public int getOs() {
        return this.os;
    }
    
    public void setOs(int os) {
        this.os = os;
    }

    
    @Column(name="SRN", nullable=false, length=100)
    public String getSrn() {
        return this.srn;
    }
    
    public void setSrn(String srn) {
        this.srn = srn;
    }




}


