package ch.csbe.yatzy.model;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
