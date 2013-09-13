package com.stardapio.webservice.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Menu {
	private List<Item> itens;

	public Menu() {
		itens = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		itens.add(item);
	}

	public List<Item> getItens() {
		return itens;
	}

}
