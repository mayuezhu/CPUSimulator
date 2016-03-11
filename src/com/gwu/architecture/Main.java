package com.gwu.architecture;

public class Main {

	GUI gwuGui;
	Computer gwuComputer;

	public Main() {
		gwuComputer = new Computer();
		gwuGui = new GUI();
	}

	public void init() {
		gwuComputer.init(gwuGui);
		gwuGui.init(gwuComputer);
	}

	public static void main(String args[]) {
		Main gwuMain = new Main();
		gwuMain.init();
	}

}
