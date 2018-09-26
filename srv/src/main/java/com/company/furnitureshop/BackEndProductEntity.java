package com.company.furnitureshop;

import com.sap.cloud.sdk.result.ElementName;
import com.sap.cloud.sdk.service.prov.api.annotations.Key;


public class BackEndProductEntity {
	@ElementName("ProductID")
	@Key
	private String ProductID;

	@ElementName("SUPPLIERID")
	private String SUPPLIERID;

	@ElementName("SUPPLIERNAME")
	private String SUPPLIERNAME;

	@ElementName("PRICE")
	private Float PRICE;

	@ElementName("STOCK")
	private String STOCK;

	@ElementName("DELIVERYDATE")
	private String DELIVERYDATE;

	@ElementName("DISCOUNT")
	private String DISCOUNT;


	public String getPRODUCTID() {
		return ProductID;
	}
	public void setPRODUCTID(String PRODUCTID) {
		this.ProductID = PRODUCTID;
	}

	public String getSUPPLIERID() {
		return SUPPLIERID;
	}
	public void setSUPPLIERID(String SUPPLIERID) {
		this.SUPPLIERID = SUPPLIERID;
	}

	public String getSUPPLIERNAME() {
		return SUPPLIERNAME;
	}
	public void setSUPPLIERNAME(String SUPPLIERNAME) {
		this.SUPPLIERNAME = SUPPLIERNAME;
	}

	public String getSTOCK() {
		return STOCK;
	}
	public void setSTOCK(String STOCK) {
		this.STOCK = STOCK;
	}

	public String getDELIVERYDATE() {
		return DELIVERYDATE;
	}
	public void setDELIVERYDATE(String DELIVERYDATE) {
		this.DELIVERYDATE = DELIVERYDATE;
	}

	public String getDISCOUNT() {
		return DISCOUNT;
	}
	public void setDISCOUNT(String DISCOUNT) {
		this.DISCOUNT = DISCOUNT;
	}
}