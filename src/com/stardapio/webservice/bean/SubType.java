package com.stardapio.webservice.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubType extends Type {
	private int idSubType;
	
	public int getidSubType() {
		return idSubType;
	}

	public void setidSubType(int idSubType) {
		this.idSubType = idSubType;
	}
}
